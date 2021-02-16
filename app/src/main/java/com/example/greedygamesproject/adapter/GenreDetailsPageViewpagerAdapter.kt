package com.example.greedygamesproject.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.greedygamesproject.ui.AlbumFragment
import com.example.greedygamesproject.ui.ArtistFragment
import com.example.greedygamesproject.ui.TrackFragment

class GenreDetailsPageViewpagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val tabTitles =
        arrayOf("Albums", "Artists", "Tracks")

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return AlbumFragment()
            1 -> return ArtistFragment()
            else -> {
                return TrackFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}