package ru.kpfu.stud.decepticons.quizcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_list_of_cards.*

class ListOfCardsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_cards)

        button_for_add.setOnClickListener {
            val intent = Intent(this, CardAddingActivity::class.java)
            startActivity(intent)
        }

        button_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        rv_cards.adapter = CardAdapter(CardsRepository.cards) { id ->
            val intent = Intent(this, CardInfoActivity::class.java)
            intent.putExtra("ID", id)
            startActivity(intent)
        }
    }
}
