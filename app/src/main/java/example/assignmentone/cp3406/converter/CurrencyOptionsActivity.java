package example.assignmentone.cp3406.converter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CurrencyOptionsActivity extends AppCompatActivity {
    Boolean currencyOptionStatus; //Boolean variable checks if user has clicked on initial or converted currency button
    ImageButton audButton; ImageButton eurButton; ImageButton gbpButton;
    ImageButton hkdButton; ImageButton idrButton; ImageButton inrButton;
    ImageButton jpyButton; ImageButton myrButton; ImageButton nzdButton;
    ImageButton sgdButton; ImageButton thbButton; ImageButton usdButton;
    Button returnButton;
    SharedPreferences preferences; //used to store and manage values based on user activity from current and other activities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_selection);
        audButton = findViewById(R.id.audButton);
        eurButton = findViewById(R.id.eurButton);
        gbpButton = findViewById(R.id.gbpButton);
        hkdButton = findViewById(R.id.hkdButton);
        idrButton = findViewById(R.id.idrButton);
        inrButton = findViewById(R.id.inrButton);
        jpyButton = findViewById(R.id.jpyButton);
        myrButton = findViewById(R.id.myrButton);
        nzdButton = findViewById(R.id.nzdButton);
        sgdButton = findViewById(R.id.sgdButton);
        thbButton = findViewById(R.id.thbButton);
        usdButton = findViewById(R.id.usdButton);
        returnButton = findViewById(R.id.returnButton);
        preferences = getSharedPreferences("Settings", MODE_PRIVATE);
    }

    @Override
    protected void onStart() {  //onStart, get
        super.onStart();
        currencyOptionStatus = preferences.getBoolean("currencyBoolean", true );
    }

    @Override
    protected void onResume() {
        super.onResume();
        currencyOptionStatus = preferences.getBoolean("currencyBoolean", true );
    }

    /*Method onButtonPress for android:onClick in the activity_currency_selection.xml file
    Checks the Boolean variable currencyOptionStatus to assign values accordingly;
    True appends the preferences values to reflect changes in the initial currency type
    False appends the preferences values to reflect changes in the converting currency type*/
    public void onButtonPress(View view){
        Intent menuActivity = new Intent(this, ConvertCurrency.class);
        switch(view.getId()){
            case (R.id.audButton):
                if(currencyOptionStatus){
                    preferences.edit().putString("Option1", "aud").apply();
                }
                else{
                    preferences.edit().putString("Option2", "aud").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.eurButton):
                if(currencyOptionStatus){
                    preferences.edit().putString("Option1", "eur").apply();
                }
                else{
                    preferences.edit().putString("Option2", "eur").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.gbpButton):
                if(currencyOptionStatus){
                    preferences.edit().putString("Option1", "gbp").apply();
                }
                else{
                    preferences.edit().putString("Option2", "gbp").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.hkdButton):
                if(currencyOptionStatus){
                    preferences.edit().putString("Option1", "hkd").apply();
                }
                else{
                    preferences.edit().putString("Option2", "hkd").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.idrButton):
                if(currencyOptionStatus){
                    preferences.edit().putString("Option1", "idr").apply();
                }
                else{
                    preferences.edit().putString("Option2", "idr").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.inrButton):
                if(currencyOptionStatus){
                    preferences.edit().putString("Option1", "inr").apply();
                }
                else{
                    preferences.edit().putString("Option2", "inr").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.jpyButton):
                if(currencyOptionStatus){
                    preferences.edit().putString("Option1", "jpy").apply();
                }
                else{
                    preferences.edit().putString("Option2", "jpy").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.myrButton):
                if(currencyOptionStatus){
                    preferences.edit().putString("Option1", "myr").apply();
                }
                else{
                    preferences.edit().putString("Option2", "myr").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.nzdButton):
                if(currencyOptionStatus){
                    preferences.edit().putString("Option1", "nzd").apply();
                }
                else{
                    preferences.edit().putString("Option2", "nzd").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.sgdButton):
                if(currencyOptionStatus){
                    preferences.edit().putString("Option1", "sgd").apply();
                }
                else{
                    preferences.edit().putString("Option2", "sgd").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.thbButton):
                if(currencyOptionStatus){
                    preferences.edit().putString("Option1", "thb").apply();
                }
                else{
                    preferences.edit().putString("Option2", "thb").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.usdButton):
                if(currencyOptionStatus){
                    preferences.edit().putString("Option1", "usd").apply();
                }
                else{
                    preferences.edit().putString("Option2", "usd").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.returnButton): //returns to activity_convert_currency without any changes
                startActivity(menuActivity);
                break;
        }
    }
}
