package com.jain.tirthankarvani

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_MESSAGE = "com.jain.tirthankarvani.MESSAGE"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun navkar(view: View) {
        val intent = Intent(this, NavkarMantra::class.java).apply {
            putExtra(EXTRA_MESSAGE, "navkar")

        }
        startActivity(intent)
    }

    fun tirthanker(view: View) {
        val intent = Intent(this, DisplayListData::class.java).apply {
            putExtra(EXTRA_MESSAGE, "tirthanker")
        }
        startActivity(intent)
    }

    fun pooja(view: View) {
        val intent = Intent(this, DisplayListData::class.java).apply {
            putExtra(EXTRA_MESSAGE, "pooja")
        }
        startActivity(intent)
    }

    fun stotra(view: View) {
        val intent = Intent(this, DisplayListData::class.java).apply {
            putExtra(EXTRA_MESSAGE, "stotra")
        }
        startActivity(intent)
    }

    fun more(view: View) {
        val intent = Intent(this, DisplayListData::class.java).apply {
            putExtra(EXTRA_MESSAGE, "more")
        }
        startActivity(intent)
    }

}

