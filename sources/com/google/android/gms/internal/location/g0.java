package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.location.LocationSettingsResult;
/* loaded from: classes8.dex */
public final class g0 extends zzr {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BaseImplementation.ResultHolder f8885a;

    public g0(BaseImplementation.ResultHolder resultHolder) {
        this.f8885a = resultHolder;
    }

    @Override // com.google.android.gms.internal.location.zzs
    public final void zzb(LocationSettingsResult locationSettingsResult) {
        this.f8885a.setResult(locationSettingsResult);
    }
}
