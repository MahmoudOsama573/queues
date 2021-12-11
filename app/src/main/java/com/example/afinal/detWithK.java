package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class detWithK extends AppCompatActivity {
    EditText time;
    EditText c;
    double sr,ar;
    TextView n;
    TextView wq;
    Button cal;
    int k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_with_k);
        Intent in=getIntent();
        sr  = in.getDoubleExtra("sr",0.00);
        ar =in.getDoubleExtra("ar",0.00);
k=in.getIntExtra("k",0);
        time=findViewById(R.id.time);
        c=findViewById(R.id.nForWq);
        n=findViewById(R.id.n);
        wq=findViewById(R.id.wq);
        cal=findViewById(R.id.cal);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {closeKeyboard();try{
                if(time.getText().toString().length()==0||c.getText().toString().length()==0){
                    Toast.makeText(getBaseContext(),"please don't leave any empty field",Toast.LENGTH_LONG).show();}
                else if(c.getText().toString().contains(".")==true){    Toast.makeText(getBaseContext(),
                        "n for wq cant be a fractional number",Toast.LENGTH_LONG).show();}
                else if(time.getText().toString().contains(".")==true){    Toast.makeText(getBaseContext(),
                        "time cant be a fractional number",Toast.LENGTH_LONG).show();}
                else{
                int t=Integer.parseInt(time.getText().toString());
                int cc=Integer.parseInt(c.getText().toString());

int ti=getti(sr,ar,k);
int nn=getn(sr,ar,ti,t);
int w=getwq(sr,ar,ti,cc);
if(nn==-1){n.setText((k-1)+" or "+(k-2));}else n.setText(nn+"");
if(w==-1){
    int i=(int)(ti*ar);wq.setText(getwq(sr,ar,ti,i-1)+" or "+getwq(sr,ar,ti,i-2));
}else wq.setText(w+"");
                GraphView graph =  findViewById(R.id.graph);
                DataPoint[] dataPoints = new DataPoint[(ti+2)*2];
                int j=0;
                int u=(int) (1/ar);

                for(int i=0;i<ti+2;i++) {
                    if (i<u) {
                        dataPoints[j++] = new DataPoint(i, 0);

                        dataPoints[j++] = new DataPoint(i + .99999, 0);
                    }else if(i>=ti){
                        dataPoints[j++] = new DataPoint(i, k-1);

                        dataPoints[j++] = new DataPoint(i + .99999, k-1);
                    }
                    else{   dataPoints[j++] = new DataPoint(i, getn(sr,ar,ti,i));

                        dataPoints[j++] = new DataPoint(i + .999, getn(sr,ar,ti,i));}
                }
                LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
                graph.addSeries(series);
            }   }catch(Exception e){
                Toast.makeText(getBaseContext(),
                        e.toString(),Toast.LENGTH_LONG).show();
            }}
        });



    }
    public  int getti(double sr,double ar,int k){
        for(int i=0;i>=0;i++){

            int i1=(int)(ar*i);
            int i2=(int)(sr*i-sr/ar);
            if(k==(i1-i2))return i;
        }
        return -1;
    }

    public int getn(double sr,double ar,int ti,int t){
        if(t<1/ar){return 0;}
        else if(t<ti){
            int i1=(int) (t*ar);
            int i2=(int) (t*sr-sr/ar);
            int no=i1-i2;
            return no;
        }else{
            return -1;
        }}
    public  int getwq(double sr,double ar,int ti,int n) {
        if (n == 0) {

            return 0;
        } else if (n<(int)(ar*ti)){

            double d1=1/sr-1/ar;
            double d2=(n-1);
            double d=d1*d2;
            return (int)d;
        }else {return -1;}
    }
    private void closeKeyboard(){
        View view=this.getCurrentFocus();
        if(view!=null){
            InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}