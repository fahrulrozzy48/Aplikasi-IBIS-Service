package com.example.ibisfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibisfood.AdminUI.MessageDetailActivity;
import com.example.ibisfood.ManagerUI.ReportDetailActivity;
import com.example.ibisfood.Model.PostMessageModel;
import com.example.ibisfood.Model.PostReportFromAdminToManagerModel;
import com.example.ibisfood.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ListPostReportAdapter extends RecyclerView.Adapter<ListPostReportAdapter.MyHolder> {

    Context context;
    List<PostReportFromAdminToManagerModel> mData;

    Locale id = new Locale("in", "ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm", id);
    SimpleDateFormat simpleDateFormatDua = new SimpleDateFormat("dd MMMM YYYY", id);

    public ListPostReportAdapter(Context context, List<PostReportFromAdminToManagerModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ListPostReportAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_post_report_list, parent, false);
        return new ListPostReportAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPostReportAdapter.MyHolder holder, int position) {

        holder.postEmailAdmin.setText(mData.get(position).getuAdminEmail());
        holder.postTimeAwal.setText(simpleDateFormatDua.format(mData.get(position).getAdminAwalTime()));
        holder.postTimeAkhir.setText(simpleDateFormatDua.format(mData.get(position).getAdminAkhirTime()));
        holder.postTimeMessageAdmin.setText(simpleDateFormat.format(mData.get(position).getAdminPostTime()));
        holder.postPenanggungJawab.setText(mData.get(position).getAdminPenanggungJawab());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView postEmailAdmin, postTimeAwal, postTimeAkhir, postTimeMessageAdmin, postPenanggungJawab;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            postEmailAdmin = itemView.findViewById(R.id.row_emailAdmin);
            postTimeAwal = itemView.findViewById(R.id.row_report_rangeAwaltanggal);
            postTimeAkhir = itemView.findViewById(R.id.row_report_rangeAkhirTanggal);
            postTimeMessageAdmin = itemView.findViewById(R.id.row_timeAdmin);
            postPenanggungJawab = itemView.findViewById(R.id.row_report_penanggung_jawab);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent postDetailReportActivity = new Intent(context, ReportDetailActivity.class);
                    int position = getAdapterPosition();

                    postDetailReportActivity.putExtra("uAdminEmail", mData.get(position).getuAdminEmail());
                    postDetailReportActivity.putExtra("adminPenanggungJawab", mData.get(position).getAdminPenanggungJawab());
                    postDetailReportActivity.putExtra("adminKategoriSatu", mData.get(position).getAdminKategoriSatu());
                    postDetailReportActivity.putExtra("adminKategoriDua", mData.get(position).getAdminKategoriDua());
                    postDetailReportActivity.putExtra("adminKategoriTiga", mData.get(position).getAdminKategoriTiga());
                    postDetailReportActivity.putExtra("adminKategoriEmpat", mData.get(position).getAdminKategoriEmpat());
                    postDetailReportActivity.putExtra("adminKategoriLima", mData.get(position).getAdminKategoriLima());
                    postDetailReportActivity.putExtra("adminKategoriEnam", mData.get(position).getAdminKategoriEnam());
                    postDetailReportActivity.putExtra("adminInputKategoriSatu", mData.get(position).getAdminInputKategoriSatu());
                    postDetailReportActivity.putExtra("adminInputKategoriDua", mData.get(position).getAdminInputKategoriDua());
                    postDetailReportActivity.putExtra("adminInputKategoriTiga", mData.get(position).getAdminInputKategoriTiga());
                    postDetailReportActivity.putExtra("adminInputKategoriEmpat", mData.get(position).getAdminInputKategoriEmpat());
                    postDetailReportActivity.putExtra("adminInputKategoriLima", mData.get(position).getAdminInputKategoriLima());
                    postDetailReportActivity.putExtra("adminInputKategoriEnam", mData.get(position).getAdminInputKategoriEnam());
                    postDetailReportActivity.putExtra("adminInputSelesaiKategoriSatu", mData.get(position).getAdminInputSelesaiKategoriSatu());
                    postDetailReportActivity.putExtra("adminInputSelesaiKategoriDua", mData.get(position).getAdminInputSelesaiKategoriDua());
                    postDetailReportActivity.putExtra("adminInputSelesaiKategoriTiga", mData.get(position).getAdminInputSelesaiKategoriTiga());
                    postDetailReportActivity.putExtra("adminInputSelesaiKategoriEmpat", mData.get(position).getAdminInputSelesaiKategoriEmpat());
                    postDetailReportActivity.putExtra("adminInputSelesaiKategoriLima", mData.get(position).getAdminInputSelesaiKategoriLima());
                    postDetailReportActivity.putExtra("adminInputSelesaiKategoriEnam", mData.get(position).getAdminInputSelesaiKategoriEnam());
                    postDetailReportActivity.putExtra("adminInputBelumSelesaiKategoriSatu", mData.get(position).getAdminInputBelumSelesaiKategoriSatu());
                    postDetailReportActivity.putExtra("adminInputBelumSelesaiKategoriDua", mData.get(position).getAdminInputBelumSelesaiKategoriDua());
                    postDetailReportActivity.putExtra("adminInputBelumSelesaiKategoriTiga", mData.get(position).getAdminInputBelumSelesaiKategoriTiga());
                    postDetailReportActivity.putExtra("adminInputBelumSelesaiKategoriEmpat", mData.get(position).getAdminInputBelumSelesaiKategoriEmpat());
                    postDetailReportActivity.putExtra("adminInputBelumSelesaiKategoriLima", mData.get(position).getAdminInputBelumSelesaiKategoriLima());
                    postDetailReportActivity.putExtra("adminInputBelumSelesaiKategoriEnam", mData.get(position).getAdminInputBelumSelesaiKategoriEnam());
                    postDetailReportActivity.putExtra("adminInputDeskripsiKategoriSatu", mData.get(position).getAdminInputDeskripsiKategoriSatu());
                    postDetailReportActivity.putExtra("adminInputDeskripsiKategoriDua", mData.get(position).getAdminInputDeskripsiKategoriDua());
                    postDetailReportActivity.putExtra("adminInputDeskripsiKategoriTiga", mData.get(position).getAdminInputDeskripsiKategoriTiga());
                    postDetailReportActivity.putExtra("adminInputDeskripsiKategoriEmpat", mData.get(position).getAdminInputDeskripsiKategoriEmpat());
                    postDetailReportActivity.putExtra("adminInputDeskripsiKategoriLima", mData.get(position).getAdminInputDeskripsiKategoriLima());
                    postDetailReportActivity.putExtra("adminInputDeskripsiKategoriEnam", mData.get(position).getAdminInputDeskripsiKategoriEnam());
                    postDetailReportActivity.putExtra("textSelesaiKategoriSatu", mData.get(position).getTextSelesaiKategoriSatu());
                    postDetailReportActivity.putExtra("textSelesaiKategoriDua", mData.get(position).getTextSelesaiKategoriDua());
                    postDetailReportActivity.putExtra("textSelesaiKategoriTiga", mData.get(position).getTextSelesaiKategoriTiga());
                    postDetailReportActivity.putExtra("textSelesaiKategoriEmpat", mData.get(position).getTextSelesaiKategoriEmpat());
                    postDetailReportActivity.putExtra("textSelesaiKategoriLima", mData.get(position).getTextSelesaiKategoriLima());
                    postDetailReportActivity.putExtra("textSelesaiKategoriEnam", mData.get(position).getTextSelesaiKategoriEnam());
                    postDetailReportActivity.putExtra("textBelumSelesaiKategoriSatu", mData.get(position).getTextBelumSelesaiKategoriSatu());
                    postDetailReportActivity.putExtra("textBelumSelesaiKategoriDua", mData.get(position).getTextBelumSelesaiKategoriDua());
                    postDetailReportActivity.putExtra("textBelumSelesaiKategoriTiga", mData.get(position).getTextBelumSelesaiKategoriTiga());
                    postDetailReportActivity.putExtra("textBelumSelesaiKategoriEmpat", mData.get(position).getTextBelumSelesaiKategoriEmpat());
                    postDetailReportActivity.putExtra("textBelumSelesaiKategoriLima", mData.get(position).getTextBelumSelesaiKategoriLima());
                    postDetailReportActivity.putExtra("textBelumSelesaiKategoriEnam", mData.get(position).getTextBelumSelesaiKategoriEnam());
                    postDetailReportActivity.putExtra("postKey", mData.get(position).getPostAdminKey());


//                    postDetailMessageActivity.putExtra("pTime",simpleDateFormat.format(mData.get(position).getpTime()));

//                    long timestamp  = (long) mData.get(position).getpTime();
                    long timestamp = (long) mData.get(position).getAdminPostTime();
                    postDetailReportActivity.putExtra("adminPostTime", timestamp);

                    long timestamps = (long) mData.get(position).getAdminAwalTime();
                    postDetailReportActivity.putExtra("adminAwalTime", timestamps);

                    long timestampt = (long) mData.get(position).getAdminAkhirTime();
                    postDetailReportActivity.putExtra("adminAkhirTime", timestampt);

                    context.startActivity(postDetailReportActivity);
                }
            });


        }

        public void setTime(String date) {

            postTimeMessageAdmin = itemView.findViewById(R.id.row_timeAdmin);
            postTimeMessageAdmin.setText(date);

            postTimeAkhir = itemView.findViewById(R.id.row_report_rangeAkhirTanggal);
            postTimeAkhir.setText(date);

            postTimeAwal = itemView.findViewById(R.id.row_report_rangeAwaltanggal);
            postTimeAwal.setText(date);

        }
    }

}