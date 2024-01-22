package com.mappls.sdk.navigation;

import android.app.Activity;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityManager;
import androidx.core.content.ContextCompat;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.model.NavigationPath;
import com.mappls.sdk.navigation.routing.NavigationStep;
import com.mappls.sdk.navigation.t;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import com.mappls.sdk.traffic.db.BeaconDatabase;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public class NavigationApplication extends Application {
    public BeaconDatabase A;
    public Location c;
    public final c h = new c(this);
    public final u i = new u();
    public t j;
    public i k;
    public Handler l;
    public SavingTrackHelper m;
    public NavigationService n;
    public NavigationLocationProvider o;
    public com.mappls.sdk.navigation.util.b p;
    public com.mappls.sdk.navigation.routing.d q;
    public com.mappls.sdk.navigation.session.a r;
    public com.mappls.sdk.navigation.voice.g s;
    public NotificationHelper t;
    public x u;
    public com.mappls.sdk.navigation.refresh.h v;
    public com.mappls.sdk.navigation.helpers.c w;
    public Locale x;
    public Locale y;
    public File z;

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            while (true) {
                NavigationApplication navigationApplication = NavigationApplication.this;
                if (navigationApplication.n == null) {
                    NavigationApplication.b(navigationApplication);
                    return;
                }
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    NavigationLogger.e(e);
                }
            }
        }
    }

    public NavigationApplication() {
        new HashMap();
        this.c = null;
        this.j = null;
        this.x = null;
    }

    public static void b(NavigationApplication navigationApplication) {
        navigationApplication.getClass();
        System.runFinalizersOnExit(true);
        System.exit(0);
    }

    public static boolean isLocationEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                return Settings.Secure.getInt(context.getContentResolver(), "location_mode") != 0;
            } catch (Settings.SettingNotFoundException unused) {
                return false;
            }
        }
        return !TextUtils.isEmpty(Settings.Secure.getString(context.getContentResolver(), "location_providers_allowed"));
    }

    public final void a(int i, int i2) {
        Intent intent = new Intent(this, NavigationService.class);
        if (this.n != null) {
            NavigationLogger.d("startNavigationService", new Object[0]);
            NavigationService navigationService = this.n;
            i |= navigationService.f12872a;
            i2 = Math.min(navigationService.c(), i2);
            this.n.stopSelf();
        }
        intent.putExtra("SERVICE_USED_BY", i);
        intent.putExtra("SERVICE_OFF_INTERVAL", i2);
        startService(intent);
    }

    public final void a(Context context) {
        String str = (String) this.j.t0.get();
        if (this.s == null || !com.mappls.sdk.navigation.util.a.a(str, (Object) null)) {
            this.h.c(context, str);
        }
    }

    public final void a(Runnable runnable) {
        this.l.post(runnable);
    }

    public final boolean a() {
        com.mappls.sdk.navigation.apis.a aVar = (com.mappls.sdk.navigation.apis.a) k().h.get();
        if (aVar == com.mappls.sdk.navigation.apis.a.ON) {
            return true;
        }
        if (aVar == com.mappls.sdk.navigation.apis.a.OFF) {
            return false;
        }
        return ((AccessibilityManager) getSystemService("accessibility")).isEnabled();
    }

    public final String b() {
        String str = Build.BRAND;
        String str2 = Build.MODEL;
        String string = Settings.Secure.getString(getContentResolver(), "android_id");
        return str + ':' + str2 + ':' + string;
    }

    public final i c() {
        return this.k;
    }

    public void closeApplicationAnywayImpl(Activity activity, boolean z) {
        activity.finish();
        if (this.n == null) {
            System.runFinalizersOnExit(true);
            System.exit(0);
        } else if (z) {
            NavigationLogger.d("closeApplicationAnywayImpl", new Object[0]);
            stopService(new Intent(this, NavigationService.class));
            new Thread(new a()).start();
        }
    }

    public final File d() {
        return new File(this.z, "nav_track/recordings/");
    }

    public final BeaconDatabase e() {
        return this.A;
    }

    public final NavigationService f() {
        return this.n;
    }

    public final com.mappls.sdk.navigation.session.a g() {
        return this.r;
    }

    public NavigationPath getCalculatedRoute() {
        NavigationPath navigationPath = new NavigationPath();
        navigationPath.setPath(this.q.k().getImmutableAllLocations());
        return navigationPath;
    }

    public Location getCurrentLocation() {
        if (isLocationEnabled(this)) {
            return this.c;
        }
        return null;
    }

    public NavigationLocationProvider getLocationProvider() {
        return this.o;
    }

    public NotificationHelper getNotificationHelper() {
        return this.t;
    }

    public List<NavigationStep> getRouteDirections() {
        return this.q.k().getImmutableAllDirections();
    }

    public final com.mappls.sdk.navigation.routing.d h() {
        return this.q;
    }

    public final com.mappls.sdk.navigation.refresh.h i() {
        return this.v;
    }

    public final SavingTrackHelper j() {
        return this.m;
    }

    public final t k() {
        if (this.j == null) {
            NavigationLogger.e("Trying to access settings before they were created", new Object[0]);
        }
        return this.j;
    }

    public final int l() {
        boolean z = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("bluetooth_phone_call_switch_preference", false);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        boolean z2 = true;
        if (ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0 || defaultAdapter == null || !defaultAdapter.isEnabled() || defaultAdapter.getProfileConnectionState(1) != 2) {
            z2 = false;
        }
        return (z && z2) ? 0 : 3;
    }

    public final x m() {
        return this.u;
    }

    public final u n() {
        return this.i;
    }

    public final com.mappls.sdk.navigation.helpers.c o() {
        return this.w;
    }

    public void onAutoDriveEnabled() {
        q locationSimulation = getLocationProvider().getLocationSimulation();
        com.mappls.sdk.navigation.routing.d dVar = this.q;
        if (locationSimulation.a() || !dVar.q() || !dVar.t() || dVar.s()) {
            return;
        }
        locationSimulation.c();
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        if (this.x == null || configuration.locale.getLanguage().equals(this.x.getLanguage())) {
            super.onConfigurationChanged(configuration);
            return;
        }
        super.onConfigurationChanged(configuration);
        if (Build.VERSION.SDK_INT < 14) {
            configuration.locale = this.x;
        }
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        Locale.setDefault(this.x);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0116  */
    @Override // android.app.Application
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onCreate() {
        /*
            Method dump skipped, instructions count: 297
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.NavigationApplication.onCreate():void");
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public final void onLowMemory() {
        super.onLowMemory();
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        NavigationLogger.setApplication(null);
        if (MapplsNavigationHelper.getInstance().isCloseServiceOnRemovingTask()) {
            MapplsNavigationHelper.getInstance().stopNavigation();
            com.mappls.sdk.navigation.routing.d dVar = this.q;
            if (dVar != null) {
                dVar.o().getClass();
                com.mappls.sdk.navigation.routing.h.h();
            }
            getNotificationHelper().removeNotifications();
        }
    }

    public final void p() {
        this.l.post(new l());
    }

    public final void q() {
        this.l.post(new m());
    }

    public void setCurrentLocation(Location location) {
        this.c = location;
    }

    public void stopNavigation() {
        NavigationLogger.d("stopNavigation", new Object[0]);
        if (this.o.getLocationSimulation().a()) {
            this.o.getLocationSimulation().d();
        }
        this.u.b();
        this.q.o().getClass();
        com.mappls.sdk.navigation.routing.h.d();
        this.q.a((String) null, (List<LatLng>) new ArrayList(), true);
        this.q.b(false);
        this.q.a(false);
        t.b(t.this);
        t tVar = this.j;
        t.g gVar = (t.g) tVar.E0;
        gVar.getClass();
        gVar.set((d) ((t.j) tVar.w0).get());
        this.u.a(false);
        NavigationService navigationService = this.n;
        if (navigationService != null) {
            navigationService.stopSelf();
        }
    }
}
