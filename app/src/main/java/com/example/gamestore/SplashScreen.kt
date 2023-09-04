package com.example.gamestore

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Membuat Handler untuk menjalankan kode di thread utama (UI thread).
        val handler = Handler(Looper.getMainLooper())

        // Menunda tampilan SplashScreen selama 3 detik (3000 milidetik).
        handler.postDelayed({
            // Membuat intent untuk berpindah ke MainActivity.
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Menutup SplashScreen agar tidak kembali ke sana saat menekan tombol "kembali".
            finish()
        }, 3000) // Delay selama 3 detik (3000 milidetik).
    }
}
