package ru.kpfu.stud.decepticons.quizcard

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quiz_finish.*

class QuizFinishActivity : AppCompatActivity() {

    private lateinit var pref: SharedPreferences
    private val APP_PREFERENCES = "settings"
    private val APP_PREFERENCES_SAVED_CARDS = "savedCards"
    private var savedCards = hashSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_finish)

        button_back_to_main.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
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
