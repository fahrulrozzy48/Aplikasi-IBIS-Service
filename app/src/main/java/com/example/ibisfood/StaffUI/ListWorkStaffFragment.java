package com.example.ibisfood.StaffUI;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibisfood.Adapter.ListJobListAdapter;
import com.example.ibisfood.Adapter.ListMessageServiceAdapter;
import com.example.ibisfood.Model.PostMessageModel;
import com.example.ibisfood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ListWorkStaffFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;

    ListJobListAdapter posAdapter;
    List<PostMessageModel> postMessageModelList;

    TextView inputMaksimal, inputMinimal;
    CardView btnSampaiTanggal, btnDariTanggal;
    Button btnFilterTanggal;
    Calendar calendar = Calendar.getInstance();
    Locale id = new Locale("in","ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY",id);
    Context mContext;
    Date date_minimal;
    Date date_maksimal;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        LinearLayoutManager lin = new LinearLayoutManager(getContext());
        lin.setStackFromEnd(true);
        lin.setReverseLayout(true);
        View rootView = inflater.inflate(R.layout.fragment_list_work_staff, container, false);

        postMessageModelList = new ArrayList<>();
        postMessageModelList.clear();


        auth = FirebaseAuth.getInstance();
        recyclerView = rootView.findViewById(R.id.rv_joblist_list);

        mContext = getContext();
        inputMinimal = rootView.findViewById(R.id.date_dariTanggal);
        inputMaksimal = rootView.findViewById(R.id.date_sampaiTanggal);
        btnDariTanggal = rootView.findViewById(R.id.CV_dariTanggal);
        btnSampaiTanggal = rootView.findViewById(R.id.CV_sampaiTanggal);
        btnFilterTanggal = rootView.findViewById(R.id.btn_filterDate);


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("PostMessage");

        recyclerView.setLayoutManager(lin);
        recyclerView.setHasFixedSize(true);

        btnDariTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calendar.set(year,month,dayOfMonth);
                        inputMinimal.setText(simpleDateFormat.format(calendar.getTime()));
                        date_minimal = calendar.getTime();

                        String input1 = inputMinimal.getText().toString();
                        String input2 = inputMaksimal.getText().toString();

                        if(input1.isEmpty() && input2.isEmpty()){
                            btnFilterTanggal.setEnabled(false);
                        } else if(!input1.isEmpty() && input2.isEmpty()){
                            btnFilterTanggal.setEnabled(false);
                        } else if(input1.isEmpty() && !input2.isEmpty()){
                            btnFilterTanggal.setEnabled(false);
                        }else {
                            if (date_minimal.getTime() > date_maksimal.getTime()){
                                Toast.makeText(getContext(),"Tanggal tidak falid",Toast.LENGTH_SHORT).show();
                                btnFilterTanggal.setEnabled(false);
                            } else {
                                btnFilterTanggal.setEnabled(true);
                            }
                        }
                    }
                }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });


        btnSampaiTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calendar.set(year,month,dayOfMonth);
                        inputMaksimal.setText(simpleDateFormat.format(calendar.getTime()));
                        date_maksimal = calendar.getTime();



                        String input1 = inputMaksimal.getText().toString();
                        String input2 = inputMinimal.getText().toString();

                        if(input1.isEmpty() && input2.isEmpty()){
                            btnFilterTanggal.setEnabled(false);
                        } else if(!input1.isEmpty() && input2.isEmpty()) {
                            btnFilterTanggal.setEnabled(false);
                        } else if(input1.isEmpty() && !input2.isEmpty()){
                            btnFilterTanggal.setEnabled(false);
                        } else {
                            if (date_maksimal.getTime() < date_minimal.getTime()){
                                Toast.makeText(getContext(),"Tanggal tidak falid",Toast.LENGTH_SHORT).show();
                                btnFilterTanggal.setEnabled(false);
                            } else {
                                btnFilterTanggal.setEnabled(true);
                            }
                        }
                    }
                }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        });


        btnFilterTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = database.getReference().child("PostMessage").orderByChild("pTime").startAt(date_minimal.getTime()).endAt(date_maksimal.getTime());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        showListener(snapshot);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(),"Data tidak ditemukan",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        showData();


        return rootView;
    }

    private void showListener(DataSnapshot snapshot) {
        postMessageModelList = new ArrayList<>();
        postMessageModelList.clear();
        for(DataSnapshot ds:snapshot.getChildren()){

            PostMessageModel post = ds.getValue(PostMessageModel.class);
            postMessageModelList.add(post);

        }

        posAdapter = new ListJobListAdapter(mContext,postMessageModelList);
        recyclerView.setAdapter(posAdapter);
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
}