package com.shs.forgetmenot.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.shs.forgetmenot.R

import kotlinx.android.synthetic.main.activity_add_person.*

class AddPerson : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person)
    }
}
