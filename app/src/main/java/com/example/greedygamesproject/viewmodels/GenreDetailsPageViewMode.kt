package com.example.greedygamesproject.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.greedygamesproject.repository.BusinessLogicGenreDetailsPage
import com.example.greedygamesproject.util.DataState
import com.example.moduleforapiservices.models.ModelAlbumApi
import com.example.moduleforapiservices.models.ModelArtistApi
import com.example.moduleforapiservices.models.ModelGenreInfo
import com.example.moduleforapiservices.models.ModelTrackApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class GenreDetailsPageViewMode
@ViewModelInject
constructor(
    application: Application,
    private val apiCallBusinessLogic: BusinessLogicGenreDetailsPage
) : AndroidViewModel(application) {
    var mutableLiveDataAlbum: MutableLiveData<DataState<ModelAlbumApi>> = MutableLiveData()
    var mutableLiveDataArtist: MutableLiveData<DataState<ModelArtistApi>> = MutableLiveData()
    var mutableLiveDataTrack: MutableLiveData<DataState<ModelTrackApi>> = MutableLiveData()
    var mutableLiveDataGenreInfo: MutableLiveData<DataState<ModelGenreInfo>> = MutableLiveData()


    fun callGenreInfo(genreTag: String, mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetGenreInfoEvent -> {
                    apiCallBusinessLogic.callGenreInfo(genreTag).onEach { dataState ->
                        mutableLiveDataGenreInfo.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    fun callAlbumApi(genreTag: String, mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetAlbumEvent -> {
                    apiCallBusinessLogic.callAlbumApi(genreTag).onEach { dataState ->
                        mutableLiveDataAlbum.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    fun callArtistApi(genreTag: String, mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetArtistEvent -> {
                    apiCallBusinessLogic.callArtistApi(genreTag).onEach { dataState ->
                        mutableLiveDataArtist.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    fun callTrackApi(genreTag: String, mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetTrackEvent -> {
                    apiCallBusinessLogic.callTrackApi(genreTag).onEach { dataState ->
                        mutableLiveDataTrack.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    sealed class MainStateEvent {
        object GetGenreInfoEvent : MainStateEvent()
        object GetAlbumEvent : MainStateEvent()
        object GetArtistEvent : MainStateEvent()
        object GetTrackEvent : MainStateEvent()
    }
}