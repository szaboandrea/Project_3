package com.example.labor3;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyDatePickerFragment extends DialogFragment {
    private String text;

    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            TextView tvBirthDate = getActivity().findViewById(R.id.tv_birth_date);
            text = "Birth date: " + view.getYear() + "."  + (view.getMonth()+1) + "." + view.getDayOfMonth();
            tvBirthDate.setText(text);
        }
    };
}
