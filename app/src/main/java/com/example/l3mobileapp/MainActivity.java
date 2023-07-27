package com.example.l3mobileapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.loader.content.Loader;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
/*    private String [] workStatus = {"Employed", "Unemployed"};*/
    public void updateDOB(LocalDate dob){
        TextView dobControl = findViewById(R.id.dob_control);
        dobControl.setText(dob.toString());
    }
    //Define variables to references the layout
    EditText nameInput;
    EditText emailInput;
    EditText phoneInput;
    Spinner sp;
    String workStatus;
    Button submitBtn;
    Toolbar myToolBar;

    //Define the function getInputs() to get values from inputs
    private void getInputs(){
        nameInput = findViewById(R.id.name_input);
        emailInput = findViewById(R.id.email_input);
        phoneInput = findViewById(R.id.phone_input);

        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();
        String phone = phoneInput.getText().toString();
        workStatus = sp.getSelectedItem().toString();

        //Use function displayNextALert to display an AlertDialog
        displayNextAlert(name, phone, email, workStatus);
    }
    public void  displayNextAlert(String name, String phone, String email, String workStatus){
        new AlertDialog.Builder(this)
                .setTitle("Detail Entered")
                .setMessage(
                        "Details: \n" +
                                name + "\n" +
                                phone + "\n" +
                                email + "\n" +
                                workStatus
                        )
                .setNeutralButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    TextView dobControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dobControl = findViewById(R.id.dob_control);

        // Get a reference from the toolbar
        myToolBar = findViewById(R.id.toolbar);

        // Set toolbar as actionbar for the activity
        setSupportActionBar(myToolBar);

        dobControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        // ...

        // Refer the Spinner from the layout
        sp = findViewById(R.id.spinner);

        // Refer the button from the layout
        submitBtn = findViewById(R.id.submit_btn);

        // Adding behavior to the button
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox cb = findViewById(R.id.checkBox);

                if (!cb.isChecked()){
                    Toast
                            .makeText(
                                    MainActivity.this,
                                    "You must check the box",
                                    Toast.LENGTH_LONG
                            )
                            .show();
                    return;
                }
                getInputs();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == R.id.itemNext){
            getInputs();
            return true;
        }
        else if (item.getItemId() == R.id.itemExit){
            Toast.makeText(
                    getApplicationContext(),
                    "You asked to exit, but why not start another app?",
                    Toast.LENGTH_LONG
            ).show();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}