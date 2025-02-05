package com.example.zakatcalc;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private EditText weightInput, goldValueInput;
    private RadioGroup typeSelector;
    private RadioButton selectedType;
    private TextView totalGoldValue, zakatPayableValue, totalZakat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        weightInput = findViewById(R.id.weightInput);
        goldValueInput = findViewById(R.id.goldValueInput);
        typeSelector = findViewById(R.id.typeSelector);
        totalGoldValue = findViewById(R.id.totalGoldValue);
        zakatPayableValue = findViewById(R.id.zakatPayableValue);
        totalZakat = findViewById(R.id.totalZakat);

        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateZakat();
            }
        });
    }

    private void calculateZakat() {
        double weight = Double.parseDouble(weightInput.getText().toString());
        double goldValue = Double.parseDouble(goldValueInput.getText().toString());

        int selectedId = typeSelector.getCheckedRadioButtonId();
        selectedType = findViewById(selectedId);

        double uruf = selectedType.getText().toString().equals("Keep") ? 85 : 200;
        double totalValue = weight * goldValue;
        double zakatValue = (weight > uruf) ? (weight - uruf) * goldValue : 0;
        double totalZakatAmount = zakatValue * 0.025;

        totalGoldValue.setText(String.format("RM %.2f", totalValue));
        zakatPayableValue.setText(String.format("RM %.2f", zakatValue));
        totalZakat.setText(String.format("RM %.2f", totalZakatAmount));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_About) {
            Intent aboutIntent = new Intent(this, About.class);
            startActivity(aboutIntent);
            return true;
        } else if (id == R.id.item_Instruction) {
            Intent instructionIntent = new Intent(this, Instruction.class);
            startActivity(instructionIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
