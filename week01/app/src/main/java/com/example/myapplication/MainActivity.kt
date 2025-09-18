package com.example.myapplication

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
import com.example.myapplication.ui.theme.MyApplicationTheme
import android.widget.TextView
import android.widget.ImageView
import android.widget.Toast
import android.graphics.Color
import kotlin.collections.MutableMap


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.simple)

        // 메세지 문자열 배열
        var texts = arrayOf<String>(
            "좋은 일 있었어?",
            "뭔데 그리 들뜬건지 궁금하네~",
            "오늘 수업만 듣고 온 거야?",
            "그럴 땐 그냥 자는게 나아. 자장가 불러줄까?",
            "누가 화나게 했어? 내가 혼내줄게")

        // MutableMap 선언
        val imagetextMap = mutableMapOf<ImageView, TextView>()

        val idleImage = findViewById<ImageView>(R.id.idleImage)
        val happyImage = findViewById<ImageView>(R.id.happyImage)
        val sosoImage = findViewById<ImageView>(R.id.sosoImage)
        val sadImage = findViewById<ImageView>(R.id.sadImage)
        val angryImage = findViewById<ImageView>(R.id.angryImage)

        // 매핑
        imagetextMap[idleImage] = findViewById<TextView>(R.id.idleText)
        imagetextMap[happyImage] = findViewById<TextView>(R.id.happyText)
        imagetextMap[sosoImage] = findViewById<TextView>(R.id.sosoText)
        imagetextMap[sadImage] = findViewById<TextView>(R.id.sadText)
        imagetextMap[angryImage] = findViewById<TextView>(R.id.angryText)

        //클릭 메서드
        for ((imageView, textView) in imagetextMap) {
            imageView.setOnClickListener {
                // 모든 텍스트 색상 초기화
                imagetextMap.values.forEach { it.setTextColor(getColor(R.color.black)) }

                when (imageView) { //switch-case
                    idleImage -> {textView.setTextColor(Color.YELLOW)
                        Toast.makeText(this, texts[0], Toast.LENGTH_SHORT).show()}
                    happyImage -> {textView.setTextColor(Color.CYAN)
                        Toast.makeText(this, texts[1], Toast.LENGTH_SHORT).show()}
                    sosoImage -> {textView.setTextColor(Color.BLUE)
                        Toast.makeText(this, texts[2], Toast.LENGTH_SHORT).show()}
                    sadImage -> {textView.setTextColor(Color.GREEN)
                        Toast.makeText(this, texts[3], Toast.LENGTH_SHORT).show()}
                    angryImage -> {textView.setTextColor(Color.RED)
                        Toast.makeText(this, texts[4], Toast.LENGTH_SHORT).show()}
                }
            }


        }

    }
}
