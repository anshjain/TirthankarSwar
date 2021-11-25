package com.jain.tirthankarvani

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView

class NavkarMantra : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navkar_mantra)
        var switch = findViewById<Switch>(R.id.switch1)

        findViewById<TextView>(R.id.nmheader).text = "Namokaar Mantra"
        findViewById<TextView>(R.id.textView1).text = "Namo Ari-han-ta-nam"
        findViewById<TextView>(R.id.textView2).text = "Namo Siddh-a-nam"
        findViewById<TextView>(R.id.textView3).text = "Namo Ayariyanam"
        findViewById<TextView>(R.id.textView4).text = "Namo Uva-jjha-ya-nam"
        findViewById<TextView>(R.id.textView5).text = "Namo Loye Sav-va Sahu-nam !!"
        findViewById<TextView>(R.id.textView6).text =
            "Eso Panch Namo-Karo, Sav-va pava-ppana-sano \n Mangal-a-nam cha savve-sim, Padh-amama hava-yi Man-galam !!"

        switch.setOnCheckedChangeListener { _, isChecked ->
            if (! isChecked) {
                findViewById<TextView>(R.id.nmheader).text = "Namokaar Mantra"
                findViewById<TextView>(R.id.textView1).text = "Namo Ari-han-ta-nam"
                findViewById<TextView>(R.id.textView2).text = "Namo Siddh-a-nam"
                findViewById<TextView>(R.id.textView3).text = "Namo Ayariyanam"
                findViewById<TextView>(R.id.textView4).text = "Namo Uva-jjha-ya-nam"
                findViewById<TextView>(R.id.textView5).text = "Namo Loye Sav-va Sahu-nam !!"
                findViewById<TextView>(R.id.textView6).text =
                    "Eso Panch Namo-Karo, Sav-va pava-ppana-sano \n Mangal-a-nam cha savve-sim, Padh-amama hava-yi Man-galam !!"
            } else {
                findViewById<TextView>(R.id.nmheader).text = "नवकार मंत्र"
                findViewById<TextView>(R.id.textView1).text = "णमो अरिहंताणं"
                findViewById<TextView>(R.id.textView2).text = "णमो सिद्धाणं"
                findViewById<TextView>(R.id.textView3).text = "णमो आयरियाणं"
                findViewById<TextView>(R.id.textView4).text = "णमो उवज्झायाणं"
                findViewById<TextView>(R.id.textView5).text = "णमो लोए सव्व साहूणं !!"
                findViewById<TextView>(R.id.textView6).text =
                    "एसो पंच णमोक्कारो, सव्व पावप्पणासणो \n मंगला णं च सव्वेसिं, पढमं हवई मंगलं !!"

            }
        }
    }
}
