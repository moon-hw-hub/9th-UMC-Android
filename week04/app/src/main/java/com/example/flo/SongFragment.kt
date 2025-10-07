package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentSongBinding
import android.widget.ImageView


class SongFragment : Fragment() {
    lateinit var binding : FragmentSongBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater, container, false)


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