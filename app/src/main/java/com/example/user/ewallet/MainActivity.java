package com.example.user.ewallet;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.stripe.android.model.Card;
import com.stripe.android.view.CardInputWidget;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static String walletID;
    public static double balance;
    public static int loyaltyPoint;
    public static String loginPassword;
    public static String currentCard;
    public static String currentCardType;
    String updateCurrentCard;
    String updateCurrentCardType;


    Card cardToSave;

    TextView tvBalance;

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
        loginPassword = extras.getString("loginPassword");
        currentCard = extras.getString("currentCard");
        currentCardType = extras.getString("currentCardType");

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
                        //checkBalance(MainActivity.this, "https://gabriellb-wp14.000webhostapp.com/select_user.php");
                        checkBalance(MainActivity.this, "https://martpay.000webhostapp.com/gab_select_user.php");
                        fragment = fragmentWallet.newInstance();
                        break;
                    case R.id.action_fund:
                        //checkCard(MainActivity.this, "https://gabriellb-wp14.000webhostapp.com/select_user.php");
                        checkCard(MainActivity.this, "https://martpay.000webhostapp.com/gab_select_user.php");
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
        Intent intent = new Intent(this, changecard.class);
        startActivity(intent);
        /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, fragmentChangeCard.newInstance());
        transaction.commit();*/
    }

    //Confirm bind/change card
    /*public void onConfirmClick(){
        //View view =  inflater.inflate(R.layout.fragment_changecard, container, false);
        CardInputWidget mCardInputWidget = (CardInputWidget) findViewById(R.id.card_input_widget2);
        cardToSave = mCardInputWidget.getCard();

        if (cardToSave != null) {
            updateCurrentCard = cardToSave.getNumber();
        }



    }*/

    //For Cancel button in change card UI
    public void goAddFund(View v){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, fragmentFund.newInstance());
        transaction.commit();
    }

    public void checkBalance(Context context, String url) {
        //mPostCommentResponse.requestStarted();
        RequestQueue queue = Volley.newRequestQueue(context);

        //Send data
        try {
            StringRequest postRequest = new StringRequest(
                    Request.Method.POST,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            JSONObject jsonObject;
                            try {
                                String err = "";
                                jsonObject = new JSONObject(response);
                                int success = jsonObject.getInt("success");
                                String message = jsonObject.getString("message");
                                if (success == 0) {
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                                } else if (success == 1) {
                                    balance = jsonObject.getDouble("Balance");
                                    loyaltyPoint = jsonObject.getInt("LoyaltyPoint");
                                    //Toast.makeText(getApplicationContext(), "Balance loaded", Toast.LENGTH_LONG).show();
                                    tvBalance = (TextView) findViewById(R.id.tvBalance);
                                    if (tvBalance != null)
                                        tvBalance.setText(String.format("RM %.2f", MainActivity.balance));
                                } else if (success == 2) {
                                    //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                    err += "Wallet not found.";

                                } else {
                                    Toast.makeText(getApplicationContext(), "err", Toast.LENGTH_SHORT).show();

                                }
                                //show error
                                if (err.length() > 0) {
                                    Toast.makeText(getApplicationContext(), err, Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error. " + error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("WalletID", walletID);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            queue.add(postRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkCard(Context context, String url) {
        //mPostCommentResponse.requestStarted();
        RequestQueue queue = Volley.newRequestQueue(context);

        //Send data
        try {
            StringRequest postRequest = new StringRequest(
                    Request.Method.POST,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            JSONObject jsonObject;
                            try {
                                String err = "";
                                jsonObject = new JSONObject(response);
                                int success = jsonObject.getInt("success");
                                String message = jsonObject.getString("message");
                                if (success == 0) {
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                                } else if (success == 1) {
                                    //balance = jsonObject.getDouble("Balance");
                                    //loyaltyPoint = jsonObject.getInt("LoyaltyPoint");
                                    //Toast.makeText(getApplicationContext(), "Balance loaded", Toast.LENGTH_LONG).show();
                                    tvBalance = (TextView) findViewById(R.id.tvBalance);
                                    TextView tvCardType = (TextView) findViewById(R.id.tvCardType);
                                    TextView tvCardEnding = (TextView) findViewById(R.id.tvCardEnding);
                                    //if (tvBalance != null)
                                        //tvBalance.setText(String.format("RM %.2f", MainActivity.balance));
                                    if (tvCardType != null)
                                        tvCardType.setText(tvCardType.getText().toString() + MainActivity.currentCardType);
                                    if (tvCardEnding != null)
                                        tvCardEnding.setText(tvCardEnding.getText().toString() + MainActivity.currentCard);
                                } else if (success == 2) {
                                    //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                    err += "Wallet not found.";

                                } else {
                                    Toast.makeText(getApplicationContext(), "err", Toast.LENGTH_SHORT).show();

                                }
                                //show error
                                if (err.length() > 0) {
                                    Toast.makeText(getApplicationContext(), err, Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error. " + error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("WalletID", walletID);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            queue.add(postRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(currentCard.matches("TBD")){
            Toast.makeText(this, "Please bind a card to wallet.", Toast.LENGTH_LONG).show();
            /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_main, fragmentChangeCard.newInstance());
            transaction.commit();*/
        }

    }

}
