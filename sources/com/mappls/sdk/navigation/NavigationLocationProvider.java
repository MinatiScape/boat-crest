package com.mappls.sdk.navigation;

import android.content.Context;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import androidx.core.content.ContextCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mappls.android.lms.MapplsLMSManager;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.iface.LocationChangedListener;
import com.mappls.sdk.navigation.t;
import com.mappls.sdk.navigation.util.GPSInfo;
import com.mappls.sdk.services.api.MapplsApiConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/* loaded from: classes11.dex */
public class NavigationLocationProvider implements SensorEventListener {
    public static final int REQUEST_LOCATION_PERMISSION = 100;
    public GpsStatus.Listener B;
    public t.InterfaceC0646t<Boolean> D;
    public t.InterfaceC0646t<Boolean> E;
    public LocationChangedListener f;
    public boolean h;
    public int s;
    public NavigationApplication t;
    public t u;
    public q v;

    /* renamed from: a  reason: collision with root package name */
    public float f12870a = 0.0f;
    public float b = 0.0f;
    public float c = 0.0f;
    public float d = 0.0f;
    public List<WeakReference<LocationChangedListener>> e = new LinkedList();
    public long g = 0;
    public boolean i = false;
    public float[] j = new float[3];
    public float[] k = new float[3];
    public float l = 360.0f;
    public float[] m = new float[50];
    public float[] n = new float[50];
    public int o = 0;
    public int p = 0;
    public boolean q = false;
    public Float r = null;
    public Location w = null;
    public NavLocation x = null;
    public GPSInfo y = new GPSInfo();
    public List<e> z = new ArrayList();
    public List<d> A = new ArrayList();
    public float[] C = new float[9];
    public LocationListener F = new a();
    public LinkedList<LocationListener> G = new LinkedList<>();

    /* loaded from: classes11.dex */
    public class a implements LocationListener {
        public a() {
        }

        @Override // android.location.LocationListener
        public final void onLocationChanged(Location location) {
            if (location != null) {
                NavigationLocationProvider.this.g = location.getTime();
            }
            if (NavigationLocationProvider.this.v.a()) {
                return;
            }
            NavigationLocationProvider navigationLocationProvider = NavigationLocationProvider.this;
            navigationLocationProvider.t(NavigationLocationProvider.convertLocation(location, navigationLocationProvider.t));
        }

        @Override // android.location.LocationListener
        public final void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    /* loaded from: classes11.dex */
    public class b implements GpsStatus.Listener {

        /* renamed from: a  reason: collision with root package name */
        public GpsStatus f12871a;
        public final /* synthetic */ LocationManager b;

        public b(LocationManager locationManager) {
            this.b = locationManager;
        }

        @Override // android.location.GpsStatus.Listener
        public final void onGpsStatusChanged(int i) {
            GpsStatus gpsStatus = this.b.getGpsStatus(this.f12871a);
            this.f12871a = gpsStatus;
            NavigationLocationProvider.this.x(gpsStatus);
            NavigationLocationProvider navigationLocationProvider = NavigationLocationProvider.this;
            navigationLocationProvider.y(navigationLocationProvider.x);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Runnable {
        public final /* synthetic */ long h;
        public final /* synthetic */ com.mappls.sdk.navigation.routing.d i;

        public c(long j, com.mappls.sdk.navigation.routing.d dVar) {
            this.h = j;
            this.i = dVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            NavLocation lastKnownLocation = NavigationLocationProvider.this.getLastKnownLocation();
            if (lastKnownLocation == null || lastKnownLocation.getTime() <= this.h) {
                NavigationLocationProvider.this.h = true;
                if (this.i.q() && this.i.h() > 0) {
                    if (NavigationLocationProvider.this.f != null) {
                        NavigationLocationProvider.this.f.onGPSConnectionChanged(false);
                    }
                    if (NavigationLocationProvider.this.e != null) {
                        Iterator it = NavigationLocationProvider.this.e.iterator();
                        while (it.hasNext()) {
                            LocationChangedListener locationChangedListener = (LocationChangedListener) ((WeakReference) it.next()).get();
                            if (locationChangedListener == null) {
                                it.remove();
                            } else {
                                locationChangedListener.onGPSConnectionChanged(false);
                            }
                        }
                    }
                }
                NavigationLocationProvider.this.t(null);
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface d {
        void a();
    }

    /* loaded from: classes11.dex */
    public interface e {
        void a();
    }

    /* loaded from: classes11.dex */
    public class f implements LocationListener {
        public f() {
        }

        public /* synthetic */ f(NavigationLocationProvider navigationLocationProvider, int i) {
            this();
        }

        @Override // android.location.LocationListener
        public final void onLocationChanged(Location location) {
            if (NavigationLocationProvider.this.A() || NavigationLocationProvider.this.v.a()) {
                return;
            }
            NavigationLocationProvider navigationLocationProvider = NavigationLocationProvider.this;
            navigationLocationProvider.t(NavigationLocationProvider.convertLocation(location, navigationLocationProvider.t));
        }

        @Override // android.location.LocationListener
        public final void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    public NavigationLocationProvider(NavigationApplication navigationApplication) {
        this.t = navigationApplication;
        t k = navigationApplication.k();
        this.u = k;
        this.D = k.m;
        this.E = k.n;
        this.v = new q(navigationApplication, this);
    }

    public static NavLocation convertLocation(Location location, NavigationApplication navigationApplication) {
        if (location == null) {
            return null;
        }
        NavLocation navLocation = new NavLocation(location.getProvider());
        navLocation.setLatitude(location.getLatitude());
        navLocation.setLongitude(location.getLongitude());
        navLocation.setTime(location.getTime());
        if (location.hasAccuracy()) {
            navLocation.setAccuracy(location.getAccuracy());
        }
        if (location.hasSpeed()) {
            navLocation.setSpeed(location.getSpeed());
        }
        if (location.hasAltitude()) {
            navLocation.setAltitude(location.getAltitude());
        }
        if (location.hasBearing()) {
            navLocation.setBearing(location.getBearing());
        }
        return navLocation;
    }

    public static boolean isLocationPermissionAvailable(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    public static boolean isNotSimulatedLocation(NavLocation navLocation) {
        if (navLocation != null) {
            return !"mapmyindia".equals(navLocation.getProvider());
        }
        return true;
    }

    public static boolean isPointAccurateForRouting(NavLocation navLocation) {
        return navLocation != null && (!navLocation.hasAccuracy() || navLocation.getAccuracy() < 25.0f);
    }

    public static Location revertLocation(NavLocation navLocation, NavigationApplication navigationApplication) {
        if (navLocation == null) {
            return null;
        }
        Location location = new Location(navLocation.getProvider());
        location.setLatitude(navLocation.getLatitude());
        location.setLongitude(navLocation.getLongitude());
        location.setTime(navLocation.getTime());
        if (navLocation.hasAccuracy()) {
            location.setAccuracy(navLocation.getAccuracy());
        }
        if (navLocation.hasSpeed()) {
            location.setSpeed(navLocation.getSpeed());
        }
        if (navLocation.hasAltitude()) {
            location.setAltitude(navLocation.getAltitude());
        }
        if (navLocation.hasBearing()) {
            location.setBearing(navLocation.getBearing());
        }
        return location;
    }

    public final boolean A() {
        if (!this.t.q.q() && System.currentTimeMillis() - this.g >= 12000) {
            return r();
        }
        return true;
    }

    public void addCompassListener(d dVar) {
        if (this.A.contains(dVar)) {
            return;
        }
        this.A.add(dVar);
    }

    public void addLocationChangeListener(LocationChangedListener locationChangedListener) {
        this.e.add(new WeakReference<>(locationChangedListener));
    }

    public void addLocationListener(e eVar) {
        if (this.z.contains(eVar)) {
            return;
        }
        this.z.add(eVar);
    }

    public boolean checkGPSEnabled(Context context) {
        boolean z;
        boolean z2;
        LocationManager locationManager = (LocationManager) this.t.getSystemService(FirebaseAnalytics.Param.LOCATION);
        try {
            z = locationManager.isProviderEnabled("gps");
        } catch (Exception e2) {
            NavigationLogger.e(e2);
            z = false;
        }
        try {
            z2 = locationManager.isProviderEnabled("network");
        } catch (Exception e3) {
            NavigationLogger.e(e3);
            z2 = false;
        }
        return z || z2;
    }

    public void checkIfLastKnownLocationIsValid() {
        NavLocation lastKnownLocation = getLastKnownLocation();
        if (lastKnownLocation == null || System.currentTimeMillis() - lastKnownLocation.getTime() <= 30000) {
            return;
        }
        t(null);
    }

    public NavLocation getFirstTimeRunDefaultLocation() {
        LocationManager locationManager;
        List<String> providers;
        if (isLocationPermissionAvailable(this.t) && (providers = (locationManager = (LocationManager) this.t.getSystemService(FirebaseAnalytics.Param.LOCATION)).getProviders(true)) != null) {
            ArrayList arrayList = new ArrayList(providers);
            int indexOf = arrayList.indexOf("passive");
            if (indexOf > -1) {
                arrayList.add(0, (String) arrayList.remove(indexOf));
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                NavLocation convertLocation = convertLocation(locationManager.getLastKnownLocation((String) it.next()), this.t);
                if (convertLocation != null) {
                    return convertLocation;
                }
            }
            return null;
        }
        return null;
    }

    public GPSInfo getGPSInfo() {
        return this.y;
    }

    public synchronized Float getHeading() {
        return this.r;
    }

    public NavLocation getLastKnownLocation() {
        return this.x;
    }

    public Location getLastLocation() {
        return this.w;
    }

    public q getLocationSimulation() {
        return this.v;
    }

    public final float l(float f2) {
        if (this.l == 360.0f && getLastKnownLocation() != null) {
            NavLocation lastKnownLocation = getLastKnownLocation();
            this.l = new GeomagneticField((float) lastKnownLocation.getLatitude(), (float) lastKnownLocation.getLongitude(), (float) lastKnownLocation.getAltitude(), System.currentTimeMillis()).getDeclination();
        }
        float f3 = this.l;
        return f3 != 360.0f ? f2 + f3 : f2;
    }

    public final float m(float f2) {
        int i = this.s;
        return i == 1 ? f2 + 90.0f : i == 2 ? f2 + 180.0f : i == 3 ? f2 - 90.0f : f2;
    }

    public final void n(NavLocation navLocation) {
        if (navLocation == null || !r()) {
            return;
        }
        z(navLocation);
    }

    public final void o() {
        if (this.r == null && this.o == 0) {
            Arrays.fill(this.m, this.c);
            Arrays.fill(this.n, this.d);
            this.f12870a = this.c;
            this.b = this.d;
            return;
        }
        float[] fArr = this.m;
        int length = fArr.length;
        int i = (this.o + 1) % length;
        this.o = i;
        int i2 = (this.p + 1) % length;
        this.p = i2;
        float f2 = this.f12870a;
        float f3 = this.c;
        float f4 = length;
        this.f12870a = (((-fArr[i]) + f3) / f4) + f2;
        fArr[i] = f3;
        float f5 = this.b;
        float[] fArr2 = this.n;
        float f6 = this.d;
        this.b = (((-fArr2[i2]) + f6) / f4) + f5;
        fArr2[i2] = f6;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr;
        if (this.q) {
            return;
        }
        synchronized (this) {
            if (this.i) {
                this.q = true;
                float f2 = 0.0f;
                int type = sensorEvent.sensor.getType();
                if (type == 1) {
                    System.arraycopy(sensorEvent.values, 0, this.j, 0, 3);
                } else if (type == 2) {
                    System.arraycopy(sensorEvent.values, 0, this.k, 0, 3);
                } else if (type != 3) {
                    this.q = false;
                    return;
                } else {
                    f2 = sensorEvent.values[0];
                }
                if (this.D.get().booleanValue()) {
                    float[] fArr2 = this.j;
                    if (fArr2 == null || (fArr = this.k) == null) {
                        this.q = false;
                        return;
                    } else if (!SensorManager.getRotationMatrix(this.C, null, fArr2, fArr)) {
                        this.q = false;
                        return;
                    } else {
                        f2 = (float) Math.toDegrees(SensorManager.getOrientation(this.C, new float[3])[0]);
                    }
                }
                double l = (float) ((l(m(f2)) / 180.0f) * 3.141592653589793d);
                this.c = (float) Math.sin(l);
                this.d = (float) Math.cos(l);
                if (this.E.get().booleanValue()) {
                    o();
                } else {
                    this.f12870a = this.c;
                    this.b = this.d;
                }
                w();
                this.q = false;
            }
        }
    }

    public final float p(float f2, float f3) {
        float atan2 = (float) ((Math.atan2(f2, f3) * 180.0d) / 3.141592653589793d);
        while (atan2 < -180.0f) {
            atan2 += 360.0f;
        }
        while (atan2 > 180.0f) {
            atan2 -= 360.0f;
        }
        return atan2;
    }

    public void pauseAllUpdates() {
        v();
        registerOrUnregisterCompassListener(false);
    }

    public final GpsStatus.Listener q(LocationManager locationManager) {
        b bVar = new b(locationManager);
        this.B = bVar;
        return bVar;
    }

    public final boolean r() {
        return Build.DEVICE.equals("generic");
    }

    public void reDownloadAGPS() {
        if (System.currentTimeMillis() - ((Long) this.t.k().q0.get()).longValue() <= 57600000 || !this.t.k().a(true)) {
            return;
        }
        try {
            LocationManager locationManager = (LocationManager) this.t.getSystemService(FirebaseAnalytics.Param.LOCATION);
            locationManager.sendExtraCommand("gps", "delete_aiding_data", null);
            Bundle bundle = new Bundle();
            locationManager.sendExtraCommand("gps", "force_xtra_injection", bundle);
            locationManager.sendExtraCommand("gps", "force_time_injection", bundle);
            this.t.k().q0.set(Long.valueOf(System.currentTimeMillis()));
        } catch (Exception e2) {
            this.t.k().q0.set(0L);
            NavigationLogger.d(e2);
        }
    }

    public synchronized void registerOrUnregisterCompassListener(boolean z) {
        boolean z2 = this.i;
        if (z2 && !z) {
            NavigationLogger.d("Disable sensor", new Object[0]);
            ((SensorManager) this.t.getSystemService("sensor")).unregisterListener(this);
            this.i = false;
            this.r = null;
        } else if (!z2 && z) {
            NavigationLogger.d("Enable sensor", new Object[0]);
            SensorManager sensorManager = (SensorManager) this.t.getSystemService("sensor");
            if (this.D.get().booleanValue()) {
                Sensor defaultSensor = sensorManager.getDefaultSensor(1);
                if (defaultSensor == null || !sensorManager.registerListener(this, defaultSensor, 2)) {
                    NavigationLogger.d("Sensor accelerometer could not be enabled", new Object[0]);
                }
                Sensor defaultSensor2 = sensorManager.getDefaultSensor(2);
                if (defaultSensor2 == null || !sensorManager.registerListener(this, defaultSensor2, 2)) {
                    NavigationLogger.d("Sensor magnetic field could not be enabled", new Object[0]);
                }
            } else {
                Sensor defaultSensor3 = sensorManager.getDefaultSensor(3);
                if (defaultSensor3 == null || !sensorManager.registerListener(this, defaultSensor3, 2)) {
                    NavigationLogger.d("Sensor orientation could not be enabled", new Object[0]);
                }
            }
            this.i = true;
        }
    }

    public void removeCompassListener(d dVar) {
        this.A.remove(dVar);
    }

    public void removeLocationChangeListener(LocationChangedListener locationChangedListener) {
        Iterator<WeakReference<LocationChangedListener>> it = this.e.iterator();
        while (it.hasNext()) {
            LocationChangedListener locationChangedListener2 = it.next().get();
            if (locationChangedListener2 == null || locationChangedListener == locationChangedListener2) {
                it.remove();
            }
        }
    }

    public void removeLocationListener(e eVar) {
        this.z.remove(eVar);
    }

    public void resumeAllUpdates() {
        LocationManager locationManager = (LocationManager) this.t.getSystemService(FirebaseAnalytics.Param.LOCATION);
        reDownloadAGPS();
        if (isLocationPermissionAvailable(this.t)) {
            locationManager.addGpsStatusListener(q(locationManager));
            try {
                locationManager.requestLocationUpdates("gps", 0L, 0.0f, this.F);
            } catch (IllegalArgumentException e2) {
                NavigationLogger.d(e2, "GPS location provider not available", new Object[0]);
            }
            List<String> providers = locationManager.getProviders(true);
            if (providers == null) {
                return;
            }
            for (String str : providers) {
                if (str != null && !str.equals("gps")) {
                    try {
                        f fVar = new f(this, 0);
                        locationManager.requestLocationUpdates(str, 0L, 0.0f, fVar);
                        this.G.add(fVar);
                    } catch (IllegalArgumentException e3) {
                        NavigationLogger.d(e3, o.a(str, " location provider not available"), new Object[0]);
                    }
                }
            }
        }
    }

    public final void s(NavLocation navLocation) {
        if (this.v.a()) {
            this.h = false;
            return;
        }
        com.mappls.sdk.navigation.routing.d dVar = this.t.q;
        if (navLocation != null) {
            long time = navLocation.getTime();
            NavigationApplication navigationApplication = this.t;
            Message obtain = Message.obtain(navigationApplication.l, new p(navigationApplication, new c(time, dVar)));
            obtain.what = 5001;
            navigationApplication.l.removeMessages(5001);
            navigationApplication.l.sendMessageDelayed(obtain, 18000L);
        }
    }

    @Deprecated
    public void setLocationChangedListener(LocationChangedListener locationChangedListener) {
        this.f = locationChangedListener;
    }

    public void setLocationFromSimulation(NavLocation navLocation) {
        t(navLocation);
        NavigationApplication navigationApplication = this.t;
        navigationApplication.setCurrentLocation(revertLocation(navLocation, navigationApplication));
    }

    public void setSatelliteInfo(GPSInfo gPSInfo) {
        this.y = gPSInfo;
        LocationChangedListener locationChangedListener = this.f;
        if (locationChangedListener != null) {
            locationChangedListener.onSatelliteInfoChanged(gPSInfo);
        }
        List<WeakReference<LocationChangedListener>> list = this.e;
        if (list != null) {
            Iterator<WeakReference<LocationChangedListener>> it = list.iterator();
            while (it.hasNext()) {
                LocationChangedListener locationChangedListener2 = it.next().get();
                if (locationChangedListener2 == null) {
                    it.remove();
                } else {
                    locationChangedListener2.onSatelliteInfoChanged(gPSInfo);
                }
            }
        }
    }

    public final void t(NavLocation navLocation) {
        if (navLocation == null) {
            x(null);
        }
        if (navLocation != null && this.h) {
            this.h = false;
            com.mappls.sdk.navigation.routing.d dVar = this.t.q;
            if (dVar.q() && dVar.h() > 0) {
                LocationChangedListener locationChangedListener = this.f;
                if (locationChangedListener != null) {
                    locationChangedListener.onGPSConnectionChanged(true);
                }
                List<WeakReference<LocationChangedListener>> list = this.e;
                if (list != null) {
                    Iterator<WeakReference<LocationChangedListener>> it = list.iterator();
                    while (it.hasNext()) {
                        LocationChangedListener locationChangedListener2 = it.next().get();
                        if (locationChangedListener2 == null) {
                            it.remove();
                        } else {
                            locationChangedListener2.onGPSConnectionChanged(true);
                        }
                    }
                }
            }
        }
        n(navLocation);
        s(navLocation);
        NavigationApplication navigationApplication = this.t;
        com.mappls.sdk.navigation.routing.d dVar2 = navigationApplication.q;
        if (navLocation != null) {
            navigationApplication.m.updateLocation(navLocation);
            Iterator it2 = s.b().iterator();
            while (it2.hasNext()) {
                ((s) it2.next()).d();
            }
        }
        if (dVar2.q()) {
            StringBuilder a2 = h.a("isPointAccurateForRouting(location) = ");
            a2.append(isPointAccurateForRouting(navLocation));
            NavigationLogger.d(a2.toString(), new Object[0]);
            if (navLocation == null || isPointAccurateForRouting(navLocation)) {
                navLocation = dVar2.a(navLocation, this.u.s.get().booleanValue());
                LocationChangedListener locationChangedListener3 = this.f;
                if (locationChangedListener3 != null) {
                    locationChangedListener3.onLocationChanged(revertLocation(navLocation, this.t));
                }
                List<WeakReference<LocationChangedListener>> list2 = this.e;
                if (list2 != null) {
                    Iterator<WeakReference<LocationChangedListener>> it3 = list2.iterator();
                    while (it3.hasNext()) {
                        LocationChangedListener locationChangedListener4 = it3.next().get();
                        if (locationChangedListener4 == null) {
                            it3.remove();
                        } else {
                            locationChangedListener4.onLocationChanged(revertLocation(navLocation, this.t));
                        }
                    }
                }
            }
        } else if ((dVar2.u() && this.u.m() == null) || getLocationSimulation().a()) {
            dVar2.a(navLocation, false);
        }
        this.t.w.b();
        this.x = navLocation;
        y(navLocation);
    }

    public void u(Location location, NavLocation navLocation, boolean z) {
        if (this.v.a()) {
            return;
        }
        if (z) {
            s(navLocation);
        }
        MapplsApiConfiguration.getInstance().setLocation(location);
        if (MapplsLMSManager.isInitialised()) {
            MapplsLMSManager.getInstance().setCurrentLocation(location);
        }
        this.x = navLocation;
        this.w = location;
        if (location != null) {
            try {
                if (com.mappls.sdk.traffic.a.b() != null) {
                    com.mappls.sdk.traffic.a.b().a(location);
                }
            } catch (Exception e2) {
                NavigationLogger.e(e2);
            }
        }
        t(navLocation);
    }

    public void updateScreenOrientation(int i) {
        this.s = i;
    }

    public final void v() {
        LocationManager locationManager = (LocationManager) this.t.getSystemService(FirebaseAnalytics.Param.LOCATION);
        locationManager.removeGpsStatusListener(this.B);
        LocationListener locationListener = this.F;
        while (true) {
            locationManager.removeUpdates(locationListener);
            if (this.G.isEmpty()) {
                return;
            }
            locationListener = this.G.poll();
        }
    }

    public final void w() {
        this.r = Float.valueOf(p(this.f12870a, this.b));
        for (d dVar : this.A) {
            this.r.floatValue();
            dVar.a();
        }
    }

    public final void x(GpsStatus gpsStatus) {
        int i;
        int i2;
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
        GPSInfo gPSInfo = this.y;
        gPSInfo.fixed = z;
        gPSInfo.foundSatellites = i;
        gPSInfo.usedSatellites = i2;
        LocationChangedListener locationChangedListener = this.f;
        if (locationChangedListener != null) {
            locationChangedListener.onSatelliteInfoChanged(gPSInfo);
        }
        List<WeakReference<LocationChangedListener>> list = this.e;
        if (list != null) {
            Iterator<WeakReference<LocationChangedListener>> it = list.iterator();
            while (it.hasNext()) {
                LocationChangedListener locationChangedListener2 = it.next().get();
                if (locationChangedListener2 == null) {
                    it.remove();
                } else {
                    locationChangedListener2.onSatelliteInfoChanged(this.y);
                }
            }
        }
    }

    public final void y(NavLocation navLocation) {
        for (e eVar : this.z) {
            eVar.a();
        }
    }

    public final void z(NavLocation navLocation) {
        if (navLocation == null || navLocation.distanceTo(navLocation) <= 3.0f) {
            return;
        }
        float distanceTo = navLocation.distanceTo(navLocation);
        long time = navLocation.getTime() - navLocation.getTime();
        float f2 = time == 0 ? 0.0f : (distanceTo * 1000.0f) / ((float) time);
        if (f2 > 100.0f) {
            f2 = 100.0f;
        }
        navLocation.setSpeed(f2);
    }
}
