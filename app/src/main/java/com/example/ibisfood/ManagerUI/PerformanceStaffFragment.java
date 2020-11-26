package com.example.ibisfood.ManagerUI;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ibisfood.Adapter.ListEmailStaffAdapter;
import com.example.ibisfood.Model.PostMessageModel;
import com.example.ibisfood.Model.UserModel;
import com.example.ibisfood.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PerformanceStaffFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    Context mContext;


//    List<PostMessageModel> postMessageModelList;
    List<UserModel> userModelsList;
    ListEmailStaffAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_performance_staff, container, false);


        mContext = getContext();

        recyclerView = rootView.findViewById(R.id.rv_list_leaderboard);
        database = FirebaseDatabase.getInstance();
//        databaseReference = database.getReference("PostMessage");
        databaseReference = database.getReference("User");




        showData1();


        return rootView;
    }

    private void showData1() {

        Query query = databaseReference.orderByChild("UserLevel").equalTo("Staff");

        query.addValueEventListener(new ValueEventListener() {
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
        userModelsList = new ArrayList<>();
        userModelsList.clear();

        for(DataSnapshot ds:snapshot.getChildren()){
            UserModel post = ds.getValue(UserModel.class);
            userModelsList.add(post);
        }

        adapter = new ListEmailStaffAdapter(mContext, userModelsList);
        recyclerView.setAdapter(adapter);

    }
}