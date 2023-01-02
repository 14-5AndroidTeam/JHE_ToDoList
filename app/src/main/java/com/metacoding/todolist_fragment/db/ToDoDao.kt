package com.metacoding.todolist_fragment.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

//데이터 접근 객체에서 데이터로 무엇을 할 것인지 정해주기

@Dao
interface ToDoDao {

    //ToDoEntity의 모든 데이터를 불러오는 쿼리 함수
    @Query("SELECT * FROM ToDoEntity")
    fun getAll(): List<ToDoEntity>

    //ToDoEntity 객체를 테이블에 삽입하는 함수
    @Insert
    fun insertTodo(todo: ToDoEntity)

    //특정 ToDoEntity 객체를 테이블에서 삭제하는 함수
    @Delete
    fun deleteToDo(todo: ToDoEntity)
}