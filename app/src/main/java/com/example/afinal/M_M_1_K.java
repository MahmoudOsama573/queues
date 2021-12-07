package com.example.afinal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class M_M_1_K extends AppCompatActivity {
    EditText ar,ar1,ar2;
    EditText sr,sr1,sr2;
    EditText k;
    Button cal;
    TextView l,lq,w,wq,p;
    double srr;
    double arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mm1_k);
        cal=findViewById(R.id.calculate);
        ar=findViewById(R.id.arrival_rate);
        sr=findViewById(R.id.service_rate);
        l=findViewById(R.id.l);
        lq=findViewById(R.id.lq);
        w=findViewById(R.id.w);
        wq=findViewById(R.id.wq);
        p=findViewById(R.id.Pn);
        sr1=findViewById(R.id.service_rate1);
        sr2=findViewById(R.id.service_rate2);
        ar1=findViewById(R.id.arrival_rate1);
        ar2=findViewById(R.id.arrival_rate2);
        k=findViewById(R.id.k);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
                try{
                String ssr,ssr1,ssr2,aar,aar1,aar2;
                ssr=sr.getText().toString();
                ssr1=sr1.getText().toString();
                ssr2=sr2.getText().toString();
                aar=ar.getText().toString();
                aar1=ar1.getText().toString();
                aar2=ar2.getText().toString();
                if((aar.length()==0&&aar1.length()==0)||(aar.length()==0&&aar2.length()==0)){
                    Toast.makeText(getBaseContext(),
                            "please enter an arrival rate value in a right way",Toast.LENGTH_LONG).show();
                }else if((aar.length()!=0&&aar1.length()!=0)||(aar.length()!=0&&aar2.length()!=0)){
                    Toast.makeText(getBaseContext(),
                            "please enter an arrival rate value in a right way",Toast.LENGTH_LONG).show();
                }
                else if((ssr.length()==0&&ssr1.length()==0)||(ssr.length()==0&&ssr2.length()==0)){
                    Toast.makeText(getBaseContext(),
                            "please enter a service rate value in a right way",Toast.LENGTH_LONG).show();
                }else if((ssr.length()!=0&&ssr1.length()!=0)||(ssr.length()!=0&&ssr2.length()!=0)){
                    Toast.makeText(getBaseContext(),
                            "please enter a service rate value in a right way",Toast.LENGTH_LONG).show();
                }else {
                    if (aar.length() != 0) {
                        arr = Double.parseDouble(ar.getText().toString());
                    } else {
                        arr = Double.parseDouble(ar1.getText().toString()) / Double.parseDouble(ar2.getText().toString());

                    }
                    if (ssr.length() != 0) {
                        srr = Double.parseDouble(sr.getText().toString());
                    } else {
                        srr = Double.parseDouble(sr1.getText().toString()) / Double.parseDouble(sr2.getText().toString());

                    }

if(k.getText().toString().length()==0){

    Toast.makeText(getBaseContext(),
            "please enter k ", Toast.LENGTH_LONG).show();
}
else if(k.getText().toString().contains(".")==true){    Toast.makeText(getBaseContext(),
        "k cant be a fractional number",Toast.LENGTH_LONG).show();}
else {
    int ki=Integer.parseInt(k.getText().toString());
                    l.setText(conv(getl(srr, arr,ki)) + "");
                    lq.setText(conv(getlq(srr, arr,ki,getl(srr, arr,ki)) )+ "");
                    w.setText(conv(getw(srr, arr,ki,getl(srr, arr,ki))) + "");
                    wq.setText(conv(getwq(srr,getw(srr, arr,ki,getl(srr, arr,ki)))) + "");
            }}
                }catch(Exception e){
                    Toast.makeText(getBaseContext(),
                            e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    public  double getw(double sr,double ar,int k,double l){
        double p=ar/sr;
        double pk=0;
        if(p==1){pk=1/(1+k);}else{
            pk=Math.pow(p, k)*((1-p)/(1-Math.pow(p, k+1)));
        }
        double w=l/(ar*(1-pk));
        return w;

    }
    public  double getl(double sr,double ar,int k){
        double p=ar/sr;
        if (p==1){return k/2; }else{
            double d=p*((1-(k+1)*Math.pow(p, k)+k*Math.pow(p, k+1))
                    /((1-p)*(1-Math.pow(p, k+1))));

            return d;
        }
    }
    public double getwq(double sr,double w){
        return (w-1/sr);
    }
    public double getlq(double sr,double ar,double k,double l){
        double p=ar/sr;
        double pk=0;
        if(p==1){pk=1/(1+k);}else{
            pk=Math.pow(p, k)*((1-p)/(1-Math.pow(p, k+1)));
        }
        return(l-(p*(1-pk)));
    }
    private void closeKeyboard(){
        View view=this.getCurrentFocus();
        if(view!=null){
            InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
    public double conv(double d){
        d=d*100;
        d = (double)((int) d);
        d=d/100;
        return  d;
    }
}