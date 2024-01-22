package com.touchgui.sdk.internal;

import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public final class t9 {

    /* renamed from: a  reason: collision with root package name */
    public final a0 f13826a;
    public final CopyOnWriteArrayList b = new CopyOnWriteArrayList();
    public final m9 c = new m9(this);
    public final p9 d = new p9(this);
    public final q9 e = new q9(this);
    public final r9 f = new r9(this);
    public final s9 g = new s9(this);

    public t9(a0 a0Var) {
        this.f13826a = a0Var;
        a();
    }

    public final void a() {
        new g6(this.f13826a, new i2()).execute(this.c);
        new g6(this.f13826a, new j2()).execute(this.d);
        new g6(this.f13826a, new k2()).execute(this.e);
        new g6(this.f13826a, new l2()).execute(this.f);
        new g6(this.f13826a, new v5()).execute(this.g);
    }
}
