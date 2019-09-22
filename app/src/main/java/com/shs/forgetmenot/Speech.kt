package com.shs.forgetmenot

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import java.util.*
import android.os.Looper


class Speech(private val context: Context?, private val cb: (String) -> Unit) :
    RecognitionListener {

    private var recognizer: SpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

    private val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

    init {
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)
    }

    override fun onReadyForSpeech(params: Bundle?) {
    }

    override fun onRmsChanged(rmsdB: Float) {
    }

    override fun onBufferReceived(buffer: ByteArray?) {
    }

    override fun onPartialResults(partialResults: Bundle?) {
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
    }

    override fun onBeginningOfSpeech() {
        Log.d("SPEECH", "begin")
    }

    override fun onEndOfSpeech() {
    }

    override fun onError(error: Int) {
        if ((error == SpeechRecognizer.ERROR_NO_MATCH) || (error == SpeechRecognizer.ERROR_SPEECH_TIMEOUT)) {
            Log.d("SPEECH", "error $error")
            startListening()
        }
    }

    override fun onResults(results: Bundle?) {
        if (results != null) {
            val data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)

            if (data != null) {
                Log.d("SPEECH", data[0])

                val speech = data[0]
                Log.d("SPEECH", "result: $speech")
                if (context != null) {
                    val commands = context.resources.getStringArray(R.array.name_commands)
                    for (command in commands) {
                        if (speech.startsWith(command)) {
                            Log.d("SPEECH", "command detected: $speech")
                            val name = speech.substring(command.length).trim()
                            
                            if (name.length > 1) {
                                Log.d("SPEECH", "invoking callback with value: $name")
                                cb(name)
                            }
                            break
                        }
                    }
                }
            }
//            mText.setText("results: " + String.valueOf(data.size))
        }
        stopListening()
        startListening()
    }

    fun startListening() {
        Log.d("SPEECH", "start")

        recognizer.stopListening()
        recognizer.destroy()
        recognizer = SpeechRecognizer.createSpeechRecognizer(context)
        recognizer.setRecognitionListener(this)

        val handler = Handler(Looper.getMainLooper())
        val runnable = { recognizer.startListening(intent) }
        handler.post(runnable)
    }

    fun stopListening() {
        recognizer.stopListening()
        recognizer.destroy()
    }

}
