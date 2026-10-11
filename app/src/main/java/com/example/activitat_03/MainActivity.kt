package com.example.activitat_03

import android.content.Context
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
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private var pesoActual: Int = 30
    private var edadActual: Int = 20
    private var alturaActual: Int = 140
    lateinit var card_male: MaterialCardView
    lateinit var card_female: MaterialCardView

    lateinit var card_height: TextView
    lateinit var slider_height: Slider
    lateinit var button_add_weight: Button
    lateinit var button_less_weight: Button
    lateinit var textWeight: TextView
    lateinit var button_add_age: Button
    lateinit var button_less_age: Button
    lateinit var textAge: TextView
    lateinit var button_calculate: Button




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

    }
    // Inicializo variables -> general
    private fun inicializarVariables()    {
        card_male = findViewById(R.id.select_male)
        card_female = findViewById(R.id.select_female)
        card_height = findViewById(R.id.height)
        slider_height = findViewById(R.id.slider_height)
        button_add_weight = findViewById(R.id.button_add_weight)
        button_less_weight= findViewById(R.id.button_less_weight)
        textWeight = findViewById(R.id.textWeight)
        button_add_age = findViewById(R.id.button_add_age)
        button_less_age = findViewById(R.id.button_less_age)
        textAge = findViewById(R.id.textAge)
        button_calculate = findViewById(R.id.calculate)
    }

    // Inicializo los listener para facilita el proceso
    private fun inicializarListener()  {
        card_male.setOnClickListener { selectGender(card_male) }
        card_female.setOnClickListener { selectGender(card_female) }

        // En el caso del slider, necesito addOnChangeListener
        slider_height.addOnChangeListener { _, f, _ ->
            alturaActual = f.toInt()
            card_height.text = "$alturaActual cm"
        }

        button_add_weight.setOnClickListener {
            pesoActual += 1
            putWeight()
        }

        button_less_weight.setOnClickListener {
            pesoActual = (pesoActual - 1).coerceAtLeast(1)
            putWeight()
        }

        button_add_age.setOnClickListener {
            edadActual += 1
            putAge()
        }

        button_less_age.setOnClickListener {
            edadActual = (edadActual - 1).coerceAtLeast(1)
            putAge()
        }

        button_calculate.setOnClickListener {
            calculatBMI()
            changeActivity()
        }
    }
    private fun selectGender(card : View?) {
        if (card == card_male) {
            card_male.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_card_selected))
            card_female.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_card))
        } else if (card == card_female) {
            card_male.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_card))
            card_female.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_card_selected))
        }
    }

    private fun putWeight() {
        textWeight.text = pesoActual.toString()
    }

    private fun putAge() {
        textAge.text =edadActual.toString()
    }

    private fun calculatBMI() {
        val bmi = pesoActual/(alturaActual.toDouble()/100 * alturaActual.toDouble()/100)

    }

    private fun changeActivity() {

    }
}

