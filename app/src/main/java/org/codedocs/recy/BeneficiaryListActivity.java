package org.codedocs.recy;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class BeneficiaryListActivity extends AppCompatActivity {
    private AppCompatActivity activity = BeneficiaryListActivity.this;
    Context context = BeneficiaryListActivity.this;
    private RecyclerView recyclerViewBeneficiary;
    private ArrayList<Beneficiary> listBeneficiary;
    private BeneficiaryRecyclerAdapter beneficiaryRecyclerAdapter;
    private DatabaseHelper databaseHelper;
    SearchView searchBox;
    private ArrayList<Beneficiary> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary_list);
        initViews();
        initObjects();
    }
    private void initObjects() {
        listBeneficiary = new ArrayList<>();
        beneficiaryRecyclerAdapter = new BeneficiaryRecyclerAdapter(listBeneficiary, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewBeneficiary.setLayoutManager(mLayoutManager);
        recyclerViewBeneficiary.setItemAnimator(new DefaultItemAnimator());
        recyclerViewBeneficiary.setHasFixedSize(true);
        recyclerViewBeneficiary.setAdapter(beneficiaryRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        get();

    }
    private void initViews() {
        recyclerViewBeneficiary = (RecyclerView) findViewById(R.id.xrecy);
    }

    public void get(){

         new AsyncTask<Void,Void,Void>(){

             @Override
             protected Void doInBackground(Void... voids) {
                 listBeneficiary.clear();
                 listBeneficiary.addAll(databaseHelper.getAllBeneficiary());
                 return null;
             }
             @Override
             protected void onPostExecute(Void aVoid) {
                 super.onPostExecute(aVoid);
                 beneficiaryRecyclerAdapter.notifyDataSetChanged();
             }
         }.execute();

    }
}
