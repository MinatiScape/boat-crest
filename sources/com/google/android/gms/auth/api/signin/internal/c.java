package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
/* loaded from: classes6.dex */
public final class c extends zba {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f8211a;

    public c(d dVar) {
        this.f8211a = dVar;
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zba, com.google.android.gms.auth.api.signin.internal.zbr
    public final void zbc(Status status) throws RemoteException {
        this.f8211a.setResult((d) status);
    }
}
