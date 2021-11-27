package com.jain.tirthankarvani

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.chrisbanes.photoview.PhotoView
import java.util.*


class VTCalendar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vt_calendar)
        findViewById<TextView>(R.id.textView).text = "Jain Calendar"

        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)

        val filename = "vt_" + month + "_" + year
        val id = resources.getIdentifier(filename, "drawable", packageName);
        val image = findViewById<View>(R.id.imageView5) as PhotoView
        if (id != 0){
            image.setImageResource(id)
        } else {
            image.setImageResource(R.mipmap.tirthankar_vani)
        }
    }
}
