package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGFunctions;
import com.touchgui.sdk.bean.TGSleepData;
/* loaded from: classes12.dex */
public final class p4 extends k4 {
    public final boolean g;
    public final /* synthetic */ com.touchgui.sdk.g h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p4(com.touchgui.sdk.g gVar, boolean z, int i) {
        super(i);
        this.h = gVar;
        this.g = z;
    }

    @Override // com.touchgui.sdk.internal.g4
    public final void a() {
        com.touchgui.sdk.g gVar = this.h;
        boolean z = this.g;
        v8 v8Var = new v8(gVar.f13736a, new a5((byte) (z ? 6 : 4), gVar.f13736a.a(TGFunctions.FUNC_SLEEP_DURATION_EXCLUDE_AWAKE)));
        v8Var.f = false;
        v8Var.execute(this.e);
    }

    @Override // com.touchgui.sdk.internal.g4
    public final void b() {
        v8 v8Var = new v8(this.h.f13736a, new w4((byte) (this.g ? 6 : 4)));
        v8Var.f = false;
        v8Var.execute(this.f);
    }

    @Override // com.touchgui.sdk.internal.k4
    public final void a(Object obj) {
        com.touchgui.sdk.g.a(this.h, (TGSleepData) obj, this.g);
    }
}
