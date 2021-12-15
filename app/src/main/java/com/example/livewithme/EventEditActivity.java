package com.example.livewithme;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;
import java.util.Date;

public class EventEditActivity extends AppCompatActivity
{

    private EditText titleEditText, descEditText;
    private Button deleteButton;
    private Event selectedNote;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
//        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
//        eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));
        //Context context = getApplicationContext();
        checkForEditNote();
    }

    private void initWidgets()
    {
        //titleEditText = findViewById(R.id.eventNameET);
        eventNameET = findViewById(R.id.eventNameET);
        //eventDateTV = findViewById(R.id.eventDateTV);
        //eventTimeTV = findViewById(R.id.eventTimeTV);
        //descEditText = findViewById(R.id.descriptionEditText);
        deleteButton = findViewById(R.id.deleteButton);
    }

    private void checkForEditNote()
    {
        Intent previousIntent = getIntent();

        int passedNoteID = previousIntent.getIntExtra(Event.NOTE_EDIT_EXTRA, -1);
        selectedNote = Event.getNoteForID(passedNoteID);

        if (selectedNote != null)
        {
//            titleEditText.setText(selectedNote.getTitle());
            eventNameET.setText(selectedNote.getDescription());
        }
        else
        {
            deleteButton.setVisibility(View.INVISIBLE);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveEventAction(View view)
    {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        //String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(eventNameET.getText());
        System.out.println(desc);
        //System.out.println(desc);
        if(selectedNote == null) {
            //System.out.println("null");
            int id = Event.noteArrayList.size();
            Event newNote = new Event(id, desc);
            Event.noteArrayList.add(newNote);
            sqLiteManager.addNoteToDatabase(newNote);
//            String eventName = eventNameET.getText().toString();
//            Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time);
//            Event.eventsList.add(newEvent);
//            sqLiteManager.addNoteToDatabase(newNote);
        }
        else {
            //System.out.println("not null");
            //selectedNote.setTitle(title);
            selectedNote.setDescription(desc);
            sqLiteManager.updateNoteInDB(selectedNote);
        }

        finish();
    }

    public void deleteNote(View view)
    {
        selectedNote.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateNoteInDB(selectedNote);
        finish();
    }
    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;

    private LocalTime time;

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_event_edit);
//        initWidgets();
//        time = LocalTime.now();
//        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
//        eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));
//    }

//    private void initWidgets()
//    {
//        eventNameET = findViewById(R.id.eventNameET);
//        eventDateTV = findViewById(R.id.eventDateTV);
//        eventTimeTV = findViewById(R.id.eventTimeTV);
//    }

//    public void saveEventAction(View view)
//    {
//        String eventName = eventNameET.getText().toString();
//        Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time);
//        Event.eventsList.add(newEvent);
//        finish();
//    }
}
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import android.content.Intent;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//
//public class EventEditActivity extends AppCompatActivity
//{
//
//    private EditText titleEditText, descEditText;
//    private Button deleteButton;
//    private Event selectedNote;
//
//    private Date deleted;
//    private LocalDate date;
//    private String title;
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_event_edit);
//        initWidgets();
//        time = LocalTime.now();
//        date = CalendarUtils.selectedDate;
//        deleted = null;
////        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
////        eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));
//        //Context context = getApplicationContext();
//        checkForEditNote();
//    }
//
//    private void initWidgets()
//    {
//        //titleEditText = findViewById(R.id.eventNameET);
//        eventNameET = findViewById(R.id.eventNameET);
//        //eventDateTV = findViewById(R.id.eventDateTV);
//        //eventTimeTV = findViewById(R.id.eventTimeTV);
//        //descEditText = findViewById(R.id.descriptionEditText);
//        deleteButton = findViewById(R.id.deleteButton);
//    }
//
//    private void checkForEditNote()
//    {
//        Intent previousIntent = getIntent();
//
//        int passedNoteID = previousIntent.getIntExtra(Event.NOTE_EDIT_EXTRA, -1);
//        selectedNote = Event.getNoteForID(passedNoteID);
//
//        if (selectedNote != null)
//        {
////            titleEditText.setText(selectedNote.getTitle());
//            eventNameET.setText(selectedNote.getDescription());
//        }
//        else
//        {
//            deleteButton.setVisibility(View.INVISIBLE);
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public void saveEventAction(View view)
//    {
//        System.out.println("SAVE ACTION HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
//        //String title = String.valueOf(titleEditText.getText());
//        String desc = String.valueOf(eventNameET.getText());
//        //System.out.println(desc);
//        if(selectedNote == null) {
//            //System.out.println("null");
//            int id = Event.noteArrayList.size();
//            Event newNote = new Event(id, desc, deleted, date);
//
//            LocalDate d = date;
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String formattedDate = d.format(formatter);
//
//            Event newNote2 = new Event(id, desc, formattedDate);
//            System.out.println("AGGGGGGGHHHHHHH " + newNote.getDate());
//            //LocalDate currDate = newNote.getDate();
//            //newNote.setDate(currDate);
//            Event.noteArrayList.add(newNote);
//            sqLiteManager.addNoteToDatabase(newNote2);
////            String eventName = eventNameET.getText().toString();
////            Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time);
////            Event.eventsList.add(newEvent);
////            sqLiteManager.addNoteToDatabase(newNote);
//        }
//        else {
//            LocalDate d = date;
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String formattedDate = d.format(formatter);
//            //System.out.println("not null");
//            selectedNote.setTitle(formattedDate);
//            selectedNote.setDescription(desc);
//            sqLiteManager.updateNoteInDB(selectedNote);
//        }
//
//        finish();
//    }
//
//    public void deleteNote(View view)
//    {
//        selectedNote.setDeleted(new Date());
//        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
//        sqLiteManager.updateNoteInDB(selectedNote);
//        finish();
//    }
//    private EditText eventNameET;
//    private TextView eventDateTV, eventTimeTV;
//
//    private LocalTime time;
//
////    @RequiresApi(api = Build.VERSION_CODES.O)
////    @Override
////    protected void onCreate(Bundle savedInstanceState)
////    {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_event_edit);
////        initWidgets();
////        time = LocalTime.now();
////        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
////        eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));
////    }
//
////    private void initWidgets()
////    {
////        eventNameET = findViewById(R.id.eventNameET);
////        eventDateTV = findViewById(R.id.eventDateTV);
////        eventTimeTV = findViewById(R.id.eventTimeTV);
////    }
//
////    public void saveEventAction(View view)
////    {
////        String eventName = eventNameET.getText().toString();
////        Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time);
////        Event.eventsList.add(newEvent);
////        finish();
////    }
//}
