package com.google.android.gms.common.api.internal;

import androidx.annotation.BinderThread;
import java.lang.ref.WeakReference;
/* loaded from: classes6.dex */
public final class o extends com.google.android.gms.signin.internal.zac {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference f8280a;

    public o(zaaw zaawVar) {
        this.f8280a = new WeakReference(zaawVar);
    }

    @Override // com.google.android.gms.signin.internal.zac, com.google.android.gms.signin.internal.zae
    @BinderThread
    public final void zab(com.google.android.gms.signin.internal.zak zakVar) {
        zabi zabiVar;
        zaaw zaawVar = (zaaw) this.f8280a.get();
        if (zaawVar == null) {
            return;
        }
        zabiVar = zaawVar.f8298a;
        zabiVar.f(new n(this, zaawVar, zaawVar, zakVar));
    }
}
