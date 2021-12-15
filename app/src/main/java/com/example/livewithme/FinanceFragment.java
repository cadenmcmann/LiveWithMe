package com.example.livewithme;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.text.Layout;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        // Inflate the layout for this fragment
        View financeFragmentView = inflater.inflate(R.layout.fragment_finance, container, false);
        // need to make this reference on the group's expenses
        db = FirebaseDatabase.getInstance().getReference();


        // make reference to "group" prop of user based on username
        DatabaseReference userGroupRef = db.child("Users").child(username).child("Group");
        ValueEventListener userGroupListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.getValue() != null) {
                    String groupName = snapshot.getValue().toString();
                    sharedPreferences.edit().putString("userGroupName", groupName).apply();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("err", error.toException().toString());
            }
        };
        userGroupRef.addValueEventListener(userGroupListener);

        String groupName = sharedPreferences.getString("userGroupName", "");

        DatabaseReference expensesReference = db.child("Groups").child(groupName).child("Expenses");
        recyclerView = financeFragmentView.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        options = new FirebaseRecyclerOptions.Builder<Expense>().setQuery(expensesReference, Expense.class).build();
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