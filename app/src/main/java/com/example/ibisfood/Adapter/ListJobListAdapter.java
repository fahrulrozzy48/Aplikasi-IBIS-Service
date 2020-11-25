package com.example.ibisfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibisfood.AdminUI.MessageDetailActivity;
import com.example.ibisfood.Model.PostMessageModel;
import com.example.ibisfood.R;
import com.example.ibisfood.StaffUI.JobDetailActivity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ListJobListAdapter extends RecyclerView.Adapter<ListJobListAdapter.MyHolder> {

    Context context;
    List<PostMessageModel> mData;

    Locale id = new Locale("in","ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm",id);


    public ListJobListAdapter(Context context, List<PostMessageModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ListJobListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_job_list_staff, parent, false);
        return new ListJobListAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListJobListAdapter.MyHolder holder, int position) {

        holder.postEmailKamarClient.setText(mData.get(position).getuEmail());
        holder.postKategoriClient.setText(mData.get(position).getpKategori());
        holder.postStatusJob.setText(mData.get(position).getStaffStatusJob());
        holder.postTimeMessageClient.setText(simpleDateFormat.format(mData.get(position).getpTime()));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView postEmailKamarClient, postKategoriClient, postTimeMessageClient, postStatusJob;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            postEmailKamarClient = itemView.findViewById(R.id.row_emailkamar_staff);
            postKategoriClient = itemView.findViewById(R.id.row_kategori_staff);
            postTimeMessageClient= itemView.findViewById(R.id.row_time_staff);
            postStatusJob = itemView.findViewById(R.id.row_status_pekerjaan_staff);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent postJobDetailActivity = new Intent(context, JobDetailActivity.class);
                    int position = getAdapterPosition();

                    postJobDetailActivity.putExtra("uEmail",mData.get(position).getuEmail());
//                    postJobDetailActivity.putExtra("pNoRoom",mData.get(position).getpNoRoom());
                    postJobDetailActivity.putExtra("pTitle",mData.get(position).getpTitle());
                    postJobDetailActivity.putExtra("pKategori",mData.get(position).getpKategori());
                    postJobDetailActivity.putExtra("pDescription",mData.get(position).getpDescription());
                    postJobDetailActivity.putExtra("postKey",mData.get(position).getPostKey());
//                    postJobDetailActivity.putExtra("pImage",mData.get(position).getpImage());

                    //staff get data
                    postJobDetailActivity.putExtra("staffEmailJob",mData.get(position).getStaffEmailJob());
                    postJobDetailActivity.putExtra("staffImageJob",mData.get(position).getStaffImageJob());
                    postJobDetailActivity.putExtra("staffDescriptionJob",mData.get(position).getStaffDescriptionJob());
                    postJobDetailActivity.putExtra("staffStatusJob",mData.get(position).getStaffStatusJob());

                    long timestamp  = (long) mData.get(position).getpTime();
                    postJobDetailActivity.putExtra("pTime",timestamp) ;

                    context.startActivity(postJobDetailActivity);

                }
            });




        }
    }
}
