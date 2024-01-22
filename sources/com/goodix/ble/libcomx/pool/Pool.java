package com.goodix.ble.libcomx.pool;

import java.util.ArrayList;
/* loaded from: classes5.dex */
public class Pool {

    /* renamed from: a  reason: collision with root package name */
    public Class<? extends IRecyclable> f8040a;
    public ArrayList<IRecyclable> b;
    public int c = 0;

    public Pool(Class<? extends IRecyclable> cls, int i) {
        this.f8040a = cls;
        this.b = new ArrayList<>(i);
    }

    public synchronized void a(IRecyclable iRecyclable) {
        this.b.add(iRecyclable);
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [com.goodix.ble.libcomx.pool.IRecyclable] */
    /* JADX WARN: Type inference failed for: r1v9, types: [com.goodix.ble.libcomx.pool.IRecyclable] */
    public synchronized <T> T get() {
        T t;
        Object obj = null;
        if (this.b.isEmpty()) {
            try {
                ?? r1 = (T) this.f8040a.newInstance();
                try {
                    r1.reuse(this);
                    this.c++;
                    t = r1;
                } catch (IllegalAccessException | InstantiationException unused) {
                    obj = r1;
                    t = (T) obj;
                    return t;
                }
            } catch (IllegalAccessException | InstantiationException unused2) {
            }
        } else {
            int size = this.b.size() - 1;
            while (size > 0 && this.b.get(size).getRefCnt() >= 1) {
                size--;
            }
            ?? r12 = (T) this.b.remove(size);
            r12.reuse(this);
            t = r12;
        }
        return t;
    }
}
