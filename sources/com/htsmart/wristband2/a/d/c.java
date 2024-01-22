package com.htsmart.wristband2.a.d;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import com.htsmart.wristband2.a.a.m;
import com.htsmart.wristband2.bean.ConnectionState;
import com.htsmart.wristband2.exceptions.AckException;
import com.htsmart.wristband2.packet.PacketData;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes11.dex */
public abstract class c extends com.htsmart.wristband2.a.c.a {
    public final ArrayBlockingQueue<byte[]> A;
    public final Scheduler B;
    public ConnectionState C;
    public String D;
    public final PublishSubject<PacketData> E;
    public final PublishSubject<byte[]> F;
    public final PublishSubject<BleDisconnectedException> G;
    public final Function<BleDisconnectedException, ObservableSource<?>> H;
    public boolean t;
    public final d u = new d();
    public final ReentrantLock v;
    public final Condition w;
    public boolean x;
    public volatile boolean y;
    public final AtomicInteger z;

    public c() {
        ReentrantLock reentrantLock = new ReentrantLock(true);
        this.v = reentrantLock;
        this.w = reentrantLock.newCondition();
        this.z = new AtomicInteger();
        this.A = new ArrayBlockingQueue<>(64);
        this.B = Schedulers.newThread();
        this.C = ConnectionState.DISCONNECTED;
        this.E = PublishSubject.create();
        this.F = PublishSubject.create();
        this.G = PublishSubject.create();
        this.H = new Function() { // from class: com.htsmart.wristband2.a.d.e
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Observable.error((BleDisconnectedException) obj);
            }
        };
        Executors.newSingleThreadExecutor().submit(new Runnable() { // from class: com.htsmart.wristband2.a.d.g
            @Override // java.lang.Runnable
            public final void run() {
                c.this.S();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void P(long j, PacketData packetData) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.E.onNext(packetData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void R() {
        this.v.lock();
        try {
            this.w.signalAll();
        } finally {
            try {
            } finally {
            }
        }
    }

    public final void Q(boolean z, int i) {
        try {
            c(d.a(z, i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0066 A[Catch: all -> 0x0118, TryCatch #2 {all -> 0x0118, blocks: (B:24:0x005f, B:26:0x0066, B:28:0x006a, B:30:0x0076, B:31:0x0084, B:34:0x00af, B:35:0x00b5, B:37:0x00bd, B:39:0x00ce, B:41:0x00d1, B:43:0x00d7, B:44:0x00df, B:46:0x00e5, B:49:0x010e), top: B:62:0x005f }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b5 A[Catch: all -> 0x0118, TryCatch #2 {all -> 0x0118, blocks: (B:24:0x005f, B:26:0x0066, B:28:0x006a, B:30:0x0076, B:31:0x0084, B:34:0x00af, B:35:0x00b5, B:37:0x00bd, B:39:0x00ce, B:41:0x00d1, B:43:0x00d7, B:44:0x00df, B:46:0x00e5, B:49:0x010e), top: B:62:0x005f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void S() {
        /*
            Method dump skipped, instructions count: 305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htsmart.wristband2.a.d.c.S():void");
    }

    @Override // com.htsmart.wristband2.a.b.a
    @CallSuper
    public void a(ConnectionState connectionState) {
        ConnectionState connectionState2 = ConnectionState.CONNECTED;
        if (connectionState == connectionState2) {
            this.z.set(1);
            this.y = false;
            this.D = getConnectedAddress();
        } else if (connectionState == ConnectionState.DISCONNECTED) {
            if (this.C == connectionState2) {
                new Thread(new Runnable() { // from class: com.htsmart.wristband2.a.d.f
                    @Override // java.lang.Runnable
                    public final void run() {
                        c.this.R();
                    }
                }).start();
            }
            this.G.onNext(new BleDisconnectedException(this.D));
        }
        this.C = connectionState;
    }

    public void a(final PacketData packetData, final long j) {
        new Thread(new Runnable() { // from class: com.htsmart.wristband2.a.d.h
            @Override // java.lang.Runnable
            public final void run() {
                c.this.P(j, packetData);
            }
        }).start();
    }

    public void a(PacketData packetData, m mVar) throws Exception {
        byte[] a2 = a.a(packetData.getCmdId(), b.a(packetData.getKeyId(), packetData.getKeyData()));
        this.v.lock();
        while (true) {
            try {
                if ((this.t || this.u.d()) && !mVar.b()) {
                    this.w.await(500L, TimeUnit.MILLISECONDS);
                }
            } finally {
                this.v.unlock();
            }
        }
        if (!mVar.b()) {
            int i = this.z.get();
            this.t = true;
            byte[] c = d.c(a2, i);
            this.x = false;
            Exception e = null;
            int i2 = 0;
            do {
                try {
                    c(c);
                    this.w.await(5000L, TimeUnit.MILLISECONDS);
                } catch (Exception e2) {
                    e = e2;
                    i2 = 3;
                }
                i2++;
                if (this.x || i2 >= 3) {
                    break;
                }
            } while (!mVar.b());
            this.t = false;
            this.z.incrementAndGet();
            if (e != null) {
                throw e;
            }
            if (!this.x) {
                throw new AckException(c);
            }
        }
    }

    public void a(boolean z) {
        this.y = z;
    }

    @Override // com.htsmart.wristband2.a.c.a
    public final void a(@NonNull byte[] bArr) {
        if (this.y) {
            this.F.onNext(bArr);
            return;
        }
        try {
            this.A.put(bArr);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Observable<PacketData> j() {
        return Observable.merge(this.G.flatMap(this.H), this.E).observeOn(this.B);
    }

    public Observable<PacketData> k() {
        return this.E.observeOn(this.B);
    }

    public Observable<byte[]> l() {
        return Observable.merge(this.G.flatMap(this.H), this.F).observeOn(this.B);
    }

    public Scheduler m() {
        return this.B;
    }
}
