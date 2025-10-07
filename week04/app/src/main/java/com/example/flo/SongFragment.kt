package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentSongBinding
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager


class SongFragment : Fragment() {
    lateinit var binding : FragmentSongBinding
    private var songDatas = ArrayList<TrackSong>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater, container, false)

        // 데이터 리스트 생성 더미 데이터
        songDatas.apply {
            add(TrackSong(title = "라일락", singer = "아이유 (IU)"))

        }

        var songRVAdapter = SongRVAdapter(songDatas)
        binding.albumTrackRv.adapter = songRVAdapter

        binding.albumTrackRv.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)


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