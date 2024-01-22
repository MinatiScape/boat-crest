package com.google.android.gms.internal.measurement;

import android.os.Bundle;
/* loaded from: classes8.dex */
public final class t0 extends zzch {

    /* renamed from: a  reason: collision with root package name */
    public final com.google.android.gms.measurement.internal.zzgt f8929a;

    public t0(com.google.android.gms.measurement.internal.zzgt zzgtVar) {
        this.f8929a = zzgtVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzci
    public final int zzd() {
        return System.identityHashCode(this.f8929a);
    }

    @Override // com.google.android.gms.internal.measurement.zzci
    public final void zze(String str, String str2, Bundle bundle, long j) {
        this.f8929a.onEvent(str, str2, bundle, j);
    }
}
