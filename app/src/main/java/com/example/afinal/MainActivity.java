package com.example.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    Button deter;
    Button mm1;
    Button mm1k;
    Button mmc;
    Button mmck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent det=new Intent(getBaseContext(),DeterAct.class);
        Intent MM1=new Intent(getBaseContext(),M_M_1.class);
        Intent MM1K=new Intent(getBaseContext(),M_M_1_K.class);
        Intent MMC=new Intent(getBaseContext(),M_M_C.class);
        Intent MMCK=new Intent(getBaseContext(),M_M_C_K.class);
        deter=findViewById(R.id.deter);
        mm1=findViewById(R.id.M_M_1);
        mm1k=findViewById(R.id.M_M_1_k);
        mmc=findViewById(R.id.M_M_C);
        mmck=findViewById(R.id.M_M_C_k);
        deter.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(det);
            }
        });
        mm1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MM1);
            }
        });
        mm1k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MM1K);
            }
        });
        mmc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MMC);
            }
        });
        mmck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MMCK);
            }
        });
    }
}