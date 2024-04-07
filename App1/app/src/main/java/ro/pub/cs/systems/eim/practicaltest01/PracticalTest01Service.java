package ro.pub.cs.systems.eim.practicaltest01;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Date;

public class PracticalTest01Service extends Service {
    private static final long DELAY_MILLIS = 5000; // 5 secunde
    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(Looper.getMainLooper());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            final int number1 = intent.getIntExtra("number1", 0);
            final int number2 = intent.getIntExtra("number2", 0);



            // Propagare mesaj cu difuzare la fiecare 10 secunde
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Calculează media aritmetică și media geometrică
                    double arithmeticMean = (number1 + number2) / 2.0;
                    double geometricMean = Math.sqrt(number1 * number2);

                    Log.d("PracticalTestService", "Number 1: " + String.valueOf(number1));
                    Log.d("PracticalTestService", "Number 2: " + String.valueOf(number2));

                    // Trimite mesajul cu difuzare utilizând trei acțiuni diferite asociate aleator

                    String base = "";

                    Intent broadcastIntent1 = new Intent(base + "action1");
                    broadcastIntent1.putExtra("message", new Date().toString());
                    sendBroadcast(broadcastIntent1);

                    Intent broadcastIntent2 = new Intent(base + "action2");
                    broadcastIntent2.putExtra("message", String.valueOf(arithmeticMean));
                    sendBroadcast(broadcastIntent2);

                    Intent broadcastIntent3 = new Intent(base + "action3");
                    broadcastIntent3.putExtra("message", String.valueOf(geometricMean));
                    sendBroadcast(broadcastIntent3);

                    // Repetă propagarea mesajului cu difuzare
                    handler.postDelayed(this, DELAY_MILLIS);
                }
            }, DELAY_MILLIS);
        }

        // Serviciul va fi repornit dacă este distrus de către sistem
        return START_STICKY;
    }
}
