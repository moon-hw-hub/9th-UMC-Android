package com.example.flo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flo.databinding.FragmentLockerBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class LockerFragment : Fragment() {
    lateinit var binding: FragmentLockerBinding
    private var songDatas = ArrayList<Song>()
    private var gson: Gson = Gson()

    private val information = arrayListOf("저장한 곡", "음악피일", "저장앨범")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerBinding.inflate(inflater, container, false)

        //데이터 리스트 생성 더미 데이터
        songDatas.apply {
            add(Song(img = R.drawable.img_album_exp, title = "Butter",
                singer = "방탄소년단 (BTS)"))
        }

        //저장한 곡들 데이터
        val songJson = arguments?.getString("song")
        val song = gson.fromJson(songJson, Song::class.java)
        //setInit()

        val lockerAdapter = LockerVPAdapter(this)
        binding.lockerContentVp.adapter = lockerAdapter
        TabLayoutMediator(binding.lockerContentTb, binding.lockerContentVp) {
                tap, position ->
            tap.text = information[position]

        }.attach()
        return binding.root
    }

    private fun setInit(song: Song) {

    }
}