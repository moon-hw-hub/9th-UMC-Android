package com.example.yourapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yourapplication.databinding.FragmentWishlistBinding //뷰바인딩 클래스 사용을 위한 패키지 임포트
import androidx.navigation.fragment.findNavController
import com.example.yourapplication.databinding.FragmentProfileBinding
import android.content.Intent
import com.example.yourapplication.EditActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 프로필 수정 화면으로 전환
        binding.editButton.setOnClickListener {
            val intent = Intent(requireContext(), EditActivity::class.java)
            startActivity(intent)

        }


    }

}