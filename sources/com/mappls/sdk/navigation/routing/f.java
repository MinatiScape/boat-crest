package com.mappls.sdk.navigation.routing;

import com.mappls.sdk.navigation.iface.INavigationListener;
import com.mappls.sdk.navigation.routing.d;
import java.lang.ref.WeakReference;
import java.util.Iterator;
/* loaded from: classes11.dex */
public final class f implements Runnable {
    public final /* synthetic */ d h;

    public f(d dVar) {
        this.h = dVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.h.h != null) {
            Iterator it = this.h.h.iterator();
            while (it.hasNext()) {
                INavigationListener iNavigationListener = (INavigationListener) ((WeakReference) it.next()).get();
                if (iNavigationListener == null) {
                    it.remove();
                } else {
                    iNavigationListener.onNavigationFinished();
                }
            }
        }
        Iterator it2 = this.h.f.iterator();
        while (it2.hasNext()) {
            d.c cVar = (d.c) ((WeakReference) it2.next()).get();
            if (cVar == null) {
                it2.remove();
            } else {
                cVar.c();
            }
        }
    }
}
