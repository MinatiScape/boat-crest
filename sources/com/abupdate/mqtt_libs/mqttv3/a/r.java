package com.abupdate.mqtt_libs.mqttv3.a;

import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.MqttMessage;
import com.abupdate.mqtt_libs.mqttv3.a.c.u;
import com.realsil.sdk.dfu.DfuConstants;
/* loaded from: classes.dex */
public class r {
    public String j;
    public volatile boolean b = false;
    public boolean c = false;
    public boolean d = false;
    public Object e = new Object();
    public Object f = new Object();

    /* renamed from: a  reason: collision with root package name */
    public MqttMessage f1959a = null;
    public u g = null;
    public MqttException h = null;
    public String[] i = null;
    public com.abupdate.mqtt_libs.mqttv3.c k = null;
    public IMqttActionListener l = null;
    public Object m = null;
    public int n = 0;
    public boolean o = false;

    public r(String str) {
    }

    public int a() {
        return this.n;
    }

    public boolean b() throws MqttException {
        if (c() == null) {
            return true;
        }
        throw c();
    }

    public MqttException c() {
        return this.h;
    }

    public boolean d() {
        return this.b;
    }

    public boolean e() {
        return this.c;
    }

    public IMqttActionListener f() {
        return this.l;
    }

    public void g() {
        synchronized (this.e) {
            if (this.h == null && this.c) {
                this.b = true;
                this.c = false;
            } else {
                this.c = false;
            }
            this.e.notifyAll();
        }
        synchronized (this.f) {
            this.d = true;
            this.f.notifyAll();
        }
    }

    public void h() throws MqttException {
        boolean z;
        synchronized (this.f) {
            synchronized (this.e) {
                MqttException mqttException = this.h;
                if (mqttException != null) {
                    throw mqttException;
                }
            }
            while (true) {
                z = this.d;
                if (z) {
                    break;
                }
                try {
                    this.f.wait();
                } catch (InterruptedException unused) {
                }
            }
            if (!z) {
                MqttException mqttException2 = this.h;
                if (mqttException2 == null) {
                    throw i.a(6);
                }
                throw mqttException2;
            }
        }
    }

    public void i() {
        synchronized (this.e) {
            this.g = null;
            this.b = false;
        }
        synchronized (this.f) {
            this.d = true;
            this.f.notifyAll();
        }
    }

    public com.abupdate.mqtt_libs.mqttv3.c j() {
        return this.k;
    }

    public MqttMessage k() {
        return this.f1959a;
    }

    public u l() {
        return this.g;
    }

    public String[] m() {
        return this.i;
    }

    public Object n() {
        return this.m;
    }

    public String o() {
        return this.j;
    }

    public boolean p() {
        return this.o;
    }

    public int[] q() {
        int[] iArr = new int[0];
        u uVar = this.g;
        return uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.q ? ((com.abupdate.mqtt_libs.mqttv3.a.c.q) uVar).f() : iArr;
    }

    public boolean r() {
        u uVar = this.g;
        if (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.c) {
            return ((com.abupdate.mqtt_libs.mqttv3.a.c.c) uVar).f();
        }
        return false;
    }

    public u s() {
        return this.g;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=");
        stringBuffer.append(o());
        stringBuffer.append(" ,topics=");
        if (m() != null) {
            for (int i = 0; i < m().length; i++) {
                stringBuffer.append(m()[i]);
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(" ,usercontext=");
        stringBuffer.append(n());
        stringBuffer.append(" ,isComplete=");
        stringBuffer.append(d());
        stringBuffer.append(" ,isNotified=");
        stringBuffer.append(p());
        stringBuffer.append(" ,exception=");
        stringBuffer.append(c());
        stringBuffer.append(" ,actioncallback=");
        stringBuffer.append(f());
        return stringBuffer.toString();
    }

    public void a(int i) {
        this.n = i;
    }

    public void a(IMqttActionListener iMqttActionListener) {
        this.l = iMqttActionListener;
    }

    public u b(long j) throws MqttException {
        synchronized (this.e) {
            while (!this.b) {
                if (this.h == null) {
                    if (j <= 0) {
                        try {
                            this.e.wait();
                        } catch (InterruptedException e) {
                            this.h = new MqttException(e);
                        }
                    } else {
                        this.e.wait(j);
                    }
                }
                if (!this.b) {
                    MqttException mqttException = this.h;
                    if (mqttException != null) {
                        throw mqttException;
                    }
                    if (j > 0) {
                        break;
                    }
                }
            }
        }
        return this.g;
    }

    public void a(long j) throws MqttException {
        if (b(j) == null && !this.b) {
            MqttException mqttException = new MqttException((int) DfuConstants.MAX_CONNECTION_LOCK_TIMEOUT);
            this.h = mqttException;
            throw mqttException;
        }
        b();
    }

    public void a(u uVar, MqttException mqttException) {
        synchronized (this.e) {
            if (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.b) {
                this.f1959a = null;
            }
            this.c = true;
            this.g = uVar;
            this.h = mqttException;
        }
    }

    public void a(com.abupdate.mqtt_libs.mqttv3.c cVar) {
        this.k = cVar;
    }

    public void a(MqttMessage mqttMessage) {
        this.f1959a = mqttMessage;
    }

    public void a(String[] strArr) {
        this.i = strArr;
    }

    public void a(Object obj) {
        this.m = obj;
    }

    public void a(String str) {
        this.j = str;
    }

    public void a(MqttException mqttException) {
        synchronized (this.e) {
            this.h = mqttException;
        }
    }

    public void a(boolean z) {
        this.o = z;
    }
}
