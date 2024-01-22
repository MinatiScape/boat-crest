package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
@ShowFirstParty
/* loaded from: classes10.dex */
public final class zza {

    /* renamed from: a  reason: collision with root package name */
    public long f10038a = Long.MIN_VALUE;

    public final zza zza(long j) {
        Preconditions.checkArgument(j >= 0, "intervalMillis can't be negative.");
        this.f10038a = j;
        return this;
    }

    public final zzb zzb() {
        Preconditions.checkState(this.f10038a != Long.MIN_VALUE, "Must set intervalMillis.");
        return new zzb(this.f10038a, true, null, null, null, false, null, 0L, null);
    }
}
