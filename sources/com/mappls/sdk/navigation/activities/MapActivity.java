package com.mappls.sdk.navigation.activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.OnMapReadyCallback;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.R;
import com.mappls.sdk.navigation.apis.NavigationLogger;
/* loaded from: classes11.dex */
public abstract class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    public MapView mapView;

    public final void c(@Nullable Bundle bundle) {
        MapView mapView = (MapView) findViewById(R.id.vector_map_view);
        this.mapView = mapView;
        mapView.onCreate(bundle);
        this.mapView.getMapAsync(this);
    }

    public final void d() {
        getWindow().setSoftInputMode(3);
    }

    public MapView getMapView() {
        return this.mapView;
    }

    public NavigationApplication getMyApplication() {
        return (NavigationApplication) getApplication();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        try {
            setContentView(R.layout.activity_map);
            c(bundle);
            getWindow().addFlags(128);
            d();
            getMyApplication().a(this);
        } catch (Exception e) {
            NavigationLogger.d(e);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mapView.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.mapView.onLowMemory();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.mapView.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mapView.onResume();
        if (getMyApplication().f() == null || !getMyApplication().f().f() || !MapplsNavigationHelper.getInstance().isNavigating()) {
            getMyApplication().stopNavigation();
            getMyApplication().getNotificationHelper().removeNotifications();
        }
        getMyApplication().getNotificationHelper().refreshNotifications();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mapView.onSaveInstanceState(bundle);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.mapView.onStart();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.mapView.onStop();
        getMyApplication().getNotificationHelper().removeNotifications();
    }
}
