package com.goodix.ble.libcomx.pool;
/* loaded from: classes5.dex */
public class AbsPoolItem implements IRecyclable {

    /* renamed from: a  reason: collision with root package name */
    public Pool f8039a;
    public int b = 0;

    @Override // com.goodix.ble.libcomx.pool.IRecyclable
    public int getRefCnt() {
        return this.b;
    }

    @Override // com.goodix.ble.libcomx.pool.IRecyclable
    public synchronized void release() {
        int i = this.b - 1;
        this.b = i;
        if (i < 1) {
            this.f8039a.a(this);
        }
    }

    @Override // com.goodix.ble.libcomx.pool.IRecyclable
    public synchronized void retain() {
        this.b++;
    }

    @Override // com.goodix.ble.libcomx.pool.IRecyclable
    public void reuse(Pool pool) {
        this.f8039a = pool;
        this.b = 0;
    }
}
