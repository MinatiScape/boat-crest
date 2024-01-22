package com.google.android.gms.internal.measurement;

import android.os.Bundle;
/* loaded from: classes8.dex */
public final class s0 extends zzch {

    /* renamed from: a  reason: collision with root package name */
    public final com.google.android.gms.measurement.internal.zzgs f8925a;

    public s0(com.google.android.gms.measurement.internal.zzgs zzgsVar) {
        this.f8925a = zzgsVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzci
    public final int zzd() {
        return System.identityHashCode(this.f8925a);
    }

    @Override // com.google.android.gms.internal.measurement.zzci
    public final void zze(String str, String str2, Bundle bundle, long j) {
        this.f8925a.interceptEvent(str, str2, bundle, j);
    }
}
