package com.abupdate.mqtt_libs.mqttv3.a;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.a.c.u;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class e implements Runnable {
    public b j;
    public com.abupdate.mqtt_libs.mqttv3.a.c.g k;
    public a l;
    public f m;
    public String o;
    public Future q;
    public boolean h = false;
    public Object i = new Object();
    public Thread n = null;
    public final Semaphore p = new Semaphore(1);

    public e(a aVar, b bVar, f fVar, OutputStream outputStream) {
        this.j = null;
        this.l = null;
        this.m = null;
        this.k = new com.abupdate.mqtt_libs.mqttv3.a.c.g(bVar, outputStream);
        this.l = aVar;
        this.j = bVar;
        this.m = fVar;
    }

    public void a(String str, ExecutorService executorService) {
        this.o = str;
        synchronized (this.i) {
            if (!this.h) {
                this.h = true;
                this.q = executorService.submit(this);
            }
        }
    }

    public final void b(u uVar, Exception exc) {
        MqttException mqttException;
        if (!(exc instanceof MqttException)) {
            mqttException = new MqttException(32109, exc);
        } else {
            mqttException = (MqttException) exc;
        }
        this.h = false;
        this.l.a((com.abupdate.mqtt_libs.mqttv3.n) null, mqttException);
    }

    @Override // java.lang.Runnable
    public void run() {
        Thread currentThread = Thread.currentThread();
        this.n = currentThread;
        currentThread.setName(this.o);
        try {
            this.p.acquire();
            u uVar = null;
            while (this.h && this.k != null) {
                try {
                    try {
                        uVar = this.j.e();
                        if (uVar != null) {
                            if (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.b) {
                                this.k.a(uVar);
                                this.k.flush();
                            } else {
                                com.abupdate.mqtt_libs.mqttv3.n a2 = this.m.a(uVar);
                                if (a2 != null) {
                                    synchronized (a2) {
                                        this.k.a(uVar);
                                        try {
                                            this.k.flush();
                                        } catch (IOException e) {
                                            if (!(uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.e)) {
                                                throw e;
                                                break;
                                            }
                                        }
                                        this.j.c(uVar);
                                    }
                                } else {
                                    continue;
                                }
                            }
                        } else {
                            this.h = false;
                        }
                    } catch (MqttException e2) {
                        b(uVar, e2);
                    } catch (Exception e3) {
                        b(uVar, e3);
                    }
                } finally {
                    this.h = false;
                    this.p.release();
                }
            }
        } catch (InterruptedException unused) {
            this.h = false;
        }
    }

    public void a() {
        Semaphore semaphore;
        synchronized (this.i) {
            Future future = this.q;
            if (future != null) {
                future.cancel(true);
            }
            if (this.h) {
                this.h = false;
                if (!Thread.currentThread().equals(this.n)) {
                    while (this.h) {
                        try {
                            this.j.h();
                            this.p.tryAcquire(100L, TimeUnit.MILLISECONDS);
                        } catch (InterruptedException unused) {
                            semaphore = this.p;
                        }
                    }
                    semaphore = this.p;
                    semaphore.release();
                }
            }
            this.n = null;
        }
    }
}
