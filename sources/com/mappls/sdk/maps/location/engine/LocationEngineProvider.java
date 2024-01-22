package com.mappls.sdk.maps.location.engine;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.GoogleApiAvailability;
/* loaded from: classes11.dex */
public final class LocationEngineProvider {
    @NonNull
    @Deprecated
    public static LocationEngine getBestLocationEngine(@NonNull Context context, boolean z) {
        return getBestLocationEngine(context);
    }

    public static LocationEngine getLocationEngine(Context context, boolean z) {
        if (GoogleLocationEngineManager.getInstance().a() == null) {
            if (z) {
                return new c(new b(context.getApplicationContext()));
            }
            return new c(new d(context.getApplicationContext()));
        } else if (z) {
            return new c(GoogleLocationEngineManager.getInstance().a());
        } else {
            return new c(new d(context.getApplicationContext()));
        }
    }

    @NonNull
    public static LocationEngine getBestLocationEngine(@NonNull Context context) {
        e.a(context, "context == null");
        boolean c = e.c("com.google.android.gms.location.LocationServices");
        if (e.c("com.google.android.gms.common.GoogleApiAvailability")) {
            c &= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0;
        }
        return getLocationEngine(context, c);
    }
}
