package com.test.twinster.twinstertest.Utils;

import com.test.twinster.twinstertest.Model.Club;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ClubService {

    @GET("clubs.json")
    Call<ArrayList<Club>> searchClubs();

}