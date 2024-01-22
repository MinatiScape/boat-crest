package com.clevertap.android.sdk;

import android.app.Activity;
import android.location.Location;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class CoreMetaData extends l {
    public static WeakReference<Activity> A = null;
    public static int B = 0;
    public static int C = 0;
    public static boolean z = false;

    /* renamed from: a  reason: collision with root package name */
    public WeakReference<Activity> f2574a;
    public boolean n;
    public boolean q;
    public boolean r;
    public long b = 0;
    public boolean c = false;
    public final Object d = new Object();
    public String e = null;
    public int f = 0;
    public boolean g = false;
    public boolean h = false;
    public boolean i = false;
    public int j = 0;
    public boolean k = false;
    public boolean l = false;
    public boolean m = false;
    public int o = 0;
    public Location p = null;
    public final Object s = new Object();
    public HashMap<String, Integer> t = new HashMap<>();
    public long u = 0;
    public String v = null;
    public String w = null;
    public String x = null;
    public JSONObject y = null;

    public static int e() {
        return C;
    }

    public static void f() {
        B++;
    }

    public static int getActivityCount() {
        return B;
    }

    public static Activity getCurrentActivity() {
        WeakReference<Activity> weakReference = A;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public static String getCurrentActivityName() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            return currentActivity.getLocalClassName();
        }
        return null;
    }

    public static boolean isAppForeground() {
        return z;
    }

    public static void k(int i) {
        C = i;
    }

    public static void setActivityCount(int i) {
        B = i;
    }

    public static void setAppForeground(boolean z2) {
        z = z2;
    }

    public static void setCurrentActivity(@Nullable Activity activity) {
        if (activity == null) {
            A = null;
        } else if (activity.getLocalClassName().contains("InAppNotificationActivity")) {
        } else {
            A = new WeakReference<>(activity);
        }
    }

    public synchronized void a() {
        this.x = null;
    }

    public synchronized void b() {
        this.w = null;
    }

    public synchronized void c() {
        this.v = null;
    }

    public synchronized void d() {
        this.y = null;
    }

    public void g(boolean z2) {
        synchronized (this.d) {
            this.c = z2;
        }
    }

    public HashMap<String, Integer> getAllCustomSdkVersions() {
        return this.t;
    }

    public Activity getAppInboxActivity() {
        WeakReference<Activity> weakReference = this.f2574a;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public long getAppInstallTime() {
        return this.b;
    }

    public synchronized String getCampaign() {
        return this.x;
    }

    public int getCurrentSessionId() {
        return this.f;
    }

    public int getCustomSdkVersion(String str) {
        Integer num = this.t.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public int getGeofenceSDKVersion() {
        return this.j;
    }

    public int getLastSessionLength() {
        return this.o;
    }

    public Location getLocationFromUser() {
        return this.p;
    }

    public synchronized String getMedium() {
        return this.w;
    }

    public long getReferrerClickTime() {
        return this.u;
    }

    public String getScreenName() {
        return this.e;
    }

    public synchronized String getSource() {
        return this.v;
    }

    public synchronized JSONObject getWzrkParams() {
        return this.y;
    }

    public synchronized void h(String str) {
        if (this.x == null) {
            this.x = str;
        }
    }

    public void i(int i) {
        this.f = i;
    }

    public boolean inCurrentSession() {
        return this.f > 0;
    }

    public boolean isAppLaunchPushed() {
        boolean z2;
        synchronized (this.d) {
            z2 = this.c;
        }
        return z2;
    }

    public boolean isBgPing() {
        return this.l;
    }

    public boolean isCurrentUserOptedOut() {
        boolean z2;
        synchronized (this.s) {
            z2 = this.g;
        }
        return z2;
    }

    public boolean isFirstRequestInSession() {
        return this.h;
    }

    public boolean isFirstSession() {
        return this.i;
    }

    public boolean isInstallReferrerDataSent() {
        return this.k;
    }

    public boolean isLocationForGeofence() {
        return this.m;
    }

    public boolean isOffline() {
        return this.q;
    }

    public boolean isProductConfigRequested() {
        return this.n;
    }

    public boolean isWebInterfaceInitializedExternally() {
        return this.r;
    }

    public void j(boolean z2) {
        this.i = z2;
    }

    public void l(boolean z2) {
        this.k = z2;
    }

    public void m(int i) {
        this.o = i;
    }

    public synchronized void n(String str) {
        if (this.w == null) {
            this.w = str;
        }
    }

    public void o(boolean z2) {
        this.q = z2;
    }

    public void p(long j) {
        this.u = j;
    }

    public synchronized void q(String str) {
        if (this.v == null) {
            this.v = str;
        }
    }

    public void setAppInboxActivity(@Nullable Activity activity) {
        this.f2574a = new WeakReference<>(activity);
    }

    public void setAppInstallTime(long j) {
        this.b = j;
    }

    public void setBgPing(boolean z2) {
        this.l = z2;
    }

    public void setCurrentScreenName(String str) {
        this.e = str;
    }

    public void setCurrentUserOptedOut(boolean z2) {
        synchronized (this.s) {
            this.g = z2;
        }
    }

    public void setCustomSdkVersion(String str, int i) {
        this.t.put(str, Integer.valueOf(i));
    }

    public void setFirstRequestInSession(boolean z2) {
        this.h = z2;
    }

    public void setGeofenceSDKVersion(int i) {
        this.j = i;
    }

    public void setLocationForGeofence(boolean z2) {
        this.m = z2;
    }

    public void setLocationFromUser(Location location) {
        this.p = location;
    }

    public void setProductConfigRequested(boolean z2) {
        this.n = z2;
    }

    public void setWebInterfaceInitializedExternally(boolean z2) {
        this.r = z2;
    }

    public synchronized void setWzrkParams(JSONObject jSONObject) {
        if (this.y == null) {
            this.y = jSONObject;
        }
    }
}
