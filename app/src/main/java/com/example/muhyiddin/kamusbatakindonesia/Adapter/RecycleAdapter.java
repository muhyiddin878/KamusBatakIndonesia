package com.example.muhyiddin.kamusbatakindonesia.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.muhyiddin.kamusbatakindonesia.BahasaBatakTerbaru;
import com.example.muhyiddin.kamusbatakindonesia.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private List<BahasaBatakTerbaru> mDataset;
    private List<BahasaBatakTerbaru> mDatasetClone;
    public String jenis="Bahasa Batak";
    public String jenis2="Bahasa Indonesia";
    private String switcher;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView list;
        public TextView list2;
        public ImageButton tombolswitch;


        public MyViewHolder(View v, String switcher) {
            super(v);
            list =(TextView) v.findViewById(R.id.listBatak);
            list2 =(TextView) v.findViewById(R.id.ListArti);
            tombolswitch=(ImageButton) v.findViewById(R.id.tombolswitch);
        }
    }

    public List<BahasaBatakTerbaru> getmDataset() {
        return mDataset;
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecycleAdapter(List<BahasaBatakTerbaru> myDataset, String switcher) {

        mDataset = myDataset;
        this.mDataset= mDataset;
        mDatasetClone=mDataset;
        this.switcher = switcher;

    }



    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filterResults.values = mDatasetClone;
                } else {
                    List<BahasaBatakTerbaru> filteredList = new ArrayList<>();
                    for (BahasaBatakTerbaru row : mDatasetClone) {
                        if (row.getBatak().toLowerCase().contains(charString.toLowerCase()) || row.getBatak().contains(charSequence)) {
                            filteredList.add(row);
                        }

                    }
                    filterResults.values = filteredList;
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mDataset=(ArrayList<BahasaBatakTerbaru>) filterResults.values;
                notifyDataSetChanged();
            }

        };
    }



    // Create new views (invoked by the layout manager)
    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v, switcher);
        return vh;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (switcher.equals("BATAK_TO_INDONESIA")){
            holder.list.setText(mDataset.get(position).getBatak());
            holder.list2.setText(jenis);
        } else {
            holder.list.setText(mDataset.get(position).getArti());
            holder.list2.setText(jenis2);
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}