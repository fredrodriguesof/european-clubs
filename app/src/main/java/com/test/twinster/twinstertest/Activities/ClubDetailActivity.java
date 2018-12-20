package com.test.twinster.twinstertest.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.test.twinster.twinstertest.Model.Club;
import com.test.twinster.twinstertest.R;
import com.test.twinster.twinstertest.Utils.DownloadImageTask;

public class ClubDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_detail_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Club club = (Club) getIntent().getSerializableExtra("club");
        configDetails(club);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    private void configDetails(Club club){
        ImageView imvClubLogoDetail = findViewById(R.id.imvClubLogoDetail);
        TextView txvClubNameDetail = findViewById(R.id.txvClubNameDetail);
        TextView txvClubDescription = findViewById(R.id.txvClubDescription);

        String clubDesc = getString(R.string.club_desc, club.getName(), club.getCountry(), club.getValue());

        setTitle(club.getName());
        txvClubNameDetail.setText(club.getCountry());
        txvClubDescription.setText(clubDesc);
        imvClubLogoDetail.setImageResource(R.drawable.club_placeholder);
        new DownloadImageTask(imvClubLogoDetail).execute(club.getImage());
    }

}
