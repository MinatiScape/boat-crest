package org.eclipse.paho.client.mqttv3.persist;

import java.util.Enumeration;
import java.util.Hashtable;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
/* loaded from: classes13.dex */
public class MemoryPersistence implements MqttClientPersistence {
    public Hashtable<String, MqttPersistable> h;

    public final void a() throws MqttPersistenceException {
        if (this.h == null) {
            throw new MqttPersistenceException();
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void clear() throws MqttPersistenceException {
        a();
        this.h.clear();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence, java.lang.AutoCloseable
    public void close() throws MqttPersistenceException {
        Hashtable<String, MqttPersistable> hashtable = this.h;
        if (hashtable != null) {
            hashtable.clear();
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public boolean containsKey(String str) throws MqttPersistenceException {
        a();
        return this.h.containsKey(str);
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public MqttPersistable get(String str) throws MqttPersistenceException {
        a();
        return this.h.get(str);
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public Enumeration<String> keys() throws MqttPersistenceException {
        a();
        return this.h.keys();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void open(String str, String str2) throws MqttPersistenceException {
        this.h = new Hashtable<>();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void put(String str, MqttPersistable mqttPersistable) throws MqttPersistenceException {
        a();
        this.h.put(str, mqttPersistable);
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void remove(String str) throws MqttPersistenceException {
        a();
        this.h.remove(str);
    }
}
