package com.example.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myData: PremiumModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun display(){
            if(myData.premiumAmount != 0.00)
                textViewPremium.text ="Insurance Premium:RM  "+ myData.premiumAmount.toString()
        }

      fun getPremium(): Double{
           return when(spinnerAge.selectedItemPosition){
               0 -> 60.00
               1 -> 70.00 +
               (if(radioButtonMale.isChecked) 50.00 else 0.0) + (if(checkBoxSmoker.isChecked)100.00 else 0.0)
               2 -> 90.00 +
                       (if(radioButtonMale.isChecked) 100.00 else 0.0) +
                       (if(checkBoxSmoker.isChecked)150.00 else 0.0)
               3 -> 120.00 +
                       (if(radioButtonMale.isChecked) 150.00 else 0.0) +
                       (if(checkBoxSmoker.isChecked)200.00 else 0.0)
               4 -> 150.00 +
                       (if(radioButtonMale.isChecked) 200.00 else 0.0) +
                       (if(checkBoxSmoker.isChecked)250.00 else 0.0)
               else -> 150.00 +
                       (if(radioButtonMale.isChecked) 200.00 else 0.0) +
                       (if(checkBoxSmoker.isChecked)300.00 else 0.0)
           }
      }

        myData = ViewModelProviders.of(this).get(PremiumModel::class.java)
        display()

        buttonCalculate.setOnClickListener(){
            myData.premiumAmount = getPremium()
           display()


        }

        buttonReset.setOnClickListener(){
            spinnerAge.setSelection(0)
            radioGroupGender.clearCheck()
            checkBoxSmoker.setChecked(false)
            textViewPremium.text = "Insurance Premium : "
        }
    }
}
