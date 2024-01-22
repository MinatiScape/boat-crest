package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
/* loaded from: classes10.dex */
public final class f4 implements zzgs {

    /* renamed from: a  reason: collision with root package name */
    public final com.google.android.gms.internal.measurement.zzci f10115a;
    public final /* synthetic */ AppMeasurementDynamiteService b;

    public f4(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzci zzciVar) {
        this.b = appMeasurementDynamiteService;
        this.f10115a = zzciVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final void interceptEvent(String str, String str2, Bundle bundle, long j) {
        try {
            this.f10115a.zze(str, str2, bundle, j);
        } catch (RemoteException e) {
            zzfs zzfsVar = this.b.f10110a;
            if (zzfsVar != null) {
                zzfsVar.zzay().zzk().zzb("Event interceptor threw exception", e);
            }
        }
    }
}
