package example.assignmentone.cp3406.converter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CurrencyOptionsActivity extends AppCompatActivity {

    Boolean currencyOptionString;
    ImageButton audButton; ImageButton eurButton; ImageButton gbpButton;
    ImageButton hkdButton; ImageButton idrButton; ImageButton inrButton;
    ImageButton jpyButton; ImageButton myrButton; ImageButton nzdButton;
    ImageButton sgdButton; ImageButton thbButton; ImageButton usdButton;
    Button returnButton;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
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
    protected void onStart() {
        super.onStart();
        currencyOptionString = preferences.getBoolean("currencyBoolean", true );
    }

    @Override
    protected void onResume() {
        super.onResume();
        currencyOptionString = preferences.getBoolean("currencyBoolean", true );
    }

    public void onButtonPress(View view){
        Intent menuActivity = new Intent(this, ConvertCurrency.class);

        switch(view.getId()){
            case (R.id.audButton):
                if(currencyOptionString){
                    preferences.edit().putString("Option1", "aud").apply();
                }
                else{
                    preferences.edit().putString("Option2", "aud").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.eurButton):
                if(currencyOptionString){
                    preferences.edit().putString("Option1", "eur").apply();
                }
                else{
                    preferences.edit().putString("Option2", "eur").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.gbpButton):
                if(currencyOptionString){
                    preferences.edit().putString("Option1", "gbp").apply();
                }
                else{
                    preferences.edit().putString("Option2", "gbp").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.hkdButton):
                if(currencyOptionString){
                    preferences.edit().putString("Option1", "hkd").apply();
                }
                else{
                    preferences.edit().putString("Option2", "hkd").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.idrButton):
                if(currencyOptionString){
                    preferences.edit().putString("Option1", "idr").apply();
                }
                else{
                    preferences.edit().putString("Option2", "idr").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.inrButton):
                if(currencyOptionString){
                    preferences.edit().putString("Option1", "inr").apply();
                }
                else{
                    preferences.edit().putString("Option2", "inr").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.jpyButton):
                if(currencyOptionString){
                    preferences.edit().putString("Option1", "jpy").apply();
                }
                else{
                    preferences.edit().putString("Option2", "jpy").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.myrButton):
                if(currencyOptionString){
                    preferences.edit().putString("Option1", "myr").apply();
                }
                else{
                    preferences.edit().putString("Option2", "myr").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.nzdButton):
                if(currencyOptionString){
                    preferences.edit().putString("Option1", "nzd").apply();
                }
                else{
                    preferences.edit().putString("Option2", "nzd").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.sgdButton):
                if(currencyOptionString){
                    preferences.edit().putString("Option1", "sgd").apply();
                }
                else{
                    preferences.edit().putString("Option2", "sgd").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.thbButton):
                if(currencyOptionString){
                    preferences.edit().putString("Option1", "thb").apply();
                }
                else{
                    preferences.edit().putString("Option2", "thb").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.usdButton):
                if(currencyOptionString){
                    preferences.edit().putString("Option1", "usd").apply();
                }
                else{
                    preferences.edit().putString("Option2", "usd").apply();
                }
                startActivity(menuActivity);
                break;

            case (R.id.returnButton):
                startActivity(menuActivity);
                break;
        }
    }
}
