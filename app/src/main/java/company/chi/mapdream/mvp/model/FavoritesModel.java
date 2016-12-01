package company.chi.mapdream.mvp.model;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import company.chi.mapdream.network.backendless.model.Favorite;

public class FavoritesModel implements IFavoritesModel {

    private ArrayList<Favorite> mFavorites = new ArrayList<>();
    @Override
    public void loadVisits(final FavoritesListener callback) {
        Backendless.Persistence.of( "Favorite" ).find(new AsyncCallback<BackendlessCollection<Map>>() {
            @Override
            public void handleResponse(BackendlessCollection<Map> response){
                for (Map map : response.getData()) {
                    mFavorites.add(new Favorite(
                            (double) map.get("lat"),
                            (double) map.get("lng"),
                            map.get("name").toString(),
                            map.get("phone").toString(),
                            map.get("address").toString(),
                            (Date) map.get("created")
                    ));
                }
                callback.onFavoritesLoaded(mFavorites);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                callback.onFailed(fault.toString());
            }
        });
    }
}
