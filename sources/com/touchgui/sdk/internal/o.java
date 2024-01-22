package com.touchgui.sdk.internal;

import androidx.work.PeriodicWorkRequest;
import com.clevertap.android.sdk.Constants;
import com.touchgui.sdk.TGLogger;
/* loaded from: classes12.dex */
public final class o {

    /* renamed from: a  reason: collision with root package name */
    public boolean f13804a = true;
    public final long b = System.currentTimeMillis();
    public final Runnable c = new Runnable() { // from class: com.touchgui.sdk.internal.pc
        @Override // java.lang.Runnable
        public final void run() {
            o.this.b();
        }
    };
    public final /* synthetic */ q d;

    public o(q qVar) {
        this.d = qVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b() {
        if (q.a(this.d)) {
            return;
        }
        a();
    }

    public final void a() {
        if (System.currentTimeMillis() - this.b < PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS) {
            this.d.u.postDelayed(this.c, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
            return;
        }
        this.f13804a = false;
        synchronized (this.d.l) {
            q qVar = this.d;
            if (qVar.k != 2) {
                qVar.b(false);
            }
        }
        TGLogger.d(this.d.n, "Never reconnect");
    }
}
