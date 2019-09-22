package com.shs.forgetmenot

import android.os.Bundle
import android.os.TestLooperManager
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_show_contact_info.*

class ShowContactInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_contact_info)

        var showName = findViewById<TextView>(R.id.firstLastNameLayout)
        var showAge = findViewById<TextView>(R.id.AgeLayout)
        var showFame = findViewById<TextView>(R.id.familialRelationLayout)

        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("name")
            showName.text = value
        }
    }
}
