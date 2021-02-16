package com.example.greedygamesproject.repository

import com.example.greedygamesproject.util.DataState
import com.example.moduleforapiservices.models.ModelGenreApi
import com.example.moduleforapiservices.utils.ApiServices
import com.example.moduleforapiservices.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiCallBusinessLogic
@Inject
constructor(val apiServices: ApiServices) {
    suspend fun callHomePageApi(): Flow<DataState<ModelGenreApi>> = flow {
        emit(DataState.Loading)
        try {
            val getUserPostList = apiServices.getGenreApi(
                Constants.toptageMethod,
                Constants.format,
                Constants.api_key
            )
            emit(DataState.Success(getUserPostList))
        } catch (e: Exception) {
            emit(DataState.Error)
        }
    }
}