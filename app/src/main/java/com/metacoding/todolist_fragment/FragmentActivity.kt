package com.metacoding.todolist_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager

class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        //-------------------------------------------------
        //1. Fragment ToDoList 만들기기

       //1. 프래그먼트를 붙이거나 뗄 때에는 프래그먼트 매니저를 사용해서 한다.
        //프래그먼트 매니저 갖고 오기
        val fragmentManager = supportFragmentManager

        //붙였다 떼었다 할 프래그먼트 객체 만들어주기
        val fragmentToDoList = FragmentRecyclerView()

        //Transaction(작업의 단위) 만들어주기
        val transaction = fragmentManager.beginTransaction()    //작업 시작
        transaction.replace(R.id.rootView, fragmentToDoList)    //투두리스트 프래그먼트를 메인 액티비티에 붙여주기
        transaction.commit()    //작업의 끝. 마침표 찍고 여기까지 작업해줘

    }

}