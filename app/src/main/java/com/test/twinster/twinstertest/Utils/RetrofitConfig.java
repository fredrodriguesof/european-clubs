package com.test.twinster.twinstertest.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                            .baseUrl("https://public.allaboutapps.at/hiring/")
                            .addConverterFactory(JacksonConverterFactory.create())
                            .build();
    }

    public ClubService getClubService() {
        return this.retrofit.create(ClubService.class);
    }

}