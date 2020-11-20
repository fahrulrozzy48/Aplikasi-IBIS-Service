package com.example.ibisfood.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibisfood.AdminUI.UpdateKategoriActivity;
import com.example.ibisfood.Model.SpinnerModel;
import com.example.ibisfood.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ListKategoriManagemenAdapter extends RecyclerView.Adapter<ListKategoriManagemenAdapter.Myholder> {

    Context context;
    List<SpinnerModel> mData;
    private Activity activity;
    FirebaseDataListener listener;

    //Deklarasi objek dari Interfece
//    dataListener listener;


    private OnCallBack onCallBack;

    public ListKategoriManagemenAdapter() {
    }

    public ListKategoriManagemenAdapter(Context context, List<SpinnerModel> mData) {
        this.context = context;
        this.mData = mData;
        listener = (FirebaseDataListener) context;
    }


    public void setOnCallBack(OnCallBack onCallBack) {
        this.onCallBack = onCallBack;
    }

    @NonNull
    @Override
    public ListKategoriManagemenAdapter.Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_kategori_managemen, parent, false);
        return new ListKategoriManagemenAdapter.Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListKategoriManagemenAdapter.Myholder holder, final int position) {
        holder.nameKategori.setText(mData.get(position).getNameKategori());


        holder.cv_kategori.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //update dan delete kategori
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view_kategori_managemen);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = (Button) dialog.findViewById(R.id.bt_edit_data_kategori);
                Button delButton = (Button) dialog.findViewById(R.id.bt_delete_data_kategori);

                //apabila tombol edit diklik
                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                context.startActivity(UpdateKategoriActivity.getActIntent((Activity) context).putExtra("Kategori", (Parcelable) mData.get(position)));
                            }
                        }
                );

                //apabila tombol delete diklik
                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                /**
                                 *  Kodingan untuk Delete data (memanggil interface delete data)
                                 */
                                dialog.dismiss();
                                listener.onDeleteData(mData.get(position), position);
                            }
                        }
                );
                return true;
            }
        });
//        holder.cv_kategori.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager manager = ((AppCompatActivity) activity).getSupportFragmentManager();
//
//            }
//        });
//                /*
//                  Kodingan untuk membuat fungsi Edit dan Delete
//                 */
//                final String[] action = {"Update", "Delete"};
//                AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
//                alert.setItems(action,  new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int i) {
//                        switch (i){
//                            case 0:
//                        /*
//                          Berpindah Activity pada halaman layout updateData
//                          dan mengambil data , berdasarkan posisinya
//                          untuk dikirim pada activity selanjutnya
//                        */
//                                Bundle bundle = new Bundle();
//                                bundle.putString("nameKategori", mData.get(position).getNameKategori());
//                                bundle.putString("getPrimaryKey", mData.get(position).getKey());
//                                Intent intent = new Intent(v.getContext(), UpdateKategoriActivity.class);
//                                intent.putExtras(bundle);
//                                context.startActivity(intent);
//                                break;
//                            case 1:
//                                //Pembahasan selanjutnya mengenai fungsi Delete
//                                break;
//                        }
//                    }
//                });
//                alert.create();
//                alert.show();
//
//                return true;
//            }
//        });


//        holder.btnDeleteKategori.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onCallBack.onButtonDelete(mData.get(position));
//            }
//        });

//        holder.btnDeleteKategori.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(holder.nameKategori.getContext());
//                builder.setTitle("Delete Category");
//                builder.setMessage("Delete?");
//
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        FirebaseDatabase.getInstance().getReference().child("Kategori").child(mData.get(position).getKey()).removeValue();
//                    }
//                });
//
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                builder.show();
//            }
//        });
//
//        holder.btnUpdateKategori.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onCallBack.onButtonUpdate(mData.get(position));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {

        TextView nameKategori;
        ImageView btnUpdateKategori, btnDeleteKategori;
        CardView cv_kategori;

        public Myholder(@NonNull View itemView) {
            super(itemView);

            nameKategori = itemView.findViewById(R.id.row_read_kategori);
            cv_kategori = itemView.findViewById(R.id.card_view_kategori);
//            btnUpdateKategori = itemView.findViewById(R.id.row_update_kategori);
//            btnDeleteKategori = itemView.findViewById(R.id.row_delete_kategori);
        }

    }

    public interface OnCallBack {
        void onButtonDelete(SpinnerModel kategoriModel);

        void onButtonUpdate(SpinnerModel kategoriModel);
    }

    //Membuat Interfece
    public interface dataListener {
        void onDeleteData(SpinnerModel data, int position);
    }

    public interface FirebaseDataListener{
        void onDeleteData(SpinnerModel spinnerModel, int position);
    }
}
