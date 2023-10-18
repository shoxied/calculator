package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.RoundingMode
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    lateinit var editText: TextView
    lateinit var TextHistory1: TextView
    lateinit var TextHistory2: TextView
    lateinit var TextHistory3: TextView
    lateinit var TextHistory4: TextView
    lateinit var TextHistory5: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        TextHistory1 = findViewById(R.id.TextHistory1)
        TextHistory2 = findViewById(R.id.TextHistory2)
        TextHistory3 = findViewById(R.id.TextHistory3)
        TextHistory4 = findViewById(R.id.TextHistory4)
        TextHistory5 = findViewById(R.id.TextHistory5)
    }

    fun click1(view: View) {
        val str = editText.text.toString()
        if (str == "0") {
            editText.text = "1"
        }
        else{
            editText.append("1")
        }
    }

    fun click2(view: View) {
        val str = editText.text.toString()
        if (str == "0") {
            editText.text = "2"
        }
        else{
            editText.append("2")
        }
    }

    fun click3(view: View) {
        val str = editText.text.toString()
        if (str == "0") {
            editText.text = "3"
        }
        else{
            editText.append("3")
        }
    }

    fun click4(view: View) {
        val str = editText.text.toString()
        if (str == "0") {
            editText.text = "4"
        }
        else{
            editText.append("4")
        }
    }

    fun click5(view: View) {
        val str = editText.text.toString()
        if (str == "0") {
            editText.text = "5"
        }
        else{
            editText.append("5")
        }
    }

    fun click6(view: View) {
        val str = editText.text.toString()
        if (str == "0") {
            editText.text = "6"
        }
        else{
            editText.append("6")
        }
    }

    fun click7(view: View) {
        val str = editText.text.toString()
        if (str == "0") {
            editText.text = "7"
        }
        else{
            editText.append("7")
        }
    }

    fun click8(view: View) {
        val str = editText.text.toString()
        if (str == "0") {
            editText.text = "8"
        }
        else{
            editText.append("8")
        }
    }

    fun click9(view: View) {
        val str = editText.text.toString()
        if (str == "0") {
            editText.text = "9"
        }
        else{
            editText.append("9")
        }
    }

    fun click0(view: View) {
        val str = editText.text.toString()
        if (str != "0"){
            editText.append("0")
        }
    }

    fun clickAC(view: View) {
        TextHistory5.text = ""
        TextHistory4.text = ""
        TextHistory3.text = ""
        TextHistory2.text = ""
        TextHistory1.text = ""
        editText.text = "0"
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
        if (str != "." && str != ")" && str != "(" && str != "/" && str != "*" && str != "-" && str != "+"){
            editText.append(".")
        }
    }

    fun clickDIV(view: View) {
        val str = "${editText.text.last()}"
        if (str != "." && str != "(" && str != "/" && str != "*" && str != "-" && str != "+"){
            editText.append("/")
        }
    }

    fun clickMULT(view: View) {
        val str = "${editText.text.last()}"
        if (str != "." && str != "(" && str != "/" && str != "*" && str != "-" && str != "+"){
            editText.append("*")
        }
    }

    fun clickMINUS(view: View) {
        val str = "${editText.text.last()}"
        if (str != "." && str != "(" && str != "/" && str != "*" && str != "-" && str != "+"){
            editText.append("-")
        }
    }

    fun cliclPLUS(view: View) {
        val str = "${editText.text.last()}"
        if (str != "." && str != "(" && str != "/" && str != "*" && str != "-" && str != "+"){
            editText.append("+")
        }
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
            var result = ExpressionBuilder("${editText.text}").build().evaluate().toBigDecimal()
            if (result.scale() > 4){
                result = result.setScale(4, RoundingMode.HALF_EVEN)
            }
            editText.text = "${result.stripTrailingZeros()}"
            TextHistory5.text = TextHistory4.text
            TextHistory4.text = TextHistory3.text
            TextHistory3.text = TextHistory2.text
            TextHistory2.text = TextHistory1.text
            TextHistory1.text = editText.text
        }
        catch(error:Exception){
            editText.text = "Ошибка"
        }
    }

    fun TextHistory1Click(view: View) {
        if (TextHistory1.text.isNotEmpty()) editText.text = TextHistory1.text
    }

    fun TextHistory2Click(view: View) {
        if (TextHistory2.text.isNotEmpty()) editText.text = TextHistory2.text
    }
    fun TextHistory3Click(view: View) {
        if (TextHistory3.text.isNotEmpty()) editText.text = TextHistory3.text
    }
    fun TextHistory4Click(view: View) {
        if (TextHistory4.text.isNotEmpty()) editText.text = TextHistory4.text
    }
    fun TextHistory5Click(view: View) {
        if (TextHistory5.text.isNotEmpty()) editText.text = TextHistory5.text
    }

}