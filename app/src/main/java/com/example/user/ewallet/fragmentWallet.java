package com.example.user.ewallet;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;

/**
 * Created by Gabb on 11/10/2017.
 */

public class fragmentWallet extends Fragment {


    public static fragmentWallet newInstance() {
        fragmentWallet fragment = new fragmentWallet();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallet, container, false);}


}
