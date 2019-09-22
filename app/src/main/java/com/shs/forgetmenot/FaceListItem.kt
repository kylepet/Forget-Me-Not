package com.shs.forgetmenot

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.mikepenz.materialize.holder.StringHolder

open class FaceListItem : AbstractItem<FaceListItem.ViewHolder>() {

    var picture: Bitmap? = null
    var name: String? = null
    var familialRelation: String? = null

    /** defines the type defining this item. must be unique. preferably an id */
    override val type: Int
        get() = R.id.savedfaceslist

    /** defines the layout which will be used for this item in the list  */
    override val layoutRes: Int
        get() = R.layout.cardview

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    fun withName(name: String): FaceListItem {
        this.name = name
        return this
    }

    fun withFamCon(famCon: String): FaceListItem {
        this.familialRelation = famCon
        return this
    }

    fun withPic(pic: Bitmap): FaceListItem {
        this.picture = pic
        return this
    }

    class ViewHolder(view: View) : FastAdapter.ViewHolder<FaceListItem>(view) {
        var picture: com.mikhaellopez.circularimageview.CircularImageView = view.findViewById(R.id.picture)
        var name: TextView = view.findViewById(R.id.firstLastName)
        var familialRelation: TextView = view.findViewById(R.id.familialRelation)


        override fun bindView(item: FaceListItem, payloads: MutableList<Any>) {

            picture.setImageBitmap(item.picture)
            name.text = item.name
            familialRelation.text = item.familialRelation

        }

        override fun unbindView(item: FaceListItem) {
            picture.setImageBitmap(null)
            name.text = null
            familialRelation.text = null
        }
    }
}