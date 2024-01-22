package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.List;
import java.util.Map;
@WorkerThread
/* loaded from: classes10.dex */
public final class x1 implements Runnable {
    public final URL h;
    public final String i;
    public final /* synthetic */ zzhz j;
    public final zzfq k;

    public x1(zzhz zzhzVar, String str, URL url, byte[] bArr, Map map, zzfq zzfqVar, byte[] bArr2) {
        this.j = zzhzVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzfqVar);
        this.h = url;
        this.k = zzfqVar;
        this.i = str;
    }

    public final /* synthetic */ void a(int i, Exception exc, byte[] bArr, Map map) {
        zzfq zzfqVar = this.k;
        zzfqVar.zza.zzC(this.i, i, exc, bArr, map);
    }

    public final void b(final int i, final Exception exc, final byte[] bArr, final Map<String, List<String>> map) {
        this.j.zzs.zzaz().zzp(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzhx
            @Override // java.lang.Runnable
            public final void run() {
                x1.this.a(i, exc, bArr, map);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0071  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r10 = this;
            com.google.android.gms.measurement.internal.zzhz r0 = r10.j
            r0.zzax()
            r0 = 0
            r1 = 0
            com.google.android.gms.measurement.internal.zzhz r2 = r10.j     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L6a
            java.net.URL r3 = r10.h     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L6a
            java.net.HttpURLConnection r2 = r2.zza(r3)     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L6a
            int r3 = r2.getResponseCode()     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L56
            java.util.Map r4 = r2.getHeaderFields()     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4d
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L3e
            r5.<init>()     // Catch: java.lang.Throwable -> L3e
            java.io.InputStream r6 = r2.getInputStream()     // Catch: java.lang.Throwable -> L3e
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch: java.lang.Throwable -> L3c
        L24:
            int r8 = r6.read(r7)     // Catch: java.lang.Throwable -> L3c
            if (r8 <= 0) goto L2e
            r5.write(r7, r0, r8)     // Catch: java.lang.Throwable -> L3c
            goto L24
        L2e:
            byte[] r0 = r5.toByteArray()     // Catch: java.lang.Throwable -> L3c
            r6.close()     // Catch: java.lang.Throwable -> L46 java.io.IOException -> L48
            r2.disconnect()
            r10.b(r3, r1, r0, r4)
            return
        L3c:
            r0 = move-exception
            goto L40
        L3e:
            r0 = move-exception
            r6 = r1
        L40:
            if (r6 == 0) goto L45
            r6.close()     // Catch: java.lang.Throwable -> L46 java.io.IOException -> L48
        L45:
            throw r0     // Catch: java.lang.Throwable -> L46 java.io.IOException -> L48
        L46:
            r0 = move-exception
            goto L61
        L48:
            r0 = move-exception
            goto L6f
        L4a:
            r0 = move-exception
            r4 = r1
            goto L61
        L4d:
            r0 = move-exception
            r4 = r1
            goto L6f
        L50:
            r3 = move-exception
            r4 = r1
            r9 = r3
            r3 = r0
            r0 = r9
            goto L61
        L56:
            r3 = move-exception
            r4 = r1
            r9 = r3
            r3 = r0
            r0 = r9
            goto L6f
        L5c:
            r2 = move-exception
            r3 = r0
            r4 = r1
            r0 = r2
            r2 = r4
        L61:
            if (r2 == 0) goto L66
            r2.disconnect()
        L66:
            r10.b(r3, r1, r1, r4)
            throw r0
        L6a:
            r2 = move-exception
            r3 = r0
            r4 = r1
            r0 = r2
            r2 = r4
        L6f:
            if (r2 == 0) goto L74
            r2.disconnect()
        L74:
            r10.b(r3, r0, r1, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.x1.run():void");
    }
}
