package org.eclipse.paho.client.mqttv3.internal;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class CommsTokenStore {
    public static final String e = "org.eclipse.paho.client.mqttv3.internal.CommsTokenStore";

    /* renamed from: a  reason: collision with root package name */
    public Logger f15446a;
    public final Hashtable b;
    public String c;
    public MqttException d;

    public CommsTokenStore(String str) {
        String str2 = e;
        Logger logger = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, str2);
        this.f15446a = logger;
        this.d = null;
        logger.setResourceName(str);
        this.b = new Hashtable();
        this.c = str;
        this.f15446a.fine(str2, "<Init>", "308");
    }

    public void clear() {
        this.f15446a.fine(e, "clear", "305", new Object[]{Integer.valueOf(this.b.size())});
        synchronized (this.b) {
            this.b.clear();
        }
    }

    public int count() {
        int size;
        synchronized (this.b) {
            size = this.b.size();
        }
        return size;
    }

    public MqttDeliveryToken[] getOutstandingDelTokens() {
        MqttDeliveryToken[] mqttDeliveryTokenArr;
        synchronized (this.b) {
            this.f15446a.fine(e, "getOutstandingDelTokens", "311");
            Vector vector = new Vector();
            Enumeration elements = this.b.elements();
            while (elements.hasMoreElements()) {
                MqttToken mqttToken = (MqttToken) elements.nextElement();
                if (mqttToken != null && (mqttToken instanceof MqttDeliveryToken) && !mqttToken.internalTok.isNotified()) {
                    vector.addElement(mqttToken);
                }
            }
            mqttDeliveryTokenArr = (MqttDeliveryToken[]) vector.toArray(new MqttDeliveryToken[vector.size()]);
        }
        return mqttDeliveryTokenArr;
    }

    public Vector getOutstandingTokens() {
        Vector vector;
        synchronized (this.b) {
            this.f15446a.fine(e, "getOutstandingTokens", "312");
            vector = new Vector();
            Enumeration elements = this.b.elements();
            while (elements.hasMoreElements()) {
                MqttToken mqttToken = (MqttToken) elements.nextElement();
                if (mqttToken != null) {
                    vector.addElement(mqttToken);
                }
            }
        }
        return vector;
    }

    public MqttToken getToken(MqttWireMessage mqttWireMessage) {
        return (MqttToken) this.b.get(mqttWireMessage.getKey());
    }

    public void open() {
        synchronized (this.b) {
            this.f15446a.fine(e, "open", "310");
            this.d = null;
        }
    }

    public void quiesce(MqttException mqttException) {
        synchronized (this.b) {
            this.f15446a.fine(e, "quiesce", "309", new Object[]{mqttException});
            this.d = mqttException;
        }
    }

    public MqttToken removeToken(MqttWireMessage mqttWireMessage) {
        if (mqttWireMessage != null) {
            return removeToken(mqttWireMessage.getKey());
        }
        return null;
    }

    public MqttDeliveryToken restoreToken(MqttPublish mqttPublish) {
        MqttDeliveryToken mqttDeliveryToken;
        synchronized (this.b) {
            String num = Integer.toString(mqttPublish.getMessageId());
            if (this.b.containsKey(num)) {
                mqttDeliveryToken = (MqttDeliveryToken) this.b.get(num);
                this.f15446a.fine(e, "restoreToken", "302", new Object[]{num, mqttPublish, mqttDeliveryToken});
            } else {
                mqttDeliveryToken = new MqttDeliveryToken(this.c);
                mqttDeliveryToken.internalTok.setKey(num);
                this.b.put(num, mqttDeliveryToken);
                this.f15446a.fine(e, "restoreToken", "303", new Object[]{num, mqttPublish, mqttDeliveryToken});
            }
        }
        return mqttDeliveryToken;
    }

    public void saveToken(MqttToken mqttToken, MqttWireMessage mqttWireMessage) throws MqttException {
        synchronized (this.b) {
            MqttException mqttException = this.d;
            if (mqttException == null) {
                String key = mqttWireMessage.getKey();
                this.f15446a.fine(e, "saveToken", "300", new Object[]{key, mqttWireMessage});
                saveToken(mqttToken, key);
            } else {
                throw mqttException;
            }
        }
    }

    public String toString() {
        String stringBuffer;
        String property = System.getProperty("line.separator", "\n");
        StringBuffer stringBuffer2 = new StringBuffer();
        synchronized (this.b) {
            Enumeration elements = this.b.elements();
            while (elements.hasMoreElements()) {
                stringBuffer2.append("{" + ((MqttToken) elements.nextElement()).internalTok + "}" + property);
            }
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer;
    }

    public MqttToken removeToken(String str) {
        this.f15446a.fine(e, "removeToken", "306", new Object[]{str});
        if (str != null) {
            return (MqttToken) this.b.remove(str);
        }
        return null;
    }

    public MqttToken getToken(String str) {
        return (MqttToken) this.b.get(str);
    }

    public void saveToken(MqttToken mqttToken, String str) {
        synchronized (this.b) {
            this.f15446a.fine(e, "saveToken", "307", new Object[]{str, mqttToken.toString()});
            mqttToken.internalTok.setKey(str);
            this.b.put(str, mqttToken);
        }
    }
}
