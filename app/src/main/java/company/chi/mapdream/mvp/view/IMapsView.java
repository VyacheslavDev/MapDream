package company.chi.mapdream.mvp.view;


import android.location.Location;

import com.google.android.gms.maps.model.MarkerOptions;

import company.chi.mapdream.network.foursquare.model.Venue;

public interface IMapsView {
    void onLocationLoaded(MarkerOptions markerOptions, Venue venue, boolean error);
    void dontHavePosition();
    void onPositionComplete(Location location);
    void onSaveVisitAtdb();
}
