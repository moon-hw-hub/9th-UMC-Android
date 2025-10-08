package com.example.flo

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.example.flo.databinding.ItemSavedsongBinding

class LockerRVAdapter(private var songs: ArrayList<Song>) : RecyclerView.Adapter<LockerRVAdapter.ViewHolder>()
 {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): LockerRVAdapter.ViewHolder {
        val binding: ItemSavedsongBinding = ItemSavedsongBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false
        )

        return ViewHolder(binding)
    }

     override fun onBindViewHolder(
         holder: ViewHolder,
         position: Int
     ) {
         holder.bind(songs[position])
         holder.itemView.setOnClickListener {

         }
     }

    override fun getItemCount(): Int = songs.size

    inner class ViewHolder(var binding: ItemSavedsongBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            binding.songListAlbumImgIv.setImageResource(song.img)
            binding.songMusicTitleTv.text = song.title
            binding.songSingerNameTv.text = song.singer
        }
    }

}