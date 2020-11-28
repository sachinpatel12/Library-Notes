package com.example.notes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class NotesAdapter extends BaseAdapter {
    private final ArrayList<Note> notes;
    private final Activity activity;
    public NotesAdapter(ArrayList<Note> noteArrayList,Activity activity ) {
        this.notes=noteArrayList;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return notes.size() ;
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=activity.getLayoutInflater();
        View inflate=layoutInflater.inflate(R.layout.note_item,null,false);
        TextView title = inflate.findViewById(R.id.title);
        TextView desc = inflate.findViewById(R.id.desc);
        TextView date = inflate.findViewById(R.id.date);
        title.setText(notes.get(position).getTitle());
        desc.setText(notes.get(position).getDescription());
        date.setText(notes.get(position).getCreated_on());
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NewNoteActivity.setNote(notes.get(position));
                activity.startActivity(new Intent(activity,NewNoteActivity.class));
            }
        });
        inflate.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(activity);
                builder.setTitle("Options");
                CharSequence[] items={"Open","Delete","Share"};
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int pos) {
                        switch (pos)
                        {
                            case 0:
                                openNoteActivity(notes.get(position));
                                break;
                            case 1:
                                AppData.Delete(notes.get(position),activity);
                                notifyDataSetChanged();
                                break;
                            case 2:
                                MainActivity.sharenote(notes.get(position),activity);
                                break;
                    }
                }});
                builder.create().show();
                return true;
            }
        });

        return inflate;
    }

    private void openNoteActivity(Note note) {
        NewNoteActivity.setNote(note);
        activity.startActivity(new Intent(activity,NewNoteActivity.class));
    }


}
