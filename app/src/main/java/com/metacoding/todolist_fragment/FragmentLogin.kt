package com.metacoding.todolist_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentLogin : Fragment() {   //1. 프래그먼트의 역할을 하기 위해서는 Fragment를 상속을 받아야 한다

    //2. fragment에 해당하는 UI도 만들어줘야 한다.

    //뷰를 그리고, 그 뷰를 반환하는 onCreateView를 override해서 원하는 xml을 넣어주고 뷰를 리턴한다.
    override fun onCreateView(
        inflater: LayoutInflater,   //우리가 작성한 xml 을 올려서 사용자의 눈에 보이게 해준다.
        container: ViewGroup?,      //프래그먼트는 액티비티에 종속돼 있다. 이 컨테이너로 들어온 걸 부모로 삼아 자식뷰로 들어가게 된다.
        savedInstanceState: Bundle?
    ): View? {

        //xml을 view로 만드는 과정 (어떤 xml파일을 view로 만들지, 부모뷰는 뭘로 할지, 루트 뷰에 즉시 붙일지 말지
        //그 만든 뷰를 리턴한다.
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

}