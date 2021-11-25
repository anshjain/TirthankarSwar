package com.jain.tirthankarvani

import kotlin.properties.Delegates

public class TVaniData {
    lateinit var name:String
    lateinit var desc:String
    var img_name by Delegates.notNull<Int>()
    lateinit var view_name:String
    lateinit var view_type:String

    constructor(name: String, desc: String, img_name: Int, view_name: String, view_type: String) {
        this.name = name
        this.desc = desc
        this.img_name = img_name
        this.view_name = view_name
        this.view_type = view_type
    }

    constructor()

    fun getTVName(): String? {
        return this.name
    }
}