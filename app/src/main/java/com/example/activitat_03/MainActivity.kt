package com.example.activitat_03

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {

    private var pesoActual: Int = 30
    private var edadActual: Int = 20
    private var alturaActual: Int = 140
    lateinit var cardMale: MaterialCardView
    lateinit var cardFemale: MaterialCardView

    lateinit var cardHeight: TextView
    lateinit var sliderHeight: Slider
    lateinit var buttonAddWeight: Button
    lateinit var buttonLessWeight: Button
    lateinit var textWeight: TextView
    lateinit var buttonAddAge: Button
    lateinit var buttonLessAge: Button
    lateinit var textAge: TextView
    lateinit var buttonCalculate: Button


    companion object{
        const val BMI_RESULT = "BMI_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inicializarVariables()
        inicializarListener()
        putHeight()
        putWeight()
        putAge()

    }
    // Inicializo variables -> general
    private fun inicializarVariables()    {
        cardMale = findViewById(R.id.select_male)
        cardFemale = findViewById(R.id.select_female)
        cardHeight = findViewById(R.id.height)
        sliderHeight = findViewById(R.id.slider_height)
        buttonAddWeight = findViewById(R.id.button_add_weight)
        buttonLessWeight= findViewById(R.id.button_less_weight)
        textWeight = findViewById(R.id.textWeight)
        buttonAddAge = findViewById(R.id.button_add_age)
        buttonLessAge = findViewById(R.id.button_less_age)
        textAge = findViewById(R.id.textAge)
        buttonCalculate = findViewById(R.id.calculate)
    }

    // Inicializo los listener para facilita el proceso
    private fun inicializarListener()  {
        cardMale.setOnClickListener { selectGender(cardMale) }
        cardFemale.setOnClickListener { selectGender(cardFemale) }

        // En el caso del slider, necesito addOnChangeListener
        sliderHeight.addOnChangeListener { _, f, _ ->
            alturaActual = f.toInt()
            cardHeight.text = getString(R.string.height_cm, alturaActual)
        }

        buttonAddWeight.setOnClickListener {
            pesoActual += 1
            putWeight()
        }

        buttonLessWeight.setOnClickListener {
            pesoActual = (pesoActual - 1).coerceAtLeast(1)
            putWeight()
        }

        buttonAddAge.setOnClickListener {
            edadActual += 1
            putAge()
        }


        buttonLessAge.setOnClickListener {
            edadActual = (edadActual - 1).coerceAtLeast(1)
            putAge()
        }

        buttonCalculate.setOnClickListener {
            val resultBMI = calculateBMI()
            changeActivity(resultBMI)
        }
    }
    private fun selectGender(card : View?) {
        if (card == cardMale) {
            cardMale.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_card_selected))
            cardFemale.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_card))
        } else if (card == cardFemale) {
            cardMale.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_card))
            cardFemale.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_card_selected))
        }
    }

    private fun putHeight() {
        cardHeight.text = getString(R.string.height_cm, alturaActual)
    }
    private fun putWeight() {
        textWeight.text = pesoActual.toString()
    }

    private fun putAge() {
        textAge.text =edadActual.toString()
    }

    private fun calculateBMI(): Double {
        val heightMt = alturaActual /100.0
        val bmi = pesoActual/(heightMt * heightMt)
        return bmi
    }

    private fun changeActivity(resultBMI: Double) {

        val navigationA2 = Intent(this, MainActivity2::class.java)
        navigationA2.putExtra(BMI_RESULT, resultBMI)
        startActivity(navigationA2)

    }
}

