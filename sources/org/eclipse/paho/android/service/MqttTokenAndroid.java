package org.eclipse.paho.android.service;

import com.realsil.sdk.dfu.DfuConstants;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
/* loaded from: classes13.dex */
public class MqttTokenAndroid implements IMqttToken {

    /* renamed from: a  reason: collision with root package name */
    public IMqttActionListener f15433a;
    public volatile boolean b;
    public volatile MqttException c;
    public Object d;
    public MqttAndroidClient e;
    public Object f;
    public String[] g;
    public IMqttToken h;
    public MqttException i;

    public MqttTokenAndroid(MqttAndroidClient mqttAndroidClient, Object obj, IMqttActionListener iMqttActionListener) {
        this(mqttAndroidClient, obj, iMqttActionListener, null);
    }

    public MqttTokenAndroid(MqttAndroidClient mqttAndroidClient, Object obj, IMqttActionListener iMqttActionListener, String[] strArr) {
        this.d = new Object();
        this.e = mqttAndroidClient;
        this.f = obj;
        this.f15433a = iMqttActionListener;
        this.g = strArr;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public IMqttActionListener getActionCallback() {
        return this.f15433a;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public IMqttAsyncClient getClient() {
        return this.e;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public MqttException getException() {
        return this.c;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public int[] getGrantedQos() {
        return this.h.getGrantedQos();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public int getMessageId() {
        IMqttToken iMqttToken = this.h;
        if (iMqttToken != null) {
            return iMqttToken.getMessageId();
        }
        return 0;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public MqttWireMessage getResponse() {
        return this.h.getResponse();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public boolean getSessionPresent() {
        return this.h.getSessionPresent();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public String[] getTopics() {
        return this.g;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public Object getUserContext() {
        return this.f;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public boolean isComplete() {
        return this.b;
    }

    public void notifyComplete() {
        synchronized (this.d) {
            this.b = true;
            this.d.notifyAll();
            IMqttActionListener iMqttActionListener = this.f15433a;
            if (iMqttActionListener != null) {
                iMqttActionListener.onSuccess(this);
            }
        }
    }

    public void notifyFailure(Throwable th) {
        synchronized (this.d) {
            this.b = true;
            this.i = th instanceof MqttException ? (MqttException) th : new MqttException(th);
            this.d.notifyAll();
            if (th instanceof MqttException) {
                this.c = (MqttException) th;
            }
            IMqttActionListener iMqttActionListener = this.f15433a;
            if (iMqttActionListener != null) {
                iMqttActionListener.onFailure(this, th);
            }
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public void setActionCallback(IMqttActionListener iMqttActionListener) {
        this.f15433a = iMqttActionListener;
    }

    public void setComplete(boolean z) {
        this.b = z;
    }

    public void setDelegate(IMqttToken iMqttToken) {
        this.h = iMqttToken;
    }

    public void setException(MqttException mqttException) {
        this.c = mqttException;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public void setUserContext(Object obj) {
        this.f = obj;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public void waitForCompletion() {
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

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public void waitForCompletion(long j) {
        synchronized (this.d) {
            try {
                this.d.wait(j);
            } catch (InterruptedException unused) {
            }
            if (!this.b) {
                throw new MqttException((int) DfuConstants.MAX_CONNECTION_LOCK_TIMEOUT);
            }
            MqttException mqttException = this.i;
            if (mqttException != null) {
                throw mqttException;
            }
        }
    }
}
