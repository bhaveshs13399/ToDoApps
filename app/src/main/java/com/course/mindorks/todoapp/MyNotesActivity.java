package com.course.mindorks.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.course.mindorks.todoapp.adapter.NotesAdapter;
import com.course.mindorks.todoapp.clicklisteners.ItemClickListeners;
import com.course.mindorks.todoapp.model.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyNotesActivity extends AppCompatActivity {

    String fullName;
    FloatingActionButton fabAddNotes;
    SharedPreferences sharedPreferences;
    String TAG = "MyNotesActivity";
    RecyclerView recyclerViewNotes;
    ArrayList<Notes> notesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);

        bindViews();
        setSharedPreferences();
        intentData();

        fabAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupDialogbox();

            }
        });

        getSupportActionBar().setTitle(fullName);
    }

    private void setSharedPreferences() {
        sharedPreferences = getSharedPreferences(PrefConstant.INSTANCE.getSHARED_PREFERENCES_NAME(), MODE_PRIVATE);

    }

    private void bindViews() {
        fabAddNotes = findViewById(R.id.fabAddNotes);
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
    }

    private void intentData() {
        Intent intent = getIntent();
        if (TextUtils.isEmpty(fullName)) {
            fullName = sharedPreferences.getString(PrefConstant.INSTANCE.getFULL_NAME(), "");
            Log.d(TAG, fullName);
        } else {
            fullName = intent.getStringExtra(AppConstant.INSTANCE.getFULL_NAME());
        }
    }

    private void setupDialogbox() {
        View view = LayoutInflater.from(MyNotesActivity.this).inflate(R.layout.add_dialog_box_layout, null);
        final EditText editTextTitle = view.findViewById(R.id.editTextTitle);
        final EditText editTextDescreption = view.findViewById(R.id.editTextDescreption);
        Button buttonSubmit = view.findViewById(R.id.buttonSubmit);

        //dialog
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();
        dialog.show();


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((!TextUtils.isEmpty(editTextTitle.getText().toString()) &&
                        (!TextUtils.isEmpty(editTextDescreption.getText().toString())))) {
                    String title = editTextTitle.getText().toString();
                    String description = editTextDescreption.getText().toString();
                    Notes notes = new Notes();
                    notes.setTitle(title);
                    notes.setDescription(description);
                    notesList.add(notes);
                    setupRecyclerView();
                    dialog.hide();
                } else {
                    Toast.makeText(MyNotesActivity.this, "Please enter title and description", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void setupRecyclerView() {

        ItemClickListeners itemClickListeners = new ItemClickListeners() {
            @Override
            public void onClick(Notes notes) {
                Log.d(TAG, "onClick working " + notes.getTitle());
                Intent intent = new Intent(MyNotesActivity.this, DetailActivity.class);
                intent.putExtra(AppConstant.INSTANCE.getTITLE(), notes.getTitle());
                intent.putExtra(AppConstant.INSTANCE.getDESCRIPTION(), notes.getDescription());
                startActivity(intent);
            }
        };


        NotesAdapter notesAdapter = new NotesAdapter(notesList, itemClickListeners);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyNotesActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewNotes.setLayoutManager(linearLayoutManager);
        recyclerViewNotes.setAdapter(notesAdapter);
    }
}