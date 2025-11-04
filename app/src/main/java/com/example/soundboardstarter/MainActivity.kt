package com.example.soundboardstarter

import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken



class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    lateinit var buttonA: Button
    lateinit var buttonBb: Button
    lateinit var buttonB: Button
    lateinit var buttonC: Button
    lateinit var buttonCS: Button
    lateinit var buttonD: Button
    lateinit var buttonDS: Button
    lateinit var buttonE: Button
    lateinit var buttonF: Button
    lateinit var buttonFS: Button
    lateinit var buttonG: Button
    lateinit var buttonGS: Button
    lateinit var soundPool: SoundPool
    var aNote = 0
    var bbNote = 0
    var bNote = 0
    var cNote = 0
    var cSNote = 0
    var dNote = 0
    var dSNote = 0
    var eNote = 0
    var fNote = 0
    var fSNote = 0
    var gNote = 0
    var gSNote = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var gson = Gson()
        val inputStream = resources.openRawResource(R.raw.song)

        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val type = object : TypeToken<List<Note>>() {}.type
        val questions = gson.fromJson<List<Note>>(jsonString, type)
        Log.d(com.example.soundboardstarter..MainActivity.Companion.TAG, "onCreate: $questions")



        wireWidgets()
        initializeSoundPool()
        setListeners()
    }



        private fun initializeSoundPool() {

            this.volumeControlStream = AudioManager.STREAM_MUSIC
            soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
//        soundPool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
//           // isSoundPoolLoaded = true
//        })
            aNote = soundPool.load(this, R.raw.scalea, 1)
            bbNote = soundPool.load(this, R.raw.scalebb, 1)
            bNote = soundPool.load(this, R.raw.scaleb, 1)
            cNote = soundPool.load(this, R.raw.scalec, 1)
            cSNote = soundPool.load(this, R.raw.scalecs, 1)
            dNote = soundPool.load(this, R.raw.scaled, 1)
            dSNote = soundPool.load(this, R.raw.scaleds, 1)
            eNote = soundPool.load(this, R.raw.scalee, 1)
            fNote = soundPool.load(this, R.raw.scalef, 1)
            fSNote = soundPool.load(this, R.raw.scalefs, 1)
            gNote = soundPool.load(this, R.raw.scaleg, 1)
            gSNote = soundPool.load(this, R.raw.scalegs, 1)
        }

        private fun wireWidgets() {
            buttonA = findViewById(R.id.button_main_a)
            buttonBb = findViewById(R.id.button_main_bb)
            buttonB = findViewById(R.id.button_main_b)
            buttonC = findViewById(R.id.button_main_c)
            buttonCS = findViewById(R.id.button_main_cS)
            buttonD = findViewById(R.id.button_main_d)
            buttonDS = findViewById(R.id.button_main_dS)
            buttonE = findViewById(R.id.button_main_e)
            buttonF = findViewById(R.id.button_main_f)
            buttonFS = findViewById(R.id.button_main_fS)
            buttonG = findViewById(R.id.button_main_g)
            buttonGS = findViewById(R.id.button_main_gS)

        }

        private fun setListeners() {
            val soundBoardListener = SoundBoardListener()
            buttonA.setOnClickListener(soundBoardListener)
            buttonBb.setOnClickListener(soundBoardListener)
            buttonB.setOnClickListener(soundBoardListener)
            buttonC.setOnClickListener(soundBoardListener)
            buttonCS.setOnClickListener(soundBoardListener)
            buttonD.setOnClickListener(soundBoardListener)
            buttonDS.setOnClickListener(soundBoardListener)
            buttonE.setOnClickListener(soundBoardListener)
            buttonF.setOnClickListener(soundBoardListener)
            buttonFS.setOnClickListener(soundBoardListener)
            buttonG.setOnClickListener(soundBoardListener)
            buttonGS.setOnClickListener(soundBoardListener)
        }


        private fun playNote(noteId: Int) {
            soundPool.play(noteId, 1f, 1f, 1, 0, 1f)
        }

        private inner class SoundBoardListener : View.OnClickListener {
            override fun onClick(v: View?) {
                when (v?.id) {
                    R.id.button_main_a -> playNote(aNote)
                    R.id.button_main_bb -> playNote(bbNote)
                    R.id.button_main_b -> playNote(bNote)
                    R.id.button_main_c -> playNote(cNote)
                    R.id.button_main_cS -> playNote(cSNote)
                    R.id.button_main_d -> playNote(dNote)
                    R.id.button_main_dS -> playNote(dSNote)
                    R.id.button_main_e -> playNote(eNote)
                    R.id.button_main_f -> playNote(fNote)
                    R.id.button_main_fS -> playNote(fSNote)
                    R.id.button_main_g -> playNote(gNote)
                    R.id.button_main_gS -> playNote(gSNote)
                }
            }
        }
    }

