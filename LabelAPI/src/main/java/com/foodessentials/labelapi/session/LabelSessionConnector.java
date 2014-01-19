package com.foodessentials.labelapi.session;

import com.foodessentials.labelapi.LabelApi;
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
 * Created by ortiguelae on 1/14/14.<br>
 *
 * This class connects with the FoodEssentials LabelApi to create a {@link Session}. If the {@link
 * Session} is created, it will contain the username and sessionId.<br>
 */
public class LabelSessionConnector
        extends AsyncTask<LabelSessionCallback, Void, LabelSessionCallback> {

    private Uri mUri;

    public String mSessionID;

    private String mUserID;

    public LabelSessionConnector(Uri uri) {
        mUri = uri;

    }

    @Override
    protected LabelSessionCallback doInBackground(LabelSessionCallback... callbacks) {
        String result;

        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(mUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = Utils.convertStreamToString(in);
            JSONObject jObject = new JSONObject(result);

            if (jObject != null) {

                String sessionId = jObject.getString("session_id");
                String userId = jObject.getString("user_id");
                mSessionID = sessionId;
                mUserID = userId;


            }
        } catch (MalformedURLException e) {
            callbacks[0].response(null, e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            callbacks[0].response(null, e.toString());
            e.printStackTrace();
        } catch (JSONException e) {
            callbacks[0].response(null, e.toString());
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return callbacks[0];
    }

    @Override
    protected void onPostExecute(LabelSessionCallback labelSessionCallback) {
        super.onPostExecute(labelSessionCallback);
        Session session = new Session();
        session.mSessionID = mSessionID;
        session.mUserID = mUserID;
        LabelApi.mSession = session;
        labelSessionCallback.response(session, null);

    }


}