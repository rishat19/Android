package ru.kpfu.stud.decepticons.quizcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_card_info.*
import kotlinx.android.synthetic.main.activity_card_info.button_back
import kotlinx.android.synthetic.main.activity_list_of_cards.*

class CardInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_info)

        val id = intent?.extras?.getInt("ID", -1) ?: 0
        val card = CardsRepository.cards[id]
        showEnglishVersion(card)
        var languageFlag = false

        button_for_translate.setOnClickListener {
            languageFlag = !languageFlag
            if (languageFlag) {
                showRussianVersion(card)
            } else {
                showEnglishVersion(card)
            }
        }

        button_back.setOnClickListener {
            val intent = Intent(this, ListOfCardsActivity::class.java)
            startActivity(intent)
        }

    }

    private fun showEnglishVersion(card: Card) {
        tv_language.setText(R.string.english)
        flag.setImageResource(R.drawable.icon_great_britain)
        tv_card_text.text = card.engText
        button_for_translate.text = "Показать перевод"
    }

    private fun showRussianVersion(card: Card) {
        tv_language.setText(R.string.russian)
        flag.setImageResource(R.drawable.icon_russia)
        tv_card_text.text = card.ruText
        button_for_translate.text = "Показать оригинал"
    }

}
