package com.example.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class SoundViewModel: BaseObservable() {
  fun onButtonClick() {
    TODO("Not yet implemented")
  }

  var sound: Sound? = null
    set (sound) {
      field = sound
      notifyPropertyChanged(BR.title)
    }

  @get: Bindable
  val title: String?
    get() = sound?.name
}