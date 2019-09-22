package com.shs.forgetmenot

import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

open class FaceListItem : AbstractItem<FaceListItem.ViewHolder>() {
    var name: String? = null
    var description: String? = null

    /** defines the type defining this item. must be unique. preferably an id */
    override val type: Int
        get() = R.id.savedfaceslist

    /** defines the layout which will be used for this item in the list  */
    override val layoutRes: Int
        get() = R.layout.cardview

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : FastAdapter.ViewHolder<FaceListItem>(view) {
        //var name: TextView = view.findViewById(R.id.material_drawer_name)
       // var description: TextView = view.findViewById(R.id.material_drawer_description)


        override fun bindView(item: FaceListItem, payloads: MutableList<Any>) {
            //name.text = item.name
            //description.text = item.name
        }

        override fun unbindView(item: FaceListItem) {
            //name.text = null
            //description.text = null
        }
    }
}