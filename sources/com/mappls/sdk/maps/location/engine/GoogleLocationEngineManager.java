package com.mappls.sdk.maps.location.engine;

import com.google.android.gms.location.LocationCallback;
/* loaded from: classes11.dex */
public class GoogleLocationEngineManager {
    public static final GoogleLocationEngineManager b = new GoogleLocationEngineManager();

    /* renamed from: a  reason: collision with root package name */
    public LocationEngineImpl<LocationCallback> f12753a;

    public static GoogleLocationEngineManager getInstance() {
        return b;
    }

    public LocationEngineImpl<LocationCallback> a() {
        return this.f12753a;
    }

    public void setLocationEngine(LocationEngineImpl<LocationCallback> locationEngineImpl) {
        this.f12753a = locationEngineImpl;
    }
}
