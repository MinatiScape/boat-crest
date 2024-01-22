package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGSyncGps;
/* loaded from: classes12.dex */
public final class za extends cb {
    public final boolean e;
    public final /* synthetic */ gb f;

    public za(gb gbVar, boolean z) {
        this.f = gbVar;
        this.e = z;
    }

    @Override // com.touchgui.sdk.internal.sa
    public final void a() {
        new g5(this.f.f13767a, new d6(this.e)).execute(this.c);
    }

    @Override // com.touchgui.sdk.internal.sa
    public final void b() {
        new g5(this.f.f13767a, new e6(this.e)).execute(this.d);
    }

    @Override // com.touchgui.sdk.internal.cb
    public final boolean a(Object obj) {
        TGSyncGps tGSyncGps = (TGSyncGps) obj;
        ua uaVar = this.f.d;
        if (uaVar != null) {
            uaVar.onGpsData(tGSyncGps);
        }
        return this.e && tGSyncGps.isHaveMoreData();
    }
}
