package com.jain.tirthankarvani

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class DisplayListData : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var adapter: TvaniAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_list_data)

        val jsonString = getJsonDataFromAsset(applicationContext, "TV.json")

        val obj = JSONObject(jsonString)
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        val dataobj = obj.getJSONObject(message)
        var displaytype = dataobj.getString("display_type");

        if (displaytype == "list") {
            displayList(dataobj)
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

    private fun displayList(dataobj: JSONObject) {
        var header = dataobj.getString("header");

        findViewById<TextView>(R.id.ListHeader).apply {
            text = header
        }

        var tvDataList = dataobj.getJSONArray("data_list");
        var isImg = dataobj.has("img_name")
        var imgName = ""
        if (isImg){
            imgName = dataobj.getString("img_name");
        }

        var (tvListDetails, isView) = getListDetails(tvDataList, imgName)

        val list = findViewById<ListView>(R.id.contentval)
        adapter = TvaniAdapter(this, tvListDetails)
        list.adapter = adapter

        if (isView) {
            list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedOption = tvListDetails[position]
                val detailIntent = newIntent(this, selectedOption)
                startActivity(detailIntent)

            }
        }

        var editSearch = findViewById<SearchView>(R.id.search);
        editSearch.setOnQueryTextListener(this);
    }

    private fun getListDetails(dataObj: JSONArray, imgName: String): Pair<ArrayList<TVaniData>, Boolean> {

        var tvListDetails: ArrayList<TVaniData> = ArrayList();
        var isView: Boolean = true
        var isViewType: Boolean = true
        var imagemap:HashMap<String, Int> = hashMapOf(
            "vani" to R.mipmap.tvani,
            "chaubeesi" to R.mipmap.tv_logo,
            "pooja" to R.mipmap.pooja_logo
        )

        for (i in 0 until dataObj.length()) {
            var vanidata = TVaniData();
            var jsonobjdata = dataObj.getJSONObject(i)

            vanidata.name = jsonobjdata.getString("name")
            vanidata.desc = jsonobjdata.getString("desc")
            vanidata.img_name = imagemap[imgName]!!
            isView = jsonobjdata.has("view_name")
            if (isView){
                vanidata.view_name = jsonobjdata.getString("view_name")
            }
            isViewType = jsonobjdata.has("view_type")
            if (isViewType){
                vanidata.view_type = jsonobjdata.getString("view_type")
            }
            tvListDetails.add(vanidata)
        }
        return Pair(tvListDetails,isView)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        adapter.filter(newText)
        return false
    }

    companion object {
        const val EXTRA_NAME = "Name"
        const val EXTRA_DESC = "description"

        fun newIntent(context: Context, vaniData: TVaniData): Intent {
            var detailIntent: Intent = if (vaniData.view_type == "list") {
                Intent(context, DisplayListData::class.java)

            } else if (vaniData.view_type == "image") {
                Intent(context, VTCalendar::class.java)
            } else {
                Intent(context, DisplayMessageActivity::class.java)
            }

            detailIntent.putExtra(EXTRA_MESSAGE, vaniData.view_name)
            return detailIntent
        }
    }
}
