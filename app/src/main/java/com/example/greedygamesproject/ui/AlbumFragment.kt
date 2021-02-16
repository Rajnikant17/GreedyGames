package com.example.greedygamesproject.ui

import android.os.Bundle
import android.util.Log
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
import com.example.greedygamesproject.R
import com.example.greedygamesproject.adapter.AlbumAdapter
import com.example.greedygamesproject.databinding.FragmentAlbumListingBinding
import com.example.greedygamesproject.util.DataState
import com.example.greedygamesproject.viewmodels.GenreDetailsPageViewMode
import com.example.moduleforapiservices.models.Album
import com.example.moduleforapiservices.models.ModelAlbumApi

class AlbumFragment : Fragment(), AlbumAdapter.AlbumItemClickInterface {
    val genreDetailsPageViewMode: GenreDetailsPageViewMode by viewModels({ requireParentFragment() })
    lateinit var fragmentAlbumListingBinding: FragmentAlbumListingBinding
    var albumAdapter: AlbumAdapter? = null
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentAlbumListingBinding =
            FragmentAlbumListingBinding.inflate(inflater, container, false)
        return fragmentAlbumListingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        super.onViewCreated(view, savedInstanceState)
        observeAlbumApiData()
    }

    fun observeAlbumApiData() {

        genreDetailsPageViewMode.mutableLiveDataAlbum.observe(
            viewLifecycleOwner,
            Observer { dataState ->
                when (dataState) {
                    is DataState.Success<ModelAlbumApi> -> {
                        fragmentAlbumListingBinding.progressbar.visibility = View.GONE
                        setTopAlbumAdapter(dataState.data.albums.album)
                    }
                    is DataState.Error -> {
                        fragmentAlbumListingBinding.progressbar.visibility = View.GONE
                    }
                    is DataState.Loading -> {
                        if (!fragmentAlbumListingBinding.progressbar.isVisible)
                            fragmentAlbumListingBinding.progressbar.visibility = View.VISIBLE
                    }
                }
            })
    }

    fun setTopAlbumAdapter(albumList: List<Album>) {
        val linearLayoutManager = LinearLayoutManager(requireActivity())
        fragmentAlbumListingBinding.rvAlbum.setLayoutManager(linearLayoutManager)
        albumAdapter = AlbumAdapter(
            requireActivity(),
            albumList,
            this
        )
        fragmentAlbumListingBinding.rvAlbum.adapter = albumAdapter
    }

    override fun albumItemClickMethod(artistName: String, albumName: String) {
        val bundle = Bundle()
        bundle.putString("artistName", artistName)
        bundle.putString("albumName", albumName)
        navController.navigate(R.id.action_generesDetailsFragment_to_albumDetailsFragment, bundle)
    }
}