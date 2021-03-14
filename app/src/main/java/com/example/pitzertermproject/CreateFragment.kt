/*
    CreateFragment.kt
    Ethan Pitzer
    2021-13-3

    Create fragment is a fragment off of the MainActivity where the user may navigate to in order
    to use the creation portion of my ticket creation application.
 */
package com.example.pitzertermproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class CreateFragment : Fragment() {

    lateinit var btn_submit: Button
    lateinit var et_name: EditText
    lateinit var et_topic: EditText
    lateinit var et_description: EditText

    //  this is my context here...literally all good variable names are keywords
    //  why tho?? ._.
    lateinit var myThing: Context

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create, container, false)

        btn_submit = view.findViewById(R.id.btn_submit)
        et_name = view.findViewById<EditText>(R.id.et_name)
        et_topic = view.findViewById<EditText>(R.id.et_topic)
        et_description = view.findViewById<EditText>(R.id.et_description)

        btn_submit.setOnClickListener() {
            Log.i("Create ticket fragment","submit button pressed")

            //  so long as this thing isn't null, you can have toast
            if (container != null) {
                myThing = container.context
            }

            var ticketModel: TicketModel

            //  are the name and topic field missing?
            if (et_name.text.toString() == "" && et_topic.text.toString() == "") {
                Toast.makeText(myThing, "Name and topic required", Toast.LENGTH_LONG).show()
            }

            //  is the name field missing?
            else if (et_name.text.toString() == "" && et_topic.text.toString() != "") {
                Toast.makeText(myThing, "Name required", Toast.LENGTH_LONG).show()
            }

            //  is the topic field missing?
            else if (et_name.text.toString() != "" && et_topic.text.toString() == "") {
                Toast.makeText(myThing, "Topic required", Toast.LENGTH_LONG).show()
            }

            //  both name and topic field have entries - do the thing!
            else {

                try{

                    ticketModel = TicketModel(et_name.text.toString(), et_topic.text.toString(), et_description.text.toString(), -1)
                    Toast.makeText(myThing, "Ticket submitted successfully!", Toast.LENGTH_SHORT).show()

                } catch(error: Exception ) {

                    ticketModel = TicketModel("John Doe", "Error", "Bad Insert", -1)
                    Toast.makeText(myThing, "Problem creating ticket..", Toast.LENGTH_SHORT).show()

                }

                val myHelper: DataBaseHelper = DataBaseHelper(myThing)
                myHelper.addOne(ticketModel)
            }

        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
