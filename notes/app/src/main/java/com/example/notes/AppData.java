package com.example.notes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AppData {
    private static ArrayList<Note> noteArrayList = new ArrayList<>();

    public static ArrayList<Note> getNoteArrayList() {
        return noteArrayList;
    }

    public static void setNoteArrayList(ArrayList<Note> noteArrayList) {
        AppData.noteArrayList = noteArrayList;
    }



    public static void save(Note note, Activity activity) {
        boolean c = false;
        for (int i = 0; i < noteArrayList.size(); i++)
        {
            if(noteArrayList.get(i).getID()==note.getID()) {
                noteArrayList.set(i, note);
                c = true;
            }
        }
        if (!c)
        {
            noteArrayList.add(note);
        }
        savetosharedpreferences(activity);
    }
    private static void savetosharedpreferences(Activity activity)
    {
        Gson gson=new Gson();
        String json=gson.toJson(noteArrayList);
        SharedPreferences sharedPreferences=activity.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=sharedPreferences.edit();
        edit.putString("notes",json);
        edit.apply();
    }
    public static void Delete(Note note,Activity activity) {
        noteArrayList.remove(note);
        savetosharedpreferences(activity);
    }
    public static ArrayList<Note> retrieveNotes(Activity activity)
    {
        Gson gson=new Gson();
        SharedPreferences sharedPreferences=activity.getSharedPreferences("pref", Context.MODE_PRIVATE);
        String notes=sharedPreferences.getString("notes",null);
        Type listType=new TypeToken<ArrayList<Note>>(){}.getType();
        ArrayList<Note> list=gson.fromJson(notes,listType);
        if(list!=null)
        {
            noteArrayList.clear();
            noteArrayList.addAll(list);
            return list;
        }
        else 
        {
            return new ArrayList<>();
        }
    }

}

