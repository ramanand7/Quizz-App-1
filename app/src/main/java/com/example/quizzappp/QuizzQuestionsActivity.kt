package com.example.quizzappp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizzQuestionsActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var buttonsubmit : Button

    private var mCurrentPosition : Int = 1
    private var mQuestionsList : ArrayList<Question>?=null
    private var mSelectedOption : Int = 0
    private var mCorrectAnswer: Int =0

    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_questions)
        mQuestionsList = Constants.getQuestions()
       buttonsubmit = findViewById(R.id.btn_submit)
        mUserName = intent.getStringExtra(Constants.USER_NAME)
     setQuestion()

        findViewById<TextView>(R.id.tv_option_one).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_option_two).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_option_three).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_option_four).setOnClickListener(this)
        buttonsubmit.setOnClickListener(this)

    }


        private fun setQuestion(){

            val question = mQuestionsList!!.get(mCurrentPosition-1)



            defaultoptionview()

            if (mCurrentPosition == mQuestionsList!!.size){
                buttonsubmit.text = "FINISH"
            }
            else{
                buttonsubmit.text="SUBMIT"
            }

            findViewById<ProgressBar>(R.id.progressBar).progress = mCurrentPosition
            findViewById<TextView>(R.id.tv_progress).text = "$mCurrentPosition"+"/"+findViewById<ProgressBar>(R.id.progressBar).max
            findViewById<ImageView>(R.id.iv_image).setImageResource(question.image)

            findViewById<TextView>(R.id.tv_option_one).text = question.optionone
            findViewById<TextView>(R.id.tv_option_two).text = question.optiontew
            findViewById<TextView>(R.id.tv_option_three).text = question.optionthree
            findViewById<TextView>(R.id.tv_option_four).text = question.optionfour





        }

    private fun defaultoptionview(){
        val options =ArrayList<TextView>()
        options.add(0,findViewById<TextView>(R.id.tv_option_one))
        options.add(1,findViewById<TextView>(R.id.tv_option_two))
        options.add(2,findViewById<TextView>(R.id.tv_option_three))
        options.add(3,findViewById<TextView>(R.id.tv_option_four))

        for (option in options ){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface=Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this
            ,R.drawable.default_option_border_bg)
        }

    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.tv_option_one->{
                selectedOptionView(findViewById(R.id.tv_option_one),1)
            }
            R.id.tv_option_two->{
                selectedOptionView(findViewById(R.id.tv_option_two),2)
            }
            R.id.tv_option_three->{
                selectedOptionView(findViewById(R.id.tv_option_three),3)
            }
            R.id.tv_option_four->{
                selectedOptionView(findViewById(R.id.tv_option_four),4)
            }
            R.id.btn_submit->{
                if (mSelectedOption==0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition<=mQuestionsList!!.size->{
                            setQuestion()
                        }else->{



                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, mUserName)
                        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswer)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                        startActivity(intent)
                        finish()
                        }
                    }
                }
                else{
                    val question = mQuestionsList?.get(mCurrentPosition-1)
                    if (question!!.correctoption != mSelectedOption){
                        answerView(mSelectedOption,R.drawable.wrorng_option_border_bg)
                    }else{
                        mCorrectAnswer++
                    }
                    answerView(question.correctoption,R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size){
                        findViewById<Button>(R.id.btn_submit).text = "FINISH"
                    }

                    else{
                        findViewById<Button>(R.id.btn_submit).text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOption = 0

                }
            }


        }


    }

    private fun answerView(answer : Int , drawableView : Int){

        when(answer){
            1->{
                findViewById<TextView>(R.id.tv_option_one).background = ContextCompat.getDrawable(this,drawableView)
            }
            2->{

                findViewById<TextView>(R.id.tv_option_two).background = ContextCompat.getDrawable(this,drawableView)
            }
            3->{
                findViewById<TextView>(R.id.tv_option_three).background = ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                findViewById<TextView>(R.id.tv_option_four).background = ContextCompat.getDrawable(this,drawableView)
            }

        }
    }

    private fun selectedOptionView(tv : TextView , selectedOption : Int){

        defaultoptionview()
        mSelectedOption = selectedOption

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this
                ,R.drawable.selected_option_border)


    }

}
