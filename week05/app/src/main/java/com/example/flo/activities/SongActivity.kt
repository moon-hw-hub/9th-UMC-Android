package com.example.flo.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.R
import com.example.flo.databinding.ActivitySongBinding
import com.example.flo.dataclasses.PlayedSong
import com.example.flo.dataclasses.Song

class SongActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongBinding
    lateinit var song: Song // 첫 isPlaying은 False
    lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSong() //송 데이터 받아와 실행
        setPlayer(song)

        //우측 상단 버튼 누르면 액티비티 종료
        binding.songDownIb.setOnClickListener{
            finish()
        }

        //재생, 일시정지
        binding.songMiniplayerIv.setOnClickListener {
            setPlayerStatus(true)
        }
        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(false)
        }

        //이전곡/다음곡
        binding.songPreviousIv.setOnClickListener {
            restart()
        }
        binding.songNextIv.setOnClickListener {
            restart()
        }

        //반복재생
        var isRoop = false
        binding.songRepeatIv.setOnClickListener {
            if (!isRoop) {
                binding.songRepeatIv.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN)
                isRoop = true
            } else {
                binding.songRepeatIv.clearColorFilter()
                isRoop = false
            }
        }

        //전체재생(랜덤재생인듯?)
        var willPlayAll = false

        binding.songRandomIv.setOnClickListener {
            if (!willPlayAll) {
                binding.songRandomIv.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN)
                willPlayAll = true
            } else {
                binding.songRandomIv.clearColorFilter()
                willPlayAll = false
            }
        }

    }

    //앱이 꺼질때 스레드 종료
    override fun onDestroy() {
        super.onDestroy()
        timer.interrupt()
    }

    //메인액티비티에서 song데이터를 받아오는 함수
    private fun initSong() {
        if(intent.hasExtra("title")&&intent.hasExtra("singer")){
            song = Song(
                title = intent.getStringExtra("title")!!,
                singer = intent.getStringExtra("singer")!!,
                second = intent.getIntExtra("second",0),
                playTime = intent.getIntExtra("playTime",0),
                isPlaying = intent.getBooleanExtra("isPlaying", false)
            )
        }
        startTimer()
    }

    //송 데이터를 뷰에 렌더링하는 함수
    private fun setPlayer(song: Song) {
        binding.songMusicTitleTv.text = intent.getStringExtra("title")
        binding.songSingerNameTv.text = intent.getStringExtra("singer")
        binding.songStartTimeTv.text = String.format("%02d:%02d", song.second / 60, song.second % 60)
        binding.songEndTimeTv.text = String.format("%02d:%02d", song.playTime / 60, song.second % 60)
        binding.songProgressSb.progress = (song.second*1000 / song.playTime)

        setPlayerStatus(song.isPlaying)
    }

    //재생, 일시정지 버튼 로직 구현 함수
    private fun setPlayerStatus(isPlaying: Boolean) {
        song.isPlaying = isPlaying
        timer.isPlaying = isPlaying

        if(isPlaying){
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
        } else {
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        }
    }

    //타이머 시작 함수
    private fun startTimer() {
        timer = Timer(song.playTime, song.isPlaying)
        timer.start()
    }

    //재시작 함수
    private fun restart() {
        timer.interrupt() // 기존 타이머 스레드 종료
        song.second = 0 // 곡 시간 초기화
        song.isPlaying = true // 재생 상태 설정

        setPlayer(song) // UI 업데이트
        startTimer() // 새로운 타이머 스레드를 생성하고 재시작
    }

    inner class Timer(private val playTime: Int, var isPlaying: Boolean = true):Thread() {
        private var second:Int = 0 // 타이머 텍스트뷰에 사용할 용도
        private var mills:Float = 0f // 경과된 시간 누적용, 시크바에 사용할 용도

        override fun run() {
            super.run()
            try {
                while(true){
                    if (second >= playTime){
                        break
                    }
                    //노래가 재생중일동안 실행
                    if (isPlaying){
                        sleep(50)
                        mills += 50

                        //시크바 갱신
                        runOnUiThread {
                            binding.songProgressSb.progress = ((mills / playTime)*100).toInt()
                        }

                        //타이머 텍스트뷰 갱신
                        if (mills%1000 == 0f) {
                            runOnUiThread {
                                binding.songStartTimeTv.text = String.format("%02d:%02d", second / 60, second % 60)
                            }
                            second++
                        }
                    }
                }
            }catch(e: InterruptedException) { //액티비티가 종료되었을 경우 스레드 종료
                Log.d("Song", "스레드가 죽었습니다. ${e.message}")
            }

        }
    }
}