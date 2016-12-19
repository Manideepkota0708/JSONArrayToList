package com.example.mani_4647.jsonarraytolist;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivityByRecyclerView extends AppCompatActivity {


    private static final String TAG = "MainActivity";


    ArrayList<ListModel> listModel;
    CustomAdapter custadap;
    String jsonstr;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter madap;

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycler_view);
        listModel = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }



        
    }

    public void run() throws Exception {

        Request request = new Request.Builder().url("https://jsonplaceholder.typicode.com/posts").build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                jsonstr = response.body().string();


                MainActivityByRecyclerView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            JSONArray json = new JSONArray(jsonstr);
                            JSONObject e;
                            for (int i = 0; i < json.length(); i++) {

                                e = json.getJSONObject(i);
                                String userId = e.getString("userId");
                                String id = e.getString("id");
                                String title = e.getString("title");
                                String body = e.getString("body");

                                listModel.add(new ListModel(userId, id, title, body));
                            }


                            madap = new CustomAdapter(listModel);
                            mRecyclerView.setAdapter(madap);


                        } catch (JSONException j) {
                            j.printStackTrace();
                        }


                    }
                });

            }

        });
    }

}


