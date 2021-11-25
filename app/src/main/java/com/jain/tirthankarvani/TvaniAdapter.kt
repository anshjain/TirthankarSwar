package com.jain.tirthankarvani

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class TvaniAdapter(private val context: Context,
                   private val dataSource: ArrayList<TVaniData>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private var tvDataList: ArrayList<TVaniData> = ArrayList<TVaniData>(dataSource)


    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        val holder: ViewHolder

        if (convertView == null) {

            view = inflater.inflate(R.layout.list_tvani, parent, false)

            holder = ViewHolder()
            holder.thumbnailImageView = view.findViewById(R.id.list_tvani_thumbnail) as ImageView
            holder.headerText = view.findViewById(R.id.list_tvani_title) as TextView
            holder.descView = view.findViewById(R.id.list_tvani_desc) as TextView
            view.tag = holder

        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }
        val headerText = holder.headerText
        val descView = holder.descView
        val thumbnailImageView = holder.thumbnailImageView


        val tvdata = getItem(position) as TVaniData

        headerText.text = tvdata.name
        descView.text = tvdata.desc
        thumbnailImageView.setImageResource(tvdata.img_name);

        return view
    }

    private class ViewHolder {
        lateinit var headerText: TextView
        lateinit var descView: TextView
        lateinit var thumbnailImageView: ImageView
    }

    fun filter(charText: String) {
        var charText = charText
        charText = charText.toLowerCase(Locale.getDefault())
        dataSource.clear()
        if (charText.isEmpty()) {
            dataSource.addAll(tvDataList)
        } else {
            for (wp in tvDataList) {
                if (wp.getTVName()!!.toLowerCase(Locale.getDefault()).contains(charText)) {
                    dataSource.add(wp)
                }
            }
        }
        notifyDataSetChanged()
    }

}

