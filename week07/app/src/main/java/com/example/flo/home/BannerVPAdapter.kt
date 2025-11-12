package com.example.flo.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BannerVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentlist : ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int { // 데이터를 몇개를 전달할 것이냐
        return fragmentlist.size
    }

    override fun createFragment(position: Int): Fragment = fragmentlist[position]

    //리스트에 추가하는 함수
    fun addFragment(fragment: Fragment) {
        fragmentlist.add(fragment)
        notifyItemInserted(fragmentlist.size - 1) //뷰페이저에게 새로운 값이 추가가 됐다고 알림
    }


}