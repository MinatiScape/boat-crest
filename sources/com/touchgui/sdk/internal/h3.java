package com.touchgui.sdk.internal;

import androidx.core.internal.view.SupportMenu;
import com.touchgui.sdk.TGErrorCode;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.exception.TGException;
/* loaded from: classes12.dex */
public final class h3 implements Runnable, Comparable {

    /* renamed from: a  reason: collision with root package name */
    public final d f13769a;
    public final int b;
    public final Object c = new Object();
    public int d = 512;
    public int e = 0;
    public final /* synthetic */ i3 f;

    public h3(i3 i3Var, d dVar, int i) {
        this.f = i3Var;
        this.f13769a = dVar;
        this.b = i;
    }

    public final void a(boolean z) {
        a0 a0Var = this.f.f13776a;
        TGLogger.d(a0Var, "cancel from user:" + z);
        synchronized (this.c) {
            if (z) {
                this.e = 3;
            }
            boolean z2 = true;
            if (this.f13769a.g != 1) {
                z2 = false;
            }
            if (z2) {
                this.c.notifyAll();
            }
        }
    }

    public final boolean b() {
        if (a(256)) {
            return false;
        }
        if (a(1024) || a(2)) {
            return true;
        }
        if (a(4) || a(512)) {
            return false;
        }
        return !a(2048);
    }

    public final void c() {
        while (true) {
            if (!a() && !b()) {
                return;
            }
            if (a()) {
                this.f13769a.b(this.b);
                long currentTimeMillis = System.currentTimeMillis();
                this.c.wait(this.f13769a.f13752a);
                if (this.e != 3) {
                    if (System.currentTimeMillis() - currentTimeMillis >= this.f13769a.f13752a) {
                        throw TGException.timeout(0);
                    }
                } else {
                    throw TGException.canceled();
                }
            }
            if (b()) {
                long currentTimeMillis2 = System.currentTimeMillis();
                this.c.wait(this.f13769a.f13752a);
                if (this.e != 3) {
                    if (System.currentTimeMillis() - currentTimeMillis2 >= this.f13769a.f13752a) {
                        throw TGException.timeout(1);
                    }
                } else {
                    throw TGException.canceled();
                }
            }
            if (a(1) || a(256)) {
                int i = (this.d & SupportMenu.CATEGORY_MASK) >> 16;
                if (i > 0) {
                    throw new TGException(TGErrorCode.getMessage(i), i);
                }
            }
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Integer.compare(this.f13769a.b, ((h3) obj).f13769a.b);
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.c) {
            if (this.e != 3) {
                this.e = 1;
                this.f13769a.c();
                try {
                    c();
                    this.f13769a.d();
                } catch (Exception e) {
                    this.f13769a.a(e);
                }
                this.e = 2;
            } else {
                this.f13769a.a((Exception) TGException.canceled());
            }
        }
        this.f.a(this);
    }

    public final boolean a(int i) {
        return (this.d & i) == i;
    }

    public final boolean a() {
        if (a(1) || a(256)) {
            return false;
        }
        if (a(4)) {
            return true;
        }
        if (a(1024)) {
            return false;
        }
        if (a(512)) {
            return true;
        }
        return true ^ a(8);
    }

    public final boolean a(byte[] bArr) {
        int i;
        synchronized (this.c) {
            if (this.e != 1) {
                return false;
            }
            d dVar = this.f13769a;
            if (dVar.d.a(bArr)) {
                try {
                    i = dVar.d.onResponse(bArr);
                } catch (Exception e) {
                    e.printStackTrace();
                    i = 256;
                }
            } else {
                i = 0;
            }
            if (i != 0) {
                this.d = i;
                this.c.notify();
            }
            return i != 0;
        }
    }

    public final boolean a(byte[] bArr, boolean z) {
        synchronized (this.c) {
            if (this.e != 1) {
                return false;
            }
            int a2 = this.f13769a.a(bArr, z);
            if (a2 != 0) {
                this.d = a2;
                this.c.notify();
            }
            return a2 != 0;
        }
    }
}
