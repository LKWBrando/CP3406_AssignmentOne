package example.assignmentone.cp3406.converter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {
    RadioGroup sigFigRadioGroup;
    RadioGroup themeRadioGroup;
    RadioButton wholeNumber;
    RadioButton twoDecimals;
    RadioButton defaultTheme;
    RadioButton nightTheme;
    RadioButton funTheme;
    ImageView defaultImage;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        setPrefTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");
        sigFigRadioGroup = findViewById(R.id.sigFigRadioGroup);
        wholeNumber = findViewById(R.id.wholeNumber);
        twoDecimals = findViewById(R.id.twoDecimals);
        themeRadioGroup = findViewById(R.id.themeRadioGroup);
        defaultTheme = findViewById(R.id.defaultTheme);
        nightTheme = findViewById(R.id.nightTheme);
        funTheme = findViewById(R.id.funTheme);
        defaultImage = findViewById(R.id.defaultImage);

        if(preferences.getInt("roundingOff", 2) == 0){
            sigFigRadioGroup.check(wholeNumber.getId());
        }
        else{sigFigRadioGroup.check(twoDecimals.getId());}

        if(preferences.getString("themeName", "AppTheme").equals("AppTheme")){
            themeRadioGroup.check(defaultTheme.getId());
        }
        else if(preferences.getString("themeName", "AppTheme").equals("NightMode")){
            themeRadioGroup.check(nightTheme.getId());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        String imageFileName = preferences.getString("settingsPref", "sgd");
        int resId = getResources().getIdentifier(imageFileName, "drawable", getPackageName());
        defaultImage.setImageResource(resId);
    }

    public void onButtonPress(View view){
        Intent goToCurrencyMenu = new Intent(this, CurrencyOptionsActivity.class);  //Intent for switching to the currency options menu
        Intent returnToCurrencyConverter = new Intent(this, ConvertCurrency.class);  //Intent for switching to the currency options menu

        switch(view.getId()){
            case R.id.backButton:
                startActivity(returnToCurrencyConverter);
                finish();
                break;

            case R.id.currencyOptionsButton:
                preferences.edit().putBoolean("settingsBoolean", true).apply();
                startActivity(goToCurrencyMenu);
                break;
        }
    }

    public void onRadioButtonPress(View view){
        Intent refreshSettings = new Intent(this, SettingsActivity.class);
        refreshSettings.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        switch(view.getId()){
            case R.id.wholeNumber:
                preferences.edit().putInt("roundingOff", 0).apply();
                break;
            case R.id.twoDecimals:
                preferences.edit().putInt("roundingOff", 2).apply();
                break;
            case R.id.defaultTheme:
                preferences.edit().putString("themeName", "AppTheme").apply();
                finish();
                startActivity(refreshSettings);
                break;
            case R.id.nightTheme:
                preferences.edit().putString("themeName", "NightMode").apply();
                startActivity(refreshSettings);
                break;
            case R.id.funTheme:
                preferences.edit().putString("themeName", "FunMode").apply();
                startActivity(refreshSettings);
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
