package company.chi.mapdream.mvp.presenter;


import java.util.ArrayList;
import java.util.List;

import company.chi.mapdream.mvp.model.IVisitModel;
import company.chi.mapdream.mvp.model.VisitModel;
import company.chi.mapdream.mvp.view.IVisitView;
import company.chi.mapdream.data.model.Visit;

public class VisitPresenter implements IVisitPresenter {
    private VisitModel mVisitModel;
    private IVisitView mVisitsView;


    public VisitPresenter() {
        mVisitModel = new VisitModel();
    }


    @Override
    public void attach(IVisitView visitView) {
        mVisitsView = visitView;
    }

    @Override
    public void detach() {
        mVisitsView = null;
    }

    public void getVisits() {
        mVisitModel.loadVisits(new IVisitModel.VisitListener() {
            @Override
            public void onVisitsLoaded(ArrayList<Visit> visits) {
                if (mVisitsView != null) {
                    mVisitsView.onVisitsLoaded(visits);
                }
            }

            @Override
            public void onFailed(String error) {

            }
        });


    }

}
