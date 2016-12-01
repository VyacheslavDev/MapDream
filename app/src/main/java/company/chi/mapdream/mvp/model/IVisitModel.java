package company.chi.mapdream.mvp.model;


import java.util.ArrayList;
import java.util.List;

import company.chi.mapdream.data.model.Visit;

public interface IVisitModel {
    interface VisitListener{
        void onVisitsLoaded(ArrayList<Visit> visits);
        void onFailed(String error);
    }

    void loadVisits(VisitListener callback);
}
