package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    boolean t;
    private Menu menu;
    private ListView listView;
    private GridView gridView;
    boolean isListviewOn=true;

    public static void sharenote(Note note, Activity activity) {
        String title = note.getTitle();
        String desc = note.getDescription();
        Intent sharingintent = new Intent(Intent.ACTION_SEND);
        sharingintent.setType("text/plain");
        sharingintent.putExtra(Intent.EXTRA_SUBJECT, title);
        sharingintent.putExtra(Intent.EXTRA_TEXT, desc);
        activity.startActivity(sharingintent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Notes");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.grid, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setupListview() {
        if (menu != null) {
            menu.getItem(1).setVisible(false);
            menu.getItem(0).setVisible(true);
        }
        listView = findViewById(R.id.list);
        listView.setVisibility(View.VISIBLE);
        ArrayList<Note> notes = AppData.retrieveNotes(this);
        listView.setAdapter(new NotesAdapter(AppData.getNoteArrayList(), this));
        isListviewOn = true;
        if (gridView != null) {
            gridView.setVisibility(View.GONE);
        }
    }

    private void setupGridview() {
        if (menu != null) {
            menu.getItem(0).setVisible(false);
            menu.getItem(1).setVisible(true);
        }
        gridView = findViewById(R.id.gridview);
        gridView.setVisibility(View.VISIBLE);
        ArrayList<Note> notes = AppData.retrieveNotes(this);
        gridView.setAdapter(new NotesAdapter(AppData.getNoteArrayList(), this));
        isListviewOn = false;
        if (listView != null) {
            listView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.gv) {
            setupGridview();
        }
        if (item.getItemId() == R.id.list) {
            setupListview();
        }
        if (item.getItemId() == R.id.search) {
            Intent intent=new Intent(this,SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onpress(View view) {
        Intent intent = new Intent(this, NewNoteActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isListviewOn) {
            setupListview();
        } else {
            setupGridview();
        }
    }
}
