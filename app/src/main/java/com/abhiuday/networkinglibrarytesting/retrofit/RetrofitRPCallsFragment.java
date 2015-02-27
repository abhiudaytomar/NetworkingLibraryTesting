package com.abhiuday.networkinglibrarytesting.retrofit;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhiuday.networkinglibrarytesting.R;

/**
 Created by abhiuday.tomar on 27/02/15.
 */
public class RetrofitRPCallsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.retrofit_rp_calls_fragment, container, false);
        return rootView;
    }
}
