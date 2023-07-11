package com.example.littlelemonfinal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.littlelemonfinal.model.common.BaseResult
import com.example.littlelemonfinal.model.data.ErrorResponse
import com.example.littlelemonfinal.model.data.MenuItemNetwork
import com.example.littlelemonfinal.model.data.repositories.HomeRepository
import com.example.littlelemonfinal.model.services.MenuItemDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
    @Inject
    lateinit var menuItemDao: MenuItemDao
    private val error = MutableLiveData<String>()
    private val progressBarLiveData = MutableLiveData<Boolean>()
    val menuItemsLiveData = MutableLiveData<List<MenuItemNetwork>>()
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        error.postValue(throwable.message)
        progressBarLiveData.postValue(false)
    }


    private val state = MutableStateFlow<HomeScreenState>(HomeScreenState.Init)
    val mState: StateFlow<HomeScreenState> get() = state


    private fun setLoading() {
        state.value = HomeScreenState.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = HomeScreenState.IsLoading(false)
    }

    private fun showToast(message: String) {
        state.value = HomeScreenState.ShowToast(message)
    }


    fun getMenuFromDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.loadMenuFromDatabase()
                .onStart {
                    setLoading()
                }
                .catch { e ->
                    hideLoading()
                    showToast(e.message.toString())
                }
                .collect { baseResult ->
                    hideLoading()
                    state.value = HomeScreenState.SuccessLoadFromDataBase(baseResult)
                    if (baseResult.isEmpty()) {
                        state.value = HomeScreenState.ShowEmptyState(Unit)
                    }

                }

        }
//        return homeRepository.loadMenuFromDatabase()
    }

    fun fetchMenuFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.fetchMenuItems()
                .onStart {
                    setLoading()
                }
                .catch { e ->
                    hideLoading()
                    showToast(e.message.toString())
                }
                .collect { baseResult ->
                    hideLoading()
                    when (baseResult) {
                        is BaseResult.Error -> state.value =
                            HomeScreenState.ErrorLoad(baseResult.rawResponse)

                        is BaseResult.Success -> state.value =
                            HomeScreenState.SuccessLoadFromServer(baseResult.data)
                    }



        }
    }


}

sealed class HomeScreenState {
    object Init : HomeScreenState()
    data class IsLoading(val isLoading: Boolean) : HomeScreenState()
    data class ShowToast(val message: String) : HomeScreenState()
    data class ShowEmptyState(val x: Unit) : HomeScreenState()
    data class SuccessLoadFromServer(val menuItems: List<MenuItemNetwork>) : HomeScreenState()
    data class SuccessLoadFromDataBase(val menuItems: List<MenuItemNetwork>) : HomeScreenState()
    data class ErrorLoad(val rawResponse: ErrorResponse) : HomeScreenState()
}