package com.example.shop_database_sqlite.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shop_database_sqlite.DatabaseHelper;
import com.example.shop_database_sqlite.R;

public class AddRecordActivity extends AppCompatActivity {

    private EditText pNameEt, pEmailEt, pPhoneEt;
    Button saveInfoBt;
    ActionBar actionBar;


    private String name;
    String phone;
    private DatabaseHelper dbHelper;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:
                this.finish();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.AddAgent);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);



        pNameEt = findViewById(R.id.Name);
        pPhoneEt = findViewById(R.id.price);
        saveInfoBt = findViewById(R.id.addButton);


        dbHelper = new DatabaseHelper(this);


        //Нажатие на кнопку сохранить
        saveInfoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });


    }
    //получаем данные и передаем их в базу данных
    private void getData() {

        if (pNameEt.getText().toString().length()<3 ||  pPhoneEt.getText().toString().length()<3){
            Toast.makeText(this, "все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
        }else {
            name = " "+pNameEt.getText().toString().trim();
            phone = " "+pPhoneEt.getText().toString().trim();

            dbHelper.insertInfo(
                    ""+name,
                    ""+phone
            );
            startActivity(new Intent(AddRecordActivity.this , MainActivity.class));
            Toast.makeText(AddRecordActivity.this, "Данные успешно добавленны!", Toast.LENGTH_SHORT).show();

        }
    }

}