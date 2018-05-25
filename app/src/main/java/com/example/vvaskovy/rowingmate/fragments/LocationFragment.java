package com.example.vvaskovy.rowingmate.fragments;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vvaskovy.rowingmate.GPSTracker;
import com.example.vvaskovy.rowingmate.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class LocationFragment extends Fragment implements OnMapReadyCallback {



    GoogleMap mGoogleMap;
    MapView mapView;
    View view;
    GPSTracker gpsTracker;
    Location location;
    double latitude, longitude;
    //private OnFragmentInteractionListener mListener;

    public LocationFragment() {
        // Required empty public constructor
    }


    public static LocationFragment newInstance(String param1, String param2) {
        LocationFragment fragment = new LocationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_location, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) view.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
        Context context = getActivity().getApplicationContext();
        gpsTracker = new GPSTracker(context);
        try {
            location = gpsTracker.getLocation();
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        } catch (Exception e) {
            latitude = 51.109977;
            longitude = 17.057071;
        }


    }
    /*   public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/


  /*  @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng myPosition = new LatLng(latitude,longitude);
        mGoogleMap.addMarker(new MarkerOptions().position(myPosition).title("My Position"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition));


        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(myPosition, 17);
        mGoogleMap.animateCamera(cameraUpdate);


    }


 /*   public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }*/
}
