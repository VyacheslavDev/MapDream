package company.chi.mapdream.network.retrofit;

import company.chi.mapdream.network.foursquare.response.getPointOnMapResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRetrofit {
    @GET("venues/search?client_id=P2CBNP31QJMWXFQ4Z1HIR3LDM4IKLBGJJULWYUGL4FAMDX4R&client_secret=GV5UHWYW3CAY4RCFYLR5OKU3T10IKOF4ASNO1Z0NEP4SVC0O&v=20161021")
    Call<getPointOnMapResponse> getPointOnMap(@Query("ll") String latLong, @Query("query") String search, @Query("range") int range);

}
