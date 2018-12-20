package com.test.twinster.twinstertest.Activities.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.test.twinster.twinstertest.Activities.ClubDetailActivity;
import com.test.twinster.twinstertest.Model.Club;
import com.test.twinster.twinstertest.R;
import com.test.twinster.twinstertest.Utils.DownloadImageTask;

import java.util.ArrayList;

public class RcvClubAdapter extends RecyclerView.Adapter<RcvClubAdapter.RcvClubViewHolder> {

    private final Context mCtx;
    private final ArrayList<Club> mClubs;

    public RcvClubAdapter(Context ctx, ArrayList clubs) {
        mCtx = ctx;
        mClubs = clubs;
    }

    @Override
    public RcvClubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RcvClubViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.club_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RcvClubViewHolder holder, int position) {
        Club club = mClubs.get(position);
        String valueMarket = club.getValue() + " " + mCtx.getResources().getString(R.string.million);

        holder.txvClubName.setText(club.getName());
        holder.txvClubCountry.setText(club.getCountry());
        holder.txvClubValue.setText(valueMarket);
        holder.imvClubLogo.setImageResource(R.drawable.club_placeholder);
        new DownloadImageTask(holder.imvClubLogo).execute(club.getImage());
    }

    @Override
    public int getItemCount() {
        return mClubs != null ? mClubs.size() : 0;
    }

    protected class RcvClubViewHolder extends RecyclerView.ViewHolder {

        protected TextView txvClubName;
        protected TextView txvClubCountry;
        protected TextView txvClubValue;
        protected ImageView imvClubLogo;

        public RcvClubViewHolder(final View itemView) {
            super(itemView);

            txvClubName = itemView.findViewById(R.id.txvClubName);
            txvClubCountry = itemView.findViewById(R.id.txvClubCountry);
            txvClubValue = itemView.findViewById(R.id.txvClubValue);
            imvClubLogo = itemView.findViewById(R.id.imvClubLogo);

            //Setup the click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =  new Intent(mCtx, ClubDetailActivity.class);
                    intent.putExtra("club", mClubs.get(getLayoutPosition()));
                    mCtx.startActivity(intent);
                }
            });
        }
    }
}