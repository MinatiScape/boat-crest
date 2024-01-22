package com.google.android.gms.maps;

import android.content.Context;
import androidx.annotation.NonNull;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public final class MapsInitializer {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10042a = "MapsInitializer";
    @GuardedBy("MapsInitializer.class")
    public static boolean b = false;
    @GuardedBy("MapsInitializer.class")
    public static Renderer c = Renderer.LEGACY;

    /* loaded from: classes10.dex */
    public enum Renderer {
        LEGACY,
        LATEST
    }

    public static synchronized int initialize(@NonNull Context context) {
        int initialize;
        synchronized (MapsInitializer.class) {
            initialize = initialize(context, null, null);
        }
        return initialize;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(16:11|12|14|15|16|17|(10:19|(1:(1:22))|23|24|(1:26)|27|28|(1:30)|31|32)|36|23|24|(0)|27|28|(0)|31|32) */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005b, code lost:
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005c, code lost:
        android.util.Log.e(com.google.android.gms.maps.MapsInitializer.f10042a, "Failed to retrieve renderer type or log initialization.", r5);
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004f A[Catch: RemoteException -> 0x005b, all -> 0x0089, TryCatch #3 {RemoteException -> 0x005b, blocks: (B:21:0x0049, B:23:0x004f, B:24:0x0053), top: B:48:0x0049, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0076 A[Catch: all -> 0x0089, TRY_LEAVE, TryCatch #2 {, blocks: (B:4:0x0003, B:7:0x001e, B:10:0x0025, B:11:0x0029, B:13:0x0038, B:15:0x003d, B:21:0x0049, B:23:0x004f, B:24:0x0053, B:28:0x0063, B:30:0x0076, B:27:0x005c, B:34:0x007e, B:35:0x0083, B:37:0x0085), top: B:47:0x0003, inners: #0, #1, #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized int initialize(@androidx.annotation.NonNull android.content.Context r5, @androidx.annotation.Nullable com.google.android.gms.maps.MapsInitializer.Renderer r6, @androidx.annotation.Nullable com.google.android.gms.maps.OnMapsSdkInitializedCallback r7) {
        /*
            java.lang.Class<com.google.android.gms.maps.MapsInitializer> r0 = com.google.android.gms.maps.MapsInitializer.class
            monitor-enter(r0)
            java.lang.String r1 = "Context is null"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5, r1)     // Catch: java.lang.Throwable -> L89
            java.lang.String r1 = com.google.android.gms.maps.MapsInitializer.f10042a     // Catch: java.lang.Throwable -> L89
            java.lang.String r2 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L89
            java.lang.String r3 = "preferredRenderer: "
            java.lang.String r2 = r3.concat(r2)     // Catch: java.lang.Throwable -> L89
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L89
            boolean r1 = com.google.android.gms.maps.MapsInitializer.b     // Catch: java.lang.Throwable -> L89
            r2 = 0
            if (r1 == 0) goto L25
            if (r7 == 0) goto L23
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = com.google.android.gms.maps.MapsInitializer.c     // Catch: java.lang.Throwable -> L89
            r7.onMapsSdkInitialized(r5)     // Catch: java.lang.Throwable -> L89
        L23:
            monitor-exit(r0)
            return r2
        L25:
            com.google.android.gms.maps.internal.zzf r1 = com.google.android.gms.maps.internal.zzca.zza(r5, r6)     // Catch: com.google.android.gms.common.GooglePlayServicesNotAvailableException -> L84 java.lang.Throwable -> L89
            com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate r3 = r1.zze()     // Catch: android.os.RemoteException -> L7d java.lang.Throwable -> L89
            com.google.android.gms.maps.CameraUpdateFactory.zza(r3)     // Catch: android.os.RemoteException -> L7d java.lang.Throwable -> L89
            com.google.android.gms.internal.maps.zzi r3 = r1.zzj()     // Catch: android.os.RemoteException -> L7d java.lang.Throwable -> L89
            com.google.android.gms.maps.model.BitmapDescriptorFactory.zza(r3)     // Catch: android.os.RemoteException -> L7d java.lang.Throwable -> L89
            r3 = 1
            com.google.android.gms.maps.MapsInitializer.b = r3     // Catch: java.lang.Throwable -> L89
            r4 = 2
            if (r6 == 0) goto L48
            int r6 = r6.ordinal()     // Catch: java.lang.Throwable -> L89
            if (r6 == 0) goto L49
            if (r6 == r3) goto L46
            goto L48
        L46:
            r3 = r4
            goto L49
        L48:
            r3 = r2
        L49:
            int r6 = r1.zzd()     // Catch: android.os.RemoteException -> L5b java.lang.Throwable -> L89
            if (r6 != r4) goto L53
            com.google.android.gms.maps.MapsInitializer$Renderer r6 = com.google.android.gms.maps.MapsInitializer.Renderer.LATEST     // Catch: android.os.RemoteException -> L5b java.lang.Throwable -> L89
            com.google.android.gms.maps.MapsInitializer.c = r6     // Catch: android.os.RemoteException -> L5b java.lang.Throwable -> L89
        L53:
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r5)     // Catch: android.os.RemoteException -> L5b java.lang.Throwable -> L89
            r1.zzl(r5, r3)     // Catch: android.os.RemoteException -> L5b java.lang.Throwable -> L89
            goto L63
        L5b:
            r5 = move-exception
            java.lang.String r6 = com.google.android.gms.maps.MapsInitializer.f10042a     // Catch: java.lang.Throwable -> L89
            java.lang.String r1 = "Failed to retrieve renderer type or log initialization."
            android.util.Log.e(r6, r1, r5)     // Catch: java.lang.Throwable -> L89
        L63:
            java.lang.String r5 = com.google.android.gms.maps.MapsInitializer.f10042a     // Catch: java.lang.Throwable -> L89
            com.google.android.gms.maps.MapsInitializer$Renderer r6 = com.google.android.gms.maps.MapsInitializer.c     // Catch: java.lang.Throwable -> L89
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L89
            java.lang.String r1 = "loadedRenderer: "
            java.lang.String r6 = r1.concat(r6)     // Catch: java.lang.Throwable -> L89
            android.util.Log.d(r5, r6)     // Catch: java.lang.Throwable -> L89
            if (r7 == 0) goto L7b
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = com.google.android.gms.maps.MapsInitializer.c     // Catch: java.lang.Throwable -> L89
            r7.onMapsSdkInitialized(r5)     // Catch: java.lang.Throwable -> L89
        L7b:
            monitor-exit(r0)
            return r2
        L7d:
            r5 = move-exception
            com.google.android.gms.maps.model.RuntimeRemoteException r6 = new com.google.android.gms.maps.model.RuntimeRemoteException     // Catch: java.lang.Throwable -> L89
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L89
            throw r6     // Catch: java.lang.Throwable -> L89
        L84:
            r5 = move-exception
            int r5 = r5.errorCode     // Catch: java.lang.Throwable -> L89
            monitor-exit(r0)
            return r5
        L89:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.MapsInitializer.initialize(android.content.Context, com.google.android.gms.maps.MapsInitializer$Renderer, com.google.android.gms.maps.OnMapsSdkInitializedCallback):int");
    }
}
