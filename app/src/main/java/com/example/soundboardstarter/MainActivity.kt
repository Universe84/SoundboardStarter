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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//commit
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

    val songString = "A 500 A 0 E 500 A 0 E 250 D 250 C 250 G 500 C 0 E 500 C 0 E 250 D 250 C 250 A 500 A 0 E 500 E 250 D 250 C 250 C 0 G 500"
    val noteMap = HashMap<String, Int>()

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

    private fun stringConvert(song : String): ArrayList<Note> {
        val songShorten = song.split(" ")
        val listOfNotes = arrayListOf<Note>()
        for(i in songShorten.indices step 2){
            listOfNotes.add(Note(songShorten[i + 1].toLong(), songShorten[i]))
        }
        return listOfNotes
    }

    private suspend fun playSong(song: List<Note>){
        for(i in song){
            playNote(i.note)
            delay(i.duration)
        }
    }

    private suspend fun playSimpleSong() {
        withContext(Dispatchers.Main) {
            binding.buttonMainA.text = "hi"
        }
        playNote(aNote)
        delay(500)
        playNote(bNote)
        playNote(aNote)
        delay(500)
        playNote(bNote)
        playNote(aNote)
        delay(500)
        playNote(bNote)
        playNote(aNote)
        delay(500)
        playNote(bNote)
        playNote(aNote)
        delay(500)
        playNote(bNote)
        playNote(aNote)
        delay(500)
        playNote(bNote)
        withContext(Dispatchers.Main) {
            binding.buttonMainA.text = "A"
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

            noteMap["A"] = aNote
            noteMap["Bb"] = bbNote
            noteMap["B"] = bNote
            noteMap["C"] = cNote
            noteMap["CS"] = cSNote
            noteMap["D"] = dNote
            noteMap["DS"] = dSNote
            noteMap["E"] = eNote
            noteMap["F"] = fNote
            noteMap["FS"] = fSNote
            noteMap["G"] = gNote
            noteMap["GS"] = gSNote

        }

    private fun playNote(note : String){
        playNote(noteMap[note] ?: 0)
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
            binding.buttonMainPlaySong.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    playSong(stringConvert(songString))
                }
            }


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

