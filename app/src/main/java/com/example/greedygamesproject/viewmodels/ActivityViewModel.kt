package com.example.greedygamesproject.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.greedygamesproject.repository.ApiCallBusinessLogic
import com.example.greedygamesproject.util.DataState
import com.example.moduleforapiservices.models.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ActivityViewModel
@ViewModelInject
constructor(application: Application, private val apiCallBusinessLogic: ApiCallBusinessLogic) :
    AndroidViewModel(application) {
    var mutableLiveDataGenre: MutableLiveData<DataState<ModelGenreApi>> = MutableLiveData()
    lateinit var fullItemArrayList: ArrayList<GenreTag>
    var isGenreExpanded = false
    val finalArrayListToBeSetInAdapter = arrayListOf<GenreTag>()
    fun callHomePageGenreApi(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetHomePageEvent -> {
                    apiCallBusinessLogic.callHomePageApi().onEach { dataState ->
                        mutableLiveDataGenre.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    sealed class MainStateEvent {

        object GetHomePageEvent : MainStateEvent()
    }
}