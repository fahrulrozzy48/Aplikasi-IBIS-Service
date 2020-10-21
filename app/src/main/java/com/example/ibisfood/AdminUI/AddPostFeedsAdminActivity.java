package com.example.ibisfood.AdminUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibisfood.Model.PostAddFeedsAdminToUserModel;
import com.example.ibisfood.Model.PostMessageModel;
import com.example.ibisfood.R;
import com.example.ibisfood.UserUI.AddPostServicesActivity;
import com.example.ibisfood.UserUI.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddPostFeedsAdminActivity extends AppCompatActivity {

    EditText descAddPostFeeds;
    ImageView imageAddPostFeeds;
    TextView tanggalPost;
    Button btnPilihTanggalPosting;
    Calendar calendar = Calendar.getInstance();
    Locale id = new Locale("in","ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY",id);

    ArrayList<String> arrayList = new ArrayList<>();
    ProgressDialog pd;

    Button btnAddPostFeeds;
    private static final int PReqCode = 2;
    private static final int REQUESCODE = 2 ;
    private Uri pickedImgUri = null;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    private static final int CAMERA_IMAGE_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post_feeds_admin);


        //ini
        imageAddPostFeeds = findViewById(R.id.image_addPostFeeds);
        descAddPostFeeds =  findViewById(R.id.description_addPostFeeds);
        btnAddPostFeeds = findViewById(R.id.btn_send_addPostFeeds);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        pd = new ProgressDialog(this);



        //set up image
        setupImageClick();




        //send post
        btnAddPostFeeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd.setMessage("Please Wait...");
                pd.show();


                if ( imageAddPostFeeds != null && !descAddPostFeeds.getText().toString().isEmpty()){


                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("feeds_image");
                    final StorageReference imageFilePath = storageReference.child(pickedImgUri.getLastPathSegment());
                    imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {


                                    String imageDownloadLink = uri.toString();
                                    String description = descAddPostFeeds.getText().toString();

                                    PostAddFeedsAdminToUserModel post = new PostAddFeedsAdminToUserModel(
                                            currentUser.getEmail(),
                                            imageDownloadLink,
                                            description
                                    );

                                    addPost(post);


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    pd.dismiss();
                                    showMessage(e.getMessage());
                                }
                            });


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            showMessage(e.getMessage());
                        }
                    });

                } else {
                    pd.dismiss();
                    showMessage("Please verify all input fields, Date and choose Post Image") ;

                }
            }
        });




    }

    private void addPost(PostAddFeedsAdminToUserModel post) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("PostFeeds").push();

        // get post unique ID and upadte post key
        String key = myRef.getKey();
        post.setPostKey(key);


        // add post data to firebase database

        myRef.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                showMessage("Send Successfully");
                startActivity(new Intent(AddPostFeedsAdminActivity.this, AdminActivity.class));
            }
        });


    }





    private void showMessage(String message) {
        Toast.makeText(AddPostFeedsAdminActivity.this,message,Toast.LENGTH_LONG).show();
    }

    private void setupImageClick() {
        imageAddPostFeeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAndRequestForPermission();
            }
        });
    }

    private void checkAndRequestForPermission() {
        if ((ContextCompat.checkSelfPermission(AddPostFeedsAdminActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(AddPostFeedsAdminActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(AddPostFeedsAdminActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(AddPostFeedsAdminActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(AddPostFeedsAdminActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            } else if (ActivityCompat.shouldShowRequestPermissionRationale(AddPostFeedsAdminActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Toast.makeText(AddPostFeedsAdminActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            } else if (ActivityCompat.shouldShowRequestPermissionRationale(AddPostFeedsAdminActivity.this, Manifest.permission.CAMERA)) {

                Toast.makeText(AddPostFeedsAdminActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            }

            else
            {
                ActivityCompat.requestPermissions(AddPostFeedsAdminActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                        PReqCode);
            }

        }
        else

            imagePickDialog();
    }

    private void imagePickDialog() {
        String[] option = {"Camera","Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    cameraPick();
                }
                if (which == 1){
                    openGallery();
                }
            }
        });

        builder.create().show();
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
    }

    private void cameraPick() {
        ContentValues contentValues= new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp No Room");
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp Pick");
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp Desc");
        pickedImgUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pickedImgUri);
        startActivityForResult(intent, CAMERA_IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null ) {

            // the user has successfully picked an image
            // we need to save its reference to a Uri variable
            pickedImgUri = data.getData() ;
            imageAddPostFeeds.setImageURI(pickedImgUri);

        } else if ( requestCode == CAMERA_IMAGE_CODE){
            imageAddPostFeeds.setImageURI(pickedImgUri);
        }


    }
}