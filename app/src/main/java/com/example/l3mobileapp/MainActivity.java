package com.example.l3mobileapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
        {
            LocalDate d = LocalDate.now();
            int year = d.getYear();
            int month = d.getMonthValue();
            int day = d.getDayOfMonth();
            return new DatePickerDialog(getActivity(), this, year, --month, day);}
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day){
            LocalDate dob = LocalDate.of(year, ++month, day);
            ((MainActivity)getActivity()).updateDOB(dob);
        }
    }
    // Create an array with options
    private String [] workStatus = {"Employed", "Unemployed"};
    Spinner sp;
    public void updateDOB(LocalDate dob){
        TextView dobControl = findViewById(R.id.dob_control);
        dobControl.setText(dob.toString());
    }
    TextView dobControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dobControl = findViewById(R.id.dob_control);
        dobControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        // Get reference to spinner
        sp = findViewById(R.id.spinner);

        // Create an adapter
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, workStatus);
        // Connect adapter to spinner
        sp.setAdapter(dataAdapter);
    }
}