package com.google.android.gms.internal.vision;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes10.dex */
public abstract class zzbe<T> {
    @SuppressLint({"StaticFieldLeak"})
    public static Context g;
    public static zzcu<zzcn<zzba>> h;

    /* renamed from: a  reason: collision with root package name */
    public final zzbk f10014a;
    public final String b;
    public final T c;
    public volatile int d;
    public volatile T e;
    public static final Object f = new Object();
    public static final AtomicInteger i = new AtomicInteger();

    public zzbe(zzbk zzbkVar, String str, T t) {
        this.d = -1;
        String str2 = zzbkVar.f10016a;
        if (str2 == null && zzbkVar.b == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        if (str2 != null && zzbkVar.b != null) {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
        this.f10014a = zzbkVar;
        this.b = str;
        this.c = t;
    }

    public static zzbe<Long> a(zzbk zzbkVar, String str, long j) {
        return new j(zzbkVar, str, Long.valueOf(j));
    }

    public static <T> zzbe<T> b(zzbk zzbkVar, String str, T t, zzbh<T> zzbhVar) {
        return new k(zzbkVar, str, t, zzbhVar);
    }

    public static zzbe<Boolean> c(zzbk zzbkVar, String str, boolean z) {
        return new i(zzbkVar, str, Boolean.valueOf(z));
    }

    public static void e() {
        i.incrementAndGet();
    }

    public static final /* synthetic */ zzcn h() {
        new zzaz();
        return zzaz.zzf(g);
    }

    public static void init(Context context) {
        synchronized (f) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (g != context) {
                zzaq.c();
                zzbj.d();
                e.a();
                i.incrementAndGet();
                g = context;
                h = zzcx.zza(h.h);
            }
        }
    }

    public static void maybeInit(Context context) {
        synchronized (f) {
            if (g == null) {
                init(context);
            }
        }
    }

    public abstract T d(Object obj);

    @Nullable
    public final T f() {
        d b;
        Object zzb;
        boolean z = false;
        if (!this.f10014a.g) {
            String str = (String) e.d(g).zzb("gms:phenotype:phenotype_flag:debug_bypass_phenotype");
            if (str != null && zzal.zzev.matcher(str).matches()) {
                z = true;
            }
        }
        if (!z) {
            zzbk zzbkVar = this.f10014a;
            Uri uri = zzbkVar.b;
            if (uri != null) {
                if (!zzbc.zza(g, uri)) {
                    b = null;
                } else if (this.f10014a.h) {
                    ContentResolver contentResolver = g.getContentResolver();
                    String lastPathSegment = this.f10014a.b.getLastPathSegment();
                    String packageName = g.getPackageName();
                    StringBuilder sb = new StringBuilder(String.valueOf(lastPathSegment).length() + 1 + String.valueOf(packageName).length());
                    sb.append(lastPathSegment);
                    sb.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                    sb.append(packageName);
                    b = zzaq.zza(contentResolver, zzbb.getContentProviderUri(sb.toString()));
                } else {
                    b = zzaq.zza(g.getContentResolver(), this.f10014a.b);
                }
            } else {
                b = zzbj.b(g, zzbkVar.f10016a);
            }
            if (b != null && (zzb = b.zzb(zzac())) != null) {
                return d(zzb);
            }
        } else if (Log.isLoggable("PhenotypeFlag", 3)) {
            String valueOf = String.valueOf(zzac());
            Log.d("PhenotypeFlag", valueOf.length() != 0 ? "Bypass reading Phenotype values for flag: ".concat(valueOf) : new String("Bypass reading Phenotype values for flag: "));
        }
        return null;
    }

    @Nullable
    public final T g() {
        zzcl<Context, Boolean> zzclVar;
        zzbk zzbkVar = this.f10014a;
        if (!zzbkVar.e && ((zzclVar = zzbkVar.i) == null || zzclVar.apply(g).booleanValue())) {
            e d = e.d(g);
            zzbk zzbkVar2 = this.f10014a;
            Object zzb = d.zzb(zzbkVar2.e ? null : l(zzbkVar2.c));
            if (zzb != null) {
                return d(zzb);
            }
        }
        return null;
    }

    public final T get() {
        T f2;
        int i2 = i.get();
        if (this.d < i2) {
            synchronized (this) {
                if (this.d < i2) {
                    if (g != null) {
                        if (!this.f10014a.f ? (f2 = f()) == null && (f2 = g()) == null : (f2 = g()) == null && (f2 = f()) == null) {
                            f2 = this.c;
                        }
                        zzcn<zzba> zzcnVar = h.get();
                        if (zzcnVar.isPresent()) {
                            zzbk zzbkVar = this.f10014a;
                            String zza = zzcnVar.get().zza(zzbkVar.b, zzbkVar.f10016a, zzbkVar.d, this.b);
                            f2 = zza == null ? this.c : d(zza);
                        }
                        this.e = f2;
                        this.d = i2;
                    } else {
                        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
                    }
                }
            }
        }
        return this.e;
    }

    public final String l(String str) {
        if (str == null || !str.isEmpty()) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf(this.b);
            return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        return this.b;
    }

    public final String zzac() {
        return l(this.f10014a.d);
    }

    public /* synthetic */ zzbe(zzbk zzbkVar, String str, Object obj, j jVar) {
        this(zzbkVar, str, obj);
    }
}
