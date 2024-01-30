package com.example.calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    val expression: MutableLiveData<String> = MutableLiveData()

    fun getResult(): Double{
        return Solution().solve("${expression.value}")
    }
}