package com.example.flo
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentSavedalbumBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import com.google.gson.Gson


class SavedalbumFragment : Fragment() {

    lateinit var binding : FragmentSavedalbumBinding

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedalbumBinding.inflate(inflater, container, false)
        Log.d("FragmentCheck", "SavedAlbumFragment onCreateView")

        val songJson = arguments?.getString("song")
        val song = Gson().fromJson(songJson, Song::class.java)

        return binding.root
    }

}