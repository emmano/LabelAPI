package com.foodessentials.labelapi.api;

import org.json.JSONObject;

/**
 * Created by ortiguelae on 1/14/14.
 */
public interface LabelApiCallback {

    public void onResult(JSONObject object, String error);

}
