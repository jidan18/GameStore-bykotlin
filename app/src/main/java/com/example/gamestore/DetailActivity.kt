package com.example.gamestore

import Game
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.gamestore.databinding.ActivityDetailBinding


@Suppress("DEPRECATION", "NAME_SHADOWING")
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    // Nama konstanta untuk kirim data
    companion object {
        const val DATA_GAME = "data_game"
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan objek Game dari intent
        val data = intent.getParcelableExtra<Game>(DATA_GAME)

        // Menampilkan data game ke tampilan jika data tidak null
        if (data != null) {
            binding.imgGame.setImageResource(data.photo)
            binding.tvGameTitle.text = data.title
            binding.tvGameDescription.text = data.description
            binding.tvDetailPrice.text = data.price
            binding.tvDetailRelease.text = data.release
        }

        // Mengatur judul ActionBar dengan nama game jika data tidak null
        supportActionBar?.title = data?.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Mendapatkan referensi tombol Share
        val shareButton: Button = findViewById(R.id.action_share)

        // Menambahkan onClickListener untuk tombol Share
        shareButton.setOnClickListener {
            val data = intent.getParcelableExtra<Game>(DATA_GAME)

            if (data != null) {
                data.title?.let { it1 -> data.description?.let { it2 -> data.price?.let { it3 -> shareDetail(it1, it2, it3) } } }
            } else {
                showToast("Data game tidak ditemukan")
            }
        }
    }

    // Fungsi untuk menampilkan pesan Toast
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Override untuk tombol kembali di ActionBar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    // Fungsi untuk berbagi informasi detail game
    private fun shareDetail(nameGame: String, informations: String, price: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        val detailText ="Game Name : $nameGame \n\nGame Description : $informations" +
                "\n\nGame Price : $price"
        shareIntent.putExtra(Intent.EXTRA_TEXT, detailText)
        startActivity(Intent.createChooser(shareIntent, "Bagikan informasi melalui:"))
    }
}