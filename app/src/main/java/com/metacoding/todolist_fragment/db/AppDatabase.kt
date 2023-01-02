package com.metacoding.todolist_fragment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(ToDoEntity::class), version = 1)   //1. 데이터베이스와 관련된 모든 Entity 나열

abstract class AppDatabase : RoomDatabase() {    //2. RoomDatabase 를 상속받는 추상 클래스 만들기
    abstract fun getTodoDao(): ToDoDao

    companion object {
        val databaseName = "db_todo"  //데이터 베이스 이름
        var appDatabase: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase?{

            if(appDatabase == null){    //앱 데이터베이스가 null이면 객체 생성
                appDatabase = Room.databaseBuilder(context,
                AppDatabase::class.java,
                databaseName).build()
            }
            return appDatabase  //null이 아니면 기존 객체 반환
        }
    }
}
