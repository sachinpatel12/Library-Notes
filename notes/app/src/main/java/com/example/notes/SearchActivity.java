package com.example.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Note> allNotes;
    private ArrayList<Note> filteredNotes = new ArrayList<>();
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        allNotes = AppData.retrieveNotes(this);
        setupListview();
        searchBar = findViewById(R.id.search);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    searchNotes(s.toString());
                } else {
                    filteredNotes.clear();
                    setupListview();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void searchNotes(String query) {
        filteredNotes.clear();
        for (int i = 0; i < allNotes.size(); i++) {
            Note note = allNotes.get(i);
            if (note.getTitle().toLowerCase().startsWith(query.toLowerCase())) {
                filteredNotes.add(note);
            }
        }
        setupListview();
    }

    public void onBackPressed(View view) {
        finish();
    }

    private void setupListview() {
        listView = findViewById(R.id.listView);
        listView.setVisibility(View.VISIBLE);
        listView.setAdapter(new NotesAdapter(filteredNotes, this));
    }
}
