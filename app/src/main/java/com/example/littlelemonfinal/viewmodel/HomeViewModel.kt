package com.example.littlelemonfinal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemonfinal.model.common.BaseResult
import com.example.littlelemonfinal.model.data.ErrorResponse
import com.example.littlelemonfinal.model.data.MenuItemNetwork
import com.example.littlelemonfinal.model.data.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val state = MutableStateFlow<HomeScreenState>(HomeScreenState.Init)
    val homeViewState: StateFlow<HomeScreenState> get() = state

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading




    private fun showToast(message: String) {
        state.value = HomeScreenState.ShowToast(message)
    }

    init {
        getMenuFromDataBase()
        fetchMenuFromServer()
    }


    private fun getMenuFromDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.loadMenuFromDatabase()
                .onStart {
                    _isLoading.value=true;
                }
                .catch { e ->
                    _isLoading.value=false;

                    showToast(e.message.toString())
                }
                .collect { menuItems ->
                    _isLoading.value=false;
                    state.value = HomeScreenState.SuccessLoadFromDataBase(menuItems)
                    if (menuItems.isEmpty()) {
                        state.value = HomeScreenState.ShowEmptyState(Unit)
                    }

                }

        }
    }

    private fun fetchMenuFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.fetchMenuItems()
                .onStart {
                    _isLoading.value=true;

                }
                .catch { e ->
                    _isLoading.value=false;

                    showToast(e.message.toString())
                }
                .collect { baseResult ->
                    _isLoading.value=false;

                    when (baseResult) {
                        is BaseResult.Error -> state.value =
                            HomeScreenState.ErrorLoad(baseResult.rawResponse)

                        is BaseResult.Success -> {
                            state.value =
                                HomeScreenState.SuccessLoadFromServer(baseResult.data)
                            homeRepository.saveMenuToDatabase(baseResult.data)
                        }
                    }



        }
    }


}

sealed class HomeScreenState {
    object Init : HomeScreenState()
    data class ShowToast(val message: String) : HomeScreenState()
    data class ShowEmptyState(val x: Unit) : HomeScreenState()
    data class SuccessLoadFromServer(val menuItems: List<MenuItemNetwork>) : HomeScreenState()
    data class SuccessLoadFromDataBase(val menuItems: List<MenuItemNetwork>) : HomeScreenState()
    data class ErrorLoad(val rawResponse: ErrorResponse) : HomeScreenState()
}}