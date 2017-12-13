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
import static example.assignmentone.cp3406.converter.ConversionMethods.convertActual;
import static example.assignmentone.cp3406.converter.ConversionMethods.convertUSD;
import static example.assignmentone.cp3406.converter.ConversionMethods.roundConvertedValue;

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
        preferences = getSharedPreferences("Settings" , MODE_PRIVATE);
        setPrefTheme();
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
        String prefCurrencyType;
        String imageFileName;
        int resId;
        prefCurrencyType = preferences.getString("settingsPref", "sgd");
        inputCurrencyType.setText(preferences.getString("settingsPref", "sgd"));
        resId = getResources().getIdentifier(prefCurrencyType, "drawable", getPackageName());
        inputImage.setImageResource(resId);

        String checkUserInput = preferences.getString("Option1", prefCurrencyType);
        if(!inputCurrencyType.getText().toString().equals(checkUserInput)){
            inputCurrencyType.setText(preferences.getString("Option1", prefCurrencyType));
            imageFileName = inputCurrencyType.getText().toString();
            resId = getResources().getIdentifier(imageFileName, "drawable", getPackageName());
            inputImage.setImageResource(resId);
        }

        //Checks SharedPreferences for user input on the currency type, then displays the corresponding value
        convertedCurrencyType.setText(preferences.getString("Option2", "Choose a currency!"));
        imageFileName = convertedCurrencyType.getText().toString();
        if(imageFileName.equals("Choose a currency!")){
            convertedImage.setImageResource(R.drawable.dollar_sign);
        }else{
            resId = getResources().getIdentifier(imageFileName, "drawable", getPackageName());
            convertedImage.setImageResource(resId);}

        //imageFileName = inputCurrencyType.getText().toString();
        //if(!imageFileName.equals(prefCurrencyType)){
        //resId = getResources().getIdentifier(imageFileName, "drawable", getPackageName());
        //inputImage.setImageResource(resId);}

        //sets image of currency type according to current selected user input
        inputCurrency.setText(preferences.getString("initialUserInput", null)); //Saves the user's input between switching activities.

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
                        userInputUsd = convertUSD(inputCurrencyType.getText().toString(), userInput); //Calling conversion methods
                        convertedResult = convertActual(convertedCurrencyType.getText().toString(), userInputUsd); //Calling conversion methods
                        int roundOffInt = preferences.getInt("roundingOff", 2);
                        convertedCurrency.setText(Double.toString(roundConvertedValue(convertedResult, roundOffInt))); //rounding off to two decimal places, then parsing to String
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
                inputCurrencyType.setText(preferences.getString("settingsPref", "sgd"));
                String prefImageFileName = preferences.getString("settingsPref", "sgd");
                int resId = getResources().getIdentifier(prefImageFileName, "drawable", getPackageName());
                inputImage.setImageResource(resId);

                convertedImage.setImageResource(R.drawable.dollar_sign);
                convertedCurrencyType.setText("Choose a currency!");

                preferences.edit().putString("Option1", null).apply();
                preferences.edit().putString("Option2", null).apply();

                inputCurrency.setText("");
                convertedCurrency.setText("The result appear here!");
                equalsMessage.setText("");
                userInput = 0;
                userInputUsd = 0;
                convertedResult = 0;
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
