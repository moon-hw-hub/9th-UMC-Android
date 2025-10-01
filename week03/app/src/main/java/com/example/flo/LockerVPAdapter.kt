package com.example.flo

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.flo.SavedsongFragment
import com.example.flo.SongfileFragment
import com.example.flo.SavedalbumFragment


class LockerVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        Log.d("FragmentCheck2", "createfrag")
        return when(position) {
            0 -> {
                SavedsongFragment()
            }
            1 -> {
                SongfileFragment()
            }
            else -> {SavedalbumFragment()}
        }

    }


}