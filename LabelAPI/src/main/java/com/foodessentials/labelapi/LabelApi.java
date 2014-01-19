package com.foodessentials.labelapi;

import com.foodessentials.labelapi.api.ILabelReference;
import com.foodessentials.labelapi.api.LabelApiCallback;
import com.foodessentials.labelapi.net.LabelApiConnector;
import com.foodessentials.labelapi.session.LabelSessionCallback;
import com.foodessentials.labelapi.session.LabelSessionConnector;
import com.foodessentials.labelapi.session.Session;
import com.foodessentials.labelapi.utils.Utils;

import android.net.Uri;

/**
 * Created by ortiguelae on 1/14/14.
 */
public class LabelApi {

    public static Session mSession;
    private LabelReference mLabelReference;

    public LabelApi() {

        mLabelReference = new LabelReference();

    }

    public LabelReference getLabelReference(){
        return mLabelReference;
    }

  public void createSession(String userID, LabelSessionCallback callback){

      Uri uri = new Uri.Builder().scheme("http").authority("api.foodessentials.com")
              .path("createsession").appendQueryParameter("uid", userID)
              .appendQueryParameter("devid", Utils.DEVICE_ID)
              .appendQueryParameter("appid", Utils.APP_ID)
              .appendQueryParameter("f", Utils.FORMAT)
              .appendQueryParameter("api_key", Utils.API_KEY)
              .build();

      new LabelSessionConnector(uri).execute(callback);
  }

    public  class LabelReference implements ILabelReference {


        @Override
        public void searchProducts(String query, int queryLimit, int offset,
                LabelApiCallback callback) {

            Uri uri = new Uri.Builder().scheme("http").authority("api.foodessentials.com")
                    .path("searchprods").appendQueryParameter("q", query)
                    .appendQueryParameter("sid", mSession.mSessionID).appendQueryParameter("n",
                            Integer.toString(queryLimit)).appendQueryParameter("s",
                            Integer.toString(offset))
                    .appendQueryParameter("f", Utils.FORMAT)
                    .appendQueryParameter("api_key", Utils.API_KEY)
                    .build();
            new LabelApiConnector(uri).execute(callback);


        }

        @Override
        public void productScore(String upc, LabelApiCallback callback) {
            Uri uri = new Uri.Builder().scheme("http").authority("api.foodessentials.com")
                    .path("productscore").appendQueryParameter("u", upc)
                    .appendQueryParameter("sid", mSession.mSessionID)
                    .appendQueryParameter("f", Utils.FORMAT)
                    .appendQueryParameter("api_key", Utils.API_KEY)
                    .build();
            new LabelApiConnector(uri).execute(callback);
        }

        @Override
        public void label(String upc, LabelApiCallback callback) {
            Uri uri = new Uri.Builder().scheme("http").authority("api.foodessentials.com")
                    .path("label").appendQueryParameter("u", upc)
                    .appendQueryParameter("sid", mSession.mSessionID)
                    .appendQueryParameter("appid",Utils.APP_ID)
                    .appendQueryParameter("f", Utils.FORMAT)
                    .appendQueryParameter("api_key", Utils.API_KEY)
                    .build();
            new LabelApiConnector(uri).execute(callback);
        }

        @Override
        public void labelArray(String upc, LabelApiCallback callback) {
            Uri uri = new Uri.Builder().scheme("http").authority("api.foodessentials.com")
                    .path("labelarray").appendQueryParameter("u", upc)
                    .appendQueryParameter("sid", mSession.mSessionID)
                    .appendQueryParameter("n",""+1)
                    .appendQueryParameter("s",""+0)
                    .appendQueryParameter("f", Utils.FORMAT)
                    .appendQueryParameter("api_key", Utils.API_KEY)
                    .build();
            new LabelApiConnector(uri).execute(callback);
        }

        @Override
        public void labelSummary(String upc, LabelApiCallback callback) {
            Uri uri = new Uri.Builder().scheme("http").authority("api.foodessentials.com")
                    .path("label_summary").appendQueryParameter("u", upc)
                    .appendQueryParameter("sid", mSession.mSessionID)
                    .appendQueryParameter("appid",Utils.APP_ID)
                    .appendQueryParameter("f", Utils.FORMAT)
                    .appendQueryParameter("api_key", Utils.API_KEY)
                    .build();
            new LabelApiConnector(uri).execute(callback);
        }

        @Override
        public void getAllergenAdditive(String upc, String property, PROPERTY_TYPE type,
                LabelApiCallback callback) {
            Uri uri = new Uri.Builder().scheme("http").authority("api.foodessentials.com")
                    .path("getallergenadditive").appendQueryParameter("u", upc)
                    .appendQueryParameter("sid", mSession.mSessionID)
                    .appendQueryParameter("appid",Utils.APP_ID)
                    .appendQueryParameter("f", Utils.FORMAT)
                    .appendQueryParameter("property", property)
                    .appendQueryParameter("proptype",type.type)
                    .appendQueryParameter("api_key", Utils.API_KEY)
                    .build();
            new LabelApiConnector(uri).execute(callback);
        }

        @Override
        public void getPropDescription(PROPERTY_TYPE type, String nameNutrient,
                LabelApiCallback callback) {
            Uri uri = new Uri.Builder().scheme("http").authority("api.foodessentials.com")
                    .path("getpropdescription")

                    .appendQueryParameter("type", type.type)
                    .appendQueryParameter("name", nameNutrient)
                    .appendQueryParameter("f", Utils.FORMAT)
                    .appendQueryParameter("api_key", Utils.API_KEY)
                    .build();
            new LabelApiConnector(uri).execute(callback);
        }
    }

}
