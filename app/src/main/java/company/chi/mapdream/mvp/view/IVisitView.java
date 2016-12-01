package company.chi.mapdream.mvp.view;


import java.util.ArrayList;

import company.chi.mapdream.data.model.Visit;

public interface IVisitView {
    void onVisitsLoaded(ArrayList<Visit> visits);
}
