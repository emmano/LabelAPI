package com.foodessentials.labelapi.session;

/**
 * Created by ortiguelae on 1/14/14.<br>
 *
 * Callback used to get a hold of a {@link Session}
 *
 */
public interface LabelSessionCallback {

    public void response(Session session, String error);
}
