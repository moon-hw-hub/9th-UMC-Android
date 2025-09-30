package com.example.flo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flo.databinding.FragmentAlbumBinding
import com.google.android.material.tabs.TabLayoutMediator


class AlbumFragment : Fragment() {
    lateinit var binding: FragmentAlbumBinding

    private val information = arrayListOf("수록곡", "상세정보", "영상")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        //홈프래그먼트에서 번들로 전달한 값을 받음
        val albumName = arguments?.getString("album")
        val singerName = arguments?.getString("singer")
        binding.albumMusicTitleTv.text = albumName
        binding.albumSingerNameTv.text = singerName

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

        val albumAdapter = AlbumVPAdapter(this)
        binding.albumContentVp.adapter = albumAdapter
        TabLayoutMediator(binding.albumContentTb, binding.albumContentVp) {
            tap, position ->
            tap.text = information[position]

        }.attach()

        return binding.root


    }



}