package com.example.beatbox

import android.content.res.AssetManager
import android.util.Log
import java.util.Collections.emptyList

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"

class BeatBox (private val assets: AssetManager) {
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
      sounds.add (sound)
    }

    return sounds
  }

}