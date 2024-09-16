package com.example.akvaholdingmobile

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class NewsDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val newsIndex = intent.getIntExtra("newsIndex", -1)

        if (newsIndex != -1 && newsIndex < newsList.size) {
            val news = newsList[newsIndex]
            val newsTitleTextView: TextView = findViewById(R.id.news_detail_title)
            newsTitleTextView.text = news.title

            val imageViewKapital: ImageView = findViewById(R.id.imageView_kapital)
            if (news.imagePath == "kapital") {
                imageViewKapital.setImageResource(R.drawable.kapital)
                imageViewKapital.visibility = View.VISIBLE
            } else {
                imageViewKapital.visibility = View.GONE
            }

            val imageViewPromej: ImageView = findViewById(R.id.imageView_promej)
            if (news.imagePath == "promej") {
                imageViewPromej.setImageResource(R.drawable.promej)
                imageViewPromej.visibility = View.VISIBLE
            } else {
                imageViewPromej.visibility = View.GONE
            }

            val imageViewTarif: ImageView = findViewById(R.id.imageView_tarif)
            if (news.imagePath == "tarif") {
                imageViewTarif.setImageResource(R.drawable.tarif)
                imageViewTarif.visibility = View.VISIBLE
            } else {
                imageViewTarif.visibility = View.GONE
            }

            val newsContentTextView: TextView = findViewById(R.id.news_detail_content)
            newsContentTextView.text = news.content

            val backButton: ImageView = findViewById(R.id.back_button)
            backButton.setOnClickListener {
                onBackPressed()
            }
        } else {
            // Обработка случая, когда индекс новости недействителен или отсутствует
        }
    }
}


