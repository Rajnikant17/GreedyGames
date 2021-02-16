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
import com.example.greedygamesproject.adapter.ArtistAdapter
import com.example.greedygamesproject.databinding.FragmentArtistListingBinding
import com.example.greedygamesproject.util.DataState
import com.example.greedygamesproject.viewmodels.GenreDetailsPageViewMode
import com.example.moduleforapiservices.models.Artist
import com.example.moduleforapiservices.models.ModelArtistApi

class ArtistFragment : Fragment(), ArtistAdapter.ArtistItemClickInterface {
    val genreDetailsPageViewMode: GenreDetailsPageViewMode by viewModels({ requireParentFragment() })
    lateinit var fragmentArtistListingBinding: FragmentArtistListingBinding
    var artistAdapter: ArtistAdapter? = null
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentArtistListingBinding =
            FragmentArtistListingBinding.inflate(inflater, container, false)
        return fragmentArtistListingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        observeArtistApiData()
    }

    fun observeArtistApiData() {
        genreDetailsPageViewMode.mutableLiveDataArtist.observe(
            viewLifecycleOwner,
            Observer { dataState ->
                when (dataState) {
                    is DataState.Success<ModelArtistApi> -> {
                        fragmentArtistListingBinding.progressbar.visibility = View.GONE
                        setTopArtistAdapter(dataState.data.topartists.artist)
                    }
                    is DataState.Error -> {
                        fragmentArtistListingBinding.progressbar.visibility = View.GONE
                    }
                    is DataState.Loading -> {
                        if (!fragmentArtistListingBinding.progressbar.isVisible)
                            fragmentArtistListingBinding.progressbar.visibility = View.VISIBLE
                    }
                }
            })
    }

    fun setTopArtistAdapter(artistList: List<Artist>) {
        val linearLayoutManager = LinearLayoutManager(requireActivity())
        fragmentArtistListingBinding.rvArtist.setLayoutManager(linearLayoutManager)
        artistAdapter = ArtistAdapter(
            requireActivity(),
            artistList,
            this
        )
        fragmentArtistListingBinding.rvArtist.adapter = artistAdapter
    }

    override fun artistItemClickMethod(artistName: String) {
        val bundle = Bundle()
        bundle.putString("artistName", artistName)
        navController.navigate(R.id.action_generesDetailsFragment_to_artistDetailFragment, bundle)
    }
}