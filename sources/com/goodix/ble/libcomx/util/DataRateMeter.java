package com.goodix.ble.libcomx.util;

import com.goodix.ble.libcomx.event.Event;
import com.goodix.ble.libcomx.event.IEventListener;
/* loaded from: classes6.dex */
public class DataRateMeter implements Runnable, IEventListener<byte[]> {
    public static final int EVT_UPDATED = 247;
    public static final String s = DataRateMeter.class.getSimpleName();
    public Thread h;
    public long l;
    public int m;
    public int n;
    public int o;
    public long q;
    public long r;
    public long i = 1000;
    public int j = 3;
    public Event<Void> k = new Event<>(this, 247);
    public boolean p = false;

    public Event<Void> evtUpdated() {
        return this.k;
    }

    public long getElapsedTime() {
        long j;
        long j2;
        if (this.p) {
            j = System.currentTimeMillis();
            j2 = this.q;
        } else {
            j = this.r;
            j2 = this.q;
        }
        return j - j2;
    }

    public int getSpeed() {
        return this.n;
    }

    public int getSpeedAvg() {
        return this.o;
    }

    public long getTotalByteCnt() {
        return this.l;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i;
        long j;
        boolean z;
        Thread thread = this.h;
        int i2 = this.j;
        int i3 = 0;
        while (thread != null) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                Thread.sleep(this.i);
                long currentTimeMillis2 = System.currentTimeMillis();
                long j2 = currentTimeMillis2 - currentTimeMillis;
                if (!this.p) {
                    synchronized (this) {
                        this.h = null;
                    }
                } else {
                    synchronized (this) {
                        i = this.m;
                        this.m = 0;
                        j = this.l;
                        z = this.p;
                    }
                    if (z) {
                        this.o = (int) ((j * 1000) / (currentTimeMillis2 - this.q));
                        if (j2 > 0) {
                            this.n = (int) ((i * 1000) / j2);
                        } else {
                            this.n = 0;
                        }
                        this.k.postEvent(null);
                    }
                    if (i2 <= 0) {
                        continue;
                    } else {
                        i3 = this.n > 0 ? 0 : i3 + 1;
                        if (i3 > i2) {
                            synchronized (this) {
                                this.h = null;
                            }
                        } else {
                            continue;
                        }
                    }
                }
                thread = null;
            } catch (InterruptedException unused) {
                synchronized (this) {
                    this.h = null;
                    return;
                }
            }
        }
    }

    public void setMaxIdleCnt(int i) {
        this.j = i;
    }

    public void setUpdatePeriod(long j) {
        if (j > 0) {
            this.i = j;
        }
    }

    public synchronized void start() {
        this.l = 0L;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        long currentTimeMillis = System.currentTimeMillis();
        this.q = currentTimeMillis;
        this.r = currentTimeMillis;
        this.p = true;
    }

    public synchronized void stop() {
        this.p = false;
        long currentTimeMillis = System.currentTimeMillis();
        this.r = currentTimeMillis;
        this.o = (int) ((this.l * 1000) / (currentTimeMillis - this.q));
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, byte[] bArr) {
        if (bArr == null || !this.p) {
            return;
        }
        synchronized (this) {
            if (this.p) {
                this.m += bArr.length;
                this.l += bArr.length;
                if (this.h == null) {
                    Thread thread = new Thread(this, s);
                    this.h = thread;
                    thread.start();
                }
            }
        }
    }
}
