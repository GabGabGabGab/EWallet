package com.example.user.ewallet;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Gabb on 11/10/2017.
 */

public class fragmentWallet extends Fragment {

    TextView tvBalance;
    public static boolean allowRefresh;
    String loginPassword;
    int currentCard;

    public static fragmentWallet newInstance() {
        fragmentWallet fragment = new fragmentWallet();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_wallet, container, false);
        tvBalance = (TextView) inflatedView.findViewById(R.id.tvBalance);
        tvBalance.setText(String.format("RM %.2f" ,MainActivity.balance ));
        allowRefresh=false;

        // Inflate the layout for this fragment
        return inflatedView;


    }

    public void onResume() {
        super.onResume();
        if (allowRefresh)
        {
            allowRefresh = false;
            tvBalance.setText(String.format("RM %.2f" ,MainActivity.balance ));
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }


}
