package de.derandroidpro.fragebogentutorial;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btn;
    Button btnj; //JA-Button
    Button btnn; //NEIN-Button
    TextView tv;
    TextView tvq;
    int qcount = 0; //Fragen-Zähler
    int jcount = 0; //JA-Zähler
    int ncount = 0; // NEIN-Zähler
    int qvector = 0; //Fragelisten-Vektor
    public String antwort = "1. Test!";
    public List<String> questions = Arrays.asList(//
            "Frage 1: Magst Du Kräutertee?",
            "Frage 2: Magst Du Kamillentee?",
            "Frage 3: Magst Du Grünen Tee?",
            "Frage 4: Magst Du Schwarzen Tee?",
            "Frage 5: Magst Du Früchtetee?",
            "Frage 6: Magst Du Fencheltee??",
            "Frage 7: Magst Du Rooibusch-Tee? ",
            "Frage 8: Magst Du Earl-Grey Tee?",
            "Frage 9: Magst Du Pu Erh Tee?",
            "Frage 10: Magst Du Vanille-Tee? ",
            "Frage 11: Magst Du Ingwer-Tee?",
            "Frage 12: Magst Du Yogi-Tee?",
            "Frage 13: Magst Du Kokos-Mandel-Tee?",
            "Frage 14: Magst Du Holunderblüten-Tee?",
            "Frage 15: Magst Du Brennessel-Tee?",
            "Frage 16: Magst Du Apfelsaft?",
            "Frage 17: Magst Du Orangensaft? ",
            "Frage 18: Magst Du Traubensaft?",
            "Frage 19: Magst Du Kirschsaft?",
            "Frage 20: Magst Du Bananensaft?",
            "Frage 21: Magst Du Tomatensaft?",
            "Frage 22: Magst Du Pfirsichsaft?",
            "Frage 23: Magst Du Aprikosensaft?",
            "Frage 24: Magst Du Mangosaft?",
            "Frage 25: Magst Du Karottensaft?",
            "Frage 26: Magst Du Gemüsesaft?",
            "Frage 27: Magst Du Sauerkrautsaft? ",
            "Frage 28: Magst Du Ananassaft?",
            "Frage 29: Magst Du Papayasaft?",
            "Frage 30: Magst Du Birnensaft?",
            "Frage 31: Magst Du Vita Cola?",
            "Frage 32: Magst Du Coca Cola?",
            "Frage 33: Magst Du Pepsi Cola?",
            "Frage 34: Magst Du Sprite?",
            "Frage 35: Magst Du Fanta?",
            "Frage 36: Magst Du Fanta Orange?",
            "Frage 37: Magst Du Fanta Grapefruit? ",
            "Frage 38: Magst Du Red Bull?",
            "Frage 39: Magst Du Mezzo Mix?",
            "Frage 40: Magst Du Isostar? ",
            "Frage 41: Magst Du Bluna?",
            "Frage 42: Magst Du Bionade?",
            "Frage 43: Magst Du Bitter Lemon?",
            "Frage 44: Magst Du Ginger Ale?",
            "Frage 45: Magst Du Sinalco?");
    //    JProgressBar proba; //Statusanzeige





    final String scripturlstring = "http://10.42.0.41/zurmo/test9.php";//receive.php";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvq = (TextView) findViewById(R.id.textView2);
        et = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.button);
        btnj = (Button) findViewById(R.id.button2);
        btnn = (Button) findViewById(R.id.button3);
        tvq.setText(questions.get(qvector));
        //        proba = (ProgressBar findViewById(R.id.progressBar))


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (internetAvailable()) {
                    sendToServer(et.getText().toString(),null,null,null);
                } else {
                    Toast.makeText(getApplicationContext(), "Internet ist nicht verfügbar.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (internetAvailable()) {
                    qcount++;
                    jcount++;
                    sendToServer(et.getText().toString(),tvq.getText().toString(),btnj.getText().toString(),Integer.toString(qcount));
                    qvector++;
                    tvq.setText(questions.get(qvector));
                } else {
                    Toast.makeText(getApplicationContext(), "Internet ist nicht verfügbar.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (internetAvailable()) {
                    qcount++;
                    ncount++;
                    sendToServer(et.getText().toString(),tvq.getText().toString(),btnn.getText().toString(),Integer.toString(qcount));
//                    sendToServer(tvq.getText().toString());
//                    sendToServer(Integer.toString(ncount));
//                    sendToServer(btnn.getText().toString());
                    qvector++;
                    tvq.setText(questions.get(qvector));
                } else {
                    Toast.makeText(getApplicationContext(), "Internet ist nicht verfügbar.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void sendToServer(final String User, final String Frage, final String Antwort, final String Fragenr) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String textparam = "User=" + URLEncoder.encode(User, "UTF-8") +"&Frage=" + URLEncoder.encode(Frage, "UTF-8") +"&Antwort=" + URLEncoder.encode(Antwort, "UTF-8") + "&Fragenr" + URLEncoder.encode(Fragenr, "UTF-8");

                    URL scripturl = new URL(scripturlstring);
                    HttpURLConnection connection = (HttpURLConnection) scripturl.openConnection();
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setFixedLengthStreamingMode(textparam.getBytes().length);

                    OutputStreamWriter contentWriter = new OutputStreamWriter(connection.getOutputStream());
                    contentWriter.write(textparam);
                    contentWriter.flush();
                    contentWriter.close();

                    InputStream answerInputStream = connection.getInputStream();
                    final String answer = getTextFromInputStream(answerInputStream);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(answer);
                        }
                    });
                    answerInputStream.close();
                    connection.disconnect();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public String getTextFromInputStream(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();

        String aktuelleZeile;
        try {
            while ((aktuelleZeile = reader.readLine()) != null) {
                stringBuilder.append(aktuelleZeile);
                stringBuilder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString().trim();
    }

    public boolean internetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Fragebogen", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://de.derandroidpro.sendtexttoservertutorial/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Fragebogen", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://de.derandroidpro.Fragebogentutorial/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
