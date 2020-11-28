package com.example.notes;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class WalkthroughActivity extends AppCompatActivity {
    private ImageView dot1,dot2;
    TextView f1,f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);
        ViewPager viewPager =findViewById(R.id.frag);
        TabLayout tabLayout=findViewById(R.id.tab);
        viewPager.setAdapter(new mycustomViewPager(getSupportFragmentManager()));
        dot1=findViewById(R.id.dot1);
        dot2=findViewById(R.id.dot2);
        f1=findViewById(R.id.f1);
        f2=findViewById(R.id.f2);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        dot1.setImageResource(R.drawable.dotselected);
                        dot2.setImageResource(R.drawable.dot);
                        //f1.setTextColor(Color.parseColor("#ffffff"));
                        //f2.setTextColor(Color.parseColor("#80FFFFFF"));
                        break;
                    case 1:
                        dot2.setImageResource(R.drawable.dotselected);
                        dot1.setImageResource(R.drawable.dot);
                        //f2.setTextColor(Color.parseColor("#ffffff"));
                        //f1.setTextColor(Color.parseColor("#80FFFFFF"));

                        break;



                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
tabLayout.setupWithViewPager(viewPager);
    }
}
