package com.google.android.gms.internal.clearcut;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.UserManager;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;
/* loaded from: classes7.dex */
public abstract class zzae<T> {
    public static final Object g = new Object();
    @SuppressLint({"StaticFieldLeak"})
    public static Context h;
    public static volatile Boolean i;
    public static volatile Boolean j;

    /* renamed from: a */
    public final zzao f8612a;
    public final String b;
    public final String c;
    public final T d;
    public volatile zzab e;
    public volatile SharedPreferences f;

    public zzae(zzao zzaoVar, String str, T t) {
        String str2;
        String str3;
        String str4;
        String str5;
        Uri uri;
        Uri uri2;
        this.e = null;
        this.f = null;
        str2 = zzaoVar.f8613a;
        if (str2 == null) {
            uri2 = zzaoVar.b;
            if (uri2 == null) {
                throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
            }
        }
        str3 = zzaoVar.f8613a;
        if (str3 != null) {
            uri = zzaoVar.b;
            if (uri != null) {
                throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
            }
        }
        this.f8612a = zzaoVar;
        str4 = zzaoVar.c;
        String valueOf = String.valueOf(str4);
        String valueOf2 = String.valueOf(str);
        this.c = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        str5 = zzaoVar.d;
        String valueOf3 = String.valueOf(str5);
        String valueOf4 = String.valueOf(str);
        this.b = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
        this.d = t;
    }

    public /* synthetic */ zzae(zzao zzaoVar, String str, Object obj, e eVar) {
        this(zzaoVar, str, obj);
    }

    public static <T> zzae<T> a(zzao zzaoVar, String str, T t, zzan<T> zzanVar) {
        return new h(zzaoVar, str, t, zzanVar);
    }

    public static zzae<String> b(zzao zzaoVar, String str, String str2) {
        return new g(zzaoVar, str, str2);
    }

    public static zzae<Boolean> c(zzao zzaoVar, String str, boolean z) {
        return new f(zzaoVar, str, Boolean.valueOf(z));
    }

    public static <V> V d(i<V> iVar) {
        try {
            return iVar.zzp();
        } catch (SecurityException unused) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                return iVar.zzp();
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
    }

    public static boolean e(String str, boolean z) {
        if (l()) {
            return ((Boolean) d(new i(str, false) { // from class: com.google.android.gms.internal.clearcut.d

                /* renamed from: a  reason: collision with root package name */
                public final String f8573a;
                public final boolean b = false;

                {
                    this.f8573a = str;
                }

                @Override // com.google.android.gms.internal.clearcut.i
                public final Object zzp() {
                    Boolean valueOf;
                    valueOf = Boolean.valueOf(zzy.zza(zzae.h.getContentResolver(), this.f8573a, this.b));
                    return valueOf;
                }
            })).booleanValue();
        }
        return false;
    }

    public static boolean l() {
        if (i == null) {
            Context context = h;
            if (context == null) {
                return false;
            }
            i = Boolean.valueOf(PermissionChecker.checkCallingOrSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
        }
        return i.booleanValue();
    }

    public static void maybeInit(Context context) {
        Context applicationContext;
        if (h == null) {
            synchronized (g) {
                if ((Build.VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) && (applicationContext = context.getApplicationContext()) != null) {
                    context = applicationContext;
                }
                if (h != context) {
                    i = null;
                }
                h = context;
            }
        }
    }

    public final T get() {
        boolean z;
        if (h != null) {
            z = this.f8612a.f;
            if (z) {
                T k = k();
                if (k != null) {
                    return k;
                }
                T j2 = j();
                if (j2 != null) {
                    return j2;
                }
            } else {
                T j3 = j();
                if (j3 != null) {
                    return j3;
                }
                T k2 = k();
                if (k2 != null) {
                    return k2;
                }
            }
            return this.d;
        }
        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
    }

    @Nullable
    @TargetApi(24)
    public final T j() {
        Uri uri;
        String str;
        boolean z;
        String str2;
        Uri uri2;
        if (e("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false)) {
            String valueOf = String.valueOf(this.b);
            Log.w("PhenotypeFlag", valueOf.length() != 0 ? "Bypass reading Phenotype values for flag: ".concat(valueOf) : new String("Bypass reading Phenotype values for flag: "));
        } else {
            uri = this.f8612a.b;
            if (uri != null) {
                if (this.e == null) {
                    ContentResolver contentResolver = h.getContentResolver();
                    uri2 = this.f8612a.b;
                    this.e = zzab.zza(contentResolver, uri2);
                }
                String str3 = (String) d(new i(this, this.e) { // from class: com.google.android.gms.internal.clearcut.b

                    /* renamed from: a  reason: collision with root package name */
                    public final zzae f8569a;
                    public final zzab b;

                    {
                        this.f8569a = this;
                        this.b = r2;
                    }

                    @Override // com.google.android.gms.internal.clearcut.i
                    public final Object zzp() {
                        return this.b.zzg().get(this.f8569a.b);
                    }
                });
                if (str3 != null) {
                    return zzb(str3);
                }
            } else {
                str = this.f8612a.f8613a;
                if (str != null) {
                    if (Build.VERSION.SDK_INT < 24 || h.isDeviceProtectedStorage()) {
                        z = true;
                    } else {
                        if (j == null || !j.booleanValue()) {
                            j = Boolean.valueOf(((UserManager) h.getSystemService(UserManager.class)).isUserUnlocked());
                        }
                        z = j.booleanValue();
                    }
                    if (!z) {
                        return null;
                    }
                    if (this.f == null) {
                        Context context = h;
                        str2 = this.f8612a.f8613a;
                        this.f = context.getSharedPreferences(str2, 0);
                    }
                    SharedPreferences sharedPreferences = this.f;
                    if (sharedPreferences.contains(this.b)) {
                        return zza(sharedPreferences);
                    }
                }
            }
        }
        return null;
    }

    @Nullable
    public final T k() {
        boolean z;
        String str;
        z = this.f8612a.e;
        if (z || !l() || (str = (String) d(new i(this) { // from class: com.google.android.gms.internal.clearcut.c

            /* renamed from: a  reason: collision with root package name */
            public final zzae f8571a;

            {
                this.f8571a = this;
            }

            @Override // com.google.android.gms.internal.clearcut.i
            public final Object zzp() {
                return this.f8571a.m();
            }
        })) == null) {
            return null;
        }
        return zzb(str);
    }

    public final /* synthetic */ String m() {
        return zzy.zza(h.getContentResolver(), this.c, (String) null);
    }

    public abstract T zza(SharedPreferences sharedPreferences);

    public abstract T zzb(String str);
}
