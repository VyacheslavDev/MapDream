package company.chi.mapdream.mvp.model;

import java.util.ArrayList;

import company.chi.mapdream.network.backendless.model.Favorite;


public interface IFavoritesModel {
    interface FavoritesListener{
        void onFavoritesLoaded(ArrayList<Favorite> Favorite);
        void onFailed(String error);
    }

    void loadVisits(IFavoritesModel.FavoritesListener callback);
}
