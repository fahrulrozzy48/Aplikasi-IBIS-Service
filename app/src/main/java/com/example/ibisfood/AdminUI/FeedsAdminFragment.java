package com.example.ibisfood.AdminUI;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibisfood.Adapter.ListMessageServiceAdapter;
import com.example.ibisfood.Adapter.ListPostFeedsAdapter;
import com.example.ibisfood.Model.PostAddFeedsAdminToUserModel;
import com.example.ibisfood.Model.PostMessageModel;
import com.example.ibisfood.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class FeedsAdminFragment extends Fragment {

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

        View rootView = inflater.inflate(R.layout.fragment_feeds_admin, container, false);


        // ini
        auth = FirebaseAuth.getInstance();
        recyclerView = rootView.findViewById(R.id.rv_postFeeds_list);
        mContext = getContext();


        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab_post_feed_admin);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),AddPostFeedsAdminActivity.class);
                startActivity(intent);
            }
        });



        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("PostFeeds");

        recyclerView.setLayoutManager(lin);
        recyclerView.setHasFixedSize(true);


//        showData();


        return rootView;
    }

//    private void showData() {
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                showListener(snapshot);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getContext(),""+error,Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void showListener(DataSnapshot snapshot) {
//        postAddFeedsAdminToUserModelList = new ArrayList<>();
//        postAddFeedsAdminToUserModelList.clear();
//        for(DataSnapshot ds:snapshot.getChildren()){
//
//            PostAddFeedsAdminToUserModel post = ds.getValue(PostAddFeedsAdminToUserModel.class);
//            postAddFeedsAdminToUserModelList.add(post);
//
//        }
//
//        postAdapter = new ListPostFeedsAdapter(mContext,postAddFeedsAdminToUserModelList);
//        recyclerView.setAdapter(postAdapter);
//    }

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