package com.abupdate.mqtt_libs.mqttv3.b;

import com.abupdate.mqtt_libs.mqttv3.g;
import com.abupdate.mqtt_libs.mqttv3.j;
import com.abupdate.mqtt_libs.mqttv3.k;
import java.util.Enumeration;
import java.util.Hashtable;
/* loaded from: classes.dex */
public class a implements g {

    /* renamed from: a  reason: collision with root package name */
    public Hashtable f1961a;

    @Override // com.abupdate.mqtt_libs.mqttv3.g
    public void a() throws k {
        this.f1961a.clear();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.g
    public Enumeration b() throws k {
        return this.f1961a.keys();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.g
    public void c() throws k {
        this.f1961a.clear();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.g
    public j a(String str) throws k {
        return (j) this.f1961a.get(str);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.g
    public void b(String str) throws k {
        this.f1961a.remove(str);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.g
    public boolean c(String str) throws k {
        return this.f1961a.containsKey(str);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.g
    public void a(String str, String str2) throws k {
        this.f1961a = new Hashtable();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.g
    public void a(String str, j jVar) throws k {
        this.f1961a.put(str, jVar);
    }
}
