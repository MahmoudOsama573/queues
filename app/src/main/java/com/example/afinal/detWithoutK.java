package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Vector;

public class detWithoutK extends AppCompatActivity {
    double sr,ar; boolean b;
    EditText m;
    EditText time;
    EditText c;
    TextView n;
    TextView wq;
    LinearLayout wql;
    LinearLayout ml;
    LinearLayout cl;
    Button cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_without_k);
      Intent in=getIntent();
      sr  = in.getDoubleExtra("sr",0.00);
      ar =in.getDoubleExtra("ar",0.00);
      b=in.getBooleanExtra("case1",true);
     m=findViewById(R.id.m);
     time=findViewById(R.id.time);
     c=findViewById(R.id.nForWq);
     n=findViewById(R.id.n);
     wq=findViewById(R.id.wq);
     wql=findViewById(R.id.wqlayout);
     ml=findViewById(R.id.mlayout);
     cl=findViewById(R.id.nForWqlayout);
     cal=findViewById(R.id.cal);
     if(b==false){
         ml.setVisibility(View.VISIBLE);
         wql.setVisibility(View.VISIBLE);
         cl.setVisibility(View.VISIBLE);
     }
     cal.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             closeKeyboard();
             try{
             if(time.getText().toString().length()==0){
             Toast.makeText(getBaseContext(),"please don't leave any empty field",Toast.LENGTH_LONG).show();}
             else if(time.getText().toString().contains(".")==true){    Toast.makeText(getBaseContext(),
                     "time cant be a fractional number",Toast.LENGTH_LONG).show();}
             else{
             int t=Integer.parseInt(time.getText().toString());
             if(b==true){
                int no=getn(sr,ar,t);
                 n.setText(no+"");
                 int u=(int) (1/ar);
                 GraphView graph =  findViewById(R.id.graph);
                 DataPoint[] dataPoints = new DataPoint[44];
                 int j=0;


                 for(int i=0;i<22;i++) {
                     if (i<u) {
                         dataPoints[j++] = new DataPoint(i, 0);

                     dataPoints[j++] = new DataPoint(i + .9999999, 0);
                 }else{   dataPoints[j++] = new DataPoint(i, getn(sr,ar,i));
                         dataPoints[j++] = new DataPoint(i + .9999999, getn(sr,ar,i));}
                 }
                 LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
                 graph.addSeries(series);
             }else{
                 if(m.getText().toString().length()==0||c.getText().toString().length()==0){
                     Toast.makeText(getBaseContext(),"please don't leave any empty field",Toast.LENGTH_LONG).show();}
                 else if(c.getText().toString().contains(".")==true){    Toast.makeText(getBaseContext(),
                         "costomers no cant bee a fractional number",Toast.LENGTH_LONG).show();}
                 else if(m.getText().toString().contains(".")==true){    Toast.makeText(getBaseContext(),
                         "m cant be a fractional number",Toast.LENGTH_LONG).show();}
                 else{
                 int mm=Integer.parseInt(m.getText().toString());
                 int nw=Integer.parseInt(c.getText().toString());
                 if(getti(sr,ar,mm)==-1){
                     Toast.makeText(getBaseContext(),
                             "wrong inputs",Toast.LENGTH_LONG).show();
                 }else{
wq.setText(getwq(sr,ar,mm,nw)+"");
if(getn2(sr,ar,t,mm)<=0)n.setText("1 or 0");
else n.setText(getn2(sr,ar,t,mm)+"");
                 GraphView graph =  findViewById(R.id.graph);
                 int ti=getti(sr,ar,mm);
                 DataPoint[] dataPoints = new DataPoint[40];
                 int j=0;


                 for(int i=0;i<20;i++) {
                     if (i>ti) {
                         dataPoints[j++] = new DataPoint(i, 0);

                         dataPoints[j++] = new DataPoint(i + .9999999, 0);
                     }else{

                         dataPoints[j++] = new DataPoint(i, getn2(sr,ar,i,mm));

                         dataPoints[j++] = new DataPoint(i + .9999999, getn2(sr,ar,i,mm));}
                 }
                 LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
                 graph.addSeries(series);
             }}
         }}
         }catch(Exception e){
                 Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_LONG).show();}

             }
         }
     );
    }
    public int getn(double sr,double ar,int t){
        int i1=(int) (t*ar);
        int i2=(int) (t*sr-sr/ar);
        int no=i1-i2;
        return no;
    }
    public int getn2(double sr,double ar,int t,int m){

        int i1=(int)(ar*t);
        int i2=(int)(sr*t);
        int i=m+i1-i2;
        if (i>0){
 return i;
        }
        else return 0;
    }
    public   int getwq(double sr,double ar,int m,int n) {
        if (n == 0) {
            int i = (int) ((m - 1) / (2 * sr));
            return i;
        } else if (n<=(int)(ar*getti(sr,ar,m))){

            double d1=(m-1+n)*(1/sr);
            double d2=n*(1/ar);
            double d=d1-d2;
            return (int)d;
        }else {return 0;}
    }
 public   int getti(double sr,double ar,int m){
        for(int i=0;i<100000;i++){

            int i1=(int)(ar*i);
            int i2=(int)(sr*i);
            if(m==(i2-i1))return i;
        }
        return -1;
    }
    public  int getn(double sr,double ar,int ti,int t){
        if(t<1/ar){return 0;}
        else if(t<ti){
            int i1=(int) (t*ar);
            int i2=(int) (t*sr-sr/ar);
            int no=i1-i2;
            return no;
        }else{
            return -1;
        }

    }

    private void closeKeyboard(){
        View view=this.getCurrentFocus();
        if(view!=null){
            InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}
