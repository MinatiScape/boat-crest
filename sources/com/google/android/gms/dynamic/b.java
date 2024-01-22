package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;
/* loaded from: classes6.dex */
public final class b implements h {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Activity f8385a;
    public final /* synthetic */ Bundle b;
    public final /* synthetic */ Bundle c;
    public final /* synthetic */ DeferredLifecycleHelper d;

    public b(DeferredLifecycleHelper deferredLifecycleHelper, Activity activity, Bundle bundle, Bundle bundle2) {
        this.d = deferredLifecycleHelper;
        this.f8385a = activity;
        this.b = bundle;
        this.c = bundle2;
    }

    @Override // com.google.android.gms.dynamic.h
    public final void a(LifecycleDelegate lifecycleDelegate) {
        LifecycleDelegate lifecycleDelegate2;
        lifecycleDelegate2 = this.d.f8379a;
        lifecycleDelegate2.onInflate(this.f8385a, this.b, this.c);
    }

    @Override // com.google.android.gms.dynamic.h
    public final int zaa() {
        return 0;
    }
}
