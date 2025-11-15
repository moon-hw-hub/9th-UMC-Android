package com.example.flo.activities

import android.os.Bundle
import android.util.Log

import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.data.SongDatabase

import com.example.flo.data.User
import com.example.flo.databinding.ActivitySignupBinding
import kotlin.math.sign

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpSignUpBtn.setOnClickListener {
            signUp() //회원가입
            finish() //로그인액티비티로 이동
        }
    }

    //유저 정보를 가져오는 메서드
    private fun getUser(): User {
        val email : String = binding.signUpIdEt.text.toString() + "@" + binding.signUpDirectInputEt.text.toString()
        val pwd: String = binding.signUpPasswordEt.text.toString()

        return User(email, pwd)
    }

    //회원가입 메서드
    private fun signUp() {
        //예외 처리
        if (binding.signUpIdEt.text.toString().isEmpty() || binding.signUpDirectInputEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        if (binding.signUpPasswordEt.text.toString() != binding.signUpPasswordCheckEt.text.toString()) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        //입력한 정보를 DB에 저장
        val userDB = SongDatabase.getInstance(this)!!
        userDB.userDao().insert(getUser())

        //디버깅
        val user = userDB.userDao().getUsers()
        Log.d("SIGNUPACT", user.toString())
    }
}