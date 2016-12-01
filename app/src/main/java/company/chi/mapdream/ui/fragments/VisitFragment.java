package company.chi.mapdream.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.List;

import company.chi.mapdream.R;
import company.chi.mapdream.adapters.AdapterVisits;
import company.chi.mapdream.data.model.Visit;
import company.chi.mapdream.mvp.presenter.VisitPresenter;
import company.chi.mapdream.mvp.view.IVisitView;

public class VisitFragment extends Fragment implements IVisitView {

    private VisitPresenter presenter;
    private View mView;
    private Toolbar mToolbar;
    private AdapterVisits mAdapterVisits;
    private RecyclerView mRecyclerView;
    private ArrayList<Visit> mVisits = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_visits, container, false);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mToolbar = (Toolbar) mView.findViewById(R.id.toolbar_visits);
        mToolbar.setTitle(getString(R.string.you_visits));
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycleView_visits);


        presenter = new VisitPresenter();

        return mView;
    }

    private void onDrawRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapterVisits = new AdapterVisits(mVisits);
        mRecyclerView.setAdapter(mAdapterVisits);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attach(this);
        presenter.getVisits();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detach();
    }

    @Override
    public void onVisitsLoaded(ArrayList<Visit> visits) {
        mVisits = visits;
        onDrawRecyclerView();
    }


}
