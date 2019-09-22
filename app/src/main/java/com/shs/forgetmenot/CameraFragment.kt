package com.shs.forgetmenot

import android.graphics.*
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.Surface.ROTATION_90
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import com.google.firebase.ml.vision.face.FirebaseVisionFace
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.VideoResult
import com.otaliastudios.cameraview.frame.Frame
import com.otaliastudios.cameraview.frame.FrameProcessor
import okhttp3.*
import org.json.JSONObject
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt

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

    lateinit var faces: List<FirebaseVisionFace>
    var xClick = 0
    var yClick = 0
    lateinit var imageData : Frame
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.camera_fragment, container, false)

        val speech = Speech(activity, this::receiveName)
        speech.startListening()

        val camera = view.findViewById<CameraView>(R.id.cameraView)
        val faceOverlay = view.findViewById<ImageView>(R.id.faceOverlay)

        faceOverlay.bringToFront()
        faceOverlay.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, m: MotionEvent): Boolean {
                if(m.action == MotionEvent.ACTION_DOWN) {
                    var currentImage = imageData
                    Log.v("currentImageLen", imageData.toString())
                    Toast.makeText(activity, "You clicked on frame", Toast.LENGTH_SHORT).show()
                    Log.v("clicked", "true")
                    xClick = m.getX().roundToInt()
                    yClick =  m.getY().roundToInt()
                    if(faces!=null) {
                        var current = faces
                        Log.v("NumFaces", current.size.toString())
                        for (i in 0..current.size-1){
                            var rect = current[i].boundingBox
                            Log.v("Rectangle", rect.flattenToString())
                            Log.v("XClick", xClick.toString())
                            Log.v("YClick", yClick.toString())
                            var cont = false
                            if(rect.contains(xClick-100,yClick-400) && cont) {
                                Log.v("Click", "Clicked")
                                var out = ByteArrayOutputStream()
                                var yuvImage = YuvImage(imageData.data, ImageFormat.NV21, imageData.size.width, imageData.size.height, null)
                                //yuvImage.compressToJpeg(Rect(0,0,imageData.size.width, imageData.size.height), 90, out)
                                yuvImage.compressToJpeg(rect, 90, out)
                                var imageBytes = out.toByteArray()
                                var encoded = Base64.encodeToString(imageBytes, Base64.DEFAULT)

                                var image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                                Log.v("bitmapStats", image.height.toString())
                                var client = OkHttpClient();
                                var urlBuilder = HttpUrl.parse("http://10.1.221.115:3000/").newBuilder()
                                urlBuilder.addQueryParameter("img", encoded)
                                var url = urlBuilder.toString()
                                var request = Request.Builder().url(url).build()
                                var response = client.newCall(request).execute()
                                Log.v("response from server", response.body().string())
//                                var json = JSONObject()
//                                json.put("string64", encoded)
//                                var JSON = MediaType.parse("application/json; charset=utf-8")
//                                var body = RequestBody.create(JSON, encoded)
//                                var request = Request.Builder().url("http://10.1.221.115:3000/").post(body).build()
//                                var response = client.newCall(request).execute();
//                                Log.v("response from server", response.body().string())

                                //var arrayInputStream = ByteArrayInputStream(imageData)
                                //var bitmap = BitmapFactory.decodeStream(arrayInputStream)

                                //var resized = Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.right-rect.left, rect.bottom-rect.top)
                            }
                        }
                    }

                }
                return true;
            }
        })

        camera.setLifecycleOwner(viewLifecycleOwner)

        camera.addFrameProcessor(object : FrameProcessor {
            override fun process(frame: Frame) {
                imageData = frame.freeze()
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
                        //faceOverlay.setImageBitmap(null)
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
                        for (face in it) {
                            faces = it
                            val bounds = face.boundingBox
                            if(bounds.height()>400) {
                                val rotY =
                                    face.headEulerAngleY // Head is rotated to the right rotY degrees
                                val rotZ = face.headEulerAngleZ // Head is tilted sideways rotZ degrees
                                canvas.drawRect(bounds, dotPaint)
                                if (face.trackingId != FirebaseVisionFace.INVALID_ID) {
                                    val id = face.trackingId
                                }
                                var  done = true
                                val charPaint = Paint()
                                charPaint.color = Color.RED
                                charPaint.style = Paint.Style.FILL
                                charPaint.textSize = 70.toFloat()
                                if(done) {
                                    done = false
                                    canvas.drawText("KYLE", bounds.left.toFloat(), bounds.bottom.toFloat(), charPaint)
                                }
                            }
                        }
                        faceOverlay.setImageBitmap(bitmap)
                    }
            }

        })
        camera.addCameraListener(object : CameraListener() {
            override fun onPictureTaken(result: PictureResult) {
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

    fun receiveName(name: String) {

    }


}

