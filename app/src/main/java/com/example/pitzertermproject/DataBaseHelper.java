/*
    DataBaseHelper.kt
    Ethan Pitzer
    2021-13-3

    DataBaseHelper is a java class which creates by extension a native SQLiteOpenHelper object.
    From here, DataBase operation commands can be programmed and passed to this object instance. This
    object, when first called, will either create the database if it isn't already created, or it
    will open it otherwise. The methods from this class are passed and accessed using fragments
    and their local context, which then acts on the database as either a read-only operation,
    or as a write-enabled operation. Errors are thrown from within this class, but largely
    caught and handled from with the application environment.
 */
package com.example.pitzertermproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TICKET_TABLE = "TICKET_TABLE";
    public static final String COLUMN_TICKET_NAME = "TICKET_NAME";
    public static final String COLUMN_TICKET_TOPIC = "TICKET_TOPIC";
    public static final String COLUMN_TICKET_DESCRIPTION = "TICKET_DESCRIPTION";
    public static final String COLUMN_ID = "ID";

    //  constructor for the database. good times!
    public DataBaseHelper(@Nullable Context context) {
        super(context, "tickets.db", null, 1);
    }

    //  this is called the first time a database is accessed. There should be code in here to create
    //  a new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TICKET_TABLE +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TICKET_NAME + " TEXT, " +
                COLUMN_TICKET_TOPIC + " TEXT, " +
                COLUMN_TICKET_DESCRIPTION + " TEXT)";

        db.execSQL(createTableStatement);
    }

    //  this is called if the database version number changes.it prevents previous users apps from
    //  breaking when you change teh database design
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //  add ticket to the database method
    public boolean addOne(TicketModel ticketModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TICKET_NAME, ticketModel.getUserName());
        cv.put(COLUMN_TICKET_TOPIC, ticketModel.getHelpTopic());
        cv.put(COLUMN_TICKET_DESCRIPTION, ticketModel.getHelpDescription());

        long insert = db.insert(TICKET_TABLE, null, cv);

        return insert != -1;
    }

    //  grab all tickets for viewing
    public List<TicketModel> getAll() {
        List<TicketModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TICKET_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {

            //  loop through the cursor (result set) and create new ticket objects. Put them in
            //  return list

            do {

                int ticketId = cursor.getInt(0);
                String ticketName = cursor.getString(1);
                String ticketTopic = cursor.getString(2);
                String ticketDescription = cursor.getString(3);

                TicketModel newTicket = new TicketModel(ticketName,ticketTopic, ticketDescription, ticketId);
                returnList.add(newTicket);

            } while (cursor.moveToNext());
        }

        else {
            //  failure. do not add anything to the list

        }

        cursor.close();
        db.close();

        return returnList;
    }

    //  edit statement to update ticket within database
    public boolean editOne(TicketModel ticketModel) {
        //  find ticketModel in the database. if it found, update and return true.
        //  if it is not found, return false

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TICKET_NAME, ticketModel.getUserName());
        cv.put(COLUMN_TICKET_TOPIC, ticketModel.getHelpTopic());
        cv.put(COLUMN_TICKET_DESCRIPTION, ticketModel.getHelpDescription());

        String WHERE = COLUMN_ID + " = " + ticketModel.getId();

        long insert = db.update(TICKET_TABLE, cv, WHERE, null);

        return insert == 0;
    }
}
