package com.example.flo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flo.databinding.FragmentLockerBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.example.flo.SavedsongFragment
import com.example.flo.SongfileFragment
import com.example.flo.SavedalbumFragment

class LockerFragment : Fragment() {
    lateinit var binding: FragmentLockerBinding

    private val information = arrayListOf("저장한 곡", "음악피일", "저장앨범")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerBinding.inflate(inflater, container, false)

        val lockerAdapter = LockerVPAdapter(this)
        binding.lockerContentVp.adapter = lockerAdapter
        TabLayoutMediator(binding.lockerContentTb, binding.lockerContentVp) {
                tap, position ->
            tap.text = information[position]

        }.attach()
        return binding.root
    }
}