package company.chi.mapdream.mvp.presenter;


import company.chi.mapdream.mvp.view.IFavoritesView;

public interface IFavoritesPresenter {
    void attach(IFavoritesView iFavoritesView);
    void detach();
}
