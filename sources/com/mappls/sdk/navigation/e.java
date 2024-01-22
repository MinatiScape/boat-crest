package com.mappls.sdk.navigation;

import com.mappls.sdk.navigation.c;
import java.util.Iterator;
/* loaded from: classes11.dex */
public final class e implements Runnable {
    public final /* synthetic */ c h;

    public e(c cVar) {
        this.h = cVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Iterator it = this.h.e.iterator();
        while (it.hasNext()) {
            ((c.InterfaceC0639c) it.next()).a();
        }
    }
}
