package com.example.wango8311.contactapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText search;
    String[] fields;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        search = (EditText) findViewById(R.id.sText);
        fields= new String[]{"Name: " ,"Number: ", "Gender: " };
        myDb = new DatabaseHelper(this);
    }


    public void searchDataN(View v){
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
            if (res.getString(1).equals(search.getText().toString())) {
                for (int j = 1; j<=3; j++){
                    buffer.append(fields[j-1]);
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

