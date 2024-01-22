package com.mappls.sdk.navigation.location;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.LocationManager;
import androidx.core.content.ContextCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mappls.sdk.navigation.util.GPSInfo;
/* loaded from: classes11.dex */
public final class f implements d {

    /* renamed from: a  reason: collision with root package name */
    public GpsStatus.Listener f12917a;
    public LocationManager b;
    public a c;
    public final Context d;

    public f(Context context) {
        this.d = context;
    }

    public static void b(f fVar, GpsStatus gpsStatus) {
        int i;
        int i2;
        fVar.getClass();
        boolean z = false;
        if (gpsStatus != null) {
            i = 0;
            i2 = 0;
            for (GpsSatellite gpsSatellite : gpsStatus.getSatellites()) {
                i++;
                if (gpsSatellite.usedInFix()) {
                    i2++;
                    z = true;
                }
            }
        } else {
            i = 0;
            i2 = 0;
        }
        GPSInfo gPSInfo = new GPSInfo();
        gPSInfo.fixed = z;
        gPSInfo.foundSatellites = i;
        gPSInfo.usedSatellites = i2;
        a aVar = fVar.c;
        if (aVar != null) {
            aVar.a(gPSInfo);
        }
    }

    @Override // com.mappls.sdk.navigation.location.d
    public final void a() {
        if (this.b == null || ContextCompat.checkSelfPermission(this.d, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            return;
        }
        this.b.removeGpsStatusListener(this.f12917a);
    }

    @Override // com.mappls.sdk.navigation.location.d
    public final void a(a aVar) {
        this.c = aVar;
        this.b = (LocationManager) this.d.getSystemService(FirebaseAnalytics.Param.LOCATION);
        if (ContextCompat.checkSelfPermission(this.d, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            LocationManager locationManager = this.b;
            e eVar = new e(this, locationManager);
            this.f12917a = eVar;
            locationManager.addGpsStatusListener(eVar);
        }
    }
}
