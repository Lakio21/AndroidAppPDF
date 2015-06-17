package com.inc.lakio.androidapppdf.Controller;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Dictionary;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;


/**
 * Created by Lakio on 15/06/2015.
 */
public class RequestManager {

    final private String _url = "";

    public String get(String urlString) {
        String reponse = "";

        URL _url = null;

        try {

            _url = new URL(urlString);

            HttpURLConnection cnx = (HttpURLConnection) _url.openConnection();

            cnx.setConnectTimeout(10000);

            InputStream reader = cnx.getInputStream();

            reponse = IOUtils.toString(reader, "UTF-8");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reponse;
    }

    public String GetSomething(String urlString)
    {

        String url = urlString;
        BufferedReader inStream = null;
        String result = "";

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(new HttpGet(url));
            inStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer buffer = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = inStream.readLine()) != null) {
                buffer.append(line + NL);
            }
            inStream.close();

            result = buffer.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public String post(String urlString, String jsonParams) {
        try {
            String postParameters = jsonParams;

            URL urlToRequest = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) urlToRequest.openConnection();

            urlConnection.setDoOutput(true);
            urlConnection.setInstanceFollowRedirects(false);

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("charset", "utf-8");
            urlConnection.setUseCaches(false);

            try (OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream())) {
                wr.write(postParameters);
            }

        } catch (Exception e) {

        }
        return "";

    }
}
