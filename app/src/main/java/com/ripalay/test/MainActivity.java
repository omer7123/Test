package com.ripalay.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult;
    private Integer firstNumber;
    private Double firstNumberPercent;
    private boolean proverka;
    private String operation = "";
    private Integer secondNumber;
    private String test;
    private MaterialButton share;
    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tv_result);
        share = findViewById(R.id.btn_share);
        share.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("result", Integer.toString(firstNumber) + operation
                    + Integer.toString(secondNumber)+ "=" + Integer.toString(result));
            startActivity(intent);
        });
    }

    public void onClickNumber(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                writeSymbol("1");
                break;
            case R.id.btn_two:
                writeSymbol("2");
                break;
            case R.id.btn_three:
                writeSymbol("3");
                break;
            case R.id.btn_four:
                writeSymbol("4");
                break;
            case R.id.btn_five:
                writeSymbol("5");
                break;
            case R.id.btn_six:
                writeSymbol("6");
                break;
            case R.id.btn_seven:
                writeSymbol("7");
                break;

            case R.id.btn_eight:
                writeSymbol("8");
                break;

            case R.id.btn_nine:
                writeSymbol("9");
                break;
            case R.id.btn_null:
                writeSymbol("0");
                break;

            case R.id.btn_dot:
                writeSymbol(".");
                break;


        }
    }

    public void onClickOption(View view) {
        switch (view.getId()) {
            case R.id.btn_clear:
                tvResult.setText("0");
                share.setVisibility(View.GONE);
                break;

            case R.id.btn_equals:

                if (tvResult.getText().toString().replace(firstNumber + operation, "") != "") {
                    secondNumber = Integer.parseInt(tvResult.getText().toString().replace(firstNumber + operation, ""));
                    switch (operation) {
                        case "+":
                            result = firstNumber + secondNumber;
                            tvResult.setText(firstNumber + operation + secondNumber + "=" + (result));
                            break;
                        case "-":
                            result = firstNumber - secondNumber;
                            tvResult.setText(firstNumber + operation + secondNumber + "=" + (result));
                            break;
                        case "*":
                            result = firstNumber * secondNumber;
                            tvResult.setText(firstNumber + operation + secondNumber + "=" + (result));
                            break;
                        case "/":
                            if (secondNumber != 0) {
                                result = firstNumber / secondNumber;
                                tvResult.setText(firstNumber + operation + secondNumber + "=" + (result));
                            } else {
                                Toast.makeText(this, "Делить на ноль нельзя", Toast.LENGTH_SHORT).show();
                                tvResult.setText("Error");
                            }
                            break;
                    }
                } else {
                    Toast.makeText(this, "Необходимо вводить два числа", Toast.LENGTH_SHORT).show();
                }

                share.setVisibility(View.VISIBLE);
                proverka = true;

                break;
            case R.id.btn_plus:
                writeOperation("+");
                break;
            case R.id.btn_minus:
                writeOperation("-");
                break;

            case R.id.btn_multiplication:
                writeOperation("*");
                break;

            case R.id.btn_division:
                writeOperation("/");
                break;
            case R.id.btn_percent:

                firstNumberPercent = (Double.parseDouble(tvResult.getText().toString()) * 0.01);
                tvResult.setText(Double.toString(firstNumberPercent));
                proverka = true;
                break;


        }
    }


    public void writeSymbol(String s) {
        if (tvResult.getText().toString().equals("0") || proverka == true) {
            operation = "";
            share.setVisibility(View.GONE);
            tvResult.setText(s);
        } else {
            tvResult.append(s);
        }
        proverka = false;

    }

    public void writeOperation(String s) {
        if (operation == "") {
            firstNumber = Integer.parseInt(tvResult.getText().toString());
            // test = Double.toString(firstNumber);
            tvResult.append(s);
            operation = s;
        }
    }
}
