package com.example.flo.locker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flo.databinding.ItemSavedsongBinding
import com.example.flo.dataclasses.SavedSong

class SavedsongRVAdapter(private var savedsongList: ArrayList<SavedSong>): RecyclerView.Adapter<SavedsongRVAdapter.ViewHolder>() {

    interface MyItemClickListener {
        //fun onItemClick(savedSong: SavedSong)
        fun onRemoveSong(position: Int)
    }
    private lateinit var myItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        myItemClickListener = itemClickListener
    }

    fun addItem(savedSong: SavedSong) {
        savedsongList.add(savedSong)
        notifyDataSetChanged()
    }

    //아이템 삭제용
    fun removeItem(position: Int) {
        savedsongList.removeAt(position)
        notifyDataSetChanged()
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
        holder.bind(savedsongList[position])
        holder.binding.songMoreIv.setOnClickListener { myItemClickListener.onRemoveSong(position) }

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