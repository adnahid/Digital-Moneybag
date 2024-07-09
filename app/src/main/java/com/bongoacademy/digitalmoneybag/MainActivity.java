package com.bongoacademy.digitalmoneybag;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.DatabaseHelper;
import com.bongoacademy.addData;

public class MainActivity extends AppCompatActivity {
    TextView finalBalance,tvExpenseBalance,tvAddExpense,tvShowAllDataExpense,
            tvIncomeBalance,tvAddIncome,tvShowAllDataIncome;

    DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finalBalance = findViewById(R.id.finalBalance);
        tvExpenseBalance = findViewById(R.id.tvExpenseBalance);
        tvAddExpense = findViewById(R.id.tvAddExpense);
        tvShowAllDataExpense = findViewById(R.id.tvShowAllDataExpense);
        tvIncomeBalance = findViewById(R.id.tvIncomeBalance);
        tvAddIncome = findViewById(R.id.tvAddIncome);
        tvShowAllDataIncome = findViewById(R.id.tvShowAllDataIncome);
        databaseHelper = new DatabaseHelper(this);



        //========OnClick
        tvAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData.Expense = true;
                Intent intent = new Intent(MainActivity.this,addData.class);
                startActivity(intent);
            }
        });



        tvAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData.Expense = false;
                Intent intent = new Intent(MainActivity.this,addData.class);
                startActivity(intent);
            }
        });

        tvShowAllDataExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showData.AddExpense = true;
                Intent intent = new Intent(MainActivity.this,showData.class);
                startActivity(intent);
            }
        });


        tvShowAllDataIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showData.AddExpense = false;
                Intent intent = new Intent(MainActivity.this,showData.class);
                startActivity(intent);
            }
        });

        updateUI();




    }


    public void updateUI(){
        tvExpenseBalance.setText("BTD "+databaseHelper.calculateTotalExpense());
        tvIncomeBalance.setText("BTD "+databaseHelper.calculateTotalIncome());
        double balance = databaseHelper.calculateTotalIncome()-databaseHelper.calculateTotalExpense();
        finalBalance.setText("BDT "+ balance);

    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateUI();
    }


}