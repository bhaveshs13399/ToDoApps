package com.course.mindorks.todoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.course.mindorks.todoapp.R;
import com.course.mindorks.todoapp.clicklisteners.ItemClickListeners;
import com.course.mindorks.todoapp.model.Notes;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    List<Notes> adapterList = new ArrayList<>();
    private ItemClickListeners itemClickListeners;

    public NotesAdapter(List<Notes> list, ItemClickListeners itemClickListeners) {
        this.adapterList = list;
        this.itemClickListeners = itemClickListeners;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {

        // bind the data from list view to holder
        final Notes notes = adapterList.get(position);
        holder.textViewTitle.setText(notes.getTitle());
        holder.textViewDescription.setText(notes.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListeners.onClick(notes);
            }
        });
    }

    @Override
    public int getItemCount() {

        return adapterList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }
    }
}
