package com.example.livewithme;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

public class FinanceFragment extends Fragment {

    private Button showDialogButton;

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
        showDialogButton = (Button) financeFragmentView.findViewById(R.id.addExpenseButton);

        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });


        return financeFragmentView;
    }

    public void openDialog() {


        ExpenseDialog expenseDialog = new ExpenseDialog();
        expenseDialog.show(getParentFragmentManager(), "expense dialog");
    }
}