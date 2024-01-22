package com.abupdate.mqtt_libs.mqttv3.a;

import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.IMqttToken;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
/* loaded from: classes.dex */
public class g implements IMqttActionListener {

    /* renamed from: a  reason: collision with root package name */
    public com.abupdate.mqtt_libs.mqttv3.g f1954a;
    public com.abupdate.mqtt_libs.mqttv3.e b;
    public a c;
    public com.abupdate.mqtt_libs.mqttv3.h d;
    public com.abupdate.mqtt_libs.mqttv3.n e;
    public Object f;
    public IMqttActionListener g;
    public int h;
    public com.abupdate.mqtt_libs.mqttv3.f i;
    public boolean j;

    public g(com.abupdate.mqtt_libs.mqttv3.e eVar, com.abupdate.mqtt_libs.mqttv3.g gVar, a aVar, com.abupdate.mqtt_libs.mqttv3.h hVar, com.abupdate.mqtt_libs.mqttv3.n nVar, Object obj, IMqttActionListener iMqttActionListener, boolean z) {
        this.f1954a = gVar;
        this.b = eVar;
        this.c = aVar;
        this.d = hVar;
        this.e = nVar;
        this.f = obj;
        this.g = iMqttActionListener;
        this.h = hVar.d();
        this.j = z;
    }

    public void a() throws com.abupdate.mqtt_libs.mqttv3.k {
        com.abupdate.mqtt_libs.mqttv3.n nVar = new com.abupdate.mqtt_libs.mqttv3.n(this.b.getClientId());
        nVar.setActionCallback(this);
        nVar.setUserContext(this);
        this.f1954a.a(this.b.getClientId(), this.b.getServerURI());
        if (this.d.l()) {
            this.f1954a.c();
        }
        if (this.d.d() == 0) {
            this.d.c(4);
        }
        try {
            this.c.a(this.d, nVar);
        } catch (MqttException e) {
            onFailure(nVar, e);
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttActionListener
    public void onFailure(IMqttToken iMqttToken, Throwable th) {
        MqttException mqttException;
        int length = this.c.g().length;
        int f = this.c.f() + 1;
        if (f >= length && (this.h != 0 || this.d.d() != 4)) {
            if (this.h == 0) {
                this.d.c(0);
            }
            if (th instanceof MqttException) {
                mqttException = (MqttException) th;
            } else {
                mqttException = new MqttException(th);
            }
            this.e.f1970a.a(null, mqttException);
            this.e.f1970a.g();
            this.e.f1970a.a((com.abupdate.mqtt_libs.mqttv3.c) this.b);
            if (this.g != null) {
                this.e.setUserContext(this.f);
                this.g.onFailure(this.e, th);
                return;
            }
            return;
        }
        if (this.h == 0) {
            if (this.d.d() == 4) {
                this.d.c(3);
            } else {
                this.d.c(4);
                this.c.a(f);
            }
        } else {
            this.c.a(f);
        }
        try {
            a();
        } catch (com.abupdate.mqtt_libs.mqttv3.k e) {
            onFailure(iMqttToken, e);
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttActionListener
    public void onSuccess(IMqttToken iMqttToken) {
        if (this.h == 0) {
            this.d.c(0);
        }
        this.e.f1970a.a(iMqttToken.getResponse(), null);
        this.e.f1970a.g();
        this.e.f1970a.a((com.abupdate.mqtt_libs.mqttv3.c) this.b);
        this.c.l();
        if (this.g != null) {
            this.e.setUserContext(this.f);
            this.g.onSuccess(this.e);
        }
        if (this.i != null) {
            this.i.a(this.j, this.c.g()[this.c.f()].e());
        }
    }

    public void a(com.abupdate.mqtt_libs.mqttv3.f fVar) {
        this.i = fVar;
    }
}
