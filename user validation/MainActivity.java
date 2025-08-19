package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText editName, editEmail, editPhone, editPassword, editConfirmPassword, editDOB;
    RadioGroup genderGroup;
    Spinner spinnerState;
    Button btnSubmit;

    // To store selected DOB
    int dobYear, dobMonth, dobDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPassword = findViewById(R.id.editConfirmPassword);
        editDOB = findViewById(R.id.editDOB);

        genderGroup = findViewById(R.id.genderGroup);
        spinnerState = findViewById(R.id.spinnerState);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Setup spinner with states
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Select State", "kerala", "Tamil Nadu", "Karnataka"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(adapter);

        // Show DatePickerDialog when clicking DOB field
        editDOB.setFocusable(false);
        editDOB.setClickable(true);
        editDOB.setOnClickListener(v -> showDatePickerDialog());

        // Submit button listener
        btnSubmit.setOnClickListener(v -> validateForm());
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();

        // Default date to 18 years ago
        int year = calendar.get(Calendar.YEAR) - 18;
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    dobYear = selectedYear;
                    dobMonth = selectedMonth;
                    dobDay = selectedDay;

                    @SuppressLint("DefaultLocale") String dobString = String.format("%02d/%02d/%04d", dobDay, dobMonth + 1, dobYear);
                    editDOB.setText(dobString);
                }, year, month, day);

        // Set max date to today minus 18 years
        Calendar maxDate = Calendar.getInstance();
        maxDate.add(Calendar.YEAR, -18);
        datePickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());

        datePickerDialog.show();
    }

    private void validateForm() {
        String name = editName.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();
        String password = editPassword.getText().toString();
        String confirmPassword = editConfirmPassword.getText().toString();
        int genderId = genderGroup.getCheckedRadioButtonId();
        String state = spinnerState.getSelectedItem().toString();

        // Validate Name
        if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
            editName.setError("Enter a valid name (letters and spaces only)");
            editName.requestFocus();
            return;
        }

        // Validate Email
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Enter a valid email");
            editEmail.requestFocus();
            return;
        }

        // Validate Gender
        if (genderId == -1) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate Phone
        if (!phone.matches("\\d{10}")) {
            editPhone.setError("Enter 10-digit phone number");
            editPhone.requestFocus();
            return;
        }

        // Validate DOB (must be set)
        if (editDOB.getText().toString().isEmpty()) {
            editDOB.setError("Select your date of birth");
            editDOB.requestFocus();
            return;
        }

        // Validate age from dobYear, dobMonth, dobDay
        Calendar dob = Calendar.getInstance();
        dob.set(dobYear, dobMonth, dobDay);

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        if (age < 18) {
            Toast.makeText(this, "You must be at least 18 years old", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate Password - allow letters, digits, special chars, min 8 chars, at least 1 letter and 1 digit
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d).{8,}$")) {
            editPassword.setError("Password must be at least 8 characters and include letters and numbers");
            editPassword.requestFocus();
            return;
        }

        // Confirm Password
        if (!password.equals(confirmPassword)) {
            editConfirmPassword.setError("Passwords do not match");
            editConfirmPassword.requestFocus();
            return;
        }

        // Validate State
        if (state.equals("Select State")) {
            Toast.makeText(this, "Please select a state", Toast.LENGTH_SHORT).show();
            return;
        }

        // All validations passed
        Toast.makeText(this, "Form is valid!", Toast.LENGTH_LONG).show();

        // TODO: proceed with form submission logic
    }
}
