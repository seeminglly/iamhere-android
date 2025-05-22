package com.example.iamhere.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.iamhere.MainActivity
import com.example.iamhere.R

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val studentButton = view.findViewById<Button>(R.id.studentButton)
        val professorButton = view.findViewById<Button>(R.id.professorButton)
        val loginButton = view.findViewById<Button>(R.id.loginButton)

        val idEditText = view.findViewById<EditText>(R.id.editTextId)
        val pwEditText = view.findViewById<EditText>(R.id.editTextPassword)

        studentButton.setOnClickListener {
            viewModel.setUserType("학생")
            Toast.makeText(context, "학생 로그인 선택됨", Toast.LENGTH_SHORT).show()
        }

        professorButton.setOnClickListener {
            viewModel.setUserType("교수")
            Toast.makeText(context, "교수 로그인 선택됨", Toast.LENGTH_SHORT).show()
        }

        loginButton.setOnClickListener {
            Log.d("LoginFragment", "로그인 버튼 눌림")
            val id = idEditText.text.toString().trim()
            val pw = pwEditText.text.toString().trim()
            val userType = viewModel.userType.value

            val isValidLogin = when (userType) {
                "학생" -> (id == "20220883" && pw == "1234")
                "교수" -> (id == "prof001" && pw == "abcd")
                else -> false
            }

            if (isValidLogin) {
                Toast.makeText(context, "$userType 로그인 성공", Toast.LENGTH_SHORT).show()

                // 로그인 성공 시 MainActivity로 이동
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)

                // LoginActivity 종료
                requireActivity().finish()
            } else {
                Toast.makeText(context, "아이디 또는 비밀번호가 올바르지 않습니다", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
