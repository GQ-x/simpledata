package com.example.alqahtag.myfiles;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextPhone;

    public static final String FILE_NAME = "contacts.txt";

    private File file;
    private FileOutputStream outputStream;
    private FileInputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.nameText);
        editTextPhone = (EditText) findViewById(R.id.phoneText);

//        initialize file object

//       create a new file at the system default location for the app with the given file name.
        file = new File(this.getFilesDir(), FILE_NAME);
    }

    public void save(View v){

        String data = editTextName.getText().toString() + "|" + editTextPhone.getText().toString();
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(data.getBytes());
            outputStream.close();
            Toast.makeText(v.getContext(),"data saved",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void load(View v){
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        try{
            inputStream = new FileInputStream(file);
            inputStream.read(bytes);
            inputStream.close();
            String data = new String(bytes);
            editTextName.setText(data.split("\\|")[0]);
            editTextPhone.setText(data.split("\\|")[1]);
            Toast.makeText(getBaseContext(), "data loaded", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
