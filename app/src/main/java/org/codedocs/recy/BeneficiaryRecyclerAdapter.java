package org.codedocs.recy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Abhimanyu on 3/31/2018.
 */

public class BeneficiaryRecyclerAdapter extends RecyclerView.Adapter<BeneficiaryRecyclerAdapter.BeneficiaryViewHolder> {
    ArrayList<Beneficiary> listBeneficiary;
    Context mContext;
    ArrayList<Beneficiary> mFilteredList;

    public BeneficiaryRecyclerAdapter(ArrayList<Beneficiary> listBeneficiary,Context mContext){
        this.listBeneficiary=listBeneficiary;
        this.mContext=mContext;
        this.mFilteredList=listBeneficiary;
    }
    @Override
    public BeneficiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beneficiary_recycler,parent,false);
        return new BeneficiaryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BeneficiaryRecyclerAdapter.BeneficiaryViewHolder holder, int position) {
        holder.mname.setText(listBeneficiary.get(position).getName());
        holder.memail.setText(listBeneficiary.get(position).getEmail());
        holder.maddress.setText(listBeneficiary.get(position).getAddress());
        holder.mcountry.setText(listBeneficiary.get(position).getCountry());

    }

    @Override
    public int getItemCount() {
        return listBeneficiary.size();
    }
    public class BeneficiaryViewHolder extends RecyclerView.ViewHolder {
        TextView mname,memail,maddress,mcountry;

        public BeneficiaryViewHolder(View view) {
            super(view);
            mname=(TextView)view.findViewById(R.id.lname);
            memail=(TextView)view.findViewById(R.id.lemail);
            maddress=(TextView)view.findViewById(R.id.laddress);
            mcountry=(TextView)view.findViewById(R.id.lcountry);


        }
    }
}

