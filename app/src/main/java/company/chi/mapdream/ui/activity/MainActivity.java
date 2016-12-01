package company.chi.mapdream.ui.activity;

import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import company.chi.mapdream.R;
import company.chi.mapdream.ui.fragments.FavoritesFragment;
import company.chi.mapdream.ui.fragments.MapFragment;
import company.chi.mapdream.ui.fragments.VisitFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private FragmentTransaction mFragmentTransaction;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view_singup);

        replaceFragment(R.id.place_for_fragment_FrameLayout,  new MapFragment());


        onListenNavigationView();

    }

    private void replaceFragment(int resId, Fragment fragment) {
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(resId,  fragment);
        mFragmentTransaction.commit();
    }

    private void onListenNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.see_map) {
            replaceFragment(R.id.place_for_fragment_FrameLayout,  new MapFragment());
        } else if (id == R.id.my_favorites) {
            replaceFragment(R.id.place_for_fragment_FrameLayout,  new FavoritesFragment());
        } else if (id == R.id.my_visits) {
            replaceFragment(R.id.place_for_fragment_FrameLayout,  new VisitFragment());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
