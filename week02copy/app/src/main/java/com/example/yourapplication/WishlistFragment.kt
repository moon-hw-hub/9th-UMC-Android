package com.example.yourapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yourapplication.databinding.FragmentWishlistBinding //뷰바인딩 클래스 사용을 위한 패키지 임포트
import androidx.navigation.fragment.findNavController

class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_wishlist, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 텍스트뷰에 텍스트 설정
        binding.centerText.text = "장바구니가 비어 있습니다.\n제품을 추가하면 여기에 표시됩니다."
        binding.buyButton.text = "주문하기"
        binding.buyButton.setOnClickListener {
            findNavController().navigate(R.id.action_wishlistFragment_to_buyFragment)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 메모리 누수 방지
    }


}