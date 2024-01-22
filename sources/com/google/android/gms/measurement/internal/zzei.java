package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.GuardedBy;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
/* loaded from: classes10.dex */
public final class zzei extends x0 {
    public char b;
    public long c;
    @GuardedBy("this")
    public String d;
    public final zzeg e;
    public final zzeg f;
    public final zzeg g;
    public final zzeg h;
    public final zzeg i;
    public final zzeg j;
    public final zzeg k;
    public final zzeg l;
    public final zzeg m;

    public zzei(zzfs zzfsVar) {
        super(zzfsVar);
        this.b = (char) 0;
        this.c = -1L;
        this.e = new zzeg(this, 6, false, false);
        this.f = new zzeg(this, 6, true, false);
        this.g = new zzeg(this, 6, false, true);
        this.h = new zzeg(this, 5, false, false);
        this.i = new zzeg(this, 5, true, false);
        this.j = new zzeg(this, 5, false, true);
        this.k = new zzeg(this, 4, false, false);
        this.l = new zzeg(this, 3, false, false);
        this.m = new zzeg(this, 2, false, false);
    }

    public static String d(boolean z, String str, Object obj, Object obj2, Object obj3) {
        String str2 = "";
        if (str == null) {
            str = "";
        }
        String e = e(z, obj);
        String e2 = e(z, obj2);
        String e3 = e(z, obj3);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        String str3 = ", ";
        if (!TextUtils.isEmpty(e)) {
            sb.append(str2);
            sb.append(e);
            str2 = ", ";
        }
        if (TextUtils.isEmpty(e2)) {
            str3 = str2;
        } else {
            sb.append(str2);
            sb.append(e2);
        }
        if (!TextUtils.isEmpty(e3)) {
            sb.append(str3);
            sb.append(e3);
        }
        return sb.toString();
    }

    @VisibleForTesting
    public static String e(boolean z, Object obj) {
        String str;
        String className;
        if (obj == null) {
            return "";
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf(((Integer) obj).intValue());
        }
        int i = 0;
        if (obj instanceof Long) {
            if (!z) {
                return String.valueOf(obj);
            }
            Long l = (Long) obj;
            if (Math.abs(l.longValue()) < 100) {
                return String.valueOf(obj);
            }
            String str2 = String.valueOf(obj).charAt(0) == '-' ? "-" : "";
            String valueOf = String.valueOf(Math.abs(l.longValue()));
            long round = Math.round(Math.pow(10.0d, valueOf.length() - 1));
            long round2 = Math.round(Math.pow(10.0d, valueOf.length()) - 1.0d);
            StringBuilder sb = new StringBuilder(str2.length() + 43 + str2.length());
            sb.append(str2);
            sb.append(round);
            sb.append("...");
            sb.append(str2);
            sb.append(round2);
            return sb.toString();
        } else if (obj instanceof Boolean) {
            return String.valueOf(obj);
        } else {
            if (obj instanceof Throwable) {
                Throwable th = (Throwable) obj;
                StringBuilder sb2 = new StringBuilder(z ? th.getClass().getName() : th.toString());
                String h = h(zzfs.class.getCanonicalName());
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTrace[i];
                    if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null && h(className).equals(h)) {
                        sb2.append(": ");
                        sb2.append(stackTraceElement);
                        break;
                    }
                    i++;
                }
                return sb2.toString();
            } else if (!(obj instanceof p)) {
                return z ? "-" : String.valueOf(obj);
            } else {
                str = ((p) obj).f10128a;
                return str;
            }
        }
    }

    public static String h(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
    }

    public static Object zzn(String str) {
        if (str == null) {
            return null;
        }
        return new p(str);
    }

    public final zzeg zzc() {
        return this.l;
    }

    public final zzeg zzd() {
        return this.e;
    }

    public final zzeg zze() {
        return this.g;
    }

    @Override // com.google.android.gms.measurement.internal.x0
    public final boolean zzf() {
        return false;
    }

    public final zzeg zzh() {
        return this.f;
    }

    public final zzeg zzi() {
        return this.k;
    }

    public final zzeg zzj() {
        return this.m;
    }

    public final zzeg zzk() {
        return this.h;
    }

    public final zzeg zzl() {
        return this.j;
    }

    public final zzeg zzm() {
        return this.i;
    }

    @VisibleForTesting
    @EnsuresNonNull({"logTagDoNotUseDirectly"})
    public final String zzq() {
        String str;
        synchronized (this) {
            if (this.d == null) {
                if (this.zzs.zzy() != null) {
                    this.d = this.zzs.zzy();
                } else {
                    this.d = this.zzs.zzf().e();
                }
            }
            Preconditions.checkNotNull(this.d);
            str = this.d;
        }
        return str;
    }

    public final void zzt(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && Log.isLoggable(zzq(), i)) {
            Log.println(i, zzq(), d(false, str, obj, obj2, obj3));
        }
        if (z2 || i < 5) {
            return;
        }
        Preconditions.checkNotNull(str);
        zzfp i2 = this.zzs.i();
        if (i2 == null) {
            Log.println(6, zzq(), "Scheduler not set. Not logging error/warn");
        } else if (!i2.a()) {
            Log.println(6, zzq(), "Scheduler not initialized. Not logging error/warn");
        } else {
            if (i >= 9) {
                i = 8;
            }
            i2.zzp(new o(this, i, str, obj, obj2, obj3));
        }
    }
}
