package com.example.greedygamesproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.greedygamesproject.BR
import com.example.greedygamesproject.R
import com.example.greedygamesproject.databinding.TrackRowBinding
import com.example.moduleforapiservices.models.Track

class TrackAdapter(val context: Context, private val itemList: List<Track>) :
    RecyclerView.Adapter<TrackAdapter.Myhandler>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myhandler {
        val trackRowBinding =
            TrackRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return Myhandler(trackRowBinding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: Myhandler, position: Int) {
        holder.bind(itemList.get(position))
        if (itemList.get(position).image.isNotEmpty()) {
            Glide.with(context)
                .load(itemList.get(position).image.get(2).text)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.binding.ivTrack)
        } else {
            Glide.with(context)
                .load(R.drawable.placeholder)
                .into(holder.binding.ivTrack)
        }
    }

    class Myhandler(val binding: TrackRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(track: Track) {
            binding.trackData = track
            binding.setVariable(BR.trackData, track)
            binding.executePendingBindings()
        }
    }
}