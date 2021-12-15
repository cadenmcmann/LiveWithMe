package com.example.livewithme;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SQLiteManager extends SQLiteOpenHelper
{
    private static SQLiteManager sqLiteManager;

    private static final String DATABASE_NAME = "NoteDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Note";
    private static final String COUNTER = "Counter";

    private static final String ID_FIELD = "id";
    private static final String TITLE_FIELD = "title";
    private static final String DESC_FIELD = "desc";
    private static final String DELETED_FIELD = "deleted";

    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    public SQLiteManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context)
    {
        if(sqLiteManager == null)
            sqLiteManager = new SQLiteManager(context);

        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD)
                .append(" INT, ")
                .append(TITLE_FIELD)
                .append(" TEXT, ")
                .append(DESC_FIELD)
                .append(" TEXT, ")
                .append(DELETED_FIELD)
                .append(" TEXT)");

        sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
//        switch (oldVersion)
//        {
//            case 1:
//                sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + NEW_COLUMN + " TEXT");
//            case 2:
//                sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + NEW_COLUMN + " TEXT");
//        }
    }

    public void addNoteToDatabase(Event note)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, note.getId());
        //contentValues.put(TITLE_FIELD, note.getTitle());
        contentValues.put(DESC_FIELD, note.getDescription());
        System.out.println(note.getDescription());
        //contentValues.put(DELETED_FIELD, getStringFromDate(note.getDeleted()));
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void populateNoteListArray()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null))
        {
            if(result.getCount() != 0)
            {
                while (result.moveToNext())
                {
                    int id = result.getInt(1);
                    //String title = result.getString(2);
                    String desc = result.getString(3);
                    String stringDeleted = result.getString(4);
                    Date deleted = getDateFromString(stringDeleted);
                    Event note = new Event(id,desc,deleted);
                    Event.noteArrayList.add(note);
                }
            }
        }
    }

    public void updateNoteInDB(Event note)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, note.getId());
        //contentValues.put(TITLE_FIELD, note.getTitle());
        contentValues.put(DESC_FIELD, note.getDescription());
        contentValues.put(DELETED_FIELD, getStringFromDate(note.getDeleted()));

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " =? ", new String[]{String.valueOf(note.getId())});
    }

    private String getStringFromDate(Date date)
    {
        if(date == null)
            return null;
        return dateFormat.format(date);
    }

    private Date getDateFromString(String string)
    {
        try
        {
            return dateFormat.parse(string);
        }
        catch (ParseException | NullPointerException e)
        {
            return null;
        }
    }
}


//import android.annotation.SuppressLint;
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.os.Build;
//
//import androidx.annotation.RequiresApi;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//
//
//public class SQLiteManager extends SQLiteOpenHelper
//{
//    private static SQLiteManager sqLiteManager;
//
//    private static final String DATABASE_NAME = "NoteDB";
//    private static final int DATABASE_VERSION = 1;
//    private static final String TABLE_NAME = "Note";
//    private static final String COUNTER = "Counter";
//
//    private static final String ID_FIELD = "id";
//    private static final String TITLE_FIELD = "title";
//    private static final String DESC_FIELD = "desc";
//    private static final String DELETED_FIELD = "deleted";
//    private static final String DATE_FIELD = "date";
//
//    @SuppressLint("SimpleDateFormat")
//    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
//
//    public SQLiteManager(Context context)
//    {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    public static SQLiteManager instanceOfDatabase(Context context)
//    {
//        if(sqLiteManager == null)
//            sqLiteManager = new SQLiteManager(context);
//
//        return sqLiteManager;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase)
//    {
//        StringBuilder sql;
//        sql = new StringBuilder()
//                .append("CREATE TABLE ")
//                .append(TABLE_NAME)
//                .append("(")
//                .append(COUNTER)
//                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
//                .append(ID_FIELD)
//                .append(" INT, ")
//                .append(TITLE_FIELD)
//                .append(" TEXT, ")
//                .append(DESC_FIELD)
//                .append(" TEXT, ")
//                .append(DELETED_FIELD)
//                .append(" TEXT)");
//
//        sqLiteDatabase.execSQL(sql.toString());
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
//    {
////        switch (oldVersion)
////        {
////            case 1:
////                sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + NEW_COLUMN + " TEXT");
////            case 2:
////                sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + NEW_COLUMN + " TEXT");
////        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public void addNoteToDatabase(Event note)
//    {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(ID_FIELD, note.getId());
//
//        LocalDate d = note.getDate();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String formattedDate = d.format(formatter);
//        System.out.println(d);
//        contentValues.put(TITLE_FIELD, formattedDate);
//        System.out.println("DATABASE ADDING " + note.getTitle());
//        contentValues.put(DESC_FIELD, note.getDescription());
//        System.out.println("SQLITE DATAEBASE" + note.getDescription());
//        //System.out.println(note.getDescription());
//        contentValues.put(DELETED_FIELD, getStringFromDate(note.getDeleted()));
//        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public void populateNoteListArray()
//    {
//       // CalendarUtils.selectedDate = LocalDate.now();
//        System.out.println("POPULATE NOTE LIST ARRAY");
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//
//        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null))
//        {
//            System.out.println(1);
//            if(result.getCount() != 0)
//            {
//                System.out.println(2);
//                while (result.moveToNext())
//                {
//                    System.out.println(3);
//                    int id = result.getInt(1);
//                    //String title = result.getString(2);
//                    String desc = result.getString(3);
//                    String stringDeleted = result.getString(4);
//                    Date deleted = getDateFromString(stringDeleted);
//                    //need to be able to get the date from here like above
//                    Event note = new Event(id,desc,deleted,CalendarUtils.selectedDate);
//                    System.out.println("DESC" + note.getDescription());
//                    System.out.println("DATEEEEEE");
//                    System.out.println(note.getDate());
//                    Event.noteArrayList.add(note);
//                    System.out.println(Event.noteArrayList.size() + "DATABASE ARRAYLIST SIZE");
//                }
//            }
//        }
//    }
//
//    public void updateNoteInDB(Event note)
//    {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(ID_FIELD, note.getId());
//        //contentValues.put(TITLE_FIELD, note.getTitle());
//        contentValues.put(DESC_FIELD, note.getDescription());
//        contentValues.put(DELETED_FIELD, getStringFromDate(note.getDeleted()));
//
//        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " =? ", new String[]{String.valueOf(note.getId())});
//    }
//
//    private String getStringFromDate(Date date)
//    {
//        if(date == null)
//            return null;
//        return dateFormat.format(date);
//    }
//
//    private Date getDateFromString(String string)
//    {
//        try
//        {
//            return dateFormat.parse(string);
//        }
//        catch (ParseException | NullPointerException e)
//        {
//            return null;
//        }
//    }
//}
