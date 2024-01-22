package com.mappls.sdk.navigation;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mappls.sdk.maps.location.engine.LocationEngine;
import com.mappls.sdk.maps.location.engine.LocationEngineCallback;
import com.mappls.sdk.maps.location.engine.LocationEngineProvider;
import com.mappls.sdk.maps.location.engine.LocationEngineRequest;
import com.mappls.sdk.maps.location.engine.LocationEngineResult;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.util.GPSInfo;
/* loaded from: classes11.dex */
public class NavigationService extends Service implements LocationListener {
    public static final /* synthetic */ int p = 0;
    public static PowerManager.WakeLock v;
    public int i;
    public String j;
    public int k;
    public Handler l;
    public PendingIntent m;
    public NavigationLocationProvider n;
    public Handler q;
    public LocationEngine r;
    public com.mappls.sdk.navigation.location.d t;

    /* renamed from: a  reason: collision with root package name */
    public int f12872a = 0;
    public c h = new c();
    public GPSInfo o = new GPSInfo();
    public com.mappls.sdk.navigation.location.a s = new com.mappls.sdk.navigation.location.a() { // from class: com.mappls.sdk.navigation.a
        @Override // com.mappls.sdk.navigation.location.a
        public final void a(GPSInfo gPSInfo) {
            NavigationService.this.c(gPSInfo);
        }
    };
    public LocationEngineCallback<LocationEngineResult> u = new b();

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public final /* synthetic */ NavigationApplication h;

        public a(NavigationApplication navigationApplication) {
            this.h = navigationApplication;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.h.getNotificationHelper().refreshNotifications();
        }
    }

    /* loaded from: classes11.dex */
    public class b implements LocationEngineCallback<LocationEngineResult> {
        public b() {
        }

        @Override // com.mappls.sdk.maps.location.engine.LocationEngineCallback
        public final void onFailure(@NonNull Exception exc) {
        }

        @Override // com.mappls.sdk.maps.location.engine.LocationEngineCallback
        public final void onSuccess(LocationEngineResult locationEngineResult) {
            LocationEngineResult locationEngineResult2 = locationEngineResult;
            if ((NavigationService.this.o == null || !NavigationService.this.o.fixed) && MapplsNavigationHelper.getInstance().isGPSCheckEnableForLocationChange()) {
                return;
            }
            NavigationService.this.onLocationChanged(locationEngineResult2.getLastLocation());
        }
    }

    /* loaded from: classes11.dex */
    public static class c extends Binder {
    }

    @SuppressLint({"InvalidWakeLockTag"})
    public static synchronized PowerManager.WakeLock a(Context context) {
        PowerManager.WakeLock wakeLock;
        synchronized (NavigationService.class) {
            if (v == null) {
                v = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "NavigationService");
            }
            wakeLock = v;
        }
        return wakeLock;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(GPSInfo gPSInfo) {
        this.o = gPSInfo;
        NavigationLocationProvider navigationLocationProvider = this.n;
        if (navigationLocationProvider != null) {
            navigationLocationProvider.setSatelliteInfo(gPSInfo);
        }
    }

    public final Handler a() {
        return this.l;
    }

    public final void a(NavigationApplication navigationApplication, int i) {
        int i2 = this.f12872a;
        if ((i2 & i) > 0) {
            this.f12872a = i2 - i;
        }
        if (this.f12872a == 0) {
            NavigationLogger.d("stopIfNeeded", new Object[0]);
            navigationApplication.stopService(new Intent(navigationApplication, NavigationService.class));
            return;
        }
        NotificationHelper notificationHelper = ((NavigationApplication) getApplication()).getNotificationHelper();
        notificationHelper.updateTopNotification();
        notificationHelper.refreshNotifications();
    }

    public final int b() {
        return this.k;
    }

    public final int c() {
        return this.i;
    }

    public final String d() {
        return this.j;
    }

    public final int e() {
        return this.f12872a;
    }

    public final boolean f() {
        return this.f12872a != 0;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.h;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        NavigationApplication navigationApplication = (NavigationApplication) getApplication();
        HandlerThread handlerThread = new HandlerThread("NavigationService");
        handlerThread.start();
        this.q = new Handler(handlerThread.getLooper());
        NavigationLogger.d("onCreate", new Object[0]);
        a(this).acquire();
        this.r = LocationEngineProvider.getBestLocationEngine(this);
        this.t = com.mappls.sdk.navigation.location.b.a(this);
        LocationEngineRequest build = new LocationEngineRequest.Builder(0L).setFastestInterval(0L).setPriority(0).build();
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.r.requestLocationUpdates(build, this.u, Looper.getMainLooper());
            this.t.a(this.s);
        }
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        NavigationLogger.d("onDestroy", new Object[0]);
        NavigationApplication navigationApplication = (NavigationApplication) getApplication();
        navigationApplication.n = null;
        this.f12872a = 0;
        LocationManager locationManager = (LocationManager) getSystemService(FirebaseAnalytics.Param.LOCATION);
        try {
            this.t.a();
        } catch (SecurityException unused) {
            NavigationLogger.d("Location SERVICE permission not granted", new Object[0]);
        }
        PowerManager.WakeLock a2 = a(this);
        if (a2.isHeld()) {
            a2.release();
        }
        if (this.m != null) {
            ((AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(this.m);
        }
        stopForeground(true);
        navigationApplication.getNotificationHelper().updateTopNotification();
        navigationApplication.l.postDelayed(new a(navigationApplication), 500L);
        v = null;
        this.s = null;
        this.h = null;
        this.j = null;
        Handler handler = this.l;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.l = null;
        }
        LocationEngine locationEngine = this.r;
        if (locationEngine != null) {
            locationEngine.removeLocationUpdates(this.u);
            this.r = null;
            this.u = null;
        }
        this.m = null;
        this.n = null;
        this.o = null;
        Handler handler2 = this.q;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
            this.q = null;
        }
    }

    @Override // android.location.LocationListener
    public final void onLocationChanged(Location location) {
        NavigationLogger.d("onLocationChanged", new Object[0]);
        if (location != null) {
            NavigationApplication navigationApplication = (NavigationApplication) getApplication();
            navigationApplication.setCurrentLocation(location);
            NavLocation convertLocation = NavigationLocationProvider.convertLocation(location, navigationApplication);
            if (!(this.i == 0)) {
                LocationEngine locationEngine = this.r;
                if (locationEngine != null) {
                    locationEngine.removeLocationUpdates(this.u);
                }
                NavigationLogger.d("Its one time location request releasing wake lock", new Object[0]);
                PowerManager.WakeLock a2 = a(this);
                if (a2.isHeld()) {
                    a2.release();
                }
            }
            NavigationLocationProvider navigationLocationProvider = this.n;
            if (navigationLocationProvider != null) {
                navigationLocationProvider.u(location, convertLocation, this.i == 0);
            }
        }
    }

    @Override // android.location.LocationListener
    public final void onProviderDisabled(String str) {
    }

    @Override // android.location.LocationListener
    public final void onProviderEnabled(String str) {
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        NavigationLogger.d("onStartCommand", new Object[0]);
        this.l = new Handler();
        NavigationApplication navigationApplication = (NavigationApplication) getApplication();
        navigationApplication.k();
        this.f12872a = intent.getIntExtra("SERVICE_USED_BY", 0);
        this.i = intent.getIntExtra("SERVICE_OFF_INTERVAL", 0);
        if ((this.f12872a & 1) != 0) {
            this.i = 0;
        }
        this.j = "gps";
        int i3 = this.i / 5;
        this.k = i3;
        int min = Math.min(i3, 720000);
        this.k = min;
        int max = Math.max(min, 30000);
        this.k = max;
        this.k = Math.min(max, this.i);
        this.n = navigationApplication.getLocationProvider();
        navigationApplication.n = this;
        if (this.i == 0) {
            LocationManager locationManager = (LocationManager) getSystemService(FirebaseAnalytics.Param.LOCATION);
        } else {
            AlarmManager alarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
            this.m = PendingIntent.getBroadcast(this, 0, new Intent(this, OnNavigationServiceAlarmReceiver.class), Build.VERSION.SDK_INT >= 31 ? 33554432 : 134217728);
            alarmManager.setRepeating(2, SystemClock.elapsedRealtime() + 500, this.i, this.m);
        }
        Notification buildTopNotification = navigationApplication.getNotificationHelper().buildTopNotification();
        if (buildTopNotification != null) {
            startForeground(100, buildTopNotification);
            navigationApplication.getNotificationHelper().refreshNotifications();
            return 3;
        }
        return 3;
    }

    @Override // android.location.LocationListener
    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }

    @Override // android.app.Service
    public final void onTaskRemoved(Intent intent) {
        NavigationApplication navigationApplication = (NavigationApplication) getApplication();
        if (MapplsNavigationHelper.getInstance().isCloseServiceOnRemovingTask()) {
            navigationApplication.getNotificationHelper().removeNotifications();
            if (navigationApplication.n != null) {
                NavigationLogger.d("onTaskRemoved", new Object[0]);
                stopSelf();
            }
        }
    }
}
