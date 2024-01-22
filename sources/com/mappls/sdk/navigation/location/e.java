package com.mappls.sdk.navigation.location;

import android.content.Context;
import android.location.GpsStatus;
import android.location.LocationManager;
import androidx.core.content.ContextCompat;
/* loaded from: classes11.dex */
public final class e implements GpsStatus.Listener {

    /* renamed from: a  reason: collision with root package name */
    public GpsStatus f12916a;
    public final /* synthetic */ LocationManager b;
    public final /* synthetic */ f c;

    public e(f fVar, LocationManager locationManager) {
        this.c = fVar;
        this.b = locationManager;
    }

    @Override // android.location.GpsStatus.Listener
    public final void onGpsStatusChanged(int i) {
        Context context;
        context = this.c.d;
        if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            GpsStatus gpsStatus = this.b.getGpsStatus(this.f12916a);
            this.f12916a = gpsStatus;
            f.b(this.c, gpsStatus);
        }
    }
}
