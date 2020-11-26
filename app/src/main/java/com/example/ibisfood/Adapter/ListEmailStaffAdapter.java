package com.example.ibisfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibisfood.Model.PostMessageModel;
import com.example.ibisfood.Model.UserModel;
import com.example.ibisfood.R;

import java.util.List;

public class ListEmailStaffAdapter extends RecyclerView.Adapter<ListEmailStaffAdapter.MyHolder> {

    Context context;
//    List<PostMessageModel> mData;
    List<UserModel> mData;

    public ListEmailStaffAdapter() {
    }

//    public ListEmailStaffAdapter(Context context, List<PostMessageModel> mData) {
//        this.context = context;
//        this.mData = mData;
//    }


    public ListEmailStaffAdapter(Context context, List<UserModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ListEmailStaffAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_list_email_staff, parent, false);
        return new ListEmailStaffAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListEmailStaffAdapter.MyHolder holder, int position) {

//        holder.emailStaff.setText(mData.get(position).getStaffEmailJob());

        holder.emailStaff.setText(mData.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView emailStaff;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            emailStaff = itemView.findViewById(R.id.row_email_staff);
        }
    }
}
