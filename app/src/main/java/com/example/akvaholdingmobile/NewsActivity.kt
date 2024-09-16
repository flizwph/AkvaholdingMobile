package com.example.akvaholdingmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Предполагаем, что у вас есть список новостей
val newsList = listOf(
    News("пожарная безопастность", "Контент новости 1", 4),
    News("Капитальный ремонт!!", "Контент новости 2", 0, "kapital"),
    News("Предупреждение! Снегопад!", "Контент новости 3", 0, "promej"),
    News("Новые Ставки и тарифы", "Контент новости 4", 0, "tarif"),
    News("Уведомление о внесении изменений", """
        Доводим до вашего сведения, что в октябре 2023 г. внесены изменения в постановление Правительства Москвы № 354 от 06.05.2011г. к "Правилам предоставления коммунальных услуг собственникам и пользователям помещений в многоквартирных домах и жилых домов".
        
        Убедительно просим своевременно производить поверку квартирных приборов учета воды. По истечении срока эксплуатации, определяемого периодом времени до очередной поверки, плата за коммунальную услугу, предоставляемую потребителю, определяется исходя из нормативов потребления коммунальных услуг на каждого зарегистрированного с применением повышающего коэффициента, величина которого принимается равной 3.
        
        Также просим своевременно передавать показания квартирных приборов учета вода с 16-го по 30-е число каждого месяца удобным для Вас способом:
        - через сайты или мобильные приложения "mos.ru", "gosuslugi.ru";
        - по телефону единой сервисной службы: 8 (495) 539-25-25
        
        во избежание начислений платы за коммунальные услуги по воде исходя из нормативов потребления коммунальных услуг.
        
        Администрация Управляющей компании&nbsp;
        ООО "Аква-холдинг"
    """.trimIndent(), 0)
)


class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val backButton: View = findViewById(R.id.back_button)
        backButton.setOnClickListener { onBackPressed() }

        val newsTitles = listOf(
            R.id.news_title_1,
            R.id.news_title_2,
            R.id.news_title_3,
            R.id.news_title_4,
            R.id.news_title_5
        )

        for ((index, titleId) in newsTitles.withIndex()) {
            val newsTitle: TextView = findViewById(titleId)
            newsTitle.setOnClickListener {
                // Создаём Intent для перехода к NewsDetailActivity
                val intent = Intent(this@NewsActivity, NewsDetailActivity::class.java)
                // Передаём индекс новости в качестве дополнительного параметра
                intent.putExtra("newsIndex", index)
                startActivity(intent)
            }
        }
    }
}

