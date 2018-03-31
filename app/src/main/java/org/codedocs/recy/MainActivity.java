package org.codedocs.recy;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView mname,memail,maddres,mcountry,mid;
    Button mreg,mlist;
    DatabaseHelper databaseHelper;
    InputValidation inputValidation;
    Beneficiary beneficiary;
    AppCompatActivity activity=MainActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initlistener();
        initobjects();
    }
    public void initobjects(){
        databaseHelper=new DatabaseHelper(activity);

        beneficiary =new Beneficiary();
    }
    public void initlistener(){
        mreg.setOnClickListener(this);
        mlist.setOnClickListener(this);
    }
   public void initViews(){

        mname=(TextView)findViewById(R.id.xname);
       memail=(TextView)findViewById(R.id.xemail);
       maddres=(TextView)findViewById(R.id.xaddress);
       mcountry=(TextView)findViewById(R.id.xcountry);
       mid=(TextView)findViewById(R.id.xid);
       mreg=(Button)findViewById(R.id.register);
       mlist=(Button)findViewById(R.id.list);

   }
    private void emptyInputEditText() {
       mid.setText(null);
        mname.setText(null);
        memail.setText(null);
        mcountry.setText(null);
        maddres.setText(null);
    }

    @Override
    public void onClick(View view) {
       switch(view.getId()){
           case R.id.register:
               if(databaseHelper.check(memail.getText().toString()))
               postDataToSQLite();
               else{
                   Toast.makeText(this,"entry already exists",Toast.LENGTH_LONG).show();
               }
           break;
           case R.id.list:

               Intent accountsIntent=new Intent(MainActivity.this,BeneficiaryListActivity.class);
               //accountsIntent.putExtra("ID",mid.getText().toString().trim());
               //accountsIntent.putExtra("NAME",mname.getText().toString().trim());
               //accountsIntent.putExtra("EMAIL",memail.getText().toString().trim());
               //accountsIntent.putExtra("ADDRESS",maddres.getText().toString().trim());
               //accountsIntent.putExtra("COUNTRY",mcountry.getText().toString().trim());
               emptyInputEditText();
               startActivity(accountsIntent);
               }


       }



    public void postDataToSQLite(){
       beneficiary.setId(Integer.parseInt(mid.getText().toString().trim()));
        beneficiary.setName(mname.getText().toString().trim());
        beneficiary.setEmail(memail.getText().toString().trim());
        beneficiary.setAddress(maddres.getText().toString().trim());
        beneficiary.setCountry(mcountry.getText().toString().trim());
        databaseHelper.add(beneficiary);
        Log.e("bleh", "done");
        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();

    }
}
