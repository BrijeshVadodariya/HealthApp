package com.example.healthapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragement_private extends Fragment {

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
        FirebaseRecyclerAdapter<hospital, Fragement_All.BlogAdapter> FirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<hospital, Fragement_All.BlogAdapter>
                (hospital.class, R.layout.custom_list, Fragement_All.BlogAdapter.class ,mDatabase) {
            @Override
            protected void populateViewHolder(final Fragement_All.BlogAdapter blogAdapter, final hospital hospi, final int i) {
                final DatabaseReference postRef = getRef(i);
                final String postKey = postRef.getKey();
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String hname = dataSnapshot.child(postKey).child("h_pgflag").getValue().toString();
                        if(hname.equals("p")){
                            blogAdapter.setH_name(hospi.getH_name());
                            blogAdapter.setImg(hospi.getH_pgflag());
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

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
}
