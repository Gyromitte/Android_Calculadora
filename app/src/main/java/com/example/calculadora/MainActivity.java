package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextResult;
    private Button buttonC, buttonDivide, buttonPercentage, buttonDelete, button7, button8,
            button9, buttonMinus, button4, button5, button6, buttonPlus, button1, button2,
            button3, buttonMultiply, buttonPoint, button0, buttonEquals;

    private StringBuilder currentInput;
    private double result;
    private String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextResult = findViewById(R.id.editTextResult);

        buttonC = findViewById(R.id.buttonC);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonDelete = findViewById(R.id.buttonDelete);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonMinus = findViewById(R.id.buttonMinus);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        buttonPlus = findViewById(R.id.buttonPlus);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonPoint = findViewById(R.id.buttonPoint);
        button0 = findViewById(R.id.button0);
        buttonEquals = findViewById(R.id.buttonEquals);

        currentInput = new StringBuilder();
        result = 0;
        operator = "";

        buttonC.setOnClickListener(v -> clearText());
        buttonDivide.setOnClickListener(v -> onButtonClick("÷"));
        buttonDelete.setOnClickListener(v -> onDeleteClick());
        button7.setOnClickListener(v -> onButtonClick("7"));
        button8.setOnClickListener(v -> onButtonClick("8"));
        button9.setOnClickListener(v -> onButtonClick("9"));
        buttonMinus.setOnClickListener(v -> onButtonClick("-"));
        button4.setOnClickListener(v -> onButtonClick("4"));
        button5.setOnClickListener(v -> onButtonClick("5"));
        button6.setOnClickListener(v -> onButtonClick("6"));
        buttonPlus.setOnClickListener(v -> onButtonClick("+"));
        button1.setOnClickListener(v -> onButtonClick("1"));
        button2.setOnClickListener(v -> onButtonClick("2"));
        button3.setOnClickListener(v -> onButtonClick("3"));
        buttonMultiply.setOnClickListener(v -> onButtonClick("x"));
        buttonPoint.setOnClickListener(v -> onButtonClick("."));
        button0.setOnClickListener(v -> onButtonClick("0"));
        buttonEquals.setOnClickListener(v -> onEqualsClick());
    }

    private void clearText() {
        currentInput.setLength(0);
        result = 0;
        operator = "";
        editTextResult.setText("");
    }

    private void onButtonClick(String input) {
        currentInput.append(input);
        editTextResult.setText(currentInput.toString());
    }

    private void onDeleteClick() {
        if (currentInput.length() > 0) {
            currentInput.deleteCharAt(currentInput.length() - 1);
            editTextResult.setText(currentInput.toString());
        }
    }

    private void performOperation() {
        String[] tokens = currentInput.toString().split("(?<=[+\\-x÷])|(?=[+\\-x÷])");

        if (tokens.length < 3) {
            // No hay suficientes operandos y operadores para realizar una operación
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            return;
        }

        double operand1 = Double.parseDouble(tokens[0]);
        String currentOperator = "";

        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].matches("[+\\-x÷]")) {
                currentOperator = tokens[i];
            } else {
                double operand2 = Double.parseDouble(tokens[i]);

                switch (currentOperator) {
                    case "+":
                        operand1 += operand2;
                        break;
                    case "-":
                        operand1 -= operand2;
                        break;
                    case "x":
                        operand1 *= operand2;
                        break;
                    case "÷":
                        if (operand2 != 0) {
                            operand1 /= operand2;
                        } else {
                            Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        break;
                }
            }
        }

        result = operand1;
        currentInput.setLength(0);
        currentInput.append(result);
        editTextResult.setText(currentInput.toString());
    }



    private void onEqualsClick() {
        performOperation();
        operator = "";
    }
}

