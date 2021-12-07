package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class DeterAct extends AppCompatActivity {
Button cont;
RadioButton with;
RadioButton without;
EditText k;
EditText ar,ar1,ar2;
EditText sr,sr1,sr2;
LinearLayout ll;
double srr;
double arr;
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deter);
        Intent detwithK=new Intent(getBaseContext(),detWithK.class);
        tv=findViewById(R.id.textView);
        cont=findViewById(R.id.calculate);
        with=findViewById(R.id.radiowith);
        without=findViewById(R.id.radiowithout);
        k=findViewById(R.id.k);
        ar=findViewById(R.id.arrival_rate);
        sr=findViewById(R.id.service_rate);
        ll=findViewById(R.id.klayout);
        sr1=findViewById(R.id.service_rate1);
        sr2=findViewById(R.id.service_rate2);
        ar1=findViewById(R.id.arrival_rate1);
        ar2=findViewById(R.id.arrival_rate2);
        Intent detWithout=new Intent(getBaseContext(),detWithoutK.class);
        with.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                ll.setVisibility(View.VISIBLE);
                else
                    ll.setVisibility(View.INVISIBLE);
            }
        });
        cont.setOnClickListener(new View.OnClickListener() {
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
                }else{
                    if(aar.length()!=0){
                        arr=Double.parseDouble(ar.getText().toString());
                    }else{
                        arr=Double.parseDouble(ar1.getText().toString())/Double.parseDouble(ar2.getText().toString());

                    }
                    if(ssr.length()!=0){
srr=Double.parseDouble(sr.getText().toString());
}
                    else{
                        srr=Double.parseDouble(sr1.getText().toString())/Double.parseDouble(sr2.getText().toString());

                    }
boolean b=with.isChecked();
if(b==false){
    if(srr<arr)
    detWithout.putExtra("case1",true);
    else
        detWithout.putExtra("case1",false);
    detWithout.putExtra("sr",srr);
    detWithout.putExtra("ar",arr);
startActivity(detWithout);
}else{if(k.getText().toString().length()==0){
    Toast.makeText(getBaseContext(),"please don't leave any empty field",Toast.LENGTH_LONG).show();}
else if(k.getText().toString().contains(".")==true){    Toast.makeText(getBaseContext(),
        "k cant be a fractional number",Toast.LENGTH_LONG).show();}
    else{
    if(srr>=arr){  detWithout.putExtra("case1",false);
    detWithout.putExtra("sr",srr);
    detWithout.putExtra("ar",arr);
    startActivity(detWithout);}else{
    detwithK.putExtra("sr",srr);
    detwithK.putExtra("ar",arr);
    detwithK.putExtra("k",Integer.parseInt(k.getText().toString()));
    startActivity(detwithK);
} }  }
           }   }catch(Exception e){
                    Toast.makeText(getBaseContext(),
                            e.toString(),Toast.LENGTH_LONG).show();
                }}
        });
    }
    public void closeKeyboard(){
        View view=this.getCurrentFocus();
        if(view!=null){
            InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}