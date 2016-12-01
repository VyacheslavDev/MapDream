package company.chi.mapdream.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import company.chi.mapdream.R;
import company.chi.mapdream.data.model.Visit;

public class AdapterVisits extends RecyclerView.Adapter<AdapterVisits.VisitAdapterHolder> {


    private ArrayList<Visit> mArrayListVisit;
    private Context mContext;

    public static class VisitAdapterHolder extends RecyclerView.ViewHolder {

        TextView mDateTextView, mDescriptionTextView, mNameTextView, mSortTextView;
        ImageView mImageView;

        public VisitAdapterHolder(View view) {
            super(view);
            mNameTextView = (TextView) view.findViewById(R.id.name_visits_card);
            mDateTextView = (TextView) view.findViewById(R.id.date_visits_card);
            mDescriptionTextView = (TextView) view.findViewById(R.id.description_visits_card);
            mImageView = (ImageView) view.findViewById(R.id.image_visits_card);
            mSortTextView = (TextView) view.findViewById(R.id.sortOfDate_textView);
        }
    }

    public AdapterVisits(ArrayList<Visit> mArrayListVisit) {
        this.mArrayListVisit = mArrayListVisit;
    }

    @Override
    public VisitAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(R.layout.visits_item, parent, false);
        VisitAdapterHolder visitAdapterHolder = new VisitAdapterHolder(v);

        return visitAdapterHolder;
    }

    @Override
    public void onBindViewHolder(VisitAdapterHolder holder, int position) {

        holder.mNameTextView.setText(mArrayListVisit.get(position).getNamePlace());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy' 'HH:mm");
        holder.mDateTextView.setText(simpleDateFormat.format(mArrayListVisit.get(position).getCreate()));
        holder.mDescriptionTextView.setText(mContext.getString(R.string.timeVisit)+" "+simpleDateFormat.format(mArrayListVisit.get(position).getDateTime()));

        Picasso.with(mContext)
                .load(String.valueOf(mArrayListVisit.get(position).getPhotoUrl()))
                .placeholder(R.drawable.ic_search_black_24dp)
                .error(R.drawable.ic_close_black_24dp)
                .resize(50,50)
                .into(holder.mImageView);

        if (mArrayListVisit.get(position).getDateTime().getTime() > System.currentTimeMillis()) {
            holder.mSortTextView.setText("Предстоит");
            holder.mSortTextView.setVisibility(View.VISIBLE);
        } // TODO: 11/16/2016  
    }


    @Override
    public int getItemCount() {
        return mArrayListVisit.size();
    }
}
