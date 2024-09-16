package com.example.akvaholdingmobile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

@Suppress("DEPRECATION")
class SecondActivity : AppCompatActivity() {

    private val buttonIds = arrayOf(
        R.id.buttonMeteringDevices,
        R.id.buttonElevatorIssue,
        R.id.buttonLightingIssue,
        R.id.buttonEngineeringSystemsIssue,
        R.id.buttonSanitary,
        R.id.buttonOther
    )

    private val fieldGroups = arrayOf(
        arrayOf(R.id.checkBoxMeteringDevices1, R.id.editTextWhereMeteringDevices1, R.id.imageButtonMeteringDevices1, R.id.imageViewMeteringDevices1,
            R.id.checkBoxMeteringDevices2, R.id.editTextWhereMeteringDevices2, R.id.imageButtonMeteringDevices2, R.id.imageViewMeteringDevices2,
            R.id.checkBoxMeteringDevices3, R.id.editTextWhereMeteringDevices3, R.id.imageButtonMeteringDevices3, R.id.imageViewMeteringDevices3),
        arrayOf(R.id.checkBoxElevatorIssue1, R.id.checkBoxElevatorIssue2, R.id.editTextElevatorIssue, R.id.imageButtonElevatorIssue, R.id.imageViewElevatorIssue, R.id.editTextElevatorIssueEnter, R.id.editTextElevatorIssueFloor),
        arrayOf(R.id.editTextLightingIssueFloor, R.id.editTextLightingIssueEntrance, R.id.editTextLightingIssuePlace, R.id.EditTextLightingIssueDiscr,
            R.id.imageButtonLightingIssue, R.id.imageViewLightingIssue),
        arrayOf(R.id.checkBoxEngineeringSystemsIssueColdWater, R.id.checkBoxEngineeringSystemsIssueHotWater, R.id.checkBoxEngineeringSystemsIssueSewerage,
            R.id.checkBoxEngineeringSystemsIssueHeating, R.id.editTextEngineeringSystemsIssuePlace, R.id.editTextEngineeringSystemsIssueEnter, R.id.editTextEngineeringSystemsIssueFloor, R.id.editTextEngineeringSystemsIssueDiscr,
            R.id.imageButtonEngineeringSystemsIssue, R.id.imageViewEngineeringSystemsIssue),
        arrayOf(R.id.editTextSanitaryPlace, R.id.editTextSanitaryDiscr,R.id.editTextSanitaryEnter, R.id.editTextSanitaryFloor, R.id.imageButtonSanitary, R.id.imageViewSanitary),
        arrayOf(R.id.editTextOtherPlace, R.id.editTextOtherDiscr, R.id.editTextOtherEnter, R.id.editTextOtherFloor, R.id.imageButtonOther, R.id.imageViewOther)
    )

    private  val PICK_IMAGE_REQUEST = 1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        buttonIds.forEachIndexed { index, buttonId ->
            findViewById<Button>(buttonId).setOnClickListener { handleButtonClick(index) }
        }

        findViewById<Button>(R.id.sendButton).setOnClickListener { sendEmail() }

        // Установка слушателей для всех кнопок добавления фото
        fieldGroups.flatten().forEach { fieldId ->
            if (fieldId == R.id.imageButtonMeteringDevices1 ||
                fieldId == R.id.imageButtonMeteringDevices2 ||
                fieldId == R.id.imageButtonMeteringDevices3 ||
                fieldId == R.id.imageButtonElevatorIssue ||
                fieldId == R.id.imageButtonLightingIssue ||
                fieldId == R.id.imageButtonEngineeringSystemsIssue ||
                fieldId == R.id.imageButtonSanitary ||
                fieldId == R.id.imageButtonOther) {

                findViewById<ImageButton>(fieldId).setOnClickListener {
                    openGallery()
                }
            }
        }
    }

    private fun handleButtonClick(index: Int) {
        try {
            val visibleFields = fieldGroups.getOrNull(index) ?: return

            fieldGroups.flatten().forEach { fieldId ->
                findViewById<View>(fieldId)?.apply {
                    visibility = View.GONE
                    clearAnimation()
                }
            }

            visibleFields.forEach { fieldId ->
                findViewById<View>(fieldId)?.apply {
                    visibility = View.VISIBLE
                    startAnimation(AnimationUtils.loadAnimation(this@SecondActivity, R.anim.slide_down))
                }
            }

            updateLayout()
        } catch (e: Exception) {
            Log.e("SecondActivity", "Ошибка при обработке нажатия кнопки", e)
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data?.data != null) {
            val selectedImageUri: Uri = data.data!!
            fieldGroups.flatten().forEach { fieldId ->
                val view = findViewById<View>(fieldId)
                if (view is ImageView && view.visibility == View.VISIBLE) {
                    view.setImageURI(selectedImageUri)
                }
            }
        }
    }

    private fun updateLayout() {
        val layout = findViewById<ConstraintLayout>(R.id.main)
        val constraintSet = ConstraintSet().apply { clone(layout) }

        var lastViewId = R.id.addressEditText

        buttonIds.forEachIndexed { index, buttonId ->
            constraintSet.connect(buttonId, ConstraintSet.TOP, lastViewId, ConstraintSet.BOTTOM, 16)
            constraintSet.connect(buttonId, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16)
            constraintSet.connect(buttonId, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16)
            lastViewId = buttonId

            fieldGroups.getOrNull(index)?.forEach { fieldId ->
                constraintSet.connect(fieldId, ConstraintSet.TOP, lastViewId, ConstraintSet.BOTTOM, 8)
                constraintSet.connect(fieldId, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16)
                constraintSet.connect(fieldId, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16)
                lastViewId = fieldId
            }
        }

        constraintSet.connect(R.id.sendButton, ConstraintSet.TOP, lastViewId, ConstraintSet.BOTTOM, 16)
        constraintSet.connect(R.id.sendButton, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16)
        constraintSet.connect(R.id.sendButton, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16)

        constraintSet.applyTo(layout)
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun sendEmail() {
        val data = StringBuilder()
        val requiredFields = arrayOf(
            R.id.lastNameEditText1,
            R.id.nameEditText1,
            R.id.patronymicEditText1,
            R.id.phoneEditText1,
            R.id.addressEditText1
        )

        var allFieldsFilled = true

        // Проверка обязательных полей
        for (fieldId in requiredFields) {
            val view = findViewById<EditText>(fieldId)
            if (view.text.isEmpty()) {
                view.setBackgroundColor(Color.RED)  // Подсветка поля красным цветом
                allFieldsFilled = false
            } else {
                view.setBackgroundColor(Color.TRANSPARENT)  // Сбрасываем цвет фона, если поле заполнено
            }
        }

        if (!allFieldsFilled) {
            Toast.makeText(this, "Пожалуйста, заполните необходимые поля.", Toast.LENGTH_SHORT).show()
            return
        }

        // Обработка дополнительных полей
        requiredFields.forEach { fieldId ->
            val view = findViewById<EditText>(fieldId)
            if (view.text.isNotEmpty()) {
                data.append(view.hint).append(": ").append(view.text).append("\n")
            }
        }

        // Обработка полей, открывающихся по нажатию кнопок
        buttonIds.forEachIndexed { index, buttonId ->
            val button = findViewById<Button>(buttonId)
            val groupFields = fieldGroups.getOrNull(index) ?: return@forEachIndexed

            val groupData = StringBuilder()

            groupFields.forEach { fieldId ->
                when (val view = findViewById<View>(fieldId)) {
                    is EditText -> {
                        if (view.text.isNotEmpty()) {
                            groupData.append(view.hint).append(": ").append(view.text).append("\n")
                        }
                    }
                    is CheckBox -> {
                        if (view.isChecked) {
                            groupData.append(view.text).append("\n")
                        }
                    }
                }
            }

            if (groupData.isNotEmpty()) {
                data.append(button.text).append("\n").append(groupData).append("\n")
            }
        }

        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("pidorokpidorasovich@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Данные из приложения")
            putExtra(Intent.EXTRA_TEXT, data.toString())
        }

        if (emailIntent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(emailIntent, "Отправить email..."))
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
