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
import com.example.soundboardstarter.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }


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

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var gson = Gson()
        val inputStream = resources.openRawResource(R.raw.song)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val type = object : TypeToken<List<Note>>(){}.type
        val notes = gson.fromJson<List<Note>>(jsonString, type)
        Log.d(TAG, "onCreate: $notes")





        initializeSoundPool()
        setListeners()

    }

    private fun playSong(song: List<Note>){
        for(note in song){

        }
    }

    private fun delay(time: Long){
        try {
            Thread.sleep(time)
        }
        catch(e: InterruptedException){
            e.printStackTrace()
        }
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



        private fun setListeners() {
            val soundBoardListener = SoundBoardListener()
            binding.buttonMainA.setOnClickListener(soundBoardListener)
            binding.buttonMainB.setOnClickListener(soundBoardListener)
            binding.buttonMainBb.setOnClickListener(soundBoardListener)
            binding.buttonMainC.setOnClickListener(soundBoardListener)
            binding.buttonMainCS.setOnClickListener(soundBoardListener)
            binding.buttonMainD.setOnClickListener(soundBoardListener)
            binding.buttonMainDS.setOnClickListener(soundBoardListener)
            binding.buttonMainE.setOnClickListener(soundBoardListener)
            binding.buttonMainF.setOnClickListener(soundBoardListener)
            binding.buttonMainFS.setOnClickListener(soundBoardListener)
            binding.buttonMainG.setOnClickListener(soundBoardListener)
            binding.buttonMainGS.setOnClickListener(soundBoardListener)
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

