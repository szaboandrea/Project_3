package com.example.labor3;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DatabaseSettingActivity extends AppCompatActivity {

    private TextView mFirstName, mLastName, mDepartment;
    Database mydb;
    private Button btnDeleteAll, btnShowDatas, btnAddElements, btnBack, btnDeleteHobbies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_setting);
        mydb = new Database(this);

        mFirstName = findViewById(R.id.et_first_name_database);
        mLastName = findViewById(R.id.et_last_name_database);
        mDepartment = findViewById(R.id.et_department_database);
        btnAddElements = findViewById(R.id.btn_add_new_element);
        btnDeleteAll = findViewById(R.id.btn_delete_all_datas);
        btnShowDatas = findViewById(R.id.btn_show_datas);
        btnBack = findViewById(R.id.btn_back_database);
        btnDeleteHobbies = findViewById(R.id.btn_delete_hobbies);


        btnShowDatas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAll();
            }
        });

        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDatas();
            }
        });

        btnAddElements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatabaseSettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDeleteHobbies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteHobbies();
            }
        });
    }

    public void AddData(){
        boolean isInserted;
        isInserted = mydb.insertProfile(
                mFirstName.getText().toString(),
                mLastName.getText().toString(),
                mDepartment.getText().toString()
        );
        if (isInserted==true){
            Toast.makeText(DatabaseSettingActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(DatabaseSettingActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void getAll(){
        Cursor res = mydb.getaAllData();
        if (res.getCount() == 0){
            showMessage("Error", "Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("id :"+ res.getString(0)+"\n");
            buffer.append("first_name :"+ res.getString(1)+"\n");
            buffer.append("last_name :"+ res.getString(2)+"\n");
            buffer.append("department :"+ res.getString(3)+"\n\n");
        }
        showMessage("Data", buffer.toString());
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void deleteDatas(){
        Integer deleteRows = mydb.deleteDatas();
        if (deleteRows > 0){
            Toast.makeText(DatabaseSettingActivity.this, "Delete datas", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(DatabaseSettingActivity.this,"Data not deleted", Toast.LENGTH_LONG ).show();
        }
    }

    public void deleteHobbies(){
        Integer deleteRows = mydb.deleteHobbies();
        if (deleteRows > 0){
            Toast.makeText(DatabaseSettingActivity.this, "Delete hobbies", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(DatabaseSettingActivity.this,"Hobbies not deleted", Toast.LENGTH_LONG ).show();
        }
    }
}
