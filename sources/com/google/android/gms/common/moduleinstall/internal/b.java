package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate;
/* loaded from: classes6.dex */
public final class b extends zag {

    /* renamed from: a  reason: collision with root package name */
    public final ListenerHolder f8356a;

    public b(ListenerHolder listenerHolder) {
        this.f8356a = listenerHolder;
    }

    @Override // com.google.android.gms.common.moduleinstall.internal.zah
    public final void zab(ModuleInstallStatusUpdate moduleInstallStatusUpdate) {
        this.f8356a.notifyListener(new a(this, moduleInstallStatusUpdate));
    }
}
