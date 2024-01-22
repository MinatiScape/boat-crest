package com.google.android.gms.dynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
/* loaded from: classes6.dex */
public final class d implements h {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FrameLayout f8387a;
    public final /* synthetic */ LayoutInflater b;
    public final /* synthetic */ ViewGroup c;
    public final /* synthetic */ Bundle d;
    public final /* synthetic */ DeferredLifecycleHelper e;

    public d(DeferredLifecycleHelper deferredLifecycleHelper, FrameLayout frameLayout, LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.e = deferredLifecycleHelper;
        this.f8387a = frameLayout;
        this.b = layoutInflater;
        this.c = viewGroup;
        this.d = bundle;
    }

    @Override // com.google.android.gms.dynamic.h
    public final void a(LifecycleDelegate lifecycleDelegate) {
        LifecycleDelegate lifecycleDelegate2;
        this.f8387a.removeAllViews();
        FrameLayout frameLayout = this.f8387a;
        lifecycleDelegate2 = this.e.f8379a;
        frameLayout.addView(lifecycleDelegate2.onCreateView(this.b, this.c, this.d));
    }

    @Override // com.google.android.gms.dynamic.h
    public final int zaa() {
        return 2;
    }
}
