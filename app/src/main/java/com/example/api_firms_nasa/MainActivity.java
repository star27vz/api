package com.example.api_firms_nasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FIRMSAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new FIRMSAdapter();

        // Consumir la API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://firms.modaps.eosdis.nasa.gov")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FIRMSAPI api = retrofit.create(FIRMSAPI.class);

        Call<String> call = api.getFireData("21d76cf92c0676b49a244e553987c53f", "MODIS_NRT", "PER", 4);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                List<Fire> fires = Fire.fromCSV(response.body());

                adapter.addFires(fires);

                recyclerView.setAdapter(adapter);
                for (Fire fire : fires) {

                    double lat = fire.getLat();
                    double lon = fire.getLon();

                    TextView tvLat = findViewById(R.id.tvLat);
                    tvLat.setText(String.valueOf(lat));
                    TextView tvLon = findViewById(R.id.tvLon);
                    tvLon.setText(String.valueOf(lon));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
