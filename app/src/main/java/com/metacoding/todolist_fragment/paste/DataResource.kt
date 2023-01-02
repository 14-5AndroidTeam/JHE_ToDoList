package com.metacoding.todolist_fragment.paste

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

/*Retrofit 객체 생성*/


object DataResource {

    //어디에 요청할 것인지
    val baseUrl = "https://todolist-369816.df.r.appspot.com"

    var gson = GsonBuilder().setLenient().create()

    //.addConverterFactory : 사람이 알아들을 수 없는 데이터를 JSON 으로 convert
    //JSON 도 코틀린에는 존재하지 않으므로 코틀린 클래스의 오브젝트로 바꿔줘야 한다
    //이걸 해주는게 Gson 이다.
    //GSON -> 읽을 수 없는 데이터를 코틀린 객체로 바꿔준다.

    /** Retrofit 객체생성 **/
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    /*retrofit service 객체 만들기*/
    // 서비스를 갖고 올 때에는 아래와 같이 적는다.
    // retrofit.create(서비스 클래스 이름::class.java)
    val service = retrofit.create(Service::class.java)
}