package com.example.ibisfood.UserUI;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibisfood.Adapter.ListPostFeedsAdapter;
import com.example.ibisfood.Model.PostAddFeedsAdminToUserModel;
import com.example.ibisfood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FeedsUserFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;

    ListPostFeedsAdapter postAdapter;
    List<PostAddFeedsAdminToUserModel> postAddFeedsAdminToUserModelList;

    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayoutManager lin = new LinearLayoutManager(getContext());
        lin.setStackFromEnd(true);
        lin.setReverseLayout(true);

        View rootView = inflater.inflate(R.layout.fragment_feeds_user, container, false);

        // ini
        auth = FirebaseAuth.getInstance();
        recyclerView = rootView.findViewById(R.id.rv_postFeedsUser_list);
        mContext = getContext();


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("PostFeeds");

        recyclerView.setLayoutManager(lin);
        recyclerView.setHasFixedSize(true);




        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                postAddFeedsAdminToUserModelList = new ArrayList<>();
                for (DataSnapshot postsnap: snapshot.getChildren() ){
                    PostAddFeedsAdminToUserModel postFeeds = postsnap.getValue(PostAddFeedsAdminToUserModel.class);
                    postAddFeedsAdminToUserModelList.add(postFeeds);
                }

                postAdapter = new ListPostFeedsAdapter(getActivity(),postAddFeedsAdminToUserModelList);
                recyclerView.setAdapter(postAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}