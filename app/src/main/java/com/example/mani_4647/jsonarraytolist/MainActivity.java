package com.example.mani_4647.jsonarraytolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    String internetData;
    StringBuffer stringBuffer;

    OkHttpClient client = new OkHttpClient();
    List<HashMap<String, String>> peopleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://jsonplaceholder.typicode.com/posts")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                stringBuffer = new StringBuffer(response.body().string());

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Toast.makeText(MainActivity.this, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
                        internetData = stringBuffer.toString();
                        String stringFormOfJsonObject = null;
                        try {
                            JSONArray jsonArray = new JSONArray(internetData);
                            JSONObject jsonObject;

                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                stringFormOfJsonObject = jsonObject.toString();

                                String userId = jsonObject.getString("userId");
                                String id = jsonObject.getString("id");
                                String title = jsonObject.getString("title");
                                String body = jsonObject.getString("body");

                                HashMap<String, String> person = new HashMap<>();
                                person.put("userId", userId);
                                person.put("id", id);
                                person.put("title", title);
                                person.put("body", body);

                                peopleList.add(person);

                            }
                            ListAdapter listAdapter;
                            listAdapter = new SimpleAdapter(MainActivity.this, peopleList, R.layout.single_item_layout,
                                    new String[]{"userId", "id", "title", "body"},
                                    new int[]{R.id.etUserId, R.id.etId, R.id.etTitle, R.id.etBody});

                            ListView listView = (ListView) findViewById(R.id.lvJSONData);
                            listView.setAdapter(listAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Succesfully Completed JSON ARRAY TO LIST
                        // learning about git

                        // 17:16:29

                    }
                });
            }
        });
    }


}
