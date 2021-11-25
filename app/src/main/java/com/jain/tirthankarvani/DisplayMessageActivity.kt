package com.jain.tirthankarvani

import android.content.Context
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Switch
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import org.json.JSONObject
import java.io.IOException


class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val jsonString = getJsonDataFromAsset(applicationContext, "TV.json")
        var switch = findViewById<Switch>(R.id.switch2)

        val obj = JSONObject(jsonString)
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        val dataobj = obj.getJSONObject(message)
        var lookupstring = dataobj.getBoolean("look_string");

        displayDetails(dataobj, false, lookupstring)
        switch.visibility = VISIBLE;

        if (!lookupstring)
            switch.visibility = GONE;

        switch.setOnCheckedChangeListener { _, isChecked ->
            displayDetails(dataobj, isChecked, lookupstring)
        }

    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun displayDetails(dataobj: JSONObject, isChecked: Boolean,  lookupstring: Boolean) {

        var dataDetail = dataobj.getString("data_detail");
        var header = dataobj.getString("header");
        if (isChecked && lookupstring) {
            header = dataobj.getString("header_change");
        }


        findViewById<TextView>(R.id.Header).apply {
            text = header
        }
        var data: CharSequence
        @StringRes val resId =
            resources.getIdentifier(dataDetail, "string", packageName)
        data = getString(resId)
        if (lookupstring) {
            if (isChecked) {
                var fullname = dataDetail + "_org"
                @StringRes val resId =
                    resources.getIdentifier(fullname, "string", packageName)
                data = getString(resId)
            }
        }
        findViewById<TextView>(R.id.contentdetail).text = HtmlCompat.fromHtml(
            data,
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

    }

}

