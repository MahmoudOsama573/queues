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

public class M_M_C_K extends AppCompatActivity {
    EditText ar,ar1,ar2;
    EditText sr,sr1,sr2;
    EditText c,k;
    Button cal;
    TextView l,lq,w,wq;
    double srr;
    double arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mm_ck);
        cal=findViewById(R.id.calculate);
        ar=findViewById(R.id.arrival_rate);
        sr=findViewById(R.id.service_rate);
        l=findViewById(R.id.l);
        lq=findViewById(R.id.lq);
        w=findViewById(R.id.w);
        wq=findViewById(R.id.wq);
        c=findViewById(R.id.c);
        sr1=findViewById(R.id.service_rate1);
        sr2=findViewById(R.id.service_rate2);
        ar1=findViewById(R.id.arrival_rate1);
        ar2=findViewById(R.id.arrival_rate2);
        k=findViewById(R.id.K);
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
                    if(c.getText().toString().length()==0){Toast.makeText(getBaseContext(),
                            "please enter c",Toast.LENGTH_LONG).show();}
                   else if(k.getText().toString().length()==0){Toast.makeText(getBaseContext(),
                            "please enter k",Toast.LENGTH_LONG).show();}
                    else if(c.getText().toString().contains(".")==true){    Toast.makeText(getBaseContext(),
                            "c cant be a fractional number",Toast.LENGTH_LONG).show();}
                    else if(k.getText().toString().contains(".")==true){    Toast.makeText(getBaseContext(),
                            "k cant be a fractional number",Toast.LENGTH_LONG).show();}
                    else{

                        int cc=Integer.parseInt(c.getText().toString());
                      int kk=Integer.parseInt(k.getText().toString());
                        lq.setText(conv(getlq(srr,arr,cc,kk))+"");
                        w.setText(conv(getw(srr,arr,cc,kk,getl(srr,arr,cc,kk,getlq(srr,arr,cc,kk))))+"");
                        l.setText(conv(getl(srr,arr,cc,kk,getlq(srr,arr,cc,kk)))+"");
                        wq.setText(conv(getwq(srr,arr,cc,kk,getlq(srr,arr,cc,kk)))+"");
                    }

                }
            }catch(Exception e){
                    Toast.makeText(getBaseContext(),
                            e.toString(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public double getw(double sr,double ar,int c,int k,double l){
        double r=ar/sr;
        double p=r/c;
        double pk=0;
        if(p==1){pk=1/(1+k);}else{
            pk=Math.pow(p, k)*((1-p)/(1-Math.pow(p, k+1)));
        }
        return(l/(ar*(1-pk)));
    }
    public  double getl(double sr,double ar,int c,int k,double lq){
        double r =ar/sr;
        double p=r/c;
        double p0;
        if(p!=1){
            double  b=((Math.pow(r, c))/(factorial(c)))*((1-Math.pow(p, k-c+1))/(1-p));
            for(int i=0;i<c;i++){
                b+=(Math.pow(r, i))/(factorial(i));}

            p0=1/b;}else{
            double b =((Math.pow(r, c))/(factorial(c)))*(k-c+1);
            for(int i=0;i<c;i++){
                b+=(Math.pow(r, i))/(factorial(i));}
            p0=1/b;
        }
        double l=lq+c;
        for(int i=0;i<c;i++){
            l-=(p0*(c-i)*(Math.pow(r, i)/factorial(i)));
        }
        return l;
    }
    public  double getwq(double sr,double ar,int c,int k,double lq){
        double r=ar/sr;
        double p=r/c;
        double pk=0;
        if(p==1){pk=1/(1+k);}else{
            pk=Math.pow(p, k)*((1-p)/(1-Math.pow(p, k+1)));
        }
        return(lq/(ar*(1-pk)));
    }
    public  double getlq(double sr,double ar,int c,int k){
        double r =ar/sr;
        double p=r/c;
        if(p!=1){
            double  b=((Math.pow(r, c))/(factorial(c)))*((1-Math.pow(p, k-c+1))/(1-p));
            for(int i=0;i<c;i++){
                b+=(Math.pow(r, i))/(factorial(i));}

            double p0=1/b;
            return (((p*Math.pow(r, c)*p0)/(factorial(c)*Math.pow(1-p, 2)))*
                    (1-Math.pow(p,k-c+1)-((1-p)*(k-c+1)*Math.pow(p,k-c))));
        }
        else{
            double b =((Math.pow(r, c))/(factorial(c)))*(k-c+1);
            for(int i=0;i<c;i++){
                b+=(Math.pow(r, i))/(factorial(i));}
            double p0=1/b;
            return (((p*Math.pow(r, c)*p0)/(factorial(c)*Math.pow(1-p, 2)))*
                    (1-Math.pow(p,k-c+1)-((1-p)*(k-c+1)*Math.pow(p,k-c))));            }
    }

    int factorial(int n)
    {
        if (n == 0)
            return 1;

        return n*factorial(n-1);
    }
    public double conv(double d){
        d=d*100;
        d = (double)((int) d);
        d=d/100;
        return  d;
    }
    private void closeKeyboard(){
        View view=this.getCurrentFocus();
        if(view!=null){
            InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}