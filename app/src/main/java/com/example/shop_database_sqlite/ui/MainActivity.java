package com.example.shop_database_sqlite.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop_database_sqlite.Adapter;
import com.example.shop_database_sqlite.DatabaseHelper;
import com.example.shop_database_sqlite.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    ActionBar actionBar;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;

    TextView ad;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.Agent);

        recyclerView = findViewById(R.id.recycleView);
        databaseHelper = new DatabaseHelper(this);

        ad=findViewById(R.id.txt_price);
        button = findViewById(R.id.button_main);


        //добавление и установка адаптера
        showRecord();


        fab = findViewById(R.id.addFabButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
                startActivity(intent);

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ower = ad.getText().toString();
                Toast.makeText(MainActivity.this, "Вы совершили покупку на " + ower + " Рублей", Toast.LENGTH_SHORT).show();
                ad.setText("0");
            }
        });





    }
    //добавление и установка адаптера
    private void showRecord() {
        Adapter adapter = new Adapter(MainActivity.this, databaseHelper.getAllData(), ad);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showRecord();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == event.KEYCODE_BACK){
            moveTaskToBack(true);
        }

        return super.onKeyDown(keyCode, event);
    }
}