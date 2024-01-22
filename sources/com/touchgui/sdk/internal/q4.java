package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGSyncSpo2;
/* loaded from: classes12.dex */
public final class q4 extends o4 {
    public final boolean e;
    public final /* synthetic */ com.touchgui.sdk.g f;

    public q4(com.touchgui.sdk.g gVar, boolean z) {
        this.f = gVar;
        this.e = z;
    }

    @Override // com.touchgui.sdk.internal.g4
    public final void a() {
        g5 g5Var = new g5(this.f.f13736a, new b6(this.e));
        g5Var.f = false;
        g5Var.execute(this.c);
    }

    @Override // com.touchgui.sdk.internal.g4
    public final void b() {
        new g5(this.f.f13736a, new c6(this.e)).execute(this.d);
    }

    @Override // com.touchgui.sdk.internal.o4
    public final boolean a(Object obj) {
        TGSyncSpo2 tGSyncSpo2 = (TGSyncSpo2) obj;
        com.touchgui.sdk.g.a(this.f, tGSyncSpo2, this.e);
        return this.e && tGSyncSpo2.isHaveMoreData();
    }
}
