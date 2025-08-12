package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView display;
    String input = "";
    double firstNumber = 0;
    String operator = "";
    boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);

        // Digits
        int[] digitIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        };

        View.OnClickListener digitClickListener = v -> {
            Button b = (Button) v;
            if (isNewInput) {
                input = "";
                isNewInput = false;
            }
            input += b.getText().toString();
            display.setText(input);
        };

        for (int id : digitIds) {
            findViewById(id).setOnClickListener(digitClickListener);
        }

        // Operators
        findViewById(R.id.btnAdd).setOnClickListener(v -> onOperatorClick("+"));
        findViewById(R.id.btnSubtract).setOnClickListener(v -> onOperatorClick("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> onOperatorClick("*"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> onOperatorClick("/"));

        // Equal
        findViewById(R.id.btnEqual).setOnClickListener(v -> onEqualClick());

        // Clear
        findViewById(R.id.btnClear).setOnClickListener(v -> {
            input = "";
            operator = "";
            firstNumber = 0;
            display.setText("0");
            isNewInput = true;
        });
    }

    private void onOperatorClick(String op) {
        if (!input.isEmpty()) {
            firstNumber = Double.parseDouble(input);
            operator = op;
            isNewInput = true;
        }
    }

    private void onEqualClick() {
        if (!input.isEmpty() && !operator.isEmpty()) {
            double secondNumber = Double.parseDouble(input);
            double result = 0;

            switch (operator) {
                case "+": result = firstNumber + secondNumber; break;
                case "-": result = firstNumber - secondNumber; break;
                case "*": result = firstNumber * secondNumber; break;
                case "/":
                    if (secondNumber == 0) {
                        display.setText("Error");
                        input = "";
                        return;
                    } else {
                        result = firstNumber / secondNumber;
                    }
                    break;
            }

            input = String.valueOf(result);
            display.setText(input);
            isNewInput = true;
            operator = "";
        }
    }
}
