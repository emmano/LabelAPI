package com.foodessentials.apitest;

import com.foodessentials.labelapi.LabelApi;
import com.foodessentials.labelapi.api.LabelApiCallback;
import com.foodessentials.labelapi.session.LabelSessionCallback;
import com.foodessentials.labelapi.session.Session;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class LabelAPITestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_apitest);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.label_apitest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment
            implements LabelSessionCallback, LabelApiCallback {

        LabelApi mApi;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_label_apitest, container, false);
            mApi = new LabelApi();

            mApi.createSession("Emmanuel_0", this);

            return rootView;
        }

        @Override
        public void response(Session session, String error) {
            if(!session.mSessionID.isEmpty())
            mApi.mLabelReference.searchProducts("bacon", 1, 0, this);
        }

        /* TODO make custom JSONOBject wrappers to deliver to the callback*/
        @Override
        public void onResult(JSONObject object, String error) {
            if (error == null) {
                Toast.makeText(getActivity(), object.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

}
