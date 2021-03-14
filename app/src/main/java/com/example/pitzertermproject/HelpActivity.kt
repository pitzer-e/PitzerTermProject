/*
    HelpActivity.kt
    Ethan Pitzer
    2021-13-3

    HelpActivity is a simple landing page activity which the user is brought to once they select
    the Help menu item from any of the main fragments. Navigation back to the main activity from
    this HelpActivity is done by using the back button within the android emulation (or physical)
    device itself.  This page provides information on how to use this app, and what resources
    were used in the creation of the app.
 */
package com.example.pitzertermproject

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import androidx.appcompat.app.AppCompatActivity

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
    }
}