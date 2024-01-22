package org.eclipse.paho.client.mqttv3.internal;

import com.coveiot.android.leonardo.utils.MusicConstants;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class CommsCallback implements Runnable {
    public static final String y = CommsCallback.class.getName();
    public final Logger h;
    public MqttCallback i;
    public MqttCallbackExtended j;
    public Hashtable<String, IMqttMessageListener> k;
    public ClientComms l;
    public final Vector<MqttWireMessage> m;
    public final Vector<MqttToken> n;
    public a o;
    public a p;
    public final Object q;
    public Thread r;
    public String s;
    public Future<?> t;
    public final Object u;
    public final Object v;
    public ClientState w;
    public boolean x;

    /* loaded from: classes13.dex */
    public enum a {
        STOPPED,
        RUNNING,
        QUIESCING;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static a[] valuesCustom() {
            a[] valuesCustom = values();
            int length = valuesCustom.length;
            a[] aVarArr = new a[length];
            System.arraycopy(valuesCustom, 0, aVarArr, 0, length);
            return aVarArr;
        }
    }

    public CommsCallback(ClientComms clientComms) {
        Logger logger = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, y);
        this.h = logger;
        a aVar = a.STOPPED;
        this.o = aVar;
        this.p = aVar;
        this.q = new Object();
        this.u = new Object();
        this.v = new Object();
        this.x = false;
        this.l = clientComms;
        this.m = new Vector<>(10);
        this.n = new Vector<>(10);
        this.k = new Hashtable<>();
        logger.setResourceName(clientComms.getClient().getClientId());
    }

    public final void a(MqttToken mqttToken) throws MqttException {
        synchronized (mqttToken) {
            this.h.fine(y, "handleActionComplete", "705", new Object[]{mqttToken.internalTok.getKey()});
            if (mqttToken.isComplete()) {
                this.w.notifyComplete(mqttToken);
            }
            mqttToken.internalTok.notifyComplete();
            if (!mqttToken.internalTok.isNotified()) {
                if (this.i != null && (mqttToken instanceof MqttDeliveryToken) && mqttToken.isComplete()) {
                    this.i.deliveryComplete((MqttDeliveryToken) mqttToken);
                }
                fireActionEvent(mqttToken);
            }
            if (mqttToken.isComplete() && (mqttToken instanceof MqttDeliveryToken)) {
                mqttToken.internalTok.setNotified(true);
            }
        }
    }

    public void asyncOperationComplete(MqttToken mqttToken) {
        if (isRunning()) {
            this.n.addElement(mqttToken);
            synchronized (this.u) {
                this.h.fine(y, "asyncOperationComplete", "715", new Object[]{mqttToken.internalTok.getKey()});
                this.u.notifyAll();
            }
            return;
        }
        try {
            a(mqttToken);
        } catch (Throwable th) {
            this.h.fine(y, "asyncOperationComplete", "719", null, th);
            this.l.shutdownConnection(null, new MqttException(th));
        }
    }

    public final void b(MqttPublish mqttPublish) throws MqttException, Exception {
        String topicName = mqttPublish.getTopicName();
        this.h.fine(y, "handleMessage", "713", new Object[]{Integer.valueOf(mqttPublish.getMessageId()), topicName});
        deliverMessage(topicName, mqttPublish.getMessageId(), mqttPublish.getMessage());
        if (this.x) {
            return;
        }
        if (mqttPublish.getMessage().getQos() == 1) {
            this.l.p(new MqttPubAck(mqttPublish), new MqttToken(this.l.getClient().getClientId()));
        } else if (mqttPublish.getMessage().getQos() == 2) {
            this.l.deliveryComplete(mqttPublish);
            MqttPubComp mqttPubComp = new MqttPubComp(mqttPublish);
            ClientComms clientComms = this.l;
            clientComms.p(mqttPubComp, new MqttToken(clientComms.getClient().getClientId()));
        }
    }

    public void connectionLost(MqttException mqttException) {
        try {
            if (this.i != null && mqttException != null) {
                this.h.fine(y, "connectionLost", "708", new Object[]{mqttException});
                this.i.connectionLost(mqttException);
            }
            MqttCallbackExtended mqttCallbackExtended = this.j;
            if (mqttCallbackExtended == null || mqttException == null) {
                return;
            }
            mqttCallbackExtended.connectionLost(mqttException);
        } catch (Throwable th) {
            this.h.fine(y, "connectionLost", "720", new Object[]{th});
        }
    }

    public boolean deliverMessage(String str, int i, MqttMessage mqttMessage) throws Exception {
        Enumeration<String> keys = this.k.keys();
        boolean z = false;
        while (keys.hasMoreElements()) {
            String nextElement = keys.nextElement();
            IMqttMessageListener iMqttMessageListener = this.k.get(nextElement);
            if (iMqttMessageListener != null && MqttTopic.isMatched(nextElement, str)) {
                mqttMessage.setId(i);
                iMqttMessageListener.messageArrived(str, mqttMessage);
                z = true;
            }
        }
        if (this.i == null || z) {
            return z;
        }
        mqttMessage.setId(i);
        this.i.messageArrived(str, mqttMessage);
        return true;
    }

    public void fireActionEvent(MqttToken mqttToken) {
        IMqttActionListener actionCallback;
        if (mqttToken == null || (actionCallback = mqttToken.getActionCallback()) == null) {
            return;
        }
        if (mqttToken.getException() == null) {
            this.h.fine(y, "fireActionEvent", "716", new Object[]{mqttToken.internalTok.getKey()});
            actionCallback.onSuccess(mqttToken);
            return;
        }
        this.h.fine(y, "fireActionEvent", "716", new Object[]{mqttToken.internalTok.getKey()});
        actionCallback.onFailure(mqttToken, mqttToken.getException());
    }

    public Thread getThread() {
        return this.r;
    }

    public boolean isQuiesced() {
        return isQuiescing() && this.n.size() == 0 && this.m.size() == 0;
    }

    public boolean isQuiescing() {
        boolean z;
        synchronized (this.q) {
            z = this.o == a.QUIESCING;
        }
        return z;
    }

    public boolean isRunning() {
        boolean z;
        synchronized (this.q) {
            a aVar = this.o;
            a aVar2 = a.RUNNING;
            z = (aVar == aVar2 || aVar == a.QUIESCING) && this.p == aVar2;
        }
        return z;
    }

    public void messageArrived(MqttPublish mqttPublish) {
        if (this.i != null || this.k.size() > 0) {
            synchronized (this.v) {
                while (isRunning() && !isQuiescing() && this.m.size() >= 10) {
                    try {
                        this.h.fine(y, MqttServiceConstants.MESSAGE_ARRIVED_ACTION, "709");
                        this.v.wait(200L);
                    } catch (InterruptedException unused) {
                    }
                }
            }
            if (isQuiescing()) {
                return;
            }
            this.m.addElement(mqttPublish);
            synchronized (this.u) {
                this.h.fine(y, MqttServiceConstants.MESSAGE_ARRIVED_ACTION, "710");
                this.u.notifyAll();
            }
        }
    }

    public void messageArrivedComplete(int i, int i2) throws MqttException {
        if (i2 == 1) {
            this.l.p(new MqttPubAck(i), new MqttToken(this.l.getClient().getClientId()));
        } else if (i2 == 2) {
            this.l.deliveryComplete(i);
            MqttPubComp mqttPubComp = new MqttPubComp(i);
            ClientComms clientComms = this.l;
            clientComms.p(mqttPubComp, new MqttToken(clientComms.getClient().getClientId()));
        }
    }

    public void quiesce() {
        synchronized (this.q) {
            if (this.o == a.RUNNING) {
                this.o = a.QUIESCING;
            }
        }
        synchronized (this.v) {
            this.h.fine(y, "quiesce", "711");
            this.v.notifyAll();
        }
    }

    public void removeMessageListener(String str) {
        this.k.remove(str);
    }

    public void removeMessageListeners() {
        this.k.clear();
    }

    @Override // java.lang.Runnable
    public void run() {
        MqttToken mqttToken;
        MqttPublish mqttPublish;
        Thread currentThread = Thread.currentThread();
        this.r = currentThread;
        currentThread.setName(this.s);
        synchronized (this.q) {
            this.o = a.RUNNING;
        }
        while (isRunning()) {
            try {
                try {
                    synchronized (this.u) {
                        if (isRunning() && this.m.isEmpty() && this.n.isEmpty()) {
                            this.h.fine(y, "run", "704");
                            this.u.wait();
                        }
                    }
                } catch (Throwable th) {
                    try {
                        Logger logger = this.h;
                        String str = y;
                        logger.fine(str, "run", "714", null, th);
                        this.l.shutdownConnection(null, new MqttException(th));
                        synchronized (this.v) {
                            this.h.fine(str, "run", "706");
                            this.v.notifyAll();
                        }
                    } catch (Throwable th2) {
                        synchronized (this.v) {
                            this.h.fine(y, "run", "706");
                            this.v.notifyAll();
                            throw th2;
                        }
                    }
                }
            } catch (InterruptedException unused) {
            }
            if (isRunning()) {
                synchronized (this.n) {
                    if (this.n.isEmpty()) {
                        mqttToken = null;
                    } else {
                        mqttToken = this.n.elementAt(0);
                        this.n.removeElementAt(0);
                    }
                }
                if (mqttToken != null) {
                    a(mqttToken);
                }
                synchronized (this.m) {
                    if (this.m.isEmpty()) {
                        mqttPublish = null;
                    } else {
                        mqttPublish = (MqttPublish) this.m.elementAt(0);
                        this.m.removeElementAt(0);
                    }
                }
                if (mqttPublish != null) {
                    b(mqttPublish);
                }
            }
            if (isQuiescing()) {
                this.w.checkQuiesceLock();
            }
            synchronized (this.v) {
                this.h.fine(y, "run", "706");
                this.v.notifyAll();
            }
        }
        synchronized (this.q) {
            this.o = a.STOPPED;
        }
        this.r = null;
    }

    public void setCallback(MqttCallback mqttCallback) {
        this.i = mqttCallback;
    }

    public void setClientState(ClientState clientState) {
        this.w = clientState;
    }

    public void setManualAcks(boolean z) {
        this.x = z;
    }

    public void setMessageListener(String str, IMqttMessageListener iMqttMessageListener) {
        this.k.put(str, iMqttMessageListener);
    }

    public void setReconnectCallback(MqttCallbackExtended mqttCallbackExtended) {
        this.j = mqttCallbackExtended;
    }

    public void start(String str, ExecutorService executorService) {
        this.s = str;
        synchronized (this.q) {
            if (this.o == a.STOPPED) {
                this.m.clear();
                this.n.clear();
                this.p = a.RUNNING;
                if (executorService == null) {
                    new Thread(this).start();
                } else {
                    this.t = executorService.submit(this);
                }
            }
        }
        while (!isRunning()) {
            try {
                Thread.sleep(100L);
            } catch (Exception unused) {
            }
        }
    }

    public void stop() {
        synchronized (this.q) {
            Future<?> future = this.t;
            if (future != null) {
                future.cancel(true);
            }
        }
        if (isRunning()) {
            Logger logger = this.h;
            String str = y;
            logger.fine(str, MusicConstants.CMDSTOP, "700");
            synchronized (this.q) {
                this.p = a.STOPPED;
            }
            if (!Thread.currentThread().equals(this.r)) {
                synchronized (this.u) {
                    this.h.fine(str, MusicConstants.CMDSTOP, "701");
                    this.u.notifyAll();
                }
                while (isRunning()) {
                    try {
                        Thread.sleep(100L);
                    } catch (Exception unused) {
                    }
                    this.w.notifyQueueLock();
                }
            }
            this.h.fine(y, MusicConstants.CMDSTOP, "703");
        }
    }
}
