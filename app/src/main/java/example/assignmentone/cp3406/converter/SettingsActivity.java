package example.assignmentone.cp3406.converter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity {
    RadioButton wholeNumber;
    RadioButton twoDecimals;
    ImageView defaultImage;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");
        wholeNumber = findViewById(R.id.wholeNumber);
        twoDecimals = findViewById(R.id.twoDecimals);
        defaultImage = findViewById(R.id.defaultImage);
        preferences = getSharedPreferences("Settings", MODE_PRIVATE);
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
        Intent returnToMenu = new Intent(this, MainActivity.class); //Intent for returning to the main menu

        switch(view.getId()){
            case R.id.backButton:
                startActivity(returnToCurrencyConverter);
            case R.id.homeButton:
                startActivity(returnToMenu);
            case R.id.currencyOptionsButton:
                preferences.edit().putBoolean("settingsBoolean", true).apply();
                startActivity(goToCurrencyMenu);
        }
    }
}
