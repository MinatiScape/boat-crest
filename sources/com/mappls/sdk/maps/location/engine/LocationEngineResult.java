package com.mappls.sdk.maps.location.engine;

import android.content.Intent;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.location.LocationResult;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes11.dex */
public final class LocationEngineResult {

    /* renamed from: a  reason: collision with root package name */
    public final List<Location> f12756a;

    public LocationEngineResult(List<Location> list) {
        this.f12756a = Collections.unmodifiableList(list);
    }

    public static LocationEngineResult a(Intent intent) {
        if (c(intent)) {
            return create((Location) intent.getExtras().getParcelable(FirebaseAnalytics.Param.LOCATION));
        }
        return null;
    }

    public static LocationEngineResult b(Intent intent) {
        LocationResult extractResult = LocationResult.extractResult(intent);
        if (extractResult != null) {
            return create(extractResult.getLocations());
        }
        return null;
    }

    public static boolean c(Intent intent) {
        return intent != null && intent.hasExtra(FirebaseAnalytics.Param.LOCATION);
    }

    @NonNull
    public static LocationEngineResult create(@Nullable Location location) {
        ArrayList arrayList = new ArrayList();
        if (location != null) {
            arrayList.add(location);
        }
        return new LocationEngineResult(arrayList);
    }

    @Nullable
    public static LocationEngineResult extractResult(Intent intent) {
        LocationEngineResult b = e.c("com.google.android.gms.location.LocationResult") ? b(intent) : null;
        return b == null ? a(intent) : b;
    }

    @Nullable
    public Location getLastLocation() {
        if (this.f12756a.isEmpty()) {
            return null;
        }
        return this.f12756a.get(0);
    }

    public List<Location> getLocations() {
        return Collections.unmodifiableList(this.f12756a);
    }

    @NonNull
    public static LocationEngineResult create(@Nullable List<Location> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList(list);
            arrayList.removeAll(Collections.singleton(null));
            return new LocationEngineResult(arrayList);
        }
        return new LocationEngineResult(Collections.emptyList());
    }
}
