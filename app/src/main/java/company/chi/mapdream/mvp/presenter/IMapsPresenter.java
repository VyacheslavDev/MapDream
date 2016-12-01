package company.chi.mapdream.mvp.presenter;


import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

import company.chi.mapdream.mvp.view.IMapsView;

public interface IMapsPresenter {
    void attach(IMapsView iMapsView);
    void detach();

    void onLoadSaveVisit(LatLng latLng, String photoUrl, String namePlace, Date dateTime);

}
