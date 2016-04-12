package com.gguzman.android.ggcoupons;


import android.os.Build;
import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;

/**
 * Class that manages all the connections and requests to the
 * 8coupons API.
 * @author Gabriel Guzmán
 * @version 1.0
 * @since v1.2016.03.22
 */
public class CouponsRequest {

    /**
    *Method that calls one of the 8Coupons web services, depending on the {@code serviceURL}
    * param. It returns a JSONArray with the answer. For now, it have been tested with
    * the Categories and Subcategories requests only.<br><br>
     *     It uses {@code HttpUrlConnection} instead of HttpClient because the latter is being
     *     phased out.
     *
    * @param serviceUrl The URL for the web service.
    * @return A JSONArray with the answer from the server.
    * @since v1.2016.03.22
    */
    //TODO: Add the exceptions (method and documentation)
    public static JSONArray requestWebService(String serviceUrl) {
        disableConnectionReuseIfNecessary();
        HttpURLConnection urlConnection = null;

        /*To avoid problems with HttpUrlConnection
          GG 2016.03.27
         */
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            // create connection
            URL urlToRequest = new URL(serviceUrl);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
           // urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
           // urlConnection.setReadTimeout(DATA_RETRIEVAL_TIMEOUT);
            // handle issues
            //int statusCode = urlConnection.getResponseCode();
            // create JSON array from content
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
           // Log.e("resp ", getResponseText(in).replaceAll("\\r\\n|\\r|\\n", ""));
            /*The replaceAll is used to delete all the line breaks, this can be omitted
            since we're obtaining the string contained between [ and ] in the return statement.
            GG 2016.03.27
             */
            String resp = getResponseText(in).replaceAll("\\r\\n|\\r|\\n", "");
            //TODO: Test other calls apart from Categories to validate this method
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
            //Log.e("parsing json",e.getMessage());
        } finally {
            //close the connection
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    /**
     * Required in order to prevent issues in earlier Android version.
     * @since v1.2016.03.22
     */
    private static void disableConnectionReuseIfNecessary() {
        // see HttpURLConnection API doc
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    /**
     * Method to pre-process the response from the web service. Maybe this is not necessary
     * @param inStream Input stream of data to process
     * @return Processed string with the response
     * @since v1.2016.03.22
     */
    private static String getResponseText(InputStream inStream) {
        // very nice trick from
        // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return new Scanner(inStream).useDelimiter("\\A").next();
    }
}
