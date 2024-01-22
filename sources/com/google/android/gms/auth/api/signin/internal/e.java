package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
/* loaded from: classes6.dex */
public final class e extends zba {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ f f8212a;

    public e(f fVar) {
        this.f8212a = fVar;
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zba, com.google.android.gms.auth.api.signin.internal.zbr
    public final void zbb(Status status) throws RemoteException {
        this.f8212a.setResult((f) status);
    }
}
