package company.chi.mapdream.mvp.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.List;

import company.chi.mapdream.network.foursquare.model.Venue;

public interface IMapsModel {
    interface OnLoadListener{
        void onSuccesses(List<Venue> responses);
        void onFail(boolean error);
    }
    void loadPlaces(OnLoadListener onLoadListener, String search, LatLng latLng);
    interface  OnSaveListenerVIsit{
        void onSuccess();
        void onFail();
    }
    void saveVisit( HashMap hashMap, OnSaveListenerVIsit onSaveListenerVIsit);
}
