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
import com.example.greedygamesproject.adapter.GenreAdapter
import com.example.greedygamesproject.databinding.FragmentAlbumDetailsBinding
import com.example.greedygamesproject.util.DataState
import com.example.greedygamesproject.viewmodels.AlbumDetailsPageViewModel
import com.example.moduleforapiservices.models.GenreTag
import com.example.moduleforapiservices.models.Image
import com.example.moduleforapiservices.models.ModelAlbumInfoApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailsFragment : Fragment(), GenreAdapter.GenreItemClickInterface {
    val albumDetailsPageViewModel: AlbumDetailsPageViewModel by viewModels()

    lateinit var fragmentAlbumDetailsBinding: FragmentAlbumDetailsBinding
    var genreAdapter: GenreAdapter? = null
    private var artistName: String? = null
    private var albumName: String? = null
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            artistName = it.getString("artistName")
            albumName = it.getString("albumName")
        }
        artistName?.let {
            albumName?.let { it1 ->
                albumDetailsPageViewModel.callAlbumInfo(
                    it,
                    it1, AlbumDetailsPageViewModel.MainStateEvent.GetAlbumInfoEvent
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentAlbumDetailsBinding =
            FragmentAlbumDetailsBinding.inflate(inflater, container, false)
        return fragmentAlbumDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        observeAlbumInfoData()
    }

    fun observeAlbumInfoData() {
        albumDetailsPageViewModel.mutableLiveDataAlbumInfo.observe(
            viewLifecycleOwner,
            Observer { dataState ->
                when (dataState) {
                    is DataState.Success<ModelAlbumInfoApi> -> {
                        fragmentAlbumDetailsBinding.progressbar.visibility = View.GONE
                        setAlbumDetails(
                            dataState.data.album.image,
                            dataState.data.album.name,
                            dataState.data.album.artist,
                            dataState.data.album.wiki.description
                        )
                        setGenreAdapter(dataState.data.album.tags.tag)
                    }
                    is DataState.Error -> {
                        fragmentAlbumDetailsBinding.progressbar.visibility = View.GONE
                    }
                    is DataState.Loading -> {
                        if (!fragmentAlbumDetailsBinding.progressbar.isVisible)
                            fragmentAlbumDetailsBinding.progressbar.visibility = View.VISIBLE
                    }
                }
            })
    }

    fun setAlbumDetails(imageurlList: List<Image>, title: String, artist: String, desc: String) {

        fragmentAlbumDetailsBinding.tvTitle.text = title
        fragmentAlbumDetailsBinding.tvArtist.text = artist
        fragmentAlbumDetailsBinding.tvAlbumdesc.text = desc

        if (imageurlList.isNotEmpty()) {
            Glide.with(this)
                .load(imageurlList.get(2).text)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(fragmentAlbumDetailsBinding.ivAlbum)
        } else {
            Glide.with(this)
                .load(R.drawable.placeholder)
                .centerCrop()
                .into(fragmentAlbumDetailsBinding.ivAlbum)
        }
    }

    fun setGenreAdapter(genreTagList: List<GenreTag>) {
        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        fragmentAlbumDetailsBinding.rvGenre.setLayoutManager(linearLayoutManager)
        genreAdapter = GenreAdapter(
            requireActivity(),
            genreTagList,
            this
        )
        fragmentAlbumDetailsBinding.rvGenre.adapter = genreAdapter
    }

    override fun genreItemClickMethod(tag: String) {
        val bundle = Bundle()
        bundle.putString("tag", tag)
        navController.navigate(R.id.action_albumDetailsFragment_to_generesDetailsFragment, bundle)
    }
}