package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
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
    lateinit var buttonDEL: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        TextHistory1 = findViewById(R.id.TextHistory1)
        TextHistory2 = findViewById(R.id.TextHistory2)
        TextHistory3 = findViewById(R.id.TextHistory3)
        TextHistory4 = findViewById(R.id.TextHistory4)
        TextHistory5 = findViewById(R.id.TextHistory5)
        buttonDEL = findViewById(R.id.buttonDEL)

        buttonDEL.setOnLongClickListener {
            editText.text = "0"
            true
        }
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
        if (!setOf(".", ")", "(", "/", "*", "-", "+").contains(str)) editText.append(".")
    }

    fun clickDIV(view: View) {
        val str = "${editText.text.last()}"
        if (!setOf(".", "(", "/", "*", "+", "-").contains(str)) editText.append("/")
    }

    fun clickMULT(view: View) {
        val str = "${editText.text.last()}"
        if (!setOf(".", "(", "/", "*", "+", "-").contains(str)) editText.append("*")
    }

    fun clickMINUS(view: View) {
        val str = "${editText.text.last()}"
        if (!setOf(".", "(", "/", "*", "+", "-").contains(str)) editText.append("-")
    }

    fun cliclPLUS(view: View) {
        val str = "${editText.text.last()}"
        if (!setOf(".", "(", "/", "*", "+", "-").contains(str)) editText.append("+")
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
        TextHistory5.text = TextHistory4.text
        TextHistory4.text = TextHistory3.text
        TextHistory3.text = TextHistory2.text
        TextHistory2.text = TextHistory1.text
        TextHistory1.text = editText.text
        try {
            var result = ExpressionBuilder("${editText.text}").build().evaluate().toBigDecimal()
            if (result.scale() > 4){
                result = result.setScale(4, RoundingMode.HALF_EVEN)
            }
            editText.text = "${result.stripTrailingZeros()}"

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