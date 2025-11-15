package com.example.flo.locker

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.flo.activities.LoginActivity
import com.example.flo.activities.MainActivity
import com.example.flo.databinding.FragmentLockerBinding
import com.example.flo.data.Song
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.log

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

    override fun onStart() {
        super.onStart()
        initView()
    }

    //SharedPreferences에서 JWT를 가져오는 메서드
    private fun getJwt(): Int {
        val spf = activity?.getSharedPreferences("auth", MODE_PRIVATE) //activity? : 프래그먼트에서의 작성방법
        return spf!!.getInt("jwt", 0)
    }

    //로그인으로 할지 로그아웃으로 할지 뷰를 결정, 클릭리스너도 담당
    private fun initView() {
        val jwt: Int = getJwt()
        if (jwt == 0) {
            binding.login.text = "로그인"
            binding.login.setOnClickListener {
                startActivity(Intent(activity, LoginActivity::class.java))
            }
        }
        else {
            binding.login.text = "로그아웃"
            binding.login.setOnClickListener {
                //로그아웃 진행
                logout()
                startActivity(Intent(activity, MainActivity::class.java))
            }
        }
    }

    private fun logout() {
        val spf = activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        val editor = spf!!.edit()
        editor.remove("jwt")
        editor.apply()
    }

    private fun setInit(song: Song) {

    }
}