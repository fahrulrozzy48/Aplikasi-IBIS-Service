package com.example.ibisfood.ManagerUI;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ibisfood.Adapter.ListApprovalAdapter;
import com.example.ibisfood.Adapter.ListPostReportAdapter;
import com.example.ibisfood.Model.PostReportFromAdminToManagerModel;
import com.example.ibisfood.Model.PostRequestPermission;
import com.example.ibisfood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class RequestAnswerFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;

    ListApprovalAdapter posAdapter;
    List<PostRequestPermission> postRequestPermissionList;

    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayoutManager lin = new LinearLayoutManager(getContext());
        lin.setStackFromEnd(true);
        lin.setReverseLayout(true);

        View rootView = inflater.inflate(R.layout.fragment_request_answer, container, false);

        mContext = getContext();
        auth = FirebaseAuth.getInstance();
        recyclerView = rootView.findViewById(R.id.rv_post_request_list);


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("PostRequest");


        recyclerView.setLayoutManager(lin);
        recyclerView.setHasFixedSize(true);

        showData();

        return rootView;
    }

    private void showData() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                showListener(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),""+error,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showListener(DataSnapshot snapshot) {
        postRequestPermissionList = new ArrayList<>();
        postRequestPermissionList.clear();
        for(DataSnapshot ds:snapshot.getChildren()){

            PostRequestPermission post = ds.getValue(PostRequestPermission.class);
            postRequestPermissionList.add(post);

        }

        posAdapter = new ListApprovalAdapter(mContext,postRequestPermissionList);
        recyclerView.setAdapter(posAdapter);
    }
}