package com.zodiac.user1;


import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class GisService extends Service {

    public static final String CHANNEL = "GIS_SERVICE";
    public static final String INFO = "INFO";

    @Override
    public void onCreate() {
        // сообщение о создании служб;
     //   Toast.makeText(this, "Service created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // сообщение о запуске службы
      //  Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();

        // создаем объект нашего AsyncTask (необходимо для работы с сетью)
        GisAsyncTask t = new GisAsyncTask();
        t.execute(); // запускаем AsyncTask

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        //сообщение об остановке службы
        //Toast.makeText(this, "Service stoped", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class GisAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPostExecute(String aVoid) {
            Intent i = new Intent(CHANNEL);
            i.putExtra(INFO, aVoid);
            sendBroadcast(i); // рассылае
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            try {
                int count;
                URL url = new URL("http://kanashen.ru/category/%D0%BD%D0%BE%D0%B2%D0%BE%D1%81%D1%82%D0%B8/feed/");
                Scanner scanner = new Scanner((InputStream) url.getContent());
                while (scanner.hasNextLine()){
                    result = result + scanner.nextLine() + "\n";
                }
            } catch (Exception e) {
                result = e.toString();
            }
            return result;
        }
    }
}
