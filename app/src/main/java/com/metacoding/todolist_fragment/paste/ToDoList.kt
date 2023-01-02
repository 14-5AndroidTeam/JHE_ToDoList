package com.metacoding.todolist_fragment.paste

import com.google.gson.annotations.SerializedName
import com.metacoding.todolist_fragment.model.Todos

/*todos 리스트 클래스 만들기*/
data class ToDoList(
    @SerializedName("todos")
    var todos:List<Todos>,
    @SerializedName("total_post")
    var total_post:Int,
)
