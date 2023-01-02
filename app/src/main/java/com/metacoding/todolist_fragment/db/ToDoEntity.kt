package com.metacoding.todolist_fragment.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//데이터 클래스 - 데이터 구조 정의
@Entity
data class ToDoEntity (
    @PrimaryKey(autoGenerate = true) var id: Int? = null,   //기본키(인덱스) - 자동 증가
    @ColumnInfo(name="title") val title: String,            //할 일
    @ColumnInfo(name="deadline") val deadline: String,      //마감기한
)