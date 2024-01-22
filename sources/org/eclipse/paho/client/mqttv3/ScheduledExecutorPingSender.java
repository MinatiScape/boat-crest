package org.eclipse.paho.client.mqttv3;

import com.coveiot.android.leonardo.utils.MusicConstants;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class ScheduledExecutorPingSender implements MqttPingSender {
    public static final String f = "org.eclipse.paho.client.mqttv3.ScheduledExecutorPingSender";

    /* renamed from: a  reason: collision with root package name */
    public final Logger f15440a = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, f);
    public ClientComms b;
    public ScheduledExecutorService c;
    public ScheduledFuture d;
    public String e;

    /* loaded from: classes13.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String name = Thread.currentThread().getName();
            Thread currentThread = Thread.currentThread();
            currentThread.setName("MQTT Ping: " + ScheduledExecutorPingSender.this.e);
            ScheduledExecutorPingSender.this.f15440a.fine(ScheduledExecutorPingSender.f, "PingTask.run", "660", new Object[]{Long.valueOf(System.nanoTime())});
            ScheduledExecutorPingSender.this.b.checkForActivity();
            Thread.currentThread().setName(name);
        }

        public /* synthetic */ a(ScheduledExecutorPingSender scheduledExecutorPingSender, a aVar) {
            this();
        }
    }

    public ScheduledExecutorPingSender(ScheduledExecutorService scheduledExecutorService) {
        if (scheduledExecutorService != null) {
            this.c = scheduledExecutorService;
            return;
        }
        throw new IllegalArgumentException("ExecutorService cannot be null.");
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void init(ClientComms clientComms) {
        if (clientComms != null) {
            this.b = clientComms;
            this.e = clientComms.getClient().getClientId();
            return;
        }
        throw new IllegalArgumentException("ClientComms cannot be null.");
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void schedule(long j) {
        this.d = this.c.schedule(new a(this, null), j, TimeUnit.MILLISECONDS);
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void start() {
        this.f15440a.fine(f, "start", "659", new Object[]{this.e});
        schedule(this.b.getKeepAlive());
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void stop() {
        this.f15440a.fine(f, MusicConstants.CMDSTOP, "661", null);
        ScheduledFuture scheduledFuture = this.d;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }
}
