/*
    EditFragment.kt
    Ethan Pitzer
    2021-13-3

    Edit fragment is a fragment off of the MainActivity where the user may navigate to in order
    to use the edit portion of my ticket creation app. Here, the user may enter a provided ticket ID
    integer that is stored within the database, which will then update with the passed parameters
    that the user provides. Error checking is provided against bad ID entries, or non-filled
    fields
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
import java.lang.Exception

class EditFragment : Fragment() {

    lateinit var btn_edit: Button
    lateinit var et_ID: EditText
    lateinit var et_newName: EditText
    lateinit var et_newTopic: EditText
    lateinit var et_newDesc: EditText
    lateinit var myThing: Context

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit, container, false)

        btn_edit = view.findViewById(R.id.btn_edit)
        et_ID = view.findViewById(R.id.et_ticketID)
        et_newName = view.findViewById(R.id.et_newName)
        et_newTopic = view.findViewById(R.id.et_newTopic)
        et_newDesc = view.findViewById(R.id.et_newDescription)

        btn_edit.setOnClickListener() {
            Log.i("Edit ticket fragment","update button pressed")

            //  so long as this thing isn't null, you can have toast
            if (container != null) {
                myThing = container.context
            }

            var ticketModel: TicketModel

            //  are the name and topic field missing?
            if (et_newName.text.toString() == "" && et_newTopic.text.toString() == "") {
                Toast.makeText(myThing, "Name and topic required", Toast.LENGTH_LONG).show()
            }

            //  is the name field missing?
            else if (et_newName.text.toString() == "" && et_newTopic.text.toString() != "") {
                Toast.makeText(myThing, "Name required", Toast.LENGTH_LONG).show()
            }

            //  is the topic field missing?
            else if (et_newName.text.toString() != "" && et_newTopic.text.toString() == "") {
                Toast.makeText(myThing, "Topic required", Toast.LENGTH_LONG).show()
            }

            //  both name and topic field have entries - do the thing!
            else {
                val myHelper = DataBaseHelper(myThing)

                try{

                    ticketModel = TicketModel(et_newName.text.toString(), et_newTopic.text.toString(), et_newDesc.text.toString(), et_ID.text.toString().toInt())
                    val check = myHelper.editOne(ticketModel)

                    if (check) {
                        Toast.makeText(myThing, "Ticket ID not found", Toast.LENGTH_LONG).show()
                    }

                    else {
                        Toast.makeText(myThing, "Update successful!", Toast.LENGTH_LONG).show()
                    }

                } catch(error: Exception ) {

                    ticketModel = TicketModel("John Doe", "Error", "Bad Insert", -1)
                    Toast.makeText(myThing, "Problem updating ticket..check ID", Toast.LENGTH_LONG).show()

                }

            }

        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}