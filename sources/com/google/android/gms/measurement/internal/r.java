package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.util.List;
import java.util.Map;
@WorkerThread
/* loaded from: classes10.dex */
public final class r implements Runnable {
    public final q h;
    public final int i;
    public final Throwable j;
    public final byte[] k;
    public final String l;
    public final Map<String, List<String>> m;

    public /* synthetic */ r(String str, q qVar, int i, Throwable th, byte[] bArr, Map map, zzel zzelVar) {
        Preconditions.checkNotNull(qVar);
        this.h = qVar;
        this.i = i;
        this.j = th;
        this.k = bArr;
        this.l = str;
        this.m = map;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.h.a(this.l, this.i, this.j, this.k, this.m);
    }
}
