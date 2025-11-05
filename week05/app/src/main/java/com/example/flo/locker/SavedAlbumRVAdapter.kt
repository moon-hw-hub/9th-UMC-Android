package com.example.flo.locker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flo.databinding.ItemSavedalbumBinding
import com.example.flo.dataclasses.Album

class SavedAlbumRVAdapter(private var albumList: ArrayList<Album>):
    RecyclerView.Adapter<SavedAlbumRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): SavedAlbumRVAdapter.ViewHolder {
        val binding = ItemSavedalbumBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedAlbumRVAdapter.ViewHolder, position: Int) {
        holder.bind(albumList[position])
    }

    override fun getItemCount(): Int = albumList.size

    inner class ViewHolder(var binding: ItemSavedalbumBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.albumTitleTv.text = album.title
            binding.albumSingerNameTv.text = album.singer
            binding.itemAlbumCoverImgIv.setImageResource(album.coverImage!!)
            binding.albumInformationTv.text = album.information
        }

    }

}