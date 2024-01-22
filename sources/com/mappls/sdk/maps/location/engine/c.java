package com.mappls.sdk.maps.location.engine;

import android.app.PendingIntent;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes11.dex */
public class c<T> implements LocationEngine {

    /* renamed from: a  reason: collision with root package name */
    public final LocationEngineImpl<T> f12761a;
    public Map<LocationEngineCallback<LocationEngineResult>, T> b;

    public c(LocationEngineImpl<T> locationEngineImpl) {
        this.f12761a = locationEngineImpl;
    }

    @VisibleForTesting
    public T a(@NonNull LocationEngineCallback<LocationEngineResult> locationEngineCallback) {
        if (this.b == null) {
            this.b = new ConcurrentHashMap();
        }
        T t = this.b.get(locationEngineCallback);
        if (t == null) {
            t = this.f12761a.createListener(locationEngineCallback);
        }
        this.b.put(locationEngineCallback, t);
        return t;
    }

    @VisibleForTesting
    public T b(@NonNull LocationEngineCallback<LocationEngineResult> locationEngineCallback) {
        Map<LocationEngineCallback<LocationEngineResult>, T> map = this.b;
        if (map != null) {
            return map.remove(locationEngineCallback);
        }
        return null;
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngine
    public void getLastLocation(@NonNull LocationEngineCallback<LocationEngineResult> locationEngineCallback) throws SecurityException {
        e.a(locationEngineCallback, "callback == null");
        this.f12761a.getLastLocation(locationEngineCallback);
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngine
    public void removeLocationUpdates(@NonNull LocationEngineCallback<LocationEngineResult> locationEngineCallback) {
        e.a(locationEngineCallback, "callback == null");
        this.f12761a.removeLocationUpdates((LocationEngineImpl<T>) b(locationEngineCallback));
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngine
    public void requestLocationUpdates(@NonNull LocationEngineRequest locationEngineRequest, @NonNull LocationEngineCallback<LocationEngineResult> locationEngineCallback, @Nullable Looper looper) throws SecurityException {
        e.a(locationEngineRequest, "request == null");
        e.a(locationEngineCallback, "callback == null");
        LocationEngineImpl<T> locationEngineImpl = this.f12761a;
        T a2 = a(locationEngineCallback);
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        locationEngineImpl.requestLocationUpdates(locationEngineRequest, a2, looper);
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngine
    public void removeLocationUpdates(PendingIntent pendingIntent) {
        this.f12761a.removeLocationUpdates(pendingIntent);
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngine
    public void requestLocationUpdates(@NonNull LocationEngineRequest locationEngineRequest, PendingIntent pendingIntent) throws SecurityException {
        e.a(locationEngineRequest, "request == null");
        this.f12761a.requestLocationUpdates(locationEngineRequest, pendingIntent);
    }
}
