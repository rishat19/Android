package ru.kpfu.stud.raganiev.myapplication0001

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var pref: SharedPreferences
    private val APP_PREFERENCES = "mysettings"
    private val APP_PREFERENCES_EMAIL = "email"
    private val APP_PREFERENCES_IS_LOGGED_IN = "isLoggedIn"
    private var email: String? = ""
    private var isLoggedIn = false
    private val users = HashSet<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        //There is some ugly hardcode
        users.add(User("ist.kazan@gmail.com", "F3renet5", "Alexander", "Ferenets", R.drawable.ferenets))
        users.add(User("ma@it.kfu.ru", "Abram5ky", "Michael", "Abramsky", R.drawable.abramsky))
        if (pref.contains(APP_PREFERENCES_IS_LOGGED_IN)) {
            isLoggedIn = pref.getBoolean(APP_PREFERENCES_IS_LOGGED_IN, false)
            email = pref.getString(APP_PREFERENCES_EMAIL, "")
        }
        if (intent.getBooleanExtra("logout", false)) {
            isLoggedIn = false
        }
        if (isLoggedIn) {
            users.forEach {
                if (email.toString().equals(it.getEmail())) {
                    val intent = Intent(this, InfoActivity::class.java).apply {
                        putExtra("email", it.getEmail())
                        putExtra("name", it.getName())
                        putExtra("surname", it.getSurname())
                        putExtra("photoId", it.getPhotoId())
                    }
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    fun onClickLogin(view: View) {
        if (username.text.isNotEmpty() && password.text.isNotEmpty()) {
            val emailRegex = Regex(pattern = "^(?![\\-.])[-0-9A-z.]+(?<![\\-.])@((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$")
            val passwordRegex = Regex(pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}$")
            if (emailRegex.containsMatchIn(username.text) && passwordRegex.containsMatchIn(password.text)) {
                var flag = true
                users.forEach {
                    if (username.text.toString().equals(it.getEmail()) && password.text.toString().equals(it.getPassword())) {
                        flag = false
                        val intent = Intent(this, InfoActivity::class.java).apply {
                            putExtra("email", it.getEmail())
                            putExtra("name", it.getName())
                            putExtra("surname", it.getSurname())
                            putExtra("photoId", it.getPhotoId())
                        }
                        isLoggedIn = true
                        email = it.getEmail()
                        startActivity(intent)
                        finish()
                    }
                }
                if (flag) {
                    Toast.makeText(this, "Error: user does not exist.", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Error: invalid input format.", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Error: fields are empty.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onPause() {
        super.onPause()
        val editor = pref.edit()
        editor.putBoolean(APP_PREFERENCES_IS_LOGGED_IN, isLoggedIn)
        editor.putString(APP_PREFERENCES_EMAIL, email)
        editor.apply()
    }

    override fun onStop() {
        super.onStop()
        val editor = pref.edit()
        editor.putBoolean(APP_PREFERENCES_IS_LOGGED_IN, isLoggedIn)
        editor.putString(APP_PREFERENCES_EMAIL, email)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        if (pref.contains(APP_PREFERENCES_IS_LOGGED_IN)) {
            isLoggedIn = pref.getBoolean(APP_PREFERENCES_IS_LOGGED_IN, false)
            email = pref.getString(APP_PREFERENCES_EMAIL, "")
        }
        if (intent.getBooleanExtra("logout", false)) {
            isLoggedIn = false
        }
        if (isLoggedIn) {
            users.forEach {
                if (email.toString().equals(it.getEmail())) {
                    val intent = Intent(this, InfoActivity::class.java).apply {
                        putExtra("email", it.getEmail())
                        putExtra("name", it.getName())
                        putExtra("surname", it.getSurname())
                        putExtra("photoId", it.getPhotoId())
                    }
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

}
