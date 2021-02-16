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
import com.example.greedygamesproject.adapter.TrackAdapter
import com.example.greedygamesproject.databinding.FragmentTrackListingBinding
import com.example.greedygamesproject.util.DataState
import com.example.greedygamesproject.viewmodels.GenreDetailsPageViewMode
import com.example.moduleforapiservices.models.ModelTrackApi
import com.example.moduleforapiservices.models.Track

class TrackFragment : Fragment() {
    val genreDetailsPageViewMode: GenreDetailsPageViewMode by viewModels({ requireParentFragment() })
    lateinit var fragmentTrackListingBinding: FragmentTrackListingBinding
    var trackAdapter: TrackAdapter? = null
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentTrackListingBinding =
            FragmentTrackListingBinding.inflate(inflater, container, false)
        return fragmentTrackListingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        observeTrackApiData()
    }

    fun observeTrackApiData() {
        genreDetailsPageViewMode.mutableLiveDataTrack.observe(
            viewLifecycleOwner,
            Observer { dataState ->
                when (dataState) {
                    is DataState.Success<ModelTrackApi> -> {
                        fragmentTrackListingBinding.progressbar.visibility = View.GONE
                        setTopTrackAdapter(dataState.data.tracks.track)
                    }
                    is DataState.Error -> {
                        fragmentTrackListingBinding.progressbar.visibility = View.GONE
                    }
                    is DataState.Loading -> {
                        if (!fragmentTrackListingBinding.progressbar.isVisible)
                            fragmentTrackListingBinding.progressbar.visibility = View.VISIBLE
                    }
                }
            })
    }

    fun setTopTrackAdapter(trackList: List<Track>) {
        val linearLayoutManager = LinearLayoutManager(requireActivity())
        fragmentTrackListingBinding.rvTrack.setLayoutManager(linearLayoutManager)
        trackAdapter = TrackAdapter(
            requireActivity(),
            trackList
        )
        fragmentTrackListingBinding.rvTrack.adapter = trackAdapter
    }
}