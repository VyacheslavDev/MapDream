package company.chi.mapdream.mvp.view;

import java.util.ArrayList;

import company.chi.mapdream.network.backendless.model.Favorite;

public interface IFavoritesView {
    void onFavoriteLoaded(ArrayList<Favorite> Favorite);
}
