package company.chi.mapdream.mvp.model;


import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import company.chi.mapdream.data.model.Visit;
import company.chi.mapdream.network.backendless.model.Favorite;

public class VisitModel implements IVisitModel {

    private ArrayList<Visit> mVisits = new ArrayList<>();

    @Override
    public void loadVisits(final VisitListener callback) {
        Backendless.Persistence.of("Visit").find(new AsyncCallback<BackendlessCollection<Map>>() {
            @Override
            public void handleResponse(BackendlessCollection<Map> response) {
                for (Map map : response.getData()) {
                    mVisits.add(new Visit(
                            (double) map.get("lat"),
                            (double) map.get("lng"),
                            map.get("NamePlace").toString(),
                            map.get("PhotoUrl").toString(),
                             (Date) map.get("DateTime"),
                            (Date) map.get("created")
                    ));
                }
                callback.onVisitsLoaded(mVisits);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                callback.onFailed(fault.toString());
            }
        });
    }
}
