package com.example.flo.locker

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class LockerVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        Log.d("FragmentCheck2", "createfrag")
//        val gson = Gson()
//        val songJson = gson.toJson(song)

        return when(position) {
            0 -> {
                SavedsongFragment()

            }
            1 -> {
                SongfileFragment()
            }
            else -> {
                SavedalbumFragment()
            }
        }

    }


}