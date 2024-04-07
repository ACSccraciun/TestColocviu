package ro.pub.cs.systems.eim.practicaltest01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01MainActivity extends AppCompatActivity {

    private TextView counter1;
    private TextView counter2;

    private Button press, press_too, second_activity;

    private int REQUEST_CODE = 1473;

    private int THRESHOLD = 5;

    private boolean serviceStarted = false;

    private MyBroadcastReceiver receiver;


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

        receiver = new MyBroadcastReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action1");
        intentFilter.addAction("action2");
        intentFilter.addAction("action3");
        registerReceiver(receiver, intentFilter);
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

                Integer sum = Integer.parseInt(counter1.getText().toString()) + Integer.parseInt(counter2.getText().toString());
                if (sum > THRESHOLD && !serviceStarted) {
                    Intent serviceIntent = new Intent(PracticalTest01MainActivity.this, PracticalTest01Service.class);
                    serviceIntent.putExtra("number1", Integer.parseInt(counter1.getText().toString()));
                    serviceIntent.putExtra("number2", Integer.parseInt(counter2.getText().toString()));
                    startService(serviceIntent);
                    serviceStarted = true;
                }
            }
        });

        press_too.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer new_value = Integer.parseInt(counter2.getText().toString()) + 1;
                counter2.setText(new_value.toString());

                Integer sum = Integer.parseInt(counter1.getText().toString()) + Integer.parseInt(counter2.getText().toString());
                if (sum > THRESHOLD && !serviceStarted) {
                    Intent serviceIntent = new Intent(PracticalTest01MainActivity.this, PracticalTest01Service.class);
                    serviceIntent.putExtra("number1", Integer.parseInt(counter1.getText().toString()));
                    serviceIntent.putExtra("number2", Integer.parseInt(counter2.getText().toString()));
                    startService(serviceIntent);
                    serviceStarted = true;
                }
            }
        });

        second_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticalTest01MainActivity.this, PracticalTest01SecondaryActivity.class);

                Integer sum = Integer.parseInt(counter1.getText().toString()) + Integer.parseInt(counter2.getText().toString());
                intent.putExtra("sum", sum.toString());

                startActivityForResult(intent, REQUEST_CODE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "CANCEL", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent serviceIntent = new Intent(PracticalTest01MainActivity.this, PracticalTest01Service.class);
        stopService(serviceIntent);

        unregisterReceiver(receiver);
    }
}