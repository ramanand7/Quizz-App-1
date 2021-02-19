package com.example.quizzappp

data class Question (
        val id : Int,
        val question : String,
        val image : Int,
        val optionone:String,
        val optiontew : String,
        val optionthree: String,
        val optionfour:String,
        val correctoption:Int
        )