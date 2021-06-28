package com.example.beatbox

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException
import java.util.Collections.emptyList

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"
private const val MAX_SOUNDS = 10

class BeatBox (private val assets: AssetManager) {
  private val soundPool = SoundPool.Builder()
    .setMaxStreams(MAX_SOUNDS)
    .build()

  val sounds: List<Sound>

  init {
    sounds = loadSounds()
  }

  private fun loadSounds(): List<Sound>
  {
    val soundNames: List<String>
    try {
      soundNames = assets.list (SOUNDS_FOLDER)!!.asList()
    }
    catch (e: Exception) {
      Log.e(TAG, "Could not list assets", e)
      return emptyList()
    }

    val sounds = mutableListOf<Sound>()
    soundNames.forEach { filename ->
      val assetPath = "$SOUNDS_FOLDER/$filename"
      val sound = Sound (assetPath)
      try {
        load (sound)
        sounds.add (sound)
      }
      catch (ioe: IOException) {
        Log.e (TAG, "Couldn't load sound $filename", ioe)
      }
    }

    return sounds
  }

  private fun load (sound: Sound) {
    val afd: AssetFileDescriptor = assets.openFd(sound.assetPath)
    val soundId = soundPool.load (afd, 1)
    sound.id = soundId
  }

  fun play(sound: Sound) {
    sound.id?.let {
      soundPool.play(it, 1.0f, 1.0f, 1, 0, 1.0f)
    }
  }

}