package ru.kpfu.stud.decepticons.quizcard

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*
import java.util.*

class QuizActivity : AppCompatActivity() {

    private lateinit var pref: SharedPreferences
    private val APP_PREFERENCES = "settings"
    private val APP_PREFERENCES_SAVED_CARDS = "savedCards"
    private var savedCards = hashSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val flag = intent.getBooleanExtra("FLAG", true)
        var quizQueue: Queue<Card> = LinkedList(CardsRepository.cards.shuffled().take(10))
        var count = 0
        var countText = "Переведено $count / 10"
        tv_translated.text = countText
        if (flag) {
            tv_card_text.text = quizQueue.peek().engText
            tv_lang.text = "Перевод на русский язык:"
            et_translation.setHint(R.string.translate)
        } else {
            tv_card_text.text = quizQueue.peek().ruText
            tv_lang.text = "Перевод на английский язык:"
            et_translation.setHint(R.string.translate2)
        }

        button_for_check.setOnClickListener {
            if (flag) {
                if (et_translation.text.toString().toLowerCase(Locale.ROOT) == quizQueue.peek().ruText.toLowerCase(Locale.ROOT)) {
                    count++
                    quizQueue.remove()
                    Toast.makeText(this, "Правильно!", Toast.LENGTH_LONG).show()
                } else {
                    quizQueue.add(quizQueue.poll())
                    Toast.makeText(this, "Ошибка!", Toast.LENGTH_LONG).show()
                }
            } else {
                if (et_translation.text.toString().toLowerCase(Locale.ROOT) == quizQueue.peek().engText.toLowerCase(Locale.ROOT)) {
                    count++
                    quizQueue.remove()
                    Toast.makeText(this, "Правильно!", Toast.LENGTH_LONG).show()
                } else {
                    quizQueue.add(quizQueue.poll())
                    Toast.makeText(this, "Ошибка!", Toast.LENGTH_LONG).show()
                }
            }
            if (quizQueue.isEmpty()) {
                val intent = Intent(this, QuizFinishActivity::class.java)
                startActivity(intent)
            } else {
                countText = "Переведено $count / 10"
                tv_translated.text = countText
                et_translation.text.clear()
                if (flag) {
                    tv_card_text.text = quizQueue.peek().engText
                } else {
                    tv_card_text.text = quizQueue.peek().ruText
                }
            }
        }

        button_for_translate.setOnClickListener {
            if (flag) {
                Toast.makeText(this, quizQueue.peek().ruText, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, quizQueue.peek().engText, Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onStop() {
        super.onStop()
        pref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        val editor = pref.edit()
        CardsRepository.cards.forEach {
            savedCards.add(it.toString())
        }
        editor.putStringSet(APP_PREFERENCES_SAVED_CARDS, savedCards)
        editor.apply()
    }

}
