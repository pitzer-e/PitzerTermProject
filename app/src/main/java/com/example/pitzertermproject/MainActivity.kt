/*
    MainActivity.kt
    Ethan Pitzer
    2021-13-3

    MainActivity is where fragments and MyUtils are setup and started. The body of this code
    doesn't do any of the work that is found withing the fragments, but rather simply acts
    as a location where the application is started from
 */
package com.example.pitzertermproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        MyUtils.setup(this)

        //  start music from MyUtils
        MyUtils.startMe(this)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow,
                ),
                drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    //  helper function that inflates the menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    //  helper method for navigation support float behavior
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    //  function for the help or about buttons
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.i("Main", "options selected")
        when (item.itemId) {
            R.id.action_about -> {
                val myIntent = Intent(this, AboutActivity::class.java)
                startActivity(myIntent)

                Log.i("Main","about selected")
            }
            R.id.action_help -> {
                val myIntent = Intent(this, HelpActivity::class.java)
                startActivity(myIntent)

                Log.i("Main", "help selected")
            }
            R.id.action_settings -> {
                val myIntent = Intent(this, SettingsActivity::class.java)
                startActivity(myIntent)

                Log.i("Main", "settings selected")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //  internal function to save values from fragment
    internal fun saveFragmentValues(name: String, email: String, topic: String, description: String) {

    }
}