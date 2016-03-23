package com.android.gguzman.ggcoupons;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Gabriel on 22/03/2016.
 */
public class CouponsRequest {

    public static JSONArray requestWebService(String serviceUrl) {
        disableConnectionReuseIfNecessary();

        HttpURLConnection urlConnection = null;
        try {
            // create connection
            URL urlToRequest = new URL(serviceUrl);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
           // urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
           // urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);

            // handle issues
            int statusCode = urlConnection.getResponseCode();

            // create JSON object from content
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
           // Log.e("resp interna ", getResponseText(in).replaceAll("\\r\\n|\\r|\\n", ""));
            String resp = new String(getResponseText(in).replaceAll("\\r\\n|\\r|\\n", ""));
            return new JSONArray(resp.substring(resp.indexOf("["), resp.lastIndexOf("]") + 1));

        } catch (MalformedURLException e) {
            // URL is invalid
        } catch (SocketTimeoutException e) {
            // data retrieval or connection timed out
        } catch (IOException e) {
            // could not read response body
            // (could not create input stream)
        } catch (JSONException e) {
            // response body is no valid JSON string
            Log.e("parseando el json",e.getMessage());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }

    /**
     * required in order to prevent issues in earlier Android version.
     */
    private static void disableConnectionReuseIfNecessary() {
        // see HttpURLConnection API doc
        if (Integer.parseInt( Build.VERSION.SDK)
                < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private static String getResponseText(InputStream inStream) {
        // very nice trick from
        // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return new Scanner(inStream).useDelimiter("\\A").next();
    }



//    //public String requestContent(String url)
//    public String requestContent(String tal) {
//
//        URL url;
//        String result = null;
//
//        try {
//            url = new URL(tal);
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            try {
//                Log.e("codigo",urlConnection.getResponseCode()+"code");
//                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//                Log.e("null?",(in!=null) ? "si":"no");
//                result = convertStreamToString(in);
//                Log.e("resultMetodo",result);
//            } catch (Exception e){
//                e.printStackTrace();
//                Log.e("excepInterna", "primera");
//            } finally {
//                urlConnection.disconnect();
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            Log.e("excepInterna", "MalformerURL");
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e("excepInterna", "IOExcept");
//        }
//
//        return result;
//    }
//
//    public String convertStreamToString(InputStream is) {
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//        StringBuilder sb = new StringBuilder();
//        String line = null;
//
//        try {
//            while ((line = reader.readLine()) != null) {
//                Log.e("line",line);
//                sb.append(line + "\n");
//            }
//        } catch (IOException e) {
//            Log.e("subinterna", "IOExceptionConvert");
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//            }
//        }
//
//        return sb.toString();
//    }


}
