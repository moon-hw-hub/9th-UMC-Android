package com.example.flo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flo.databinding.ItemSavedsongBinding

class SavedsongRVAdapter(private var savedsongList: ArrayList<SavedSong>): RecyclerView.Adapter<SavedsongRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): SavedsongRVAdapter.ViewHolder {
        var binding: ItemSavedsongBinding = ItemSavedsongBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(savedsongList[position])

    }

    override fun getItemCount():Int = savedsongList.size

    inner class ViewHolder(var binding: ItemSavedsongBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(savedsong: SavedSong) {
            binding.songListAlbumImgIv.setImageResource(savedsong.img)
            binding.songMusicTitleTv.text = savedsong.title
            binding.songSingerNameTv.text = savedsong.singer
        }

    }
}