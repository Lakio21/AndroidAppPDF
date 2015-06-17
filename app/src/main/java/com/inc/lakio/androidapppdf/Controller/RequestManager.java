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
import java.net.URI;
import java.net.URL;
import java.util.Dictionary;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;


/**
 * Created by Lakio on 15/06/2015.
 */
public final class RequestManager {

    private String urlString = "http://10.131.128.123:8080/webservice/webapi/";

    private static volatile RequestManager instance = null;

    private HttpClient httpClient;
    private HttpGet httpGet;
    private HttpPost httpPost;

    private RequestManager()
    {
        httpClient = new DefaultHttpClient();
        httpGet = new HttpGet();
        httpPost = new HttpPost();
    }

    private class AsyncGet extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            if(params.length > 0) {
                httpGet.setURI(URI.create(params[0]));
                try {
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    return getStringFromResponse(httpResponse);
                } catch (IOException e) {
                    return e.getMessage();
                }
            }
            return null;
        }
    }

    private class AsyncPost extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            if(params.length > 1) {
                try {
                    httpPost.setURI(URI.create(params[0]));
                    httpPost.setEntity(new StringEntity(params[1]));
                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    return getStringFromResponse(httpResponse);
                }
                catch (Exception ex)
                {
                    return ex.getMessage();
                }
            }
            return null;
        }
    }

    public final static RequestManager getInstance(){
        if(RequestManager.instance == null){
            synchronized (RequestManager.class){
                if(RequestManager.instance == null){
                    RequestManager.instance = new RequestManager();
                }
            }
        }
        return RequestManager.instance;
    }

    public String get(String url) throws IOException, ExecutionException, InterruptedException {
        AsyncGet asyncGet = new AsyncGet();
        String result = asyncGet.execute(urlString+url).get();
        if(result == null)
        {
            return "error";
        }
        else
        {
            return result;
        }
    }

    public String post(String url, String json) throws IOException, ExecutionException, InterruptedException {
        AsyncPost asyncPost = new AsyncPost();
        String result = asyncPost.execute(urlString+url, json).get();
        if(result == null)
        {
            return "error";
        }
        else
        {
            return result;
        }
    }

    private static String getStringFromResponse(HttpResponse response) throws IOException {
        String result = "";
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        while ((line = bufferedReader.readLine()) != null)
        {
            result += line;
        }
        return result;
    }
}
