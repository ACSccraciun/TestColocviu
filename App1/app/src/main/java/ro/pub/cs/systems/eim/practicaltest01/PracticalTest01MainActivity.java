package ro.pub.cs.systems.eim.practicaltest01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01MainActivity extends AppCompatActivity {

    private TextView counter1;
    private TextView counter2;

    private Button press, press_too, second_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        init();
        buttonListeners();

        if (savedInstanceState != null) {
            String savedText = savedInstanceState.getString("counter1");
            if (savedText != null) {
                counter1.setText(savedText);
            }

            savedText = savedInstanceState.getString("counter2");

            if (savedText != null) {
                counter2.setText(savedText);
            }
        }
    }

    private void init() {
        counter1 = findViewById(R.id.count1);
        counter2 = findViewById(R.id.count2);

        press = findViewById(R.id.press);
        press_too = findViewById(R.id.press_too);

        second_activity = findViewById(R.id.second_activity);
    }

    private void buttonListeners() {
        press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer new_value = Integer.parseInt(counter1.getText().toString()) + 1;
                counter1.setText(new_value.toString());
            }
        });

        press_too.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer new_value = Integer.parseInt(counter2.getText().toString()) + 1;
                counter2.setText(new_value.toString());
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       // outState.putString(KEY_TEXT_VALUE, editText.getText().toString());
        outState.putString("counter1", counter1.getText().toString());
        outState.putString("counter2", counter2.getText().toString());
    }
}