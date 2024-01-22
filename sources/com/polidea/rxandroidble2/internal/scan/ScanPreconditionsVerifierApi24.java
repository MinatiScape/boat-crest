package com.polidea.rxandroidble2.internal.scan;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.exceptions.BleScanException;
import io.reactivex.Scheduler;
import java.util.Date;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class ScanPreconditionsVerifierApi24 implements ScanPreconditionsVerifier {
    public static final long d = TimeUnit.SECONDS.toMillis(30);

    /* renamed from: a  reason: collision with root package name */
    public final long[] f13474a = new long[5];
    public final ScanPreconditionsVerifierApi18 b;
    public final Scheduler c;

    @Inject
    public ScanPreconditionsVerifierApi24(ScanPreconditionsVerifierApi18 scanPreconditionsVerifierApi18, @Named("computation") Scheduler scheduler) {
        this.b = scanPreconditionsVerifierApi18;
        this.c = scheduler;
    }

    public final int a() {
        long j = Long.MAX_VALUE;
        int i = -1;
        for (int i2 = 0; i2 < 5; i2++) {
            long j2 = this.f13474a[i2];
            if (j2 < j) {
                i = i2;
                j = j2;
            }
        }
        return i;
    }

    @Override // com.polidea.rxandroidble2.internal.scan.ScanPreconditionsVerifier
    public void verify(boolean z) {
        this.b.verify(z);
        int a2 = a();
        long j = this.f13474a[a2];
        long now = this.c.now(TimeUnit.MILLISECONDS);
        long j2 = d;
        if (now - j >= j2) {
            this.f13474a[a2] = now;
            return;
        }
        throw new BleScanException(2147483646, new Date(j + j2));
    }
}
