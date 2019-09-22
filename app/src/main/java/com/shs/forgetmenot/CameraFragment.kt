package com.shs.forgetmenot

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Surface.ROTATION_90
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.otaliastudios.cameraview.CameraListener
import com.shs.forgetmenot.ui.camera.CameraFragment
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.VideoResult
import com.otaliastudios.cameraview.frame.Frame
import com.otaliastudios.cameraview.frame.FrameProcessor
import androidx.core.view.ViewCompat.getRotation
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import com.google.firebase.ml.vision.face.FirebaseVisionFace
import com.google.firebase.ml.vision.face.FirebaseVisionFaceContour
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions
import com.otaliastudios.cameraview.gesture.Gesture

//TODO
//
//Have a place to upload people's faces
//
//Tap on a square to take a picture and label someone's face
//
//Have recognization of red squares from stored faces
//
//If tap on people that are already recognized, displays information about them

class CameraFragment : Fragment() {

    var faces: List<FirebaseVisionFace>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):View? {

        var view = inflater.inflate(R.layout.camera_fragment, container, false)
        
        val camera = view.findViewById<CameraView>(R.id.cameraView)
        val faceOverlay = view.findViewById<ImageView>(R.id.faceOverlay)

        faceOverlay.bringToFront()

        faceOverlay.setOnClickListener {
           Toast.makeText(activity, "You clicked on frame", Toast.LENGTH_SHORT).show()
           Log.v("clicked","true")
        }

        camera.setLifecycleOwner(viewLifecycleOwner)

        camera.addFrameProcessor(object : FrameProcessor{
            override fun process(frame: Frame) {
                val data = frame.data
                val rotation = frame.rotation
                val time = frame.time
                val size = frame.size
                val format = frame.format
                val width = frame.size.width
                val height = frame.size.height
                val metadata = FirebaseVisionImageMetadata.Builder()
                    .setWidth(width)
                    .setHeight(height)
                    .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
                    .setRotation(ROTATION_90)
                    .build()
                val firebaseVisionImage = FirebaseVisionImage.fromByteArray(frame.data, metadata)
                val options = FirebaseVisionFaceDetectorOptions.Builder().enableTracking().build()
                val faceDetector = FirebaseVision.getInstance().getVisionFaceDetector(options)
                faceDetector.detectInImage(firebaseVisionImage)
                    .addOnSuccessListener {
                        faceOverlay.setImageBitmap(null)
                        val bitmap = Bitmap.createBitmap(height, width, Bitmap.Config.ARGB_8888)
                        val canvas = Canvas(bitmap)
                        val dotPaint = Paint()
                        dotPaint.color = Color.RED
                        dotPaint.style = Paint.Style.STROKE
                        dotPaint.strokeWidth = 4F
                        val linePaint = Paint()
                        linePaint.color = Color.GREEN
                        linePaint.style = Paint.Style.STROKE
                        linePaint.strokeWidth = 2F
                        Log.v("Hello", "Message")
                        Log.v("Faces", it.size.toString())
                        for (face in it) {
                            faces = it
                            val bounds = face.boundingBox
                            val rotY = face.headEulerAngleY // Head is rotated to the right rotY degrees
                            val rotZ = face.headEulerAngleZ // Head is tilted sideways rotZ degrees
                            canvas.drawRect(bounds, dotPaint)
                            if (face.trackingId != FirebaseVisionFace.INVALID_ID) {
                                val id = face.trackingId
                            }
                        }
                        Log.v("Face Recognition", "Success")
                        faceOverlay.setImageBitmap(bitmap)
                    }
            }

        })
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

