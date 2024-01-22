package com.google.android.gms.phenotype;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.UserManager;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.phenotype.zzf;
import javax.annotation.Nullable;
@KeepForSdk
@Deprecated
/* loaded from: classes10.dex */
public abstract class PhenotypeFlag<T> {
    public static final Object e = new Object();
    @SuppressLint({"StaticFieldLeak"})
    public static Context f;
    public static Boolean g;

    /* renamed from: a  reason: collision with root package name */
    public final Factory f10163a;
    public final String b;
    public final String c;
    public final T d;

    @KeepForSdk
    /* loaded from: classes10.dex */
    public static class Factory {

        /* renamed from: a  reason: collision with root package name */
        public final String f10164a;
        public final Uri b;
        public final String c;
        public final String d;
        public final boolean e;
        public final boolean f;

        @KeepForSdk
        public Factory(Uri uri) {
            this(null, uri, "", "", false, false);
        }

        public Factory(String str, Uri uri, String str2, String str3, boolean z, boolean z2) {
            this.f10164a = str;
            this.b = uri;
            this.c = str2;
            this.d = str3;
            this.e = z;
            this.f = z2;
        }

        @KeepForSdk
        public PhenotypeFlag<String> createFlag(String str, String str2) {
            return PhenotypeFlag.a(this, str, str2);
        }

        @KeepForSdk
        public Factory withGservicePrefix(String str) {
            boolean z = this.e;
            if (z) {
                throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
            }
            return new Factory(this.f10164a, this.b, str, this.d, z, this.f);
        }

        @KeepForSdk
        public Factory withPhenotypePrefix(String str) {
            return new Factory(this.f10164a, this.b, this.c, str, this.e, this.f);
        }
    }

    /* loaded from: classes10.dex */
    public interface a<V> {
        V zzh();
    }

    public PhenotypeFlag(Factory factory, String str, T t) {
        if (factory.f10164a == null && factory.b == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        if (factory.f10164a != null && factory.b != null) {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
        this.f10163a = factory;
        String valueOf = String.valueOf(factory.c);
        String valueOf2 = String.valueOf(str);
        this.c = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        String valueOf3 = String.valueOf(factory.d);
        String valueOf4 = String.valueOf(str);
        this.b = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
        this.d = t;
    }

    public /* synthetic */ PhenotypeFlag(Factory factory, String str, Object obj, k kVar) {
        this(factory, str, obj);
    }

    public static PhenotypeFlag<String> a(Factory factory, String str, String str2) {
        return new l(factory, str, str2);
    }

    public static <V> V b(a<V> aVar) {
        try {
            return aVar.zzh();
        } catch (SecurityException unused) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                return aVar.zzh();
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
    }

    public static boolean c(final String str, boolean z) {
        if (h()) {
            return ((Boolean) b(new a(str, false) { // from class: com.google.android.gms.phenotype.j

                /* renamed from: a  reason: collision with root package name */
                public final String f10168a;
                public final boolean b = false;

                {
                    this.f10168a = str;
                }

                @Override // com.google.android.gms.phenotype.PhenotypeFlag.a
                public final Object zzh() {
                    Boolean valueOf;
                    valueOf = Boolean.valueOf(zzf.zza(PhenotypeFlag.f.getContentResolver(), this.f10168a, this.b));
                    return valueOf;
                }
            })).booleanValue();
        }
        return false;
    }

    public static boolean h() {
        if (g == null) {
            Context context = f;
            if (context == null) {
                return false;
            }
            g = Boolean.valueOf(PermissionChecker.checkCallingOrSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
        }
        return g.booleanValue();
    }

    @KeepForSdk
    public static void maybeInit(Context context) {
        Context applicationContext;
        com.google.android.gms.internal.phenotype.zzh.maybeInit(context);
        if (f == null) {
            com.google.android.gms.internal.phenotype.zzh.init(context);
            synchronized (e) {
                if ((Build.VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) && (applicationContext = context.getApplicationContext()) != null) {
                    context = applicationContext;
                }
                if (f != context) {
                    g = null;
                }
                f = context;
            }
        }
    }

    @Nullable
    @TargetApi(24)
    public final T f() {
        if (c("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false)) {
            String valueOf = String.valueOf(this.b);
            Log.w("PhenotypeFlag", valueOf.length() != 0 ? "Bypass reading Phenotype values for flag: ".concat(valueOf) : new String("Bypass reading Phenotype values for flag: "));
        } else if (this.f10163a.b != null) {
            final zza zza = zza.zza(f.getContentResolver(), this.f10163a.b);
            String str = (String) b(new a(this, zza) { // from class: com.google.android.gms.phenotype.h

                /* renamed from: a  reason: collision with root package name */
                public final PhenotypeFlag f10166a;
                public final zza b;

                {
                    this.f10166a = this;
                    this.b = zza;
                }

                @Override // com.google.android.gms.phenotype.PhenotypeFlag.a
                public final Object zzh() {
                    return this.b.zza().get(this.f10166a.b);
                }
            });
            if (str != null) {
                return zza(str);
            }
        } else if (this.f10163a.f10164a == null || !(Build.VERSION.SDK_INT < 24 || f.isDeviceProtectedStorage() || ((UserManager) f.getSystemService(UserManager.class)).isUserUnlocked())) {
            return null;
        } else {
            SharedPreferences sharedPreferences = f.getSharedPreferences(this.f10163a.f10164a, 0);
            if (sharedPreferences.contains(this.b)) {
                return zza(sharedPreferences);
            }
        }
        return null;
    }

    @Nullable
    public final T g() {
        String str;
        if (this.f10163a.e || !h() || (str = (String) b(new a(this) { // from class: com.google.android.gms.phenotype.i

            /* renamed from: a  reason: collision with root package name */
            public final PhenotypeFlag f10167a;

            {
                this.f10167a = this;
            }

            @Override // com.google.android.gms.phenotype.PhenotypeFlag.a
            public final Object zzh() {
                return this.f10167a.i();
            }
        })) == null) {
            return null;
        }
        return zza(str);
    }

    @KeepForSdk
    public T get() {
        if (f != null) {
            if (this.f10163a.f) {
                T g2 = g();
                if (g2 != null) {
                    return g2;
                }
                T f2 = f();
                if (f2 != null) {
                    return f2;
                }
            } else {
                T f3 = f();
                if (f3 != null) {
                    return f3;
                }
                T g3 = g();
                if (g3 != null) {
                    return g3;
                }
            }
            return this.d;
        }
        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
    }

    public final /* synthetic */ String i() {
        return zzf.zza(f.getContentResolver(), this.c, (String) null);
    }

    public abstract T zza(SharedPreferences sharedPreferences);

    public abstract T zza(String str);
}
