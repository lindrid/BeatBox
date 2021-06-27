package com.example.beatbox

private const val WAV = ".vaw"

class Sound (val assetPath: String, var id: Int? = null) {
  val name = assetPath.split("/").last().removeSuffix(WAV)
}