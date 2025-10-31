package com.example.flo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.gson.Gson

//수록곡 전달을 위해 인자 하나 더 추가
class AlbumVPAdapter(fragment: Fragment, private val album: Album ) : FragmentStateAdapter(fragment){

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SongFragment().apply {
                arguments = Bundle().apply {
                    val albumJson = Gson().toJson(album)
                    putString("album", albumJson)
                }

            }
            1 -> {
                DetailFragment()
                }
            else -> VideoFragment()
        }

    }


}