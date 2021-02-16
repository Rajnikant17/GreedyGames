package com.example.greedygamesproject.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.greedygamesproject.repository.BusinessLogicArtistDetailPage
import com.example.greedygamesproject.util.DataState
import com.example.moduleforapiservices.models.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ArtistDetailsPageViewModel
@ViewModelInject
constructor(
    application: Application,
    private val apiCallBusinessLogic: BusinessLogicArtistDetailPage
) : AndroidViewModel(application) {
    var mutableLiveDataAlbum: MutableLiveData<DataState<ModelArtistTopAlbum>> = MutableLiveData()
    var mutableLiveDataTrack: MutableLiveData<DataState<ModelArtistTopTrack>> = MutableLiveData()
    var mutableLiveDataArtistInfo: MutableLiveData<DataState<ModelArtistInfoApi>> =
        MutableLiveData()

    fun callArtistInfo(artistName: String, mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetArtistInfoEvent -> {
                    apiCallBusinessLogic.callArtistInfo(artistName).onEach { dataState ->
                        mutableLiveDataArtistInfo.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    fun callAlbumApi(artistName: String, mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetAlbumEvent -> {
                    apiCallBusinessLogic.callAlbumApi(artistName).onEach { dataState ->
                        mutableLiveDataAlbum.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    fun callTrackApi(artistName: String, mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetTrackEvent -> {
                    apiCallBusinessLogic.callTrackApi(artistName).onEach { dataState ->
                        mutableLiveDataTrack.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    sealed class MainStateEvent {

        object GetArtistInfoEvent : MainStateEvent()
        object GetAlbumEvent : MainStateEvent()
        object GetTrackEvent : MainStateEvent()
    }


}