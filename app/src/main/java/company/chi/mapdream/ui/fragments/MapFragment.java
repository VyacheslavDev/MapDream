package company.chi.mapdream.ui.fragments;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.persistence.BackendlessDataQuery;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import company.chi.mapdream.AlarmReceiver;
import company.chi.mapdream.R;
import company.chi.mapdream.mvp.presenter.FavoritesPresenter;
import company.chi.mapdream.mvp.presenter.MapsPresenter;
import company.chi.mapdream.mvp.view.IFavoritesView;
import company.chi.mapdream.mvp.view.IMapsView;
import company.chi.mapdream.network.backendless.model.Favorite;
import company.chi.mapdream.network.foursquare.model.Venue;

public class MapFragment extends Fragment implements IMapsView, IFavoritesView, OnMapReadyCallback, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    public static final int REQUEST_CODE = 100;
    private View mView;
    private GoogleMap mMap;
    private EditText mSearchEdit;
    private Marker mPositionMarkerNow = null;
    private ImageButton mImageButton;
    private FloatingActionButton mFloatingActionButton;
    private MapsPresenter mapsPresenter;
    private FavoritesPresenter favoritesPresenter;
    private boolean isActionBtnPress = false;
    private BottomSheetBehavior mBottomSheetBehavior;

    private ArrayList<Favorite> mFavorites = new ArrayList<>();
    private HashMap<Marker, Venue> mHashMapMarkerVenue = new HashMap<>();
    private LatLng mLatLng;


    private TextView mName_Place;
    private TextView mAddress;
    private TextView mContactPhone;
    private Button mVisitPlace;
    private CheckBox mCheckBox_Star_Favorite;
    private ImageView mImageView_Preview;
    private String mUrlAddressPlace;
    private Calendar mDate = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mapsPresenter = new MapsPresenter();
        favoritesPresenter = new FavoritesPresenter();

        mName_Place = (TextView) mView.findViewById(R.id.name_place_textview);
        mAddress = (TextView) mView.findViewById(R.id.address_place_textview);
        mContactPhone = (TextView) mView.findViewById(R.id.phone_place_textview);
        mImageView_Preview = (ImageView) mView.findViewById(R.id.app_bar_image);
        mCheckBox_Star_Favorite = (CheckBox) mView.findViewById(R.id.favorite_checkbox_star);
        mVisitPlace = (Button) mView.findViewById(R.id.VisitPlace_btn_bottom);

        View bottomSheet = mView.findViewById(R.id.bottom_sheet_nested);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(0);

        mFloatingActionButton = (FloatingActionButton) mView.findViewById(R.id.action_btn_search_position);
        mSearchEdit = (EditText) mView.findViewById(R.id.edit_text_map_search);
        mImageButton = (ImageButton) mView.findViewById(R.id.btn_go_map_search);


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        onListenBtnArrow();
        onListenBtnVisitPlace();
        onListenKeyboard();
        onListenGetMyPosition();
        onListenBottomSheet();
        onListenCheckBoxStarPress();

        mapsPresenter.getMyPoint(getActivity().getApplicationContext());
        return mView;
    }


    private void onListenCheckBoxStarPress() {
        mCheckBox_Star_Favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckBox_Star_Favorite.isChecked()) {
                    goToServerFavorits();
                } else {
                    deleteFavorite();
                }
            }
        });
    }

    private void deleteFavorite() {
        new AsyncTask<Favorite, Favorite, Favorite>() {
            @Override
            protected Favorite doInBackground(Favorite... params) {
                String whereClause = "lng = " + "'" + mLatLng.longitude + "'";
                BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                dataQuery.setWhereClause(whereClause);

                BackendlessCollection<Map> map = Backendless.Persistence.of("Favorite").find(dataQuery);
                Long result = Backendless.Persistence.of("PhoneBook").remove(map.getData().get(0));
                if (result != null) {
                    for (Favorite favorite : mFavorites) {
                        if (mLatLng.longitude == favorite.getLng()) {
                            Log.e("ERRRRRRORRRRRR", "Бекендлс свое дело сделал");
                            if (mFavorites.remove(favorite)) {
                                Log.e("ERRRRRRORRRRRR", "Бекендл внатуре красавчик");
                            }
                        }
                    }
                }
                return null;
            }

        }.execute();


    }

    private HashMap shapeHashMap(LatLng latLng, String name, String phone, String address) {
        HashMap addLineToFavorite = new HashMap();
        addLineToFavorite.put("lat", latLng.latitude);
        addLineToFavorite.put("lng", latLng.longitude);
        addLineToFavorite.put("name", name);
        addLineToFavorite.put("phone", phone);
        addLineToFavorite.put("address", address);
        return addLineToFavorite;
    }

    private void goToServerFavorits() {
//        new AsyncTask<Map, Map, Map>() {
//
//            @Override
//            protected void onPreExecute() {
//                shapeHashMap(
//                        mLatLng,
//                        mName_Place.getText().toString(),
//                        mContactPhone.getText().toString(),
//                        mAddress.getText().toString());
//                super.onPreExecute();
//
//            }
//            @Override
//            protected Map doInBackground(Map... params) {
//                Map response = Backendless.Persistence.of("Favorite").save(params[0]);
//                return response;
//            }
//            @Override
//            protected void onPostExecute(Map response) {
//                super.onPostExecute(response);
//
//                mFavorites.add(new Favorite(
//                        (double) response.get("lat"),
//                        (double) response.get("lng"),
//                        response.get("name").toString(),
//                        response.get("phone").toString(),
//                        response.get("address").toString(),
//                        (Date) response.get("created")
//                ));
//            }
//        }.execute();

        class AsyncTaskContol extends AsyncTask<Map, Map, Map> {
            HashMap mResp = new HashMap();

            public AsyncTaskContol(LatLng latLng, String name, String phone, String address) {
                mResp = shapeHashMap(latLng, name, phone, address);
            }

            private HashMap shapeHashMap(LatLng latLng, String name, String phone, String address) {
                HashMap addLineToFavorite = new HashMap();
                addLineToFavorite.put("lat", latLng.latitude);
                addLineToFavorite.put("lng", latLng.longitude);
                addLineToFavorite.put("name", name);
                addLineToFavorite.put("phone", phone);
                addLineToFavorite.put("address", address);
                return addLineToFavorite;
            }

            @Override
            protected Map doInBackground(Map... params) {
                Map response = Backendless.Persistence.of("Favorite").save(mResp);
                return response;
            }

            @Override
            protected void onPostExecute(Map response) {
                super.onPostExecute(response);
                mFavorites.add(new Favorite(
                        (double) response.get("lat"),
                        (double) response.get("lng"),
                        response.get("name").toString(),
                        response.get("phone").toString(),
                        response.get("address").toString(),
                        (Date) response.get("created")
                ));
            }
        }

        AsyncTaskContol asyncTaskContol = new AsyncTaskContol(mLatLng,
                mName_Place.getText().toString(),
                mContactPhone.getText().toString(),
                mAddress.getText().toString());
        asyncTaskContol.execute();
    }

    private void onListenBottomSheet() {
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                                                        @Override
                                                        public void onStateChanged(@NonNull View bottomSheet, int newState) {

                                                        }

                                                        @Override
                                                        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                                                            if (slideOffset < 0.01) {
                                                                mBottomSheetBehavior.setPeekHeight(0);
                                                            }
                                                        }
                                                    }

        );
    }

    private void onListenKeyboard() {
        mSearchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideKeydoard();
                    mapsPresenter.getPointOnMapFromFoursquare(String.valueOf(mSearchEdit.getText()));
                    clearAllData();
                    return true;
                }
                return false;
            }
        });

    }

    private void hideKeydoard() {
        InputMethodManager inputManager = (InputMethodManager)
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void clearAllData() {
        mMap.clear();
        mHashMapMarkerVenue.clear();
        mBottomSheetBehavior.setPeekHeight(0);
    }

    private void onListenBtnArrow() {
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeydoard();
                mapsPresenter.getPointOnMapFromFoursquare(String.valueOf(mSearchEdit.getText()));
                clearAllData();
            }
        });
    }

    private void onListenGetMyPosition() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                isActionBtnPress = true;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mapsPresenter.attach(this);
        favoritesPresenter.attach(this);
        favoritesPresenter.getFavorites();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapsPresenter.detach();
        favoritesPresenter.detach();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        onShowMarker();
    }

    void onShowMarker() {
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 13));
                marker.showInfoWindow();
                fillBottomSheetData(marker);
                mBottomSheetBehavior.setPeekHeight(150);
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                return true;
            }
        });
    }

    private void isFavorites(Marker marker) {
        for (Favorite favorite : mFavorites) {
            if (marker.getPosition().longitude == favorite.getLng() && marker.getPosition().latitude == favorite.getLat()) {
                mCheckBox_Star_Favorite.setChecked(true);
                break;
            } else {
                mCheckBox_Star_Favorite.setChecked(false);
            }
        }
    }

    private void fillBottomSheetData(Marker marker) {
        isFavorites(marker);

        mLatLng = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
        mName_Place.setText(mHashMapMarkerVenue.get(marker).getName());
        if (mHashMapMarkerVenue.get(marker).getContact().getFormattedPhone() == null) {
            mContactPhone.setText(getString(R.string.unknown));
        } else {
            mContactPhone.setText(mHashMapMarkerVenue.get(marker).getContact().getFormattedPhone());
        }
        if (mHashMapMarkerVenue.get(marker).getLocation().getAddress() == null) {
            mAddress.setText(getString(R.string.unknown));
        } else {
            mAddress.setText(mHashMapMarkerVenue.get(marker).getLocation().getAddress());
        }

        mUrlAddressPlace = mHashMapMarkerVenue.get(marker).getCategories().get(0).getIcon().getPrefix() + "100" +
                mHashMapMarkerVenue.get(marker).getCategories().get(0).getIcon().getSuffix();

        Picasso.with(getContext())
                .load(mUrlAddressPlace)
                .resize(100, 100)
                .error(R.drawable.ic_do_not_disturb_black_48dp)
                .centerCrop()
                .into(mImageView_Preview);
    }

    @Override
    public void onLocationLoaded(MarkerOptions markerOptions, Venue venue, boolean error) {
        if (error) {
            Snackbar.make(mView.findViewById(android.R.id.content), getString(R.string.error_get_data), Snackbar.LENGTH_LONG);
        } else {
            mHashMapMarkerVenue.put(mMap.addMarker(markerOptions), venue);
        }
    }

    @Override
    public void dontHavePosition() {
        showSnackbarOnTheTop(Snackbar.make(getActivity().findViewById(android.R.id.content), getString(R.string.hasPosition), Snackbar.LENGTH_LONG));
    }

    @Override
    public void onPositionComplete(Location location) {
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.ic_point_current);

        if (mPositionMarkerNow != null) {
            mPositionMarkerNow.remove();
        }
        mPositionMarkerNow = mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).icon(BitmapDescriptorFactory.fromBitmap(icon)).title(getString(R.string.map_position)));
        if (isActionBtnPress) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mPositionMarkerNow.getPosition(), 16));
            isActionBtnPress = false;
        }
    }

    @Override
    public void onSaveVisitAtdb() {

    }

    private void showSnackbarOnTheTop(Snackbar snack) {
        View view = snack.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);

        TextView snackbarTextView = (TextView) snack.getView().findViewById(android.support.design.R.id.snackbar_text);
        snackbarTextView.setTextSize(getResources().getInteger(R.integer.textSize_SnackBar_Map_Activity));
        snackbarTextView.setMaxLines(getResources().getInteger(R.integer.maxLines_SnackBar_Map_Activity));
        snack.show();
    }

    @Override
    public void onFavoriteLoaded(ArrayList<Favorite> Favorite) {
        mFavorites = Favorite;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        mDate.set(Calendar.YEAR, year);
        mDate.set(Calendar.MONTH, monthOfYear);
        mDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                MapFragment.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true
        );


        tpd.show(getActivity().getFragmentManager(), "Timepickerdialog");
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        mDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mDate.set(Calendar.MINUTE, minute);
        mDate.set(Calendar.SECOND, second);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mapsPresenter.onLoadSaveVisit(mLatLng, mUrlAddressPlace, mName_Place.getText().toString(), mDate.getTime());
                    setAlarmManager();
                } catch (Exception e) {
                }
            }
        }).start();

    }

    private void setAlarmManager() {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        intent.putExtra("title", mName_Place.getText().toString());
        intent.putExtra("Description", getString(R.string.you_want_visit_in, mDate.get(Calendar.HOUR_OF_DAY) + ":" + mDate.get(Calendar.MINUTE)) + " " + mName_Place.getText().toString());

        PendingIntent alarmIntent = PendingIntent.getBroadcast(getActivity(), REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, mDate.getTime().getTime(), alarmIntent);

    }

    private void onListenBtnVisitPlace() {
        mVisitPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        MapFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });
    }


}
