package com.example.iamhere.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
            val id = idEditText.text.toString()
            val pw = pwEditText.text.toString()
            val userType = viewModel.userType.value

            // 로그인 로직 (예시용 Toast)
            Toast.makeText(context, "$userType 로그인 시도: ID=$id", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
