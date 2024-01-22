package com.mappls.sdk.maps.location.engine;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.analytics.FirebaseAnalytics;
/* loaded from: classes11.dex */
public class a implements LocationEngineImpl<LocationListener> {

    /* renamed from: a  reason: collision with root package name */
    public final LocationManager f12757a;
    public String b = "passive";

    public a(@NonNull Context context) {
        this.f12757a = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
    }

    @VisibleForTesting
    public static Criteria b(int i) {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(d(i));
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(e(i));
        return criteria;
    }

    public static int d(int i) {
        return (i == 0 || i == 1) ? 1 : 2;
    }

    public static int e(int i) {
        if (i != 0) {
            return i != 1 ? 1 : 2;
        }
        return 3;
    }

    public final String a(int i) {
        String bestProvider = i != 3 ? this.f12757a.getBestProvider(b(i), true) : null;
        return bestProvider != null ? bestProvider : "passive";
    }

    @SuppressLint({"MissingPermission"})
    public Location c(String str) throws SecurityException {
        try {
            return this.f12757a.getLastKnownLocation(str);
        } catch (IllegalArgumentException e) {
            Log.e("AndroidLocationEngine", e.toString());
            return null;
        }
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineImpl
    @SuppressLint({"MissingPermission"})
    /* renamed from: f */
    public void removeLocationUpdates(@NonNull LocationListener locationListener) {
        if (locationListener != null) {
            this.f12757a.removeUpdates(locationListener);
        }
    }

    @SuppressLint({"MissingPermission"})
    public void g(@NonNull LocationEngineRequest locationEngineRequest, @NonNull LocationListener locationListener, @Nullable Looper looper) throws SecurityException {
        String a2 = a(locationEngineRequest.getPriority());
        this.b = a2;
        this.f12757a.requestLocationUpdates(a2, locationEngineRequest.getInterval(), locationEngineRequest.getDisplacement(), locationListener, looper);
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineImpl
    @SuppressLint({"MissingPermission"})
    public void requestLocationUpdates(@NonNull LocationEngineRequest locationEngineRequest, @NonNull PendingIntent pendingIntent) throws SecurityException {
        String a2 = a(locationEngineRequest.getPriority());
        this.b = a2;
        this.f12757a.requestLocationUpdates(a2, locationEngineRequest.getInterval(), locationEngineRequest.getDisplacement(), pendingIntent);
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineImpl
    public void removeLocationUpdates(PendingIntent pendingIntent) {
        if (pendingIntent != null) {
            this.f12757a.removeUpdates(pendingIntent);
        }
    }
}
