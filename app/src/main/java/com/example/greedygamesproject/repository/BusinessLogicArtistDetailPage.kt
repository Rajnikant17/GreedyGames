package com.example.greedygamesproject.repository

import com.example.greedygamesproject.util.DataState
import com.example.moduleforapiservices.models.*
import com.example.moduleforapiservices.utils.ApiServices
import com.example.moduleforapiservices.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BusinessLogicArtistDetailPage
@Inject
constructor(val apiServices: ApiServices) {

    suspend fun callArtistInfo(artistName: String): Flow<DataState<ModelArtistInfoApi>> = flow {
        emit(DataState.Loading)
        try {
            val getArtistInfoData = apiServices.getArtistInfoApi(
                Constants.getArtistInfo,
                artistName,
                Constants.format,
                Constants.api_key
            )
            emit(DataState.Success(getArtistInfoData))
        } catch (e: Exception) {
            emit(DataState.Error)
        }
    }


    suspend fun callAlbumApi(artistName: String): Flow<DataState<ModelArtistTopAlbum>> = flow {
        emit(DataState.Loading)
        try {
            val getAlbumData = apiServices.getArtistTopAlbum(
                Constants.getArtistTopAlbum, artistName,
                Constants.format,
                Constants.api_key
            )
            emit(DataState.Success(getAlbumData))
        } catch (e: Exception) {
            emit(DataState.Error)
        }
    }

    suspend fun callTrackApi(artistName: String): Flow<DataState<ModelArtistTopTrack>> = flow {
        emit(DataState.Loading)
        try {
            val getUserPostList = apiServices.getArtistTopTrack(
                Constants.getArtistTopTrack, artistName,
                Constants.format,
                Constants.api_key
            )
            emit(DataState.Success(getUserPostList))
        } catch (e: Exception) {
            emit(DataState.Error)
        }
    }
}