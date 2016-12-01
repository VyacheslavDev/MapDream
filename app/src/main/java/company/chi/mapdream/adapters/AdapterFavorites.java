package company.chi.mapdream.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import company.chi.mapdream.R;
import company.chi.mapdream.network.backendless.model.Favorite;

public class AdapterFavorites extends RecyclerView.Adapter<AdapterFavorites.FavoritesAdapterHolder>{

    private ArrayList<Favorite> mArrayListFavorites;
    private Context mContext;

    public AdapterFavorites(ArrayList<Favorite> mArrayListFavorites) {
        this.mArrayListFavorites = mArrayListFavorites;
    }

    public class FavoritesAdapterHolder extends RecyclerView.ViewHolder{

        TextView mDateTextView, mPhoneNumberTextView, mNameTextView, mAddressTextView;

        public FavoritesAdapterHolder(View itemView) {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.name_favorites_card);
            mDateTextView = (TextView) itemView.findViewById(R.id.date_favorites_card);
            mPhoneNumberTextView = (TextView) itemView.findViewById(R.id.description_favorites_card_phone);
            mAddressTextView = (TextView) itemView.findViewById(R.id.description_favorites_card_address);
        }
    }

    @Override
    public FavoritesAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(R.layout.favorite_item, parent, false);
        FavoritesAdapterHolder favoritesAdapterHolder = new FavoritesAdapterHolder(v);
        return favoritesAdapterHolder;
    }

    @Override
    public void onBindViewHolder(FavoritesAdapterHolder holder, int position) {
        holder.mNameTextView.setText(mArrayListFavorites.get(position).getName());
        holder.mPhoneNumberTextView.setText(mArrayListFavorites.get(position).getPhone());
        holder.mAddressTextView.setText(mArrayListFavorites.get(position).getAddress());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy' 'HH:mm");
        holder.mDateTextView.setText(simpleDateFormat.format(mArrayListFavorites.get(position).getCreated()));
    }

    @Override
    public int getItemCount() {
        return mArrayListFavorites.size();
    }


}
