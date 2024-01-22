package com.google.android.gms.fitness.service;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.zzv;
import java.util.List;
/* loaded from: classes6.dex */
public final class b implements SensorEventDispatcher {

    /* renamed from: a  reason: collision with root package name */
    public final zzv f8466a;

    public b(zzv zzvVar) {
        this.f8466a = (zzv) Preconditions.checkNotNull(zzvVar);
    }

    @Override // com.google.android.gms.fitness.service.SensorEventDispatcher
    public final void publish(DataPoint dataPoint) throws RemoteException {
        dataPoint.zzh();
        this.f8466a.zzc(dataPoint);
    }

    @Override // com.google.android.gms.fitness.service.SensorEventDispatcher
    public final void publish(List<DataPoint> list) throws RemoteException {
        for (DataPoint dataPoint : list) {
            publish(dataPoint);
        }
    }
}
