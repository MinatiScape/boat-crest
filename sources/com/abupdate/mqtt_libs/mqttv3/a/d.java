package com.abupdate.mqtt_libs.mqttv3.a;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.a.c.u;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
/* loaded from: classes.dex */
public class d implements Runnable {
    public b j;
    public a k;
    public com.abupdate.mqtt_libs.mqttv3.a.c.f l;
    public f m;
    public String p;
    public Future q;
    public boolean h = false;
    public Object i = new Object();
    public Thread n = null;
    public final Semaphore o = new Semaphore(1);

    public d(a aVar, b bVar, f fVar, InputStream inputStream) {
        this.j = null;
        this.k = null;
        this.m = null;
        this.l = new com.abupdate.mqtt_libs.mqttv3.a.c.f(bVar, inputStream);
        this.k = aVar;
        this.j = bVar;
        this.m = fVar;
    }

    public void a(String str, ExecutorService executorService) {
        this.p = str;
        synchronized (this.i) {
            if (!this.h) {
                this.h = true;
                this.q = executorService.submit(this);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        com.abupdate.mqtt_libs.mqttv3.a.c.f fVar;
        Thread currentThread = Thread.currentThread();
        this.n = currentThread;
        currentThread.setName(this.p);
        try {
            this.o.acquire();
            com.abupdate.mqtt_libs.mqttv3.n nVar = null;
            while (this.h && (fVar = this.l) != null) {
                try {
                    try {
                        fVar.available();
                        u a2 = this.l.a();
                        if (a2 instanceof com.abupdate.mqtt_libs.mqttv3.a.c.b) {
                            nVar = this.m.a(a2);
                            if (nVar != null) {
                                synchronized (nVar) {
                                    this.j.a((com.abupdate.mqtt_libs.mqttv3.a.c.b) a2);
                                }
                            } else if (!(a2 instanceof com.abupdate.mqtt_libs.mqttv3.a.c.m) && !(a2 instanceof com.abupdate.mqtt_libs.mqttv3.a.c.l) && !(a2 instanceof com.abupdate.mqtt_libs.mqttv3.a.c.k)) {
                                throw new MqttException(6);
                            }
                        } else if (a2 != null) {
                            this.j.d(a2);
                        }
                    } finally {
                        this.o.release();
                    }
                } catch (MqttException e) {
                    this.h = false;
                    this.k.a(nVar, e);
                } catch (IOException e2) {
                    this.h = false;
                    if (!this.k.d()) {
                        this.k.a(nVar, new MqttException(32109, e2));
                    }
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
                    try {
                        this.o.acquire();
                        semaphore = this.o;
                    } catch (InterruptedException unused) {
                        semaphore = this.o;
                    } catch (Throwable th) {
                        this.o.release();
                        throw th;
                    }
                    semaphore.release();
                }
            }
        }
        this.n = null;
    }
}
