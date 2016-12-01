package company.chi.mapdream.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;

import company.chi.mapdream.R;
import company.chi.mapdream.adapters.AdapterFavorites;
import company.chi.mapdream.mvp.presenter.FavoritesPresenter;
import company.chi.mapdream.mvp.view.IFavoritesView;
import company.chi.mapdream.network.backendless.model.Favorite;

public class FavoritesFragment extends Fragment implements IFavoritesView {
    private View mView;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private AdapterFavorites mAdapterFavorites;
    private ArrayList<Favorite> mFavorites = new ArrayList<>();
    private FavoritesPresenter mFavoritesPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_favorites,container,false);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycleView_favorites);

        mToolbar = (Toolbar) mView.findViewById(R.id.toolbar_favorites);
        mToolbar.setTitle(getString(R.string.my_favorites));

        mFavoritesPresenter = new FavoritesPresenter();
        return mView;
    }

    private void onDrawRecyclerView(ArrayList<Favorite> Favorite) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapterFavorites = new AdapterFavorites(Favorite);
        mRecyclerView.setAdapter(mAdapterFavorites);
    }


    @Override
    public void onStart() {
        super.onStart();
        mFavoritesPresenter.attach(this);
        mFavoritesPresenter.getFavorites();
    }

    @Override
    public void onStop() {
        super.onStop();
        mFavoritesPresenter.detach();
    }

    @Override
    public void onFavoriteLoaded(ArrayList<Favorite> Favorite) {
     mFavorites = Favorite;
     onDrawRecyclerView(mFavorites);
    }
}
