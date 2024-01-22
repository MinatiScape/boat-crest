package org.eclipse.paho.client.mqttv3.internal;

import com.coveiot.android.leonardo.utils.MusicConstants;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttOutputStream;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class CommsSender implements Runnable {
    public static final String s = CommsSender.class.getName();
    public Logger h = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, s);
    public a i;
    public a j;
    public final Object k;
    public Thread l;
    public String m;
    public Future<?> n;
    public ClientState o;
    public MqttOutputStream p;
    public ClientComms q;
    public CommsTokenStore r;

    /* loaded from: classes13.dex */
    public enum a {
        STOPPED,
        RUNNING,
        STARTING;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static a[] valuesCustom() {
            a[] valuesCustom = values();
            int length = valuesCustom.length;
            a[] aVarArr = new a[length];
            System.arraycopy(valuesCustom, 0, aVarArr, 0, length);
            return aVarArr;
        }
    }

    public CommsSender(ClientComms clientComms, ClientState clientState, CommsTokenStore commsTokenStore, OutputStream outputStream) {
        a aVar = a.STOPPED;
        this.i = aVar;
        this.j = aVar;
        this.k = new Object();
        this.l = null;
        this.o = null;
        this.q = null;
        this.r = null;
        this.p = new MqttOutputStream(clientState, outputStream);
        this.q = clientComms;
        this.o = clientState;
        this.r = commsTokenStore;
        this.h.setResourceName(clientComms.getClient().getClientId());
    }

    public final void a(MqttWireMessage mqttWireMessage, Exception exc) {
        MqttException mqttException;
        this.h.fine(s, "handleRunException", "804", null, exc);
        if (!(exc instanceof MqttException)) {
            mqttException = new MqttException(32109, exc);
        } else {
            mqttException = (MqttException) exc;
        }
        synchronized (this.k) {
            this.j = a.STOPPED;
        }
        this.q.shutdownConnection(null, mqttException);
    }

    public boolean isRunning() {
        boolean z;
        synchronized (this.k) {
            a aVar = this.i;
            a aVar2 = a.RUNNING;
            z = aVar == aVar2 && this.j == aVar2;
        }
        return z;
    }

    @Override // java.lang.Runnable
    public void run() {
        a aVar;
        a aVar2;
        Thread currentThread = Thread.currentThread();
        this.l = currentThread;
        currentThread.setName(this.m);
        synchronized (this.k) {
            this.i = a.RUNNING;
        }
        try {
            synchronized (this.k) {
                aVar = this.j;
            }
            MqttWireMessage mqttWireMessage = null;
            while (aVar == a.RUNNING && this.p != null) {
                try {
                    mqttWireMessage = this.o.get();
                    if (mqttWireMessage != null) {
                        this.h.fine(s, "run", "802", new Object[]{mqttWireMessage.getKey(), mqttWireMessage});
                        if (mqttWireMessage instanceof MqttAck) {
                            this.p.write(mqttWireMessage);
                            this.p.flush();
                        } else {
                            MqttToken token = mqttWireMessage.getToken();
                            if (token == null) {
                                token = this.r.getToken(mqttWireMessage);
                            }
                            if (token != null) {
                                synchronized (token) {
                                    this.p.write(mqttWireMessage);
                                    try {
                                        this.p.flush();
                                    } catch (IOException e) {
                                        if (!(mqttWireMessage instanceof MqttDisconnect)) {
                                            throw e;
                                        }
                                    }
                                    this.o.notifySent(mqttWireMessage);
                                }
                            }
                        }
                    } else {
                        this.h.fine(s, "run", "803");
                        synchronized (this.k) {
                            this.j = a.STOPPED;
                        }
                    }
                } catch (MqttException e2) {
                    a(mqttWireMessage, e2);
                } catch (Exception e3) {
                    a(mqttWireMessage, e3);
                }
                synchronized (this.k) {
                    aVar2 = this.j;
                }
                aVar = aVar2;
            }
            synchronized (this.k) {
                this.i = a.STOPPED;
                this.l = null;
            }
            this.h.fine(s, "run", "805");
        } catch (Throwable th) {
            synchronized (this.k) {
                this.i = a.STOPPED;
                this.l = null;
                throw th;
            }
        }
    }

    public void start(String str, ExecutorService executorService) {
        this.m = str;
        synchronized (this.k) {
            a aVar = this.i;
            a aVar2 = a.STOPPED;
            if (aVar == aVar2 && this.j == aVar2) {
                this.j = a.RUNNING;
                if (executorService == null) {
                    new Thread(this).start();
                } else {
                    this.n = executorService.submit(this);
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
        if (isRunning()) {
            synchronized (this.k) {
                Future<?> future = this.n;
                if (future != null) {
                    future.cancel(true);
                }
                this.h.fine(s, MusicConstants.CMDSTOP, "800");
                if (isRunning()) {
                    this.j = a.STOPPED;
                    this.o.notifyQueueLock();
                }
            }
            while (isRunning()) {
                try {
                    Thread.sleep(100L);
                } catch (Exception unused) {
                }
                this.o.notifyQueueLock();
            }
            this.h.fine(s, MusicConstants.CMDSTOP, "801");
        }
    }
}
