package com.metacoding.todolist_fragment

import android.app.DatePickerDialog
import com.metacoding.todolist_fragment.paste.Event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.metacoding.todolist_fragment.ViewModel.FormModel
import com.metacoding.todolist_fragment.databinding.AddTodoFragmentBinding
import androidx.lifecycle.Observer
import java.util.*


class FragmentAddToDo : Fragment() {

    //뷰 바인딩 사용
    private lateinit var binding: AddTodoFragmentBinding

    /**LiveData 변수 생성*/
    private var _cancel = MutableLiveData<Event<Boolean>>()
    val cancel: LiveData<Event<Boolean>> = _cancel

    // on below line creating a variable. - 날짜
    lateinit var dateEdt: EditText


    //2. fragment에 해당하는 UI도 만들어줘야 한다.

    //뷰를 그리고, 그 뷰를 반환하는 onCreateView를 override해서 원하는 xml을 넣어주고 뷰를 리턴한다.
    override fun onCreateView(
        inflater: LayoutInflater,   //우리가 작성한 xml 을 올려서 사용자의 눈에 보이게 해준다.
        container: ViewGroup?,      //프래그먼트는 액티비티에 종속돼 있다. 이 컨테이너로 들어온 걸 부모로 삼아 자식뷰로 들어가게 된다.
        savedInstanceState: Bundle?
    ): View? {

        //xml을 view로 만드는 과정 (어떤 xml파일을 view로 만들지, 부모뷰는 뭘로 할지, 루트 뷰에 즉시 붙일지 말지
        //그 만든 뷰를 리턴한다.
        binding = AddTodoFragmentBinding.inflate(inflater, container, false) //바인딩 클래스 객체 생성
        val view = binding.root     // 바인딩 객체의 root 뷰 참조

        //database
//        db= AppDatabase.getInstance(mainActivity)!!
//        todoDao = db.getTodoDao()

        return view     // 생성한 뷰 설정
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        /*뷰모델 만들기*/
        var viewmodel = FormModel()

        //확인 버튼이 눌린 경우
        binding.addTodoBtn.setOnClickListener {
            Log.d("testt", "만들기 버튼 눌림")


            val todoTitle = binding.editTodo.text.toString() //할 일의 제목
            val todoDeadline =
                binding.editDeadline.text.toString()  //할일의 마감기한  *****이 부분 수정 필요할 수도...

            /*Toast*/
            val text = "Kotlin Toast in Fragment!"
            val duration = Toast.LENGTH_SHORT
            val applicationContext = activity?.applicationContext
            val toast = Toast.makeText(applicationContext, text, duration)

            /** 토스트 띄워주기 **/
            if (todoTitle.isBlank() || todoDeadline.isBlank()) {
                Toast.makeText(
                    requireContext(), "모든 항목을 채워주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                //postForm에 할 일과 마감기한을 넘겨준다.
                viewmodel.postForm(
                    binding.editTodo.text.toString(),
                    binding.editDeadline.text.toString()
                )
            }

            /**뷰모델에게 할일,기한 데이터를 뽑아서 전달한다*/
            /**관찰하고 있는 뷰모델의 LiveData의 Observer*/
            viewmodel.submit.observe(viewLifecycleOwner, Observer {
                /**서버로 보낸 제출요청에 대한 응답이 성공인 것으로 확인됨. 따라서 메인화면으로 이동*/
                /**밑에 "새로운 TodoList항목이 성공적으로 추가되었습니다 토스트or 다이얼로그 띄우기"*/
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.rootView, FragmentRecyclerView())
                    .commit()
            })

        }

        //취소 버튼이 눌린 경우
        binding.cancelTodoBtn.setOnClickListener {

            //리사이클러뷰 프래그먼트로 변경해주기
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.rootView, FragmentRecyclerView())
                .commit()
        }

        // on below line we are adding
        // click listener for our edit text.
        binding.editDeadline.setOnClickListener {

            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = ("" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString())
                    binding.editDeadline.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }
    }
}
