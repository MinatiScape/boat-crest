package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;
/* loaded from: classes6.dex */
public final class w extends zabw {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference f8291a;

    public w(zabe zabeVar) {
        this.f8291a = new WeakReference(zabeVar);
    }

    @Override // com.google.android.gms.common.api.internal.zabw
    public final void zaa() {
        zabe zabeVar = (zabe) this.f8291a.get();
        if (zabeVar == null) {
            return;
        }
        zabe.f(zabeVar);
    }
}
