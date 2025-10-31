package com.example.flo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flo.databinding.FragmentAlbumBinding
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

        val albumJson = arguments?.getString("album")
        val album = gson.fromJson(albumJson, Album::class.java)
        setInit(album)

        //홈프래그먼트로의 화면전환
        binding.albumBackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }

        //디테일 프래그먼트로의 데이터 전달(왜안되는거야 왜 로그캣에 널이 뜨는데)
        val detailFragment = DetailFragment()

        val bundle = Bundle().apply {
            putString("singerName", binding.albumSingerNameTv.toString())
            putString("albumTitle", binding.albumMusicTitleTv.toString())
        }
        detailFragment.arguments = bundle

        //어댑터 객체 생성시 앨범 인스턴스도 넘겨줌  -> 송프래그먼트에 수록곡 정보를 넘겨주기위함
        val albumAdapter = AlbumVPAdapter(this, album)
        binding.albumContentVp.adapter = albumAdapter
        TabLayoutMediator(binding.albumContentTb, binding.albumContentVp) {
            tap, position ->
            tap.text = information[position]

        }.attach()

        return binding.root

    }
    private fun setInit(album: Album) {
        binding.albumAlbumIv.setImageResource(album.coverImage!!)
        binding.albumMusicTitleTv.text = album.title.toString()
        binding.albumSingerNameTv.text = album.singer.toString()
    }



}