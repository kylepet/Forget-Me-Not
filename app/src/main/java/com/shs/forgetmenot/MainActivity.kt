package com.shs.forgetmenot

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shs.forgetmenot.ui.AddPerson


class MainActivity : AppCompatActivity() {

    val cameraFrag = CameraFragment()
    val faceListFrag = SavedFacesFragment()

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {


            when (item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(SavedFacesFragment(), R.id.nav_host_fragment)

                    return true
                }
                R.id.navigation_camera -> {
                    replaceFragment(cameraFrag, R.id.nav_host_fragment)
                    return true
                }
                R.id.navigation_settings -> {
                    startActivity(Intent(applicationContext, AddPerson::class.java))
                    return true
                }
            }
            return false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)


        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        navView.selectedItemId = R.id.navigation_camera

        replaceFragment(cameraFrag, R.id.nav_host_fragment)

    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()


        fragmentTransaction.func()
        fragmentTransaction.commit()
    }


    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){

        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }



    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction{replace(frameId, fragment)}
    }
}
