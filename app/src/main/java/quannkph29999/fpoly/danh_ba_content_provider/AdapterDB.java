package quannkph29999.fpoly.danh_ba_content_provider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDB extends RecyclerView.Adapter<AdapterDB.ViewHolder> {
    DBHelper dbHelper;
    DAO dao;
    Context context;
    ArrayList<DanhBa> listdb;

    public AdapterDB(DAO dao, Context context, ArrayList<DanhBa> listdb) {
        this.dao = dao;
        this.context = context;
        this.listdb = listdb;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_tendb,item_sdtdb;
        ImageButton item_message;
        RelativeLayout itemdb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_tendb = itemView.findViewById(R.id.item_name);
            item_sdtdb = itemView.findViewById(R.id.item_number);
            item_message = itemView.findViewById(R.id.item_message);
            itemdb = itemView.findViewById(R.id.item_danhba);


        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_danh_ba,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.item_tendb.setText(listdb.get(position).getTen_DB());
        holder.item_sdtdb.setText(listdb.get(position).getSdt_DB());
        holder.item_sdtdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+listdb.get(position).getSdt_DB()));
                v.getContext().startActivity(intent);
            }
        });
        holder.item_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_message,null,false);
                EditText message_ten_db = view.findViewById(R.id.ed_name_message);
                EditText content_message = view.findViewById(R.id.ed_content_message);
                Button btn_gui =view.findViewById(R.id.btn_guitn);
                message_ten_db.setText(listdb.get(position).getTen_DB());
                builder.setView(view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                btn_gui.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(content_message.length() == 0){
                            content_message.requestFocus();
                            content_message.setError("Không để trống nội dung tin nhắn");
                        }
                        else {
                            Toast.makeText(context, "Đã gửi tin nhắn", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return listdb.size();
    }


}
