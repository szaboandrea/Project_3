package com.example.labor3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity {

    private Button btnPickDate, btnLogIn;
    private TextView mFirstName, mLastName, mDepartment;
    Database mydb;
    private CheckBox mCheck;
    private TextView mBirthDate;
    public static final String myPREFERENCES = "MyPref";
    public static final String FirstName = "firstNameKey";
    public static final String LastName = "lastNameKey";
    public static final String Department = "departmentKey";
    public static final String BirthDate = "birthDateKey";
    String spFirstName, spLastName, spDepartment, spBirthDate;

    SharedPreferences sharedPreferences;

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
        mCheck = findViewById(R.id.cb_remember_me);
        mBirthDate = findViewById(R.id.tv_birth_date);

        sharedPreferences = getSharedPreferences(myPREFERENCES, Context.MODE_PRIVATE);
        spFirstName = sharedPreferences.getString(FirstName,null); //sp = shared preferences first name
        spLastName = sharedPreferences.getString(LastName,null);
        spDepartment = sharedPreferences.getString(Department, null);
        spBirthDate = sharedPreferences.getString(BirthDate,null);

        if ((spFirstName != null) && (spLastName != null) && (spDepartment != null) && (spBirthDate != null)){
            mFirstName.setText(spFirstName);
            mLastName.setText(spLastName);
            mDepartment.setText(spDepartment);
            mBirthDate.setText(spBirthDate);
        }

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
                        if (mCheck.isChecked()){
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(FirstName, mFirstName.getText().toString());
                            editor.putString(LastName, mLastName.getText().toString());
                            editor.putString(Department, mDepartment.getText().toString());
                            editor.putString(BirthDate, mBirthDate.getText().toString());
                            editor.commit();
                            Toast.makeText(MainActivity.this, "Success Shared Preferences", Toast.LENGTH_LONG).show();

                        }
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
