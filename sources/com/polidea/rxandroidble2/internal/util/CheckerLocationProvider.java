package com.polidea.rxandroidble2.internal.util;

import android.content.ContentResolver;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.RequiresApi;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.internal.RxBleLog;
/* loaded from: classes12.dex */
public class CheckerLocationProvider {

    /* renamed from: a  reason: collision with root package name */
    public final ContentResolver f13505a;
    public final LocationManager b;

    @Inject
    public CheckerLocationProvider(ContentResolver contentResolver, LocationManager locationManager) {
        this.f13505a = contentResolver;
        this.b = locationManager;
    }

    public final boolean a() {
        return this.b.isProviderEnabled("network") || this.b.isProviderEnabled("gps");
    }

    @RequiresApi(19)
    public final boolean b() {
        try {
            return Settings.Secure.getInt(this.f13505a, "location_mode") != 0;
        } catch (Settings.SettingNotFoundException e) {
            RxBleLog.w(e, "Could not use LOCATION_MODE check. Falling back to a legacy/heuristic function.", new Object[0]);
            return a();
        }
    }

    public boolean isLocationProviderEnabled() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            return this.b.isLocationEnabled();
        }
        if (i >= 19) {
            return b();
        }
        return a();
    }
}
