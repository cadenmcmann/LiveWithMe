package com.example.livewithme;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event>
{
    public EventAdapter(Context context, List<Event> notes)
    {
        super(context, 0, notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Event note = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

        TextView title = convertView.findViewById(R.id.eventCellTV);
        //TextView desc = convertView.findViewById(R.id.cellDesc);
        //String eventTitle = note.getName() +" "+ CalendarUtils.formattedTime(note.getTime());

        title.setText(note.getTitle());
        //desc.setText(note.getDescription());

        return convertView;
    }
//    public EventAdapter(@NonNull Context context, List<Event> events)
//    {
//        super(context, 0, events);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
//    {
//        Event event = getItem(position);
//
//        if (convertView == null)
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
//
//        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);
//
//        String eventTitle = event.getName() +" "+ CalendarUtils.formattedTime(event.getTime());
//        eventCellTV.setText(eventTitle);
//        return convertView;
//    }
}
