package com.shs.forgetmenot

import android.graphics.Insets.add
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*










class MainActivity : AppCompatActivity() {

    val cameraFrag = CameraFragment()
    val faceListFrag = SavedFacesFragment()

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {


            when (item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(faceListFrag, R.id.nav_host_fragment)

                    return true
                }
                R.id.navigation_camera -> {
                    replaceFragment(cameraFrag, R.id.nav_host_fragment)
                    return true
                }
                R.id.navigation_notifications -> {
                    //replaceFragment(cameraFrag, R.id.nav_host_fragment)
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
