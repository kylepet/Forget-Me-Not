package com.shs.forgetmenot

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

        person.withName("Tony").withFamCon("Daddy").withPic(
            BitmapFactory.decodeResource(context?.resources,
                R.drawable.tony))


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
