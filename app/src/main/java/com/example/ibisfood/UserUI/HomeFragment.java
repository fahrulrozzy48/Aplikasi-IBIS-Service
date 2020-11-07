package com.example.ibisfood.UserUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.ibisfood.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class HomeFragment extends Fragment {

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4};

    private FirebaseAuth mAuth;

    TextView identitas;

    FirebaseUser currentUser;

    CardView btnTutorial;
    BottomSheetBehavior sheetBehavior, sheetBehavior2;
    BottomSheetDialog sheetDialog, sheetDialog2;
    View bottom_sheet, bottom_sheet2;

    CardView webview;

    private View view,viewDua;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        carouselView = (CarouselView) rootView.findViewById(R.id.carousel);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);

        identitas = rootView.findViewById(R.id.user_email_identity);
        webview = rootView.findViewById(R.id.btn_webview_ibis);

        btnTutorial = rootView.findViewById(R.id.btn_tutorial_pengguna);
        bottom_sheet = rootView.findViewById(R.id.bottom_sheet);
        bottom_sheet2 = rootView.findViewById(R.id.bottom_sheet2);
        sheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        sheetBehavior2 = BottomSheetBehavior.from(bottom_sheet);

        mAuth = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();

        identitas.setText(currentUser.getEmail());


        view = getLayoutInflater().inflate(R.layout.tutorial_buttom_sheet_dialog,null);
        viewDua = getLayoutInflater().inflate(R.layout.tutorial_service_bottom_sheet_dialog, null);

        webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WebviewIbisHotelActivity.class));
            }
        });


        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog();
            }
        });

        return rootView;

    }

    private void showBottomDialog() {


        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        (view.findViewById(R.id.btn_tutorial1_service)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (viewDua.getParent() != null){
                    ((ViewGroup)viewDua.getParent()).removeView(viewDua);
                }

                if (sheetBehavior2.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior2.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }

                sheetDialog2 = new BottomSheetDialog(getContext());
                sheetDialog2.setContentView(viewDua);

                sheetDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                sheetDialog2.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);


                sheetDialog2.show();
                sheetDialog.dismiss();
                sheetDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        sheetDialog2 = null;
                    }
                });


                (viewDua.findViewById(R.id.tutorial_service_btn_back)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (view.getParent() != null){
                            ((ViewGroup)view.getParent()).removeView(view);
                        }

                        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }

                        sheetDialog = new BottomSheetDialog(getContext());

                        sheetDialog.setContentView(view);

                        sheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        sheetDialog.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
                        sheetDialog.getWindow().getAttributes().gravity = Gravity.BOTTOM;

                        sheetDialog.show();
                        sheetDialog2.dismiss();
                    }
                });

                (viewDua.findViewById(R.id.tutorial_service_btn_keluar)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sheetDialog2.dismiss();
                    }
                });
            }
        });

        sheetDialog = new BottomSheetDialog(getContext());
        sheetDialog.setContentView(view);
//        if () {
//        Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
////            sheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        sheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        sheetDialog.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        sheetDialog.getWindow().getAttributes().gravity = Gravity.BOTTOM;
//        }

        sheetDialog.show();
        sheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                sheetDialog = null;
            }
        });

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

}
