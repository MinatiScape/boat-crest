package com.google.android.gms.dynamic;

import android.os.Bundle;
/* loaded from: classes6.dex */
public final class c implements h {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Bundle f8386a;
    public final /* synthetic */ DeferredLifecycleHelper b;

    public c(DeferredLifecycleHelper deferredLifecycleHelper, Bundle bundle) {
        this.b = deferredLifecycleHelper;
        this.f8386a = bundle;
    }

    @Override // com.google.android.gms.dynamic.h
    public final void a(LifecycleDelegate lifecycleDelegate) {
        LifecycleDelegate lifecycleDelegate2;
        lifecycleDelegate2 = this.b.f8379a;
        lifecycleDelegate2.onCreate(this.f8386a);
    }

    @Override // com.google.android.gms.dynamic.h
    public final int zaa() {
        return 1;
    }
}
