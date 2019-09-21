package com.shs.forgetmenot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.otaliastudios.cameraview.CameraListener
import com.shs.forgetmenot.ui.camera.CameraFragment
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.VideoResult


class CameraFragment : Fragment() {

    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):View? {

        var view = inflater.inflate(R.layout.camera_fragment, container, false)
        
        val camera = view.findViewById<CameraView>(R.id.cameraView)
        camera.setLifecycleOwner(viewLifecycleOwner);



        camera.addCameraListener(object : CameraListener() {
            override fun  onPictureTaken(result: PictureResult) {
                // A Picture was taken!
            }


            override fun onVideoTaken(result: VideoResult) {
                // A Video was taken!
            }
        })
        
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
    }
    

}

