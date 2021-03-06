package com.example.user.ewallet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.user.ewallet.Obejct.Inventory;
import com.example.user.ewallet.Obejct.Menu;
import com.example.user.ewallet.Obejct.Order;
import com.example.user.ewallet.Obejct.Product;
import com.example.user.ewallet.Obejct.Purchase_order;

import java.util.List;

public class Menu_screen extends AppCompatActivity {
    public static List<Product> lList = null;
    public static List<Inventory> SList = null;
    public static List<Purchase_order> OList = null;
    public static List<Order> ORDERList = null;
    public static List<Menu> MList = null;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_Menu:
                MenuFragment m = new MenuFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content,m).commit();
                break;

                case R.id.navigation_Order:
                OrderFragment o = new OrderFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content,o).commit();
                    break;

                case R.id.navigation_Inventory:
                    InventoryFragment i = new InventoryFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content,i).commit();
                    break;

                case R.id.navigation_Report:



            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }


}
