package com.example.greedygamesproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greedygamesproject.BR
import com.example.greedygamesproject.R
import com.example.greedygamesproject.databinding.GenreAdapterBinding
import com.example.moduleforapiservices.models.GenreTag

class GenreAdapter(
    val context: Context,
    private val itemList: List<GenreTag>,
    val itemClickInterface: GenreItemClickInterface
) : RecyclerView.Adapter<GenreAdapter.Myhandler>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myhandler {
        val genreAdapterBinding =
            GenreAdapterBinding.inflate(LayoutInflater.from(context), parent, false)
        return Myhandler(genreAdapterBinding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: Myhandler, position: Int) {
        holder.bind(itemList.get(position))
        holder.tv_genre.setOnClickListener {
            itemClickInterface.genreItemClickMethod(itemList.get(position).name)
        }
    }

    class Myhandler(val binding: GenreAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val tv_genre = binding.root.findViewById<AppCompatTextView>(R.id.tv_genre)
        fun bind(genreTag: GenreTag) {
            binding.genreData = genreTag
            binding.setVariable(BR.genreData, genreTag)
            binding.executePendingBindings()
        }
    }

    interface GenreItemClickInterface {
        fun genreItemClickMethod(tag: String)
    }
}