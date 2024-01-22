package com.mappls.sdk.navigation.ui.map.route;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes11.dex */
public class w implements Runnable {
    public final /* synthetic */ t h;
    public final /* synthetic */ List i;
    public final /* synthetic */ x j;

    public w(x xVar, t tVar, List list) {
        this.j = xVar;
        this.h = tVar;
        this.i = list;
    }

    @Override // java.lang.Runnable
    public void run() {
        AtomicBoolean atomicBoolean;
        atomicBoolean = this.j.k;
        if (atomicBoolean.get()) {
            return;
        }
        this.h.a(this.i);
    }
}
