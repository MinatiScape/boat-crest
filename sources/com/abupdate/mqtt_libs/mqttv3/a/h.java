package com.abupdate.mqtt_libs.mqttv3.a;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.a.c.u;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class h implements Runnable {
    public com.abupdate.mqtt_libs.mqttv3.b h;
    public k k;
    public Object j = new Object();
    public ArrayList i = new ArrayList();

    public h(com.abupdate.mqtt_libs.mqttv3.b bVar) {
        this.h = bVar;
    }

    public void a(u uVar, com.abupdate.mqtt_libs.mqttv3.n nVar) throws MqttException {
        com.abupdate.mqtt_libs.mqttv3.a aVar = new com.abupdate.mqtt_libs.mqttv3.a(uVar, nVar);
        synchronized (this.j) {
            if (this.i.size() < this.h.a()) {
                this.i.add(aVar);
            } else if (this.h.d()) {
                this.i.remove(0);
                this.i.add(aVar);
            } else {
                throw new MqttException(32203);
            }
        }
    }

    public void b(int i) {
        synchronized (this.j) {
            this.i.remove(i);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        while (a() > 0) {
            try {
                this.k.a(a(0));
                b(0);
            } catch (MqttException unused) {
                return;
            }
        }
    }

    public boolean b() {
        return this.h.c();
    }

    public com.abupdate.mqtt_libs.mqttv3.a a(int i) {
        com.abupdate.mqtt_libs.mqttv3.a aVar;
        synchronized (this.j) {
            aVar = (com.abupdate.mqtt_libs.mqttv3.a) this.i.get(i);
        }
        return aVar;
    }

    public int a() {
        int size;
        synchronized (this.j) {
            size = this.i.size();
        }
        return size;
    }

    public void a(k kVar) {
        this.k = kVar;
    }
}
