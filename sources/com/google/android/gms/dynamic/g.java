package com.google.android.gms.dynamic;
/* loaded from: classes6.dex */
public final class g implements h {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DeferredLifecycleHelper f8389a;

    public g(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.f8389a = deferredLifecycleHelper;
    }

    @Override // com.google.android.gms.dynamic.h
    public final void a(LifecycleDelegate lifecycleDelegate) {
        LifecycleDelegate lifecycleDelegate2;
        lifecycleDelegate2 = this.f8389a.f8379a;
        lifecycleDelegate2.onResume();
    }

    @Override // com.google.android.gms.dynamic.h
    public final int zaa() {
        return 5;
    }
}
