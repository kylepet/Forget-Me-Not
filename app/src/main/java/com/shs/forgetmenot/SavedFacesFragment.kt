package com.shs.forgetmenot

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


class SavedFacesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.saved_faces_fragment, container, false)

        var faceList = view.findViewById<RecyclerView>(R.id.savedfaceslist)



        //create the ItemAdapter holding your Items
        val itemAdapter = ItemAdapter<FaceListItem>()
        //create the managing FastAdapter, by passing in the itemAdapter
        val fastAdapter = FastAdapter.with(itemAdapter)

        faceList.layoutManager = LinearLayoutManager(activity)
        //set adapter to the RecyclerView
        faceList.adapter = fastAdapter

        var person = FaceListItem()

        var people = ArrayList<FaceListItem>()

        val preferences = this.activity!!.getSharedPreferences("pref", Context.MODE_PRIVATE)

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

        //Retrieve saved values
        val gson = Gson()
        val json = preferences.getString("people", "")
        var temp = gson.fromJson<Any>(json, FaceListItem::class.java)



        if (temp != null) {
            people = temp as ArrayList<FaceListItem>
        }



        person.withName("Tony").withFamCon("Daddy").withPic(
            BitmapFactory.decodeResource(context?.resources,
                R.drawable.tony))


        //configure our fastAdapter
        fastAdapter.onClickListener = { v: View?, _: IAdapter<FaceListItem>, item: FaceListItem, _: Int ->
            v?.let {
                Toast.makeText(v.context, item.name, Toast.LENGTH_LONG).show()
            }
            false
        }

         //set the items to your ItemAdapter
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)
        itemAdapter.add(person)







        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
