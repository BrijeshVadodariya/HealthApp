package com.example.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragement_All extends Fragment {

    private  RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragement_all,null);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("hospital");
        mDatabase.keepSynced(true);

        recyclerView =(RecyclerView)view.findViewById(R.id.recycle_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<hospital,BlogAdapter> FirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<hospital, BlogAdapter>
                (hospital.class, R.layout.custom_list,BlogAdapter.class ,mDatabase) {
            @Override
            protected void populateViewHolder(BlogAdapter blogAdapter, final hospital hospi, final int i) {
                final DatabaseReference postRef = getRef(i);

                blogAdapter.setH_name(hospi.getH_name());
                blogAdapter.setImg(hospi.getH_pgflag());

                final String postKey = postRef.getKey();

                blogAdapter.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(),Welcome.class);
                        intent.putExtra("id",postKey);
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(FirebaseRecyclerAdapter);

    }

    public static class BlogAdapter extends  RecyclerView.ViewHolder{
        View mView;
        public BlogAdapter(View iteamView){
            super(iteamView);
            mView = iteamView;
        }
        public void setH_name(String h_name){
            TextView name = mView.findViewById(R.id.name);
            name.setText(h_name);
        }
        public void setImg(String img) {
            ImageView name = mView.findViewById(R.id.himg);
            if (img.equals("g")) {
                name.setImageResource(R.drawable.red);
            } else {
                name.setImageResource(R.drawable.green);
            }
        }
    }

}
