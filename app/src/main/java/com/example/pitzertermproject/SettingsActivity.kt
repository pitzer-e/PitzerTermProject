/*
    SettingsActivity.kt
    Ethan Pitzer
    2021-13-3

    SettingsActivity is an activity which users may navigate to from menu activity within the
    MainActivity or any fragment off of it. The settings activity is where the user will find
    audio controls for either pausing/playing the music using a simple button, or by changing
    the volume of the music by using a SeekBar. The activity that the user creates here is then
    passed to the MyUtils class, where the project media player object is modified from
 */
package com.example.pitzertermproject

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.annotation.RequiresApi

class SettingsActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    private lateinit var slider: SeekBar

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.title = "Settings"

        setContentView(R.layout.activity_settings)
        slider = findViewById(R.id.volumeBar)
        slider.setOnSeekBarChangeListener(this)
        slider.setProgress(75, true)
    }

    //  pause/play button that is passed to the MyUtils.kt
    fun pausePlay(view: View) {
        MyUtils.pauseMe(this)
    }

    //  change volume by passing it to the MyUtils.kt
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        MyUtils.onProgressChanged(progress, fromUser)
    }

    //  purposely left non-implemented
    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    //  purposely left non-implemented
    override fun onStopTrackingTouch(seekBar: SeekBar?) {}


}

