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

    lateinit var card_male: MaterialCardView
    lateinit var card_female: MaterialCardView

    lateinit var card_height: TextView
    lateinit var slider_height: Slider
    lateinit var card_weight: MaterialCardView
    lateinit var card_age: MaterialCardView

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
        card_weight = findViewById(R.id.select_weight)
        card_age= findViewById(R.id.select_age)
        button_calculate = findViewById(R.id.calculate)
    }

    // Inicializo los listener para facilita el proceso
    private fun inicializarListener()  {
        card_male.setOnClickListener { selectGender(card_male) }
        card_female.setOnClickListener { selectGender(card_female) }

        // En el caso del slider, necesito addOnChangeListener
        slider_height.addOnChangeListener { _, f, _ ->

            val decimal = DecimalFormat("#.##")
            val cambioHeight = decimal.format(f)
            card_height.text = "$cambioHeight cm"
        }
    }
    fun selectGender(card : View?) {
        if (card == card_male) {
            card_male.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_card_selected))
            card_female.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_card))
        } else if (card == card_female) {
            card_male.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_card))
            card_female.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_card_selected))
        }
    }



}

