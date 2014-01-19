package com.foodessentials.labelapi.net;

import com.foodessentials.labelapi.api.LabelApiCallback;
import com.foodessentials.labelapi.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ortiguelae on 1/14/14.
 */
public class LabelApiConnector extends AsyncTask<LabelApiCallback,Void,LabelApiCallback> {

   Uri mUri;
    JSONObject jObject;
    public LabelApiConnector(Uri uri){
        mUri = uri;
    }

    @Override
    protected LabelApiCallback doInBackground(LabelApiCallback... labelApiCallbacks) {

        String result;

        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(mUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = Utils.convertStreamToString(in);
            jObject = new JSONObject(result);
        } catch (MalformedURLException e) {
            labelApiCallbacks[0].onResult(null, e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            labelApiCallbacks[0].onResult(null, e.toString());
            e.printStackTrace();
        } catch (JSONException e) {
            labelApiCallbacks[0].onResult(null, e.toString());
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }


        return labelApiCallbacks[0];
    }

    @Override
    protected void onPostExecute(LabelApiCallback labelApiCallback) {
        super.onPostExecute(labelApiCallback);
        if (jObject!=null)
        labelApiCallback.onResult(jObject, null);


    }
}
