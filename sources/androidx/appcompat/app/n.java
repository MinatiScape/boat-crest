package androidx.appcompat.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.PermissionChecker;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Calendar;
/* loaded from: classes.dex */
public class n {
    public static n d;

    /* renamed from: a  reason: collision with root package name */
    public final Context f406a;
    public final LocationManager b;
    public final a c = new a();

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public boolean f407a;
        public long b;
    }

    @VisibleForTesting
    public n(@NonNull Context context, @NonNull LocationManager locationManager) {
        this.f406a = context;
        this.b = locationManager;
    }

    public static n a(@NonNull Context context) {
        if (d == null) {
            Context applicationContext = context.getApplicationContext();
            d = new n(applicationContext, (LocationManager) applicationContext.getSystemService(FirebaseAnalytics.Param.LOCATION));
        }
        return d;
    }

    @SuppressLint({"MissingPermission"})
    public final Location b() {
        Location c = PermissionChecker.checkSelfPermission(this.f406a, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location c2 = PermissionChecker.checkSelfPermission(this.f406a, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        return (c2 == null || c == null) ? c2 != null ? c2 : c : c2.getTime() > c.getTime() ? c2 : c;
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public final Location c(String str) {
        try {
            if (this.b.isProviderEnabled(str)) {
                return this.b.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e) {
            Log.d("TwilightManager", "Failed to get last known location", e);
            return null;
        }
    }

    public boolean d() {
        a aVar = this.c;
        if (e()) {
            return aVar.f407a;
        }
        Location b = b();
        if (b != null) {
            f(b);
            return aVar.f407a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }

    public final boolean e() {
        return this.c.b > System.currentTimeMillis();
    }

    public final void f(@NonNull Location location) {
        long j;
        a aVar = this.c;
        long currentTimeMillis = System.currentTimeMillis();
        m b = m.b();
        b.a(currentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        b.a(currentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = b.c == 1;
        long j2 = b.b;
        long j3 = b.f405a;
        b.a(currentTimeMillis + 86400000, location.getLatitude(), location.getLongitude());
        long j4 = b.b;
        if (j2 == -1 || j3 == -1) {
            j = 43200000 + currentTimeMillis;
        } else {
            j = (currentTimeMillis > j3 ? j4 + 0 : currentTimeMillis > j2 ? j3 + 0 : j2 + 0) + Constants.ONE_MIN_IN_MILLIS;
        }
        aVar.f407a = z;
        aVar.b = j;
    }
}
