package com.example.gamestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// Deklarasi kelas AboutActivity yang merupakan turunan dari AppCompatActivity
class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengatur tata letak aktivitas dengan layout activity_about
        setContentView(R.layout.activity_about)
        // Mengatur judul ActionBar dengan string "about"
        supportActionBar?.title = getString(R.string.about)
        // Menampilkan tombol panah navigasi ke atas pada ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        // Mengakhiri aktivitas saat tombol panah navigasi ke atas ditekan
        finish()
        // Memanggil metode onSupportNavigateUp dari kelas induk
        return super.onSupportNavigateUp()
    }
}
