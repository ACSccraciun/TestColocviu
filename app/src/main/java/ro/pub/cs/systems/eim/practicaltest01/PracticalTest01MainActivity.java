package ro.pub.cs.systems.eim.practicaltest01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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


    }

    private void init() {
        counter1 = findViewById(R.id.count1);
        counter2 = findViewById(R.id.count2);

        press = findViewById(R.id.press);
        press_too = findViewById(R.id.press_too);

        second_activity = findViewById(R.id.second_activity);
    }
}