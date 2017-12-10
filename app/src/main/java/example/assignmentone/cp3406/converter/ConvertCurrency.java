package example.assignmentone.cp3406.converter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ConvertCurrency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_currency);
    }

    public void onButtonPress(View view){
        switch(view.getId()){
            case R.id.homeButton:
                Intent returnToMenu = new Intent(this, MainActivity.class);
                startActivity(returnToMenu);
                break;
        }
    }
}
