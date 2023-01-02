package com.metacoding.todolist_fragment.paste

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    /**데이터베이스에 접근할 수 있는 곳*/
    private val Dr = DataResource   //데이터 리소스 obj를 Dr에 저장

    //서버로부터 ToDoList 를 받아오는 함수
    fun getList(param: GetDataCallback<ToDoList>) {

        //service.getList를 호출하면 baseurl/todos 로 요청을 보내게 되고,
        //ToDoList를 받는다.
        val call = Dr.service.getList() //api 호출

        /**비동기 통신: enqueue*/
        //실행하려면 enqueue()안에 콜백을 만들어서 넣어줘야 한다.
        //작업을 하는데 있어 줄을 서겠다는 의미이다.
        call.enqueue(object : Callback<ToDoList>{

            //서버로부터 오는 응답을 '콜백'이라고 한다.
            //Caallback<> 안에는 우리가 요청했던 데이터의 타입을 넣어주면 된다.
            //콜백에서 implement해야하는 함수 : onResponse(서버가 우리에게 주는 응답),onFailure
            //서버로부터 응답이 왔을 때 내가 해주고 싶은 작업을 onResponse에서 해주면 된다.
            override fun onResponse(call: Call<ToDoList>, response: Response<ToDoList>) {
                /**응답 성공*/
                Log.i("response", response.body().toString()) //데이터 확인
                /**Viewmodel의 인터페이스 구현부에 데이터 넘겨주기*/
                param.onSuccess(response.body())
            }

            //내가 서버에게 요청을 했는데 서버가 응답을 못한 경우 하고싶은 작업을 작성
           override fun onFailure(call: Call<ToDoList>, t: Throwable) {
                /**응답 실패*/
                param.onFailure(t)
            }
        })
    }

    fun postForm(form: Form){
        val call = Dr.service.postForm(form) //api 호출

        call.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                /**응답 성공*/
                Log.i("post_Success", response.body().toString()) //데이터 확인
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                /**요청 실패*/
                Log.i("post_Failure", t.toString()) //데이터 확인
            }
        })
    }

    /**서버응답 결과를 뷰모델로 넘겨줄 수 있는 인터페이스*/
    interface GetDataCallback<T>{
        fun onSuccess(data:T?)
        fun onFailure(throwable: Throwable)
    }
}