package com.goodix.ble.libcomx.trx;
/* loaded from: classes5.dex */
public class TrxCtx {

    /* renamed from: a  reason: collision with root package name */
    public final TrxChain f8048a;
    public final TrxHandler b;
    public final String c;
    public TrxCtx d;
    public TrxCtx e;
    public boolean f;
    public boolean g;

    public TrxCtx(String str, TrxHandler trxHandler, TrxChain trxChain) {
        this.c = str;
        this.b = trxHandler;
        this.f8048a = trxChain;
    }

    public void a() {
        boolean z;
        synchronized (this) {
            z = true;
            if (this.g) {
                z = false;
            } else {
                this.g = true;
            }
        }
        if (z) {
            this.b.onCreate(this);
        }
    }

    public synchronized void b() {
        boolean z;
        synchronized (this) {
            z = false;
            if (this.g) {
                this.g = false;
                z = true;
            }
        }
        if (z) {
            this.b.onDestroy(this);
        }
    }

    public void c(Object obj) {
        if (this.f) {
            notifyRxComplete(obj);
            return;
        }
        try {
            this.b.onPostRxComplete(this, obj);
        } catch (Throwable th) {
            this.b.onException(this, th);
        }
    }

    public void d(Object obj) {
        if (this.f) {
            notifyTxComplete(obj);
            return;
        }
        try {
            this.b.onPostTxComplete(this, obj);
        } catch (Throwable th) {
            this.b.onException(this, th);
        }
    }

    public void e(Object obj) {
        if (this.f) {
            postRx(obj);
            return;
        }
        try {
            this.b.onRx(this, obj);
        } catch (Throwable th) {
            this.b.onException(this, th);
        }
    }

    public void f(Object obj) {
        if (this.f) {
            postTx(obj);
            return;
        }
        try {
            this.b.onTx(this, obj);
        } catch (Throwable th) {
            this.b.onException(this, th);
        }
    }

    public void g(boolean z) {
        this.b.onReady(this, z);
    }

    public TrxChain getChain() {
        return this.f8048a;
    }

    public TrxHandler getHandler() {
        return this.b;
    }

    public boolean isAvailable() {
        return this.g;
    }

    public boolean isPause() {
        return this.f;
    }

    public void notifyRxComplete(Object obj) {
        TrxCtx trxCtx = this.d;
        if (trxCtx != null) {
            trxCtx.c(obj);
        }
    }

    public void notifyTxComplete(Object obj) {
        TrxCtx trxCtx = this.e;
        if (trxCtx != null) {
            trxCtx.d(obj);
        }
    }

    public void postRx(Object obj) {
        TrxCtx trxCtx = this.e;
        if (trxCtx != null) {
            trxCtx.e(obj);
        } else {
            notifyRxComplete(obj);
        }
    }

    public void postTx(Object obj) {
        TrxCtx trxCtx = this.d;
        if (trxCtx != null) {
            trxCtx.f(obj);
        } else {
            notifyTxComplete(obj);
        }
    }

    public void send(Object obj) {
        this.f8048a.send(obj);
    }

    public void setPause(boolean z) {
        this.f = z;
        this.b.onPause(this, z);
    }
}
