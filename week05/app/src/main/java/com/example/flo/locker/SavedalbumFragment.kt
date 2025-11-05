package com.example.flo.locker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flo.R
import com.example.flo.databinding.FragmentSavedalbumBinding
import com.example.flo.dataclasses.Album
import com.example.flo.dataclasses.Song
import com.google.gson.Gson

class SavedalbumFragment : Fragment() {

    lateinit var binding : FragmentSavedalbumBinding
    private var albumDatas = ArrayList<Album>()

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedalbumBinding.inflate(inflater, container, false)
        albumDatas.apply {
            add(
                Album(
                    title = "Butter",
                    singer = "방탄소년단(BTS)",
                    coverImage = R.drawable.img_album_exp,
                    information = "2021.03.25|정규|댄스 팝"
                )
            )
            add(
                Album(
                    title = "Great!",
                    singer = "모모랜드(MOMOLAND)",
                    coverImage = R.drawable.img_great_album_exp,
                    information = "2021.03.25|정규|댄스 팝"
                )
            )
        }

        val savedAlbumRVAdapter = SavedAlbumRVAdapter(albumDatas)
        binding.savedAlbumRv.adapter = savedAlbumRVAdapter

        binding.savedAlbumRv.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )


        val songJson = arguments?.getString("song")
        val song = Gson().fromJson(songJson, Song::class.java)

        return binding.root
    }

}