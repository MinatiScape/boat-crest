package com.google.android.gms.common.internal.service;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
/* loaded from: classes6.dex */
public final class c extends zaa {

    /* renamed from: a  reason: collision with root package name */
    public final BaseImplementation.ResultHolder f8340a;

    public c(BaseImplementation.ResultHolder resultHolder) {
        this.f8340a = resultHolder;
    }

    @Override // com.google.android.gms.common.internal.service.zaa, com.google.android.gms.common.internal.service.zak
    public final void zab(int i) throws RemoteException {
        this.f8340a.setResult(new Status(i));
    }
}
