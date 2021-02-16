package com.example.greedygamesproject.repository

import com.example.greedygamesproject.util.DataState
import com.example.moduleforapiservices.models.ModelAlbumInfoApi
import com.example.moduleforapiservices.utils.ApiServices
import com.example.moduleforapiservices.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BusinessLogicAlbumsDetailsPage
@Inject
constructor(val apiServices: ApiServices) {
    suspend fun callAlbumInfo(
        artistName: String,
        albumName: String
    ): Flow<DataState<ModelAlbumInfoApi>> = flow {
        emit(DataState.Loading)
        try {
            val getAlbumInfoData = apiServices.getAlbumInfoApi(
                Constants.getAlbumInfo,
                artistName,
                albumName,
                Constants.format,
                Constants.api_key
            )
            emit(DataState.Success(getAlbumInfoData))
        } catch (e: Exception) {
            emit(DataState.Error)
        }
    }
}