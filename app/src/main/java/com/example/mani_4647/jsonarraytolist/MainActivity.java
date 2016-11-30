package com.example.mani_4647.jsonarraytolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoadJSONTask.Listener, AdapterView.OnItemClickListener {

    private ListView list_view;

    public static final String URL = "http://www.json-generator.com/api/json/get/bYFgNayPyq?indent=2";

    private List<HashMap<String, String>> people_hash_map_list = new ArrayList<>();

    private static final String KEY_USER_ID = "userId";
    private static final String KEY_ID = "id";
    private static final String KEY_TEXT = "text";
    private static final String KEY_BODY = "body";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_view = (ListView) findViewById(R.id.list_view);
        list_view.setOnItemClickListener(this);
        new LoadJSONTask(this).execute(URL);
    }

    @Override
    public void onLoaded(List<People> people_list) {

        for (People pep : people_list) {

            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_BODY,pep.getBody());
            map.put(KEY_USER_ID, pep.getUserId());
            map.put(KEY_ID, pep.getId());
            map.put(KEY_TEXT, pep.getTitle());

            people_hash_map_list.add(map);
        }

        loadListView();
    }

    @Override
    public void onError() {

        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, people_hash_map_list.get(i).get(KEY_ID),Toast.LENGTH_LONG).show();
    }

    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(MainActivity.this, people_hash_map_list, R.layout.list_item,
                new String[] { KEY_BODY,KEY_USER_ID, KEY_ID, KEY_TEXT},
                new int[] {R.id.text_view_body,R.id.text_view_user_id,R.id.text_view_id, R.id.text_view_text });

        list_view.setAdapter(adapter);

    }
}
