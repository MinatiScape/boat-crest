package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGLogger;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public final class gb {

    /* renamed from: a  reason: collision with root package name */
    public final a0 f13767a;
    public int c;
    public ua d;
    public final ArrayList b = new ArrayList();
    public final AtomicBoolean e = new AtomicBoolean(false);

    public gb(a0 a0Var) {
        this.f13767a = a0Var;
    }

    public static void a(gb gbVar, int i) {
        boolean z = true;
        if (!gbVar.f13767a.a(33689120) && gbVar.f13767a.d() != 1) {
            z = false;
        }
        if (z) {
            if (i == 0 || 4 == i) {
                gbVar.b.add(new ya(gbVar));
            }
            if (i == 0 || 5 == i) {
                gbVar.b.add(new xa(gbVar));
            }
        }
        gbVar.a(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i) {
        if (i == 1 || i == -1) {
            int i2 = this.c + 1;
            this.c = i2;
            a(i2);
        }
    }

    public final void c(int i) {
        if (this.e.getAndSet(true)) {
            TGLogger.w(this.f13767a, "Synchronization is in progress. Please do not call repeatedly");
        } else if (this.f13767a.i()) {
            TGLogger.e(this.f13767a, "use TGClient#syncHealthData() instead");
        } else {
            a0 a0Var = this.f13767a;
            TGLogger.d(a0Var, "Synchronization of sport data started, dataType=" + i);
            TGLogger.d(this.f13767a, "Start to sync sport data");
            ua uaVar = this.d;
            if (uaVar != null) {
                uaVar.onStart();
            }
            this.c = 0;
            this.b.clear();
            if (i != 0 && 1 != i) {
                new v8(this.f13767a, new x6()).execute(new qa(this, i));
            } else {
                new v8(this.f13767a, new a9()).execute(new pa(this, i));
            }
        }
    }

    public final void a(int i) {
        if (i < this.b.size()) {
            int size = this.e.get() ? this.b.size() : 0;
            if (size > 0) {
                int i2 = (i * 100) / size;
                ua uaVar = this.d;
                if (uaVar != null) {
                    uaVar.onProgress(i2);
                }
            }
            sa saVar = (sa) this.b.get(i);
            saVar.f13824a = new ra() { // from class: com.touchgui.sdk.internal.hc
                @Override // com.touchgui.sdk.internal.ra
                public final void a(int i3) {
                    gb.this.b(i3);
                }
            };
            saVar.a();
            return;
        }
        TGLogger.d(this.f13767a, "Synchronous motion data complete");
        TGLogger.d(this.f13767a, "Sync sport data is completed");
        ua uaVar2 = this.d;
        if (uaVar2 != null) {
            uaVar2.onCompleted();
        }
        this.e.set(false);
    }

    public final void a(int i, String str) {
        a0 a0Var = this.f13767a;
        TGLogger.e(a0Var, "SportData, code=" + i);
        ua uaVar = this.d;
        if (uaVar != null) {
            uaVar.onError(i, str);
        }
        this.e.set(false);
    }

    public final void a(ua uaVar) {
        this.d = uaVar;
    }
}
