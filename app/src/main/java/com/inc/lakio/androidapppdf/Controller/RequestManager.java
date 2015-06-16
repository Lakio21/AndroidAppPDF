package com.inc.lakio.androidapppdf.Controller;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Dictionary;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;


/**
 * Created by Lakio on 15/06/2015.
 */
public class RequestManager
{

    final private String _url = "";

    public String get(String urlString)
    {
        String reponse = "";

        URL _url = null;

        try {

            _url = new URL(urlString);

            HttpURLConnection cnx = (HttpURLConnection) _url.openConnection();

            cnx.setConnectTimeout(10000);

            InputStream reader = new BufferedInputStream(cnx.getInputStream());

            reponse = IOUtils.toString(reader, "UTF-8");

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return reponse;
    }

    public void post(String urlString, String jsonParams)
    {
        try
        {
            String postParameters = jsonParams;

            URL urlToRequest = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) urlToRequest.openConnection();

            urlConnection.setDoOutput( true );
            urlConnection.setInstanceFollowRedirects( false );

            urlConnection.setRequestMethod( "POST" );
            urlConnection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("charset", "utf-8");
            urlConnection.setUseCaches( false );

            try( OutputStreamWriter wr = new OutputStreamWriter( urlConnection.getOutputStream()))
            {
                wr.write(postParameters);
            }

        }
        catch (Exception e)
        {

        }

    }
}
