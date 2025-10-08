package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentSavedalbumBinding
import com.example.flo.databinding.FragmentSavedsongBinding
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager

class SavedsongFragment : Fragment() {

    lateinit var binding : FragmentSavedsongBinding
    private var savedSongList = ArrayList<SavedSong>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedsongBinding.inflate(inflater, container, false)
        //Log.d("FragmentCheck", "SavedsongFragment onCreateView")


        // 곡 리스트 더미 데이터 생성
        savedSongList.apply {
            add(SavedSong(R.drawable.img_album_exp, "Butter", "방탄소년단 (BTS)"))
            add(SavedSong(R.drawable.img_album_exp2, "아이와 나의 바다", "아이유 (IU)"))
            add(SavedSong(R.drawable.img_first_album_default, "운명 교향곡", "베토벤 (Beethoven)"))
        }

        val savedsongRVAdapter = SavedsongRVAdapter(savedSongList)
        binding.savedSongRv.adapter = savedsongRVAdapter


        savedsongRVAdapter.setMyItemClickListener(object : SavedsongRVAdapter.MyItemClickListener {
            override fun onRemoveSong(position: Int) {
                savedsongRVAdapter.removeItem(position)

            }
        })

        return binding.root

    }
}