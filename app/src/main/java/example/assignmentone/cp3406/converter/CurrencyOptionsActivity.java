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
    Boolean settingsOptionStatus;
    ImageButton audButton; ImageButton eurButton; ImageButton gbpButton;
    ImageButton hkdButton; ImageButton idrButton; ImageButton inrButton;
    ImageButton jpyButton; ImageButton myrButton; ImageButton nzdButton;
    ImageButton sgdButton; ImageButton thbButton; ImageButton usdButton;
    Button returnButton;
    SharedPreferences preferences; //used to store and manage values based on user activity from current and other activities

    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate, creating objects to be used
        preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        setPrefTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_selection);
        setTitle("List of Currency Types");
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

    }

    @Override
    protected void onStart() {  //onStart, get
        super.onStart();
        settingsOptionStatus = preferences.getBoolean("settingsBoolean", false);
        currencyOptionStatus = preferences.getBoolean("currencyBoolean", true );
    }

    @Override
    protected void onResume() {
        super.onResume();
        settingsOptionStatus = preferences.getBoolean("settingsBoolean", false);
        currencyOptionStatus = preferences.getBoolean("currencyBoolean", true );
    }

    /*Method onButtonPress for android:onClick in the activity_currency_selection.xml file
    Checks the Boolean variable currencyOptionStatus to assign values accordingly;
    True appends the preferences values to reflect changes in the initial currency type
    False appends the preferences values to reflect changes in the converting currency type*/
    public void onButtonPress(View view){
        Intent returnToCurrencyConverter = new Intent(this, ConvertCurrency.class);
        Intent returnToSettings = new Intent(this, SettingsActivity.class);
        switch(view.getId()){
            case (R.id.audButton):
                if(currencyOptionStatus && !settingsOptionStatus){  //IF button clicked is for initial input, and not from settings page
                    preferences.edit().putString("Option1", "aud").apply();
                }
                else if (!currencyOptionStatus && !settingsOptionStatus){   //Else if button clicked is for converting result, and not from settings page
                    preferences.edit().putString("Option2", "aud").apply();
                }
                else{   //Else button clicked is from settings page, appends preference based on user input
                    preferences.edit().putString("settingsPref", "aud").apply();
                    preferences.edit().putBoolean("settingsBoolean", false).apply();
                }
                finish();
                break;

            case (R.id.eurButton):
                if(currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option1", "eur").apply();
                }
                else if (!currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option2", "eur").apply();
                }
                else{
                    preferences.edit().putString("settingsPref", "eur").apply();
                    preferences.edit().putBoolean("settingsBoolean", false).apply();
                }
                finish();
                break;

            case (R.id.gbpButton):
                if(currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option1", "gbp").apply();
                }
                else if (!currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option2", "gbp").apply();
                }
                else{
                    preferences.edit().putString("settingsPref", "gbp").apply();
                    preferences.edit().putBoolean("settingsBoolean", false).apply();
                }
                finish();
                break;

            case (R.id.hkdButton):
                if(currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option1", "hkd").apply();
                }
                else if (!currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option2", "hkd").apply();
                }
                else{
                    preferences.edit().putString("settingsPref", "hkd").apply();
                    preferences.edit().putBoolean("settingsBoolean", false).apply();
                }
                finish();
                break;

            case (R.id.idrButton):
                if(currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option1", "idr").apply();
                }
                else if (!currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option2", "idr").apply();
                }
                else{
                    preferences.edit().putString("settingsPref", "idr").apply();
                    preferences.edit().putBoolean("settingsBoolean", false).apply();
                }
                finish();
                break;

            case (R.id.inrButton):
                if(currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option1", "inr").apply();
                }
                else if (!currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option2", "inr").apply();
                }
                else{
                    preferences.edit().putString("settingsPref", "inr").apply();
                    preferences.edit().putBoolean("settingsBoolean", false).apply();
                }
                finish();
                break;

            case (R.id.jpyButton):
                if(currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option1", "jpy").apply();
                }
                else if (!currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option2", "jpy").apply();
                }
                else{
                    preferences.edit().putString("settingsPref", "jpy").apply();
                    preferences.edit().putBoolean("settingsBoolean", false).apply();
                }
                finish();
                break;

            case (R.id.myrButton):
                if(currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option1", "myr").apply();
                }
                else if (!currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option2", "myr").apply();
                }
                else{
                    preferences.edit().putString("settingsPref", "myr").apply();
                    preferences.edit().putBoolean("settingsBoolean", false).apply();
                }
                finish();
                break;

            case (R.id.nzdButton):
                if(currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option1", "nzd").apply();
                }
                else if (!currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option2", "nzd").apply();
                }
                else{
                    preferences.edit().putString("settingsPref", "nzd").apply();
                    preferences.edit().putBoolean("settingsBoolean", false).apply();
                }
                finish();
                break;

            case (R.id.sgdButton):
                if(currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option1", "sgd").apply();
                }
                else if (!currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option2", "sgd").apply();
                }
                else{
                    preferences.edit().putString("settingsPref", "sgd").apply();
                    preferences.edit().putBoolean("settingsBoolean", false).apply();
                }
                finish();
                break;

            case (R.id.thbButton):
                if(currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option1", "thb").apply();
                }
                else if (!currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option2", "thb").apply();
                }
                else{
                    preferences.edit().putString("settingsPref", "thb").apply();
                    preferences.edit().putBoolean("settingsBoolean", false).apply();
                }
                finish();
                break;

            case (R.id.usdButton):
                if(currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option1", "usd").apply();
                }
                else if (!currencyOptionStatus && !settingsOptionStatus){
                    preferences.edit().putString("Option2", "usd").apply();
                }
                else{
                    preferences.edit().putString("settingsPref", "usd").apply();
                    preferences.edit().putBoolean("settingsBoolean", false).apply();
                }
                finish();
                break;

            case (R.id.returnButton): //returns to activity_convert_currency without any changes
                finish();
                break;
        }
    }

    public void setPrefTheme(){
        String prefTheme = preferences.getString("themeName", "AppTheme");
        if(prefTheme.equals("AppTheme")){
            setTheme(R.style.AppTheme);
        }
        else if(prefTheme.equals("NightMode")){
            setTheme(R.style.NightMode);
        }
    }
}
