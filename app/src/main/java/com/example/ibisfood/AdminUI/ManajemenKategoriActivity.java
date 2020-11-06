package com.example.ibisfood.AdminUI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ibisfood.Adapter.ListKategoriManagemenAdapter;
import com.example.ibisfood.Adapter.ListPostFeedsAdapter;
import com.example.ibisfood.Model.PostAddFeedsAdminToUserModel;
import com.example.ibisfood.Model.SpinnerModel;
import com.example.ibisfood.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManajemenKategoriActivity extends AppCompatActivity {


    EditText edtDataSpinner;
    Spinner spinnerKategoriManajemen;
    Button btnSimpanSpinner;

    ProgressDialog pd;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    ArrayList<String> arrayList = new ArrayList<>();


    ListKategoriManagemenAdapter postAdapter;
    List<SpinnerModel> spinnerModelList;
    RecyclerView recyclerView;
    Context mContext;


    String PostKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manajemen_kategori);


        spinnerKategoriManajemen= findViewById(R.id.spinner_kategoriManajemen);
        edtDataSpinner = findViewById(R.id.editText_MasukanDataSpinner);
        btnSimpanSpinner = findViewById(R.id.btn_simpanDataSpinner);


        recyclerView = findViewById(R.id.rv_kategori_crud);
        mContext = getBaseContext();


        readData();


        showDataSpinner();

        pd = new ProgressDialog(this);


        btnSimpanSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Send Message...");
                pd.show();

                if (!edtDataSpinner.getText().toString().isEmpty()) {

                    String kategoriSpinner = edtDataSpinner.getText().toString();

                    SpinnerModel postKategori = new SpinnerModel(
                            kategoriSpinner

                    );

                    addPost(postKategori);
                } else {
                    pd.dismiss();
                    showMessage("Please input new category") ;

                }

            }
        });


    }

    private void readData() {
        FirebaseDatabase databaseKategori = FirebaseDatabase.getInstance();
        DatabaseReference databaseReferenceKategori  = databaseKategori.getReference("Kategori");

        databaseReferenceKategori.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                spinnerModelList = new ArrayList<>();
                for (DataSnapshot postsnap: snapshot.getChildren() ){
                    SpinnerModel postFeeds = postsnap.getValue(SpinnerModel.class);

                    postFeeds.setKey(snapshot.getKey());
                    spinnerModelList.add(postFeeds);
                }

                postAdapter = new ListKategoriManagemenAdapter(getBaseContext(),spinnerModelList);
                recyclerView.setAdapter(postAdapter);
//                setClick();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



//    private void setClick() {
//        postAdapter.setOnCallBack(new ListKategoriManagemenAdapter.OnCallBack() {
//            @Override
//            public void onButtonDelete(SpinnerModel kategoriModel) {
//                deleteKategori(kategoriModel);
//            }
//
//            @Override
//            public void onButtonUpdate(SpinnerModel kategoriModel) {
//
//            }
//        });
//
//    }

//    private void deleteKategori(final SpinnerModel kategoriModel) {
//        FirebaseDatabase databaseKategori = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReferenceKategori  = databaseKategori.getReference("Kategori");
//        databaseReferenceKategori.child(kategoriModel.getKey()).removeValue(new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Toast.makeText(ManajemenKategoriActivity.this,"Data dihapus "+kategoriModel.getNameKategori(),Toast.LENGTH_LONG).show();
//            }
//        });
//    }


    private void showMessage(String message) {

        Toast.makeText(ManajemenKategoriActivity.this,message,Toast.LENGTH_LONG).show();

    }

    private void showDataSpinner() {


        databaseReference.child("Kategori").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot item: snapshot.getChildren()){
                    arrayList.add(item.child("nameKategori").getValue(String.class));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(ManajemenKategoriActivity.this,R.layout.style_spinner,arrayList);
                spinnerKategoriManajemen.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addPost(SpinnerModel postKategori) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Kategori").push();



        myRef.setValue(postKategori).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ManajemenKategoriActivity.this,"Data berhasil ditambahkan",Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }


}