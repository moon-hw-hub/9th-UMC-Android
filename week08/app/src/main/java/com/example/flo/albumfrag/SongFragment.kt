package com.example.flo.albumfrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flo.R
import com.example.flo.databinding.FragmentSongBinding
import com.example.flo.data.Album
import com.example.flo.data.Song
import com.example.flo.data.SongDatabase
import com.google.gson.Gson

class SongFragment : Fragment() {
    lateinit var binding: FragmentSongBinding
    private var songDatas = ArrayList<Song>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSongBinding.inflate(inflater, container, false)

        // 전달받은 albumId 꺼내기
        val albumId = arguments?.getInt("albumId", 0) ?: 0

        // DB에서 수록곡 조회
        val songDB = SongDatabase.getInstance(requireContext())!!
        songDatas = ArrayList(songDB.songDao().getSongsInAlbum(albumId))

        // 리사이클러뷰 어댑터 등록
        val songRVAdapter = SongRVAdapter(songDatas)
        binding.albumTrackRv.adapter = songRVAdapter
        binding.albumTrackRv.layoutManager = LinearLayoutManager(context)

        // 믹스 토글 버튼
        var isON = false
        binding.songMixoffTg.setOnClickListener {
            isON = !isON
            binding.songMixoffTg.setImageResource(
                if (isON) R.drawable.btn_toggle_on else R.drawable.btn_toggle_off
            )
        }
        return binding.root
    }
}
