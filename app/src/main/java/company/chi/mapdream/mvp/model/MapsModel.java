package company.chi.mapdream.mvp.model;


import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;

import company.chi.mapdream.network.foursquare.response.getPointOnMapResponse;
import company.chi.mapdream.network.retrofit.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsModel implements IMapsModel {
    @Override
    public void loadPlaces(final OnLoadListener onLoadListener, String search, LatLng latLng) {
        RestClient.getPointOnMap(latLng.latitude, latLng.longitude, search, 10000, new Callback<getPointOnMapResponse>() {
            @Override
            public void onResponse(Call<getPointOnMapResponse> call, Response<getPointOnMapResponse> response) {
                onLoadListener.onSuccesses(response.body().getResponse().getVenues());
            }

            @Override
            public void onFailure(Call<getPointOnMapResponse> call, Throwable t) {
                onLoadListener.onFail(true);
            }
        });
    }

    @Override
    public void saveVisit(HashMap hashMap, final OnSaveListenerVIsit onSaveListenerVIsit) {
        Backendless.Persistence.of("Visit").save(hashMap, new AsyncCallback<Map>() {
            @Override
            public void handleResponse(Map response) {
                onSaveListenerVIsit.onSuccess();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                onSaveListenerVIsit.onFail();
            }
        });
    }


}
