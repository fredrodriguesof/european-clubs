package com.test.twinster.twinstertest.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.test.twinster.twinstertest.Activities.Adapter.RcvClubAdapter;
import com.test.twinster.twinstertest.Model.Club;
import com.test.twinster.twinstertest.Model.ValueComparator;
import com.test.twinster.twinstertest.R;
import com.test.twinster.twinstertest.Utils.RetrofitConfig;
import java.util.ArrayList;
import java.util.Collections;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private RcvClubAdapter mAdapter;
    private boolean alphabeticalSort = false;
    private ArrayList<Club> clubs;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnSort) {
            if (alphabeticalSort) {
                Collections.sort(clubs);
            }
            else {
                ValueComparator comparator = new ValueComparator();
                Collections.sort(clubs, comparator);
            }

            setupRecycler(clubs);
            alphabeticalSort = !alphabeticalSort;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getClubsList();
    }

    private void setupRecycler(ArrayList<Club> clubs) {
        mRecyclerView = findViewById(R.id.rcvClub);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new RcvClubAdapter(this, clubs);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void getClubsList() {
        Call<ArrayList<Club>> call = new RetrofitConfig().getClubService().searchClubs();

        call.enqueue(new Callback<ArrayList<Club>>() {
            @Override
            public void onResponse(Call<ArrayList<Club>> call, Response<ArrayList<Club>> response) {
                clubs = response.body();
                Collections.sort(clubs);
                setupRecycler(clubs);
            }

            @Override
            public void onFailure(Call<ArrayList<Club>> call, Throwable t) {
                Log.e("ClubService   ", "Error fetching the club list:" + t.getMessage());
            }
        });
    }
}
