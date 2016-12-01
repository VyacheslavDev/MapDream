package company.chi.mapdream.network.retrofit;


import java.util.concurrent.TimeUnit;

import company.chi.mapdream.network.foursquare.response.getPointOnMapResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static final String base_url = "https://api.foursquare.com/v2/";
    private static ApiRetrofit mService;

    public static ApiRetrofit initRetrofit() {
        if (mService == null) {
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logInterceptor)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(base_url)
                    .build();
            mService = retrofit.create(ApiRetrofit.class);
            return mService;
        } else {
            return mService;
        }
    }

    public static void getPointOnMap(double lat, double lng, String search, int range, Callback<getPointOnMapResponse> callback) {
        String latLong = lat+","+lng;
        Call<getPointOnMapResponse> call = mService.getPointOnMap(latLong, search, range);
        call.enqueue(callback);
    }

}
