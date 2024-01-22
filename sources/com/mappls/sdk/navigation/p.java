package com.mappls.sdk.navigation;
/* loaded from: classes11.dex */
public final class p implements Runnable {
    public final /* synthetic */ int h = 5001;
    public final /* synthetic */ Runnable i;
    public final /* synthetic */ NavigationApplication j;

    public p(NavigationApplication navigationApplication, Runnable runnable) {
        this.j = navigationApplication;
        this.i = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.j.l.hasMessages(this.h)) {
            return;
        }
        this.i.run();
    }
}
