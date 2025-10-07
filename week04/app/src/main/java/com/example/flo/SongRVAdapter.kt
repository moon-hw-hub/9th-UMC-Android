package com.example.flo

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.flo.databinding.ItemAlbumBinding
import com.example.flo.databinding.ItemSongBinding


class SongRVAdapter(private var songList: ArrayList<Song>): RecyclerView.Adapter<SongRVAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): SongRVAdapter.ViewHolder {
        var binding: ItemSongBinding = ItemSongBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(songList[position])

    }

    override fun getItemCount():Int = songList.size

    inner class ViewHolder(var binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            binding.songMusicTitleTv.text = song.title
            binding.songSingerNameTv.text = song.singer
        }

    }

}