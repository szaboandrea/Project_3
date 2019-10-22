package com.example.labor3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewHobbyActivity extends AppCompatActivity {

    private Button btnAdd, btnShowHobbies;
    private Database myDb;
    EditText mNewHobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hobby);
        myDb = new Database(this);

        btnAdd = findViewById(R.id.btn_add);
        btnShowHobbies = findViewById(R.id.btn_show_hobbies);
        mNewHobby = findViewById(R.id.et_hobby_name);

        btnShowHobbies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewHobbyActivity.this, HobbiesActivity.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddHobby();
            }
        });
    }

    public void AddHobby(){
        boolean isInserted=false;
        if (!(mNewHobby.getText().toString()).matches("")){
            isInserted = myDb.insertHobby(
                    mNewHobby.getText().toString()
            );
        }
        if (isInserted==true){
            Toast.makeText(NewHobbyActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(NewHobbyActivity.this, "Please fill the field", Toast.LENGTH_LONG).show();
        }
    }
}
