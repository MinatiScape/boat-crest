package com.mappls.sdk.navigation.util;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.navigation.NavLocation;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.t;
import java.util.Date;
import java.util.TimeZone;
/* loaded from: classes11.dex */
public final class b implements SensorEventListener {

    /* renamed from: a  reason: collision with root package name */
    public final NavigationApplication f13043a;
    public t.j<t.k> b;
    public long c = 0;
    public boolean d = false;

    public b(NavigationApplication navigationApplication) {
        this.f13043a = navigationApplication;
        this.b = navigationApplication.k().p;
    }

    public final SunriseSunset a() {
        NavLocation lastKnownLocation = this.f13043a.getLocationProvider().getLastKnownLocation();
        if (lastKnownLocation == null) {
            lastKnownLocation = this.f13043a.getLocationProvider().getFirstTimeRunDefaultLocation();
        }
        if (lastKnownLocation == null) {
            return null;
        }
        double longitude = lastKnownLocation.getLongitude();
        Date date = new Date();
        double latitude = lastKnownLocation.getLatitude();
        if (longitude < 0.0d) {
            longitude += 360.0d;
        }
        return new SunriseSunset(latitude, longitude, date, TimeZone.getDefault());
    }

    public final boolean b() {
        t.k kVar = this.b.get();
        kVar.getClass();
        if (kVar == t.k.DAY) {
            return false;
        }
        if (kVar == t.k.NIGHT) {
            return true;
        }
        if (kVar == t.k.AUTO) {
            if (System.currentTimeMillis() - this.c > Constants.ONE_MIN_IN_MILLIS) {
                this.c = System.currentTimeMillis();
                try {
                    SunriseSunset a2 = a();
                    if (a2 != null) {
                        boolean isDaytime = a2.isDaytime();
                        NavigationLogger.d("Sunrise/sunset setting to day: " + isDaytime, new Object[0]);
                        this.d = isDaytime ^ true;
                    }
                } catch (IllegalArgumentException e) {
                    NavigationLogger.w(e, "Network location provider not available", new Object[0]);
                } catch (SecurityException e2) {
                    NavigationLogger.w(e2, "Missing permissions to get actual location!", new Object[0]);
                }
            }
            return this.d;
        }
        if (kVar == t.k.SENSOR) {
            return this.d;
        }
        return false;
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr = sensorEvent.values;
        if (fArr.length > 0) {
            boolean z = fArr[0] <= 100.0f;
            if (z == this.d || System.currentTimeMillis() - this.c <= 10000) {
                return;
            }
            this.c = System.currentTimeMillis();
            this.d = z;
            throw null;
        }
    }
}
