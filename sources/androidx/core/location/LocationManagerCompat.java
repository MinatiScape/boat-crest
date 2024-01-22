package androidx.core.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.GnssMeasurementsEvent;
import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.annotation.DoNotInline;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.collection.SimpleArrayMap;
import androidx.core.location.GnssStatusCompat;
import androidx.core.location.LocationManagerCompat;
import androidx.core.os.CancellationSignal;
import androidx.core.os.ExecutorCompat;
import androidx.core.util.Consumer;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
/* loaded from: classes.dex */
public final class LocationManagerCompat {

    /* renamed from: a  reason: collision with root package name */
    public static Field f1060a;
    public static Class<?> b;
    public static Method c;
    public static Method d;
    @GuardedBy("sLocationListeners")
    public static final WeakHashMap<k, WeakReference<l>> e = new WeakHashMap<>();

    @RequiresApi(19)
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static Class<?> f1061a;
        public static Method b;

        @DoNotInline
        public static boolean a(LocationManager locationManager, String str, LocationRequestCompat locationRequestCompat, LocationListenerCompat locationListenerCompat, Looper looper) {
            if (Build.VERSION.SDK_INT >= 19) {
                try {
                    if (f1061a == null) {
                        f1061a = Class.forName("android.location.LocationRequest");
                    }
                    if (b == null) {
                        Method declaredMethod = LocationManager.class.getDeclaredMethod("requestLocationUpdates", f1061a, LocationListener.class, Looper.class);
                        b = declaredMethod;
                        declaredMethod.setAccessible(true);
                    }
                    LocationRequest locationRequest = locationRequestCompat.toLocationRequest(str);
                    if (locationRequest != null) {
                        b.invoke(locationManager, locationRequest, locationListenerCompat, looper);
                        return true;
                    }
                } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | UnsupportedOperationException | InvocationTargetException unused) {
                }
            }
            return false;
        }

        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        @DoNotInline
        public static boolean b(LocationManager locationManager, String str, LocationRequestCompat locationRequestCompat, l lVar) {
            if (Build.VERSION.SDK_INT >= 19) {
                try {
                    if (f1061a == null) {
                        f1061a = Class.forName("android.location.LocationRequest");
                    }
                    if (b == null) {
                        Method declaredMethod = LocationManager.class.getDeclaredMethod("requestLocationUpdates", f1061a, LocationListener.class, Looper.class);
                        b = declaredMethod;
                        declaredMethod.setAccessible(true);
                    }
                    LocationRequest locationRequest = locationRequestCompat.toLocationRequest(str);
                    if (locationRequest != null) {
                        synchronized (LocationManagerCompat.e) {
                            b.invoke(locationManager, locationRequest, lVar, Looper.getMainLooper());
                            LocationManagerCompat.g(locationManager, lVar);
                        }
                        return true;
                    }
                } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | UnsupportedOperationException | InvocationTargetException unused) {
                }
            }
            return false;
        }
    }

    @RequiresApi(24)
    /* loaded from: classes.dex */
    public static class b {
        @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
        @DoNotInline
        public static boolean a(@NonNull LocationManager locationManager, @NonNull GnssMeasurementsEvent.Callback callback, @NonNull Handler handler) {
            return locationManager.registerGnssMeasurementsCallback(callback, handler);
        }

        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        @DoNotInline
        public static boolean b(LocationManager locationManager, Handler handler, Executor executor, GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(handler != null);
            SimpleArrayMap<Object, Object> simpleArrayMap = g.f1063a;
            synchronized (simpleArrayMap) {
                m mVar = (m) simpleArrayMap.get(callback);
                if (mVar == null) {
                    mVar = new m(callback);
                } else {
                    mVar.j();
                }
                mVar.i(executor);
                if (locationManager.registerGnssStatusCallback(mVar, handler)) {
                    simpleArrayMap.put(callback, mVar);
                    return true;
                }
                return false;
            }
        }

        @DoNotInline
        public static void c(@NonNull LocationManager locationManager, @NonNull GnssMeasurementsEvent.Callback callback) {
            locationManager.unregisterGnssMeasurementsCallback(callback);
        }

        @DoNotInline
        public static void d(LocationManager locationManager, Object obj) {
            if (obj instanceof m) {
                ((m) obj).j();
            }
            locationManager.unregisterGnssStatusCallback((GnssStatus.Callback) obj);
        }
    }

    @RequiresApi(28)
    /* loaded from: classes.dex */
    public static class c {
        @DoNotInline
        public static String a(LocationManager locationManager) {
            return locationManager.getGnssHardwareModelName();
        }

        @DoNotInline
        public static int b(LocationManager locationManager) {
            return locationManager.getGnssYearOfHardware();
        }

        @DoNotInline
        public static boolean c(LocationManager locationManager) {
            return locationManager.isLocationEnabled();
        }
    }

    @RequiresApi(30)
    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public static Class<?> f1062a;
        public static Method b;

        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        @DoNotInline
        public static void a(LocationManager locationManager, @NonNull String str, @Nullable CancellationSignal cancellationSignal, @NonNull Executor executor, @NonNull final Consumer<Location> consumer) {
            android.os.CancellationSignal cancellationSignal2 = cancellationSignal != null ? (android.os.CancellationSignal) cancellationSignal.getCancellationSignalObject() : null;
            Objects.requireNonNull(consumer);
            locationManager.getCurrentLocation(str, cancellationSignal2, executor, new java.util.function.Consumer() { // from class: androidx.core.location.f
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Consumer.this.accept((Location) obj);
                }
            });
        }

        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        @DoNotInline
        public static boolean b(LocationManager locationManager, Handler handler, Executor executor, GnssStatusCompat.Callback callback) {
            SimpleArrayMap<Object, Object> simpleArrayMap = g.f1063a;
            synchronized (simpleArrayMap) {
                h hVar = (h) simpleArrayMap.get(callback);
                if (hVar == null) {
                    hVar = new h(callback);
                }
                if (locationManager.registerGnssStatusCallback(executor, hVar)) {
                    simpleArrayMap.put(callback, hVar);
                    return true;
                }
                return false;
            }
        }

        @DoNotInline
        public static boolean c(LocationManager locationManager, String str, LocationRequestCompat locationRequestCompat, Executor executor, LocationListenerCompat locationListenerCompat) {
            if (Build.VERSION.SDK_INT >= 30) {
                try {
                    if (f1062a == null) {
                        f1062a = Class.forName("android.location.LocationRequest");
                    }
                    if (b == null) {
                        Method declaredMethod = LocationManager.class.getDeclaredMethod("requestLocationUpdates", f1062a, Executor.class, LocationListener.class);
                        b = declaredMethod;
                        declaredMethod.setAccessible(true);
                    }
                    LocationRequest locationRequest = locationRequestCompat.toLocationRequest(str);
                    if (locationRequest != null) {
                        b.invoke(locationManager, locationRequest, executor, locationListenerCompat);
                        return true;
                    }
                } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | UnsupportedOperationException | InvocationTargetException unused) {
                }
            }
            return false;
        }
    }

    @RequiresApi(31)
    /* loaded from: classes.dex */
    public static class e {
        @DoNotInline
        public static boolean a(LocationManager locationManager, @NonNull String str) {
            return locationManager.hasProvider(str);
        }

        @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
        @DoNotInline
        public static boolean b(@NonNull LocationManager locationManager, @NonNull Executor executor, @NonNull GnssMeasurementsEvent.Callback callback) {
            return locationManager.registerGnssMeasurementsCallback(executor, callback);
        }

        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        @DoNotInline
        public static void c(LocationManager locationManager, @NonNull String str, @NonNull LocationRequest locationRequest, @NonNull Executor executor, @NonNull LocationListener locationListener) {
            locationManager.requestLocationUpdates(str, locationRequest, executor, locationListener);
        }
    }

    /* loaded from: classes.dex */
    public static final class f implements LocationListener {
        public final LocationManager h;
        public final Executor i;
        public final Handler j = new Handler(Looper.getMainLooper());
        public Consumer<Location> k;
        @GuardedBy("this")
        public boolean l;
        @Nullable
        public Runnable m;

        public f(LocationManager locationManager, Executor executor, Consumer<Location> consumer) {
            this.h = locationManager;
            this.i = executor;
            this.k = consumer;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f() {
            this.m = null;
            onLocationChanged((Location) null);
        }

        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        public void c() {
            synchronized (this) {
                if (this.l) {
                    return;
                }
                this.l = true;
                d();
            }
        }

        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        public final void d() {
            this.k = null;
            this.h.removeUpdates(this);
            Runnable runnable = this.m;
            if (runnable != null) {
                this.j.removeCallbacks(runnable);
                this.m = null;
            }
        }

        @SuppressLint({"MissingPermission"})
        public void g(long j) {
            synchronized (this) {
                if (this.l) {
                    return;
                }
                Runnable runnable = new Runnable() { // from class: androidx.core.location.g
                    @Override // java.lang.Runnable
                    public final void run() {
                        LocationManagerCompat.f.this.f();
                    }
                };
                this.m = runnable;
                this.j.postDelayed(runnable, j);
            }
        }

        @Override // android.location.LocationListener
        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        public void onLocationChanged(@Nullable final Location location) {
            synchronized (this) {
                if (this.l) {
                    return;
                }
                this.l = true;
                final Consumer<Location> consumer = this.k;
                this.i.execute(new Runnable() { // from class: androidx.core.location.h
                    @Override // java.lang.Runnable
                    public final void run() {
                        Consumer.this.accept(location);
                    }
                });
                d();
            }
        }

        @Override // android.location.LocationListener
        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        public void onProviderDisabled(@NonNull String str) {
            onLocationChanged((Location) null);
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(@NonNull String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    /* loaded from: classes.dex */
    public static class g {
        @GuardedBy("sGnssStatusListeners")

        /* renamed from: a  reason: collision with root package name */
        public static final SimpleArrayMap<Object, Object> f1063a = new SimpleArrayMap<>();
    }

    @RequiresApi(30)
    /* loaded from: classes.dex */
    public static class h extends GnssStatus.Callback {

        /* renamed from: a  reason: collision with root package name */
        public final GnssStatusCompat.Callback f1064a;

        public h(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.f1064a = callback;
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(int i) {
            this.f1064a.onFirstFix(i);
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            this.f1064a.onSatelliteStatusChanged(GnssStatusCompat.wrap(gnssStatus));
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
            this.f1064a.onStarted();
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            this.f1064a.onStopped();
        }
    }

    /* loaded from: classes.dex */
    public static class i implements GpsStatus.Listener {

        /* renamed from: a  reason: collision with root package name */
        public final LocationManager f1065a;
        public final GnssStatusCompat.Callback b;
        @Nullable
        public volatile Executor c;

        public i(LocationManager locationManager, GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.f1065a = locationManager;
            this.b = callback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void e(Executor executor) {
            if (this.c != executor) {
                return;
            }
            this.b.onStarted();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f(Executor executor) {
            if (this.c != executor) {
                return;
            }
            this.b.onStopped();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void g(Executor executor, int i) {
            if (this.c != executor) {
                return;
            }
            this.b.onFirstFix(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void h(Executor executor, GnssStatusCompat gnssStatusCompat) {
            if (this.c != executor) {
                return;
            }
            this.b.onSatelliteStatusChanged(gnssStatusCompat);
        }

        public void i(Executor executor) {
            Preconditions.checkState(this.c == null);
            this.c = executor;
        }

        public void j() {
            this.c = null;
        }

        @Override // android.location.GpsStatus.Listener
        @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
        public void onGpsStatusChanged(int i) {
            GpsStatus gpsStatus;
            final Executor executor = this.c;
            if (executor == null) {
                return;
            }
            if (i == 1) {
                executor.execute(new Runnable() { // from class: androidx.core.location.i
                    @Override // java.lang.Runnable
                    public final void run() {
                        LocationManagerCompat.i.this.e(executor);
                    }
                });
            } else if (i == 2) {
                executor.execute(new Runnable() { // from class: androidx.core.location.j
                    @Override // java.lang.Runnable
                    public final void run() {
                        LocationManagerCompat.i.this.f(executor);
                    }
                });
            } else if (i != 3) {
                if (i == 4 && (gpsStatus = this.f1065a.getGpsStatus(null)) != null) {
                    final GnssStatusCompat wrap = GnssStatusCompat.wrap(gpsStatus);
                    executor.execute(new Runnable() { // from class: androidx.core.location.l
                        @Override // java.lang.Runnable
                        public final void run() {
                            LocationManagerCompat.i.this.h(executor, wrap);
                        }
                    });
                }
            } else {
                GpsStatus gpsStatus2 = this.f1065a.getGpsStatus(null);
                if (gpsStatus2 != null) {
                    final int timeToFirstFix = gpsStatus2.getTimeToFirstFix();
                    executor.execute(new Runnable() { // from class: androidx.core.location.k
                        @Override // java.lang.Runnable
                        public final void run() {
                            LocationManagerCompat.i.this.g(executor, timeToFirstFix);
                        }
                    });
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class j implements Executor {
        public final Handler h;

        public j(@NonNull Handler handler) {
            this.h = (Handler) Preconditions.checkNotNull(handler);
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            if (Looper.myLooper() == this.h.getLooper()) {
                runnable.run();
            } else if (this.h.post((Runnable) Preconditions.checkNotNull(runnable))) {
            } else {
                throw new RejectedExecutionException(this.h + " is shutting down");
            }
        }
    }

    /* loaded from: classes.dex */
    public static class k {

        /* renamed from: a  reason: collision with root package name */
        public final String f1066a;
        public final LocationListenerCompat b;

        public k(String str, LocationListenerCompat locationListenerCompat) {
            this.f1066a = (String) ObjectsCompat.requireNonNull(str, "invalid null provider");
            this.b = (LocationListenerCompat) ObjectsCompat.requireNonNull(locationListenerCompat, "invalid null listener");
        }

        public boolean equals(Object obj) {
            if (obj instanceof k) {
                k kVar = (k) obj;
                return this.f1066a.equals(kVar.f1066a) && this.b.equals(kVar.b);
            }
            return false;
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.f1066a, this.b);
        }
    }

    @RequiresApi(24)
    /* loaded from: classes.dex */
    public static class m extends GnssStatus.Callback {

        /* renamed from: a  reason: collision with root package name */
        public final GnssStatusCompat.Callback f1067a;
        @Nullable
        public volatile Executor b;

        public m(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.f1067a = callback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void e(Executor executor, int i) {
            if (this.b != executor) {
                return;
            }
            this.f1067a.onFirstFix(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f(Executor executor, GnssStatus gnssStatus) {
            if (this.b != executor) {
                return;
            }
            this.f1067a.onSatelliteStatusChanged(GnssStatusCompat.wrap(gnssStatus));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void g(Executor executor) {
            if (this.b != executor) {
                return;
            }
            this.f1067a.onStarted();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void h(Executor executor) {
            if (this.b != executor) {
                return;
            }
            this.f1067a.onStopped();
        }

        public void i(Executor executor) {
            Preconditions.checkArgument(executor != null, "invalid null executor");
            Preconditions.checkState(this.b == null);
            this.b = executor;
        }

        public void j() {
            this.b = null;
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(final int i) {
            final Executor executor = this.b;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.u
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.m.this.e(executor, i);
                }
            });
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(final GnssStatus gnssStatus) {
            final Executor executor = this.b;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.v
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.m.this.f(executor, gnssStatus);
                }
            });
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
            final Executor executor = this.b;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.t
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.m.this.g(executor);
                }
            });
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            final Executor executor = this.b;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.s
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.m.this.h(executor);
                }
            });
        }
    }

    public static /* synthetic */ Boolean d(LocationManager locationManager, i iVar) throws Exception {
        return Boolean.valueOf(locationManager.addGpsStatusListener(iVar));
    }

    @RequiresApi(30)
    public static boolean e(@NonNull LocationManager locationManager, @NonNull Executor executor, @NonNull GnssMeasurementsEvent.Callback callback) {
        if (Build.VERSION.SDK_INT == 30) {
            try {
                if (b == null) {
                    b = Class.forName("android.location.GnssRequest$Builder");
                }
                if (c == null) {
                    Method declaredMethod = b.getDeclaredMethod("build", new Class[0]);
                    c = declaredMethod;
                    declaredMethod.setAccessible(true);
                }
                if (d == null) {
                    Method declaredMethod2 = LocationManager.class.getDeclaredMethod("registerGnssMeasurementsCallback", Class.forName("android.location.GnssRequest"), Executor.class, GnssMeasurementsEvent.Callback.class);
                    d = declaredMethod2;
                    declaredMethod2.setAccessible(true);
                }
                Object invoke = d.invoke(locationManager, c.invoke(b.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]), new Object[0]), executor, callback);
                if (invoke != null) {
                    return ((Boolean) invoke).booleanValue();
                }
                return false;
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
                return false;
            }
        }
        throw new IllegalStateException();
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00c5 A[Catch: all -> 0x00e1, TryCatch #0 {all -> 0x00e1, blocks: (B:54:0x00a4, B:55:0x00ba, B:58:0x00bd, B:60:0x00c5, B:62:0x00cd, B:63:0x00d3, B:64:0x00d4, B:65:0x00d9, B:66:0x00da, B:67:0x00e0, B:44:0x0093), top: B:77:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00da A[Catch: all -> 0x00e1, TryCatch #0 {all -> 0x00e1, blocks: (B:54:0x00a4, B:55:0x00ba, B:58:0x00bd, B:60:0x00c5, B:62:0x00cd, B:63:0x00d3, B:64:0x00d4, B:65:0x00d9, B:66:0x00da, B:67:0x00e0, B:44:0x0093), top: B:77:0x0053 }] */
    @androidx.annotation.RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean f(final android.location.LocationManager r9, android.os.Handler r10, java.util.concurrent.Executor r11, androidx.core.location.GnssStatusCompat.Callback r12) {
        /*
            Method dump skipped, instructions count: 262
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.location.LocationManagerCompat.f(android.location.LocationManager, android.os.Handler, java.util.concurrent.Executor, androidx.core.location.GnssStatusCompat$Callback):boolean");
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    @GuardedBy("sLocationListeners")
    public static void g(LocationManager locationManager, l lVar) {
        WeakReference<l> put = e.put(lVar.g(), new WeakReference<>(lVar));
        l lVar2 = put != null ? put.get() : null;
        if (lVar2 != null) {
            lVar2.n();
            locationManager.removeUpdates(lVar2);
        }
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public static void getCurrentLocation(@NonNull LocationManager locationManager, @NonNull String str, @Nullable CancellationSignal cancellationSignal, @NonNull Executor executor, @NonNull final Consumer<Location> consumer) {
        if (Build.VERSION.SDK_INT >= 30) {
            d.a(locationManager, str, cancellationSignal, executor, consumer);
            return;
        }
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
        }
        final Location lastKnownLocation = locationManager.getLastKnownLocation(str);
        if (lastKnownLocation != null && SystemClock.elapsedRealtime() - LocationCompat.getElapsedRealtimeMillis(lastKnownLocation) < 10000) {
            executor.execute(new Runnable() { // from class: androidx.core.location.d
                @Override // java.lang.Runnable
                public final void run() {
                    Consumer.this.accept(lastKnownLocation);
                }
            });
            return;
        }
        final f fVar = new f(locationManager, executor, consumer);
        locationManager.requestLocationUpdates(str, 0L, 0.0f, fVar, Looper.getMainLooper());
        if (cancellationSignal != null) {
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.core.location.c
                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                public final void onCancel() {
                    LocationManagerCompat.f.this.c();
                }
            });
        }
        fVar.g(30000L);
    }

    @Nullable
    public static String getGnssHardwareModelName(@NonNull LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return c.a(locationManager);
        }
        return null;
    }

    public static int getGnssYearOfHardware(@NonNull LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return c.b(locationManager);
        }
        return 0;
    }

    public static boolean hasProvider(@NonNull LocationManager locationManager, @NonNull String str) {
        if (Build.VERSION.SDK_INT >= 31) {
            return e.a(locationManager, str);
        }
        if (locationManager.getAllProviders().contains(str)) {
            return true;
        }
        try {
            return locationManager.getProvider(str) != null;
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static boolean isLocationEnabled(@NonNull LocationManager locationManager) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            return c.c(locationManager);
        }
        if (i2 <= 19) {
            try {
                if (f1060a == null) {
                    Field declaredField = LocationManager.class.getDeclaredField("mContext");
                    f1060a = declaredField;
                    declaredField.setAccessible(true);
                }
                Context context = (Context) f1060a.get(locationManager);
                if (context != null) {
                    if (i2 == 19) {
                        return Settings.Secure.getInt(context.getContentResolver(), "location_mode", 0) != 0;
                    }
                    return !TextUtils.isEmpty(Settings.Secure.getString(context.getContentResolver(), "location_providers_allowed"));
                }
            } catch (ClassCastException | IllegalAccessException | NoSuchFieldException | SecurityException unused) {
            }
        }
        return locationManager.isProviderEnabled("network") || locationManager.isProviderEnabled("gps");
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    @RequiresApi(24)
    public static boolean registerGnssMeasurementsCallback(@NonNull LocationManager locationManager, @NonNull GnssMeasurementsEvent.Callback callback, @NonNull Handler handler) {
        if (Build.VERSION.SDK_INT != 30) {
            return b.a(locationManager, callback, handler);
        }
        return e(locationManager, ExecutorCompat.create(handler), callback);
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public static boolean registerGnssStatusCallback(@NonNull LocationManager locationManager, @NonNull GnssStatusCompat.Callback callback, @NonNull Handler handler) {
        if (Build.VERSION.SDK_INT >= 30) {
            return registerGnssStatusCallback(locationManager, ExecutorCompat.create(handler), callback);
        }
        return registerGnssStatusCallback(locationManager, new j(handler), callback);
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public static void removeUpdates(@NonNull LocationManager locationManager, @NonNull LocationListenerCompat locationListenerCompat) {
        WeakHashMap<k, WeakReference<l>> weakHashMap = e;
        synchronized (weakHashMap) {
            ArrayList arrayList = null;
            for (WeakReference<l> weakReference : weakHashMap.values()) {
                l lVar = weakReference.get();
                if (lVar != null) {
                    k g2 = lVar.g();
                    if (g2.b == locationListenerCompat) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(g2);
                        lVar.n();
                        locationManager.removeUpdates(lVar);
                    }
                }
            }
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    e.remove((k) it.next());
                }
            }
        }
        locationManager.removeUpdates(locationListenerCompat);
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public static void requestLocationUpdates(@NonNull LocationManager locationManager, @NonNull String str, @NonNull LocationRequestCompat locationRequestCompat, @NonNull Executor executor, @NonNull LocationListenerCompat locationListenerCompat) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 31) {
            e.c(locationManager, str, locationRequestCompat.toLocationRequest(), executor, locationListenerCompat);
        } else if (i2 < 30 || !d.c(locationManager, str, locationRequestCompat, executor, locationListenerCompat)) {
            l lVar = new l(new k(str, locationListenerCompat), executor);
            if (i2 < 19 || !a.b(locationManager, str, locationRequestCompat, lVar)) {
                synchronized (e) {
                    locationManager.requestLocationUpdates(str, locationRequestCompat.getIntervalMillis(), locationRequestCompat.getMinUpdateDistanceMeters(), lVar, Looper.getMainLooper());
                    g(locationManager, lVar);
                }
            }
        }
    }

    @RequiresApi(24)
    public static void unregisterGnssMeasurementsCallback(@NonNull LocationManager locationManager, @NonNull GnssMeasurementsEvent.Callback callback) {
        b.c(locationManager, callback);
    }

    public static void unregisterGnssStatusCallback(@NonNull LocationManager locationManager, @NonNull GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 24) {
            SimpleArrayMap<Object, Object> simpleArrayMap = g.f1063a;
            synchronized (simpleArrayMap) {
                Object remove = simpleArrayMap.remove(callback);
                if (remove != null) {
                    b.d(locationManager, remove);
                }
            }
            return;
        }
        SimpleArrayMap<Object, Object> simpleArrayMap2 = g.f1063a;
        synchronized (simpleArrayMap2) {
            i iVar = (i) simpleArrayMap2.remove(callback);
            if (iVar != null) {
                iVar.j();
                locationManager.removeGpsStatusListener(iVar);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class l implements LocationListener {
        @Nullable
        public volatile k h;
        public final Executor i;

        public l(@Nullable k kVar, Executor executor) {
            this.h = kVar;
            this.i = executor;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void h(int i) {
            k kVar = this.h;
            if (kVar == null) {
                return;
            }
            kVar.b.onFlushComplete(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void i(Location location) {
            k kVar = this.h;
            if (kVar == null) {
                return;
            }
            kVar.b.onLocationChanged(location);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void j(List list) {
            k kVar = this.h;
            if (kVar == null) {
                return;
            }
            kVar.b.onLocationChanged(list);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void k(String str) {
            k kVar = this.h;
            if (kVar == null) {
                return;
            }
            kVar.b.onProviderDisabled(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void l(String str) {
            k kVar = this.h;
            if (kVar == null) {
                return;
            }
            kVar.b.onProviderEnabled(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void m(String str, int i, Bundle bundle) {
            k kVar = this.h;
            if (kVar == null) {
                return;
            }
            kVar.b.onStatusChanged(str, i, bundle);
        }

        public k g() {
            return (k) ObjectsCompat.requireNonNull(this.h);
        }

        public void n() {
            this.h = null;
        }

        @Override // android.location.LocationListener
        public void onFlushComplete(final int i) {
            if (this.h == null) {
                return;
            }
            this.i.execute(new Runnable() { // from class: androidx.core.location.m
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.l.this.h(i);
                }
            });
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(@NonNull final Location location) {
            if (this.h == null) {
                return;
            }
            this.i.execute(new Runnable() { // from class: androidx.core.location.n
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.l.this.i(location);
                }
            });
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(@NonNull final String str) {
            if (this.h == null) {
                return;
            }
            this.i.execute(new Runnable() { // from class: androidx.core.location.p
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.l.this.k(str);
                }
            });
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(@NonNull final String str) {
            if (this.h == null) {
                return;
            }
            this.i.execute(new Runnable() { // from class: androidx.core.location.o
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.l.this.l(str);
                }
            });
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(final String str, final int i, final Bundle bundle) {
            if (this.h == null) {
                return;
            }
            this.i.execute(new Runnable() { // from class: androidx.core.location.q
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.l.this.m(str, i, bundle);
                }
            });
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(@NonNull final List<Location> list) {
            if (this.h == null) {
                return;
            }
            this.i.execute(new Runnable() { // from class: androidx.core.location.r
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.l.this.j(list);
                }
            });
        }
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public static boolean registerGnssStatusCallback(@NonNull LocationManager locationManager, @NonNull Executor executor, @NonNull GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            return f(locationManager, null, executor, callback);
        }
        Looper myLooper = Looper.myLooper();
        if (myLooper == null) {
            myLooper = Looper.getMainLooper();
        }
        return f(locationManager, new Handler(myLooper), executor, callback);
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    @RequiresApi(30)
    public static boolean registerGnssMeasurementsCallback(@NonNull LocationManager locationManager, @NonNull Executor executor, @NonNull GnssMeasurementsEvent.Callback callback) {
        if (Build.VERSION.SDK_INT > 30) {
            return e.b(locationManager, executor, callback);
        }
        return e(locationManager, executor, callback);
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public static void requestLocationUpdates(@NonNull LocationManager locationManager, @NonNull String str, @NonNull LocationRequestCompat locationRequestCompat, @NonNull LocationListenerCompat locationListenerCompat, @NonNull Looper looper) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 31) {
            e.c(locationManager, str, locationRequestCompat.toLocationRequest(), ExecutorCompat.create(new Handler(looper)), locationListenerCompat);
        } else if (i2 < 19 || !a.a(locationManager, str, locationRequestCompat, locationListenerCompat, looper)) {
            locationManager.requestLocationUpdates(str, locationRequestCompat.getIntervalMillis(), locationRequestCompat.getMinUpdateDistanceMeters(), locationListenerCompat, looper);
        }
    }
}
