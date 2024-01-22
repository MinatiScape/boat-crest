package org.eclipse.paho.client.mqttv3.internal;

import com.realsil.sdk.dfu.DfuConstants;
import java.io.EOFException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPingReq;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPingResp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRec;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRel;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSuback;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubscribe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class ClientState {
    public static final String E = "org.eclipse.paho.client.mqttv3.internal.ClientState";
    public Hashtable A;
    public Hashtable B;
    public Hashtable C;
    public MqttPingSender D;

    /* renamed from: a  reason: collision with root package name */
    public Logger f15445a;
    public int b;
    public Hashtable c;
    public volatile Vector d;
    public volatile Vector e;
    public CommsTokenStore f;
    public ClientComms g;
    public CommsCallback h;
    public long i;
    public boolean j;
    public MqttClientPersistence k;
    public HighResolutionTimer l;
    public int m;
    public int n;
    public int o;
    public final Object p;
    public final Object q;
    public boolean r;
    public long s;
    public long t;
    public long u;
    public MqttWireMessage v;
    public final Object w;
    public int x;
    public boolean y;
    public Hashtable z;

    public ClientState(MqttClientPersistence mqttClientPersistence, CommsTokenStore commsTokenStore, CommsCallback commsCallback, ClientComms clientComms, MqttPingSender mqttPingSender, HighResolutionTimer highResolutionTimer) throws MqttException {
        String str = E;
        Logger logger = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, str);
        this.f15445a = logger;
        this.b = 0;
        this.g = null;
        this.h = null;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = new Object();
        this.q = new Object();
        this.r = false;
        this.s = 0L;
        this.t = 0L;
        this.u = 0L;
        this.w = new Object();
        this.x = 0;
        this.y = false;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        logger.setResourceName(clientComms.getClient().getClientId());
        this.f15445a.finer(str, "<Init>", "");
        this.c = new Hashtable();
        this.e = new Vector();
        this.z = new Hashtable();
        this.A = new Hashtable();
        this.B = new Hashtable();
        this.C = new Hashtable();
        this.v = new MqttPingReq();
        this.o = 0;
        this.n = 0;
        this.k = mqttClientPersistence;
        this.h = commsCallback;
        this.f = commsTokenStore;
        this.g = clientComms;
        this.D = mqttPingSender;
        this.l = highResolutionTimer;
        restoreState();
    }

    public final void a() {
        synchronized (this.p) {
            int i = this.n - 1;
            this.n = i;
            this.f15445a.fine(E, "decrementInFlight", "646", new Object[]{Integer.valueOf(i)});
            if (!checkQuiesceLock()) {
                this.p.notifyAll();
            }
        }
    }

    public final synchronized int b() throws MqttException {
        int i;
        int i2 = this.b;
        int i3 = 0;
        do {
            int i4 = this.b + 1;
            this.b = i4;
            if (i4 > 65535) {
                this.b = 1;
            }
            i = this.b;
            if (i == i2 && (i3 = i3 + 1) == 2) {
                throw ExceptionHelper.createMqttException(32001);
            }
        } while (this.c.containsKey(Integer.valueOf(i)));
        Integer valueOf = Integer.valueOf(this.b);
        this.c.put(valueOf, valueOf);
        return this.b;
    }

    public final String c(int i) {
        return "r-" + i;
    }

    public MqttToken checkForActivity(IMqttActionListener iMqttActionListener) throws MqttException {
        long max;
        MqttToken mqttToken;
        Logger logger = this.f15445a;
        String str = E;
        logger.fine(str, "checkForActivity", "616", new Object[0]);
        synchronized (this.q) {
            if (this.r) {
                return null;
            }
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            timeUnit.toMillis(this.i);
            if (!this.y || this.i <= 0) {
                return null;
            }
            long nanoTime = this.l.nanoTime();
            synchronized (this.w) {
                int i = this.x;
                if (i > 0) {
                    long j = this.i;
                    if (nanoTime - this.t >= 100000 + j) {
                        this.f15445a.severe(str, "checkForActivity", "619", new Object[]{Long.valueOf(j), Long.valueOf(this.s), Long.valueOf(this.t), Long.valueOf(nanoTime), Long.valueOf(this.u)});
                        throw ExceptionHelper.createMqttException((int) DfuConstants.MAX_CONNECTION_LOCK_TIMEOUT);
                    }
                }
                if (i == 0) {
                    long j2 = this.i;
                    if (nanoTime - this.s >= 2 * j2) {
                        this.f15445a.severe(str, "checkForActivity", "642", new Object[]{Long.valueOf(j2), Long.valueOf(this.s), Long.valueOf(this.t), Long.valueOf(nanoTime), Long.valueOf(this.u)});
                        throw ExceptionHelper.createMqttException(32002);
                    }
                }
                if ((i == 0 && nanoTime - this.t >= this.i - 100000) || nanoTime - this.s >= this.i - 100000) {
                    this.f15445a.fine(str, "checkForActivity", "620", new Object[]{Long.valueOf(this.i), Long.valueOf(this.s), Long.valueOf(this.t)});
                    mqttToken = new MqttToken(this.g.getClient().getClientId());
                    if (iMqttActionListener != null) {
                        mqttToken.setActionCallback(iMqttActionListener);
                    }
                    this.f.saveToken(mqttToken, this.v);
                    this.e.insertElementAt(this.v, 0);
                    max = getKeepAlive();
                    notifyQueueLock();
                } else {
                    this.f15445a.fine(str, "checkForActivity", "634", null);
                    max = Math.max(1L, getKeepAlive() - timeUnit.toMillis(nanoTime - this.s));
                    mqttToken = null;
                }
            }
            this.f15445a.fine(str, "checkForActivity", "624", new Object[]{Long.valueOf(max)});
            this.D.schedule(max);
            return mqttToken;
        }
    }

    public boolean checkQuiesceLock() {
        int count = this.f.count();
        if (this.r && count == 0 && this.e.size() == 0 && this.h.isQuiesced()) {
            this.f15445a.fine(E, "checkQuiesceLock", "626", new Object[]{Boolean.valueOf(this.r), Integer.valueOf(this.n), Integer.valueOf(this.e.size()), Integer.valueOf(this.o), Boolean.valueOf(this.h.isQuiesced()), Integer.valueOf(count)});
            synchronized (this.q) {
                this.q.notifyAll();
            }
            return true;
        }
        return false;
    }

    public void clearState() throws MqttException {
        this.f15445a.fine(E, "clearState", ">");
        this.k.clear();
        this.c.clear();
        this.d.clear();
        this.e.clear();
        this.z.clear();
        this.A.clear();
        this.B.clear();
        this.C.clear();
        this.f.clear();
    }

    public void close() {
        this.c.clear();
        if (this.d != null) {
            this.d.clear();
        }
        this.e.clear();
        this.z.clear();
        this.A.clear();
        this.B.clear();
        this.C.clear();
        this.f.clear();
        this.c = null;
        this.d = null;
        this.e = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.f = null;
        this.h = null;
        this.g = null;
        this.k = null;
        this.v = null;
        this.l = null;
    }

    public void connected() {
        this.f15445a.fine(E, "connected", "631");
        this.y = true;
        this.D.start();
    }

    public final String d(MqttWireMessage mqttWireMessage) {
        return "r-" + mqttWireMessage.getMessageId();
    }

    public void deliveryComplete(MqttPublish mqttPublish) throws MqttPersistenceException {
        this.f15445a.fine(E, "deliveryComplete", "641", new Object[]{Integer.valueOf(mqttPublish.getMessageId())});
        this.k.remove(d(mqttPublish));
        this.C.remove(Integer.valueOf(mqttPublish.getMessageId()));
    }

    public void disconnected(MqttException mqttException) {
        this.f15445a.fine(E, "disconnected", "633", new Object[]{mqttException});
        this.y = false;
        try {
            if (this.j) {
                clearState();
            }
            this.d.clear();
            this.e.clear();
            synchronized (this.w) {
                this.x = 0;
            }
        } catch (MqttException unused) {
        }
    }

    public final String e(MqttWireMessage mqttWireMessage) {
        return "sb-" + mqttWireMessage.getMessageId();
    }

    public final String f(MqttWireMessage mqttWireMessage) {
        return "sc-" + mqttWireMessage.getMessageId();
    }

    public final String g(int i) {
        return "s-" + i;
    }

    public MqttWireMessage get() throws MqttException {
        synchronized (this.p) {
            MqttWireMessage mqttWireMessage = null;
            while (mqttWireMessage == null) {
                if ((this.d.isEmpty() && this.e.isEmpty()) || (this.e.isEmpty() && this.n >= this.m)) {
                    try {
                        Logger logger = this.f15445a;
                        String str = E;
                        logger.fine(str, "get", "644");
                        this.p.wait();
                        this.f15445a.fine(str, "get", "647");
                    } catch (InterruptedException unused) {
                    }
                }
                if (this.e != null && (this.y || (!this.e.isEmpty() && (((MqttWireMessage) this.e.elementAt(0)) instanceof MqttConnect)))) {
                    if (!this.e.isEmpty()) {
                        mqttWireMessage = (MqttWireMessage) this.e.remove(0);
                        if (mqttWireMessage instanceof MqttPubRel) {
                            int i = this.o + 1;
                            this.o = i;
                            this.f15445a.fine(E, "get", "617", new Object[]{Integer.valueOf(i)});
                        }
                        checkQuiesceLock();
                    } else if (!this.d.isEmpty()) {
                        if (this.n < this.m) {
                            mqttWireMessage = (MqttWireMessage) this.d.elementAt(0);
                            this.d.removeElementAt(0);
                            int i2 = this.n + 1;
                            this.n = i2;
                            this.f15445a.fine(E, "get", "623", new Object[]{Integer.valueOf(i2)});
                        } else {
                            this.f15445a.fine(E, "get", "622");
                        }
                    }
                }
                this.f15445a.fine(E, "get", "621");
                return null;
            }
            return mqttWireMessage;
        }
    }

    public int getActualInFlight() {
        return this.n;
    }

    public boolean getCleanSession() {
        return this.j;
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("In use msgids", this.c);
        properties.put("pendingMessages", this.d);
        properties.put("pendingFlows", this.e);
        properties.put("maxInflight", Integer.valueOf(this.m));
        properties.put("nextMsgID", Integer.valueOf(this.b));
        properties.put("actualInFlight", Integer.valueOf(this.n));
        properties.put("inFlightPubRels", Integer.valueOf(this.o));
        properties.put("quiescing", Boolean.valueOf(this.r));
        properties.put("pingoutstanding", Integer.valueOf(this.x));
        properties.put("lastOutboundActivity", Long.valueOf(this.s));
        properties.put("lastInboundActivity", Long.valueOf(this.t));
        properties.put("outboundQoS2", this.z);
        properties.put("outboundQoS1", this.A);
        properties.put("outboundQoS0", this.B);
        properties.put("inboundQoS2", this.C);
        properties.put("tokens", this.f);
        return properties;
    }

    public long getKeepAlive() {
        return TimeUnit.NANOSECONDS.toMillis(this.i);
    }

    public int getMaxInFlight() {
        return this.m;
    }

    public final String h(MqttWireMessage mqttWireMessage) {
        return "s-" + mqttWireMessage.getMessageId();
    }

    public final void i(Vector vector, MqttWireMessage mqttWireMessage) {
        int messageId = mqttWireMessage.getMessageId();
        for (int i = 0; i < vector.size(); i++) {
            if (((MqttWireMessage) vector.elementAt(i)).getMessageId() > messageId) {
                vector.insertElementAt(mqttWireMessage, i);
                return;
            }
        }
        vector.addElement(mqttWireMessage);
    }

    public final Vector j(Vector vector) {
        Vector vector2 = new Vector();
        if (vector.size() == 0) {
            return vector2;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i < vector.size()) {
            int messageId = ((MqttWireMessage) vector.elementAt(i)).getMessageId();
            int i5 = messageId - i2;
            if (i5 > i3) {
                i4 = i;
                i3 = i5;
            }
            i++;
            i2 = messageId;
        }
        int i6 = (65535 - i2) + ((MqttWireMessage) vector.elementAt(0)).getMessageId() > i3 ? 0 : i4;
        for (int i7 = i6; i7 < vector.size(); i7++) {
            vector2.addElement(vector.elementAt(i7));
        }
        for (int i8 = 0; i8 < i6; i8++) {
            vector2.addElement(vector.elementAt(i8));
        }
        return vector2;
    }

    public final synchronized void k(int i) {
        this.c.remove(Integer.valueOf(i));
    }

    public final void l() {
        this.d = new Vector(this.m);
        this.e = new Vector();
        Enumeration keys = this.z.keys();
        while (keys.hasMoreElements()) {
            Object nextElement = keys.nextElement();
            MqttWireMessage mqttWireMessage = (MqttWireMessage) this.z.get(nextElement);
            if (mqttWireMessage instanceof MqttPublish) {
                this.f15445a.fine(E, "restoreInflightMessages", "610", new Object[]{nextElement});
                mqttWireMessage.setDuplicate(true);
                i(this.d, (MqttPublish) mqttWireMessage);
            } else if (mqttWireMessage instanceof MqttPubRel) {
                this.f15445a.fine(E, "restoreInflightMessages", "611", new Object[]{nextElement});
                i(this.e, (MqttPubRel) mqttWireMessage);
            }
        }
        Enumeration keys2 = this.A.keys();
        while (keys2.hasMoreElements()) {
            Object nextElement2 = keys2.nextElement();
            MqttPublish mqttPublish = (MqttPublish) this.A.get(nextElement2);
            mqttPublish.setDuplicate(true);
            this.f15445a.fine(E, "restoreInflightMessages", "612", new Object[]{nextElement2});
            i(this.d, mqttPublish);
        }
        Enumeration keys3 = this.B.keys();
        while (keys3.hasMoreElements()) {
            Object nextElement3 = keys3.nextElement();
            this.f15445a.fine(E, "restoreInflightMessages", "512", new Object[]{nextElement3});
            i(this.d, (MqttPublish) this.B.get(nextElement3));
        }
        this.e = j(this.e);
        this.d = j(this.d);
    }

    public final MqttWireMessage m(String str, MqttPersistable mqttPersistable) throws MqttException {
        MqttWireMessage mqttWireMessage;
        try {
            mqttWireMessage = MqttWireMessage.createWireMessage(mqttPersistable);
        } catch (MqttException e) {
            this.f15445a.fine(E, "restoreMessage", "602", new Object[]{str}, e);
            if (!(e.getCause() instanceof EOFException)) {
                throw e;
            }
            if (str != null) {
                this.k.remove(str);
            }
            mqttWireMessage = null;
        }
        this.f15445a.fine(E, "restoreMessage", "601", new Object[]{str, mqttWireMessage});
        return mqttWireMessage;
    }

    public void notifyComplete(MqttToken mqttToken) throws MqttException {
        MqttWireMessage wireMessage = mqttToken.internalTok.getWireMessage();
        if (wireMessage == null || !(wireMessage instanceof MqttAck)) {
            return;
        }
        Logger logger = this.f15445a;
        String str = E;
        logger.fine(str, "notifyComplete", "629", new Object[]{Integer.valueOf(wireMessage.getMessageId()), mqttToken, wireMessage});
        MqttAck mqttAck = (MqttAck) wireMessage;
        if (mqttAck instanceof MqttPubAck) {
            this.k.remove(h(wireMessage));
            this.k.remove(e(wireMessage));
            this.A.remove(Integer.valueOf(mqttAck.getMessageId()));
            a();
            k(wireMessage.getMessageId());
            this.f.removeToken(wireMessage);
            this.f15445a.fine(str, "notifyComplete", "650", new Object[]{Integer.valueOf(mqttAck.getMessageId())});
        } else if (mqttAck instanceof MqttPubComp) {
            this.k.remove(h(wireMessage));
            this.k.remove(f(wireMessage));
            this.k.remove(e(wireMessage));
            this.z.remove(Integer.valueOf(mqttAck.getMessageId()));
            this.o--;
            a();
            k(wireMessage.getMessageId());
            this.f.removeToken(wireMessage);
            this.f15445a.fine(str, "notifyComplete", "645", new Object[]{Integer.valueOf(mqttAck.getMessageId()), Integer.valueOf(this.o)});
        }
        checkQuiesceLock();
    }

    public void notifyQueueLock() {
        synchronized (this.p) {
            this.f15445a.fine(E, "notifyQueueLock", "638");
            this.p.notifyAll();
        }
    }

    public void notifyReceivedAck(MqttAck mqttAck) throws MqttException {
        this.t = this.l.nanoTime();
        Logger logger = this.f15445a;
        String str = E;
        logger.fine(str, "notifyReceivedAck", "627", new Object[]{Integer.valueOf(mqttAck.getMessageId()), mqttAck});
        MqttToken token = this.f.getToken(mqttAck);
        if (token == null) {
            this.f15445a.fine(str, "notifyReceivedAck", "662", new Object[]{Integer.valueOf(mqttAck.getMessageId())});
        } else if (mqttAck instanceof MqttPubRec) {
            send(new MqttPubRel((MqttPubRec) mqttAck), token);
        } else if (!(mqttAck instanceof MqttPubAck) && !(mqttAck instanceof MqttPubComp)) {
            if (mqttAck instanceof MqttPingResp) {
                synchronized (this.w) {
                    this.x = Math.max(0, this.x - 1);
                    notifyResult(mqttAck, token, null);
                    if (this.x == 0) {
                        this.f.removeToken(mqttAck);
                    }
                }
                this.f15445a.fine(str, "notifyReceivedAck", "636", new Object[]{Integer.valueOf(this.x)});
            } else if (mqttAck instanceof MqttConnack) {
                MqttConnack mqttConnack = (MqttConnack) mqttAck;
                int returnCode = mqttConnack.getReturnCode();
                if (returnCode == 0) {
                    synchronized (this.p) {
                        if (this.j) {
                            clearState();
                            this.f.saveToken(token, mqttAck);
                        }
                        this.o = 0;
                        this.n = 0;
                        l();
                        connected();
                    }
                    this.g.connectComplete(mqttConnack, null);
                    notifyResult(mqttAck, token, null);
                    this.f.removeToken(mqttAck);
                    synchronized (this.p) {
                        this.p.notifyAll();
                    }
                } else {
                    throw ExceptionHelper.createMqttException(returnCode);
                }
            } else {
                notifyResult(mqttAck, token, null);
                k(mqttAck.getMessageId());
                this.f.removeToken(mqttAck);
            }
        } else {
            notifyResult(mqttAck, token, null);
        }
        checkQuiesceLock();
    }

    public void notifyReceivedBytes(int i) {
        if (i > 0) {
            this.t = this.l.nanoTime();
        }
        this.f15445a.fine(E, "notifyReceivedBytes", "630", new Object[]{Integer.valueOf(i)});
    }

    public void notifyReceivedMsg(MqttWireMessage mqttWireMessage) throws MqttException {
        this.t = this.l.nanoTime();
        this.f15445a.fine(E, "notifyReceivedMsg", "651", new Object[]{Integer.valueOf(mqttWireMessage.getMessageId()), mqttWireMessage});
        if (this.r) {
            return;
        }
        if (mqttWireMessage instanceof MqttPublish) {
            MqttPublish mqttPublish = (MqttPublish) mqttWireMessage;
            int qos = mqttPublish.getMessage().getQos();
            if (qos == 0 || qos == 1) {
                CommsCallback commsCallback = this.h;
                if (commsCallback != null) {
                    commsCallback.messageArrived(mqttPublish);
                }
            } else if (qos != 2) {
            } else {
                this.k.put(d(mqttWireMessage), mqttPublish);
                this.C.put(Integer.valueOf(mqttPublish.getMessageId()), mqttPublish);
                send(new MqttPubRec(mqttPublish), null);
            }
        } else if (mqttWireMessage instanceof MqttPubRel) {
            MqttPublish mqttPublish2 = (MqttPublish) this.C.get(Integer.valueOf(mqttWireMessage.getMessageId()));
            if (mqttPublish2 != null) {
                CommsCallback commsCallback2 = this.h;
                if (commsCallback2 != null) {
                    commsCallback2.messageArrived(mqttPublish2);
                    return;
                }
                return;
            }
            send(new MqttPubComp(mqttWireMessage.getMessageId()), null);
        }
    }

    public void notifyResult(MqttWireMessage mqttWireMessage, MqttToken mqttToken, MqttException mqttException) {
        mqttToken.internalTok.markComplete(mqttWireMessage, mqttException);
        mqttToken.internalTok.notifyComplete();
        if (mqttWireMessage != null && (mqttWireMessage instanceof MqttAck) && !(mqttWireMessage instanceof MqttPubRec)) {
            this.f15445a.fine(E, "notifyResult", "648", new Object[]{mqttToken.internalTok.getKey(), mqttWireMessage, mqttException});
            this.h.asyncOperationComplete(mqttToken);
        }
        if (mqttWireMessage == null) {
            this.f15445a.fine(E, "notifyResult", "649", new Object[]{mqttToken.internalTok.getKey(), mqttException});
            this.h.asyncOperationComplete(mqttToken);
        }
    }

    public void notifySent(MqttWireMessage mqttWireMessage) {
        int i;
        this.s = this.l.nanoTime();
        Logger logger = this.f15445a;
        String str = E;
        logger.fine(str, "notifySent", "625", new Object[]{mqttWireMessage.getKey()});
        MqttToken token = mqttWireMessage.getToken();
        if (token == null && (token = this.f.getToken(mqttWireMessage)) == null) {
            return;
        }
        token.internalTok.notifySent();
        if (mqttWireMessage instanceof MqttPingReq) {
            synchronized (this.w) {
                long nanoTime = this.l.nanoTime();
                synchronized (this.w) {
                    this.u = nanoTime;
                    i = this.x + 1;
                    this.x = i;
                }
                this.f15445a.fine(str, "notifySent", "635", new Object[]{Integer.valueOf(i)});
            }
        } else if ((mqttWireMessage instanceof MqttPublish) && ((MqttPublish) mqttWireMessage).getMessage().getQos() == 0) {
            token.internalTok.markComplete(null, null);
            this.h.asyncOperationComplete(token);
            a();
            k(mqttWireMessage.getMessageId());
            this.f.removeToken(mqttWireMessage);
            checkQuiesceLock();
        }
    }

    public void notifySentBytes(int i) {
        if (i > 0) {
            this.s = this.l.nanoTime();
        }
        this.f15445a.fine(E, "notifySentBytes", "643", new Object[]{Integer.valueOf(i)});
    }

    public void persistBufferedMessage(MqttWireMessage mqttWireMessage) throws MqttException {
        String e = e(mqttWireMessage);
        try {
            mqttWireMessage.setMessageId(b());
            String e2 = e(mqttWireMessage);
            try {
                this.k.put(e2, (MqttPublish) mqttWireMessage);
            } catch (MqttPersistenceException unused) {
                this.f15445a.fine(E, "persistBufferedMessage", "515");
                this.k.open(this.g.getClient().getClientId(), this.g.getClient().getServerURI());
                this.k.put(e2, (MqttPublish) mqttWireMessage);
            }
            this.f15445a.fine(E, "persistBufferedMessage", "513", new Object[]{e2});
        } catch (MqttException e3) {
            this.f15445a.warning(E, "persistBufferedMessage", "514", new Object[]{e});
            throw e3;
        }
    }

    public void quiesce(long j) {
        if (j > 0) {
            Logger logger = this.f15445a;
            String str = E;
            logger.fine(str, "quiesce", "637", new Object[]{Long.valueOf(j)});
            synchronized (this.p) {
                this.r = true;
            }
            this.h.quiesce();
            notifyQueueLock();
            synchronized (this.q) {
                try {
                    int count = this.f.count();
                    if (count > 0 || this.e.size() > 0 || !this.h.isQuiesced()) {
                        this.f15445a.fine(str, "quiesce", "639", new Object[]{Integer.valueOf(this.n), Integer.valueOf(this.e.size()), Integer.valueOf(this.o), Integer.valueOf(count)});
                        this.q.wait(j);
                    }
                } catch (InterruptedException unused) {
                }
            }
            synchronized (this.p) {
                this.d.clear();
                this.e.clear();
                this.r = false;
                this.n = 0;
            }
            this.f15445a.fine(E, "quiesce", "640");
        }
    }

    public boolean removeMessage(IMqttDeliveryToken iMqttDeliveryToken) throws MqttException {
        boolean z;
        MqttMessage message = iMqttDeliveryToken.getMessage();
        int messageId = iMqttDeliveryToken.getMessageId();
        synchronized (this.p) {
            z = true;
            boolean z2 = message.getQos() == 1 && this.A.remove(Integer.valueOf(messageId)) != null;
            if (message.getQos() == 2 && this.z.remove(Integer.valueOf(messageId)) != null) {
                z2 = true;
            }
            if (!this.d.removeElement(message)) {
                z = z2;
            }
            this.k.remove(g(messageId));
            this.f.removeToken(Integer.toString(messageId));
            k(messageId);
            a();
        }
        return z;
    }

    public Vector resolveOldTokens(MqttException mqttException) {
        this.f15445a.fine(E, "resolveOldTokens", "632", new Object[]{mqttException});
        if (mqttException == null) {
            mqttException = new MqttException(32102);
        }
        Vector outstandingTokens = this.f.getOutstandingTokens();
        Enumeration elements = outstandingTokens.elements();
        while (elements.hasMoreElements()) {
            MqttToken mqttToken = (MqttToken) elements.nextElement();
            synchronized (mqttToken) {
                if (!mqttToken.isComplete() && !mqttToken.internalTok.isCompletePending() && mqttToken.getException() == null) {
                    mqttToken.internalTok.setException(mqttException);
                }
            }
            if (!(mqttToken instanceof MqttDeliveryToken)) {
                this.f.removeToken(mqttToken.internalTok.getKey());
            }
        }
        return outstandingTokens;
    }

    public void restoreState() throws MqttException {
        Enumeration keys = this.k.keys();
        int i = this.b;
        Vector vector = new Vector();
        this.f15445a.fine(E, "restoreState", "600");
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            MqttWireMessage m = m(str, this.k.get(str));
            if (m != null) {
                if (str.startsWith("r-")) {
                    this.f15445a.fine(E, "restoreState", "604", new Object[]{str, m});
                    this.C.put(Integer.valueOf(m.getMessageId()), m);
                } else if (str.startsWith("s-")) {
                    MqttPublish mqttPublish = (MqttPublish) m;
                    i = Math.max(mqttPublish.getMessageId(), i);
                    if (this.k.containsKey(f(mqttPublish))) {
                        MqttPubRel mqttPubRel = (MqttPubRel) m(str, this.k.get(f(mqttPublish)));
                        if (mqttPubRel != null) {
                            this.f15445a.fine(E, "restoreState", "605", new Object[]{str, m});
                            this.z.put(Integer.valueOf(mqttPubRel.getMessageId()), mqttPubRel);
                        } else {
                            this.f15445a.fine(E, "restoreState", "606", new Object[]{str, m});
                        }
                    } else {
                        mqttPublish.setDuplicate(true);
                        if (mqttPublish.getMessage().getQos() == 2) {
                            this.f15445a.fine(E, "restoreState", "607", new Object[]{str, m});
                            this.z.put(Integer.valueOf(mqttPublish.getMessageId()), mqttPublish);
                        } else {
                            this.f15445a.fine(E, "restoreState", "608", new Object[]{str, m});
                            this.A.put(Integer.valueOf(mqttPublish.getMessageId()), mqttPublish);
                        }
                    }
                    this.f.restoreToken(mqttPublish).internalTok.setClient(this.g.getClient());
                    this.c.put(Integer.valueOf(mqttPublish.getMessageId()), Integer.valueOf(mqttPublish.getMessageId()));
                } else if (str.startsWith("sb-")) {
                    MqttPublish mqttPublish2 = (MqttPublish) m;
                    i = Math.max(mqttPublish2.getMessageId(), i);
                    if (mqttPublish2.getMessage().getQos() == 2) {
                        this.f15445a.fine(E, "restoreState", "607", new Object[]{str, m});
                        this.z.put(Integer.valueOf(mqttPublish2.getMessageId()), mqttPublish2);
                    } else if (mqttPublish2.getMessage().getQos() == 1) {
                        this.f15445a.fine(E, "restoreState", "608", new Object[]{str, m});
                        this.A.put(Integer.valueOf(mqttPublish2.getMessageId()), mqttPublish2);
                    } else {
                        this.f15445a.fine(E, "restoreState", "511", new Object[]{str, m});
                        this.B.put(Integer.valueOf(mqttPublish2.getMessageId()), mqttPublish2);
                        this.k.remove(str);
                    }
                    this.f.restoreToken(mqttPublish2).internalTok.setClient(this.g.getClient());
                    this.c.put(Integer.valueOf(mqttPublish2.getMessageId()), Integer.valueOf(mqttPublish2.getMessageId()));
                } else if (str.startsWith("sc-") && !this.k.containsKey(h((MqttPubRel) m))) {
                    vector.addElement(str);
                }
            }
        }
        Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            String str2 = (String) elements.nextElement();
            this.f15445a.fine(E, "restoreState", "609", new Object[]{str2});
            this.k.remove(str2);
        }
        this.b = i;
    }

    public void send(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        if (mqttWireMessage.isMessageIdRequired() && mqttWireMessage.getMessageId() == 0) {
            if ((mqttWireMessage instanceof MqttPublish) && ((MqttPublish) mqttWireMessage).getMessage().getQos() != 0) {
                mqttWireMessage.setMessageId(b());
            } else if ((mqttWireMessage instanceof MqttPubAck) || (mqttWireMessage instanceof MqttPubRec) || (mqttWireMessage instanceof MqttPubRel) || (mqttWireMessage instanceof MqttPubComp) || (mqttWireMessage instanceof MqttSubscribe) || (mqttWireMessage instanceof MqttSuback) || (mqttWireMessage instanceof MqttUnsubscribe) || (mqttWireMessage instanceof MqttUnsubAck)) {
                mqttWireMessage.setMessageId(b());
            }
        }
        if (mqttToken != null) {
            mqttWireMessage.setToken(mqttToken);
            try {
                mqttToken.internalTok.setMessageID(mqttWireMessage.getMessageId());
            } catch (Exception unused) {
            }
        }
        if (mqttWireMessage instanceof MqttPublish) {
            synchronized (this.p) {
                int i = this.n;
                if (i < this.m) {
                    MqttMessage message = ((MqttPublish) mqttWireMessage).getMessage();
                    this.f15445a.fine(E, MqttServiceConstants.SEND_ACTION, "628", new Object[]{Integer.valueOf(mqttWireMessage.getMessageId()), Integer.valueOf(message.getQos()), mqttWireMessage});
                    int qos = message.getQos();
                    if (qos == 1) {
                        this.A.put(Integer.valueOf(mqttWireMessage.getMessageId()), mqttWireMessage);
                        this.k.put(h(mqttWireMessage), (MqttPublish) mqttWireMessage);
                        this.f.saveToken(mqttToken, mqttWireMessage);
                    } else if (qos == 2) {
                        this.z.put(Integer.valueOf(mqttWireMessage.getMessageId()), mqttWireMessage);
                        this.k.put(h(mqttWireMessage), (MqttPublish) mqttWireMessage);
                        this.f.saveToken(mqttToken, mqttWireMessage);
                    }
                    this.d.addElement(mqttWireMessage);
                    this.p.notifyAll();
                } else {
                    this.f15445a.fine(E, MqttServiceConstants.SEND_ACTION, "613", new Object[]{Integer.valueOf(i)});
                    throw new MqttException(32202);
                }
            }
            return;
        }
        this.f15445a.fine(E, MqttServiceConstants.SEND_ACTION, "615", new Object[]{Integer.valueOf(mqttWireMessage.getMessageId()), mqttWireMessage});
        if (mqttWireMessage instanceof MqttConnect) {
            synchronized (this.p) {
                this.f.saveToken(mqttToken, mqttWireMessage);
                this.e.insertElementAt(mqttWireMessage, 0);
                this.p.notifyAll();
            }
            return;
        }
        if (mqttWireMessage instanceof MqttPingReq) {
            this.v = mqttWireMessage;
        } else if (mqttWireMessage instanceof MqttPubRel) {
            this.z.put(Integer.valueOf(mqttWireMessage.getMessageId()), mqttWireMessage);
            this.k.put(f(mqttWireMessage), (MqttPubRel) mqttWireMessage);
        } else if (mqttWireMessage instanceof MqttPubComp) {
            this.k.remove(d(mqttWireMessage));
        }
        synchronized (this.p) {
            if (!(mqttWireMessage instanceof MqttAck)) {
                this.f.saveToken(mqttToken, mqttWireMessage);
            }
            this.e.addElement(mqttWireMessage);
            this.p.notifyAll();
        }
    }

    public void setCleanSession(boolean z) {
        this.j = z;
    }

    public void setKeepAliveInterval(long j) {
        this.i = TimeUnit.MILLISECONDS.toNanos(j);
    }

    public void setKeepAliveSecs(long j) {
        this.i = TimeUnit.SECONDS.toNanos(j);
    }

    public void setMaxInflight(int i) {
        this.m = i;
        this.d = new Vector(this.m);
    }

    public void unPersistBufferedMessage(MqttWireMessage mqttWireMessage) {
        try {
            this.f15445a.fine(E, "unPersistBufferedMessage", "517", new Object[]{mqttWireMessage.getKey()});
            this.k.remove(e(mqttWireMessage));
        } catch (MqttPersistenceException unused) {
            this.f15445a.fine(E, "unPersistBufferedMessage", "518", new Object[]{mqttWireMessage.getKey()});
        }
    }

    public void undo(MqttPublish mqttPublish) throws MqttPersistenceException {
        synchronized (this.p) {
            this.f15445a.fine(E, "undo", "618", new Object[]{Integer.valueOf(mqttPublish.getMessageId()), Integer.valueOf(mqttPublish.getMessage().getQos())});
            if (mqttPublish.getMessage().getQos() == 1) {
                this.A.remove(Integer.valueOf(mqttPublish.getMessageId()));
            } else {
                this.z.remove(Integer.valueOf(mqttPublish.getMessageId()));
            }
            this.d.removeElement(mqttPublish);
            this.k.remove(h(mqttPublish));
            this.f.removeToken(mqttPublish);
            if (mqttPublish.getMessage().getQos() > 0) {
                k(mqttPublish.getMessageId());
                mqttPublish.setMessageId(0);
            }
            checkQuiesceLock();
        }
    }

    public void deliveryComplete(int i) throws MqttPersistenceException {
        this.f15445a.fine(E, "deliveryComplete", "641", new Object[]{Integer.valueOf(i)});
        this.k.remove(c(i));
        this.C.remove(Integer.valueOf(i));
    }
}
