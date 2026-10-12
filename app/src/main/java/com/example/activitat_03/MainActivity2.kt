package com.example.activitat_03

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activitat_03.MainActivity.Companion.BMI_RESULT

class MainActivity2 : AppCompatActivity() {

    private lateinit var textResult: TextView
    private lateinit var textBMI: TextView
    private lateinit var textDescription : TextView
    private lateinit var buttonRecalculate : Button
    private lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val resultBMI = intent.extras?.getDouble(BMI_RESULT)?: -1.0
        inicializarVariables()
        inicializarResultadoBMI(resultBMI)
        inicializarListener()
    }

    private fun inicializarVariables() {
        textResult = findViewById(R.id.textResult)
        textBMI = findViewById(R.id.textBMI)
        textDescription = findViewById(R.id.textDescription)
        buttonRecalculate = findViewById(R.id.button_recalculate)
        buttonBack = findViewById(R.id.button_back)
    }

    private fun inicializarListener() {
        buttonRecalculate.setOnClickListener {
            finish()
        }
        buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun inicializarResultadoBMI(resultBMI: Double) {
        if (resultBMI <= 0)  {
            textResult.setText(R.string.error)
            return
        }

        textBMI.text = String.format("%.2f", resultBMI)

        when {

            resultBMI < 18.50 -> {
                // bajo peso
                textResult.text = getString(R.string.title_bajo_peso)
                textResult.setTextColor(ContextCompat.getColor(this, R.color.bajo_peso))
                textDescription.text = getString(R.string.description_bajo_peso)

            }
            resultBMI < 25.00 -> {
                // peso normal
                textResult.text = getString(R.string.title_peso_normal)
                textResult.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                textDescription.text = getString(R.string.description_peso_normal)

            }
            resultBMI < 30.00 -> {
                // sobrepeso
                textResult.text = getString(R.string.title_sobrepeso)
                textResult.setTextColor(ContextCompat.getColor(this, R.color.sobrepeso))
                textDescription.text = getString(R.string.description_sobrepeso)

            }
            resultBMI < 35.00 -> {
                // obesidad clase I
                textResult.text = getString(R.string.title_obesidadI)
                textResult.setTextColor(ContextCompat.getColor(this, R.color.obesidadI))
                textDescription.text = getString(R.string.description_obesidadI)

            }
            resultBMI < 40.00 -> {
                // obesidad clase II
                textResult.text = getString(R.string.title_obesidadII)
                textResult.setTextColor(ContextCompat.getColor(this, R.color.obesidadII))
                textDescription.text = getString(R.string.description_obesidadII)
            }
            else -> {
                // obesidad clase III (Mórbida)
                textResult.text = getString(R.string.title_obesidadIII)
                textResult.setTextColor(ContextCompat.getColor(this, R.color.obesidadIII))
                textDescription.text = getString(R.string.description_obesidadIII)
            }
        }
    }
}
