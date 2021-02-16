package com.example.greedygamesproject.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.greedygamesproject.repository.BusinessLogicAlbumsDetailsPage
import com.example.greedygamesproject.util.DataState
import com.example.moduleforapiservices.models.ModelAlbumInfoApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AlbumDetailsPageViewModel
@ViewModelInject
constructor(
    application: Application,
    private val apiCallBusinessLogic: BusinessLogicAlbumsDetailsPage
) : AndroidViewModel(application) {
    var mutableLiveDataAlbumInfo: MutableLiveData<DataState<ModelAlbumInfoApi>> = MutableLiveData()
    fun callAlbumInfo(artistName: String, albumName: String, mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetAlbumInfoEvent -> {
                    apiCallBusinessLogic.callAlbumInfo(artistName, albumName).onEach { dataState ->
                        mutableLiveDataAlbumInfo.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    sealed class MainStateEvent {

        object GetAlbumInfoEvent : MainStateEvent()
    }
}