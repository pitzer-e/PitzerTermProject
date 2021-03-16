/*
    MyUtils.kt
    Ethan Pitzer
    2021-13-3

    MyUtils is a kotlin file location where globally used methods and objects may be stored
    and accessed. From here, the settings behavior is changed, once the settings that need changed
    are passed as parameters to the method within outside activities.
 */
package com.example.pitzertermproject

import android.content.Context
import android.media.MediaPlayer
import android.util.Log

object MyUtils {

    lateinit var mp: MediaPlayer
    var paused: Boolean = false

    //  setup..not start..but setup?
    fun setup(context: Context){
        Log.i("MyUtils", "beginning setup.....")
        mp = MediaPlayer.create(context, R.raw.backgroundmusic)
        Log.i("MyUtils", "setup done!")
    }

    //  start.....things??
    fun startMe(context: Context){
        Log.i("MyUtils","in startMe()")
        mp.start()
        mp.isLooping = true
        Log.i("MyUtils","startMe() done")
    }

    //  play or pause the music, your choice! :D
    fun pauseMe(context: Context){

        if (paused) {
            Log.i("MyUtils","you have resumed the music")
            paused = false
            mp.start()
        }

        else {
            Log.i("MyUtils","you have paused the music")
            paused = true
            mp.pause()
        }

    }

    //  change volume using slider in settings activity
    fun onProgressChanged(progress: Int, fromUser: Boolean) {
        Log.i("MyUtils", "music adjusted to: $progress")

        mp.setVolume(progress.toFloat()/100, progress.toFloat()/100)
    }
}