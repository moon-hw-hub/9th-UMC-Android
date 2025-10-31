package com.example.flo.albumfrag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flo.databinding.ItemSongBinding
import com.example.flo.dataclasses.Song

class SongRVAdapter(private var songList: ArrayList<Song>): RecyclerView.Adapter<SongRVAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
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
            binding.songListOrderTv.text = song.num
            //binding.songListAlbumImgIv.setImageResource(song.img)
            binding.songMusicTitleTv.text = song.title
            binding.songSingerNameTv.text = song.singer
        }

    }

}