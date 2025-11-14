package com.example.flo.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.home.HomeFragment
import com.example.flo.locker.LockerFragment
import com.example.flo.look.LookFragment
import com.example.flo.R
import com.example.flo.search.SearchFragment
import com.example.flo.data.Song
import com.example.flo.data.SongDatabase
import com.example.flo.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var song:Song = Song()
    private var gson: Gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_FLO)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inputDummySongs() //더미 데이터 db에 삽입

        //하단플레이어에 나오는 노래를 Song객체에 저장 -> roomDB로 대체
//        val song = Song(
//            title = binding.mainMiniplayerTitleTv.text.toString(),
//            singer = binding.mainMiniplayerSingerTv.text.toString(),
//            second = 0, playTime = 60, isPlaying = false, music = "music_lilac"
//        )

        //하단의 플레이어를 누르면 송액티비티로 전환. 이때 sharedPreference에 노래의 id를 저장
        binding.mainPlayerCl.setOnClickListener {
            //에디터를 만들고
            val editor = getSharedPreferences("song", MODE_PRIVATE).edit()

            //송의 id를 넣어준다.
            editor.putInt("songId", song.id)

            //최종 커밋
            editor.apply()

            //인텐트는 단순 전환 기능만. sharedPrefernece와 DB가 알아서 해주니까.
            val intent = Intent(this, SongActivity::class.java)
            startActivity(intent)
        }

        //실행 시 첫 화면 설정 -> 홈프래그먼트
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commit()

        //BottomNavigationView를 눌렀을 때 Fragment 변경하기
        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                //매인 화면
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commit()
                    true
                }
                //둘러보기 화면
                R.id.lookFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LookFragment())
                        .commit()
                    true
                }
                //검색 화면
                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commit()
                    true
                }
                //보관함 화면
                R.id.lockerFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LockerFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

    //메인 액티비티가 시작될 때-> sharedprference에서 id값을 가져와서 DB에서 해당 id에 해당하는 song을 가져옴
    override fun onStart() {
        super.onStart()
        //sharedprference에서 id값을 가져옴
        val spf = getSharedPreferences("song", MODE_PRIVATE)
        val songId = spf.getInt("songId", 0)

        //DB인스턴스를 만들고
        val songDB = SongDatabase.getInstance(this)!!

        //DB에서 해당 id에 해당하는 song을 가져옴
        song = if (songId==0) {
            songDB.songDao().getSong(1)
        } else {
            songDB.songDao().getSong(songId)
        }

        Log.d("song ID", song.id.toString()) // 디버깅

        //가져온 송 데이터를 뷰바인딩
        setMiniPlayer(song)
    }

    private fun setMiniPlayer(song: Song){
        binding.mainMiniplayerTitleTv.text = song.title
        binding.mainMiniplayerSingerTv.text = song.singer
        binding.mainProgressSb.progress = (song.second*100000)/song.playTime
    }

    //DB에 데이터가 없다면 더미 데이터를 넣는 작업
    private fun inputDummySongs() {
        val songDB = SongDatabase.getInstance(this)!!
        val songs = songDB.songDao().getSongs()

        //데이터가 있다면 종료
        if (songs.isNotEmpty()) return

        //데이터가 없을 경우
        songDB.songDao().insert(
            Song(
                title = "Lilac",
                singer = "아이유 (IU)",
                second = 0,
                playTime = 230,
                isPlaying = false,
                music = "music_lilac",
                coverImg = R.drawable.img_album_exp2,
                isLike = false
            )
        )

        songDB.songDao().insert(
            Song(
                title = "BBoom BBoom",
                singer = "모모랜드 (MOMOLAND)",
                second = 0,
                playTime = 240,
                isPlaying = false,
                music = "music_bboom",
                coverImg = R.drawable.img_great_album_exp,
                isLike = false
            )
        )

        songDB.songDao().insert(
            Song(
                title = "Butter",
                singer = "방탄소년단 (BTS)",
                second = 0,
                playTime = 180,
                isPlaying = false,
                music = "music_butter",
                coverImg = R.drawable.img_album_exp,
                isLike = false
            )
        )

        songDB.songDao().insert(
            Song(
                title = "IRIS OUT",
                singer = "요네즈 켄시 (Kenshi Yonezu)",
                second = 0,
                playTime = 155,
                isPlaying = false,
                music = "music_irisout",
                coverImg = R.drawable.img_iris_album_exp,
                isLike = false
            )
        )

        songDB.songDao().insert(
            Song(
                title = "Oort Cloud (오르트 구름)",
                singer = "윤하 (YOUNHA)",
                second = 0,
                playTime = 210,
                isPlaying = false,
                music = "music_oortcloud",
                coverImg = R.drawable.img_oort_album_exp,
                isLike = false
            )
        )

        //DB에 데이터가 잘 들어갔는지 로그로 확인
        val _songs = songDB.songDao().getSongs() //테이블의 모든 송을 가져옴
        Log.d("DB data", _songs.toString()) //DB에 데이터가 잘 들어갔는지 확인

    }

}