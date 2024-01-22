package com.google.android.gms.common.api.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.BaseGmsClient;
/* loaded from: classes6.dex */
public final class d0 implements BaseGmsClient.SignOutCallbacks {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zabq f8273a;

    public d0(zabq zabqVar) {
        this.f8273a = zabqVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.SignOutCallbacks
    public final void onSignOutComplete() {
        Handler handler;
        handler = this.f8273a.m.w;
        handler.post(new c0(this));
    }
}
