package com.example.flo.albumfrag

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    lateinit var binding : FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        //앨범프래그먼트에서 전달한 데이터를 받음
        val singerName = arguments?.getString("singerName")
        val albumTitle = arguments?.getString("albumTitle")

        Log.d("DetailFragment", "받은 가수 이름: $singerName")
        Log.d("DetailFragment", "받은 앨범 제목: $albumTitle")


        binding.singerNameTv.text = singerName
        binding.albumNameTv.text = albumTitle


        return binding.root
    }


}