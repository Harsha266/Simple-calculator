package com.example.simpleguicalculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//number
        tvOne.setOnClickListener{appendOnExpression("1", true) }
        tvTwo.setOnClickListener{appendOnExpression("2", true) }
        tvThree.setOnClickListener{appendOnExpression("3", true) }
        tvFour.setOnClickListener{appendOnExpression("4", true) }
        tvFive.setOnClickListener{appendOnExpression("5", true) }
        tvSix.setOnClickListener{appendOnExpression("6", true) }
        tvSeven.setOnClickListener{appendOnExpression("7", true) }
        tvEight.setOnClickListener{appendOnExpression("8", true) }
        tvNine.setOnClickListener{appendOnExpression("9", true) }
        tvDot.setOnClickListener{appendOnExpression(".", true) }
        tvZero.setOnClickListener{appendOnExpression("0", true) }

        //opertaor

        tvPlus.setOnClickListener { appendOnExpression("+", false) }
        tvMinus.setOnClickListener { appendOnExpression("-", false) }
        tvMultiply.setOnClickListener { appendOnExpression("*", false) }
        tvDivision.setOnClickListener { appendOnExpression("/", false) }
        tvBracket.setOnClickListener { appendOnExpression("(", false) }
        tvEndBracket.setOnClickListener { appendOnExpression(")", false) }

        tvClear.setOnClickListener {
            tvExpression.text=""
            tvResult.text=""
        }

        tvBack.setOnClickListener {
            tvResult.text = ""
            if(tvExpression.text.isEmpty()){

            }else{
                val string = tvExpression.text
                tvExpression.text = string.dropLast(1)
            }
        }
        tvEqual.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result =  expression.evaluate()
                val longResult =  result.toLong()
                if(longResult.toDouble() == result){
                    tvResult.text = longResult.toString()
                }else{
                    tvResult.text = result.toString()
                }

            }catch (e:Exception){
                Toast.makeText(this, "InValid Expression", Toast.LENGTH_SHORT)
            }
        }


    }


    fun appendOnExpression(string: String, canClear: Boolean ){
        if(tvResult.text.isNotEmpty()) {
            tvExpression.text = ""
        }
        if(canClear){
            tvResult.text = ""
            tvExpression.append(string)
        }else{
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text=""
        }
    }
}
