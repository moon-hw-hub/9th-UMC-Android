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

         //1. 곡 리스트 생성
        val savedsongList = arrayListOf(
            SavedSong(img = R.drawable.img_album_exp, title = "Butter", singer = "방탄소년단 (BTS)")
        )

        val savedsongRVAdapter = SavedsongRVAdapter(savedsongList)
        binding.savedSongRv.adapter = savedsongRVAdapter

        return binding.root

    }
}