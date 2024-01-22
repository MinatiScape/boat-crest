package com.google.android.gms.dynamic;

import java.util.Iterator;
import java.util.LinkedList;
/* loaded from: classes6.dex */
public final class a implements OnDelegateCreatedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DeferredLifecycleHelper f8384a;

    public a(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.f8384a = deferredLifecycleHelper;
    }

    @Override // com.google.android.gms.dynamic.OnDelegateCreatedListener
    public final void onDelegateCreated(LifecycleDelegate lifecycleDelegate) {
        LinkedList linkedList;
        LinkedList linkedList2;
        LifecycleDelegate lifecycleDelegate2;
        this.f8384a.f8379a = lifecycleDelegate;
        linkedList = this.f8384a.c;
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            lifecycleDelegate2 = this.f8384a.f8379a;
            ((h) it.next()).a(lifecycleDelegate2);
        }
        linkedList2 = this.f8384a.c;
        linkedList2.clear();
        this.f8384a.b = null;
    }
}
