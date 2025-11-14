package com.example.flo.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.flo.albumfrag.AlbumFragment
import com.example.flo.R
import com.example.flo.databinding.FragmentHomeBinding
import com.example.flo.data.Album
import com.example.flo.data.Song
import com.example.flo.activities.MainActivity
import com.example.flo.data.SongDatabase
import com.google.gson.Gson

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private var albumDatas = ArrayList<Album>() // 오늘의 앨범에 나올 앨범들을 위함

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        inputDummyAlbums() //DB에 앨범데이터 삽입(초기설정용)

        albumDatas = getTodayAlbumDatas() //DB에서 앨범 리스트 가져오기

        //리사이클러뷰 어댑터 등록 (앨범 나열)
        val albumRVAdapter = AlbumRVAdapter(albumDatas)
        binding.homeTodayMusicAlbumRv.adapter = albumRVAdapter

        //리사이클러뷰의 레이아웃 매니저 설정
        binding.homeTodayMusicAlbumRv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )

        //등록한 리사이클러뷰 어댑터 객체에 클릭리스너 세팅
        albumRVAdapter.setMyItemClickListener(object : AlbumRVAdapter.MyItemClickListener {
            override fun onItemClick(album: Album) {
                changeAlbumFragment(album) //앨범프래그먼트로 전환 시 클릭한 앨범의 id 전달
            }

//            override fun onRemoveAlbum(position: Int) {
//                albumRVAdapter.removeItem(position)
//            }
        })

       //뷰페이저2에 광고 배너들 연결
        val bannerAdapter = BannerVPAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        binding.homeBannerVp.adapter = bannerAdapter

        //광고 뷰 페이저가 좌우로 스크롤 될수 있도록 지정
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }

    private fun inputDummyAlbums() {
        //DB인스턴스를 만든다
        val songDB = SongDatabase.getInstance(requireContext())!!
        val albums = songDB.albumDao().getAlbums()

        //앨범 데이터가 있다면 종료
        if (albums.isNotEmpty()) return

        //데이터가 없을 경우 -> 앨범 데이터 삽입
        songDB.albumDao().apply {
            insert(Album(1, "LILAC", "아이유 (IU)", false, R.drawable.img_album_exp2))
            insert(Album(2, "GREAT!", "모모랜드 (MOMOLAND)", false, R.drawable.img_great_album_exp))
            insert(Album(3, "Butter", "방탄소년단 (BTS)", false, R.drawable.img_album_exp))
            insert(Album(4, "LOST CORNER", "요네즈 켄시 (Kenshi Yonezu)", false, R.drawable.img_iris_album_exp))
            insert(Album(5, "END THEORY", "윤하 (YOUNHA)", false, R.drawable.img_oort_album_exp))
        }
    }
    private fun getTodayAlbumDatas(): ArrayList<Album> {
        val songDB = SongDatabase.getInstance(requireContext())!!
        albumDatas = ArrayList(songDB.albumDao().getAlbums()) // DB에서 앨범 리스트 가져오기
        return albumDatas
    }

    //앨범프래그먼트로 전환 시 클릭한 앨범의 id 전달
    private fun changeAlbumFragment(album: Album) {
        val bundle = Bundle().apply {
            putInt("albumId", album.id) // ID만 전달
        }
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, AlbumFragment().apply { arguments = bundle })
            .commitAllowingStateLoss()
    }

}