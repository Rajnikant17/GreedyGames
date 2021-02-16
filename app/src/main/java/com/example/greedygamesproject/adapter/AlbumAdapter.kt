package com.example.greedygamesproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.greedygamesproject.BR
import com.example.greedygamesproject.R
import com.example.greedygamesproject.databinding.AlbumRowBinding
import com.example.moduleforapiservices.models.Album

class AlbumAdapter(
    val context: Context,
    private val itemList: List<Album>,
    val itemClickInterface: AlbumItemClickInterface
) : RecyclerView.Adapter<AlbumAdapter.Myhandler>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myhandler {
        val albumRowBinding =
            AlbumRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return Myhandler(albumRowBinding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: Myhandler, position: Int) {
        holder.bind(itemList.get(position))
        holder.binding.llAlbumparent.setOnClickListener {
            itemClickInterface.albumItemClickMethod(
                itemList.get(position).artist.name,
                itemList.get(position).name
            )
        }
        if (itemList.get(position).image.isNotEmpty()) {
            Glide.with(context)
                .load(itemList.get(position).image.get(2).text)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.binding.ivAlbum)
        } else {
            Glide.with(context)
                .load(R.drawable.placeholder)
                .into(holder.binding.ivAlbum)
        }
    }

    class Myhandler(val binding: AlbumRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.albumData = album
            binding.setVariable(BR.albumData, album)
            binding.executePendingBindings()
        }
    }

    interface AlbumItemClickInterface {
        fun albumItemClickMethod(artistName: String, albumName: String)
    }
}