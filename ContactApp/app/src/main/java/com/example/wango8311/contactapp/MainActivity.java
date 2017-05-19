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
    EditText editName4;
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
        editName4 = (EditText) findViewById(R.id.searchParam);
        fields= new String[]{"Name: " ,"Number: ", "Gender: " };
    }

    protected void addData (View v){
        boolean isInserted = myDb.insertData(editName1.getText().toString(),editName2.getText().toString(),editName3.getText().toString());


        if (isInserted == true){
            Log.d("MyContact", "Sucess inserting data");
            //insert toast msg here
            Toast.makeText(v.getContext(), "Added!", Toast.LENGTH_LONG).show();
           }

        else {
            Log.d("MyContact", "Failure inserting data");
            //insert toast msg here
            Toast.makeText(v.getContext(), "Failure to Add", Toast.LENGTH_LONG).show();
            }
    }

    public void viewData(View v){
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0){
            //IT BE EMPTY YO
            showMessage("Error","There doesn't seem to be anything here");
            Toast.makeText(v.getContext(), "Database is empty", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        res.moveToFirst();
        //setup loop
        for (int i=0; i< res.getCount(); i++){
            for (int j = 1; j<=3; j++){
                buffer.append(fields[j-1]);
                buffer.append(res.getString(j));
                buffer.append("\n");

            }
            res.moveToNext();
            buffer.append("\n");
        }
        showMessage("Contacts:", buffer.toString());
    Toast.makeText(v.getContext(), "Data found, now dumping", Toast.LENGTH_LONG).show();

    //System.out.println(buffer);
    }

    public void searchData(View v){
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0){
            //IT BE EMPTY YO
            showMessage("Error","There doesn't seem to be anything here");
            Toast.makeText(v.getContext(), "Database is empty", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        res.moveToFirst();
        int o =0;
        //setup loop
        for (int i=0; i< res.getCount(); i++){
            if (res.getString(1).equals(editName4.getText().toString())) {
                for (int j = 1; j<=3; j++){

                    buffer.append(res.getString(j));
                    buffer.append("\n");

                }
                o=1;
            }
            res.moveToNext();

        }
        if (o==1) {
            showMessage("Result:", buffer.toString());
            Toast.makeText(v.getContext(), "Person Found", Toast.LENGTH_LONG).show();
        }
        else{
            showMessage("Error", "No one by this name");
            Toast.makeText(v.getContext(), "Person Not Found", Toast.LENGTH_LONG).show();
        }
    }

    private void showMessage(String error, String s){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setTitle(error);
        alertDialogBuilder.setMessage(s);
        alertDialogBuilder.show();
    }
}
