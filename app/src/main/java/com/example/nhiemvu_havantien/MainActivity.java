package com.example.nhiemvu_havantien;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String tk;
    String url;
    ArrayList<JSONObject> videoList;
    RecyclerView recyclerView;
    VideoAdapter adapter;
    Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoList = new ArrayList<>();
        button = findViewById(R.id.button);
        editText = findViewById(R.id.search);
        recyclerView = findViewById(R.id.listview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new VideoAdapter(getApplicationContext(), videoList);
        recyclerView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tk = editText.getText().toString();
                url = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&q=" + tk + "&type=video&key=AIzaSyAUedqdLm23G97TGwcpnXh3kSDk2Ts1PSI";
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray dataArray = object.getJSONArray("items");
                            videoList = new ArrayList<>();
                            for (int j = 0; j < dataArray.length(); j++) {
                                JSONObject itemObj1 = dataArray.getJSONObject(j);
                                videoList.add(itemObj1);
                            }
                            adapter = new VideoAdapter(getApplicationContext(), videoList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                }
                );
                requestQueue.add(stringRequest);
            }
        });
    }

}


