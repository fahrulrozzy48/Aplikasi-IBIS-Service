package com.example.ibisfood.UserUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibisfood.Model.PostMessageModel;
import com.example.ibisfood.Model.SpinnerModel;
import com.example.ibisfood.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class AddPostServicesActivity extends AppCompatActivity {


    EditText noRoomService,titleService, descService;
    ImageView imageService;
    Date tgl_pengajuan_date, tgl_staff_job_date;
    TextView tanggalPost;
    Button btnPilihTanggalPosting;
    Calendar calendar = Calendar.getInstance();
    Locale id = new Locale("in","ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY",id);

    Spinner spinner;
    ArrayList<String> arrayList = new ArrayList<>();
    PostMessageModel postMessageModel;

    Button btnService;
    private static final int PReqCode = 2;
    private static final int REQUESCODE = 2 ;
    private Uri pickedImgUri = null;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    private static final int CAMERA_IMAGE_CODE = 200;


//    Uri image_uri = null;
//    private static final int GALLERY_IMAGE_CODE = 100;
//    private static final int CAMERA_IMAGE_CODE = 200;
    ProgressDialog pd;
//    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post_services);


//        permission();
        noRoomService = findViewById(R.id.no_room_service);
        titleService = findViewById(R.id.title_service);
        imageService = findViewById(R.id.image_service);
        descService =  findViewById(R.id.description_service);
        btnService = findViewById(R.id.btn_send_service);
        tanggalPost = findViewById(R.id.date_pilihTanggal);
        btnPilihTanggalPosting = findViewById(R.id.btn_pilihTanggal);



        //ini spinner
        spinner = findViewById(R.id.spinner_kategori);
        postMessageModel = new PostMessageModel();
        showDataSpinner();


        // ini

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        pd = new ProgressDialog(this);


        //waktu

        btnPilihTanggalPosting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddPostServicesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calendar.set(year,month,dayOfMonth);
                        tanggalPost.setText(simpleDateFormat.format(calendar.getTime()));
                        tgl_pengajuan_date = calendar.getTime();



                    }
                }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


// versi 1
//        pd = new ProgressDialog(this);
//
//        auth = FirebaseAuth.getInstance();
//
//
//        //ketika klik camera untuk ambil gambar
//        imageService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imagePickDialog();
//            }
//        });

        //ketika klik send untuk kirim data
//        btnService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String no_room = noRoomService.getText().toString();
//                String title = titleService.getText().toString();
//                String description = descService.getText().toString();
//
//                if (TextUtils.isEmpty(no_room)){
//                    noRoomService.setError("No Room is required");
//                } else if (TextUtils.isEmpty(title)){
//                    titleService.setError("Title is required");
//                } else if (TextUtils.isEmpty(description)){
//                    descService.setError("Description is required");
//                } else {
//                    uploadData(no_room,title,description);
//                }
//            }
//        });
// akhir versi 1


    //versi 2

        setupImageClick();




        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd.setMessage("Send Message...");
                pd.show();

                if (
                        !noRoomService.getText().toString().isEmpty() &&
                        !titleService.getText().toString().isEmpty()
                        && !descService.getText().toString().isEmpty()
                        && imageService != null ) {


//                    Bitmap bitmap = ((BitmapDrawable)imageService.getDrawable()).getBitmap();
//                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
//                    byte[] data = byteArrayOutputStream.toByteArray();


                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("message_image");
                    final StorageReference imageFilePath = storageReference.child(pickedImgUri.getLastPathSegment());
                    imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageDownlaodLink = uri.toString();
                                    String no_room = noRoomService.getText().toString();
                                    String title = titleService.getText().toString();
                                     String description = descService.getText().toString();
                                     String kategori = postMessageModel.setpKategori(spinner.getSelectedItem().toString());

                                     String namaStaffJob = "";
                                     String imageStaffJob = "";
                                     String descJobStaffJob = "";
                                     String statusStaffJob = "";

                                     

                                    // create post Object

                                    PostMessageModel post = new PostMessageModel(
//                                            currentUser.getEmail(),
//                                            title,
//                                            description,
//                                            imageDownlaodLink,
//                                            no_room
                                            currentUser.getEmail(),
                                            title,
                                            //spinner kategori
                                            kategori,
                                            description,
                                            imageDownlaodLink,
                                            no_room,
                                            tgl_pengajuan_date.getTime(),
                                            namaStaffJob,
                                            imageStaffJob,
                                            descJobStaffJob,
                                            statusStaffJob
//                                            tgl_staff_job_date.getTime()
                                    );


                                    // Add post to firebase database
                                    addPost(post);



                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // something goes wrong uploading picture

                                    pd.dismiss();
                                    showMessage(e.getMessage());



                                }
                            });


                        }
                    });


                }else {
                    pd.dismiss();
                    showMessage("Please verify all input fields, Date and choose Post Image") ;

                }

            }
        });


    }

    private void showDataSpinner() {
        DatabaseReference databaseReferences = FirebaseDatabase.getInstance().getReference();


        databaseReferences.child("Kategori").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot item: snapshot.getChildren()){
                    arrayList.add(item.child("nameKategori").getValue(String.class));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(AddPostServicesActivity.this,R.layout.style_spinner,arrayList);
                spinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addPost(PostMessageModel post) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("PostMessage").push();



        // get post unique ID and upadte post key
        String key = myRef.getKey();
        post.setPostKey(key);


        // add post data to firebase database

        myRef.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                showMessage("Message Successfully");
                startActivity(new Intent(AddPostServicesActivity.this,MainActivity.class));
            }
        });
    }

    private void showMessage(String message) {

        Toast.makeText(AddPostServicesActivity.this,message,Toast.LENGTH_LONG).show();

    }


    //versi 2
    private void setupImageClick() {

        imageService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAndRequestForPermission();
            }
        });
    }

    private void openGallery() {
        //TODO: open gallery intent and wait for user to pick an image !

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
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

    private void checkAndRequestForPermission() {


        if ((ContextCompat.checkSelfPermission(AddPostServicesActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(AddPostServicesActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(AddPostServicesActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(AddPostServicesActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(AddPostServicesActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            } else if (ActivityCompat.shouldShowRequestPermissionRationale(AddPostServicesActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Toast.makeText(AddPostServicesActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            } else if (ActivityCompat.shouldShowRequestPermissionRationale(AddPostServicesActivity.this, Manifest.permission.CAMERA)) {

                Toast.makeText(AddPostServicesActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            }

            else
            {
                ActivityCompat.requestPermissions(AddPostServicesActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                        PReqCode);
            }

        }
        else
//            openGallery();

            imagePickDialog();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null ) {

            // the user has successfully picked an image
            // we need to save its reference to a Uri variable
            pickedImgUri = data.getData() ;
            imageService.setImageURI(pickedImgUri);

        } else if ( requestCode == CAMERA_IMAGE_CODE){
            imageService.setImageURI(pickedImgUri);
        }


    }






// versi 1
//    private void uploadData(final String no_room, final String title, final String description) {
//
//        pd.setMessage("Send Message...");
//        pd.show();
//
//        final String timeStamp = String.valueOf(System.currentTimeMillis());
//        String filepath = "Service User/"+"post_"+timeStamp;
//
//        if (imageService.getDrawable() != null){
////            Bitmap bitmap = ((BitmapDrawable)imageService.getDrawable()).getBitmap();
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
////            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//            byte[] data = byteArrayOutputStream.toByteArray();
//
//
//            //create reference firebase of storage
//            final StorageReference reference = FirebaseStorage.getInstance().getReference().child(filepath);
//            reference.putBytes(data)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
//                            while (!uriTask.isSuccessful());
//
//                            String downloadUri = uriTask.getResult().toString();
//
//                            if (uriTask.isSuccessful()){
//                                //uri tersimpan di database publish
//
//                                //upload data ke direbase database
//                                FirebaseUser user = auth.getCurrentUser();
//
//
//                                HashMap<String, Object> hashMap = new HashMap<>();
//
//                                hashMap.put("uid",user.getUid());
//                                hashMap.put("uEmail",user.getEmail());
//                                hashMap.put("pid",timeStamp);
//                                hashMap.put("pNoRoom",no_room);
//                                hashMap.put("pTitle",title);
//                                hashMap.put("pImage",downloadUri);
//                                hashMap.put("pDescription",description);
//                                hashMap.put("pTime",timeStamp);
//
//                                //push ke firebase database
//                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("ServiceMessage");
//
//                                ref.child(timeStamp).setValue(hashMap)
//                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void aVoid) {
//
//                                                pd.dismiss();
//                                                Toast.makeText(AddPostServicesActivity.this, "Publising Message Now", Toast.LENGTH_SHORT).show();
//                                                noRoomService.setText("");
//                                                titleService.setText("");
//                                                imageService.setImageURI(null);
//                                                image_uri = null;
//
//
//
//                                                //ketika udah di upload akan pindah ke halaman utama
//                                                startActivity(new Intent(AddPostServicesActivity.this, MainActivity.class));
//                                            }
//                                        }).addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        pd.dismiss();
//                                        Toast.makeText(AddPostServicesActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//
//                            }
//
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(AddPostServicesActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                    pd.dismiss();
//                }
//            });
//        } else if(imageService.getDrawable() == null){
//
//        }
//
//    }
//
//    private void imagePickDialog() {
//        String[] option = {"Camera","Gallery"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//
//        builder.setItems(option, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (which == 0){
//                    cameraPick();
//                }
//                if (which == 1){
//                    galleryPick();
//                }
//            }
//        });
//
//        builder.create().show();
//    }
//
//    private void galleryPick() {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent,GALLERY_IMAGE_CODE);
//    }
//
//    private void cameraPick() {
//        ContentValues contentValues= new ContentValues();
//        contentValues.put(MediaStore.Images.Media.TITLE, "Temp No Room");
//        contentValues.put(MediaStore.Images.Media.TITLE, "Temp Pick");
//        contentValues.put(MediaStore.Images.Media.TITLE, "Temp Desc");
//        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
//        startActivityForResult(intent, CAMERA_IMAGE_CODE);
//    }
//
//    private void permission(){
//        Dexter.withContext(this)
//                .withPermissions(
//                        Manifest.permission.CAMERA,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE
//                ).withListener(new MultiplePermissionsListener() {
//            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
//
//            }
//            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
//        }).check();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (resultCode == RESULT_OK){
//            if (requestCode == CAMERA_IMAGE_CODE){
////                image_uri = data.getData();
//                imageService.setImageURI(image_uri);
//            }
//            if (requestCode == GALLERY_IMAGE_CODE){
//                image_uri = data.getData();
//                imageService.setImageURI(image_uri);
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
// akhir versi 1


}