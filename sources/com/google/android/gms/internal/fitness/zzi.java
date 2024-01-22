package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.concurrent.TimeUnit;
@ShowFirstParty
/* loaded from: classes8.dex */
public final class zzi {
    public static long zza(long j, TimeUnit timeUnit, TimeUnit timeUnit2) {
        return timeUnit.convert(timeUnit2.convert(j, timeUnit), timeUnit2);
    }
}
