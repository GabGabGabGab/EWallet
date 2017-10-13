package com.example.user.ewallet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static String walletID;
    public static double balance;
    public static int loyaltyPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        //get User info
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Toast.makeText(this, "ERROR: NO EXTRAS FOUND!", Toast.LENGTH_SHORT).show();
            finish();
        }
        walletID = extras.getString("walletID");
        balance = extras.getDouble("balance");
        loyaltyPoint = extras.getInt("loyaltyPoint");

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, fragmentWallet.newInstance());
        transaction.commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.action_wallet:
                        //checkBalance(MainActivity.this, "https://martpay.000webhostapp.com/select_user.php");
                        fragment = fragmentWallet.newInstance();
                        break;
                    case R.id.action_fund:
                        fragment = fragmentFund.newInstance();
                        break;
                    /*case R.id.action_transfer:
                        fragment = TransferFragment.newInstance();
                        break;
                    case R.id.action_reward:
                        checkBalance(MainActivity.this, "https://martpay.000webhostapp.com/select_user.php");
                        fragment = RewardFragment.newInstance();
                        break;*/
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_main, fragment);
                transaction.commit();
                return true;
            }
        });

    }

    public void goChangeCard(View v){
        //Intent intent = new Intent(this, fragmentChangeCard.class);
        //startActivity(intent);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, fragmentChangeCard.newInstance());
        transaction.commit();
    }

    public void goAddFund(View v){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, fragmentFund.newInstance());
        transaction.commit();
    }

}
