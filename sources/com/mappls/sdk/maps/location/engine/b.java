package com.mappls.sdk.maps.location.engine;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import java.util.Collections;
import java.util.List;
/* loaded from: classes11.dex */
public class b implements LocationEngineImpl<LocationCallback> {

    /* renamed from: a  reason: collision with root package name */
    public final FusedLocationProviderClient f12758a;

    @VisibleForTesting
    /* loaded from: classes11.dex */
    public static final class a implements OnSuccessListener<Location>, OnFailureListener {

        /* renamed from: a  reason: collision with root package name */
        public final LocationEngineCallback<LocationEngineResult> f12759a;

        public a(LocationEngineCallback<LocationEngineResult> locationEngineCallback) {
            this.f12759a = locationEngineCallback;
        }

        @Override // com.google.android.gms.tasks.OnSuccessListener
        /* renamed from: a */
        public void onSuccess(Location location) {
            this.f12759a.onSuccess(location != null ? LocationEngineResult.create(location) : LocationEngineResult.create(Collections.emptyList()));
        }

        @Override // com.google.android.gms.tasks.OnFailureListener
        public void onFailure(@NonNull Exception exc) {
            this.f12759a.onFailure(exc);
        }
    }

    /* renamed from: com.mappls.sdk.maps.location.engine.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static final class C0629b extends LocationCallback {

        /* renamed from: a  reason: collision with root package name */
        public final LocationEngineCallback<LocationEngineResult> f12760a;

        public C0629b(LocationEngineCallback<LocationEngineResult> locationEngineCallback) {
            this.f12760a = locationEngineCallback;
        }

        @Override // com.google.android.gms.location.LocationCallback
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            List<Location> locations = locationResult.getLocations();
            if (!locations.isEmpty()) {
                this.f12760a.onSuccess(LocationEngineResult.create(locations));
            } else {
                this.f12760a.onFailure(new Exception("Unavailable location"));
            }
        }
    }

    public b(@NonNull Context context) {
        this.f12758a = LocationServices.getFusedLocationProviderClient(context);
    }

    public static int d(int i) {
        if (i != 0) {
            if (i != 1) {
                return i != 2 ? 105 : 104;
            }
            return 102;
        }
        return 100;
    }

    public static LocationRequest e(LocationEngineRequest locationEngineRequest) {
        LocationRequest.Builder builder = new LocationRequest.Builder(locationEngineRequest.getInterval());
        builder.setIntervalMillis(locationEngineRequest.getInterval());
        builder.setMinUpdateIntervalMillis(locationEngineRequest.getFastestInterval());
        builder.setMaxUpdateDelayMillis(locationEngineRequest.getMaxWaitTime());
        builder.setMinUpdateDistanceMeters(locationEngineRequest.getDisplacement());
        builder.setPriority(d(locationEngineRequest.getPriority()));
        return builder.build();
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineImpl
    @NonNull
    /* renamed from: a */
    public LocationCallback createListener(LocationEngineCallback<LocationEngineResult> locationEngineCallback) {
        return new C0629b(locationEngineCallback);
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineImpl
    /* renamed from: b */
    public void removeLocationUpdates(@NonNull LocationCallback locationCallback) {
        if (locationCallback != null) {
            this.f12758a.removeLocationUpdates(locationCallback);
        }
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineImpl
    @SuppressLint({"MissingPermission"})
    /* renamed from: c */
    public void requestLocationUpdates(@NonNull LocationEngineRequest locationEngineRequest, @NonNull LocationCallback locationCallback, @Nullable Looper looper) throws SecurityException {
        this.f12758a.requestLocationUpdates(e(locationEngineRequest), locationCallback, looper);
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineImpl
    @SuppressLint({"MissingPermission"})
    public void getLastLocation(@NonNull LocationEngineCallback<LocationEngineResult> locationEngineCallback) throws SecurityException {
        a aVar = new a(locationEngineCallback);
        this.f12758a.getLastLocation().addOnSuccessListener(aVar).addOnFailureListener(aVar);
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineImpl
    public void removeLocationUpdates(PendingIntent pendingIntent) {
        if (pendingIntent != null) {
            this.f12758a.removeLocationUpdates(pendingIntent);
        }
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineImpl
    @SuppressLint({"MissingPermission"})
    public void requestLocationUpdates(@NonNull LocationEngineRequest locationEngineRequest, @NonNull PendingIntent pendingIntent) throws SecurityException {
        this.f12758a.requestLocationUpdates(e(locationEngineRequest), pendingIntent);
    }
}
