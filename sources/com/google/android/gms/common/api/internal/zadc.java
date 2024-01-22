package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
/* loaded from: classes6.dex */
public final class zadc {
    public static final Status zaa = new Status(8, "The connection to Google Play services was lost");
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final Set f8309a = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    public final s0 b = new s0(this);

    public final void a(BasePendingResult basePendingResult) {
        this.f8309a.add(basePendingResult);
        basePendingResult.zan(this.b);
    }

    public final void zab() {
        BasePendingResult[] basePendingResultArr;
        for (BasePendingResult basePendingResult : (BasePendingResult[]) this.f8309a.toArray(new BasePendingResult[0])) {
            basePendingResult.zan(null);
            if (basePendingResult.zam()) {
                this.f8309a.remove(basePendingResult);
            }
        }
    }
}
