package com.example.yourapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.yourapplication.ui.theme.YourApplicationTheme
import androidx.appcompat.app.AppCompatActivity // XML +
import com.example.yourapplication.databinding.ActivityMainBinding //뷰바인딩 클래스 사용을 위한 패키지 임포트
import androidx.navigation.fragment.NavHostFragment // 내비게이션 패키지?
import androidx.navigation.ui.setupWithNavController // 내비게이션 컨트롤러 패키지?



private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // activity_main을 메모리에 올리고, 그 루트 뷰를 binding.root로 제공함
        binding = ActivityMainBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        // BottomNavigationView를 NavController와 연결
        binding.mainBnv.setupWithNavController(navController)

        //초기 프래그먼트는 홈 화면으로
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, HomeFragment())
//            .commit()

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, BuyFragment())
//            .commit()
//
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, WishlistFragment())
//            .commit()
//
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, BasketFragment())
//            .commit()
//
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, ProfileFragment())
//            .commit()

    }
}

