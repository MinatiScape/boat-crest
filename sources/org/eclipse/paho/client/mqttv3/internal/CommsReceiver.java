package org.eclipse.paho.client.mqttv3.internal;

import com.coveiot.android.leonardo.utils.MusicConstants;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttInputStream;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class CommsReceiver implements Runnable {
    public static final String s = CommsReceiver.class.getName();
    public Logger h = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, s);
    public a i;
    public a j;
    public final Object k;
    public String l;
    public Future<?> m;
    public ClientState n;
    public ClientComms o;
    public MqttInputStream p;
    public CommsTokenStore q;
    public Thread r;

    /* loaded from: classes13.dex */
    public enum a {
        STOPPED,
        RUNNING,
        STARTING,
        RECEIVING;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static a[] valuesCustom() {
            a[] valuesCustom = values();
            int length = valuesCustom.length;
            a[] aVarArr = new a[length];
            System.arraycopy(valuesCustom, 0, aVarArr, 0, length);
            return aVarArr;
        }
    }

    public CommsReceiver(ClientComms clientComms, ClientState clientState, CommsTokenStore commsTokenStore, InputStream inputStream) {
        a aVar = a.STOPPED;
        this.i = aVar;
        this.j = aVar;
        this.k = new Object();
        this.n = null;
        this.o = null;
        this.q = null;
        this.r = null;
        this.p = new MqttInputStream(clientState, inputStream);
        this.o = clientComms;
        this.n = clientState;
        this.q = commsTokenStore;
        this.h.setResourceName(clientComms.getClient().getClientId());
    }

    public boolean isReceiving() {
        boolean z;
        synchronized (this.k) {
            z = this.i == a.RECEIVING;
        }
        return z;
    }

    public boolean isRunning() {
        boolean z;
        synchronized (this.k) {
            a aVar = this.i;
            a aVar2 = a.RUNNING;
            z = (aVar == aVar2 || aVar == a.RECEIVING) && this.j == aVar2;
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:127:0x0134, code lost:
        r1 = r9.k;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0136, code lost:
        monitor-enter(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0137, code lost:
        r9.i = org.eclipse.paho.client.mqttv3.internal.CommsReceiver.a.STOPPED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x013b, code lost:
        monitor-exit(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x013c, code lost:
        r9.r = null;
        r9.h.fine(org.eclipse.paho.client.mqttv3.internal.CommsReceiver.s, "run", "854");
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0149, code lost:
        return;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 352
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.paho.client.mqttv3.internal.CommsReceiver.run():void");
    }

    public void start(String str, ExecutorService executorService) {
        this.l = str;
        this.h.fine(s, "start", "855");
        synchronized (this.k) {
            a aVar = this.i;
            a aVar2 = a.STOPPED;
            if (aVar == aVar2 && this.j == aVar2) {
                this.j = a.RUNNING;
                if (executorService == null) {
                    new Thread(this).start();
                } else {
                    this.m = executorService.submit(this);
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
        synchronized (this.k) {
            Future<?> future = this.m;
            if (future != null) {
                future.cancel(true);
            }
            this.h.fine(s, MusicConstants.CMDSTOP, "850");
            if (isRunning()) {
                this.j = a.STOPPED;
            }
        }
        while (isRunning()) {
            try {
                Thread.sleep(100L);
            } catch (Exception unused) {
            }
        }
        this.h.fine(s, MusicConstants.CMDSTOP, "851");
    }
}
