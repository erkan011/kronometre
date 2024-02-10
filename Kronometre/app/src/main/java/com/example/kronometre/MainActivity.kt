package com.example.kronometre

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import com.example.kronometre.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var zamanidurdur: Long = 0

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {
            if (!binding.kronometre.isActivated) {
                binding.kronometre.base = SystemClock.elapsedRealtime()
            }

            val elapsedTimeSincePause = SystemClock.elapsedRealtime() - binding.kronometre.base
            binding.kronometre.base = SystemClock.elapsedRealtime() - elapsedTimeSincePause + zamanidurdur
            binding.kronometre.start()
            binding.buttonStart.visibility = View.GONE
            binding.buttonPause.visibility = View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.pause))
        }

        binding.buttonPause.setOnClickListener {
            zamanidurdur = binding.kronometre.base - SystemClock.elapsedRealtime()
            binding.kronometre.stop()
            binding.buttonPause.visibility = View.GONE
            binding.buttonStart.visibility = View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.start))
        }

        binding.buttonReset.setOnClickListener {
            binding.kronometre.base = SystemClock.elapsedRealtime()
            zamanidurdur = 0
            binding.kronometre.stop()
            binding.buttonPause.visibility = View.GONE
            binding.buttonStart.visibility = View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.start))
        }

    }
}
