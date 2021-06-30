package com.example.beatbox

import android.os.Bundle
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beatbox.databinding.ActivityMainBinding
import com.example.beatbox.databinding.ListItemSoundBinding

class MainActivity : AppCompatActivity() {
  private lateinit var beatBox: BeatBox

  override fun onCreate (savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding: ActivityMainBinding = DataBindingUtil.setContentView (this,
      R.layout.activity_main)

    beatBox = BeatBox (assets)

    val apply = binding.recyclerView.apply {
      layoutManager = GridLayoutManager(context, 3)
      adapter = SoundAdapter(beatBox.sounds)
    }

    setBeatBoxSoundRate (binding.seekBar.progress)
    binding.textView.text = getString(R.string.seek_bar_text, getSpeedPercent()) + "%"

    binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
      override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
        setBeatBoxSoundRate(i)
        binding.textView.text = getString(R.string.seek_bar_text, getSpeedPercent()) + "%"
      }

      override fun onStartTrackingTouch(seekBar: SeekBar) {
        // Do something
      }

      override fun onStopTrackingTouch(seekBar: SeekBar) {
        // Do something
      }
    })
  }

  private fun getSpeedPercent(): Int {
    return (beatBox.soundRate * 100).toInt()
  }

  private fun setBeatBoxSoundRate(progress: Int)  {
    beatBox.soundRate = when (progress) {
      0 -> 0.5f
      2 -> 2.0f
      else -> 1.0f
    }
  }

  private inner class SoundHolder (private val binding: ListItemSoundBinding):
    RecyclerView.ViewHolder (binding.root)
  {
    init {
      binding.viewModel = SoundViewModel(beatBox)
    }

    fun bind (sound: Sound) {
      binding.apply {
        viewModel?.sound = sound
        executePendingBindings()
      }
    }
  }

  private inner class SoundAdapter (private val sounds: List<Sound>):
    RecyclerView.Adapter<SoundHolder>()
  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
      val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
        layoutInflater, R.layout.list_item_sound, parent, false)
        return SoundHolder(binding)
    }

    override fun onBindViewHolder(holder: SoundHolder, position: Int) {
      holder.bind (sounds[position])
    }

    override fun getItemCount() = sounds.size
  }

  override fun onDestroy() {
    super.onDestroy()
    beatBox.release()
  }
}