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
import android.graphics.Color
import kotlin.collections.MutableMap


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.simple)

        //setContent {
            //MyApplicationTheme {
                //Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //Greeting(
                        //name = "Android",
                        //modifier = Modifier.padding(innerPadding)
                    //)
                //}
            //}
        //}
    }
}
