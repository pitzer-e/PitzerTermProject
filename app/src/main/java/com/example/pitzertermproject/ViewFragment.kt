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

    lateinit var btn_view: Button
    lateinit var myThing: Context
    lateinit var lv_ticketList: ListView
    lateinit var ticketArrayAdapter: ArrayAdapter<TicketModel>
    lateinit var myHelper: DataBaseHelper

    //  onCreate view
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view, container, false)

        btn_view = view.findViewById(R.id.btn_view)
        lv_ticketList = view.findViewById(R.id.lv_ticketList)
        btn_view.setOnClickListener(){
            Log.i("View ticket fragment","refresh button pressed")

            if (container != null) {
                myThing = container.context
            }

            myHelper = DataBaseHelper(myThing)
            ticketArrayAdapter = ArrayAdapter<TicketModel>(myThing, android.R.layout.simple_list_item_1, myHelper.getAll())
            lv_ticketList.adapter = ticketArrayAdapter

        }
        return view;
    }

    //  onCreate
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}