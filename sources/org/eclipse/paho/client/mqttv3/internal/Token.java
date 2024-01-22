package org.eclipse.paho.client.mqttv3.internal;

import com.realsil.sdk.dfu.DfuConstants;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSuback;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class Token {
    public static final String p = "org.eclipse.paho.client.mqttv3.internal.Token";
    public String j;

    /* renamed from: a  reason: collision with root package name */
    public Logger f15453a = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, p);
    public volatile boolean b = false;
    public boolean c = false;
    public boolean d = false;
    public final Object e = new Object();
    public final Object f = new Object();
    public MqttMessage message = null;
    public MqttWireMessage g = null;
    public MqttException h = null;
    public String[] i = null;
    public IMqttAsyncClient k = null;
    public IMqttActionListener l = null;
    public Object m = null;
    public int n = 0;
    public boolean o = false;

    public Token(String str) {
        this.f15453a.setResourceName(str);
    }

    public boolean checkResult() throws MqttException {
        if (getException() == null) {
            return true;
        }
        throw getException();
    }

    public IMqttActionListener getActionCallback() {
        return this.l;
    }

    public IMqttAsyncClient getClient() {
        return this.k;
    }

    public MqttException getException() {
        return this.h;
    }

    public int[] getGrantedQos() {
        int[] iArr = new int[0];
        MqttWireMessage mqttWireMessage = this.g;
        return mqttWireMessage instanceof MqttSuback ? ((MqttSuback) mqttWireMessage).getGrantedQos() : iArr;
    }

    public String getKey() {
        return this.j;
    }

    public MqttMessage getMessage() {
        return this.message;
    }

    public int getMessageID() {
        return this.n;
    }

    public MqttWireMessage getResponse() {
        return this.g;
    }

    public boolean getSessionPresent() {
        MqttWireMessage mqttWireMessage = this.g;
        if (mqttWireMessage instanceof MqttConnack) {
            return ((MqttConnack) mqttWireMessage).getSessionPresent();
        }
        return false;
    }

    public String[] getTopics() {
        return this.i;
    }

    public Object getUserContext() {
        return this.m;
    }

    public MqttWireMessage getWireMessage() {
        return this.g;
    }

    public boolean isComplete() {
        return this.b;
    }

    public boolean isCompletePending() {
        return this.c;
    }

    public boolean isInUse() {
        return (getClient() == null || isComplete()) ? false : true;
    }

    public boolean isNotified() {
        return this.o;
    }

    public void markComplete(MqttWireMessage mqttWireMessage, MqttException mqttException) {
        this.f15453a.fine(p, "markComplete", "404", new Object[]{getKey(), mqttWireMessage, mqttException});
        synchronized (this.e) {
            if (mqttWireMessage instanceof MqttAck) {
                this.message = null;
            }
            this.c = true;
            this.g = mqttWireMessage;
            this.h = mqttException;
        }
    }

    public void notifyComplete() {
        this.f15453a.fine(p, "notifyComplete", "404", new Object[]{getKey(), this.g, this.h});
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

    public void notifySent() {
        this.f15453a.fine(p, "notifySent", "403", new Object[]{getKey()});
        synchronized (this.e) {
            this.g = null;
            this.b = false;
        }
        synchronized (this.f) {
            this.d = true;
            this.f.notifyAll();
        }
    }

    public void reset() throws MqttException {
        if (!isInUse()) {
            this.f15453a.fine(p, "reset", "410", new Object[]{getKey()});
            this.k = null;
            this.b = false;
            this.g = null;
            this.d = false;
            this.h = null;
            this.m = null;
            return;
        }
        throw new MqttException(32201);
    }

    public void setActionCallback(IMqttActionListener iMqttActionListener) {
        this.l = iMqttActionListener;
    }

    public void setClient(IMqttAsyncClient iMqttAsyncClient) {
        this.k = iMqttAsyncClient;
    }

    public void setException(MqttException mqttException) {
        synchronized (this.e) {
            this.h = mqttException;
        }
    }

    public void setKey(String str) {
        this.j = str;
    }

    public void setMessage(MqttMessage mqttMessage) {
        this.message = mqttMessage;
    }

    public void setMessageID(int i) {
        this.n = i;
    }

    public void setNotified(boolean z) {
        this.o = z;
    }

    public void setTopics(String[] strArr) {
        this.i = (String[]) strArr.clone();
    }

    public void setUserContext(Object obj) {
        this.m = obj;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=");
        stringBuffer.append(getKey());
        stringBuffer.append(" ,topics=");
        if (getTopics() != null) {
            for (int i = 0; i < getTopics().length; i++) {
                stringBuffer.append(getTopics()[i]);
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(" ,usercontext=");
        stringBuffer.append(getUserContext());
        stringBuffer.append(" ,isComplete=");
        stringBuffer.append(isComplete());
        stringBuffer.append(" ,isNotified=");
        stringBuffer.append(isNotified());
        stringBuffer.append(" ,exception=");
        stringBuffer.append(getException());
        stringBuffer.append(" ,actioncallback=");
        stringBuffer.append(getActionCallback());
        return stringBuffer.toString();
    }

    public void waitForCompletion() throws MqttException {
        waitForCompletion(-1L);
    }

    public MqttWireMessage waitForResponse() throws MqttException {
        return waitForResponse(-1L);
    }

    public void waitUntilSent() throws MqttException {
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
                    this.f15453a.fine(p, "waitUntilSent", "409", new Object[]{getKey()});
                    this.f.wait();
                } catch (InterruptedException unused) {
                }
            }
            if (!z) {
                MqttException mqttException2 = this.h;
                if (mqttException2 == null) {
                    throw ExceptionHelper.createMqttException(6);
                }
                throw mqttException2;
            }
        }
    }

    public void waitForCompletion(long j) throws MqttException {
        Logger logger = this.f15453a;
        String str = p;
        logger.fine(str, "waitForCompletion", "407", new Object[]{getKey(), Long.valueOf(j), this});
        if (waitForResponse(j) == null && !this.b) {
            this.f15453a.fine(str, "waitForCompletion", "406", new Object[]{getKey(), this});
            MqttException mqttException = new MqttException((int) DfuConstants.MAX_CONNECTION_LOCK_TIMEOUT);
            this.h = mqttException;
            throw mqttException;
        }
        checkResult();
    }

    public MqttWireMessage waitForResponse(long j) throws MqttException {
        synchronized (this.e) {
            Logger logger = this.f15453a;
            String str = p;
            Object[] objArr = new Object[7];
            objArr[0] = getKey();
            objArr[1] = Long.valueOf(j);
            objArr[2] = Boolean.valueOf(this.d);
            objArr[3] = Boolean.valueOf(this.b);
            MqttException mqttException = this.h;
            objArr[4] = mqttException == null ? "false" : "true";
            objArr[5] = this.g;
            objArr[6] = this;
            logger.fine(str, "waitForResponse", "400", objArr, mqttException);
            while (!this.b) {
                if (this.h == null) {
                    try {
                        this.f15453a.fine(p, "waitForResponse", "408", new Object[]{getKey(), Long.valueOf(j)});
                        if (j <= 0) {
                            this.e.wait();
                        } else {
                            this.e.wait(j);
                        }
                    } catch (InterruptedException e) {
                        this.h = new MqttException(e);
                    }
                }
                if (!this.b) {
                    MqttException mqttException2 = this.h;
                    if (mqttException2 != null) {
                        this.f15453a.fine(p, "waitForResponse", "401", null, mqttException2);
                        throw this.h;
                    } else if (j > 0) {
                        break;
                    }
                }
            }
        }
        this.f15453a.fine(p, "waitForResponse", "402", new Object[]{getKey(), this.g});
        return this.g;
    }
}
