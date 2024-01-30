package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    var historyList: MutableList<String> = mutableListOf()

    lateinit var editText: TextView
    lateinit var buttonDEL: Button
    lateinit var RecyclerViewHistory: RecyclerView

    private lateinit var vm: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        buttonDEL = findViewById(R.id.buttonDEL)
        RecyclerViewHistory = findViewById(R.id.RecyclerViewHistory)

        buttonDEL.setOnLongClickListener {
            editText.text = "0"
            true
        }

        vm = ViewModelProvider(this)[MyViewModel::class.java]
    }

    fun clickNumber (view: View){
        val button: Button = view as Button
        val buttonText: String = button.text.toString()
        val str = "${editText.text}"
        if (str == "0"){
            editText.text = buttonText
        }else{
            editText.append(buttonText)
        }
    }

    fun clickAC(view: View) {
        editText.text = "0"
        historyList.clear()
        RecyclerViewHistory.adapter = HistoryRecyclerAdapter(historyList)
    }

    fun clickDEL(view: View) {
        var str = editText.text
        if (str != "0"){
            editText.text = str.substring(0, str.length - 1)
            str = editText.text.toString()
        }
        if (str.isEmpty()){
            editText.append("0")
        }
    }

    fun clickDOT(view: View) {
        val str = "${editText.text.last()}"
        if (!setOf(".", ")", "(", "/", "*", "-", "+").contains(str)) editText.append(".")
    }

    fun clickOperator (view: View){
        val button: Button = view as Button
        val buttonText: String = button.text.toString()
        val str = "${editText.text.last()}"
        if (!setOf(".", "(", "/", "*", "+", "-").contains(str)) editText.append(buttonText)
    }

    fun clickLeftBR(view: View) {
        val str = "${editText.text.last()}"
        if (str == "0" || str != "."){
            editText.append("(")
        }
    }

    fun clickRightBR(view: View) {
        val str = "${editText.text.last()}"
        if (str != "."){
            editText.append(")")
        }
    }

    fun clickEQ(view: View) {
        try {
            historyList.add("${editText.text}")
            RecyclerViewHistory.adapter = HistoryRecyclerAdapter(historyList)
            vm.expression.value = "${editText.text}"
            vm.getResult()
            var result = vm.getResult().toBigDecimal()
            if (result.scale() > 4) {
                result = result.setScale(4, RoundingMode.HALF_EVEN)
            }
            editText.text = "${result.stripTrailingZeros()}"
        }
        catch (e: SolutionError){
            editText.text = "${e.message}"
            Log.e("Error", "${e.message}")
        }
    }
}