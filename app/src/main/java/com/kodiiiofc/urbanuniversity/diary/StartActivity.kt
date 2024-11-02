package com.kodiiiofc.urbanuniversity.diary

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kodiiiofc.urbanuniversity.diary.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var bindind: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityStartBinding.inflate(layoutInflater)
        setContentView(bindind.root)
        bindind.startBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}