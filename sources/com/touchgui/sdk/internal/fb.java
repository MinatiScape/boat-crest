package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGSyncSwim;
/* loaded from: classes12.dex */
public final class fb extends cb {
    public final boolean e;
    public final /* synthetic */ gb f;

    public fb(gb gbVar, boolean z) {
        this.f = gbVar;
        this.e = z;
    }

    @Override // com.touchgui.sdk.internal.sa
    public final void a() {
        new g5(this.f.f13767a, new f6(this.e)).execute(this.c);
    }

    @Override // com.touchgui.sdk.internal.sa
    public final void b() {
        new g5(this.f.f13767a, new i5(this.e)).execute(this.d);
    }

    @Override // com.touchgui.sdk.internal.cb
    public final boolean a(Object obj) {
        TGSyncSwim tGSyncSwim = (TGSyncSwim) obj;
        ua uaVar = this.f.d;
        if (uaVar != null) {
            uaVar.onSwimData(tGSyncSwim);
        }
        return this.e && tGSyncSwim.isHaveMoreData();
    }
}
