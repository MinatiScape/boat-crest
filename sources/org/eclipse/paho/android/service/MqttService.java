package org.eclipse.paho.android.service;

import android.annotation.SuppressLint;
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
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
@SuppressLint({"Registered"})
/* loaded from: classes13.dex */
public class MqttService extends Service implements MqttTraceHandler {
    public static final String TAG = "MqttService";
    public String h;
    public NetworkConnectionIntentReceiver j;
    public BackgroundDataPreferenceReceiver k;
    public MqttServiceBinder m;
    public MessageStore messageStore;
    public boolean i = false;
    public volatile boolean l = true;
    public Map<String, MqttConnection> n = new ConcurrentHashMap();

    /* loaded from: classes13.dex */
    public class BackgroundDataPreferenceReceiver extends BroadcastReceiver {
        public BackgroundDataPreferenceReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MqttService.this.traceDebug("MqttService", "Reconnect since BroadcastReceiver.");
            if (!((ConnectivityManager) MqttService.this.getSystemService("connectivity")).getBackgroundDataSetting()) {
                MqttService.this.l = false;
                MqttService.this.b();
            } else if (MqttService.this.l) {
            } else {
                MqttService.this.l = true;
                MqttService.this.reconnect();
            }
        }
    }

    /* loaded from: classes13.dex */
    public class NetworkConnectionIntentReceiver extends BroadcastReceiver {
        public NetworkConnectionIntentReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        @SuppressLint({"Wakelock"})
        public void onReceive(Context context, Intent intent) {
            MqttService.this.traceDebug("MqttService", "Internal network status receive.");
            PowerManager.WakeLock newWakeLock = ((PowerManager) MqttService.this.getSystemService("power")).newWakeLock(1, "MQTT");
            newWakeLock.acquire();
            MqttService.this.traceDebug("MqttService", "Reconnect for Network recovery.");
            if (MqttService.this.isOnline()) {
                MqttService.this.traceDebug("MqttService", "Online,reconnect.");
                MqttService.this.reconnect();
            } else {
                MqttService.this.b();
            }
            newWakeLock.release();
        }
    }

    public final MqttConnection a(String str) {
        MqttConnection mqttConnection = this.n.get(str);
        if (mqttConnection != null) {
            return mqttConnection;
        }
        throw new IllegalArgumentException("Invalid ClientHandle");
    }

    public Status acknowledgeMessageArrival(String str, String str2) {
        return this.messageStore.discardArrived(str, str2) ? Status.OK : Status.ERROR;
    }

    public final void b() {
        for (MqttConnection mqttConnection : this.n.values()) {
            mqttConnection.offline();
        }
    }

    public final void c() {
        if (this.j == null) {
            NetworkConnectionIntentReceiver networkConnectionIntentReceiver = new NetworkConnectionIntentReceiver();
            this.j = networkConnectionIntentReceiver;
            registerReceiver(networkConnectionIntentReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        if (Build.VERSION.SDK_INT < 14) {
            this.l = ((ConnectivityManager) getSystemService("connectivity")).getBackgroundDataSetting();
            if (this.k == null) {
                BackgroundDataPreferenceReceiver backgroundDataPreferenceReceiver = new BackgroundDataPreferenceReceiver();
                this.k = backgroundDataPreferenceReceiver;
                registerReceiver(backgroundDataPreferenceReceiver, new IntentFilter("android.net.conn.BACKGROUND_DATA_SETTING_CHANGED"));
            }
        }
    }

    public void callbackToActivity(String str, Status status, Bundle bundle) {
        Intent intent = new Intent(MqttServiceConstants.CALLBACK_TO_ACTIVITY);
        if (str != null) {
            intent.putExtra(MqttServiceConstants.CALLBACK_CLIENT_HANDLE, str);
        }
        intent.putExtra(MqttServiceConstants.CALLBACK_STATUS, status);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void close(String str) {
        a(str).close();
    }

    public void connect(String str, MqttConnectOptions mqttConnectOptions, String str2, String str3) {
        a(str).connect(mqttConnectOptions, null, str3);
    }

    public final void d(String str, String str2, String str3) {
        if (this.h == null || !this.i) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.TRACE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_TRACE_SEVERITY, str);
        bundle.putString(MqttServiceConstants.CALLBACK_TRACE_TAG, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, str3);
        callbackToActivity(this.h, Status.ERROR, bundle);
    }

    public void deleteBufferedMessage(String str, int i) {
        a(str).deleteBufferedMessage(i);
    }

    public void disconnect(String str, long j, String str2, String str3) {
        a(str).disconnect(j, str2, str3);
        this.n.remove(str);
        stopSelf();
    }

    public void disconnect(String str, String str2, String str3) {
        a(str).disconnect(str2, str3);
        this.n.remove(str);
        stopSelf();
    }

    public final void e() {
        BackgroundDataPreferenceReceiver backgroundDataPreferenceReceiver;
        NetworkConnectionIntentReceiver networkConnectionIntentReceiver = this.j;
        if (networkConnectionIntentReceiver != null) {
            unregisterReceiver(networkConnectionIntentReceiver);
            this.j = null;
        }
        if (Build.VERSION.SDK_INT >= 14 || (backgroundDataPreferenceReceiver = this.k) == null) {
            return;
        }
        unregisterReceiver(backgroundDataPreferenceReceiver);
    }

    public MqttMessage getBufferedMessage(String str, int i) {
        return a(str).getBufferedMessage(i);
    }

    public int getBufferedMessageCount(String str) {
        return a(str).getBufferedMessageCount();
    }

    public String getClient(String str, String str2, String str3, MqttClientPersistence mqttClientPersistence) {
        String str4 = str + ":" + str2 + ":" + str3;
        if (!this.n.containsKey(str4)) {
            this.n.put(str4, new MqttConnection(this, str, str2, mqttClientPersistence, str4));
        }
        return str4;
    }

    public IMqttDeliveryToken[] getPendingDeliveryTokens(String str) {
        return a(str).getPendingDeliveryTokens();
    }

    public boolean isConnected(String str) {
        return a(str).isConnected();
    }

    public boolean isOnline() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected() && this.l;
    }

    public boolean isTraceEnabled() {
        return this.i;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        this.m.setActivityToken(intent.getStringExtra(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN));
        return this.m;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.m = new MqttServiceBinder(this);
        this.messageStore = new DatabaseMessageStore(this, this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        for (MqttConnection mqttConnection : this.n.values()) {
            mqttConnection.disconnect(null, null);
        }
        if (this.m != null) {
            this.m = null;
        }
        e();
        MessageStore messageStore = this.messageStore;
        if (messageStore != null) {
            messageStore.close();
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        c();
        return 1;
    }

    public IMqttDeliveryToken publish(String str, String str2, MqttMessage mqttMessage, String str3, String str4) {
        return a(str).publish(str2, mqttMessage, str3, str4);
    }

    public IMqttDeliveryToken publish(String str, String str2, byte[] bArr, int i, boolean z, String str3, String str4) {
        return a(str).publish(str2, bArr, i, z, str3, str4);
    }

    public void reconnect() {
        traceDebug("MqttService", "Reconnect to server, client size=" + this.n.size());
        for (MqttConnection mqttConnection : this.n.values()) {
            traceDebug("Reconnect Client:", mqttConnection.getClientId() + '/' + mqttConnection.getServerURI());
            if (isOnline()) {
                mqttConnection.reconnect();
            }
        }
    }

    public void setBufferOpts(String str, DisconnectedBufferOptions disconnectedBufferOptions) {
        a(str).setBufferOpts(disconnectedBufferOptions);
    }

    public void setTraceCallbackId(String str) {
        this.h = str;
    }

    public void setTraceEnabled(boolean z) {
        this.i = z;
    }

    public void subscribe(String str, String str2, int i, String str3, String str4) {
        a(str).subscribe(str2, i, str3, str4);
    }

    public void subscribe(String str, String[] strArr, int[] iArr, String str2, String str3) {
        a(str).subscribe(strArr, iArr, str2, str3);
    }

    public void subscribe(String str, String[] strArr, int[] iArr, String str2, String str3, IMqttMessageListener[] iMqttMessageListenerArr) {
        a(str).subscribe(strArr, iArr, str2, str3, iMqttMessageListenerArr);
    }

    @Override // org.eclipse.paho.android.service.MqttTraceHandler
    public void traceDebug(String str, String str2) {
        d("debug", str, str2);
    }

    @Override // org.eclipse.paho.android.service.MqttTraceHandler
    public void traceError(String str, String str2) {
        d("error", str, str2);
    }

    @Override // org.eclipse.paho.android.service.MqttTraceHandler
    public void traceException(String str, String str2, Exception exc) {
        if (this.h != null) {
            Bundle bundle = new Bundle();
            bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.TRACE_ACTION);
            bundle.putString(MqttServiceConstants.CALLBACK_TRACE_SEVERITY, MqttServiceConstants.TRACE_EXCEPTION);
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, str2);
            bundle.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, exc);
            bundle.putString(MqttServiceConstants.CALLBACK_TRACE_TAG, str);
            callbackToActivity(this.h, Status.ERROR, bundle);
        }
    }

    public void unsubscribe(String str, String str2, String str3, String str4) {
        a(str).unsubscribe(str2, str3, str4);
    }

    public void unsubscribe(String str, String[] strArr, String str2, String str3) {
        a(str).unsubscribe(strArr, str2, str3);
    }
}
