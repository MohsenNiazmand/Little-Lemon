package com.example.littlelemonfinal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemonfinal.model.data.MenuItemNetwork
import com.example.littlelemonfinal.model.data.MenuNetworkData
import com.example.littlelemonfinal.model.data.repositories.HomeRepository
import com.example.littlelemonfinal.model.services.MenuItemDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository): ViewModel() {
    @Inject lateinit var menuItemDao: MenuItemDao
    private val error = MutableLiveData<String>()
    private val progressBarLiveData = MutableLiveData<Boolean>()
    val menuItemsLiveData=MutableLiveData<List<MenuItemNetwork>>()
    private val coroutineExceptionHandler= CoroutineExceptionHandler{ _, throwable ->
        error.postValue(throwable.message)
        progressBarLiveData.postValue(false)
    }

    fun getMenuFromDataBase(): LiveData<List<MenuItemNetwork>>{
        return homeRepository.loadMenuFromDatabase()
    }

    fun  fetchMenuFromServer(){

        progressBarLiveData.postValue(true)
        viewModelScope.launch(Dispatchers.IO+coroutineExceptionHandler) {
            val menuItems=homeRepository.fetchMenuItems()
            menuItemsLiveData.postValue(menuItems)
            menuItemDao.insertAll(*menuItems.toTypedArray())
            progressBarLiveData.postValue(false)



        }

    }


}