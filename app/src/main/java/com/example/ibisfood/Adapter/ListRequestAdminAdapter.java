package com.example.ibisfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibisfood.AdminUI.RequestDetailActivity;
import com.example.ibisfood.ManagerUI.RequestApprovalDetailActivity;
import com.example.ibisfood.Model.PostRequestPermission;
import com.example.ibisfood.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ListRequestAdminAdapter extends RecyclerView.Adapter<ListRequestAdminAdapter.MyHolder> {

    Context context;
    List<PostRequestPermission> mData;


    Locale id = new Locale("in","ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm",id);



    public ListRequestAdminAdapter(Context context, List<PostRequestPermission> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ListRequestAdminAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_request_approval_list, parent, false);
        return new ListRequestAdminAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListRequestAdminAdapter.MyHolder holder, int position) {

        holder.emailAdmin.setText(mData.get(position).getAdminRequestEmail());
        holder.subjekRequest.setText(mData.get(position).getAdminRequestSubjek());
        holder.descRequest.setText(mData.get(position).getAdminRequestDesc());
        holder.answerApproval.setText(mData.get(position).getManagerAnswerStatus());
        holder.timeRequest.setText(simpleDateFormat.format(mData.get(position).getTimeSendRequest()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView emailAdmin, timeRequest, subjekRequest,descRequest, answerApproval;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            emailAdmin = itemView.findViewById(R.id.row_email_request_approval);
            timeRequest = itemView.findViewById(R.id.row_time_request_approval);
            subjekRequest = itemView.findViewById(R.id.row_subjek_request_approval);
            descRequest = itemView.findViewById(R.id.row_desc_request_approval);
            answerApproval = itemView.findViewById(R.id.row_status_request_approval);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent postRequestAdminActivity = new Intent(context, RequestDetailActivity.class);
                    int position = getAdapterPosition();

                    postRequestAdminActivity.putExtra("adminRequestEmail",mData.get(position).getAdminRequestEmail());
                    postRequestAdminActivity.putExtra("adminRequestSubjek",mData.get(position).getAdminRequestSubjek());
                    postRequestAdminActivity.putExtra("adminRequestDesc",mData.get(position).getAdminRequestDesc());
                    postRequestAdminActivity.putExtra("postRequestKey",mData.get(position).getPostRequestKey());

                    //manager
                    postRequestAdminActivity.putExtra("managerAnswerEmail",mData.get(position).getManagerAnswerEmail());
                    postRequestAdminActivity.putExtra("managerAnswerStatus",mData.get(position).getManagerAnswerStatus());

                    long timestamp  = (long) mData.get(position).getTimeSendRequest();
                    postRequestAdminActivity.putExtra("timeSendRequest",timestamp) ;

                    context.startActivity(postRequestAdminActivity);

                }
            });
        }
    }
}
