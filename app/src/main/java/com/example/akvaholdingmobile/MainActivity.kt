package com.example.akvaholdingmobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navView: NavigationView // Объявляем переменную navView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Настройка виджета для обработки системных полос
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Настройка кнопки для создания заявки
        val createApplicationButton: Button = findViewById(R.id.create_application)
        createApplicationButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        // Настройка кнопки для новостей
        val newsButton: Button = findViewById(R.id.news_button)
        newsButton.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }

        // Настройка кнопки для информации о компании
        val aboutCompanyButton: Button = findViewById(R.id.about_button)
        aboutCompanyButton.setOnClickListener {
            val intent = Intent(this, AboutCompanyActivity::class.java)
            startActivity(intent)
        }

        // Настройка кнопки для информации о платежах
        val paymentInfoButton: Button = findViewById(R.id.payment_button)
        paymentInfoButton.setOnClickListener {
            val intent = Intent(this, PaymentInfoActivity::class.java)
            startActivity(intent)
        }

        // Настройка кнопки для вопросов
        val questionsButton: Button = findViewById(R.id.questions_button)
        questionsButton.setOnClickListener {
            val intent = Intent(this, QuestionsActivity::class.java)
            startActivity(intent)
        }

        // Настройка кнопки для информации
        val informationButton: Button = findViewById(R.id.info_button)
        informationButton.setOnClickListener {
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }

        // Настройка TextView для телефона
        val phoneTextView: TextView = findViewById(R.id.contact_info)
        phoneTextView.setOnClickListener {
            val phoneNumber = phoneTextView.text.toString()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }

        // Настройка ActionBar
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_burger)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.burger_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Логика для кнопки "Домой"
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
