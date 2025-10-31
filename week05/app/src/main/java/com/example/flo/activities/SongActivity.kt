package com.example.flo.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.R
import com.example.flo.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySongBinding.inflate(layoutInflater)

        //재생, 일시정지
        var isPlay = false
        binding.songMiniplayerIv.setOnClickListener {
            if (!isPlay) {
                binding.songMiniplayerIv.setImageResource(R.drawable.btn_miniplay_pause)
                isPlay = true
            } else {
                binding.songMiniplayerIv.setImageResource(R.drawable.btn_miniplayer_play)
                isPlay = false
            }

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

        setContentView(binding.root)

        if (intent.hasExtra("title") && intent.hasExtra("singer")) {
            binding.songMusicTitleTv.text = intent.getStringExtra("title")
            binding.songSingerNameTv.text = intent.getStringExtra("singer")
        }

        val bundle = Bundle()
        bundle.putString("albumname", binding.songMusicTitleTv.text.toString())

        binding.songDownIb.setOnClickListener {
            //finish()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
            finish()

        }

    }
}