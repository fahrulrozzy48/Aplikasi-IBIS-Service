package com.example.ibisfood.Adapter;


import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibisfood.AdminUI.DataServiceFragment;
import com.example.ibisfood.AdminUI.MessageDetailActivity;
import com.example.ibisfood.Model.PostMessageModel;
import com.example.ibisfood.R;
import com.google.firebase.firestore.DocumentSnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListMessageServiceAdapter extends RecyclerView.Adapter<ListMessageServiceAdapter.MyHolder>  {


    Context context;
    List<PostMessageModel> mData;

    Locale id = new Locale("in","ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm",id);




    public ListMessageServiceAdapter(Context context, List<PostMessageModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_message_sevice_list, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {

//        int timestamp  = (int) mData.get(position).getpTime();

        holder.postEmailUser.setText(mData.get(position).getuEmail());
        holder.postKategoriUser.setText(mData.get(position).getpKategori());
        holder.postStatusPekerjaan.setText(mData.get(position).getStaffStatusJob());

//        long millisecond  = (long) mData.get(position).getTimestamp().getTime();
//        String dateString = DateFormat.format("MM/dd/yyyy", new Date(millisecond)).toString();
//        holder.setTime(dateString);

//        Long millisecond = (long) mData.get(position).getpTime();
//        String dateString = DateFormat.format("dd/MM/yyyy", new Date(millisecond)).toString();
//        holder.setTime(dateString);

        holder.postTimeMessage.setText(simpleDateFormat.format(mData.get(position).getpTime()));


//        holder.postTimeMessage.setText(timestamp);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    class MyHolder extends RecyclerView.ViewHolder{

        TextView postEmailUser, postKategoriUser, postTimeMessage, postStatusPekerjaan;


        public MyHolder(@NonNull View itemView) {
            super(itemView);

            postEmailUser = itemView.findViewById(R.id.row_email);
            postKategoriUser = itemView.findViewById(R.id.row_kategori);
            postTimeMessage = itemView.findViewById(R.id.row_time);
            postStatusPekerjaan = itemView.findViewById(R.id.row_status_pekerjaan);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent postDetailMessageActivity = new Intent(context,MessageDetailActivity.class);
                    int position = getAdapterPosition();

                    postDetailMessageActivity.putExtra("uEmail",mData.get(position).getuEmail());
//                    postDetailMessageActivity.putExtra("pNoRoom",mData.get(position).getpNoRoom());
                    postDetailMessageActivity.putExtra("pTitle",mData.get(position).getpTitle());
                    postDetailMessageActivity.putExtra("pKategori",mData.get(position).getpKategori());
                    postDetailMessageActivity.putExtra("pDescription",mData.get(position).getpDescription());
                    postDetailMessageActivity.putExtra("postKey",mData.get(position).getPostKey());
                    postDetailMessageActivity.putExtra("pImage",mData.get(position).getpImage());

                    //staff get data
                    postDetailMessageActivity.putExtra("staffEmailJob",mData.get(position).getStaffEmailJob());
                    postDetailMessageActivity.putExtra("staffImageJob",mData.get(position).getStaffImageJob());
                    postDetailMessageActivity.putExtra("staffDescriptionJob",mData.get(position).getStaffDescriptionJob());
                    postDetailMessageActivity.putExtra("staffStatusJob",mData.get(position).getStaffStatusJob());

//                    postDetailMessageActivity.putExtra("pTime",simpleDateFormat.format(mData.get(position).getpTime()));

//                    long timestamp  = (long) mData.get(position).getpTime();
                    long timestamp  = (long) mData.get(position).getpTime();
                    postDetailMessageActivity.putExtra("pTime",timestamp) ;
                    context.startActivity(postDetailMessageActivity);




                }
            });


        }

        public void setTime(String date){

            postTimeMessage = itemView.findViewById(R.id.row_time);
            postTimeMessage.setText(date);

        }

    }

    public interface OnItemClickListner{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }





}
