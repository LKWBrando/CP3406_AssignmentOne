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
        initialCurrency.addTextChangedListener(new TextWatcher() {
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
        switch(view.getId()){
            case R.id.homeButton:
                Intent returnToMenu = new Intent(this, MainActivity.class);
                startActivity(returnToMenu);
                break;

            case R.id.initialCurrencyButton:
                break;

            case R.id.changedCurrencyButton:
                break;

        }
    }
}
