package com.example.api_firms_nasa;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FIRMSAPI {
    @GET("api/country/csv/{key}/{product_type}/{country}/{resolution}")
    Call<String> getFireData(@Path("key") String productId, @Path("product_type") String productType, @Path("country") String country, @Path("resolution") int resolution);
}
