package com.google.android.play.integrity.internal;

import android.os.IBinder;
import java.util.List;
/* loaded from: classes10.dex */
public final class r extends l {
    public final /* synthetic */ IBinder h;
    public final /* synthetic */ u i;

    public r(u uVar, IBinder iBinder) {
        this.i = uVar;
        this.h = iBinder;
    }

    @Override // com.google.android.play.integrity.internal.l
    public final void b() {
        List<Runnable> list;
        List list2;
        this.i.h.m = g.b(this.h);
        v.n(this.i.h);
        this.i.h.g = false;
        list = this.i.h.d;
        for (Runnable runnable : list) {
            runnable.run();
        }
        list2 = this.i.h.d;
        list2.clear();
    }
}
