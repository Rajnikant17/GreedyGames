package com.example.greedygamesproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.greedygamesproject.adapter.GenreDetailsPageViewpagerAdapter
import com.example.greedygamesproject.databinding.FragmentGeneresDetailsBinding
import com.example.greedygamesproject.util.DataState
import com.example.greedygamesproject.viewmodels.GenreDetailsPageViewMode
import com.example.moduleforapiservices.models.ModelGenreInfo
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GeneresDetailsFragment : Fragment() {
    lateinit var fragmentGeneresDetailsBinding: FragmentGeneresDetailsBinding
    val genreDetailsPageViewMode: GenreDetailsPageViewMode by viewModels()
    private var genreTag: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            genreTag = it.getString("tag")
        }
        genreTag?.let {
            genreDetailsPageViewMode.callGenreInfo(
                it,
                GenreDetailsPageViewMode.MainStateEvent.GetGenreInfoEvent
            )
        }
        genreTag?.let {
            genreDetailsPageViewMode.callAlbumApi(
                it,
                GenreDetailsPageViewMode.MainStateEvent.GetAlbumEvent
            )
        }
        genreTag?.let {
            genreDetailsPageViewMode.callArtistApi(
                it,
                GenreDetailsPageViewMode.MainStateEvent.GetArtistEvent
            )
        }
        genreTag?.let {
            genreDetailsPageViewMode.callTrackApi(
                it,
                GenreDetailsPageViewMode.MainStateEvent.GetTrackEvent
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentGeneresDetailsBinding =
            FragmentGeneresDetailsBinding.inflate(inflater, container, false)
        return fragmentGeneresDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewpagerAdapter()
        observeGenreInfoData()
    }

    fun observeGenreInfoData() {

        genreDetailsPageViewMode.mutableLiveDataGenreInfo.observe(
            viewLifecycleOwner,
            Observer { dataState ->
                when (dataState) {
                    is DataState.Success<ModelGenreInfo> -> {
                        fragmentGeneresDetailsBinding.progressbar.visibility = View.GONE
                        fragmentGeneresDetailsBinding.tvGenre.text = dataState.data.tag.name
                        fragmentGeneresDetailsBinding.tvGenreDesc.text =
                            dataState.data.tag.wiki.description
                    }
                    is DataState.Error -> {
                        fragmentGeneresDetailsBinding.progressbar.visibility = View.GONE
                    }
                    is DataState.Loading -> {
                        if (!fragmentGeneresDetailsBinding.progressbar.isVisible)
                            fragmentGeneresDetailsBinding.progressbar.visibility = View.VISIBLE
                    }
                }
            })
    }

    fun setViewpagerAdapter() {
        val genreDetailsPageViewpagerAdapter =
            GenreDetailsPageViewpagerAdapter(childFragmentManager)
        fragmentGeneresDetailsBinding.viewpager.setAdapter(genreDetailsPageViewpagerAdapter)
        fragmentGeneresDetailsBinding.tabLayout.setupWithViewPager(fragmentGeneresDetailsBinding.viewpager)
    }
}