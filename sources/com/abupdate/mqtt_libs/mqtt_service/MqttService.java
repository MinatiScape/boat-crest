package com.abupdate.mqtt_libs.mqtt_service;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.abupdate.mqtt_libs.connect.MqttManager;
import com.abupdate.mqtt_libs.connect.Utils;
import com.abupdate.mqtt_libs.mqttv3.IMqttDeliveryToken;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.MqttMessage;
import com.abupdate.mqtt_libs.mqttv3.k;
import com.abupdate.mqtt_libs.mqttv3.m;
import com.abupdate.trace.Trace;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.paho.android.service.MqttServiceConstants;
@SuppressLint({"Registered"})
/* loaded from: classes.dex */
public class MqttService extends Service implements MqttTraceHandler {
    public String h;
    public com.abupdate.mqtt_libs.mqtt_service.c j;
    public d k;
    public b l;
    public f n;
    public AlarmManager p;
    public PendingIntent q;
    public c r;
    public SimpleDateFormat s;
    public boolean i = false;
    public volatile boolean m = true;
    public Map<String, com.abupdate.mqtt_libs.mqtt_service.d> o = new ConcurrentHashMap();

    /* loaded from: classes.dex */
    public class b extends BroadcastReceiver {
        public b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MqttService.this.traceDebug("MqttService", "Reconnect since BroadcastReceiver.");
            if (((ConnectivityManager) MqttService.this.getSystemService("connectivity")).getBackgroundDataSetting()) {
                if (MqttService.this.m) {
                    return;
                }
                MqttService.this.m = true;
                MqttService.this.h();
                return;
            }
            MqttService.this.m = false;
            MqttService.this.g();
        }
    }

    /* loaded from: classes.dex */
    public class c extends BroadcastReceiver {
        public c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            action.hashCode();
            if (action.equals("ACTION_ALARM_KEEP_CONNECT")) {
                Trace.d("MqttService", "onReceive() receive alarm_connect_engine's message");
                if (MqttManager.getInstance().isConneect()) {
                    Trace.d("MqttService", "onReceive() socket have connected");
                    MqttService.this.stopKeepConnect();
                } else if (Utils.isNetWorkAvailable(MqttService.this)) {
                    Trace.d("MqttService", "onReceive() try to reconnect");
                    MqttService.this.h();
                } else {
                    Trace.d("MqttService", "onReceive() The current network is not available");
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class d extends BroadcastReceiver {
        public d() {
        }

        @Override // android.content.BroadcastReceiver
        @SuppressLint({"Wakelock"})
        public void onReceive(Context context, Intent intent) {
            MqttService.this.traceDebug("MqttService", "Internal network status receive.");
            PowerManager.WakeLock newWakeLock = ((PowerManager) MqttService.this.getSystemService("power")).newWakeLock(1, "MQTT");
            newWakeLock.acquire();
            MqttService.this.traceDebug("MqttService", "Reconnect for Network recovery.");
            if (!MqttService.this.isOnline()) {
                MqttService.this.g();
            } else {
                MqttService.this.traceDebug("MqttService", "Online,reconnect.");
                MqttService.this.h();
            }
            newWakeLock.release();
        }
    }

    public h acknowledgeMessageArrival(String str, String str2) {
        if (this.j.a(str, str2)) {
            return h.OK;
        }
        return h.ERROR;
    }

    public void close(String str) {
        e(str).z();
    }

    public void connect(String str, com.abupdate.mqtt_libs.mqttv3.h hVar, String str2, String str3) throws m, MqttException {
        e(str).l(hVar, null, str3);
    }

    public boolean containClient(String str) {
        return this.o.containsKey(str);
    }

    public void d(String str, h hVar, Bundle bundle) {
        Intent intent = new Intent(MqttServiceConstants.CALLBACK_TO_ACTIVITY);
        if (str != null) {
            intent.putExtra(MqttServiceConstants.CALLBACK_CLIENT_HANDLE, str);
        }
        intent.putExtra(MqttServiceConstants.CALLBACK_STATUS, hVar);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void deleteBufferedMessage(String str, int i) {
        e(str).w(i);
    }

    public void disconnect(String str, String str2, String str3) {
        e(str).o(str2, str3);
        this.o.remove(str);
        stopSelf();
    }

    public final com.abupdate.mqtt_libs.mqtt_service.d e(String str) {
        com.abupdate.mqtt_libs.mqtt_service.d dVar = this.o.get(str);
        if (dVar != null) {
            return dVar;
        }
        throw new IllegalArgumentException("Invalid ClientHandle");
    }

    public final String f(long j) {
        if (this.s == null) {
            this.s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return this.s.format(Long.valueOf(j));
    }

    public final void g() {
        for (com.abupdate.mqtt_libs.mqtt_service.d dVar : this.o.values()) {
            dVar.C();
        }
    }

    public MqttMessage getBufferedMessage(String str, int i) {
        return e(str).e(i);
    }

    public int getBufferedMessageCount(String str) {
        return e(str).E();
    }

    public String getClient(String str, String str2, String str3, com.abupdate.mqtt_libs.mqttv3.g gVar) {
        String str4 = str + ":" + str2 + ":" + str3;
        if (!this.o.containsKey(str4)) {
            this.o.put(str4, new com.abupdate.mqtt_libs.mqtt_service.d(this, str, str2, gVar, str4));
        }
        return str4;
    }

    public IMqttDeliveryToken[] getPendingDeliveryTokens(String str) {
        return e(str).B();
    }

    public void h() {
        traceDebug("MqttService", "Reconnect to server, client size=" + this.o.size());
        for (com.abupdate.mqtt_libs.mqtt_service.d dVar : this.o.values()) {
            traceDebug("Reconnect Client:", dVar.u() + '/' + dVar.f());
            if (isOnline()) {
                dVar.D();
            }
        }
    }

    public final void i() {
        if (this.k == null) {
            d dVar = new d();
            this.k = dVar;
            registerReceiver(dVar, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        if (Build.VERSION.SDK_INT < 14) {
            this.m = ((ConnectivityManager) getSystemService("connectivity")).getBackgroundDataSetting();
            if (this.l == null) {
                b bVar = new b();
                this.l = bVar;
                registerReceiver(bVar, new IntentFilter("android.net.conn.BACKGROUND_DATA_SETTING_CHANGED"));
            }
        }
        if (this.r == null) {
            c cVar = new c();
            this.r = cVar;
            registerReceiver(cVar, new IntentFilter("ACTION_ALARM_KEEP_CONNECT"));
        }
    }

    public boolean isConnected(String str) {
        return e(str).A();
    }

    public boolean isOnline() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected() && this.m;
    }

    public boolean isTraceEnabled() {
        return this.i;
    }

    public final void j(String str, String str2, String str3) {
        if (this.h == null || !this.i) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.TRACE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_TRACE_SEVERITY, str);
        bundle.putString(MqttServiceConstants.CALLBACK_TRACE_TAG, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, str3);
        d(this.h, h.ERROR, bundle);
    }

    public final void k() {
        b bVar;
        d dVar = this.k;
        if (dVar != null) {
            unregisterReceiver(dVar);
            this.k = null;
        }
        if (Build.VERSION.SDK_INT < 14 && (bVar = this.l) != null) {
            unregisterReceiver(bVar);
        }
        c cVar = this.r;
        if (cVar != null) {
            unregisterReceiver(cVar);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        this.n.b(intent.getStringExtra(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN));
        return this.n;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.n = new f(this);
        this.j = new com.abupdate.mqtt_libs.mqtt_service.b(this, this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        for (com.abupdate.mqtt_libs.mqtt_service.d dVar : this.o.values()) {
            dVar.o(null, null);
        }
        if (this.n != null) {
            this.n = null;
        }
        k();
        com.abupdate.mqtt_libs.mqtt_service.c cVar = this.j;
        if (cVar != null) {
            cVar.a();
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        i();
        return 1;
    }

    public IMqttDeliveryToken publish(String str, String str2, byte[] bArr, int i, boolean z, String str3, String str4) throws k, MqttException {
        return e(str).d(str2, bArr, i, z, str3, str4);
    }

    public void setBufferOpts(String str, com.abupdate.mqtt_libs.mqttv3.b bVar) {
        e(str).k(bVar);
    }

    public void setTraceCallbackId(String str) {
        this.h = str;
    }

    public void setTraceEnabled(boolean z) {
        this.i = z;
    }

    public AlarmManager startKeepConnect(long j, long j2) {
        Trace.d("MqttService", "startKeepConnect() repeat time:" + ((((((float) j) + 0.0f) / 1000.0f) / 60.0f) / 60.0f) + "h,next time:" + f(j2));
        this.q = PendingIntent.getBroadcast(this, 0, new Intent("ACTION_ALARM_KEEP_CONNECT"), 0);
        this.p = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
        stopKeepConnect();
        this.p.setInexactRepeating(0, j2, j, this.q);
        return this.p;
    }

    public void stopKeepConnect() {
        PendingIntent pendingIntent;
        AlarmManager alarmManager = this.p;
        if (alarmManager == null || (pendingIntent = this.q) == null) {
            return;
        }
        alarmManager.cancel(pendingIntent);
    }

    public void subscribe(String str, String str2, int i, String str3, String str4) {
        e(str).m(str2, i, str3, str4);
    }

    @Override // com.abupdate.mqtt_libs.mqtt_service.MqttTraceHandler
    public void traceDebug(String str, String str2) {
        j("debug", str, str2);
    }

    @Override // com.abupdate.mqtt_libs.mqtt_service.MqttTraceHandler
    public void traceError(String str, String str2) {
        j("error", str, str2);
    }

    @Override // com.abupdate.mqtt_libs.mqtt_service.MqttTraceHandler
    public void traceException(String str, String str2, Exception exc) {
        if (this.h != null) {
            Bundle bundle = new Bundle();
            bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.TRACE_ACTION);
            bundle.putString(MqttServiceConstants.CALLBACK_TRACE_SEVERITY, MqttServiceConstants.TRACE_EXCEPTION);
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, str2);
            bundle.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, exc);
            bundle.putString(MqttServiceConstants.CALLBACK_TRACE_TAG, str);
            d(this.h, h.ERROR, bundle);
        }
    }

    public void unsubscribe(String str, String str2, String str3, String str4) {
        e(str).p(str2, str3, str4);
    }

    public IMqttDeliveryToken publish(String str, String str2, MqttMessage mqttMessage, String str3, String str4) throws k, MqttException {
        return e(str).c(str2, mqttMessage, str3, str4);
    }

    public void subscribe(String str, String[] strArr, int[] iArr, String str2, String str3) {
        e(str).s(strArr, iArr, str2, str3);
    }

    public void unsubscribe(String str, String[] strArr, String str2, String str3) {
        e(str).r(strArr, str2, str3);
    }

    public void disconnect(String str, long j, String str2, String str3) {
        e(str).g(j, str2, str3);
        this.o.remove(str);
        stopSelf();
    }

    public void subscribe(String str, String[] strArr, int[] iArr, String str2, String str3, com.abupdate.mqtt_libs.mqttv3.d[] dVarArr) {
        e(str).t(strArr, iArr, str2, str3, dVarArr);
    }
}
