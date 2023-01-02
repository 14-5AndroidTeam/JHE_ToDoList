package com.metacoding.todolist_fragment.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.metacoding.todolist_fragment.paste.Event
import com.metacoding.todolist_fragment.paste.Form
import com.metacoding.todolist_fragment.paste.Repository

class FormModel:androidx.lifecycle.ViewModel(){

    val repo = Repository()

    private var _submit = MutableLiveData<Event<Boolean>>()
    val submit: LiveData<Event<Boolean>> = _submit

    fun postForm(content: String, deadline: String) {
        var form = Form(
            content = content,  //할 일
            deadline = deadline //마감기한
        )
        repo.postForm(form) //레포지토리의 postForm을 호출하면서 form객체를 넘겨준다
        /**콜백함수에서 받은 반환 값을 LiveData 형식의 변수에 할당*/
        _submit.postValue(Event(true))
    }
}