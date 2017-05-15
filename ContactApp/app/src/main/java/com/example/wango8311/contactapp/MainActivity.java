package com.example.wango8311.contactapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        //add layout vars
        editName = (EditText) findViewById(R.id.editText_name);


    }

    protected void addData (View v){
        boolean isInserted = myDb.insertData(editName.getText().toString());
        Context context = getApplicationContext();
        Toast toast= new Toast(Context context);

        if (isInserted == true){
            Log.d("MyContact", "Sucess inserting data");
            //insert toast msg here

            CharSequence text = "Hello toast!";
            int duration = Toast.LENGTH_SHORT;

            toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else Log.d("MyContact", "Failure inserting data");
            //insert toast msg here

    }
}
