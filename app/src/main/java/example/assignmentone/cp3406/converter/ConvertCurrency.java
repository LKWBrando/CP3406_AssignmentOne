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
        setPrefTheme(); //Setting preferred theme
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_currency);
        setTitle("Currency Converter"); //Setting display title
        inputCurrency = findViewById(R.id.inputCurrency);
        convertedCurrency = findViewById(R.id.convertedCurrency);
        inputCurrencyType = findViewById(R.id.inputCurrencyType);
        convertedCurrencyType = findViewById(R.id.convertedCurrencyType);
        equalsMessage = findViewById(R.id.equalsMessage);
        inputImage = findViewById(R.id.inputImage);
        convertedImage = findViewById(R.id.convertedImage);
    }

    @Override
    protected void onStart(){ //onStart, run main method currencyConverterMain
        super.onStart();
        currencyConverterMain();
    }

    protected void onResume(){ //onResume, run main method currencyConverterMain
        super.onResume();
        currencyConverterMain();
    }

    /*Main method run onStart and onResume
    Sets selected preferences, string values, and images, as well as converting user input according to selected currency types*/
    public void currencyConverterMain(){
        String prefCurrencyType;    //String value for the preferred currency type
        String imageFileName;   //String value for the name of the image file
        int resId; //used in setting the image resource
        prefCurrencyType = preferences.getString("settingsPref", "sgd"); //getting the preferred currency type set by the user, if not uses default value sgd
        inputCurrencyType.setText(preferences.getString("settingsPref", "sgd")); //setting the display text based on the preferred currency type set by the user, if not uses default value sgd
        resId = getResources().getIdentifier(prefCurrencyType, "drawable", getPackageName());
        inputImage.setImageResource(resId); //setting the image based on the preferred currency type set by the user, if not uses default value sgd

        String checkUserInput = preferences.getString("Option1", prefCurrencyType); //Checking for user selection for input currency type
        if(!inputCurrencyType.getText().toString().equals(checkUserInput)){ //If user has selected a currency type that is not the default or value set in the preferences, run
            inputCurrencyType.setText(preferences.getString("Option1", prefCurrencyType)); //setting the display text based on the selected currency type
            imageFileName = inputCurrencyType.getText().toString();
            resId = getResources().getIdentifier(imageFileName, "drawable", getPackageName());
            inputImage.setImageResource(resId); //setting the image based on the currency type set by the user,
        }

        //Checks SharedPreferences for user input on the currency type, then displays the corresponding value
        convertedCurrencyType.setText(preferences.getString("Option2", "Choose a currency!"));
        imageFileName = convertedCurrencyType.getText().toString();
        if(imageFileName.equals("Choose a currency!")){    //If it is a default value, display the corresponding default image
            convertedImage.setImageResource(R.drawable.dollar_sign);
        }else{  //Else, display the corresponding image for the selected currency type
            resId = getResources().getIdentifier(imageFileName, "drawable", getPackageName());
            convertedImage.setImageResource(resId);} //sets image of currency type according to current selected user input

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
                        int roundOffInt = preferences.getInt("roundingOff", 2); //Obtaining the int value to round off based on user preference (default 2 decimal places)
                        convertedCurrency.setText(Double.toString(roundConvertedValue(convertedResult, roundOffInt))); //Rounding off converted result, then parsing to String
                        equalsMessage.setText("Equates to..."); //Setting text to show that conversion has occurred
                    }
                }
                catch(Exception e){} //Throws exception
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
   }

    //Method called for android:onClick in the activity_convert_currency.xml file
    public void onButtonPress(View view){
        Intent goToOptions = new Intent(this, SettingsActivity.class); //Intent for switching to the settings screen
        Intent returnToMenu = new Intent(this, MainActivity.class); //Intent for returning to the main menu
        Intent goToCurrencyMenu = new Intent(this, CurrencyOptionsActivity.class);  //Intent for switching to the currency options menu
        switch(view.getId()){
            case R.id.homeButton:   //Home button returns to the initial screen
                startActivity(returnToMenu);
                break;

            case R.id.optionsButton: //Switches to the settings screen
                startActivity(goToOptions);
                break;

            case R.id.initialCurrencyButton:    //Starts activity that allows users to choose currency type
                preferences.edit().putBoolean("currencyBoolean", true).apply(); //Boolean = True if button clicked is initial currency
                startActivity(goToCurrencyMenu);
                break;

            case R.id.convertedCurrencyButton:    //Starts activity that allows users to choose currency type
                preferences.edit().putBoolean("currencyBoolean", false).apply(); //Boolean = False if button clicked is converted currency
                startActivity(goToCurrencyMenu);
                break;

            case R.id.resetButton:  //Resets all variables and stored values, based on default values and preferences
                inputCurrencyType.setText(preferences.getString("settingsPref", "sgd")); //Setting input currency text based on preferences
                String prefImageFileName = preferences.getString("settingsPref", "sgd");
                int resId = getResources().getIdentifier(prefImageFileName, "drawable", getPackageName());
                inputImage.setImageResource(resId); //Setting initial currency image based on preferences
                convertedImage.setImageResource(R.drawable.dollar_sign); //Setting converting currency image to default
                convertedCurrencyType.setText("Choose a currency!"); //Setting converting currency text to default
                preferences.edit().putString("Option1", null).apply(); //Resets user selection for converting currency
                preferences.edit().putString("Option2", null).apply(); //Resets user selection for converting currency
                inputCurrency.setText(""); //Resetting values for user input
                convertedCurrency.setText("The result appear here!"); //Resetting values for converted result
                equalsMessage.setText(""); //Wiping equals message
                userInput = 0; //Resetting variable value
                userInputUsd = 0; //Resetting variable value
                convertedResult = 0; //Resetting variable value
                break;
        }
    }

    //Method to set preference application theme according to previous user selection
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
