package com.google.android.gms.vision;

import android.util.SparseIntArray;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzc {
    public static final Object c = new Object();
    @GuardedBy("lock")
    public static int d;
    @GuardedBy("lock")

    /* renamed from: a  reason: collision with root package name */
    public final SparseIntArray f10202a = new SparseIntArray();
    @GuardedBy("lock")
    public final SparseIntArray b = new SparseIntArray();

    public final int zzb(int i) {
        synchronized (c) {
            int i2 = this.f10202a.get(i, -1);
            if (i2 != -1) {
                return i2;
            }
            int i3 = d;
            d = i3 + 1;
            this.f10202a.append(i, i3);
            this.b.append(i3, i);
            return i3;
        }
    }

    public final int zzc(int i) {
        int i2;
        synchronized (c) {
            i2 = this.b.get(i);
        }
        return i2;
    }
}
