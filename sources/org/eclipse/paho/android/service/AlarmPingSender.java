package org.eclipse.paho.android.service;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
/* loaded from: classes13.dex */
public class AlarmPingSender implements MqttPingSender {

    /* renamed from: a  reason: collision with root package name */
    public ClientComms f15426a;
    public MqttService b;
    public BroadcastReceiver c;
    public AlarmPingSender d;
    public PendingIntent e;
    public volatile boolean f = false;

    /* loaded from: classes13.dex */
    public class AlarmReceiver extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        public PowerManager.WakeLock f15427a;
        public final String b;

        public AlarmReceiver() {
            this.b = MqttServiceConstants.PING_WAKELOCK + AlarmPingSender.this.d.f15426a.getClient().getClientId();
        }

        @Override // android.content.BroadcastReceiver
        @SuppressLint({"Wakelock"})
        public void onReceive(Context context, Intent intent) {
            Log.d("AlarmPingSender", "Sending Ping at:" + System.currentTimeMillis());
            PowerManager.WakeLock newWakeLock = ((PowerManager) AlarmPingSender.this.b.getSystemService("power")).newWakeLock(1, this.b);
            this.f15427a = newWakeLock;
            newWakeLock.acquire();
            if (AlarmPingSender.this.f15426a.checkForActivity(new IMqttActionListener() { // from class: org.eclipse.paho.android.service.AlarmPingSender.AlarmReceiver.1
                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    Log.d("AlarmPingSender", "Failure. Release lock(" + AlarmReceiver.this.b + "):" + System.currentTimeMillis());
                    AlarmReceiver.this.f15427a.release();
                }

                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onSuccess(IMqttToken iMqttToken) {
                    Log.d("AlarmPingSender", "Success. Release lock(" + AlarmReceiver.this.b + "):" + System.currentTimeMillis());
                    AlarmReceiver.this.f15427a.release();
                }
            }) == null && this.f15427a.isHeld()) {
                this.f15427a.release();
            }
        }
    }

    public AlarmPingSender(MqttService mqttService) {
        if (mqttService == null) {
            throw new IllegalArgumentException("Neither service nor client can be null.");
        }
        this.b = mqttService;
        this.d = this;
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void init(ClientComms clientComms) {
        this.f15426a = clientComms;
        this.c = new AlarmReceiver();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void schedule(long j) {
        long currentTimeMillis = System.currentTimeMillis() + j;
        Log.d("AlarmPingSender", "Schedule next alarm at " + currentTimeMillis);
        AlarmManager alarmManager = (AlarmManager) this.b.getSystemService(NotificationCompat.CATEGORY_ALARM);
        int i = Build.VERSION.SDK_INT;
        if (i >= 23) {
            Log.d("AlarmPingSender", "Alarm scheule using setExactAndAllowWhileIdle, next: " + j);
            alarmManager.setExactAndAllowWhileIdle(0, currentTimeMillis, this.e);
        } else if (i < 19) {
            alarmManager.set(0, currentTimeMillis, this.e);
        } else {
            Log.d("AlarmPingSender", "Alarm scheule using setExact, delay: " + j);
            alarmManager.setExact(0, currentTimeMillis, this.e);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void start() {
        String str = MqttServiceConstants.PING_SENDER + this.f15426a.getClient().getClientId();
        Log.d("AlarmPingSender", "Register alarmreceiver to MqttService" + str);
        this.b.registerReceiver(this.c, new IntentFilter(str));
        this.e = PendingIntent.getBroadcast(this.b, 0, new Intent(str), 33554432);
        schedule(this.f15426a.getKeepAlive());
        this.f = true;
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPingSender
    public void stop() {
        Log.d("AlarmPingSender", "Unregister alarmreceiver to MqttService" + this.f15426a.getClient().getClientId());
        if (this.f) {
            if (this.e != null) {
                ((AlarmManager) this.b.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(this.e);
            }
            this.f = false;
            try {
                this.b.unregisterReceiver(this.c);
            } catch (IllegalArgumentException unused) {
            }
        }
    }
}
