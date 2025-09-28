package com.example.yourapplication
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.yourapplication.R
import com.example.yourapplication.databinding.ActivityEditBinding


class EditActivity : ComponentActivity(){
    private lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // activity_edit을 메모리에 올리고, 그 루트 뷰를 binding.root로 제공함
        binding = ActivityEditBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_edit)
        setContentView(binding.root)

        binding.cancelText.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("targetFragment", "profile")
            startActivity(intent)
            finish() // 현재 액티비티 종료


        }


    }
}