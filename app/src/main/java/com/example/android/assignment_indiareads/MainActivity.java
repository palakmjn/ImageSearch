package com.example.android.assignment_indiareads;

import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.appindexing.Thing;
//import com.google.android.gms.common.api.GoogleApiClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private EditText searchView;
    private GridView imgResult;
    private ArrayList<Image> result;
    private GridAdapter adapter;
    private String url = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=";
    private int start = 0;
    private final String p_start = "&start=";
    private String query="";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
   // private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = (EditText) findViewById(R.id.search_id);
        imgResult = (GridView) findViewById(R.id.grid_id);
        adapter = new GridAdapter(this, result);
        imgResult.setAdapter(adapter);
//infinite scrolling
        imgResult.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                start += 8;
                loadImages(query,start);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       // client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    public void onImageSearch(View v){
        query = searchView.getText().toString();
        start = 0;
        loadImages(query, start);
    }

    public void loadImages(String query,int page) {
        AsyncHttpClient client = new AsyncHttpClient();
        StringBuffer query2 = new StringBuffer();
        query2.append(url);
        query2.append(query);
        query2.append(p_start + page);
        client.get(query2.toString(), null, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
                JSONArray json = null;
                try {
                    json = response.getJSONObject("responseData").getJSONArray("results");
                    adapter.addAll(Image.parseJSON(json));
                } catch (JSONException e) {
                    Log.d("error", e.toString());
                }
            }


            public void onFailure(int statusCode, PreferenceActivity.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println("failed");
            }
        });
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
//    public Action getIndexApiAction() {
//        Thing object = new Thing.Builder()
//                .setName("Main Page") // TODO: Define a title for the content shown.
//                // TODO: Make sure this auto-generated URL is correct.
//                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
//                .build();
//        return new Action.Builder(Action.TYPE_VIEW)
//                .setObject(object)
//                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
//                .build();
//    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client2.connect();
        //AppIndex.AppIndexApi.start(client2, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       // AppIndex.AppIndexApi.end(client2, getIndexApiAction());
       // client2.disconnect();
    }
}
