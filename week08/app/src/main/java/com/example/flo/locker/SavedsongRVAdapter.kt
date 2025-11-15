package com.example.flo.locker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flo.databinding.ItemSavedsongBinding
import com.example.flo.data.Song

class SavedsongRVAdapter(): RecyclerView.Adapter<SavedsongRVAdapter.ViewHolder>() {
    private var songs = ArrayList<Song>()

    interface MyItemClickListener {
        //fun onItemClick(savedSong: SavedSong)
        fun onRemoveSong(songId: Int)
    }

    private lateinit var myItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        myItemClickListener = itemClickListener
    }


    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        var binding: ItemSavedsongBinding = ItemSavedsongBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(songs[position])
        holder.binding.songMoreIv.setOnClickListener {
            myItemClickListener.onRemoveSong(songs[position].id)
            removeSong(position) //리사이클러뷰에서 삭제
        }

    }

    override fun getItemCount():Int = songs.size

    @SuppressLint("NotifyDataSetChanged")
    fun addSongs(songs: ArrayList<Song>) {
        this.songs.clear()
        this.songs.addAll(songs)

        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeSong(position: Int) {
        songs.removeAt(position)
        notifyDataSetChanged()
    }
    inner class ViewHolder(var binding: ItemSavedsongBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            binding.songListAlbumImgIv.setImageResource(song.coverImg!!)
            binding.songMusicTitleTv.text = song.title
            binding.songSingerNameTv.text = song.singer
        }

    }
}