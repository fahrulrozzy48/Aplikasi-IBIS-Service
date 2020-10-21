package com.example.ibisfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ibisfood.Model.PostAddFeedsAdminToUserModel;
import com.example.ibisfood.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ListPostFeedsAdapter extends RecyclerView.Adapter<ListPostFeedsAdapter.MyHolder> {


    Context context;
    List<PostAddFeedsAdminToUserModel> mData;

    Locale id = new Locale("in","ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY",id);

    public ListPostFeedsAdapter(Context context, List<PostAddFeedsAdminToUserModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ListPostFeedsAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_feeds_post_list, parent, false);
        return new ListPostFeedsAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPostFeedsAdapter.MyHolder holder, int position) {

        Glide.with(context).load(mData.get(position).getFeedsImage()).into(holder.imageFeeds);
        holder.descriptionFeeds.setText(mData.get(position).getFeedsDesciption());
        holder.dateFeeds.setText(simpleDateFormat.format(mData.get(position).getTimeStamp()));


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView descriptionFeeds, dateFeeds;
        ImageView imageFeeds;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            imageFeeds = itemView.findViewById(R.id.row_feedsAdmin_image);
            descriptionFeeds = itemView.findViewById(R.id.row_feedsAdmin_description);
            dateFeeds = itemView.findViewById(R.id.row_feedsAdmin_date);



        }
    }
}
