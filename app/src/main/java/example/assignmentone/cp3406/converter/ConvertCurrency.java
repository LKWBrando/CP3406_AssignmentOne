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
    int initialCurrencyFactor;
    int changedCurrencyFactor;
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
    protected void onStart() {
        super.onStart();

        //initialCurrencyFactor = preferences.getInt()
        //changedCurrencyFactor = preferences.getInt()

        initialCurrency.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    int value = Integer.parseInt(charSequence.toString());
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

    public void onButtonPress(View view){
        Intent returnToMenu = new Intent(this, MainActivity.class); //Intent for returning to the main menu
        Intent goToCurrencyMenu = new Intent(this, CurrencyOptionsActivity.class); //Intent for switching to the currency options menu
        switch(view.getId()){
            case R.id.homeButton:
                startActivity(returnToMenu);
                break;

            case R.id.initialCurrencyButton:
                startActivity(goToCurrencyMenu);
                break;

            case R.id.changedCurrencyButton:
                startActivity(goToCurrencyMenu);
                break;

        }
    }
}
