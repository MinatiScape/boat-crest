package com.goodix.ble.libcomx.trx;

import com.goodix.ble.libcomx.event.Event;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes5.dex */
public class TrxChain implements ITrx {

    /* renamed from: a  reason: collision with root package name */
    public CopyOnWriteArrayList<TrxCtx> f8047a = new CopyOnWriteArrayList<>();
    public boolean b = false;
    public TrxCtx c = null;
    public TrxCtx d = new TrxCtx("ChainTail", new TrxChainTail(), this);
    public Event<Boolean> e = new Event<>(this, -274142511);
    public Event<Object> f = new Event<>(this, ITrx.EVT_TX);
    public Event<Object> g = new Event<>(this, ITrx.EVT_RX);

    public TrxChain addFirst(String str, TrxHandler trxHandler) {
        CopyOnWriteArrayList<TrxCtx> copyOnWriteArrayList = this.f8047a;
        TrxCtx trxCtx = new TrxCtx(str, trxHandler, this);
        trxCtx.d = this.c;
        trxCtx.e = this.d;
        if (!copyOnWriteArrayList.isEmpty()) {
            TrxCtx trxCtx2 = copyOnWriteArrayList.get(0);
            trxCtx2.d = trxCtx;
            trxCtx.e = trxCtx2;
        }
        copyOnWriteArrayList.add(0, trxCtx);
        trxCtx.a();
        return this;
    }

    public TrxChain addLast(String str, TrxHandler trxHandler) {
        CopyOnWriteArrayList<TrxCtx> copyOnWriteArrayList = this.f8047a;
        TrxCtx trxCtx = new TrxCtx(str, trxHandler, this);
        trxCtx.d = this.c;
        trxCtx.e = this.d;
        if (!copyOnWriteArrayList.isEmpty()) {
            TrxCtx trxCtx2 = copyOnWriteArrayList.get(copyOnWriteArrayList.size() - 1);
            trxCtx2.e = trxCtx;
            trxCtx.d = trxCtx2;
        }
        copyOnWriteArrayList.add(trxCtx);
        trxCtx.a();
        return this;
    }

    public void clear() {
        CopyOnWriteArrayList<TrxCtx> copyOnWriteArrayList = this.f8047a;
        Iterator<TrxCtx> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            it.next().b();
        }
        copyOnWriteArrayList.clear();
    }

    @Override // com.goodix.ble.libcomx.trx.ITrx
    public Event<Boolean> evtReady() {
        return this.e;
    }

    @Override // com.goodix.ble.libcomx.trx.ITrx
    public Event<Object> evtRx() {
        return this.g;
    }

    @Override // com.goodix.ble.libcomx.trx.ITrx
    public Event<Object> evtTx() {
        return this.f;
    }

    public TrxCtx get(String str) {
        if (str != null) {
            Iterator<TrxCtx> it = this.f8047a.iterator();
            while (it.hasNext()) {
                TrxCtx next = it.next();
                if (str.equals(next.c)) {
                    return next;
                }
            }
            return null;
        }
        return null;
    }

    @Override // com.goodix.ble.libcomx.trx.ITrx
    public boolean isReady() {
        return this.b;
    }

    @Deprecated
    public void receive(Object obj) {
        CopyOnWriteArrayList<TrxCtx> copyOnWriteArrayList = this.f8047a;
        if (copyOnWriteArrayList.isEmpty()) {
            return;
        }
        copyOnWriteArrayList.get(0).e(obj);
    }

    @Override // com.goodix.ble.libcomx.trx.ITrx
    public void send(Object obj) {
        CopyOnWriteArrayList<TrxCtx> copyOnWriteArrayList = this.f8047a;
        if (copyOnWriteArrayList.isEmpty()) {
            return;
        }
        copyOnWriteArrayList.get(copyOnWriteArrayList.size() - 1).f(obj);
    }

    public void setReady(boolean z) {
        boolean z2;
        synchronized (this) {
            if (this.b != z) {
                this.b = z;
                z2 = true;
            } else {
                z2 = false;
            }
        }
        if (z2) {
            Iterator<TrxCtx> it = this.f8047a.iterator();
            while (it.hasNext()) {
                it.next().g(z);
            }
        }
    }
}
