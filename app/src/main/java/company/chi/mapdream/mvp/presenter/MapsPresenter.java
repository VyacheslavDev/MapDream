package company.chi.mapdream.mvp.presenter;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import company.chi.mapdream.mvp.model.IMapsModel;
import company.chi.mapdream.mvp.model.MapsModel;
import company.chi.mapdream.mvp.view.IMapsView;
import company.chi.mapdream.network.foursquare.model.Venue;

public class MapsPresenter implements LocationListener, IMapsPresenter {

    private Context mContext;
    private MapsModel mapsModel;
    private IMapsView iMapsView;
    private Marker mPositionMarkerNow = null;
    public MapsPresenter() {
        mapsModel = new MapsModel();
    }


    @Override
    public void attach(IMapsView iMapsView) {
        this.iMapsView = iMapsView;
    }

    @Override
    public void detach() {
        iMapsView = null;
    }

    @Override
    public void onLoadSaveVisit(LatLng latLng, String photoUrl, String namePlace, Date dateTime) {
         mapsModel.saveVisit(shapeHashMapVisit(latLng, photoUrl, namePlace, dateTime)
                 , new IMapsModel.OnSaveListenerVIsit() {
                     @Override
                     public void onSuccess() {
                         iMapsView.onSaveVisitAtdb();
                     }

                     @Override
                     public void onFail() {

                     }
                 });
    }

    public void getPointOnMapFromFoursquare(String search) {
        if(getFromSharedPref() != null) {
            mapsModel.loadPlaces(new IMapsModel.OnLoadListener() {
                @Override
                public void onSuccesses(List<Venue> responses) {
                    for (int i = 0; i < responses.size(); i++) {
                        MarkerOptions markerOptions = new MarkerOptions().position(
                                new LatLng(
                                        responses.get(i).getLocation().getLat(),
                                        responses.get(i).getLocation().getLng()))
                                .title(responses.get(i).getName());
                        iMapsView.onLocationLoaded(markerOptions, responses.get(i), false);
                    }
                }

                @Override
                public void onFail(boolean error) {
                    iMapsView.onLocationLoaded(null, null, true);
                }
            }, search, getFromSharedPref());
        } else {
            iMapsView.dontHavePosition();
        }
    }

    public void getMyPoint(Context context) {
        mContext = context;
        LocationManager locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = this;

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 15000, 15, locationListener);
    }


    @Override
    public void onLocationChanged(Location location) {
        if (mPositionMarkerNow != null) {
            mPositionMarkerNow.remove();
        }
        setFromSharedPref((float) location.getLatitude(), (float) location.getLongitude());
        iMapsView.onPositionComplete(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    private void setFromSharedPref(float lat, float lng) {
        SharedPreferences sharedPref = mContext.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("Lat", lat);
        editor.putFloat("Lng", lng);
        editor.commit();
    }

    private LatLng getFromSharedPref() {
        SharedPreferences sharedPref = mContext.getSharedPreferences("data", Context.MODE_PRIVATE);
        if (sharedPref.getFloat("Lat", 0) != 0) {
            return new LatLng(sharedPref.getFloat("Lat", 0), sharedPref.getFloat("Lng", 0));
        }
        return null;
    }

    private HashMap shapeHashMapVisit(LatLng latLng, String photoUrl, String namePlace, Date dateTime) {
        HashMap addLineToFavorite = new HashMap();
        addLineToFavorite.put("lat", latLng.latitude);
        addLineToFavorite.put("lng", latLng.longitude);
        addLineToFavorite.put("PhotoUrl", photoUrl);
        addLineToFavorite.put("NamePlace", namePlace);
        addLineToFavorite.put("DateTime", dateTime);
        return addLineToFavorite;
    }
}
