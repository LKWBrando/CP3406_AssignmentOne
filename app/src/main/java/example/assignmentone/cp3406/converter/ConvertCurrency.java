package example.assignmentone.cp3406.converter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConvertCurrency extends AppCompatActivity {
    EditText inputCurrency;
    TextView convertedCurrency;
    TextView inputCurrencyType;
    TextView convertedCurrencyType;
    TextView equalsMessage;
    ImageView inputImage;
    ImageView convertedImage;
    double userInput; //used to store user input for calculations
    double userInputUsd; //used in the conversion calculations
    double convertedResult; //used in the conversion calculations
    SharedPreferences preferences; //used to store and manage values based on user activity from current and other activities

    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate, creates all required objects
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_currency);
        setTitle("Currency Converter");
        inputCurrency = findViewById(R.id.inputCurrency);
        convertedCurrency = findViewById(R.id.convertedCurrency);
        inputCurrencyType = findViewById(R.id.inputCurrencyType);
        convertedCurrencyType = findViewById(R.id.convertedCurrencyType);
        equalsMessage = findViewById(R.id.equalsMessage);
        inputImage = findViewById(R.id.inputImage);
        convertedImage = findViewById(R.id.convertedImage);
        preferences = getSharedPreferences("Settings" , MODE_PRIVATE);
    }

    @Override
    protected void onStart(){ //onStart
        super.onStart();
        currencyConverterMain();
    }

    protected void onResume(){ //onResume
        super.onResume();
        currencyConverterMain();
    }

    /*Main method run onStart and onResume
    Sets selected preferences, string values, and images, as well as converting user input according to selected currency types*/
    public void currencyConverterMain(){
        //Checks SharedPreferences for user input on the currency type, then displays the corresponding value
        inputCurrencyType.setText(preferences.getString("Option1", "Choose a currency!"));
        convertedCurrencyType.setText(preferences.getString("Option2", "Choose a currency!"));
        inputCurrency.setText(preferences.getString("initialUserInput", null)); //Saves the user's input between switching activities.

        String imageFileName;
        int resId;
        //sets image of currency type according to current selected user input
        imageFileName = inputCurrencyType.getText().toString();
        if(imageFileName.equals("Choose a currency!")){
            inputImage.setImageResource(R.drawable.dollar_sign);
        }else{
        resId = getResources().getIdentifier(imageFileName, "drawable", getPackageName());
        inputImage.setImageResource(resId);}

        //sets image of currency type according to current selected user input
        imageFileName = convertedCurrencyType.getText().toString();
        if(imageFileName.equals("Choose a currency!")){
            convertedImage.setImageResource(R.drawable.dollar_sign);
        }else{
        resId = getResources().getIdentifier(imageFileName, "drawable", getPackageName());
        convertedImage.setImageResource(resId);}

        inputCurrency.addTextChangedListener(new TextWatcher() { //Listener for EditText inputCurrency
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    preferences.edit().putString("initialUserInput", charSequence.toString()).apply(); //to save the value when returning from choosing currency type
                    userInput = Double.parseDouble(charSequence.toString()); //converts user input into EditText to string

                    //IF/ELSE statement to check if both currency types (initial and converted) have been selected, and if so, proceeds with the conversion
                    if(!inputCurrencyType.getText().toString().equals("Choose a currency!")  && !convertedCurrencyType.getText().toString().equals("Choose a currency!")){
                        convertUSD(inputCurrencyType.getText().toString(), userInput); //Calling conversion methods
                        convertActual(convertedCurrencyType.getText().toString(), userInputUsd); //Calling conversion methods
                        convertedCurrency.setText(Double.toString(roundConvertedValue(convertedResult, 2))); //rounding off to two decimal places, then parsing to String
                        equalsMessage.setText("Equates to...");
                    }
                }
                catch(Exception e){} //Throws exception
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
   }

    public void onButtonPress(View view){   //Method called for android:onClick in the activity_convert_currency.xml file
        Intent goToOptions = new Intent(this, SettingsActivity.class);
        Intent returnToMenu = new Intent(this, MainActivity.class); //Intent for returning to the main menu
        Intent goToCurrencyMenu = new Intent(this, CurrencyOptionsActivity.class);  //Intent for switching to the currency options menu
        switch(view.getId()){
            case R.id.homeButton:   //Home button returns to the initial screen
                startActivity(returnToMenu);
                break;

            case R.id.optionsButton:
                startActivity(goToOptions);
                break;

            case R.id.initialCurrencyButton:    //Starts activity that allows users to choose currency type
                preferences.edit().putBoolean("currencyBoolean", true).apply();
                startActivity(goToCurrencyMenu);
                break;

            case R.id.convertedCurrencyButton:    //Starts activity that allows users to choose currency type
                preferences.edit().putBoolean("currencyBoolean", false).apply();
                startActivity(goToCurrencyMenu);
                break;

            case R.id.resetButton:  //Resets all variables and stored preferences
                inputCurrencyType.setText("Choose a currency!");
                convertedCurrencyType.setText("Choose a currency!");
                preferences.edit().putString("Option1", null).apply();
                preferences.edit().putString("Option2", null).apply();
                inputImage.setImageResource(R.drawable.dollar_sign);
                convertedImage.setImageResource(R.drawable.dollar_sign);
                inputCurrency.setText("");
                convertedCurrency.setText("The result appear here!");
                equalsMessage.setText("");
                userInput = 0;
                userInputUsd = 0;
                convertedResult = 0;
                break;
        }
    }

    /*Part one of converting user input into selected currency type
    Method convertUSD converts the user's input of selected currency type, stored in string variable currencyType,
    into a base unit of United States Dollar (USD) and stores it in the global variable userInputUsd*/
    public void convertUSD(String currencyType, double userInput){
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

    /*Part two of converting user input into selected currency type.
    Method convertActual takes in the value of userInputUsd and calculates the result of the conversion based on what user has selected, stored in string variable currencyType*/
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

    /*Method to round double number to two decimal places.
    Taken from https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places*/
    private static Double roundConvertedValue(Double convertedResult, int decimalPlace){
        if (decimalPlace < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(convertedResult);
        bd = bd.setScale(decimalPlace, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
