package com.app.chattingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.chattingapp.databinding.ActivitySignupBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = Firebase.auth

        mDbRef = Firebase.database.reference

        binding.signUpBtn.setOnClickListener {
            val name = binding.nameEdit.text.toString().trim()
            val email = binding.emailEdit.text.toString().trim()
            val password = binding.passwordEdit.text.toString().trim()

            signUp(name, email, password)
        }
    }

    private fun signUp(name: String, email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    val intent: Intent = Intent(this@SignUpActivity, MainActivity::class.java)
                    startActivity(intent)
                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                } else {
                    Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    Log.d("SignUp", "Error!")
                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uId: String) {
        mDbRef.child("user").child(uId).setValue(User(name,email,uId))
    }

}