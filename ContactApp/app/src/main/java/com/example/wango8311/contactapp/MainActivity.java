package com.example.wango8311.contactapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName1;
    EditText editName2;
    EditText editName3;
    Button btnAddData;
    String[] fields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        //add layout vars
        editName1 = (EditText) findViewById(R.id.editText1);
        editName2 = (EditText) findViewById(R.id.editText2);
        editName3 = (EditText) findViewById(R.id.editText3);


    }

    protected void addData (View v){
        boolean isInserted = myDb.insertData(editName1.getText().toString(),editName2.getText().toString(),editName3.getText().toString());


        if (isInserted == true){
            Log.d("MyContact", "Sucess inserting data");
            //insert toast msg here

            Toast.makeText(v.getContext(), "YAY", Toast.LENGTH_LONG).show();
            showMessage("GTG", "ok");
        }

        else Log.d("MyContact", "Failure inserting data");
            //insert toast msg here

            Toast.makeText(v.getContext(), "too bad", Toast.LENGTH_LONG).show();
            showMessage("nah", "nope");
    }

    public void viewData(){
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0){
            //IT BE EMPTY YO
            showMessage("its empty","no datuh in DB");
            Toast.makeText(getApplicationContext(), "no be datas", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        res.moveToFirst();
        //setup loop
        for (int i=0; i< res.getCount(); i++){
            for (int j = 1; j<=3; j++){
                buffer.append(fields[j-1]);
                buffer.append(res.getString(j));
                buffer.append("/n");

            }
            res.moveToNext();
        }
    showMessage("datafound", buffer.toString());
        Toast.makeText(getApplicationContext(), "df", Toast.LENGTH_LONG).show();
    System.out.println(buffer);
    }

    private void showMessage(String error, String s){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setTitle(error);
        alertDialogBuilder.setMessage(s);
        alertDialogBuilder.show();
    }
}
