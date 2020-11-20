package com.example.ibisfood.AdminUI;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibisfood.Adapter.ListMessageServiceAdapter;
import com.example.ibisfood.Model.PostMessageModel;
import com.example.ibisfood.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DataServiceFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference, databaseRef2;
    RecyclerView recyclerView;

    ListMessageServiceAdapter posAdapter;
    List<PostMessageModel> postMessageModelList;
//    List<SpinnerModel> spinnerModelList;

    TextView inputMaksimal, inputMinimal;
    CardView btnSampaiTanggal, btnDariTanggal;
    Button btnFilterTanggal;
    Calendar calendar = Calendar.getInstance();
    Locale id = new Locale("in", "ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY", id);
    Context mContext;
    Date date_minimal;
    Date date_maksimal;


    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout shimmerLayout;


    TextView countMessage;
    int sum;


    Spinner spinnerKategoriMessage;
    PostMessageModel mData;
    String[] dataKategori = {"", "Makanan", "Minuman", "Wifi", "Elektronik", "Kesehatan", "Kebersihan"};
    ArrayList<String> arrayListKategori;
    ArrayAdapter<String> arrayAdapterKategori;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayoutManager lin = new LinearLayoutManager(getContext());
        lin.setStackFromEnd(true);
        lin.setReverseLayout(true);

        View rootView = inflater.inflate(R.layout.fragment_data_service, container, false);
        // Inflate the layout for this fragment

        auth = FirebaseAuth.getInstance();
        recyclerView = rootView.findViewById(R.id.rv_message_service_list);

        mContext = getContext();
        inputMinimal = rootView.findViewById(R.id.date_dariTanggal);
        inputMaksimal = rootView.findViewById(R.id.date_sampaiTanggal);
//        btnMinimal = rootView.findViewById(R.id.btn_dariTanggal);
//        btnMaksimal = rootView.findViewById(R.id.btn_sampaiTanggal);
        btnDariTanggal = rootView.findViewById(R.id.CV_dariTanggal);
        btnSampaiTanggal = rootView.findViewById(R.id.CV_sampaiTanggal);
        btnFilterTanggal = rootView.findViewById(R.id.btn_filterDate);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("PostMessage");
        mData = new PostMessageModel();

        recyclerView.setLayoutManager(lin);
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);


//        loadPostMessage();


        countMessage = rootView.findViewById(R.id.data_count_messageService);


        //spinner searchable
        spinnerKategoriMessage = rootView.findViewById(R.id.spinner_kategori_message_list);


        arrayListKategori = new ArrayList<>(Arrays.asList(dataKategori));
        arrayAdapterKategori = new ArrayAdapter<>(mContext, R.layout.style_spinner, arrayListKategori);
        spinnerKategoriMessage.setAdapter(arrayAdapterKategori);

        showDataKategori();


//        database.getReference("Kategori").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                spinnerModelList = new ArrayList<>();
//                for (DataSnapshot ds:snapshot.getChildren()){
//
//                    SpinnerModel post = ds.getValue(SpinnerModel.class);
//                    spinnerModelList.add(post);
//
//                }
//
//                iFirebaseLoadDone.onFirebaseLoadSuccess(spinnerModelList);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                iFirebaseLoadDone.onFirebaseLoadFailed(error.getMessage());
//            }
//        });


        //shimmer
        shimmerFrameLayout = rootView.findViewById(R.id.shimmer_view_container_data_message_admin);
        shimmerLayout = rootView.findViewById(R.id.shimmer_layout_data_message_admin);

        btnDariTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calendar.set(year, month, dayOfMonth);
                        inputMinimal.setText(simpleDateFormat.format(calendar.getTime()));
                        date_minimal = calendar.getTime();

                        String input1 = inputMinimal.getText().toString();
                        String input2 = inputMaksimal.getText().toString();

                        if (input1.isEmpty() && input2.isEmpty()) {
                            btnFilterTanggal.setEnabled(false);
                        } else if (!input1.isEmpty() && input2.isEmpty()) {
                            btnFilterTanggal.setEnabled(false);
                        } else if (input1.isEmpty() && !input2.isEmpty()) {
                            btnFilterTanggal.setEnabled(false);
                        } else {
                            if (date_minimal.getTime() > date_maksimal.getTime()) {
                                Toast.makeText(getContext(), "Tanggal tidak falid", Toast.LENGTH_SHORT).show();
                                btnFilterTanggal.setEnabled(false);
                            } else {
                                btnFilterTanggal.setEnabled(true);
                            }
                        }
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });


        btnSampaiTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calendar.set(year, month, dayOfMonth);
                        inputMaksimal.setText(simpleDateFormat.format(calendar.getTime()));
                        date_maksimal = calendar.getTime();


                        String input1 = inputMaksimal.getText().toString();
                        String input2 = inputMinimal.getText().toString();

                        if (input1.isEmpty() && input2.isEmpty()) {
                            btnFilterTanggal.setEnabled(false);
                        } else if (!input1.isEmpty() && input2.isEmpty()) {
                            btnFilterTanggal.setEnabled(false);
                        } else if (input1.isEmpty() && !input2.isEmpty()) {
                            btnFilterTanggal.setEnabled(false);
                        } else {
                            if (date_maksimal.getTime() < date_minimal.getTime()) {
                                Toast.makeText(getContext(), "Tanggal tidak falid", Toast.LENGTH_SHORT).show();
                                btnFilterTanggal.setEnabled(false);
                            } else {
                                btnFilterTanggal.setEnabled(true);
                            }
                        }
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        });


        btnFilterTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Query query = database.getReference().child("PostMessage").orderByChild("pTime").startAt(date_minimal.getTime()).endAt(date_maksimal.getTime());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        showListener(snapshot);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        showData();


        return rootView;
    }

    private void showDataKategori() {
        spinnerKategoriMessage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

                if (position == 0) {
                    showData();

                } else {
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            postMessageModelList = new ArrayList<>();
                            postMessageModelList.clear();

                            final String s = spinnerKategoriMessage.getSelectedItem().toString();
                            int sumKategori = 0;

                            for (DataSnapshot ds : snapshot.getChildren()) {
                                PostMessageModel post = ds.getValue(PostMessageModel.class);

                                if (post.getpKategori().equals(s)) {
                                    postMessageModelList.add(post);
                                    ++sumKategori;
                                }
                            }
                            countMessage.setText(Integer.toString(sumKategori));
                            posAdapter = new ListMessageServiceAdapter(mContext, postMessageModelList);
                            recyclerView.setAdapter(posAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void showData() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                showListener(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showListener(DataSnapshot snapshot) {
        postMessageModelList = new ArrayList<>();
        postMessageModelList.clear();
        int sumKategori = 0;

        for (DataSnapshot ds : snapshot.getChildren()) {

            PostMessageModel post = ds.getValue(PostMessageModel.class);
            postMessageModelList.add(post);


            ++sumKategori;

//            sum = (int) snapshot.getChildrenCount();
//            countMessage.setText(Integer.toString(sum));

        }

        countMessage.setText(Integer.toString(sumKategori));
        posAdapter = new ListMessageServiceAdapter(mContext, postMessageModelList);
        recyclerView.setAdapter(posAdapter);
        shimmerFrameLayout.stopShimmer();
        shimmerLayout.setVisibility(View.GONE);
    }

    @Override
    public void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();

    }

    @Override
    public void onResume() {
        shimmerFrameLayout.startShimmer();
        super.onResume();
    }

//    @Override
//    public void onFirebaseLoadSuccess(List<SpinnerModel> spinnerList) {
//        spinnerModelList = spinnerList;
//
//        //get all name kategori
//        List<String> kategori_list = new ArrayList<>();
//        for(SpinnerModel mesage:spinnerList){
//            kategori_list.add(mesage.getNameKategori());
//
//        }
//
//
//
//        ArrayAdapter<String> adapterKategori = new ArrayAdapter<>(mContext,R.layout.style_spinner,kategori_list);
//        spinnerKategoriMessage.setAdapter(adapterKategori);
//
//
//    }

//    @Override
//    public void onFirebaseLoadFailed(String message) {
//
//    }


    //    @Override
//    public void onStart() {
//        super.onStart();
//
//
//    }

    //    private void loadPostMessage() {
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                postMessageModelList.clear();
//                for(DataSnapshot ds: snapshot.getChildren()){
//                    PostMessageModel postModel = ds.getValue(PostMessageModel.class);
//                    postMessageModelList.add(postModel);
//                    adapter = new ListMessageServiceAdapter(getContext(),postMessageModelList);
//                    recyclerView.setAdapter(adapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getContext(),""+error,Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


}