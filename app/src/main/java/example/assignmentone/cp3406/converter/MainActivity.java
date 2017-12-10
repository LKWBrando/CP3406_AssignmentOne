package example.assignmentone.cp3406.converter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton currencyButton;
    ImageButton unitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currencyButton = findViewById(R.id.currencyButton);
        unitButton = findViewById(R.id.unitButton);
    }

    public void onButtonPress(View view){
        switch(view.getId()){
            case R.id.currencyButton:
                Intent convertCurrency = new Intent(this, CurrencyActivity.class);
                startActivity(convertCurrency);
                break;

            case R.id.unitButton:
                Intent convertUnits = new Intent(this, UnitsActivity.class);
                startActivity(convertUnits);
                break;
        }

    }


}
