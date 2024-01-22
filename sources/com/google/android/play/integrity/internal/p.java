package com.google.android.play.integrity.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.IInterface;
/* loaded from: classes10.dex */
public final class p extends l {
    public final /* synthetic */ v h;

    public p(v vVar) {
        this.h = vVar;
    }

    @Override // com.google.android.play.integrity.internal.l
    public final void b() {
        IInterface iInterface;
        k kVar;
        Context context;
        ServiceConnection serviceConnection;
        v vVar = this.h;
        iInterface = vVar.m;
        if (iInterface != null) {
            kVar = vVar.b;
            kVar.d("Unbind from service.", new Object[0]);
            v vVar2 = this.h;
            context = vVar2.f10474a;
            serviceConnection = vVar2.l;
            context.unbindService(serviceConnection);
            this.h.g = false;
            this.h.m = null;
            this.h.l = null;
        }
        this.h.t();
    }
}
