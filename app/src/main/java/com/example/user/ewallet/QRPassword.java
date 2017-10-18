package com.example.user.ewallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class QRPassword extends AppCompatActivity {

    static String passedQRpw, QRpw;
    EditText etQRpw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrpassword);

        etQRpw = (EditText)findViewById(R.id.etQRpw);

        //get User info
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Toast.makeText(this, "ERROR: NO EXTRAS FOUND!", Toast.LENGTH_SHORT).show();
            finish();
        }
        passedQRpw = extras.getString("QRpw");


    }

    public void onProceedClick(View v){

        QRpw = etQRpw.getText().toString();
        if (QRpw == passedQRpw){

        }

    }

}
