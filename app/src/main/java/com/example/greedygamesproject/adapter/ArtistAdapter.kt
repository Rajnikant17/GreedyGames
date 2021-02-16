package com.example.greedygamesproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.greedygamesproject.BR
import com.example.greedygamesproject.R
import com.example.greedygamesproject.databinding.ArtistRowBinding
import com.example.moduleforapiservices.models.Artist

class ArtistAdapter(
    val context: Context,
    private val itemList: List<Artist>,
    val itemClickInterface: ArtistItemClickInterface
) : RecyclerView.Adapter<ArtistAdapter.Myhandler>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myhandler {
        val artistRowBinding =
            ArtistRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return Myhandler(artistRowBinding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: Myhandler, position: Int) {
        holder.bind(itemList.get(position))
        holder.binding.llArtistparent.setOnClickListener {
            itemClickInterface.artistItemClickMethod(itemList.get(position).name)
        }
        if (itemList.get(position).image.isNotEmpty()) {
            Glide.with(context)
                .load(itemList.get(position).image.get(2).text)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.binding.ivArtist)
        } else {
            Glide.with(context)
                .load(R.drawable.placeholder)
                .into(holder.binding.ivArtist)
        }
    }

    class Myhandler(val binding: ArtistRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(artist: Artist) {
            binding.artistData = artist
            binding.setVariable(BR.artistData, artist)
            binding.executePendingBindings()
        }
    }

    interface ArtistItemClickInterface {
        fun artistItemClickMethod(artistName: String)
    }
}