package com.mappls.android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
/* loaded from: classes11.dex */
public class HttpService implements RemoteService {
    private static final String LOGTAG = "MapplsAnalyticsAPI.Message";
    private static final int MAX_UNAVAILABLE_HTTP_RESPONSE_CODE = 599;
    private static final int MIN_UNAVAILABLE_HTTP_RESPONSE_CODE = 500;
    private static boolean sIsMapplsAnalyticsBlocked;

    private boolean onOfflineMode(OfflineMode offlineMode) {
        if (offlineMode != null) {
            try {
                return offlineMode.isOffline();
            } catch (Exception e) {
                MPLog.v(LOGTAG, "Client State should not throw exception, will assume is not on offline mode", e);
                return false;
            }
        }
        return false;
    }

    private static byte[] slurp(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr, 0, 8192);
            if (read == -1) {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    @Override // com.mappls.android.util.RemoteService
    public void checkIsMapplsAnalyticsBlocked() {
        new Thread(new Runnable() { // from class: com.mappls.android.util.HttpService.1
            /* JADX WARN: Removed duplicated region for block: B:11:0x001f A[Catch: Exception -> 0x0026, TRY_LEAVE, TryCatch #0 {Exception -> 0x0026, blocks: (B:2:0x0000, B:4:0x000c, B:9:0x0016, B:11:0x001f), top: B:14:0x0000 }] */
            /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    r2 = this;
                    java.lang.String r0 = "www.mappls.com"
                    java.net.InetAddress r0 = java.net.InetAddress.getByName(r0)     // Catch: java.lang.Exception -> L26
                    boolean r1 = r0.isLoopbackAddress()     // Catch: java.lang.Exception -> L26
                    if (r1 != 0) goto L15
                    boolean r0 = r0.isAnyLocalAddress()     // Catch: java.lang.Exception -> L26
                    if (r0 == 0) goto L13
                    goto L15
                L13:
                    r0 = 0
                    goto L16
                L15:
                    r0 = 1
                L16:
                    com.mappls.android.util.HttpService.access$002(r0)     // Catch: java.lang.Exception -> L26
                    boolean r0 = com.mappls.android.util.HttpService.access$000()     // Catch: java.lang.Exception -> L26
                    if (r0 == 0) goto L26
                    java.lang.String r0 = "MapplsAnalyticsAPI.Message"
                    java.lang.String r1 = "AdBlocker is enabled. Won't be able to use Mappls Analytics services."
                    com.mappls.android.util.MPLog.v(r0, r1)     // Catch: java.lang.Exception -> L26
                L26:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mappls.android.util.HttpService.AnonymousClass1.run():void");
            }
        }).start();
    }

    @Override // com.mappls.android.util.RemoteService
    public boolean isOnline(Context context, OfflineMode offlineMode) {
        boolean z;
        String sb;
        if (sIsMapplsAnalyticsBlocked || onOfflineMode(offlineMode)) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                sb = "A default network has not been set so we cannot be certain whether we are offline";
                z = true;
            } else {
                boolean isConnectedOrConnecting = activeNetworkInfo.isConnectedOrConnecting();
                StringBuilder sb2 = new StringBuilder("ConnectivityManager says we ");
                sb2.append(isConnectedOrConnecting ? "are" : "are not");
                sb2.append(" online");
                z = isConnectedOrConnecting;
                sb = sb2.toString();
            }
            MPLog.v(LOGTAG, sb);
            return z;
        } catch (SecurityException unused) {
            MPLog.v(LOGTAG, "Don't have permission to check connectivity, will assume we are online");
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00fc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x012a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0138 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x012f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0020 A[SYNTHETIC] */
    @Override // com.mappls.android.util.RemoteService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] performRequest(java.lang.String r17, java.util.Map<java.lang.String, java.lang.Object> r18, javax.net.ssl.SSLSocketFactory r19) {
        /*
            Method dump skipped, instructions count: 329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.android.util.HttpService.performRequest(java.lang.String, java.util.Map, javax.net.ssl.SSLSocketFactory):byte[]");
    }
}
