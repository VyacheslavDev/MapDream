package company.chi.mapdream;

import com.backendless.Backendless;

import company.chi.mapdream.network.retrofit.RestClient;


public class Application extends android.app.Application {
    private String android_key = "6CFF8B01-CEF3-7B52-FF7A-0718D8C79400";
    private String app_key = "BB365E68-20ED-EF59-FFA4-A8642ABBC500";
    private String version_backendless = "v1";

    @Override
    public void onCreate() {
        super.onCreate();
        RestClient.initRetrofit();
        Backendless.initApp(this, app_key, android_key, version_backendless);


    }
}
