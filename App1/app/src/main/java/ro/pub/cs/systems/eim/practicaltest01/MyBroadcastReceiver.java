package ro.pub.cs.systems.eim.practicaltest01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("PracticalTestBR", "Debug");
        if (intent.getAction() != null) {
            String action = intent.getAction();
            // Procesăm mesajul de difuzare în funcție de acțiunea primită
            switch (action) {
                case "action1":
                    break;
                case "action2":
                    break;
                case "action3":
                    break;
                default:
                    // Acțiune necunoscută
            }

            // Jurnalizează mesajul de difuzare în consolă
            Log.d("PracticalTestBR", "Received broadcast message with action: " + action);
            // Dacă mesajul conține date suplimentare, le puteți accesa folosind metode precum getIntent().getStringExtra(), getIntExtra() etc.

            if (intent.getExtras() != null) {
                Log.d("PracticalTestBR", intent.getStringExtra("message"));
            }
        }
    }
}
