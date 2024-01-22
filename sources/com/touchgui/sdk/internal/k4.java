package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public abstract class k4 extends g4 {
    public int b;
    public final int c;
    public final i4 e = new i4(this);
    public final j4 f = new j4(this);
    public final boolean d = true;

    public k4(int i) {
        this.c = i;
    }

    public abstract void a(Object obj);

    public final void c() {
        int i = this.b + 1;
        this.b = i;
        if (i < this.c) {
            j6.a().post(new Runnable() { // from class: com.touchgui.sdk.internal.mc
                @Override // java.lang.Runnable
                public final void run() {
                    k4.this.a();
                }
            });
        } else {
            this.f13765a.a(1);
        }
    }
}
