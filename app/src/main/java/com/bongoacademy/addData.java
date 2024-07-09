package com.bongoacademy;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.DatabaseHelper;
import com.bongoacademy.digitalmoneybag.R;

public class addData extends AppCompatActivity {
    TextView tvTitle,textShow;
    EditText edAmount,edReason;
    Button insertBtn,showBtn;
    DatabaseHelper dbHelper;
    public static boolean Expense = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_data);
        tvTitle = findViewById(R.id.tvTitle);
        textShow = findViewById(R.id.textShow);
        edAmount = findViewById(R.id.edAmount);
        edReason = findViewById(R.id.edReason);
        insertBtn = findViewById(R.id.insertBtn);
        showBtn = findViewById(R.id.showBtn);
        dbHelper = new DatabaseHelper(this);


        if (Expense== true){
            tvTitle.setText("Add Expense");
        }else tvTitle.setText("Add Income");

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stringAmount = edAmount.getText().toString();
                String stringReason = edReason.getText().toString();
                double amount = Double.parseDouble(stringAmount);

                if (edAmount.length()>0 && edReason.length()>0){
                    tvTitle.setText("Data Inserted");
                    if (Expense== true){
                        dbHelper.addExpense(amount,stringReason);
                        tvTitle.setText("Expense Added");

                    }else {
                       dbHelper.addIncome(amount,stringReason);
                        tvTitle.setText("Income Added");
                    }
                }else {
                    tvTitle.setText("Please Fill the Data");

                }

            }
        });

/*
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dbHelper.getAllData();
                textShow.setText(" Total Data "+cursor.getCount());

                if (cursor!=null && cursor.getCount()>0){
                    while (cursor.moveToNext()){
                        int id = cursor.getInt(0);
                        String Expense = cursor.getString(1);
                        String Reason = cursor.getString(2);
                        textShow.append("\n ID:"+id+"\n Reason:"+Reason+"\n Expense:"+Expense);

                    }

                }else {

                    textShow.setText("Data Not Found");
                }
            }
        });

 */




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}