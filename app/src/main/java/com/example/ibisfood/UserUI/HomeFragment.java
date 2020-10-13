package com.example.ibisfood.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.ibisfood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class HomeFragment extends Fragment {

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4};

    private FirebaseAuth mAuth;

    TextView identitas;

    FirebaseUser currentUser ;

    CardView webview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container,false);

        carouselView = (CarouselView) rootView.findViewById(R.id.carousel);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);

        identitas = rootView.findViewById(R.id.user_email_identity);
        webview = rootView.findViewById(R.id.btn_webview_ibis);

        mAuth = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();

        identitas.setText(currentUser.getEmail());

        webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),WebviewIbisHotelActivity.class));
            }
        });

        return rootView;

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

}
