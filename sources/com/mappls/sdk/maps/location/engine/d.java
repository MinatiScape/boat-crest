package com.mappls.sdk.maps.location.engine;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import timber.log.Timber;
/* loaded from: classes11.dex */
public class d extends com.mappls.sdk.maps.location.engine.a {

    /* loaded from: classes11.dex */
    public static final class a implements LocationListener {
        public final LocationEngineCallback<LocationEngineResult> h;
        public Location i;

        public a(LocationEngineCallback<LocationEngineResult> locationEngineCallback) {
            this.h = locationEngineCallback;
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (e.b(location, this.i)) {
                this.i = location;
            }
            LocationEngineCallback<LocationEngineResult> locationEngineCallback = this.h;
            if (locationEngineCallback != null) {
                locationEngineCallback.onSuccess(LocationEngineResult.create(this.i));
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
            Timber.d("onProviderDisabled: %s", str);
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
            Timber.d("onProviderEnabled: %s", str);
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
            Timber.d("onStatusChanged: %s", str);
        }
    }

    public d(@NonNull Context context) {
        super(context);
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineImpl
    @SuppressLint({"MissingPermission"})
    /* renamed from: g */
    public void requestLocationUpdates(@NonNull LocationEngineRequest locationEngineRequest, @NonNull LocationListener locationListener, @Nullable Looper looper) throws SecurityException {
        super.g(locationEngineRequest, locationListener, looper);
        if (j(locationEngineRequest.getPriority())) {
            try {
                this.f12757a.requestLocationUpdates("network", locationEngineRequest.getInterval(), locationEngineRequest.getDisplacement(), locationListener, looper);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineImpl
    public void getLastLocation(@NonNull LocationEngineCallback<LocationEngineResult> locationEngineCallback) throws SecurityException {
        Location i = i();
        if (i != null) {
            locationEngineCallback.onSuccess(LocationEngineResult.create(i));
        } else {
            locationEngineCallback.onFailure(new Exception("Last location unavailable"));
        }
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineImpl
    @NonNull
    /* renamed from: h */
    public LocationListener createListener(LocationEngineCallback<LocationEngineResult> locationEngineCallback) {
        return new a(locationEngineCallback);
    }

    public final Location i() {
        Location location = null;
        for (String str : this.f12757a.getAllProviders()) {
            Location c = c(str);
            if (c != null && e.b(c, location)) {
                location = c;
            }
        }
        return location;
    }

    public final boolean j(int i) {
        return (i == 0 || i == 1) && this.b.equals("gps");
    }

    @Override // com.mappls.sdk.maps.location.engine.a, com.mappls.sdk.maps.location.engine.LocationEngineImpl
    @SuppressLint({"MissingPermission"})
    public void requestLocationUpdates(@NonNull LocationEngineRequest locationEngineRequest, @NonNull PendingIntent pendingIntent) throws SecurityException {
        super.requestLocationUpdates(locationEngineRequest, pendingIntent);
        if (j(locationEngineRequest.getPriority())) {
            try {
                this.f12757a.requestLocationUpdates("network", locationEngineRequest.getInterval(), locationEngineRequest.getDisplacement(), pendingIntent);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}
