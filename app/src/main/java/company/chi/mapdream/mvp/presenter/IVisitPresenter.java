package company.chi.mapdream.mvp.presenter;


import company.chi.mapdream.mvp.view.IVisitView;

public interface IVisitPresenter {
    void attach(IVisitView visitView);
    void detach();

}
