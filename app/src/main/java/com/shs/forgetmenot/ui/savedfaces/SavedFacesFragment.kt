package com.shs.forgetmenot.ui.savedfaces

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shs.forgetmenot.R

class SavedFacesFragment : Fragment() {

    companion object {
        fun newInstance() = SavedFacesFragment()
    }

    private lateinit var viewModel: SavedFacesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.saved_faces_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SavedFacesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
