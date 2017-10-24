package com.example.dillichalise.jt.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dillichalise.jt.DatabaseHelperForm;
import com.example.dillichalise.jt.DetailsActivity;
import com.example.dillichalise.jt.Form;
import com.example.dillichalise.jt.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends Fragment {
    private static final String FORM_ID = "FORM_ID";
    private GoogleMap googleMap;
        MapView mMapView;

      DatabaseHelperForm myDB;

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_maps, container, false);
            myDB= new DatabaseHelperForm(getActivity());
            mMapView = (MapView) rootView.findViewById(R.id.mapView);
            mMapView.onCreate(savedInstanceState);

            mMapView.onResume(); // needed to get the map to display immediately

            try {
                MapsInitializer.initialize(getActivity().getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }

            mMapView.getMapAsync(new OnMapReadyCallback() {

                public void onMapReady(GoogleMap mMap)  {
                    googleMap = mMap;

                    // For showing a move to my location button
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                      return;
                    }

                    googleMap.setMyLocationEnabled(true);

                   List<Form> data_all = myDB.getFormContents();
//                    Log.e("data",data_all+"size");
//                   List<String> location = myDB.getFormLoc();
//                    Log.e("location",location.size()+"size");
                   // LatLng p = getLocationFromAddress(null,location1);
             for (final Form form:data_all) {

                 try {

                     Geocoder gc = new Geocoder(getActivity());
                     List<Address> list = gc.getFromLocationName(form.getLocation(), 1);
                     Address add = list.get(0);

                     double lat = add.getLatitude();
                     double lng = add.getLongitude();



//                     For dropping a marker at a point on the Map
                     LatLng ktm = new LatLng(lat, lng);
                     Marker marker = googleMap.addMarker(new MarkerOptions().position(ktm).title(form.getJobtitle()).snippet(form.getCompany()));
                     marker.setTag(form.getId());
//                     marker.setIcon();
//                     googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//                            @Override
//                            public boolean onMarkerClick(Marker marker) {
//                                Intent intent=new Intent(getActivity(),DetailsActivity.class);
//                                intent.putExtra(FORM_ID,marker.getTag().toString());
//                                intent.putExtra(FORM_ID,form.getJobtitle());
//                                intent.putExtra(FORM_ID,marker.getTag().toString());


//                                startActivity(intent);
//                                return false;
//                            }
//                        });
                     // For zooming automatically to the location of the marker
                     CameraPosition cameraPosition = new CameraPosition.Builder().target(ktm).zoom(12).build();
                     googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


                 } catch (Exception e) {
                     e.printStackTrace();

                 }
             }
                    // For dropping a marker at a point on the Map
                    //LatLng ktm = new LatLng(27.6931, 85.2807);
                    //googleMap.addMarker(new MarkerOptions().position(ktm).title("Works").snippet(""));

                    // For zooming automatically to the location of the marker
                    //CameraPosition cameraPosition = new CameraPosition.Builder().target(ktm).zoom(12).build();
                    //googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));





                    // For dropping a marker at a point on the Map
                    //LatLng kalanki = new LatLng(27.6931, 85.2807);
                    //googleMap.addMarker(new MarkerOptions().position(kalanki).title("C++ Developer").snippet("LIS Pvt. Ltd."));

                    //LatLng kalimati = new LatLng(27.7000, 85.2891);
                    //googleMap.addMarker(new MarkerOptions().position(kalimati).title("Home Tuition").snippet("Home"));

                    //LatLng kirtipur = new LatLng(27.6630, 85.2774);
                   // googleMap.addMarker(new MarkerOptions().position(kirtipur).title("Salesperson").snippet("XYZ"));

                   // LatLng Baneshwor = new LatLng(27.6915, 85.3420);
                    //googleMap.addMarker(new MarkerOptions().position(Baneshwor).title("Housemaid").snippet("ABC"));


                }
            });

            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();
            mMapView.onResume();
        }

        @Override
        public void onPause() {
            super.onPause();
            mMapView.onPause();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mMapView.onDestroy();
        }

        @Override
        public void onLowMemory() {
            super.onLowMemory();
            mMapView.onLowMemory();
        }
}