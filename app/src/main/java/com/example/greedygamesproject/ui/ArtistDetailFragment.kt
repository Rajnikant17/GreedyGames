package com.example.greedygamesproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.greedygamesproject.R
import com.example.greedygamesproject.adapter.AlbumAdapter
import com.example.greedygamesproject.adapter.GenreAdapter
import com.example.greedygamesproject.adapter.TrackAdapter
import com.example.greedygamesproject.databinding.FragmentArtistDetailBinding
import com.example.greedygamesproject.util.DataState
import com.example.greedygamesproject.viewmodels.ArtistDetailsPageViewModel
import com.example.moduleforapiservices.models.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistDetailFragment : Fragment(), GenreAdapter.GenreItemClickInterface,
    AlbumAdapter.AlbumItemClickInterface {
    val artistDetailsPageViewModel: ArtistDetailsPageViewModel by viewModels()
    lateinit var fragmentArtistDetailBinding: FragmentArtistDetailBinding
    var genreAdapter: GenreAdapter? = null
    var trackAdapter: TrackAdapter? = null
    var albumAdapter: AlbumAdapter? = null
    private var artistName: String? = null
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            artistName = it.getString("artistName")
        }
        artistName?.let {
            artistDetailsPageViewModel.callArtistInfo(
                it,
                ArtistDetailsPageViewModel.MainStateEvent.GetArtistInfoEvent
            )
        }
        artistName?.let {
            artistDetailsPageViewModel.callAlbumApi(
                it,
                ArtistDetailsPageViewModel.MainStateEvent.GetAlbumEvent
            )
        }
        artistName?.let {
            artistDetailsPageViewModel.callTrackApi(
                it,
                ArtistDetailsPageViewModel.MainStateEvent.GetTrackEvent
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentArtistDetailBinding =
            FragmentArtistDetailBinding.inflate(inflater, container, false)
        return fragmentArtistDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        observeArtistInfo()
        observeTopAlbum()
        observeTopTrack()
    }


    fun observeArtistInfo() {
        artistDetailsPageViewModel.mutableLiveDataArtistInfo.observe(
            viewLifecycleOwner,
            Observer { dataState ->
                when (dataState) {
                    is DataState.Success<ModelArtistInfoApi> -> {
                        fragmentArtistDetailBinding.progressbar.visibility = View.GONE
                        setGenreAdapter(dataState.data.artist.tags.tag)
                        setArtistDetails(
                            dataState.data.artist.image,
                            dataState.data.artist.name,
                            dataState.data.artist.stats.listeners,
                            dataState.data.artist.stats.playcount,
                            dataState.data.artist.bio.description
                        )
                    }
                    is DataState.Error -> {
                        fragmentArtistDetailBinding.progressbar.visibility = View.GONE
                    }
                    is DataState.Loading -> {
                        if (!fragmentArtistDetailBinding.progressbar.isVisible)
                            fragmentArtistDetailBinding.progressbar.visibility = View.VISIBLE
                    }
                }
            })
    }

    fun setArtistDetails(
        imageurlList: List<Image>,
        title: String,
        tvFollowers: String,
        tvLikecount: String,
        tvAlbumDesc: String
    ) {
        fragmentArtistDetailBinding.tvTitle.text = title
        fragmentArtistDetailBinding.tvFollowers.text = tvFollowers
        fragmentArtistDetailBinding.tvLikecount.text = tvLikecount
        fragmentArtistDetailBinding.tvAlbumDesc.text = tvAlbumDesc

        if (imageurlList.isNotEmpty()) {
            Glide.with(this)
                .load(imageurlList.get(2).text)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(fragmentArtistDetailBinding.ivArtist)
        } else {
            Glide.with(this)
                .load(R.drawable.placeholder)
                .centerCrop()
                .into(fragmentArtistDetailBinding.ivArtist)
        }
    }

    fun observeTopAlbum() {
        artistDetailsPageViewModel.mutableLiveDataAlbum.observe(
            viewLifecycleOwner,
            Observer { dataState ->
                when (dataState) {
                    is DataState.Success<ModelArtistTopAlbum> -> {
                        fragmentArtistDetailBinding.progressbar.visibility = View.GONE
                        setTopAlbumAdapter(dataState.data.topalbums.album)
                    }
                    is DataState.Error -> {
                        fragmentArtistDetailBinding.progressbar.visibility = View.GONE
                    }
                    is DataState.Loading -> {
                        if (!fragmentArtistDetailBinding.progressbar.isVisible)
                            fragmentArtistDetailBinding.progressbar.visibility = View.VISIBLE
                    }
                }
            })
    }

    fun observeTopTrack() {
        artistDetailsPageViewModel.mutableLiveDataTrack.observe(
            viewLifecycleOwner,
            Observer { dataState ->
                when (dataState) {
                    is DataState.Success<ModelArtistTopTrack> -> {
                        fragmentArtistDetailBinding.progressbar.visibility = View.GONE
                        setTopTrackAdapter(dataState.data.toptracks.track)
                    }
                    is DataState.Error -> {
                        fragmentArtistDetailBinding.progressbar.visibility = View.GONE
                    }
                    is DataState.Loading -> {
                        if (!fragmentArtistDetailBinding.progressbar.isVisible)
                            fragmentArtistDetailBinding.progressbar.visibility = View.VISIBLE
                    }
                }
            })
    }

    fun setGenreAdapter(genreTagList: List<GenreTag>) {
        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        fragmentArtistDetailBinding.rvGenre.setLayoutManager(linearLayoutManager)
        genreAdapter = GenreAdapter(
            requireActivity(),
            genreTagList,
            this
        )
        fragmentArtistDetailBinding.rvGenre.adapter = genreAdapter
    }

    fun setTopTrackAdapter(trackList: List<Track>) {
        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        fragmentArtistDetailBinding.rvToptracks.setLayoutManager(linearLayoutManager)
        trackAdapter = TrackAdapter(
            requireActivity(),
            trackList
        )
        fragmentArtistDetailBinding.rvToptracks.adapter = trackAdapter
    }

    fun setTopAlbumAdapter(albumList: List<Album>) {
        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        fragmentArtistDetailBinding.rvTopalbums.setLayoutManager(linearLayoutManager)
        albumAdapter = AlbumAdapter(
            requireActivity(),
            albumList,
            this
        )
        fragmentArtistDetailBinding.rvTopalbums.adapter = albumAdapter
    }


    override fun albumItemClickMethod(artistName: String, albumName: String) {
        val bundle = Bundle()
        bundle.putString("artistName", artistName)
        bundle.putString("albumName", albumName)
        navController.navigate(R.id.action_artistDetailFragment_to_albumDetailsFragment, bundle)
    }

    override fun genreItemClickMethod(tag: String) {
        val bundle = Bundle()
        bundle.putString("tag", tag)
        navController.navigate(R.id.action_artistDetailFragment_to_generesDetailsFragment, bundle)
    }
}