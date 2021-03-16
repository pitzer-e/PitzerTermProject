/*
    ViewFragment.kt
    Ethan Pitzer
    2021-13-3

    View fragment is a fragment off of the MainActivity where the user may navigate to in order
    to refresh and view all added tickets to the database. From here, the user may gather
    information about the ticket ID which may be helpful in the Edit Fragment
 */
package com.example.pitzertermproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment

class ViewFragment : Fragment() {

    private lateinit var btnView: Button
    private lateinit var myThing: Context
    private lateinit var lvTicketList: ListView
    private lateinit var ticketArrayAdapter: ArrayAdapter<TicketModel>
    private lateinit var myHelper: DataBaseHelper

    //  onCreate view
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view, container, false)

        btnView = view.findViewById(R.id.btn_view)
        lvTicketList = view.findViewById(R.id.lv_ticketList)
        btnView.setOnClickListener(){
            Log.i("View ticket fragment","refresh button pressed")

            if (container != null) {
                myThing = container.context
            }

            myHelper = DataBaseHelper(myThing)
            ticketArrayAdapter = ArrayAdapter<TicketModel>(myThing, android.R.layout.simple_list_item_1, myHelper.getAll())
            lvTicketList.adapter = ticketArrayAdapter

        }
        return view;
    }

    //  onCreate
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}