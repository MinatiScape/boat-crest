package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttToken;
/* loaded from: classes13.dex */
public class ConnectActionListener implements IMqttActionListener {

    /* renamed from: a  reason: collision with root package name */
    public MqttClientPersistence f15447a;
    public MqttAsyncClient b;
    public ClientComms c;
    public MqttConnectOptions d;
    public MqttToken e;
    public Object f;
    public IMqttActionListener g;
    public int h;
    public MqttCallbackExtended i;
    public boolean j;

    public ConnectActionListener(MqttAsyncClient mqttAsyncClient, MqttClientPersistence mqttClientPersistence, ClientComms clientComms, MqttConnectOptions mqttConnectOptions, MqttToken mqttToken, Object obj, IMqttActionListener iMqttActionListener, boolean z) {
        this.f15447a = mqttClientPersistence;
        this.b = mqttAsyncClient;
        this.c = clientComms;
        this.d = mqttConnectOptions;
        this.e = mqttToken;
        this.f = obj;
        this.g = iMqttActionListener;
        this.h = mqttConnectOptions.getMqttVersion();
        this.j = z;
    }

    public void connect() throws MqttPersistenceException {
        MqttToken mqttToken = new MqttToken(this.b.getClientId());
        mqttToken.setActionCallback(this);
        mqttToken.setUserContext(this);
        this.f15447a.open(this.b.getClientId(), this.b.getServerURI());
        if (this.d.isCleanSession()) {
            this.f15447a.clear();
        }
        if (this.d.getMqttVersion() == 0) {
            this.d.setMqttVersion(4);
        }
        try {
            this.c.connect(this.d, mqttToken);
        } catch (MqttException e) {
            onFailure(mqttToken, e);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
    public void onFailure(IMqttToken iMqttToken, Throwable th) {
        MqttException mqttException;
        int length = this.c.getNetworkModules().length;
        int networkModuleIndex = this.c.getNetworkModuleIndex() + 1;
        if (networkModuleIndex >= length && (this.h != 0 || this.d.getMqttVersion() != 4)) {
            if (this.h == 0) {
                this.d.setMqttVersion(0);
            }
            if (th instanceof MqttException) {
                mqttException = (MqttException) th;
            } else {
                mqttException = new MqttException(th);
            }
            this.e.internalTok.markComplete(null, mqttException);
            this.e.internalTok.notifyComplete();
            this.e.internalTok.setClient(this.b);
            if (this.g != null) {
                this.e.setUserContext(this.f);
                this.g.onFailure(this.e, th);
                return;
            }
            return;
        }
        if (this.h == 0) {
            if (this.d.getMqttVersion() == 4) {
                this.d.setMqttVersion(3);
            } else {
                this.d.setMqttVersion(4);
                this.c.setNetworkModuleIndex(networkModuleIndex);
            }
        } else {
            this.c.setNetworkModuleIndex(networkModuleIndex);
        }
        try {
            connect();
        } catch (MqttPersistenceException e) {
            onFailure(iMqttToken, e);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
    public void onSuccess(IMqttToken iMqttToken) {
        if (this.h == 0) {
            this.d.setMqttVersion(0);
        }
        this.e.internalTok.markComplete(iMqttToken.getResponse(), null);
        this.e.internalTok.notifyComplete();
        this.e.internalTok.setClient(this.b);
        this.c.notifyConnect();
        if (this.g != null) {
            this.e.setUserContext(this.f);
            this.g.onSuccess(this.e);
        }
        if (this.i != null) {
            this.i.connectComplete(this.j, this.c.getNetworkModules()[this.c.getNetworkModuleIndex()].getServerURI());
        }
    }

    public void setMqttCallbackExtended(MqttCallbackExtended mqttCallbackExtended) {
        this.i = mqttCallbackExtended;
    }
}
