package com.abupdate.mqtt_libs.mqtt_service;

import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.IMqttToken;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.a.c.u;
import com.abupdate.mqtt_libs.mqttv3.m;
import com.realsil.sdk.dfu.DfuConstants;
/* loaded from: classes.dex */
public class g implements IMqttToken {

    /* renamed from: a  reason: collision with root package name */
    public IMqttActionListener f1938a;
    public volatile boolean b;
    public volatile MqttException c;
    public Object d;
    public MqttAndroidClient e;
    public Object f;
    public String[] g;
    public IMqttToken h;
    public MqttException i;

    public g(MqttAndroidClient mqttAndroidClient, Object obj, IMqttActionListener iMqttActionListener) {
        this(mqttAndroidClient, obj, iMqttActionListener, null);
    }

    public void a() {
        synchronized (this.d) {
            this.b = true;
            this.d.notifyAll();
            IMqttActionListener iMqttActionListener = this.f1938a;
            if (iMqttActionListener != null) {
                iMqttActionListener.onSuccess(this);
            }
        }
    }

    public void b(IMqttToken iMqttToken) {
        this.h = iMqttToken;
    }

    public void c(Throwable th) {
        synchronized (this.d) {
            this.b = true;
            if (th instanceof MqttException) {
                this.i = (MqttException) th;
            } else {
                this.i = new MqttException(th);
            }
            this.d.notifyAll();
            if (th instanceof MqttException) {
                this.c = (MqttException) th;
            }
            IMqttActionListener iMqttActionListener = this.f1938a;
            if (iMqttActionListener != null) {
                iMqttActionListener.onFailure(this, th);
            }
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public IMqttActionListener getActionCallback() {
        return this.f1938a;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public com.abupdate.mqtt_libs.mqttv3.c getClient() {
        return this.e;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public MqttException getException() {
        return this.c;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public int[] getGrantedQos() {
        return this.h.getGrantedQos();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public int getMessageId() {
        IMqttToken iMqttToken = this.h;
        if (iMqttToken != null) {
            return iMqttToken.getMessageId();
        }
        return 0;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public u getResponse() {
        return this.h.getResponse();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public boolean getSessionPresent() {
        return this.h.getSessionPresent();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public String[] getTopics() {
        return this.g;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public Object getUserContext() {
        return this.f;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public boolean isComplete() {
        return this.b;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public void setActionCallback(IMqttActionListener iMqttActionListener) {
        this.f1938a = iMqttActionListener;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public void setUserContext(Object obj) {
        this.f = obj;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public void waitForCompletion() throws MqttException, m {
        synchronized (this.d) {
            try {
                this.d.wait();
            } catch (InterruptedException unused) {
            }
        }
        MqttException mqttException = this.i;
        if (mqttException != null) {
            throw mqttException;
        }
    }

    public g(MqttAndroidClient mqttAndroidClient, Object obj, IMqttActionListener iMqttActionListener, String[] strArr) {
        this.d = new Object();
        this.e = mqttAndroidClient;
        this.f = obj;
        this.f1938a = iMqttActionListener;
        this.g = strArr;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.IMqttToken
    public void waitForCompletion(long j) throws MqttException, m {
        synchronized (this.d) {
            try {
                this.d.wait(j);
            } catch (InterruptedException unused) {
            }
            if (this.b) {
                MqttException mqttException = this.i;
                if (mqttException != null) {
                    throw mqttException;
                }
            } else {
                throw new MqttException((int) DfuConstants.MAX_CONNECTION_LOCK_TIMEOUT);
            }
        }
    }
}
