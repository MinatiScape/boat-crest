package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.Map;
@WorkerThread
/* loaded from: classes10.dex */
public final class s implements Runnable {
    public final URL h;
    public final byte[] i;
    public final q j;
    public final String k;
    public final Map<String, String> l;
    public final /* synthetic */ zzeo m;

    public s(zzeo zzeoVar, String str, URL url, byte[] bArr, Map<String, String> map, q qVar) {
        this.m = zzeoVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(qVar);
        this.h = url;
        this.i = bArr;
        this.j = qVar;
        this.k = str;
        this.l = map;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x012e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ee A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 355
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.s.run():void");
    }
}
