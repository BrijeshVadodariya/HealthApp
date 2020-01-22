package com.example.healthapp;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    JSONArray jsonArray;
    Context context;


    public CustomAdapter(JSONArray jsonArray, Context context) {
        this.jsonArray = jsonArray;
        this.context = context;

    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        try {

            JSONObject jsonObject = jsonArray.getJSONObject(position);
            holder.name.setText(jsonObject.getString("h_name"));
            if (jsonObject.getString("h_pgflag").equals("p"))
                holder.imageView.setImageResource(R.drawable.red);
            else
                holder.imageView.setImageResource(R.drawable.green);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        ImageView imageView;
        public  String abc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.himg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();

            // check if item still exists
            if(pos != RecyclerView.NO_POSITION){
                try {
                    abc = jsonArray.getString(pos);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(v.getContext(), "You clicked "+abc , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,Next_Screen.class);
                intent.putExtra("data",abc);
                context.startActivity(intent);
            }
        }
    }



}
