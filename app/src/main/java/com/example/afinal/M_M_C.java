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

public class M_M_C extends AppCompatActivity {
    EditText ar,ar1,ar2;
    EditText sr,sr1,sr2;
    EditText c;
    Button cal;
    TextView l,lq,w,wq,ci;
    double srr;
    double arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mm_c);
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
        ci=findViewById(R.id.ci);
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

                 else if(c.getText().toString().contains(".")==true){    Toast.makeText(getBaseContext(),
                            "c cant be a fractional number",Toast.LENGTH_LONG).show();}

                    else{

                        int cc=Integer.parseInt(c.getText().toString());
                        ci.setText(conv((cc-arr/srr))+"");
                        lq.setText(conv(getlq(srr,arr,cc))+"");
                        w.setText(conv(getw(srr,arr,getlq(srr,arr,cc)))+"");
                       l.setText(conv(getl(srr,arr,getlq(srr,arr,cc)))+"");
                        wq.setText(conv(getwq(arr,getlq(srr,arr,cc)))+"");
                    }

                }
                }catch(Exception e){
                    Toast.makeText(getBaseContext(),
                            e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static double getw(double sr,double ar,double lq){

        return (lq/ar+1/sr);

    }
    public double getl(double sr,double ar,double lq){
        return (lq+ar/sr);
    }
    public  double getwq(double ar,double lq){
        return lq/ar;
    }
    public double getlq(double sr,double ar,int c){
        double r =ar/sr;if(r/c<1){
            double  b=(c*Math.pow(r, c))/(factorial(c)*(c-r));
            for(int i=0;i<c;i++){
                b+=(Math.pow(r, i))/(factorial(i));}

            double p=1/b;
            return(p*(((Math.pow(r, c+1))/(c))/(factorial(c)*Math.pow(1-(r/c), 2))));
        }
        else{
            double b =(1/factorial(c))*Math.pow(ar/sr,c)*((c*sr)/(c*sr-ar));
            for(int i=0;i<c;i++){
                b+=(1/factorial(i))*Math.pow(ar/sr, i);}
            double p=1/b;
            return(p*(((Math.pow(r, c+1))/(c))/(factorial(c)*Math.pow(1-(r/c), 2))));
        }
    }
    public double getPn(double sr,double ar,int n){
        double p =ar/sr;
        return(Math.pow(p, n)*(1-p));
    }
    static int factorial(int n)
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