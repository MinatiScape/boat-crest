package com.google.android.gms.dynamic;
/* loaded from: classes6.dex */
public final class f implements h {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DeferredLifecycleHelper f8388a;

    public f(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.f8388a = deferredLifecycleHelper;
    }

    @Override // com.google.android.gms.dynamic.h
    public final void a(LifecycleDelegate lifecycleDelegate) {
        LifecycleDelegate lifecycleDelegate2;
        lifecycleDelegate2 = this.f8388a.f8379a;
        lifecycleDelegate2.onStart();
    }

    @Override // com.google.android.gms.dynamic.h
    public final int zaa() {
        return 4;
    }
}
