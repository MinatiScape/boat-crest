package org.eclipse.paho.client.mqttv3;

import com.coveiot.android.leonardo.utils.MusicConstants;
import java.util.Timer;
import java.util.TimerTask;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class TimerPingSender implements MqttPingSender {
    public static final String e = "org.eclipse.paho.client.mqttv3.TimerPingSender";

    /* renamed from: a  reason: collision with root package name */
    public Logger f15441a = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, e);
    public ClientComms b;
    public Timer c;
    public String d;

    /* loaded from: classes13.dex */
    public class a extends TimerTask {
        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            TimerPingSender.this.f15441a.fine(TimerPingSender.e, "PingTask.run", "660", new Object[]{Long.valueOf(System.nanoTime())});
            TimerPingSender.this.b.checkForActivity();
        }

        public /* synthetic */ a(TimerPingSender timerPingSender, a aVar) {
            this();
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void init(ClientComms clientComms) {
        if (clientComms != null) {
            this.b = clientComms;
            String clientId = clientComms.getClient().getClientId();
            this.d = clientId;
            this.f15441a.setResourceName(clientId);
            return;
        }
        throw new IllegalArgumentException("ClientComms cannot be null.");
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void schedule(long j) {
        this.c.schedule(new a(this, null), j);
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void start() {
        this.f15441a.fine(e, "start", "659", new Object[]{this.d});
        Timer timer = new Timer("MQTT Ping: " + this.d);
        this.c = timer;
        timer.schedule(new a(this, null), this.b.getKeepAlive());
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void stop() {
        this.f15441a.fine(e, MusicConstants.CMDSTOP, "661", null);
        Timer timer = this.c;
        if (timer != null) {
            timer.cancel();
        }
    }
}
