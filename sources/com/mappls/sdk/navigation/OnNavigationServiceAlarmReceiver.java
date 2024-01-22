package com.mappls.sdk.navigation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.PowerManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mappls.sdk.navigation.apis.NavigationLogger;
/* loaded from: classes11.dex */
public class OnNavigationServiceAlarmReceiver extends BroadcastReceiver {

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public final /* synthetic */ PowerManager.WakeLock h;
        public final /* synthetic */ LocationManager i;
        public final /* synthetic */ NavigationService j;

        public a(PowerManager.WakeLock wakeLock, LocationManager locationManager, NavigationService navigationService) {
            this.h = wakeLock;
            this.i = locationManager;
            this.j = navigationService;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.h.isHeld()) {
                this.h.release();
                this.i.removeUpdates(this.j);
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        PowerManager.WakeLock a2 = NavigationService.a(context);
        NavigationService navigationService = ((NavigationApplication) context.getApplicationContext()).n;
        if (a2.isHeld() || navigationService == null) {
            return;
        }
        a2.acquire();
        LocationManager locationManager = (LocationManager) navigationService.getSystemService(FirebaseAnalytics.Param.LOCATION);
        try {
            locationManager.requestLocationUpdates(navigationService.d(), 0L, 0.0f, navigationService);
            if (navigationService.c() > navigationService.b()) {
                navigationService.a().postDelayed(new a(a2, locationManager, navigationService), navigationService.b());
            }
        } catch (RuntimeException e) {
            NavigationLogger.d(e);
            e.printStackTrace();
        }
    }
}
