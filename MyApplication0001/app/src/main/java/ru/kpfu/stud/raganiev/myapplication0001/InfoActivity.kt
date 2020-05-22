package ru.kpfu.stud.raganiev.myapplication0001

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        if (intent != null) {
            imageView.setImageResource(intent.getIntExtra("photoId", 0))
            textView.text = intent.getCharSequenceExtra("email")
            textView2.text = intent.getCharSequenceExtra("name")
            textView3.text = intent.getCharSequenceExtra("surname")
        }
    }

    fun onClickLogout(view: View) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("logout", true)
        }
        startActivity(intent)
        finish()
    }
}
