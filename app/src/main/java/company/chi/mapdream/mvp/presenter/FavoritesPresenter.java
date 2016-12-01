package company.chi.mapdream.mvp.presenter;


import java.util.ArrayList;

import company.chi.mapdream.mvp.model.FavoritesModel;
import company.chi.mapdream.mvp.model.IFavoritesModel;
import company.chi.mapdream.mvp.view.IFavoritesView;
import company.chi.mapdream.network.backendless.model.Favorite;

public class FavoritesPresenter implements IFavoritesPresenter {
    private FavoritesModel mFavoritesModel;
    private IFavoritesView mIFavoritesView;

    public FavoritesPresenter() {
        mFavoritesModel = new FavoritesModel();
    }

    @Override
    public void attach(IFavoritesView iFavoritesView) {
        mIFavoritesView = iFavoritesView;
    }

    @Override
    public void detach() {
        mIFavoritesView = null;
    }

    public void getFavorites() {
        mFavoritesModel.loadVisits(new IFavoritesModel.FavoritesListener() {
            @Override
            public void onFavoritesLoaded(ArrayList<Favorite> Favorite) {
                if (mIFavoritesView != null) {
                    mIFavoritesView.onFavoriteLoaded(Favorite);
                }
            }

            @Override
            public void onFailed(String error) {

            }
        });

    }


}
