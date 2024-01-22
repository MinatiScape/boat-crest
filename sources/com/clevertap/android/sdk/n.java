package com.clevertap.android.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import com.clevertap.android.sdk.events.BaseEventQueueManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.concurrent.Future;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class n extends d {

    /* renamed from: a  reason: collision with root package name */
    public int f2648a = 0;
    public int b = 0;
    public final BaseEventQueueManager c;
    public final CleverTapInstanceConfig d;
    public final Context e;
    public final CoreMetaData f;
    public final Logger g;

    public n(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, CoreMetaData coreMetaData, BaseEventQueueManager baseEventQueueManager) {
        this.e = context;
        this.d = cleverTapInstanceConfig;
        this.g = cleverTapInstanceConfig.getLogger();
        this.f = coreMetaData;
        this.c = baseEventQueueManager;
    }

    @Override // com.clevertap.android.sdk.d
    @SuppressLint({"MissingPermission"})
    public Location a() {
        try {
            LocationManager locationManager = (LocationManager) this.e.getSystemService(FirebaseAnalytics.Param.LOCATION);
            if (locationManager == null) {
                Logger.d("Location Manager is null.");
                return null;
            }
            Location location = null;
            Location location2 = null;
            for (String str : locationManager.getProviders(true)) {
                try {
                    location2 = locationManager.getLastKnownLocation(str);
                } catch (SecurityException e) {
                    Logger.v("Location security exception", e);
                }
                if (location2 != null && (location == null || location2.getAccuracy() < location.getAccuracy())) {
                    location = location2;
                }
            }
            return location;
        } catch (Throwable th) {
            Logger.v("Couldn't get user's location", th);
            return null;
        }
    }

    @Override // com.clevertap.android.sdk.d
    public Future<?> b(Location location) {
        if (location == null) {
            return null;
        }
        this.f.setLocationFromUser(location);
        Logger logger = this.g;
        String accountId = this.d.getAccountId();
        logger.verbose(accountId, "Location updated (" + location.getLatitude() + ", " + location.getLongitude() + ")");
        if (this.f.isLocationForGeofence() || CleverTapAPI.isAppForeground()) {
            int c = c();
            if (this.f.isLocationForGeofence() && c > this.b + 10) {
                Future<?> queueEvent = this.c.queueEvent(this.e, new JSONObject(), 2);
                e(c);
                Logger logger2 = this.g;
                String accountId2 = this.d.getAccountId();
                logger2.verbose(accountId2, "Queuing location ping event for geofence location (" + location.getLatitude() + ", " + location.getLongitude() + ")");
                return queueEvent;
            } else if (this.f.isLocationForGeofence() || c <= this.f2648a + 10) {
                return null;
            } else {
                Future<?> queueEvent2 = this.c.queueEvent(this.e, new JSONObject(), 2);
                d(c);
                Logger logger3 = this.g;
                String accountId3 = this.d.getAccountId();
                logger3.verbose(accountId3, "Queuing location ping event for location (" + location.getLatitude() + ", " + location.getLongitude() + ")");
                return queueEvent2;
            }
        }
        return null;
    }

    public int c() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public void d(int i) {
        this.f2648a = i;
    }

    public void e(int i) {
        this.b = i;
    }
}
