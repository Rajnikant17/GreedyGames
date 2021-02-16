package com.example.greedygamesproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.greedygamesproject.R
import com.example.greedygamesproject.adapter.GenreAdapter
import com.example.greedygamesproject.databinding.FragmentHomeBinding
import com.example.greedygamesproject.util.DataState
import com.example.greedygamesproject.viewmodels.ActivityViewModel
import com.example.moduleforapiservices.models.GenreTag
import com.example.moduleforapiservices.models.ModelGenreApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), GenreAdapter.GenreItemClickInterface {
    val activityViewModel: ActivityViewModel by activityViewModels()
    var genreAdapter: GenreAdapter? = null
    lateinit var navController: NavController
    lateinit var fragmentHomeBinding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel.callHomePageGenreApi(ActivityViewModel.MainStateEvent.GetHomePageEvent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        genreAdapter = null
        observeGenreData()
        fragmentHomeBinding.tvViewAllViewLess.setOnClickListener {
            if (activityViewModel.isGenreExpanded) {
                activityViewModel.isGenreExpanded = false
                fragmentHomeBinding.tvViewAllViewLess.text = getString(R.string.view_all)
                setAdapter(activityViewModel.fullItemArrayList.subList(0, 7))
            } else {
                activityViewModel.isGenreExpanded = true
                fragmentHomeBinding.tvViewAllViewLess.text = getString(R.string.view_less)
                setAdapter(activityViewModel.fullItemArrayList)
            }
        }
    }


    fun observeGenreData() {
        activityViewModel.mutableLiveDataGenre.observe(
            viewLifecycleOwner,
            Observer { dataState ->
                when (dataState) {
                    is DataState.Success<ModelGenreApi> -> {
                        fragmentHomeBinding.progressbar.visibility = View.GONE
                        activityViewModel.fullItemArrayList = dataState.data.toptags.tag
                        if (dataState.data.toptags.tag.size > 6) {
                            setAdapter(activityViewModel.fullItemArrayList.subList(0, 7))
                        } else
                            setAdapter(activityViewModel.fullItemArrayList)
                    }
                    is DataState.Error -> {
                        fragmentHomeBinding.progressbar.visibility = View.GONE
                    }
                    is DataState.Loading -> {
                        if (!fragmentHomeBinding.progressbar.isVisible)
                            fragmentHomeBinding.progressbar.visibility = View.VISIBLE
                    }
                }
            })
    }

    fun setAdapter(genreTagList: List<GenreTag>) {
        activityViewModel.finalArrayListToBeSetInAdapter.clear()
        activityViewModel.finalArrayListToBeSetInAdapter.addAll(genreTagList)
        if (genreAdapter == null) {
            val linearLayoutManager = LinearLayoutManager(requireActivity())
            fragmentHomeBinding.recyclerview.setLayoutManager(linearLayoutManager)
            genreAdapter = GenreAdapter(
                requireActivity(),
                activityViewModel.finalArrayListToBeSetInAdapter,
                this
            )
            fragmentHomeBinding.recyclerview.adapter = genreAdapter
        } else {
            fragmentHomeBinding.recyclerview.adapter?.notifyDataSetChanged()
        }
    }

    override fun genreItemClickMethod(tag: String) {
        val bundle = Bundle()
        bundle.putString("tag", tag)
        navController.navigate(R.id.action_homeFragment_to_generesDetailsFragment, bundle)
    }
}