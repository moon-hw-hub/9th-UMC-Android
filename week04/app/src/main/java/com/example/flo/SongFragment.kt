package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentSongBinding
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson



class SongFragment : Fragment() {
    lateinit var binding : FragmentSongBinding
//    private var songDatas = ArrayList<Song>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater, container, false)

        //수록곡 목록 표시를 위해 앨범 정보 번들을 겟또다제
        val albumJson = arguments?.getString("album")
        val album = Gson().fromJson(albumJson, Album::class.java)

        val songRVAdapter = SongRVAdapter(album.songs ?: arrayListOf())
        binding.albumTrackRv.adapter = songRVAdapter
        binding.albumTrackRv.layoutManager = LinearLayoutManager(context)

        //내 취향 믹스 미션
        var isON = false

        binding.songMixoffTg.setOnClickListener {
            if (!isON) {
                binding.songMixoffTg.setImageResource(R.drawable.btn_toggle_on)
                isON = true
            } else {
                binding.songMixoffTg.setImageResource(R.drawable.btn_toggle_off)
                isON = false
            }

        }

        return binding.root
    }
}