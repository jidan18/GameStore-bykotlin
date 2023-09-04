package com.example.gamestore

import Game // Import kelas Game dari package yang sesuai
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListGameAdapter(private val listGame: ArrayList<Game>) : RecyclerView.Adapter<ListGameAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Metode ini digunakan untuk mengatur callback ketika item di klik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        // Menginflate layout item_article untuk setiap item di RecyclerView
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        // Mendapatkan data Game pada posisi tertentu dalam listGame
        val (name, description, photo) = listGame[position]

        // Mengatur data ke elemen-elemen tampilan pada ViewHolder
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description

        // Menambahkan listener untuk item pada ViewHolder
        holder.itemView.setOnClickListener {
            // Memanggil callback onItemClicked dengan data Game yang sesuai
            onItemClickCallback.onItemClicked(listGame[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listGame.size // Mengembalikan jumlah item dalam RecyclerView

    // Kelas ViewHolder yang digunakan untuk menyimpan elemen-elemen tampilan item
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    // Interface untuk callback ketika item di klik
    interface OnItemClickCallback {
        fun onItemClicked(data: Game)
    }
}
