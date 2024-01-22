package com.abupdate.mqtt_libs.mqtt_service;

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
import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.IMqttToken;
import com.abupdate.mqtt_libs.mqttv3.l;
import java.text.SimpleDateFormat;
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes.dex */
public class a implements l {

    /* renamed from: a  reason: collision with root package name */
    public com.abupdate.mqtt_libs.mqttv3.a.a f1930a;
    public MqttService b;
    public BroadcastReceiver c;
    public a d;
    public PendingIntent e;
    public volatile boolean f = false;
    public SimpleDateFormat g;

    /* renamed from: com.abupdate.mqtt_libs.mqtt_service.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0194a extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        public PowerManager.WakeLock f1931a;
        public final String b;

        /* renamed from: com.abupdate.mqtt_libs.mqtt_service.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0195a implements IMqttActionListener {
            public C0195a() {
            }

            @Override // com.abupdate.mqtt_libs.mqttv3.IMqttActionListener
            public void onFailure(IMqttToken iMqttToken, Throwable th) {
                Log.d("AlarmPingSender", "Failure. Release lock(" + C0194a.this.b + "):" + a.this.d(System.currentTimeMillis()));
                C0194a.this.f1931a.release();
            }

            @Override // com.abupdate.mqtt_libs.mqttv3.IMqttActionListener
            public void onSuccess(IMqttToken iMqttToken) {
                Log.d("AlarmPingSender", "Success. Release lock(" + C0194a.this.b + "):" + a.this.d(System.currentTimeMillis()));
                C0194a.this.f1931a.release();
            }
        }

        public C0194a() {
            this.b = MqttServiceConstants.PING_WAKELOCK + a.this.d.f1930a.i().getClientId();
        }

        @Override // android.content.BroadcastReceiver
        @SuppressLint({"Wakelock"})
        public void onReceive(Context context, Intent intent) {
            Log.d("AlarmPingSender", "Sending Ping at:" + a.this.d(System.currentTimeMillis()));
            PowerManager.WakeLock newWakeLock = ((PowerManager) a.this.b.getSystemService("power")).newWakeLock(1, this.b);
            this.f1931a = newWakeLock;
            newWakeLock.acquire();
            if (a.this.f1930a.a(new C0195a()) == null && this.f1931a.isHeld()) {
                this.f1931a.release();
            }
        }
    }

    public a(MqttService mqttService) {
        if (mqttService != null) {
            this.b = mqttService;
            this.d = this;
            return;
        }
        throw new IllegalArgumentException("Neither service nor client can be null.");
    }

    public final String d(long j) {
        if (this.g == null) {
            this.g = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return this.g.format(Long.valueOf(j));
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.l
    public void a(com.abupdate.mqtt_libs.mqttv3.a.a aVar) {
        this.f1930a = aVar;
        this.c = new C0194a();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.l
    public void b() {
        Log.d("AlarmPingSender", "Unregister alarmreceiver to MqttService" + this.f1930a.i().getClientId());
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

    @Override // com.abupdate.mqtt_libs.mqttv3.l
    public void a() {
        String str = MqttServiceConstants.PING_SENDER + this.f1930a.i().getClientId();
        Log.d("AlarmPingSender", "Register alarmreceiver to MqttService" + str);
        this.b.registerReceiver(this.c, new IntentFilter(str));
        this.e = PendingIntent.getBroadcast(this.b, 0, new Intent(str), 134217728);
        a(this.f1930a.j());
        this.f = true;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.l
    public void a(long j) {
        long currentTimeMillis = System.currentTimeMillis() + j;
        Log.d("AlarmPingSender", "Schedule next alarm at " + d(currentTimeMillis));
        AlarmManager alarmManager = (AlarmManager) this.b.getSystemService(NotificationCompat.CATEGORY_ALARM);
        int i = Build.VERSION.SDK_INT;
        if (i >= 23) {
            Log.d("AlarmPingSender", "Alarm scheule using setExactAndAllowWhileIdle, next: " + j);
            alarmManager.setExactAndAllowWhileIdle(0, currentTimeMillis, this.e);
        } else if (i >= 19) {
            Log.d("AlarmPingSender", "Alarm scheule using setExact, delay: " + j);
            alarmManager.setExact(0, currentTimeMillis, this.e);
        } else {
            alarmManager.set(0, currentTimeMillis, this.e);
        }
    }
}
