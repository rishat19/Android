package ru.kpfu.stud.decepticons.quizcard

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_card_adding.*
import kotlinx.android.synthetic.main.activity_list_of_cards.*
import kotlinx.android.synthetic.main.activity_list_of_cards.button_back

class CardAddingActivity : AppCompatActivity() {

    private lateinit var pref: SharedPreferences
    private val APP_PREFERENCES = "settings"
    private val APP_PREFERENCES_SAVED_CARDS = "savedCards"
    private var savedCards = hashSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_adding)

        val englishTextRegex = Regex(pattern = "[A-Za-z][A-Za-z\\s\\-]*")
        val russianTextRegex = Regex(pattern = "[А-Яа-я][А-Яа-я\\s\\-]*")

        button_for_save.setOnClickListener {
            if (englishTextRegex.containsMatchIn(et_english_text.text) && russianTextRegex.containsMatchIn(et_russian_text.text)) {
                val card = Card(CardsRepository.cards.size, et_english_text.text.toString(), et_russian_text.text.toString())
                if (!CardsRepository.cards.contains(card)) {
                    CardsRepository.cards.add(card)
                    et_english_text.text.clear()
                    et_russian_text.text.clear()
                    Toast.makeText(this, "Новая карточка успешно сохранена", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Ошибка! Такая карточка уже существует", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Ошибка! Текст не введён или введён некорректно", Toast.LENGTH_LONG).show()
            }
        }

        button_back.setOnClickListener {
            val intent = Intent(this, ListOfCardsActivity::class.java)
            startActivity(intent)
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
