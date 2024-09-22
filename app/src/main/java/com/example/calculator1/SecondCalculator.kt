package com.example.calculator1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SecondCalculator : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.second_calculator)
        // Знаходження всіх потрібних елементів
        val C_P: EditText = findViewById(R.id.c_p)
        val H_P: EditText = findViewById(R.id.h_p)
        val O_P: EditText = findViewById(R.id.o_p)
        val S_P: EditText = findViewById(R.id.s_p)
        val Q_DAF: EditText = findViewById(R.id.q_daf)
        val VRMP_P: EditText = findViewById(R.id.vrmp_p)
        val A_P: EditText = findViewById(R.id.a_p)
        val V_P: EditText = findViewById(R.id.v_p)
        val calculate: Button = findViewById(R.id.submit_button)
        val go_back: Button = findViewById(R.id.back_button)
        val result: TextView = findViewById(R.id.result)
        // Повертання на головний екран
        go_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        calculate.setOnClickListener{
            val c_p = C_P.getText().toString();
            val h_p = H_P.getText().toString();
            val o_p = O_P.getText().toString();
            val s_p = S_P.getText().toString();
            val q_daf = Q_DAF.getText().toString();
            val vrmp_p = VRMP_P.getText().toString();
            val a_p = A_P.getText().toString();
            val v_p = V_P.getText().toString();

            var errorMessage = ""

            // Функція що повертає число якщо воно конвертується і дійсне а якщо ні то додає повідомлення про помилку
            fun checkAndConvert(value: String, fieldName: String): Float? {
                return if (value.isEmpty()) {

                    errorMessage += "$fieldName is empty.\n "
                    null
                } else {
                    try {
                        value.toFloat()
                    } catch (e: NumberFormatException) {
                        errorMessage += "$fieldName is not a valid number. "
                        null
                    }
                }
            }

            // Перевірка всіх чисел
            val c_p_val = checkAndConvert(c_p, "C_P")
            val h_p_val = checkAndConvert(h_p, "H_P")
            val o_p_val = checkAndConvert(o_p, "O_P")
            val s_p_val = checkAndConvert(s_p, "S_P")
            val q_daf_val = checkAndConvert(q_daf, "Q_DAF")
            val vrmp_p_val = checkAndConvert(vrmp_p, "N_P")
            val a_p_val = checkAndConvert(a_p, "W_P")
            val v_p_val = checkAndConvert(v_p, "A_P")

            if (errorMessage.isNotEmpty()) {
                // Якщо є помилка то спровіщуємо про це
                result.text = errorMessage
            } else {
                // Обраховуємо значення
                val krs = (100 - vrmp_p_val!! - a_p_val!!) / 100
                val c_w = c_p_val!! * krs
                val h_w = h_p_val!! * krs
                val s_w = s_p_val!! * krs
                val o_w = o_p_val!! * krs
                val v_w = v_p_val!! * (100 - vrmp_p_val) / 100
                val lcvwm = q_daf_val!! * (100 - vrmp_p_val - a_p_val) / 100 - 0.025 * vrmp_p_val

                // Складаємо текст результату
                val result_text = """
                    
                    Склад робочої маси:
                    C = ${"%.2f".format(c_w)}%,
                    H = ${"%.2f".format(h_w)}%,
                    S = ${"%.2f".format(s_w)}%,
                    O = ${"%.2f".format(o_w)}%,
                    A = ${"%.2f".format(a_p_val)}% 
                    V = ${"%.2f".format(v_w)}мг/кг,
                    fromula = ${"%.2f".format(q_daf_val)} * (100 - ${"%.2f".format(vrmp_p_val)} - ${"%.2f".format(a_p_val)}) / 100 - 0.025 * ${"%.2f".format(vrmp_p_val)}
                    Нижча теплота згоряння для робочої маси: ${"%.2f".format(lcvwm)} МДж/кг]            
            """.trimIndent()

                // Виводимо результат
                result.text = result_text
            }
        }


    }
}