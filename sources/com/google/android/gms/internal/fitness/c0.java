package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.BleDevicesResult;
/* loaded from: classes8.dex */
public final class c0 extends zzep {

    /* renamed from: a  reason: collision with root package name */
    public final BaseImplementation.ResultHolder<BleDevicesResult> f8819a;

    public c0(BaseImplementation.ResultHolder<BleDevicesResult> resultHolder) {
        this.f8819a = resultHolder;
    }

    @Override // com.google.android.gms.internal.fitness.zzem
    public final void zza(BleDevicesResult bleDevicesResult) {
        this.f8819a.setResult(bleDevicesResult);
    }

    public /* synthetic */ c0(BaseImplementation.ResultHolder resultHolder, x xVar) {
        this(resultHolder);
    }
}
