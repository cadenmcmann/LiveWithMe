package com.example.livewithme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ExpenseAdapter extends FirebaseRecyclerAdapter<Expense, ExpenseAdapter.ExpenseHolder>{


    public ExpenseAdapter(@NonNull FirebaseRecyclerOptions<Expense> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ExpenseHolder holder, int position, @NonNull Expense model) {
        holder.viewName.setText(model.getName());
        holder.viewDate.setText(model.getDate().toString());
        holder.viewCost.setText(String.valueOf(model.getCost()));


    }

    @NonNull
    @Override
    public ExpenseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_item, parent, false);
        return new ExpenseHolder(v);
    }

    class ExpenseHolder extends RecyclerView.ViewHolder {

        TextView viewName;
        TextView viewDate;
        TextView viewCost;

        public ExpenseHolder(@NonNull View itemView) {
            super(itemView);
            viewName = itemView.findViewById(R.id.text_view_name);
            viewDate = itemView.findViewById(R.id.text_view_date);
            viewCost = itemView.findViewById(R.id.text_view_cost);
        }
    }
}
