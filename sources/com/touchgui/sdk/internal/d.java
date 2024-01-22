package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGCommand;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.exception.TGException;
/* loaded from: classes12.dex */
public abstract class d implements TGCommand {
    public final a0 c;
    public final d8 d;
    public TGCallback e;

    /* renamed from: a  reason: collision with root package name */
    public int f13752a = 15000;
    public int b = 2;
    public boolean f = true;
    public int g = 0;

    public d(a0 a0Var, d8 d8Var) {
        this.c = a0Var;
        this.d = d8Var;
    }

    public boolean a() {
        return true;
    }

    public abstract byte[] a(int i);

    public void b(int i) {
        if (!this.c.h()) {
            throw TGException.notConnected();
        }
        if (!this.c.e.f) {
            throw TGException.notReady();
        }
        if (!a()) {
            throw TGException.notSupport("This feature is not supported:" + this.d.a());
        }
        a0 a0Var = this.c;
        int i2 = a0Var.D;
        boolean z = true;
        if (i2 != 0 && i2 != 1) {
            z = false;
        }
        if (!z) {
            throw TGException.authFail();
        }
        byte[] a2 = a(a0Var.e.d - 3);
        if (a2 == null || a2.length <= 0) {
            throw TGException.noSendData();
        }
        boolean z2 = false;
        for (int i3 = 0; i3 < 3; i3++) {
            z2 = this.c.e.a(i, a2);
            if (z2) {
                break;
            }
            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!z2) {
            throw TGException.sendDataFail();
        }
    }

    public void c() {
        String c = this.c.c();
        TGLogger.d(c, "onStart:" + this.d.a());
        this.g = 1;
    }

    @Override // com.touchgui.sdk.TGCommand
    public final void cancel() {
        a0 a0Var = this.c;
        i3 i3Var = a0Var.f;
        synchronized (i3Var) {
            i3Var.b.remove(this);
        }
        a0Var.f.a(this);
    }

    public final void d() {
        String c = this.c.c();
        TGLogger.d(c, "onStop:" + this.d.a());
        this.g = 4;
        final Object b = this.d.b();
        this.d.release();
        if (this.f) {
            j6.a().post(new Runnable() { // from class: com.touchgui.sdk.internal.ac
                @Override // java.lang.Runnable
                public final void run() {
                    d.this.a(b);
                }
            });
        } else {
            a(b);
        }
    }

    @Override // com.touchgui.sdk.TGCommand
    public final void execute(TGCallback tGCallback) {
        this.e = tGCallback;
        if (this.f) {
            a0 a0Var = this.c;
            if (a0Var.x && !(this instanceof m3) && a0Var.a(34013968)) {
                TGLogger.w(a0Var.c(), "The device is performing a file transfer, this command was ignored.");
                a((Exception) TGException.notResponse());
                return;
            } else if (this instanceof g6) {
                i3 i3Var = a0Var.f;
                synchronized (i3Var) {
                    i3Var.b.add(this);
                }
                return;
            } else {
                a0Var.f.b(this);
                return;
            }
        }
        j6.a().post(new Runnable() { // from class: com.touchgui.sdk.internal.zb
            @Override // java.lang.Runnable
            public final void run() {
                d.this.b();
            }
        });
    }

    public final void a(final Exception exc) {
        TGLogger.e(this.c.c(), String.format("onError:%s %s", this.d.a(), exc.getMessage()));
        this.g = 3;
        this.d.release();
        if (this.f) {
            j6.a().post(new Runnable() { // from class: com.touchgui.sdk.internal.bc
                @Override // java.lang.Runnable
                public final void run() {
                    d.this.a(exc);
                }
            });
        } else {
            a((Throwable) exc);
        }
    }

    public int a(byte[] bArr, boolean z) {
        if (this.d.a(bArr)) {
            if (z) {
                try {
                    return this.d.b(bArr);
                } catch (Exception e) {
                    e.printStackTrace();
                    return 1;
                }
            }
            return 1;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        a0 a0Var = this.c;
        if (a0Var.x && !(this instanceof m3) && a0Var.a(34013968)) {
            TGLogger.w(a0Var.c(), "The device is performing a file transfer, this command was ignored.");
            a((Exception) TGException.notResponse());
        } else if (this instanceof g6) {
            i3 i3Var = a0Var.f;
            synchronized (i3Var) {
                i3Var.b.add(this);
            }
        } else {
            a0Var.f.b(this);
        }
    }

    /* renamed from: b */
    public final void a(Throwable th) {
        TGCallback tGCallback = this.e;
        if (tGCallback != null) {
            tGCallback.onFailure(th);
        }
        a0 a0Var = this.c;
        a0Var.getClass();
        if ((th instanceof TGException) && ((TGException) th).getCode() == 10008 && a0Var.A > 0) {
            a0Var.z++;
            TGLogger.d("timeoutErrorCount=" + a0Var.z + ", maxTimeoutErrorCount=" + a0Var.A);
            if (a0Var.z >= a0Var.A) {
                TGLogger.d("reconnect, count=" + a0Var.B + ", limit=" + a0Var.C);
                int i = a0Var.B;
                if (i >= a0Var.C) {
                    a0Var.a();
                    return;
                }
                a0Var.B = i + 1;
                String c = a0Var.c();
                a0Var.a();
                a0Var.a(c, true);
            }
        }
    }

    /* renamed from: b */
    public void a(Object obj) {
        TGCallback tGCallback = this.e;
        if (tGCallback != null) {
            tGCallback.onSuccess(obj);
        }
    }
}
