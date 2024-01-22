package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public abstract class o4 extends g4 {
    public boolean b;
    public final m4 c = new m4(this);
    public final n4 d = new n4(this);

    public abstract boolean a(Object obj);

    public final void c() {
        if (this.b) {
            j6.a().post(new Runnable() { // from class: com.touchgui.sdk.internal.qc
                @Override // java.lang.Runnable
                public final void run() {
                    o4.this.a();
                }
            });
            return;
        }
        f4 f4Var = this.f13765a;
        if (f4Var != null) {
            f4Var.a(1);
        }
    }
}
