package example.assignmentone.cp3406.converter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ConvertCurrency extends AppCompatActivity {

    EditText initialCurrency;
    EditText changedCurrency;
    TextView initialCurrencyString;
    TextView changedCurrencyString;
    double userInput;
    double userInputUsd;
    double convertedResult;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_currency);
        initialCurrency = findViewById(R.id.initialCurrency);
        changedCurrency = findViewById(R.id.changedCurrency);
        initialCurrencyString = findViewById(R.id.initialCurrencyString);
        changedCurrencyString = findViewById(R.id.changedCurrencyString);
        preferences = getSharedPreferences("Settings" , MODE_PRIVATE);
    }

    @Override
    protected void onStart(){
        super.onStart();
        converterMain();
    }

    protected void onResume(){
        super.onResume();
        converterMain();
    }

    public void onButtonPress(View view){
        Intent returnToMenu = new Intent(this, MainActivity.class); //Intent for returning to the main menu
        Intent goToCurrencyMenu = new Intent(this, CurrencyOptionsActivity.class); //Intent for switching to the currency options menu
        switch(view.getId()){
            case R.id.homeButton:
                startActivity(returnToMenu);
                break;

            case R.id.initialCurrencyButton:
                preferences.edit().putBoolean("currencyBoolean", true).apply();
                startActivity(goToCurrencyMenu);
                break;

            case R.id.changedCurrencyButton:
                preferences.edit().putBoolean("currencyBoolean", false).apply();
                startActivity(goToCurrencyMenu);
                break;

            case R.id.resetButton:
                initialCurrencyString.setText("Choose a currency!");
                changedCurrencyString.setText("Choose a currency!");
                preferences.edit().putString("Option1", null).apply();
                preferences.edit().putString("Option2", null).apply();
                initialCurrency.setText("");
                changedCurrency.setText("");
                userInput = 0;
                userInputUsd = 0;
                convertedResult = 0;
        }
    }

   public void converterMain(){

       initialCurrencyString.setText(preferences.getString("Option1", "Choose a currency!"));
       changedCurrencyString.setText(preferences.getString("Option2", "Choose a currency!"));
       initialCurrency.setText(preferences.getString("initialUserInput", null));

       initialCurrency.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               try{
                   preferences.edit().putString("initialUserInput", charSequence.toString()).apply(); //to save the value when returning from choosing currency type
                   userInput = Double.parseDouble(charSequence.toString());
                   if(!initialCurrencyString.getText().toString().equals("Choose a currency!")  && !changedCurrencyString.getText().toString().equals("Choose a currency!")){
                       convertUSD(initialCurrencyString.getText().toString(), userInput);
                       convertActual(changedCurrencyString.getText().toString(), userInputUsd);
                       changedCurrency.setText(Double.toString(convertedResult));
                   }
               }
               catch(Exception e){}
           }

           @Override
           public void afterTextChanged(Editable editable) {
           }
       });

       changedCurrency.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });
   }

   public void convertUSD(String currencyType, double userInput){     //converting all currency types to USD, then to respective selected types
       switch(currencyType){
           case "usd":
               userInputUsd = userInput;
               break;
           case "gbp":
               userInputUsd = userInput * 1.34;
               break;
           case "sgd":
               userInputUsd = userInput * 0.74;
               break;
           case "thb":
               userInputUsd = userInput * 0.031;
               break;
           case "myr":
               userInputUsd = userInput * 0.25;
               break;
           case "jpy":
               userInputUsd = userInput * 0.0088;
               break;
           case "eur":
               userInputUsd = userInput * 1.18;
               break;
           case "aud":
               userInputUsd = userInput * 0.75;
               break;
           case "idr":
               userInputUsd = userInput * 0.000074;
               break;
           case "nzd":
               userInputUsd = userInput * 0.69;
               break;
           case "inr":
               userInputUsd = userInput * 0.016;
               break;
           case "hkd":
               userInputUsd = userInput * 0.13;
               break;
           case "":
           break;
       }
   }

   public void convertActual (String currencyType, double userInputUsd){
        switch(currencyType){
            case "usd":
                convertedResult = userInputUsd;
                break;
            case "gbp":
                convertedResult = userInputUsd * 0.75;
                break;
            case "sgd":
                convertedResult = userInputUsd * 1.35;
                break;
            case "thb":
                convertedResult = userInputUsd * 32.62;
                break;
            case "myr":
                convertedResult = userInputUsd * 4.07;
                break;
            case "jpy":
                convertedResult = userInputUsd * 113.35;
                break;
            case "eur":
                convertedResult = userInputUsd * 0.85;
                break;
            case "aud":
                convertedResult = userInputUsd * 1.33;
                break;
            case "idr":
                convertedResult = userInputUsd * 13549;
                break;
            case "nzd":
                convertedResult = userInputUsd * 1.45;
                break;
            case "inr":
                convertedResult = userInputUsd * 64.39;
                break;
            case "hkd":
                convertedResult = userInputUsd * 7.81;
                break;
            case "":
                break;
        }
   }
}
