package com.example.flo

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.flo.databinding.ItemAlbumBinding
import com.example.flo.databinding.ItemSongBinding


class SongRVAdapter(private var trackList: ArrayList<TrackSong>): RecyclerView.Adapter<SongRVAdapter.ViewHolder>(){
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
        holder.bind(trackList[position])

    }

    override fun getItemCount():Int = trackList.size


    inner class ViewHolder(var binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(trackSong: TrackSong) {
            binding.songMusicTitleTv.text = trackSong.title
            binding.songSingerNameTv.text = trackSong.singer
        }

    }

}