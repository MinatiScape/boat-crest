package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGFootballFieldGps;
/* loaded from: classes12.dex */
public final class ya extends cb {
    public final /* synthetic */ gb e;

    public ya(gb gbVar) {
        this.e = gbVar;
    }

    @Override // com.touchgui.sdk.internal.sa
    public final void a() {
        new g5(this.e.f13767a, new l5()).execute(this.c);
    }

    @Override // com.touchgui.sdk.internal.sa
    public final void b() {
        new g5(this.e.f13767a, new m5()).execute(this.d);
    }

    @Override // com.touchgui.sdk.internal.cb
    public final boolean a(Object obj) {
        TGFootballFieldGps tGFootballFieldGps = (TGFootballFieldGps) obj;
        ua uaVar = this.e.d;
        if (uaVar != null) {
            uaVar.onFootballSportFieldGps(tGFootballFieldGps);
        }
        return tGFootballFieldGps.isHaveMoreData();
    }
}
