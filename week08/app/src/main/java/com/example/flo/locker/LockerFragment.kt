package com.example.flo.locker

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.activities.LoginActivity
import com.example.flo.databinding.FragmentLockerBinding
import com.example.flo.data.Song
import com.google.android.material.tabs.TabLayoutMediator

class LockerFragment : Fragment() {
    lateinit var binding: FragmentLockerBinding
//    private var songDatas = ArrayList< SavedSong>()

    private val information = arrayListOf("저장한 곡", "음악피일", "저장앨범")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerBinding.inflate(inflater, container, false)

        //뷰페이저 어댑터 등록
        val lockerAdapter = LockerVPAdapter(this)
        binding.lockerContentVp.adapter = lockerAdapter

        //탭레이아웃과 연결
        TabLayoutMediator(binding.lockerContentTb, binding.lockerContentVp) { tap, position ->
            tap.text = information[position]

        }.attach()

        //로그인 액티비티로 화면전환
        binding.login.setOnClickListener {
            startActivity(Intent(activity, LoginActivity::class.java))
        }
        return binding.root
    }

    private fun setInit(song: Song) {

    }
}