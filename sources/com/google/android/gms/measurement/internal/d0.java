package com.google.android.gms.measurement.internal;

import android.os.Process;
import androidx.annotation.GuardedBy;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
/* loaded from: classes10.dex */
public final class d0 extends Thread {
    public final Object h;
    public final BlockingQueue<c0<?>> i;
    @GuardedBy("threadLifeCycleLock")
    public boolean j = false;
    public final /* synthetic */ zzfp k;

    public d0(zzfp zzfpVar, String str, BlockingQueue<c0<?>> blockingQueue) {
        this.k = zzfpVar;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(blockingQueue);
        this.h = new Object();
        this.i = blockingQueue;
        setName(str);
    }

    public final void a() {
        synchronized (this.h) {
            this.h.notifyAll();
        }
    }

    public final void b() {
        Object obj;
        Semaphore semaphore;
        Object obj2;
        d0 d0Var;
        d0 d0Var2;
        obj = this.k.h;
        synchronized (obj) {
            if (!this.j) {
                semaphore = this.k.i;
                semaphore.release();
                obj2 = this.k.h;
                obj2.notifyAll();
                d0Var = this.k.b;
                if (this == d0Var) {
                    this.k.b = null;
                } else {
                    d0Var2 = this.k.c;
                    if (this == d0Var2) {
                        this.k.c = null;
                    } else {
                        this.k.zzs.zzay().zzd().zza("Current scheduler thread is neither worker nor network");
                    }
                }
                this.j = true;
            }
        }
    }

    public final void c(InterruptedException interruptedException) {
        this.k.zzs.zzay().zzk().zzb(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Semaphore semaphore;
        Object obj;
        boolean z = false;
        while (!z) {
            try {
                semaphore = this.k.i;
                semaphore.acquire();
                z = true;
            } catch (InterruptedException e) {
                c(e);
            }
        }
        try {
            int threadPriority = Process.getThreadPriority(Process.myTid());
            while (true) {
                c0<?> poll = this.i.poll();
                if (poll != null) {
                    Process.setThreadPriority(true != poll.i ? 10 : threadPriority);
                    poll.run();
                } else {
                    synchronized (this.h) {
                        if (this.i.peek() == null) {
                            zzfp.j(this.k);
                            try {
                                this.h.wait(30000L);
                            } catch (InterruptedException e2) {
                                c(e2);
                            }
                        }
                    }
                    obj = this.k.h;
                    synchronized (obj) {
                        if (this.i.peek() == null) {
                            break;
                        }
                    }
                }
            }
            if (this.k.zzs.zzf().zzs(null, zzdw.zzak)) {
                b();
            }
        } finally {
            b();
        }
    }
}
