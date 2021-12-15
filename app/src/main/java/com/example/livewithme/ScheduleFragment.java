package com.example.livewithme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.EditText;

import android.content.Intent;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.example.livewithme.CalendarUtils.daysInWeekArray;
import static com.example.livewithme.CalendarUtils.monthYearFromDate;

public class ScheduleFragment extends Fragment implements CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private ListView eventListView;
    private boolean isDate = false;



    public ScheduleFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_schedule, container,false);
        initWidgets(v);
        loadFromDBToMemory();
        setOnClickListener();
        //setEventAdapter();
        selectedDate = LocalDate.now();
        setWeekView();

        Button next = (Button) v.findViewById(R.id.forward);
        next.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                selectedDate = selectedDate.plusWeeks(1);
                setWeekView();
            }
        });

        Button back = (Button) v.findViewById(R.id.backward);
        back.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                selectedDate = selectedDate.minusWeeks(1);
                setWeekView();
            }
        });

        Button addEvent = (Button) v.findViewById(R.id.addEvent);
        addEvent.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if(isDate) {
                    Intent intent = new Intent(getActivity(), EventEditActivity.class);
                    startActivity(intent);
                }
            }
        });
        return v;
    }

    private void initWidgets(View view){
        calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView);
        monthYearText = view.findViewById(R.id.monthYearTV);
        eventListView = view.findViewById(R.id.eventListView);
    }

    private void loadFromDBToMemory()
    {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(getActivity());
        sqLiteManager.populateNoteListArray();
    }

    private void setOnClickListener()
    {
        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Event selectedNote = (Event) eventListView.getItemAtPosition(position);
                Intent editNoteIntent = new Intent(getActivity().getApplicationContext(), EventEditActivity.class);
                editNoteIntent.putExtra(Event.NOTE_EDIT_EXTRA, selectedNote.getId());
                startActivity(editNoteIntent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setWeekView(){
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEventAdapter();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static LocalDate sundayForDate(LocalDate current)
    {
        LocalDate oneWeekAgo = current.minusWeeks(1);

        while (current.isAfter(oneWeekAgo))
        {
            if(current.getDayOfWeek() == DayOfWeek.SUNDAY)
                return current;

            current = current.minusDays(1);
        }

        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, LocalDate date)
    {
        isDate = true;
        CalendarUtils.selectedDate = date;
        setWeekView();
    }

    private void setEventAdapter()
    {
        ArrayList<Event> dailyEvents = Event.nonDeletedNotes();
        //ArrayList<Event> dailyEvents2 = Event.eventsForDate(CalendarUtils.selectedDate);
        //ArrayList<Event> dailyEvents3 = Event.combinedNotes(dailyEvents, dailyEvents2);
        EventAdapter eventAdapter = new EventAdapter(getActivity().getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }

    public void newNote(View view)
    {
        Intent newNoteIntent = new Intent(getActivity(), EventEditActivity.class);
        startActivity(newNoteIntent);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        setEventAdapter();
    }

}