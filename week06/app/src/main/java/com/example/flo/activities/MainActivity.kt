package com.example.flo.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.home.HomeFragment
import com.example.flo.locker.LockerFragment
import com.example.flo.look.LookFragment
import com.example.flo.R
import com.example.flo.search.SearchFragment
import com.example.flo.dataclasses.Song
import com.example.flo.activities.SongActivity
import com.example.flo.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var song:Song = Song()
    private var gson: Gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_FLO)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //하단플레이어에 나오는 노래를 Song객체에 저장
        val song = Song(
            title = binding.mainMiniplayerTitleTv.text.toString(),
            singer = binding.mainMiniplayerSingerTv.text.toString(),
            second = 0, playTime = 60, isPlaying = false, music = "music_lilac"
        )
//        val song = Song(
//            title = binding.mainMiniplayerTitleTv.text.toString(),
//            singer = binding.mainMiniplayerSingerTv.text.toString(),
//            second = 0, playTime = 60, isPlaying = false
//        )

        //하단의 플레이어를 누르면 송액티비티로 전환. intent에 재생 노래 정보를 넣음
        binding.mainPlayerCl.setOnClickListener {
            //finish() //현재 액티비티 종료
            val intent = Intent(this, SongActivity::class.java)
            intent.putExtra("title", song.title)
            intent.putExtra("singer", song.singer)
            intent.putExtra("second", song.second)
            intent.putExtra("playTime", song.playTime)
            intent.putExtra("isPlaying", song.isPlaying)
            intent.putExtra("music", song.music)
            startActivity(intent)
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commit()
        //BottomNavigationView를 눌렀을 때 Fragment 변경하기
        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                //매인 화면
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commit()
                    true
                }
                //둘러보기 화면
                R.id.lookFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LookFragment())
                        .commit()
                    true
                }
                //검색 화면
                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commit()
                    true
                }
                //보관함 화면
                R.id.lockerFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LockerFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

    private fun setMiniPlayer(song: Song){
        binding.mainMiniplayerTitleTv.text = song.title
        binding.mainMiniplayerSingerTv.text = song.singer
        binding.mainProgressSb.progress = (song.second*100000)/song.playTime
    }

    override fun onStart() {
        super.onStart()
        val sharedPreferences = getSharedPreferences("song", MODE_PRIVATE)
        val songJson = sharedPreferences.getString("songData", null)

        song = if(songJson==null){ //예외 처리
            Song(title = "라일락", singer = "아이유(IU)", second = 0, playTime = 60, isPlaying = false)
        } else {
            gson.fromJson(songJson, Song::class.java)
        }

        setMiniPlayer(song)
        Log.d("MainActivity", "Loaded song: ${song.title}, ${song.second}/${song.playTime}")
    }


}