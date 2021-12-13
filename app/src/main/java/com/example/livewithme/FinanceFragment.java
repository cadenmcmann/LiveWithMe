package com.example.livewithme;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FinanceFragment extends Fragment {

    private FloatingActionButton showDialogButton;
    private DatabaseReference db;
    private RecyclerView recyclerView;
    ExpenseAdapter adapter;
    public FirebaseRecyclerOptions<Expense> options;
    public FinanceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View financeFragmentView = inflater.inflate(R.layout.fragment_finance, container, false);
        db = FirebaseDatabase.getInstance().getReference("Expenses");

        recyclerView = financeFragmentView.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        options = new FirebaseRecyclerOptions.Builder<Expense>().setQuery(db, Expense.class).build();
        adapter = new ExpenseAdapter(options);
        recyclerView.setAdapter(adapter);



        showDialogButton = financeFragmentView.findViewById(R.id.addExpenseButton);

        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });


        return financeFragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void openDialog() {


        ExpenseDialog expenseDialog = new ExpenseDialog();
        expenseDialog.show(getParentFragmentManager(), "expense dialog");
    }
}