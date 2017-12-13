package example.assignmentone.cp3406.converter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton currencyButton;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        setPrefTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currencyButton = findViewById(R.id.currencyButton);

    }

    public void onButtonPress(View view){   //Method onButtonPress for android:onClick in the activity_main.xml file
        switch(view.getId()){
            case R.id.currencyButton:   //Starts activity_convert_currency on button click
                Intent convertCurrency = new Intent(this, ConvertCurrency.class);
                startActivity(convertCurrency);
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
