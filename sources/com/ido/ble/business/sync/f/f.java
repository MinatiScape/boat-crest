package com.ido.ble.business.sync.f;

import com.ido.ble.business.sync.ISyncDataListener;
/* loaded from: classes11.dex */
public abstract class f {

    /* renamed from: a  reason: collision with root package name */
    public a f12141a;
    public ISyncDataListener b;
    public boolean c;
    public boolean d;
    private int e;

    public int a() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public void a(ISyncDataListener iSyncDataListener) {
        this.b = iSyncDataListener;
    }

    public void a(a aVar) {
        this.f12141a = aVar;
    }

    public void b() {
        this.f12141a = null;
        this.b = null;
    }

    public abstract void c();

    public abstract void d();
}
