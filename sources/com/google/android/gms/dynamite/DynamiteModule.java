package com.google.android.gms.dynamite;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import dalvik.system.DelegateLastClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.concurrent.GuardedBy;
@KeepForSdk
/* loaded from: classes6.dex */
public final class DynamiteModule {
    @KeepForSdk
    public static final int LOCAL = -1;
    @KeepForSdk
    public static final int NONE = 0;
    @KeepForSdk
    public static final int NO_SELECTION = 0;
    @KeepForSdk
    public static final int REMOTE = 1;
    @Nullable
    @GuardedBy("DynamiteModule.class")
    public static Boolean b = null;
    @Nullable
    @GuardedBy("DynamiteModule.class")
    public static String c = null;
    @GuardedBy("DynamiteModule.class")
    public static boolean d = false;
    @GuardedBy("DynamiteModule.class")
    public static int e = -1;
    @Nullable
    @GuardedBy("DynamiteModule.class")
    public static Boolean f;
    @Nullable
    @GuardedBy("DynamiteModule.class")
    public static zzq j;
    @Nullable
    @GuardedBy("DynamiteModule.class")
    public static zzr k;

    /* renamed from: a  reason: collision with root package name */
    public final Context f8390a;
    public static final ThreadLocal g = new ThreadLocal();
    public static final ThreadLocal h = new c();
    public static final VersionPolicy.IVersions i = new d();
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE = new e();
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_LOCAL = new f();
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new g();
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new h();
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new i();
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new j();
    @NonNull
    public static final VersionPolicy zza = new k();

    @DynamiteApi
    /* loaded from: classes6.dex */
    public static class DynamiteLoaderClassLoader {
        @Nullable
        @GuardedBy("DynamiteLoaderClassLoader.class")
        public static ClassLoader sClassLoader;
    }

    @KeepForSdk
    /* loaded from: classes6.dex */
    public static class LoadingException extends Exception {
        public /* synthetic */ LoadingException(String str, zzp zzpVar) {
            super(str);
        }

        public /* synthetic */ LoadingException(String str, Throwable th, zzp zzpVar) {
            super(str, th);
        }
    }

    /* loaded from: classes6.dex */
    public interface VersionPolicy {

        @KeepForSdk
        /* loaded from: classes6.dex */
        public interface IVersions {
            int zza(@NonNull Context context, @NonNull String str);

            int zzb(@NonNull Context context, @NonNull String str, boolean z) throws LoadingException;
        }

        @KeepForSdk
        /* loaded from: classes6.dex */
        public static class SelectionResult {
            @KeepForSdk
            public int localVersion = 0;
            @KeepForSdk
            public int remoteVersion = 0;
            @KeepForSdk
            public int selection = 0;
        }

        @NonNull
        @KeepForSdk
        SelectionResult selectModule(@NonNull Context context, @NonNull String str, @NonNull IVersions iVersions) throws LoadingException;
    }

    public DynamiteModule(Context context) {
        Preconditions.checkNotNull(context);
        this.f8390a = context;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x009f, code lost:
        r10.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00dc  */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a(android.content.Context r10, java.lang.String r11, boolean r12, boolean r13) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            Method dump skipped, instructions count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.a(android.content.Context, java.lang.String, boolean, boolean):int");
    }

    public static DynamiteModule b(Context context, String str) {
        Log.i("DynamiteModule", "Selected local version of ".concat(String.valueOf(str)));
        return new DynamiteModule(context);
    }

    @GuardedBy("DynamiteModule.class")
    public static void c(ClassLoader classLoader) throws LoadingException {
        zzr zzrVar;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzrVar = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (queryLocalInterface instanceof zzr) {
                    zzrVar = (zzr) queryLocalInterface;
                } else {
                    zzrVar = new zzr(iBinder);
                }
            }
            k = zzrVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
            throw new LoadingException("Failed to instantiate dynamite loader", e2, null);
        }
    }

    public static boolean d(Cursor cursor) {
        l lVar = (l) g.get();
        if (lVar == null || lVar.f8391a != null) {
            return false;
        }
        lVar.f8391a = cursor;
        return true;
    }

    @GuardedBy("DynamiteModule.class")
    public static boolean e(Context context) {
        ApplicationInfo applicationInfo;
        Boolean bool = Boolean.TRUE;
        if (bool.equals(null) || bool.equals(f)) {
            return true;
        }
        boolean z = false;
        if (f == null) {
            ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.chimera", 0);
            if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 10000000) == 0 && resolveContentProvider != null && "com.google.android.gms".equals(resolveContentProvider.packageName)) {
                z = true;
            }
            Boolean valueOf = Boolean.valueOf(z);
            f = valueOf;
            z = valueOf.booleanValue();
            if (z && (applicationInfo = resolveContentProvider.applicationInfo) != null && (applicationInfo.flags & 129) == 0) {
                Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
                d = true;
            }
        }
        if (!z) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return z;
    }

    @Nullable
    public static zzq f(Context context) {
        zzq zzqVar;
        synchronized (DynamiteModule.class) {
            zzq zzqVar2 = j;
            if (zzqVar2 != null) {
                return zzqVar2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzqVar = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzqVar = queryLocalInterface instanceof zzq ? (zzq) queryLocalInterface : new zzq(iBinder);
                }
                if (zzqVar != null) {
                    j = zzqVar;
                    return zzqVar;
                }
            } catch (Exception e2) {
                Log.e("DynamiteModule", "Failed to load IDynamiteLoader from GmsCore: " + e2.getMessage());
            }
            return null;
        }
    }

    @KeepForSdk
    public static int getLocalVersion(@NonNull Context context, @NonNull String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            Class<?> loadClass = classLoader.loadClass("com.google.android.gms.dynamite.descriptors." + str + ".ModuleDescriptor");
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (!Objects.equal(declaredField.get(null), str)) {
                String valueOf = String.valueOf(declaredField.get(null));
                Log.e("DynamiteModule", "Module descriptor id '" + valueOf + "' didn't match expected id '" + str + "'");
                return 0;
            }
            return declaredField2.getInt(null);
        } catch (ClassNotFoundException unused) {
            Log.w("DynamiteModule", "Local module descriptor class for " + str + " not found.");
            return 0;
        } catch (Exception e2) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: ".concat(String.valueOf(e2.getMessage())));
            return 0;
        }
    }

    @KeepForSdk
    public static int getRemoteVersion(@NonNull Context context, @NonNull String str) {
        return zza(context, str, false);
    }

    @NonNull
    @ResultIgnorabilityUnspecified
    @KeepForSdk
    public static DynamiteModule load(@NonNull Context context, @NonNull VersionPolicy versionPolicy, @NonNull String str) throws LoadingException {
        int i2;
        IObjectWrapper zzh;
        DynamiteModule dynamiteModule;
        zzr zzrVar;
        Boolean valueOf;
        IObjectWrapper zze;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            ThreadLocal threadLocal = g;
            l lVar = (l) threadLocal.get();
            l lVar2 = new l(null);
            threadLocal.set(lVar2);
            ThreadLocal threadLocal2 = h;
            long longValue = ((Long) threadLocal2.get()).longValue();
            try {
                threadLocal2.set(Long.valueOf(SystemClock.elapsedRealtime()));
                VersionPolicy.SelectionResult selectModule = versionPolicy.selectModule(context, str, i);
                Log.i("DynamiteModule", "Considering local module " + str + ":" + selectModule.localVersion + " and remote module " + str + ":" + selectModule.remoteVersion);
                int i3 = selectModule.selection;
                if (i3 != 0) {
                    if (i3 == -1) {
                        if (selectModule.localVersion != 0) {
                            i3 = -1;
                        }
                    }
                    if (i3 != 1 || selectModule.remoteVersion != 0) {
                        if (i3 == -1) {
                            DynamiteModule b2 = b(applicationContext, str);
                            if (longValue == 0) {
                                threadLocal2.remove();
                            } else {
                                threadLocal2.set(Long.valueOf(longValue));
                            }
                            Cursor cursor = lVar2.f8391a;
                            if (cursor != null) {
                                cursor.close();
                            }
                            threadLocal.set(lVar);
                            return b2;
                        } else if (i3 == 1) {
                            try {
                                try {
                                    int i4 = selectModule.remoteVersion;
                                    try {
                                        try {
                                            try {
                                                synchronized (DynamiteModule.class) {
                                                    try {
                                                        if (e(context)) {
                                                            Boolean bool = b;
                                                            if (bool != null) {
                                                                if (bool.booleanValue()) {
                                                                    Log.i("DynamiteModule", "Selected remote version of " + str + ", version >= " + i4);
                                                                    synchronized (DynamiteModule.class) {
                                                                        try {
                                                                            zzrVar = k;
                                                                        } catch (Throwable th) {
                                                                            th = th;
                                                                            while (true) {
                                                                                try {
                                                                                    break;
                                                                                } catch (Throwable th2) {
                                                                                    th = th2;
                                                                                }
                                                                            }
                                                                            throw th;
                                                                        }
                                                                    }
                                                                    if (zzrVar != null) {
                                                                        l lVar3 = (l) threadLocal.get();
                                                                        if (lVar3 != null && lVar3.f8391a != null) {
                                                                            Context applicationContext2 = context.getApplicationContext();
                                                                            Cursor cursor2 = lVar3.f8391a;
                                                                            ObjectWrapper.wrap(null);
                                                                            synchronized (DynamiteModule.class) {
                                                                                valueOf = Boolean.valueOf(e >= 2);
                                                                            }
                                                                            if (valueOf.booleanValue()) {
                                                                                Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                                                                                zze = zzrVar.zzf(ObjectWrapper.wrap(applicationContext2), str, i4, ObjectWrapper.wrap(cursor2));
                                                                            } else {
                                                                                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                                                                                zze = zzrVar.zze(ObjectWrapper.wrap(applicationContext2), str, i4, ObjectWrapper.wrap(cursor2));
                                                                            }
                                                                            Context context2 = (Context) ObjectWrapper.unwrap(zze);
                                                                            if (context2 != null) {
                                                                                dynamiteModule = new DynamiteModule(context2);
                                                                            } else {
                                                                                throw new LoadingException("Failed to get module context", null);
                                                                            }
                                                                        } else {
                                                                            throw new LoadingException("No result cursor", null);
                                                                        }
                                                                    } else {
                                                                        throw new LoadingException("DynamiteLoaderV2 was not cached.", null);
                                                                    }
                                                                } else {
                                                                    Log.i("DynamiteModule", "Selected remote version of " + str + ", version >= " + i4);
                                                                    zzq f2 = f(context);
                                                                    if (f2 != null) {
                                                                        int zze2 = f2.zze();
                                                                        if (zze2 >= 3) {
                                                                            l lVar4 = (l) threadLocal.get();
                                                                            if (lVar4 != null) {
                                                                                zzh = f2.zzi(ObjectWrapper.wrap(context), str, i4, ObjectWrapper.wrap(lVar4.f8391a));
                                                                            } else {
                                                                                throw new LoadingException("No cached result cursor holder", null);
                                                                            }
                                                                        } else if (zze2 == 2) {
                                                                            Log.w("DynamiteModule", "IDynamite loader version = 2");
                                                                            zzh = f2.zzj(ObjectWrapper.wrap(context), str, i4);
                                                                        } else {
                                                                            Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                                                                            zzh = f2.zzh(ObjectWrapper.wrap(context), str, i4);
                                                                        }
                                                                        Object unwrap = ObjectWrapper.unwrap(zzh);
                                                                        if (unwrap != null) {
                                                                            dynamiteModule = new DynamiteModule((Context) unwrap);
                                                                        } else {
                                                                            throw new LoadingException("Failed to load remote module.", null);
                                                                        }
                                                                    } else {
                                                                        throw new LoadingException("Failed to create IDynamiteLoader.", null);
                                                                    }
                                                                }
                                                                if (longValue == 0) {
                                                                    threadLocal2.remove();
                                                                } else {
                                                                    threadLocal2.set(Long.valueOf(longValue));
                                                                }
                                                                Cursor cursor3 = lVar2.f8391a;
                                                                if (cursor3 != null) {
                                                                    cursor3.close();
                                                                }
                                                                threadLocal.set(lVar);
                                                                return dynamiteModule;
                                                            }
                                                            throw new LoadingException("Failed to determine which loading route to use.", null);
                                                        }
                                                        throw new LoadingException("Remote loading disabled", null);
                                                    } catch (Throwable th3) {
                                                        th = th3;
                                                        throw th;
                                                    }
                                                }
                                            } catch (Throwable th4) {
                                                th = th4;
                                            }
                                        } catch (RemoteException e2) {
                                            e = e2;
                                            throw new LoadingException("Failed to load remote module.", e, null);
                                        } catch (LoadingException e3) {
                                            throw e3;
                                        } catch (Throwable th5) {
                                            th = th5;
                                            CrashUtils.addDynamiteErrorToDropBox(context, th);
                                            throw new LoadingException("Failed to load remote module.", th, null);
                                        }
                                    } catch (RemoteException e4) {
                                        e = e4;
                                        throw new LoadingException("Failed to load remote module.", e, null);
                                    } catch (LoadingException e5) {
                                        throw e5;
                                    } catch (Throwable th6) {
                                        th = th6;
                                        CrashUtils.addDynamiteErrorToDropBox(context, th);
                                        throw new LoadingException("Failed to load remote module.", th, null);
                                    }
                                } catch (LoadingException e6) {
                                    e = e6;
                                    Log.w("DynamiteModule", "Failed to load remote module: " + e.getMessage());
                                    i2 = selectModule.localVersion;
                                    if (i2 == 0 && versionPolicy.selectModule(context, str, new m(i2, 0)).selection == -1) {
                                        return b(applicationContext, str);
                                    }
                                    throw new LoadingException("Remote load failed. No local fallback found.", e, null);
                                }
                            } catch (LoadingException e7) {
                                e = e7;
                                Log.w("DynamiteModule", "Failed to load remote module: " + e.getMessage());
                                i2 = selectModule.localVersion;
                                if (i2 == 0) {
                                }
                                throw new LoadingException("Remote load failed. No local fallback found.", e, null);
                            }
                        } else {
                            throw new LoadingException("VersionPolicy returned invalid code:" + i3, null);
                        }
                    }
                }
                throw new LoadingException("No acceptable module " + str + " found. Local version is " + selectModule.localVersion + " and remote version is " + selectModule.remoteVersion + ".", null);
            } finally {
                if (longValue == 0) {
                    h.remove();
                } else {
                    h.set(Long.valueOf(longValue));
                }
                Cursor cursor4 = lVar2.f8391a;
                if (cursor4 != null) {
                    cursor4.close();
                }
                g.set(lVar);
            }
        }
        throw new LoadingException("null application Context", null);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:109:0x01b1 -> B:128:0x01b6). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:110:0x01b3 -> B:128:0x01b6). Please submit an issue!!! */
    public static int zza(@NonNull Context context, @NonNull String str, boolean z) {
        Field declaredField;
        Throwable th;
        RemoteException e2;
        Cursor cursor;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool = b;
                Cursor cursor2 = null;
                int i2 = 0;
                if (bool == null) {
                    try {
                        declaredField = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName()).getDeclaredField("sClassLoader");
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e3) {
                        String obj = e3.toString();
                        Log.w("DynamiteModule", "Failed to load module via V2: " + obj);
                        bool = Boolean.FALSE;
                    }
                    synchronized (declaredField.getDeclaringClass()) {
                        ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                        if (classLoader == ClassLoader.getSystemClassLoader()) {
                            bool = Boolean.FALSE;
                        } else if (classLoader != null) {
                            try {
                                c(classLoader);
                            } catch (LoadingException unused) {
                            }
                            bool = Boolean.TRUE;
                        } else if (!e(context)) {
                            return 0;
                        } else {
                            if (!d) {
                                Boolean bool2 = Boolean.TRUE;
                                if (!bool2.equals(null)) {
                                    try {
                                        int a2 = a(context, str, z, true);
                                        String str2 = c;
                                        if (str2 != null && !str2.isEmpty()) {
                                            ClassLoader zza2 = zzb.zza();
                                            if (zza2 == null) {
                                                if (Build.VERSION.SDK_INT >= 29) {
                                                    String str3 = c;
                                                    Preconditions.checkNotNull(str3);
                                                    zza2 = new DelegateLastClassLoader(str3, ClassLoader.getSystemClassLoader());
                                                } else {
                                                    String str4 = c;
                                                    Preconditions.checkNotNull(str4);
                                                    zza2 = new b(str4, ClassLoader.getSystemClassLoader());
                                                }
                                            }
                                            c(zza2);
                                            declaredField.set(null, zza2);
                                            b = bool2;
                                            return a2;
                                        }
                                        return a2;
                                    } catch (LoadingException unused2) {
                                        declaredField.set(null, ClassLoader.getSystemClassLoader());
                                        bool = Boolean.FALSE;
                                    }
                                }
                            }
                            declaredField.set(null, ClassLoader.getSystemClassLoader());
                            bool = Boolean.FALSE;
                        }
                        b = bool;
                    }
                }
                if (bool.booleanValue()) {
                    try {
                        return a(context, str, z, false);
                    } catch (LoadingException e4) {
                        String message = e4.getMessage();
                        Log.w("DynamiteModule", "Failed to retrieve remote module version: " + message);
                        return 0;
                    }
                }
                zzq f2 = f(context);
                try {
                    if (f2 != null) {
                        try {
                            int zze = f2.zze();
                            if (zze >= 3) {
                                l lVar = (l) g.get();
                                if (lVar != null && (cursor = lVar.f8391a) != null) {
                                    i2 = cursor.getInt(0);
                                } else {
                                    Cursor cursor3 = (Cursor) ObjectWrapper.unwrap(f2.zzk(ObjectWrapper.wrap(context), str, z, ((Long) h.get()).longValue()));
                                    if (cursor3 != null) {
                                        try {
                                            if (cursor3.moveToFirst()) {
                                                int i3 = cursor3.getInt(0);
                                                if (i3 <= 0 || !d(cursor3)) {
                                                    cursor2 = cursor3;
                                                }
                                                if (cursor2 != null) {
                                                    cursor2.close();
                                                }
                                                i2 = i3;
                                            }
                                        } catch (RemoteException e5) {
                                            e2 = e5;
                                            cursor2 = cursor3;
                                            String message2 = e2.getMessage();
                                            Log.w("DynamiteModule", "Failed to retrieve remote module version: " + message2);
                                            if (cursor2 != null) {
                                                cursor2.close();
                                            }
                                            return i2;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            cursor2 = cursor3;
                                            if (cursor2 != null) {
                                                cursor2.close();
                                            }
                                            throw th;
                                        }
                                    }
                                    Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                                    if (cursor3 != null) {
                                        cursor3.close();
                                    }
                                }
                            } else if (zze == 2) {
                                Log.w("DynamiteModule", "IDynamite loader version = 2, no high precision latency measurement.");
                                i2 = f2.zzg(ObjectWrapper.wrap(context), str, z);
                            } else {
                                Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
                                i2 = f2.zzf(ObjectWrapper.wrap(context), str, z);
                            }
                        } catch (RemoteException e6) {
                            e2 = e6;
                        }
                    }
                    return i2;
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        } catch (Throwable th4) {
            CrashUtils.addDynamiteErrorToDropBox(context, th4);
            throw th4;
        }
    }

    @NonNull
    @ResultIgnorabilityUnspecified
    @KeepForSdk
    public Context getModuleContext() {
        return this.f8390a;
    }

    @NonNull
    @KeepForSdk
    public IBinder instantiate(@NonNull String str) throws LoadingException {
        try {
            return (IBinder) this.f8390a.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e2) {
            throw new LoadingException("Failed to instantiate module class: ".concat(String.valueOf(str)), e2, null);
        }
    }
}
