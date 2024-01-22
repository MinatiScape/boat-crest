package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGSportRealTimeData;
/* loaded from: classes12.dex */
public final class db extends cb {
    public final boolean e;
    public final /* synthetic */ gb f;

    public db(gb gbVar, boolean z) {
        this.f = gbVar;
        this.e = z;
    }

    @Override // com.touchgui.sdk.internal.sa
    public final void a() {
        new g5(this.f.f13767a, new p5(this.e)).execute(this.c);
    }

    @Override // com.touchgui.sdk.internal.sa
    public final void b() {
        new g5(this.f.f13767a, new q5(this.e)).execute(this.d);
    }

    @Override // com.touchgui.sdk.internal.cb
    public final boolean a(Object obj) {
        TGSportRealTimeData tGSportRealTimeData = (TGSportRealTimeData) obj;
        ua uaVar = this.f.d;
        if (uaVar != null) {
            uaVar.onRealTimeData(tGSportRealTimeData);
        }
        return this.e && tGSportRealTimeData.isHaveMoreData();
    }
}
