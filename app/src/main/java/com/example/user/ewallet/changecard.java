package com.example.user.ewallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.stripe.android.model.Card;
import com.stripe.android.view.CardInputWidget;
import com.stripe.android.model.Token;

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


        //if (cardToSave != null) {
            updateCurrentCard = cardToSave.getNumber();
            textView2.setText(updateCurrentCard);
        //}



    }

}
