package com.example.user.ewallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.view.CardInputWidget;
import com.stripe.android.Stripe;
import com.stripe.android.model.Token;

import static java.security.AccessController.getContext;

public class changecard extends AppCompatActivity {

    Card cardToSave;
    String updateCurrentCard;
    String updateCurrentCardType;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changecard);

        /*CardInputWidget mCardInputWidget = (CardInputWidget) findViewById(R.id.card_input_widget);
        cardToSave = mCardInputWidget.getCard();*/

    }

    public void onConfirmClick(String cardNumber, int cardExpMonth, int cardExpYear, String cardCVC){
        //View view =  inflater.inflate(R.layout.fragment_changecard, container, false);
        TextView textView2 = findViewById(R.id.textView2);

        CardInputWidget mCardInputWidget = (CardInputWidget) findViewById(R.id.card_input_widget2);
        cardToSave = mCardInputWidget.getCard();

        /*Stripe stripe = new Stripe(mContext, "pk_test_6pRNASCoBOKtIshFeQd4XMUh");
        stripe.createToken(
                cardToSave,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Send token to your server
                    }
                    public void onError(Exception error) {
                        // Show localized error message
                        Toast.makeText(getContext(),
                                error.getLocalizedString(getContext()),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        )*/

    }

}
