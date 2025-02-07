package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FourthActivity extends AppCompatActivity {

    TextView resultField; // текстовое поле для вывода результата
    EditText numberField;   // поле для ввода числа
    TextView operationField;    // текстовое поле для вывода знака операции
    Double operand = null;  // операнд операции
    String lastOperation = "="; // последняя операция

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourthlayout);
        // получаем все поля по id из activity_main.xml
        resultField = findViewById(R.id.resultField);
        numberField = findViewById(R.id.numberField);
        operationField = findViewById(R.id.operationField);

        findViewById(R.id.add).setOnClickListener((view) -> onOperationClick("+"));
        findViewById(R.id.sub).setOnClickListener((view) -> onOperationClick("-"));
        findViewById(R.id.mul).setOnClickListener((view) -> onOperationClick("*"));
        findViewById(R.id.div).setOnClickListener((view) -> onOperationClick("/"));
        findViewById(R.id.eq).setOnClickListener((view) -> onOperationClick("="));

        findViewById(R.id.n0).setOnClickListener((view) -> onNumberClick("0"));
        findViewById(R.id.n1).setOnClickListener((view) -> onNumberClick("1"));
        findViewById(R.id.n2).setOnClickListener((view) -> onNumberClick("2"));
        findViewById(R.id.n3).setOnClickListener((view) -> onNumberClick("3"));
        findViewById(R.id.n4).setOnClickListener((view) -> onNumberClick("4"));
        findViewById(R.id.n5).setOnClickListener((view) -> onNumberClick("5"));
        findViewById(R.id.n6).setOnClickListener((view) -> onNumberClick("6"));
        findViewById(R.id.n7).setOnClickListener((view) -> onNumberClick("7"));
        findViewById(R.id.n8).setOnClickListener((view) -> onNumberClick("8"));
        findViewById(R.id.n9).setOnClickListener((view) -> onNumberClick("9"));
        findViewById(R.id.comma).setOnClickListener((view) -> onNumberClick(","));
    }

    // сохранение состояния
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if (operand != null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }

    // получение ранее сохраненного состояния
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand = savedInstanceState.getDouble("OPERAND");
        resultField.setText(operand.toString());
        operationField.setText(lastOperation);
    }

    // обработка нажатия на числовую кнопку
    public void onNumberClick(String number) {
        numberField.append(number);
        if (lastOperation.equals("=") && operand != null) {
            operand = null;
        }
    }

    // обработка нажатия на кнопку операции
    public void onOperationClick(String op) {

        String number = numberField.getText().toString();
        // если введенно что-нибудь
        if (number.length() > 0) {
            number = number.replace(',', '.');
            try {
                performOperation(Double.valueOf(number), op);
            } catch (NumberFormatException ex) {
                numberField.setText("");
            }
        }
        lastOperation = op;
        operationField.setText(lastOperation);
    }

    private void performOperation(Double number, String operation) {

        // если операнд ранее не был установлен (при вводе самой первой операции)
        if (operand == null) {
            operand = number;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }
            switch (lastOperation) {
                case "=":
                    operand = number;
                    break;
                case "/":
                    if (number == 0) {
                        Toast.makeText(this, "Nu se poate de împărțit la 0", Toast.LENGTH_SHORT).show();
                        operand = 0.0;
                    } else {
                        operand /= number;
                    }
                    break;
                case "*":
                    operand *= number;
                    break;
                case "+":
                    operand += number;
                    break;
                case "-":
                    operand -= number;
                    break;
            }
        }
        resultField.setText(operand.toString().replace('.', ','));
        numberField.setText("");

        Button button_fifth = findViewById(R.id.button_fifth);
        button_fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourthActivity.this, FifthActivity.class);
                startActivity(intent);
            }
        });
    }
}
