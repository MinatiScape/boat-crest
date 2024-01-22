package com.google.android.libraries.places.internal;

import android.location.Location;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.RectangularBounds;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/* loaded from: classes10.dex */
public final class zzcn {
    private static final zzgn<zzp, String> zza = new zzgm().zza(zzp.NONE, "NONE").zza(zzp.PSK, "WPA_PSK").zza(zzp.EAP, "WPA_EAP").zza(zzp.OTHER, "SECURED_NONE").zza();

    public static String zza(zzgi<zzq> zzgiVar, int i) {
        zzft.zza(true, (Object) "maxLength must not be negative");
        StringBuilder sb = new StringBuilder();
        int size = zzgiVar.size();
        int i2 = 0;
        while (i2 < size) {
            zzq zzqVar = zzgiVar.get(i2);
            i2++;
            zzq zzqVar2 = zzqVar;
            String str = sb.length() > 0 ? "|" : "";
            String valueOf = String.valueOf(zzfj.zza(Constants.SEPARATOR_COMMA).zzb("=").zza(new StringBuilder(), new zzgm().zza("mac", zzqVar2.zza()).zza("strength_dbm", Integer.valueOf(zzqVar2.zzb())).zza("wifi_auth_type", zza.get(zzqVar2.zzc())).zza("is_connected", Boolean.valueOf(zzqVar2.zzd())).zza("frequency_mhz", Integer.valueOf(zzqVar2.zze())).zza().entrySet().iterator()).toString());
            String concat = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
            if (4000 < sb.length() + concat.length()) {
                break;
            }
            sb.append(concat);
        }
        return sb.toString();
    }

    @Nullable
    public static String zzb(@Nullable Location location) {
        if (location == null) {
            return null;
        }
        return zza(location.getLatitude(), location.getLongitude());
    }

    @Nullable
    public static Integer zza(@Nullable Location location) {
        if (location == null) {
            return null;
        }
        float accuracy = location.getAccuracy();
        if (!location.hasAccuracy() || accuracy <= 0.0f) {
            return null;
        }
        return Integer.valueOf(Math.round(accuracy * 100.0f));
    }

    @Nullable
    public static String zza(@Nullable LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return zza(latLng.latitude, latLng.longitude);
    }

    private static String zza(double d, double d2) {
        return String.format(Locale.US, "%.15f,%.15f", Double.valueOf(d), Double.valueOf(d2));
    }

    @Nullable
    public static String zza(List<String> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = list.iterator();
        while (true) {
            String str = null;
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            if (!TextUtils.isEmpty(next)) {
                String valueOf = String.valueOf(next.toLowerCase(Locale.US));
                str = valueOf.length() != 0 ? "country:".concat(valueOf) : new String("country:");
            }
            if (str != null) {
                if (sb.length() != 0) {
                    sb.append('|');
                }
                sb.append(str);
            }
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    @Nullable
    public static String zza(@Nullable LocationBias locationBias) {
        if (locationBias == null) {
            return null;
        }
        if (locationBias instanceof RectangularBounds) {
            return zza((RectangularBounds) locationBias);
        }
        throw new AssertionError("Unknown LocationBias type.");
    }

    @Nullable
    public static String zza(@Nullable LocationRestriction locationRestriction) {
        if (locationRestriction == null) {
            return null;
        }
        if (locationRestriction instanceof RectangularBounds) {
            return zza((RectangularBounds) locationRestriction);
        }
        throw new AssertionError("Unknown LocationRestriction type.");
    }

    private static String zza(RectangularBounds rectangularBounds) {
        LatLng southwest = rectangularBounds.getSouthwest();
        double d = southwest.latitude;
        double d2 = southwest.longitude;
        LatLng northeast = rectangularBounds.getNortheast();
        return String.format(Locale.US, "rectangle:%.15f,%.15f|%.15f,%.15f", Double.valueOf(d), Double.valueOf(d2), Double.valueOf(northeast.latitude), Double.valueOf(northeast.longitude));
    }
}
