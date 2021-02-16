package com.example.greedygamesproject.repository

import com.example.greedygamesproject.util.DataState
import com.example.moduleforapiservices.models.ModelAlbumApi
import com.example.moduleforapiservices.models.ModelArtistApi
import com.example.moduleforapiservices.models.ModelGenreInfo
import com.example.moduleforapiservices.models.ModelTrackApi
import com.example.moduleforapiservices.utils.ApiServices
import com.example.moduleforapiservices.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BusinessLogicGenreDetailsPage
@Inject
constructor(val apiServices: ApiServices) {
    suspend fun callGenreInfo(genreTag: String): Flow<DataState<ModelGenreInfo>> = flow {
        emit(DataState.Loading)
        try {
            val getAlbumData = apiServices.getGenreInfo(
                Constants.getTagInfo, genreTag,
                Constants.format,
                Constants.api_key
            )
            emit(DataState.Success(getAlbumData))
        } catch (e: Exception) {
            emit(DataState.Error)
        }
    }

    suspend fun callAlbumApi(genreTag: String): Flow<DataState<ModelAlbumApi>> = flow {
        emit(DataState.Loading)
        try {
            val getAlbumData = apiServices.getAlbumApi(
                Constants.getTopAlbums, genreTag,
                Constants.format,
                Constants.api_key
            )
            emit(DataState.Success(getAlbumData))
        } catch (e: Exception) {
            emit(DataState.Error)
        }
    }

    suspend fun callArtistApi(genreTag: String): Flow<DataState<ModelArtistApi>> = flow {
        emit(DataState.Loading)
        try {
            val getUserPostList = apiServices.getArtistApi(
                Constants.getTopArtists, genreTag,
                Constants.format,
                Constants.api_key
            )
            emit(DataState.Success(getUserPostList))
        } catch (e: Exception) {
            emit(DataState.Error)
        }
    }

    suspend fun callTrackApi(genreTag: String): Flow<DataState<ModelTrackApi>> = flow {
        emit(DataState.Loading)
        try {
            val getUserPostList = apiServices.getTrackApi(
                Constants.getTopTracks, genreTag,
                Constants.format,
                Constants.api_key
            )
            emit(DataState.Success(getUserPostList))
        } catch (e: Exception) {
            emit(DataState.Error)
        }
    }
}