package com.example.grimm.raveimplement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RavePayManager;

import java.util.UUID;

public class activity_main extends AppCompatActivity implements View.OnClickListener {
    Button btnOne, btnTwo;
    final double amount_1 = 50;
    final double amount_2 = 100;
    String email = "aaladan453@gmail.com";
    String fName = "Abdulalim";
    String lName = "Ladan";
    String narration = "payment for food";
    String txRef = email + "" + UUID.randomUUID();
    String country = "NG";
    String currency = "NGN";

    final String publicKey = "FLWPUBK_TEST-654d95110c75e07a3c1228ae21b55ed5-X"; //Get your public key from your account
    final String encryptionKey = "FLWSECK_TEST7f4dccb62a55"; //Get your encryption key from your account


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOne = findViewById(R.id.btn_one);
        btnOne.setOnClickListener(this);
        btnTwo = findViewById(R.id.btn_two);
        btnTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
                Toast.makeText(this, "First Button", Toast.LENGTH_SHORT).show();
                makePayment(50); //calls payment method with amount 1
                break;
            case R.id.btn_two:
                Toast.makeText(this, "Second Button", Toast.LENGTH_SHORT).show();
                makePayment(100); //calls payment method with amount 2
                break;
            default:
                Toast.makeText(this, "NO button", Toast.LENGTH_SHORT).show();
                makePayment(200);
                break;
        }
    }

    public void makePayment(double amount){
        txRef = email +" "+  UUID.randomUUID().toString();

        /*
        Create instance of RavePayManager
         */
        new RavePayManager(this).setAmount(amount)
                .setCountry(country)
                .setCurrency(currency)
                .setEmail(email)
                .setfName(fName)
                .setlName(lName)
                .setNarration(narration)
                .setPublicKey(publicKey)
                .setEncryptionKey(encryptionKey)
                .setTxRef(txRef)
                .shouldDisplayFee(true)
                .acceptAccountPayments(false)
                .acceptCardPayments(true)
                .acceptMpesaPayments(false)
                .acceptGHMobileMoneyPayments(false)
                .onStagingEnv(false)
                .allowSaveCardFeature(true)
                .initialize();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*
         *  We advise you to do a further verification of transaction's details on your server to be
         *  sure everything checks out before providing service or goods.
         */
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            String message = data.getStringExtra("response");
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                Toast.makeText(this, "SUCCESS gjfcgj" + message, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Toast.makeText(this, "ERROR hgkjfhgh" + message, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Toast.makeText(this, "CANCELLED " + message, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}