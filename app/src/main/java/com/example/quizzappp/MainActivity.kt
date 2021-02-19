package com.example.quizzappp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var buttonStart:Button
    lateinit var et_name :EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    // set the status bar color ->    window.statusBarColor = Color.TRANSPARENT
        window.statusBarColor = Color.MAGENTA
        buttonStart = findViewById(R.id.btn_start)
         et_name = findViewById(R.id.et_name)
        buttonStart.setOnClickListener(View.OnClickListener {

            if (et_name.text.toString().isEmpty()){
                 Toast.makeText(this,"please enter your beautiful name ",Toast.LENGTH_SHORT).show()


            }else{
               val intent = Intent(this,QuizzQuestionsActivity::class.java)

                intent.putExtra(Constants.USER_NAME, et_name.text.toString())


              startActivity(intent)
                finish()
            }

        })

    }
}