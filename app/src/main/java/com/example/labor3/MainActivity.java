package com.example.labor3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity {

    private Button btnPickDate, btnLogIn;
    private TextView mFirstName, mLastName, mDepartment;
    Database mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new Database(this);

        btnPickDate = findViewById(R.id.btn_pick_a_date);
        btnLogIn = findViewById(R.id.btn_log_in);
        mFirstName = findViewById(R.id.et_first_name);
        mLastName = findViewById(R.id.et_last_name);
        mDepartment = findViewById(R.id.et_department);

        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((mFirstName.getText().toString()).matches("")) ||
                        ((mLastName.getText().toString()).matches("")) ||
                        ((mDepartment.getText().toString()).matches(""))){
                    Toast.makeText(MainActivity.this, "Please fill all section", Toast.LENGTH_LONG).show();
                }
                else{
                    if (((mydb.foundName(mFirstName.getText().toString())) > 0) &&
                            ((mydb.foundLastName(mLastName.getText().toString())) > 0) &&
                            ((mydb.foundDepartment(mDepartment.getText().toString())) > 0)){
                        Intent intent = new Intent(MainActivity.this, HobbiesActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Login error, please try again", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnLogIn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(MainActivity.this,DatabaseSettingActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }

    public void showDatePicker() {
        DialogFragment newFragment = new MyDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "date picker");
    }
}
