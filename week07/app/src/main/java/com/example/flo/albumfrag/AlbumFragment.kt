package com.example.flo.albumfrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.R
import com.example.flo.activities.MainActivity
import com.example.flo.databinding.FragmentAlbumBinding
import com.example.flo.data.Album
import com.example.flo.data.SongDatabase
import com.example.flo.home.HomeFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class AlbumFragment : Fragment() {
    lateinit var binding: FragmentAlbumBinding
    private var gson: Gson = Gson()

    private val information = arrayListOf("수록곡", "상세정보", "영상")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

//        val albumJson = arguments?.getString("album")
//        val album = gson.fromJson(albumJson, Album::class.java)

        val album = getAlbumDatas() //전달받은 앨범 데이터 가져옴
        setInit(album) //앨범 정보 뷰바인딩

        //홈프래그먼트로의 화면전환
        binding.albumBackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }

        //뷰페이저 어댑터 객체 생성시 앨범 id를 넘겨줌  -> 송프래그먼트(수록곡) 정보를 넘겨줌
        val albumAdapter = AlbumVPAdapter(this, album.id)
        binding.albumContentVp.adapter = albumAdapter

        //탭레이아웃과 연결
        TabLayoutMediator(binding.albumContentTb, binding.albumContentVp) { tap, position ->
            tap.text = information[position]
        }.attach()

        return binding.root

    }
    private fun getAlbumDatas(): Album {
        // 1. arguments로 전달받은 albumId 꺼내기
        val albumId = arguments?.getInt("albumId", 0) ?: 0
        // 2. DB에서 앨범 조회
        val songDB = SongDatabase.getInstance(requireContext())!!
        val album = songDB.albumDao().getAlbum(albumId)
        return album
    }

    //앨범 정보 뷰바인딩
    private fun setInit(album: Album) {
        binding.albumAlbumIv.setImageResource(album.coverImg!!)
        binding.albumMusicTitleTv.text = album.title.toString()
        binding.albumSingerNameTv.text = album.singer.toString()
    }

}