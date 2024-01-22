package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public final class eb extends sa {
    public int b;
    public final int c;
    public final va d = new va(this);
    public final wa e = new wa(this);
    public final /* synthetic */ gb f;

    public eb(gb gbVar, int i) {
        this.f = gbVar;
        this.c = i;
    }

    @Override // com.touchgui.sdk.internal.sa
    public final void a() {
        new v8(this.f.f13767a, new b9()).execute(this.d);
    }

    @Override // com.touchgui.sdk.internal.sa
    public final void b() {
        new v8(this.f.f13767a, new c9()).execute(this.e);
    }
}
