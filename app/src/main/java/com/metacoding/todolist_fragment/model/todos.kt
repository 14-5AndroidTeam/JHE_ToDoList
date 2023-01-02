package com.metacoding.todolist_fragment.model

import com.google.gson.annotations.SerializedName

/*
    Serializing
    - 객체를 담는 과정

 */

data class Todos(
    @SerializedName("_id")
    var _id:String,
    @SerializedName("id")
    var id:Int,
    @SerializedName("content")
    var content:String,
    @SerializedName("deadline")
    var deadline:String
)