package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGFootballAvgPace;
/* loaded from: classes12.dex */
public final class xa extends cb {
    public final /* synthetic */ gb e;

    public xa(gb gbVar) {
        this.e = gbVar;
    }

    @Override // com.touchgui.sdk.internal.sa
    public final void a() {
        new g5(this.e.f13767a, new n5()).execute(this.c);
    }

    @Override // com.touchgui.sdk.internal.sa
    public final void b() {
        new g5(this.e.f13767a, new o5()).execute(this.d);
    }

    @Override // com.touchgui.sdk.internal.cb
    public final boolean a(Object obj) {
        TGFootballAvgPace tGFootballAvgPace = (TGFootballAvgPace) obj;
        ua uaVar = this.e.d;
        if (uaVar != null) {
            uaVar.onFootballSportAvgPace(tGFootballAvgPace);
        }
        return tGFootballAvgPace.isHaveMoreData();
    }
}
