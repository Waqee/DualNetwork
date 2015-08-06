package com.example.toxin.dualnetwork2;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URL;
import java.net.URLConnection;
import java.net.*;
import java.io.*;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        

        Button button = (Button)findViewById(R.id.button);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        final TextView text = (TextView)findViewById(R.id.textView);
        final TextView text2 = (TextView)findViewById(R.id.textView2);

        button3.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           text.setText("");
                                           text2.setText("");
                                       }
                                   });

            button.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){

                ConnectivityManager cm = (ConnectivityManager) getSystemService(
                        Context.CONNECTIVITY_SERVICE);
                NetworkRequest.Builder req = new NetworkRequest.Builder();
                req.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR);
                cm.requestNetwork(req.build(), new ConnectivityManager.NetworkCallback() {

                    @Override
                    public void onAvailable(Network network) {
                        URL url = null;
                        try {
                            url = new URL("http://www.oracle.com/");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        try {
                            URLConnection conn = network.openConnection(url);
                            BufferedReader in = new BufferedReader(new InputStreamReader(
                                    conn.getInputStream()));
                            final String inputLine;
                            inputLine = in.readLine();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    text.setText(inputLine);

                                }
                            });
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    // Be sure to override other options in NetworkCallback() too...

                });
            }
            }

            );
            button2.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){

                ConnectivityManager cm = (ConnectivityManager) getSystemService(
                        Context.CONNECTIVITY_SERVICE);
                NetworkRequest.Builder req = new NetworkRequest.Builder();
                req.addTransportType(NetworkCapabilities.TRANSPORT_WIFI);
                cm.requestNetwork(req.build(), new ConnectivityManager.NetworkCallback() {

                    @Override
                    public void onAvailable(Network network) {
                        URL url = null;
                        try {
                            url = new URL("http://www.oracle.com/");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        try {
                            URLConnection conn = network.openConnection(url);
                            BufferedReader in = new BufferedReader(new InputStreamReader(
                                    conn.getInputStream()));
                            final String inputLine;
                            inputLine = in.readLine();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    text2.setText(inputLine);

                                }
                            });
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    // Be sure to override other options in NetworkCallback() too...

                });
            }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
