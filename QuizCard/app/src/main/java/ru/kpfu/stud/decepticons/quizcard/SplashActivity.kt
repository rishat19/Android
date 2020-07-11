package ru.kpfu.stud.decepticons.quizcard

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    private lateinit var pref: SharedPreferences
    private val APP_PREFERENCES = "settings"
    private val APP_PREFERENCES_SAVED_CARDS = "savedCards"
    private var savedCards = hashSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //CardsRepository.cards.add(Card(0, "Table", "Стол"))
        //CardsRepository.cards.add(Card(1, "Tea", "Чай"))
        //CardsRepository.cards.add(Card(2, "Cat", "Кошка"))
        //CardsRepository.cards.add(Card(3, "Dog", "Собака"))
        //CardsRepository.cards.add(Card(4, "Ball", "Мяч"))
        pref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        if (pref.contains(APP_PREFERENCES_SAVED_CARDS)) {
            savedCards = (pref.getStringSet(APP_PREFERENCES_SAVED_CARDS, null) as HashSet<String>?)!!
            var id = 0
            savedCards.forEach {
                CardsRepository.cards.add(Card(id, it.split('~')[0], it.split('~')[1]))
                id++
            }
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
