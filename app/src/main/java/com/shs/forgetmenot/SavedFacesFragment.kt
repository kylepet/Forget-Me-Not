package com.shs.forgetmenot

import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.mikepenz.fastadapter.IAdapter
import android.content.Intent
import com.mikepenz.fastadapter.select.getSelectExtension


class SavedFacesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.saved_faces_fragment, container, false)

        var faceList = view.findViewById<RecyclerView>(R.id.savedfaceslist)


        val itemAdapter = ItemAdapter<FaceListItem>()

        val fastAdapter = FastAdapter.with(itemAdapter)




        faceList.layoutManager = LinearLayoutManager(activity)


        //set adapter to the RecyclerView
        faceList.adapter =fastAdapter



        fastAdapter.onClickListener = { view, adapter, item, position ->
            val value = "Hello world"
            val i = Intent(activity, ShowContactInfo::class.java)
            i.putExtra("name", item.name)
            Log.d("yea", "yea")
            startActivity(i)
            false
        }
        var person = FaceListItem()



        //Code to write people to array and save it
        /*

         val preferences = this.activity!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
         //set variables of 'myObject', etc.

         var prefsEditor = preferences.edit()
         var gson = Gson()
         var json = gson.toJson(people)
         prefsEditor.putString("people", json)
         prefsEditor.apply()

         */




        person.withName("Tony").withFamCon("Daddy").withPic(
            BitmapFactory.decodeResource(context?.resources,
                R.drawable.tony))








        itemAdapter.add(person)





        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
