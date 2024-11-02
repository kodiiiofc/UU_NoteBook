package com.kodiiiofc.urbanuniversity.diary

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kodiiiofc.urbanuniversity.diary.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.logoIv.alpha = 0.0f
        binding.logoIv.animate().apply {
            alpha(1.0f)
            duration = 1000
        }

        binding.startBtn.translationX = -2000f
        binding.startBtn.animate().apply {
            translationX(0f)
            duration = 1000
            startDelay = 1000
        }

        binding.startBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}