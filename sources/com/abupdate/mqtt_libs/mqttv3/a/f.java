package com.abupdate.mqtt_libs.mqttv3.a;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.a.c.u;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
/* loaded from: classes.dex */
public class f {
    public String b;
    public MqttException c = null;

    /* renamed from: a  reason: collision with root package name */
    public Hashtable f1953a = new Hashtable();

    public f(String str) {
        this.b = str;
    }

    public com.abupdate.mqtt_libs.mqttv3.n a(u uVar) {
        return (com.abupdate.mqtt_libs.mqttv3.n) this.f1953a.get(uVar.e());
    }

    public com.abupdate.mqtt_libs.mqttv3.n b(u uVar) {
        if (uVar != null) {
            return b(uVar.e());
        }
        return null;
    }

    public Vector c() {
        Vector vector;
        synchronized (this.f1953a) {
            vector = new Vector();
            Enumeration elements = this.f1953a.elements();
            while (elements.hasMoreElements()) {
                com.abupdate.mqtt_libs.mqttv3.n nVar = (com.abupdate.mqtt_libs.mqttv3.n) elements.nextElement();
                if (nVar != null) {
                    vector.addElement(nVar);
                }
            }
        }
        return vector;
    }

    public void d() {
        synchronized (this.f1953a) {
            this.f1953a.clear();
        }
    }

    public int e() {
        int size;
        synchronized (this.f1953a) {
            size = this.f1953a.size();
        }
        return size;
    }

    public String toString() {
        String stringBuffer;
        String property = System.getProperty("line.separator", "\n");
        StringBuffer stringBuffer2 = new StringBuffer();
        synchronized (this.f1953a) {
            Enumeration elements = this.f1953a.elements();
            while (elements.hasMoreElements()) {
                stringBuffer2.append("{" + ((com.abupdate.mqtt_libs.mqttv3.n) elements.nextElement()).f1970a + "}" + property);
            }
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer;
    }

    public com.abupdate.mqtt_libs.mqttv3.n b(String str) {
        if (str != null) {
            return (com.abupdate.mqtt_libs.mqttv3.n) this.f1953a.remove(str);
        }
        return null;
    }

    public com.abupdate.mqtt_libs.mqttv3.n a(String str) {
        return (com.abupdate.mqtt_libs.mqttv3.n) this.f1953a.get(str);
    }

    public com.abupdate.mqtt_libs.mqttv3.i[] b() {
        com.abupdate.mqtt_libs.mqttv3.i[] iVarArr;
        synchronized (this.f1953a) {
            Vector vector = new Vector();
            Enumeration elements = this.f1953a.elements();
            while (elements.hasMoreElements()) {
                com.abupdate.mqtt_libs.mqttv3.n nVar = (com.abupdate.mqtt_libs.mqttv3.n) elements.nextElement();
                if (nVar != null && (nVar instanceof com.abupdate.mqtt_libs.mqttv3.i) && !nVar.f1970a.p()) {
                    vector.addElement(nVar);
                }
            }
            iVarArr = (com.abupdate.mqtt_libs.mqttv3.i[]) vector.toArray(new com.abupdate.mqtt_libs.mqttv3.i[vector.size()]);
        }
        return iVarArr;
    }

    public com.abupdate.mqtt_libs.mqttv3.i a(com.abupdate.mqtt_libs.mqttv3.a.c.o oVar) {
        com.abupdate.mqtt_libs.mqttv3.i iVar;
        synchronized (this.f1953a) {
            String num = new Integer(oVar.j()).toString();
            if (this.f1953a.containsKey(num)) {
                iVar = (com.abupdate.mqtt_libs.mqttv3.i) this.f1953a.get(num);
            } else {
                com.abupdate.mqtt_libs.mqttv3.i iVar2 = new com.abupdate.mqtt_libs.mqttv3.i(this.b);
                iVar2.f1970a.a(num);
                this.f1953a.put(num, iVar2);
                iVar = iVar2;
            }
        }
        return iVar;
    }

    public void a(com.abupdate.mqtt_libs.mqttv3.n nVar, u uVar) throws MqttException {
        synchronized (this.f1953a) {
            MqttException mqttException = this.c;
            if (mqttException == null) {
                a(nVar, uVar.e());
            } else {
                throw mqttException;
            }
        }
    }

    public void a(com.abupdate.mqtt_libs.mqttv3.n nVar, String str) {
        synchronized (this.f1953a) {
            nVar.f1970a.a(str);
            this.f1953a.put(str, nVar);
        }
    }

    public void a(MqttException mqttException) {
        synchronized (this.f1953a) {
            this.c = mqttException;
        }
    }

    public void a() {
        synchronized (this.f1953a) {
            this.c = null;
        }
    }
}
