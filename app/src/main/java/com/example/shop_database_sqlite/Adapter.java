package com.example.shop_database_sqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop_database_sqlite.ui.EditRecordActivity;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private Context context;
    private ArrayList<Model> arrayList;
    private TextView ad;

    int a = 0;

    public Adapter(Context context, ArrayList<Model> arrayList, TextView ad) {
        this.context = context;
        this.arrayList = arrayList;
        this.ad = ad;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, @SuppressLint("RecyclerView") int position) {

            Model model = arrayList.get(position);

            String id = model.getId();
            String name = model.getName();
            String price = model.getPhone();



            holder.name.setText(name);
            holder.phone1.setText(price);


            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pho = Integer.parseInt(price);

                    display(pho);

                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editDialog(position,
                            id,
                            name,
                            price);
                }
            });


    }

    private void display(int a) {

        //берем в переменную что есть в TextView
        int fyf = Integer.parseInt(ad.getText().toString());
        //прибавляем к прошлой сумме след
        int fd = fyf+a;
        ad.setText("" + fd);

    }


    private void editDialog(int position, String id, String name, String price) {

        Intent intent = new Intent(context , EditRecordActivity.class);
        intent.putExtra("ID", id);
        intent.putExtra("NAME", name);
        intent.putExtra("PRICE", price);
        context.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class Holder extends RecyclerView.ViewHolder {


        TextView name, phone1;
        Button button;
        TextView  ad;


        public Holder(@NonNull View itemView) {
            super(itemView);

            ad = itemView.findViewById(R.id.txt_price);
            button = itemView.findViewById(R.id.button_bay);
            name = itemView.findViewById(R.id.name);
            phone1 = itemView.findViewById(R.id.description);
        }
    }
}