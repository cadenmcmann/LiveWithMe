package com.example.livewithme;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.LinkedHashSet;

public class Event
{
    public static ArrayList<Event> noteArrayList = new ArrayList<>();
    public static ArrayList<Event> eventsList = new ArrayList<>();
    public static String NOTE_EDIT_EXTRA =  "noteEdit";

    private int id;
    private String title;
    private String description;
    private Date deleted;

    public Event(int id, String description, Date deleted)
    {
        this.id = id;
        //this.title = title;
        this.description = description;
        this.deleted = deleted;
    }

    public Event(int id, String description)
    {
        this.id = id;
        this.description = description;
        deleted = null;
    }

    public static Event getNoteForID(int passedNoteID)
    {
        for (Event note : noteArrayList)
        {
            if(note.getId() == passedNoteID)
                return note;
        }

        return null;
    }

    public static ArrayList<Event> eventsForDate(LocalDate date)
    {
        ArrayList<Event> events = new ArrayList<>();

        for(Event event : eventsList)
        {
            if(event.getDate().equals(date))
                events.add(event);
        }

        return events;
    }

    public static ArrayList<Event> nonDeletedNotes()
    {
        ArrayList<Event> nonDeleted = new ArrayList<>();
        for(Event note : noteArrayList)
        {
            if (note.getDeleted() == null) {
                nonDeleted.add(note);
            }
        }

        return nonDeleted;
    }

    public static ArrayList<Event> combinedNotes(ArrayList<Event> nonDeleted, ArrayList<Event> events){
        Set<Event> finalSet = new LinkedHashSet<>(nonDeleted);
        finalSet.addAll(events);
        ArrayList<Event> finalEvents = new ArrayList<>(finalSet);

        return finalEvents;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getDeleted()
    {
        return deleted;
    }

    public void setDeleted(Date deleted)
    {
        this.deleted = deleted;
    }
    //    public static ArrayList<Event> eventsList = new ArrayList<>();
//
//    public static ArrayList<Event> eventsForDate(LocalDate date)
//    {
//        ArrayList<Event> events = new ArrayList<>();
//
//        for(Event event : noteArrayList)
//        {
//            if(event.getDate().equals(date))
//                events.add(event);
//        }
//
//        return events;
//    }
//
//
    private String name;
    private LocalDate date;
    private LocalTime time;
    //
//    public Event(String name, LocalDate date, LocalTime time)
//    {
//        this.name = name;
//        this.date = date;
//        this.time = time;
//    }
//
    public String getName()
    {
        return name;
    }
    //
//    public void setName(String name)
//    {
//        this.name = name;
//    }
//
    public LocalDate getDate()
    {
        return date;
    }
//
//    public void setDate(LocalDate date)
//    {
//        this.date = date;
//    }
//
//    public LocalTime getTime()
//    {
//        return time;
//    }
//
//    public void setTime(LocalTime time)
//    {
//        this.time = time;
//    }


    public void setTime(LocalTime time)
    {
        this.time = time;
    }
}
