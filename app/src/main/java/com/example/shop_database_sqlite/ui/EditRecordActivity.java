package com.example.shop_database_sqlite.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shop_database_sqlite.DatabaseHelper;
import com.example.shop_database_sqlite.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

//Класс для обновления информаций
public class EditRecordActivity extends AppCompatActivity {


    private EditText pNameEt, pPhoneEt;
    Button saveInfoBt, deleteBtn;
    ActionBar actionBar;



    private String id, name,price;
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
        setContentView(R.layout.activity_edit_record);

        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.AddInfo);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);




        pNameEt = findViewById(R.id.Name);
        pPhoneEt = findViewById(R.id.personPhone);
        saveInfoBt = findViewById(R.id.addButton);
        deleteBtn = findViewById(R.id.deleteButton);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("NAME");
        price = intent.getStringExtra("PRICE");


        pNameEt.setText(name);
        pPhoneEt.setText(price);






        dbHelper = new DatabaseHelper(this);

        //НАЖАТИЕ НА УДАЛИТЬ
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog(id);
            }
        });



        //Нажатие на кнопку сохранить
        saveInfoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });


    }
    //НАЖАТИЕ НА УДАЛИТЬ
    private void deleteDialog(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(EditRecordActivity.this);
        builder.setTitle("Удаление");
        builder.setMessage("Вы точно хотите удалить товар?");
        builder.setIcon(R.drawable.ic_add_delete);

        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.deleteInfo(id);
                startActivity(new Intent(EditRecordActivity.this, MainActivity.class));
                Toast.makeText(EditRecordActivity.this, "Данные удалены", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();
    }


    //получаем данные и передаем их в базу данных
    private void getData() {
        if (pNameEt.getText().toString().length()<3 ||  pPhoneEt.getText().toString().length()<3){
            Toast.makeText(this, "все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
        }else {
            name = " "+pNameEt.getText().toString().trim();
            price = " "+pPhoneEt.getText().toString().trim();

            dbHelper.updateInfo(
                    ""+id,
                    ""+name,
                    ""+price

            );

            startActivity(new Intent(EditRecordActivity.this, MainActivity.class));
            Toast.makeText(EditRecordActivity.this, "Информация обновлена!", Toast.LENGTH_SHORT).show();

        }

    }


}