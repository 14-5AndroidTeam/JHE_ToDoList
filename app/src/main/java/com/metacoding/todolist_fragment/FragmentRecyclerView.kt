package com.metacoding.todolist_fragment

import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.UI.ViewModel.ListModel
import com.metacoding.todolist_fragment.databinding.TodoListFragmentBinding
import com.metacoding.todolist_fragment.db.AppDatabase
import com.metacoding.todolist_fragment.db.ToDoDao
import com.metacoding.todolist_fragment.db.ToDoEntity
import com.metacoding.todolist_fragment.model.Todo
import com.metacoding.todolist_fragment.model.TodoAdapter
import com.metacoding.todolist_fragment.model.TodoViewModel
import com.metacoding.todolist_fragment.model.Todos


class FragmentRecyclerView : Fragment() {

    //뷰 바인딩 사용
    private lateinit var binding: TodoListFragmentBinding


    val mDatas = mutableListOf<Todos>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    //2. fragment에 해당하는 UI도 만들어줘야 한다.

    //뷰를 그리고, 그 뷰를 반환하는 onCreateView를 override해서 원하는 xml을 넣어주고 뷰를 리턴한다.
    override fun onCreateView(
        inflater: LayoutInflater,   //우리가 작성한 xml 을 올려서 사용자의 눈에 보이게 해준다.
        container: ViewGroup?,      //프래그먼트는 액티비티에 종속돼 있다. 이 컨테이너로 들어온 걸 부모로 삼아 자식뷰로 들어가게 된다.
        savedInstanceState: Bundle?
    ): View? {

        //xml을 view로 만드는 과정 (어떤 xml파일을 view로 만들지, 부모뷰는 뭘로 할지, 루트 뷰에 즉시 붙일지 말지
        //그 만든 뷰를 리턴한다.

//        db = AppDatabase.getInstance()!!
//        todoDao = db.getTodoDao()
//        getAllTodoList()

        binding = TodoListFragmentBinding.inflate(inflater, container, false) //바인딩 클래스 객체 생성
        val view = binding.root     // 바인딩 객체의 root 뷰 참조

        return view     // 생성한 뷰 설정
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //initializelist()    //data값 초기화
        //initRecycler()  //recyclerView 데이터 바인딩

        /**어댑터 등록하기*/
        var adapter = TodoRecyclerViewAdapter()
        binding.recyclerView.adapter = adapter

        /**뷰모델 등록하기*/
        var viewmodel = ListModel()
        viewmodel.getList()

        /*버튼 클릭 시 다음페이지 화면전환하기*/
        /*write 버튼*/
        binding.write.setOnClickListener {
            Log.d("testt", "추가하기 버튼 눌림")

            //할 일 추가 프래그먼트로 변경해주기
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.rootView, FragmentAddToDo())
                .commit()
        }

        /**뷰모델 옵저버*/
        viewmodel.setList.observe(viewLifecycleOwner, Observer {
            adapter.setList(it.peekContent().todos)
            adapter.notifyItemRangeChanged(0, it.peekContent().total_post)
        })

    }

//    fun initTodoRecyclerView(){
//        val adapter=TodoRecyclerViewAdapter() //어댑터 객체 만듦
//        adapter.datalist = mDatas //데이터 넣어줌
//        binding.recyclerView.adapter=adapter //리사이클러뷰에 어댑터 연결
//    }

//    fun initializelist() { //임의로 데이터 넣어서 만들어봄
//        with(mDatas) {
//            add(Todos("", 1, "코틀린 공부하기", "2022-12-26"))
//            add(Todos("", 2, "오픈소스 공부하기", "2022-12-28"))
//            add(Todos("", 3, "프로젝트", "2022-12-29"))
//            add(Todos("", 4, "레트로핏 공부하기", "2022-12-30"))
//            add(Todos("", 5, "요리하기", "2022-12-31"))
//            add(Todos("", 5, "놀기", "2022-01-01"))
//            add(Todos("", 5, "happy", "2022-01-01"))
//        }
//    }

//    private fun initRecycler() {
//        val adapter = TodoRecyclerViewAdapter()
//        binding.recyclerView.adapter = adapter
//
//        mDatas.apply {
//            add(Todos(_id = "123", id = 1, content = "play", deadline = "10"))
//            adapter.datalist = mDatas
//            adapter.notifyDataSetChanged()
//        }
//    }
}