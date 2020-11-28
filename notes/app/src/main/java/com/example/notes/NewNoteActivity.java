package com.example.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewNoteActivity extends AppCompatActivity {

    private TextView title;
    private TextView desc;
    String t,d;
    private static Note note;

    public static void setNote(Note note) {
        NewNoteActivity.note=note;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setTitle("New Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        if (note==null) {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd mm yyyy hh:mm a");
            Date date= Calendar.getInstance().getTime();
            note = new Note("","",simpleDateFormat.format(date));
        } else {
            title.setText(note.getTitle());
            desc.setText(note.getDescription());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            saveNoteAndExit();
        }
        if(item.getItemId()==R.id.save){
            saveNoteAndExit();
        }
        if (item.getItemId()==R.id.discard)
        {
            finish();
            Toast.makeText(this,"Discarded",Toast.LENGTH_SHORT ).show();
        }
        if (item.getItemId()==R.id.share)
        {
            MainActivity.sharenote(note,this);
            finish();
            Toast.makeText(this,"Shared",Toast.LENGTH_SHORT).show();

        }
        if (item.getItemId()==R.id.del)
        {
            AppData.Delete(note,this );
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void saveNoteAndExit() {
        t=title.getText().toString();
        d=desc.getText().toString();
        note.setTitle(t);

        note.setDescription(d);
        if ((!note.getTitle().isEmpty())&&(!note.getDescription().isEmpty())) {
            AppData.save(note, this);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveNoteAndExit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        note=null;
    }
}

