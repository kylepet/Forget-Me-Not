package com.shs.forgetmenot.ui

import android.content.Context
import android.graphics.BitmapFactory
import android.media.FaceDetector
import android.media.FaceDetector.*
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.shs.forgetmenot.R

import kotlinx.android.synthetic.main.activity_add_person.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.shs.forgetmenot.FaceListItem
import java.util.*
import kotlin.collections.ArrayList
import java.util.Collections.emptyList




class AddPerson : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person)

        val myFab = findViewById<FloatingActionButton>(R.id.savePersonFAB)
        myFab.setOnClickListener {

            val preferences = this.getSharedPreferences("pref", Context.MODE_PRIVATE)
            //set variables of 'myObject', etc.

            var newPerson = FaceListItem()

            var name = findViewById<EditText>(R.id.firstLastName).text.toString()

            newPerson.withName(name)

            newPerson.withFamCon("test")



            var person = FaceListItem()

            person.withName("Tony").withFamCon("Daddy").withPic(
                BitmapFactory.decodeResource(applicationContext.resources,
                    R.drawable.tony))



            this.finish()

        }
    }

}
