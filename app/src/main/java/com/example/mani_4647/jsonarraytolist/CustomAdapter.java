package com.example.mani_4647.jsonarraytolist;

/**
 * Created by mani-4647 on 19/12/16.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manasa-pt1119 on 16/12/16.
 */


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>
{
    Context mcontext;
    public ArrayList<ListModel> mlistmodel;
    ViewHolder viewHolder;
    LayoutInflater layoutInflater;
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public View view;
        TextView userIdText;
        TextView idText;
        TextView titleText;
        TextView bodyText;

        public ViewHolder(View itemview)
        {
            super(itemview);
            this.view=itemview;
            userIdText=(TextView)view.findViewById(R.id.userId);
            idText=(TextView)view.findViewById(R.id.id);
            titleText=(TextView)view.findViewById(R.id.title);
            bodyText=(TextView)view.findViewById(R.id.body);

        }
    }


    public CustomAdapter(ArrayList<ListModel> listModel){

        mlistmodel=listModel;
    }

    @Override
    public int getItemCount() {
        return mlistmodel.size();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_layout_recyler_view,null);
        ViewHolder mvh=new ViewHolder(v);

        return mvh;



    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ListModel ob=mlistmodel.get(position);
        String user=ob.getUserId();
        String id=ob.getId();
        String title=ob.getTitle();
        String body=ob.getBody();
        holder.userIdText.setText(user);
        holder.idText.setText(id);
        holder.titleText.setText(title);
        holder.bodyText.setText(body);



    }
}