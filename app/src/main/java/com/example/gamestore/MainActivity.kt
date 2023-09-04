package com.example.gamestore

import Game // Import kelas Game dari package yang sesuai
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamestore.R

// Deklarasi kelas MainActivity yang merupakan turunan dari AppCompatActivity
class MainActivity : AppCompatActivity() {
    private lateinit var rvGame: RecyclerView
    private val list = ArrayList<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengatur tampilan aktivitas dengan layout activity_main
        setContentView(R.layout.activity_main)

        // Menginisialisasi RecyclerView
        rvGame = findViewById(R.id.rv_game)
        rvGame.setHasFixedSize(true)

        // Mendapatkan data permainan dan menampilkannya dalam RecyclerView
        list.addAll(getListGame())
        showRecyclerList()
    }

    // Metode ini digunakan untuk mendapatkan daftar permainan dari sumber daya
    private fun getListGame(): ArrayList<Game> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val dataRelease = resources.getStringArray(R.array.data_release)
        val listGame = ArrayList<Game>()
        for (i in dataName.indices) {
            val game = Game(
                dataName[i],
                dataDescription[i],
                dataPhoto.getResourceId(i, -1),
                dataPrice[i],
                dataRelease[i]
            )
            listGame.add(game)
        }
        return listGame
    }

    // Metode ini digunakan untuk menampilkan daftar permainan dalam RecyclerView
    private fun showRecyclerList() {
        rvGame.layoutManager = LinearLayoutManager(this)
        val listGameAdapter = ListGameAdapter(list)
        rvGame.adapter = listGameAdapter

        // Menetapkan callback untuk item yang diklik dalam RecyclerView
        listGameAdapter.setOnItemClickCallback(object : ListGameAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Game) {
                // Memulai aktivitas DetailActivity dengan data permainan yang dipilih
                val intent = Intent(applicationContext, DetailActivity::class.java)
                intent.putExtra(DetailActivity.DATA_GAME, data)
                startActivity(intent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Menggabungkan menu utama
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Menangani item menu yang dipilih
        when (item.itemId) {
            R.id.action_list -> {
                rvGame.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvGame.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.about -> {
                // Memulai aktivitas AboutActivity
                startActivity(Intent(this, AboutActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
