package com.example.akvaholdingmobile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuestionsActivity : AppCompatActivity() {
    private lateinit var questionTextViews: Array<TextView>
    private lateinit var showHideButtons: Array<Button>
    private lateinit var responseTextViews: Array<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        // Инициализация элементов UI
        questionTextViews = arrayOf(
            findViewById(R.id.question_text),
            findViewById(R.id.question2),
            findViewById(R.id.question3),
            findViewById(R.id.question4),
            findViewById(R.id.question5)
        )
        showHideButtons = arrayOf(
            findViewById(R.id.show_hide_button),
            findViewById(R.id.show_hide_button2),
            findViewById(R.id.show_hide_button3),
            findViewById(R.id.show_hide_button4),
            findViewById(R.id.show_hide_button5)
        )
        responseTextViews = arrayOf(
            findViewById(R.id.response_text),
            findViewById(R.id.response2),
            findViewById(R.id.response3),
            findViewById(R.id.response4),
            findViewById(R.id.response5)
        )

        // Устанавливаем слушатель нажатия на кнопку "Назад"
        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressed()
            // Скрыть все ответы при возврате
            for ((button, response) in showHideButtons.zip(responseTextViews)) {
                hideResponse(button, response)
            }
        }

        // Устанавливаем слушатели нажатий на текст вопроса и кнопки "Открыть ответ"
        for (i in questionTextViews.indices) {
            val (question, button, response) = Triple(questionTextViews[i], showHideButtons[i], responseTextViews[i])
            question.setOnClickListener { toggleResponseVisibility(question, button, response) }
            button.setOnClickListener { toggleResponseVisibility(question, button, response) }
        }
    }

    private fun toggleResponseVisibility(question: TextView, button: Button, response: TextView) {
        if (response.visibility == View.GONE) {
            button.text = "Закрыть ответ"
            response.visibility = View.VISIBLE
        } else {
            button.text = "Открыть ответ"
            response.visibility = View.GONE
        }
    }

    private fun hideResponse(button: Button, response: TextView) {
        response.visibility = View.GONE
    }
}