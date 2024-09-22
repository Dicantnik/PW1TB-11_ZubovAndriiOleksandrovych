package com.example.calculator1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class FirstCalculator : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.first_calculator)
        // Знаходження всіх потрібних елементів
        val H_P: EditText = findViewById(R.id.h_p)
        val C_P: EditText = findViewById(R.id.c_p)
        val S_P: EditText = findViewById(R.id.s_p)
        val N_P: EditText = findViewById(R.id.n_p)
        val O_P: EditText = findViewById(R.id.o_p)
        val W_P: EditText = findViewById(R.id.w_p)
        val A_P: EditText = findViewById(R.id.a_p)
        val calculate: Button = findViewById(R.id.submit_button)
        val go_back: Button = findViewById(R.id.back_button)
        val result: TextView = findViewById(R.id.result)
        // Повертання на головний екран
        go_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        calculate.setOnClickListener{
            val h_p = H_P.getText().toString();
            val c_p = C_P.getText().toString();
            val s_p = S_P.getText().toString();
            val n_p = N_P.getText().toString();
            val o_p = O_P.getText().toString();
            val w_p = W_P.getText().toString();
            val a_p = A_P.getText().toString();

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
                        errorMessage += "$fieldName is not a valid number.\n "
                        null
                    }
                }
            }

            // Перевірка всіх чисел
            val h_p_val = checkAndConvert(h_p, "H_P")
            val c_p_val = checkAndConvert(c_p, "C_P")
            val s_p_val = checkAndConvert(s_p, "S_P")
            val n_p_val = checkAndConvert(n_p, "N_P")
            val o_p_val = checkAndConvert(o_p, "O_P")
            val w_p_val = checkAndConvert(w_p, "W_P")
            val a_p_val = checkAndConvert(a_p, "A_P")

            if (errorMessage.isNotEmpty()) {
                // Якщо є помилка то спровіщуємо про це
                result.text = errorMessage
            } else {
                // Обраховуємо значення
                val krs = 100 / (100 - w_p_val!!)
                val krg = 100 / (100 - w_p_val - a_p_val!!)
                val lcvwm = (339 * c_p_val!! + 1030 * h_p_val!! - 108.8 * (o_p_val!! - s_p_val!!) - 25 * w_p_val) / 1000
                val lcvdm = (lcvwm + 0.025 * w_p_val) * krs
                val lcvcm = (lcvwm + 0.025 * w_p_val) * krg
                // Складаємо текст результату
                val result_text = """
                    Коефіцієнт переходу до сухої маси: ${"%.2f".format(krs)}
                    Коефіцієнт переходу до горючої маси: ${"%.2f".format(krg)}                    
                    Суха маса:
                    H = ${"%.2f".format(h_p_val * krs)}%,
                    C = ${"%.2f".format(c_p_val * krs)}%,
                    S = ${"%.2f".format(s_p_val * krs)}%,
                    N = ${"%.3f".format(n_p_val!! * krs)}%,
                    O = ${"%.2f".format(o_p_val * krs)}%,
                    A = ${"%.2f".format(a_p_val * krs)}%             
                    Горюча маса:
                    H = ${"%.2f".format(h_p_val * krg)}%,
                    C = ${"%.2f".format(c_p_val * krg)}%,
                    S = ${"%.2f".format(s_p_val * krg)}%,
                    N = ${"%.3f".format(n_p_val * krg)}%,
                    O = ${"%.2f".format(o_p_val * krg)}%                    
                    Нижня теплота згоряння робочої маси: ${"%.4f".format(lcvwm)} МДж/кг
                    Нижня теплота згоряння сухої маси: ${"%.2f".format(lcvdm)} МДж/кг
                    Нижня теплота згоряння горючої маси: ${"%.1f".format(lcvcm)} МДж/кг
            """.trimIndent()

                // Виводимо результат
                result.text = result_text
            }
        }



    }
}