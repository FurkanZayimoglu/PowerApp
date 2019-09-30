package com.furkanzayimoglu.powerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{

    private PowerAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<PowerModel> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        operationResponse();
    }


    private void operationResponse() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(PowerInterface.jsonUrl)
        .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        PowerInterface api = retrofit.create(PowerInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), "android");
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        requestBodyMap.put("client",body);


        Call<String> call = api.getResponse(requestBodyMap);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
               Log.i("cevapppppppp", response.body());

                if(response.isSuccessful()){
                    if (response.body() != null){
                        Log.i("basarılııııııı", response.body());
                        String jsonResponse = response.body().toString();
                        addDataSetRecylecler(jsonResponse);

                    }else{
                       Log.i("GELMEYİİİİİ", "BOSA DÖNEYİİ");
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void addDataSetRecylecler(String jsonResponse){

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray arrayJson = jsonObject.getJSONObject("response").getJSONArray("list");
          //  Log.i("la bu benim listem", arrayJson.toString());
             arrayList = new ArrayList<>();
            for (int i =0; i < arrayJson.length(); i++){

                PowerModel model = new PowerModel();
                JSONObject resultObject = arrayJson.getJSONObject(i);

                model.setName(resultObject.getString("name"));
                model.setChannel(resultObject.getString("channel_stream_url"));
                String prefix = resultObject.getJSONObject("logo").getJSONObject("standard").getJSONObject("image").getString("prefix");
                String suffix = resultObject.getJSONObject("logo").getJSONObject("standard").getJSONObject("image").getString("suffix");
                String imageUrl = prefix +"100x100"+ suffix;
                model.setLogo(imageUrl);

                arrayList.add(model);
            }
            adapter = new PowerAdapter(this, arrayList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            adapter.setClickListener(this);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra("channel", arrayList.get(position).getChannel());
        startActivity(intent);
    }


}
