package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import org.apache.commons.codec.language.Soundex;
/* loaded from: classes10.dex */
public final class zzag {
    public static final zzag zza = new zzag(null, null);

    /* renamed from: a  reason: collision with root package name */
    public final Boolean f10140a;
    public final Boolean b;

    public zzag(Boolean bool, Boolean bool2) {
        this.f10140a = bool;
        this.b = bool2;
    }

    public static Boolean a(Boolean bool, Boolean bool2) {
        if (bool == null) {
            return bool2;
        }
        if (bool2 == null) {
            return bool;
        }
        boolean z = false;
        if (bool.booleanValue() && bool2.booleanValue()) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public static final int b(Boolean bool) {
        if (bool == null) {
            return 0;
        }
        return bool.booleanValue() ? 1 : 2;
    }

    public static Boolean c(String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("granted")) {
            return Boolean.TRUE;
        }
        if (str.equals("denied")) {
            return Boolean.FALSE;
        }
        return null;
    }

    public static Boolean d(char c) {
        if (c != '0') {
            if (c != '1') {
                return null;
            }
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static final char e(Boolean bool) {
        return bool == null ? Soundex.SILENT_MARKER : bool.booleanValue() ? '1' : '0';
    }

    public static zzag zza(Bundle bundle) {
        return bundle == null ? zza : new zzag(c(bundle.getString("ad_storage")), c(bundle.getString("analytics_storage")));
    }

    public static zzag zzb(String str) {
        Boolean bool;
        if (str != null) {
            Boolean d = str.length() >= 3 ? d(str.charAt(2)) : null;
            bool = str.length() >= 4 ? d(str.charAt(3)) : null;
            r0 = d;
        } else {
            bool = null;
        }
        return new zzag(r0, bool);
    }

    public static String zzh(Bundle bundle) {
        String string = bundle.getString("ad_storage");
        if (string == null || c(string) != null) {
            String string2 = bundle.getString("analytics_storage");
            if (string2 == null || c(string2) != null) {
                return null;
            }
            return string2;
        }
        return string;
    }

    public static boolean zzl(int i, int i2) {
        return i <= i2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzag) {
            zzag zzagVar = (zzag) obj;
            return b(this.f10140a) == b(zzagVar.f10140a) && b(this.b) == b(zzagVar.b);
        }
        return false;
    }

    public final int hashCode() {
        return ((b(this.f10140a) + 527) * 31) + b(this.b);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ConsentSettings: ");
        sb.append("adStorage=");
        Boolean bool = this.f10140a;
        if (bool == null) {
            sb.append("uninitialized");
        } else {
            sb.append(true != bool.booleanValue() ? "denied" : "granted");
        }
        sb.append(", analyticsStorage=");
        Boolean bool2 = this.b;
        if (bool2 == null) {
            sb.append("uninitialized");
        } else {
            sb.append(true == bool2.booleanValue() ? "granted" : "denied");
        }
        return sb.toString();
    }

    public final zzag zzc(zzag zzagVar) {
        return new zzag(a(this.f10140a, zzagVar.f10140a), a(this.b, zzagVar.b));
    }

    public final zzag zzd(zzag zzagVar) {
        Boolean bool = this.f10140a;
        if (bool == null) {
            bool = zzagVar.f10140a;
        }
        Boolean bool2 = this.b;
        if (bool2 == null) {
            bool2 = zzagVar.b;
        }
        return new zzag(bool, bool2);
    }

    public final Boolean zze() {
        return this.f10140a;
    }

    public final Boolean zzf() {
        return this.b;
    }

    public final String zzi() {
        return "G1" + e(this.f10140a) + e(this.b);
    }

    public final boolean zzj() {
        Boolean bool = this.f10140a;
        return bool == null || bool.booleanValue();
    }

    public final boolean zzk() {
        Boolean bool = this.b;
        return bool == null || bool.booleanValue();
    }

    public final boolean zzm(zzag zzagVar) {
        Boolean bool = this.f10140a;
        Boolean bool2 = Boolean.FALSE;
        if (bool != bool2 || zzagVar.f10140a == bool2) {
            return this.b == bool2 && zzagVar.b != bool2;
        }
        return true;
    }
}
