package org.codedocs.recy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhimanyu on 3/30/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    DatabaseHelper DBhelper;
    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, "BeneficiaryManager.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tb="create table beneficiary(bid integer not null,bname text not null, bemail text not null,baddress text not null,bcountry text not null);";
        sqLiteDatabase.execSQL(tb);


    }
    String upgrade="drop table if exists beneficiary;";

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL(upgrade);
        onCreate(sqLiteDatabase);

    }
    public void add(Beneficiary beneficiary){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues content = new ContentValues();
            content.put("bid", beneficiary.getId());
            content.put("bname", beneficiary.getName());
            content.put("bemail", beneficiary.getEmail());
            content.put("baddress", beneficiary.getAddress());
            content.put("bcountry", beneficiary.getCountry());
            db.insert("beneficiary", null, content);
            Log.e("bleh", "done");
            db.close();
        }
        catch(Exception e){
            Log.e("bleh",e.getMessage());
        }

    }
    public List<Beneficiary> getAllBeneficiary(){
        String columns[]={
                "bid",
                "bname",
                "bemail",
                "baddress",
                "bcountry",
        };
        String sortorder="bname ASC";
        List<Beneficiary> beneficiaryList=new ArrayList<Beneficiary>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query("beneficiary",columns,null,null,null,null,sortorder);
        if(cursor.moveToFirst()){
            do{
                Beneficiary beneficiary=new Beneficiary();
                beneficiary.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("bid"))));
                beneficiary.setName(cursor.getString(cursor.getColumnIndex("bname")));
                beneficiary.setEmail(cursor.getString(cursor.getColumnIndex("bemail")));
                beneficiary.setAddress(cursor.getString(cursor.getColumnIndex("baddress")));
                beneficiary.setCountry(cursor.getString(cursor.getColumnIndex("bcountry")));
                beneficiaryList.add(beneficiary);

            }while(cursor.moveToNext());
            cursor.close();
            db.close();

        }

        return beneficiaryList;
    }
  public boolean check(String email){
        String coulmns[]={"bid"};
        SQLiteDatabase db=this.getReadableDatabase();
        String selection="bemail = ?";
        String args[]={email};
        Cursor cursor=db.query("beneficiary",coulmns,selection,args,null,null,null);
        int count=cursor.getCount();
        if(count>0)
        {return false;}
        else{
            return true;
        }
    }
}
