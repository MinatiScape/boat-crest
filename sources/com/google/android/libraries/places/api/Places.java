package com.google.android.libraries.places.api;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.internal.zzcv;
import com.google.android.libraries.places.internal.zzdb;
import com.google.android.libraries.places.internal.zzdj;
import com.google.android.libraries.places.internal.zzdk;
import com.google.android.libraries.places.internal.zzft;
import java.util.Locale;
/* loaded from: classes10.dex */
public final class Places {
    private static final zzdb zza = new zzdb();

    private Places() {
    }

    @NonNull
    public static synchronized PlacesClient createClient(@NonNull Context context) {
        PlacesClient zza2;
        synchronized (Places.class) {
            try {
                zzft.zza(context, "Context must not be null.");
                zza2 = zza(context, zzdj.zza(context).zzb());
            } catch (Error | RuntimeException e) {
                zzdk.zza(e);
                throw e;
            }
        }
        return zza2;
    }

    public static synchronized void deinitialize() {
        synchronized (Places.class) {
            zza.zze();
        }
    }

    public static void initialize(@NonNull Context context, @NonNull String str) {
        try {
            zza(context, str, null, false);
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    public static synchronized boolean isInitialized() {
        boolean zzd;
        synchronized (Places.class) {
            try {
                zzd = zza.zzd();
            } catch (Error | RuntimeException e) {
                zzdk.zza(e);
                throw e;
            }
        }
        return zzd;
    }

    private static synchronized void zza(@NonNull Context context, @NonNull String str, @Nullable Locale locale, boolean z) {
        synchronized (Places.class) {
            try {
                zzft.zza(context, "Application context must not be null.");
                zzft.zza(str, "API Key must not be null.");
                zzft.zza(!str.isEmpty(), "API Key must not be empty.");
                zzdk.zza(context.getApplicationContext(), false);
                zza.zza(str, locale, false);
            } catch (Error | RuntimeException e) {
                zzdk.zza(e);
                throw e;
            }
        }
    }

    public static synchronized void initialize(@NonNull Context context, @NonNull String str, @Nullable Locale locale) {
        synchronized (Places.class) {
            try {
                zza(context, str, locale, false);
            } catch (Error | RuntimeException e) {
                zzdk.zza(e);
                throw e;
            }
        }
    }

    @NonNull
    public static synchronized PlacesClient zza(@NonNull Context context, @NonNull zzdj zzdjVar) {
        PlacesClient zzb;
        synchronized (Places.class) {
            try {
                zzft.zza(context, "Context must not be null.");
                zzft.zza(zzdjVar, "ClientProfile must not be null.");
                zzft.zzb(isInitialized(), "Places must be initialized first.");
                zzb = zzcv.zza().zza(context).zza(zza).zza(zzdjVar).zza().zzb();
            } catch (Error | RuntimeException e) {
                zzdk.zza(e);
                throw e;
            }
        }
        return zzb;
    }
}
