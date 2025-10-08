package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentSavedalbumBinding
import com.example.flo.databinding.FragmentSavedsongBinding
import android.util.Log

class SavedsongFragment : Fragment() {

    lateinit var binding : FragmentSavedsongBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedsongBinding.inflate(inflater, container, false)
        Log.d("FragmentCheck", "SavedsongFragment onCreateView")

        // 1. 곡 리스트 생성
        val songList = arrayListOf(
            Song(num = null, img = R.drawable.img_album_exp, title = "Butter", singer = "방탄소년단 (BTS)")
        )

        val songRVAdapter = SongRVAdapter(songList)
        binding.savedSongRv.adapter = songRVAdapter

        return binding.root

    }
}