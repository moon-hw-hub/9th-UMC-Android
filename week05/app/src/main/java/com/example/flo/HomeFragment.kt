package com.example.flo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.flo.databinding.FragmentHomeBinding
import com.google.gson.Gson


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private var albumDatas = ArrayList<Album>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

//        binding.homeAlbumImgIv1.setOnClickListener {
//            //앨범 프래그먼트로 데이터 전달
//            val bundle = Bundle()
//            bundle.putString("album", binding.lilac.text.toString())
//            bundle.putString("singer", binding.iu.text.toString())
//
//            val albumFragment = AlbumFragment()
//            albumFragment.arguments = bundle
//
//            //화면 전환
//            (context as MainActivity).supportFragmentManager.beginTransaction()
//                .replace(R.id.main_frm, albumFragment)
//                .commitAllowingStateLoss()
//        }
        // 데이터 리스트 생성 더미 데이터
        albumDatas.apply {
            add(
                Album(
                    title = "Butter",
                    singer = "방탄소년단 (BTS)",
                    coverImage = R.drawable.img_album_exp,
                    songs = arrayListOf(
                        Song("01", "Butter", "방탄소년단 (BTS)"),
                        Song("02", "Permission to Dance","방탄소년단 (BTS)"),
                        Song("03", "Butter (Instrumental)","방탄소년단 (BTS)"),
                        Song("04", "Permission to Dance (Instrumental)", "방탄소년단 (BTS)")
                    )
                    ))
            add(
                Album(
                    title = "Lilac",
                    singer = "아이유 (IU)",
                    coverImage = R.drawable.img_album_exp2,
                    songs = arrayListOf(
                        Song("01","라일락", "아이유 (IU)"),
                        Song("02" , "Flu", "아이유 (IU)"),
                        Song("03","Coin", "아이유 (IU)"),
                        Song("04", "봄 안녕 봄","아이유 (IU)"),
                        Song("05", "Celebrity", "아이유 (IU)"),
                        Song("06", "돌림노래", "아이유 (IU)"),
                        Song("07", "빈 컵","아이유 (IU)"),
                        Song("08", "아이와 나의 바다","아이유 (IU)"),
                        Song("09", "어푸 (Ah puh )", "아이유 (IU)"),
                        Song("10", "에필로그", "아이유 (IU)")
                    )
                )
            )
            add(
                Album(
                    title = "Temp",
                    singer = "김시선 (UMC)",
                    coverImage = R.drawable.img_potcast_exp))
            add(
                Album(
                    title = "Classic",
                    singer = "베토벤 (Beethoven)",
                    coverImage = R.drawable.img_first_album_default,
                    songs = arrayListOf(
                        Song("01", "월광 소나타", "베토벤 (Beethoven)"),
                        Song("02", "비창 소나타", "베토벤 (Beethoven)"),
                        Song("03", "운명 교향곡", "베토벤 (Beethoven)"),
                        Song("04", "영웅 교향곡", "베토벤 (Beethoven)"),
                        Song("05", "환희의 송가", "베토벤 (Beethoven)"),
                        Song("06", "황제 협주곡", "베토벤 (Beethoven)")

                    )
                )
            )
        }

        val albumRVAdapter = AlbumRVAdapter(albumDatas)
        binding.homeTodayMusicAlbumRv.adapter = albumRVAdapter

        binding.homeTodayMusicAlbumRv.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)

        albumRVAdapter.setMyItemClickListener(object : AlbumRVAdapter.MyItemClickListener {
            override fun onItemClick(album: Album) {
                changeAlbumFragment(album)
            }

//            override fun onRemoveAlbum(position: Int) {
//                albumRVAdapter.removeItem(position)
//            }
        })

       //광고 배너들 연결
        val bannerAdapter = BannerVPAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        binding.homeBannerVp.adapter = bannerAdapter

        //광고 뷰 페이지가 좌우로 스크롤 될수 있도록 지정
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }

    private fun changeAlbumFragment(album: Album) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, AlbumFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val albumJson = gson.toJson(album) //앨범객체를 Json으로 변환
                    putString("album", albumJson)
                }
            })
            .commitAllowingStateLoss()
    }


}