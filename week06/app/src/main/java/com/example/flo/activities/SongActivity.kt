package com.example.flo.activities

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.flo.services.Foreground
import com.example.flo.databinding.ActivitySongBinding
import com.example.flo.dataclasses.Song
import com.example.flo.services.MusicService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

class SongActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySongBinding
    private lateinit var song: Song
    private val gson = Gson()

    // 서비스 관련 변수
    private var musicService: MusicService? = null
    private var isBound = false
    private var updateJob: Job? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicService.MusicBinder
            musicService = binder.getService()
            isBound = true

            // Song 정보를 서비스에 전달
            musicService?.updateCurrentSongInfo(song.title, song.singer)

            updateUI()
            updateSeekbar()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSong()
        startAndBindMusicService()

        binding.songDownIb.setOnClickListener { finish() }

        // 🎵 재생/일시정지 버튼
        binding.songMiniplayerIv.setOnClickListener {
            musicService?.playMusic()
            updateUI()
        }
        binding.songPauseIv.setOnClickListener {
            musicService?.pauseMusic()
            updateUI()
        }

        // 🔁 반복재생
        var isLoop = false
        binding.songRepeatIv.setOnClickListener {
            isLoop = !isLoop
            if (isLoop)
                binding.songRepeatIv.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN)
            else
                binding.songRepeatIv.clearColorFilter()
        }

        // 🔀 랜덤재생
        var willPlayAll = false
        binding.songRandomIv.setOnClickListener {
            willPlayAll = !willPlayAll
            if (willPlayAll)
                binding.songRandomIv.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN)
            else
                binding.songRandomIv.clearColorFilter()
        }

        // 🎚️ SeekBar 조작 시 MediaPlayer 위치 이동
        binding.songProgressSb.setOnSeekBarChangeListener(object :
            android.widget.SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: android.widget.SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser && musicService != null) {
                    musicService?.seekTo(progress)
                    binding.songStartTimeTv.text = milliToTime(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: android.widget.SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: android.widget.SeekBar?) {}
        })
    }

    private fun initSong() {
        if (intent.hasExtra("title") && intent.hasExtra("singer")) {
            song = Song(
                title = intent.getStringExtra("title")!!,
                singer = intent.getStringExtra("singer")!!,
                second = intent.getIntExtra("second", 0),
                playTime = intent.getIntExtra("playTime", 0),
                isPlaying = intent.getBooleanExtra("isPlaying", false),
                music = intent.getStringExtra("music")!!
            )
        }
    }

    private fun startAndBindMusicService() {
        val intent = Intent(this, MusicService::class.java)

        // 이미 실행 중인지 확인 후 startForegroundService() 호출
        if (!isServiceRunning(MusicService::class.java)) {
            ContextCompat.startForegroundService(this, intent)
        }

        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as android.app.ActivityManager
        return manager.getRunningServices(Int.MAX_VALUE)
            .any { it.service.className == serviceClass.name }
    }

    private fun updateUI() {
        val isPlaying = musicService?.isPlaying() ?: false
        song.isPlaying = isPlaying

        binding.songMusicTitleTv.text = song.title
        binding.songSingerNameTv.text = song.singer
        binding.songEndTimeTv.text = milliToTime(musicService?.getDuration() ?: 0)

        if (isPlaying) {
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
        } else {
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        }
    }

    private fun updateSeekbar() {
        updateJob?.cancel()
        updateJob = lifecycleScope.launch(Dispatchers.Main) {
            while (isBound) {
                delay(100)
                val currentPos = musicService?.getCurrentPosition() ?: 0
                val totalDuration = musicService?.getDuration() ?: 1
                binding.songProgressSb.max = totalDuration
                binding.songProgressSb.progress = currentPos
                binding.songStartTimeTv.text = milliToTime(currentPos)
            }
        }
    }

    private fun milliToTime(ms: Int): String {
        val totalSec = ms / 1000
        val min = totalSec / 60
        val sec = totalSec % 60
        return String.format(Locale.getDefault(), "%02d:%02d", min, sec)
    }

    override fun onPause() {
        super.onPause()
        if (isBound) {
            val sharedPreferences = getSharedPreferences("song", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val songJson = gson.toJson(song)
            editor.putString("songData", songJson)
            editor.apply()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
        updateJob?.cancel()
    }
}