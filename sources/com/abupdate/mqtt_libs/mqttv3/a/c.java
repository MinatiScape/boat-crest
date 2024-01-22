package com.abupdate.mqtt_libs.mqttv3.a;

import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.MqttCallback;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.MqttMessage;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
/* loaded from: classes.dex */
public class c implements Runnable {
    public MqttCallback h;
    public com.abupdate.mqtt_libs.mqttv3.f i;
    public a k;
    public Thread p;
    public b s;
    public String u;
    public Future w;

    /* renamed from: a  reason: collision with root package name */
    public boolean f1950a = false;
    public boolean n = false;
    public Object o = new Object();
    public Object q = new Object();
    public Object r = new Object();
    public boolean t = false;
    public final Semaphore v = new Semaphore(1);
    public Vector l = new Vector(10);
    public Vector m = new Vector(10);
    public Hashtable j = new Hashtable();

    public c(a aVar) {
        this.k = aVar;
    }

    public void a(b bVar) {
        this.s = bVar;
    }

    public void b() {
        this.n = true;
        synchronized (this.r) {
            this.r.notifyAll();
        }
    }

    public boolean c() {
        return this.n && this.m.size() == 0 && this.l.size() == 0;
    }

    public final void d(com.abupdate.mqtt_libs.mqttv3.n nVar) throws MqttException {
        synchronized (nVar) {
            if (nVar.isComplete()) {
                this.s.a(nVar);
            }
            nVar.f1970a.g();
            if (!nVar.f1970a.p()) {
                if (this.h != null && (nVar instanceof com.abupdate.mqtt_libs.mqttv3.i) && nVar.isComplete()) {
                    this.h.deliveryComplete((com.abupdate.mqtt_libs.mqttv3.i) nVar);
                }
                a(nVar);
            }
            if (nVar.isComplete() && ((nVar instanceof com.abupdate.mqtt_libs.mqttv3.i) || (nVar.getActionCallback() instanceof IMqttActionListener))) {
                nVar.f1970a.a(true);
            }
        }
    }

    public void e() {
        this.j.clear();
    }

    @Override // java.lang.Runnable
    public void run() {
        com.abupdate.mqtt_libs.mqttv3.n nVar;
        com.abupdate.mqtt_libs.mqttv3.a.c.o oVar;
        Thread currentThread = Thread.currentThread();
        this.p = currentThread;
        currentThread.setName(this.u);
        try {
            this.v.acquire();
            while (this.f1950a) {
                try {
                    try {
                        synchronized (this.q) {
                            if (this.f1950a && this.l.isEmpty() && this.m.isEmpty()) {
                                this.q.wait();
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            this.f1950a = false;
                            this.k.a((com.abupdate.mqtt_libs.mqttv3.n) null, new MqttException(th));
                            this.v.release();
                            synchronized (this.r) {
                                this.r.notifyAll();
                            }
                        } catch (Throwable th2) {
                            this.v.release();
                            synchronized (this.r) {
                                this.r.notifyAll();
                                throw th2;
                            }
                        }
                    }
                } catch (InterruptedException unused) {
                }
                if (this.f1950a) {
                    synchronized (this.m) {
                        if (this.m.isEmpty()) {
                            nVar = null;
                        } else {
                            nVar = (com.abupdate.mqtt_libs.mqttv3.n) this.m.elementAt(0);
                            this.m.removeElementAt(0);
                        }
                    }
                    if (nVar != null) {
                        d(nVar);
                    }
                    synchronized (this.l) {
                        if (this.l.isEmpty()) {
                            oVar = null;
                        } else {
                            oVar = (com.abupdate.mqtt_libs.mqttv3.a.c.o) this.l.elementAt(0);
                            this.l.removeElementAt(0);
                        }
                    }
                    if (oVar != null) {
                        c(oVar);
                    }
                }
                if (this.n) {
                    this.s.f();
                }
                this.v.release();
                synchronized (this.r) {
                    this.r.notifyAll();
                }
            }
        } catch (InterruptedException unused2) {
            this.f1950a = false;
        }
    }

    public void a(String str, ExecutorService executorService) {
        this.u = str;
        synchronized (this.o) {
            if (!this.f1950a) {
                this.l.clear();
                this.m.clear();
                this.f1950a = true;
                this.n = false;
                this.w = executorService.submit(this);
            }
        }
    }

    public final void c(com.abupdate.mqtt_libs.mqttv3.a.c.o oVar) throws MqttException, Exception {
        a(oVar.g(), oVar.j(), oVar.h());
        if (this.t) {
            return;
        }
        if (oVar.h().getQos() == 1) {
            this.k.d(new com.abupdate.mqtt_libs.mqttv3.a.c.k(oVar), new com.abupdate.mqtt_libs.mqttv3.n(this.k.i().getClientId()));
        } else if (oVar.h().getQos() == 2) {
            this.k.a(oVar);
            com.abupdate.mqtt_libs.mqttv3.a.c.l lVar = new com.abupdate.mqtt_libs.mqttv3.a.c.l(oVar);
            a aVar = this.k;
            aVar.d(lVar, new com.abupdate.mqtt_libs.mqttv3.n(aVar.i().getClientId()));
        }
    }

    public void b(com.abupdate.mqtt_libs.mqttv3.n nVar) {
        if (this.f1950a) {
            this.m.addElement(nVar);
            synchronized (this.q) {
                this.q.notifyAll();
            }
            return;
        }
        try {
            d(nVar);
        } catch (Throwable th) {
            this.k.a((com.abupdate.mqtt_libs.mqttv3.n) null, new MqttException(th));
        }
    }

    public void a() {
        Semaphore semaphore;
        synchronized (this.o) {
            Future future = this.w;
            if (future != null) {
                future.cancel(true);
            }
            if (this.f1950a) {
                this.f1950a = false;
                if (!Thread.currentThread().equals(this.p)) {
                    try {
                        synchronized (this.q) {
                            this.q.notifyAll();
                        }
                        this.v.acquire();
                        semaphore = this.v;
                    } catch (InterruptedException unused) {
                        semaphore = this.v;
                    }
                    semaphore.release();
                }
            }
            this.p = null;
        }
    }

    public Thread d() {
        return this.p;
    }

    public void a(MqttCallback mqttCallback) {
        this.h = mqttCallback;
    }

    public void a(com.abupdate.mqtt_libs.mqttv3.f fVar) {
        this.i = fVar;
    }

    public void a(MqttException mqttException) {
        try {
            MqttCallback mqttCallback = this.h;
            if (mqttCallback != null && mqttException != null) {
                mqttCallback.connectionLost(mqttException);
            }
            com.abupdate.mqtt_libs.mqttv3.f fVar = this.i;
            if (fVar == null || mqttException == null) {
                return;
            }
            fVar.connectionLost(mqttException);
        } catch (Throwable unused) {
        }
    }

    public void a(com.abupdate.mqtt_libs.mqttv3.n nVar) {
        IMqttActionListener actionCallback;
        if (nVar == null || (actionCallback = nVar.getActionCallback()) == null) {
            return;
        }
        if (nVar.getException() == null) {
            actionCallback.onSuccess(nVar);
        } else {
            actionCallback.onFailure(nVar, nVar.getException());
        }
    }

    public void a(com.abupdate.mqtt_libs.mqttv3.a.c.o oVar) {
        if (this.h != null || this.j.size() > 0) {
            synchronized (this.r) {
                while (this.f1950a && !this.n && this.l.size() >= 10) {
                    try {
                        this.r.wait(200L);
                    } catch (InterruptedException unused) {
                    }
                }
            }
            if (this.n) {
                return;
            }
            this.l.addElement(oVar);
            synchronized (this.q) {
                this.q.notifyAll();
            }
        }
    }

    public void a(String str, com.abupdate.mqtt_libs.mqttv3.d dVar) {
        this.j.put(str, dVar);
    }

    public void a(String str) {
        this.j.remove(str);
    }

    public boolean a(String str, int i, MqttMessage mqttMessage) throws Exception {
        Enumeration keys = this.j.keys();
        boolean z = false;
        while (keys.hasMoreElements()) {
            String str2 = (String) keys.nextElement();
            if (com.abupdate.mqtt_libs.mqttv3.o.a(str2, str)) {
                mqttMessage.setId(i);
                ((com.abupdate.mqtt_libs.mqttv3.d) this.j.get(str2)).a(str, mqttMessage);
                z = true;
            }
        }
        if (this.h == null || z) {
            return z;
        }
        mqttMessage.setId(i);
        this.h.messageArrived(str, mqttMessage);
        return true;
    }
}
