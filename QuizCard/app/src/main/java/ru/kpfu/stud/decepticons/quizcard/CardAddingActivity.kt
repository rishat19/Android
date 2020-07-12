package ru.kpfu.stud.decepticons.quizcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_card_adding.*
import kotlinx.android.synthetic.main.activity_list_of_cards.*
import kotlinx.android.synthetic.main.activity_list_of_cards.button_back

class CardAddingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_adding)

        val englishTextRegex = Regex(pattern = "[A-Za-z][A-Za-z\\s\\-]*")
        val russianTextRegex = Regex(pattern = "[А-Яа-я][А-Яа-я\\s\\-]*")

        button_for_save.setOnClickListener {
            if (englishTextRegex.containsMatchIn(et_english_text.text) && russianTextRegex.containsMatchIn(et_russian_text.text)) {
                CardsRepository.cards.add(Card(CardsRepository.cards.size, et_english_text.text.toString(), et_russian_text.text.toString()))
                et_english_text.text.clear()
                et_russian_text.text.clear()
                Toast.makeText(this, "Новая карточка успешно сохранена", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Ошибка! Текст не введён или введён некорректно", Toast.LENGTH_LONG).show()
            }
        }

        button_back.setOnClickListener {
            val intent = Intent(this, ListOfCardsActivity::class.java)
            startActivity(intent)
        }

    }
}
