package com.example.beatbox

private const val WAV = ".vaw"

class Sound (val assetPath: String) {
  val name = assetPath.split("/").last().removeSuffix(WAV)
}