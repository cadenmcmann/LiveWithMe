package com.example.livewithme;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExpenseDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final View expenseDialogLayout = getLayoutInflater().inflate(R.layout.expense_dialog, null);
        final View financeFragmentView = getLayoutInflater().inflate(R.layout.fragment_finance, null);

        EditText expenseNameInput = (EditText) expenseDialogLayout.findViewById(R.id.expenseNameInput);
        EditText expenseCostInput = (EditText) expenseDialogLayout.findViewById(R.id.expenseCostInput);
        EditText expenseDateInput = (EditText) expenseDialogLayout.findViewById(R.id.expenseDateInput);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setTitle("New Expense")
                .setView(expenseDialogLayout)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = db.getReference();

                        TextView textView = new TextView(getActivity());


                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        return builder.create();
    };
}
