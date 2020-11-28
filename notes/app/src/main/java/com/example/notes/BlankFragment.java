package com.example.notes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


     View rootview;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview=inflater.inflate(R.layout.fragment_blank, container, false);
        //Bundle bundle=getArguments();
        //int pos=bundle.getInt("pos",0);
        TextView text=rootview.findViewById(R.id.t);
        text.setText("Fragment 1" );
        return rootview;


    }

}
