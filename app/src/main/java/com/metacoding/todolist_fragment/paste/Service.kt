package com.metacoding.todolist_fragment.paste

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Service {
    /**GET, POST 등의 API 정의하기*/

   @GET("/todos") //todolist 불러오는 api - 이 주소에 요청

    //요청하기 위해 사용되는 함수
    //서버로부터 오는 정보의 타입 : ToDoList(todos 의 list)
    //레트로핏에서는 이걸 'Call' 로 또 한 번 감싸줘야 한다. 그냥 문법임.-retrofit2에 해당하는 Call import해주기
    fun getList(): Call<ToDoList>

    /*Post 요청*/
    @POST("/todos")
    fun postForm(
        //네트워크에서는 post요청으로 todo를 넘겨주면 투두 정보가 Body로 들어간다 -> 서버가 데이터 베이스에 등록
        @Body form: Form    //서버에 추가할 할일을 넘겨주기
    ):Call<String>
}