package com.example.flo.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flo.databinding.ItemAlbumBinding
import com.example.flo.dataclasses.Album

class AlbumRVAdapter(private var albumList: ArrayList<Album>): RecyclerView.Adapter<AlbumRVAdapter.ViewHolder>() {

    //리스너 인터페이스 정의
    interface MyItemClickListener {
        fun onItemClick(album: Album)
//        fun onRemoveAlbum(position: Int)
    }
    private lateinit var myItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        myItemClickListener = itemClickListener
    }

    fun addItem(album: Album) {
        albumList.add(album)
        notifyDataSetChanged()
    }

//    fun removeItem(position: Int) {
//        albumList.removeAt(position)
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemAlbumBinding = ItemAlbumBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false
        )
        //아이템 뷰 객체를 던져줌
        return ViewHolder(binding)
    }

    // 뷰홀더에 데이터를 바인딩해줄때마다 호출, 클릭 이벤트 작성
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albumList[position])
        holder.itemView.setOnClickListener { myItemClickListener.onItemClick(albumList[position]) }
        //holder.binding.itemAlbumTitleTv.setOnClickListener { myItemClickListener.onRemoveAlbum(position) }
    }

    //데이터 세트 크기를 알려줌. 리사이클러뷰의 끝이 어딘지
    override fun getItemCount(): Int = albumList.size

    //뷰홀더 클래스
    inner class ViewHolder(var binding: ItemAlbumBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) {
            binding.itemAlbumTitleTv.text = album.title
            binding.itemAlbumSingerTv.text = album.singer
            binding.itemAlbumCoverImgIv.setImageResource(album.coverImage!!)
        }

    }

}