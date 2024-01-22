package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public abstract class g4 {

    /* renamed from: a  reason: collision with root package name */
    public f4 f13765a;

    public abstract void a();

    public final void a(f4 f4Var) {
        this.f13765a = f4Var;
        j6.a().post(new Runnable() { // from class: com.touchgui.sdk.internal.gc
            @Override // java.lang.Runnable
            public final void run() {
                g4.this.a();
            }
        });
    }

    public abstract void b();
}
