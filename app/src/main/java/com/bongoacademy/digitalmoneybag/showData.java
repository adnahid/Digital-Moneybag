package com.bongoacademy.digitalmoneybag;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class showData extends AppCompatActivity {
    ListView showList;
    TextView tvShowData;
    DatabaseHelper db;

    EditText editText;

    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap<String,String> hashMap = new HashMap<>();

    public static boolean AddExpense = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_data);

        showList = findViewById(R.id.showList);
        editText = findViewById(R.id.editText);
        db = new DatabaseHelper(this);
        ShowAllData();








    }


    public void ShowAllData(){

        if (AddExpense == true){
            editText.setText("Expense Data List");

            Cursor cursor = db.getAllData();
            if (cursor!=null && cursor.getCount()>0){
                arrayList = new ArrayList<>();

                while (cursor.moveToNext()){

                    int id = cursor.getInt(0);
                    double amount   = cursor.getDouble(1);
                    String reason  = cursor.getString(2);
                    double time   = cursor.getDouble(3);

                    hashMap = new HashMap<>();
                    hashMap.put("id",""+id);
                    hashMap.put("amount",""+amount);
                    hashMap.put("reason",""+reason);
                    hashMap.put("time",""+time);
                    arrayList.add(hashMap);
                }


                showList.setAdapter(new adapter ());

            }else {
                editText.append("\n Data Not Found");
            }



        }else {
            editText.setText("Income Data List");

            Cursor cursor = db.getAllIncomeData();
            if (cursor!=null && cursor.getCount()>0){
                arrayList = new ArrayList<>();

                while (cursor.moveToNext()){

                    int id = cursor.getInt(0);
                    double amount   = cursor.getDouble(1);
                    String reason  = cursor.getString(2);
                    double time   = cursor.getDouble(3);

                    hashMap = new HashMap<>();
                    hashMap.put("id",""+id);
                    hashMap.put("amount",""+amount);
                    hashMap.put("reason",""+reason);
                    hashMap.put("time",""+time);
                    arrayList.add(hashMap);
                }


                showList.setAdapter(new adapter ());

            }else {
                tvShowData.append("\n Data Not Found");
            }

        }
    }



    //adapter
    public class adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = getLayoutInflater();
            View MyView = inflater.inflate(R.layout.item_layout,viewGroup,false);

            TextView tvID = MyView.findViewById(R.id.tvID);
            TextView tvReason = MyView.findViewById(R.id.tvReason);
            TextView addBalance = MyView.findViewById(R.id.addBalance);
            TextView deletingData = MyView.findViewById(R.id.deletingData);


            hashMap = arrayList.get(position);
            String id = hashMap.get("id");
            String amount = hashMap.get("amount");
            String reason = hashMap.get("reason");
            String time = hashMap.get("time");

            tvID.setText("ID"+id);
            addBalance.setText("BDT "+amount);
            tvReason.setText(reason);

            deletingData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (AddExpense==true){
                        db.getExpenseIdDelete(id);
                        ShowAllData();
                    }else {
                        db.getIncomeIdDelete(id);
                        ShowAllData();
                    }

                }
            });




            return MyView;
        }
    }

}