package org.eclipse.paho.android.service;

import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.paho.android.service.MessageStore;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
/* loaded from: classes13.dex */
public class MqttConnection implements MqttCallbackExtended {

    /* renamed from: a  reason: collision with root package name */
    public String f15430a;
    public String b;
    public MqttClientPersistence c;
    public MqttConnectOptions d;
    public String e;
    public MqttService i;
    public String r;
    public String f = null;
    public MqttAsyncClient g = null;
    public AlarmPingSender h = null;
    public volatile boolean j = true;
    public boolean k = true;
    public volatile boolean l = false;
    public Map<IMqttDeliveryToken, String> m = new HashMap();
    public Map<IMqttDeliveryToken, MqttMessage> n = new HashMap();
    public Map<IMqttDeliveryToken, String> o = new HashMap();
    public Map<IMqttDeliveryToken, String> p = new HashMap();
    public PowerManager.WakeLock q = null;
    public DisconnectedBufferOptions s = null;

    /* loaded from: classes13.dex */
    public class MqttConnectionListener implements IMqttActionListener {

        /* renamed from: a  reason: collision with root package name */
        public final Bundle f15431a;

        public MqttConnectionListener(Bundle bundle) {
            this.f15431a = bundle;
        }

        @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
        public void onFailure(IMqttToken iMqttToken, Throwable th) {
            this.f15431a.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, th.getLocalizedMessage());
            this.f15431a.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, th);
            MqttConnection.this.i.callbackToActivity(MqttConnection.this.e, Status.ERROR, this.f15431a);
        }

        @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
        public void onSuccess(IMqttToken iMqttToken) {
            MqttConnection.this.i.callbackToActivity(MqttConnection.this.e, Status.OK, this.f15431a);
        }
    }

    public MqttConnection(MqttService mqttService, String str, String str2, MqttClientPersistence mqttClientPersistence, String str3) {
        this.c = null;
        this.i = null;
        this.r = null;
        this.f15430a = str;
        this.i = mqttService;
        this.b = str2;
        this.c = mqttClientPersistence;
        this.e = str3;
        this.r = getClass().getCanonicalName() + HexStringBuilder.DEFAULT_SEPARATOR + str2 + HexStringBuilder.DEFAULT_SEPARATOR + "on host " + str;
    }

    public final void a() {
        if (this.q == null) {
            this.q = ((PowerManager) this.i.getSystemService("power")).newWakeLock(1, this.r);
        }
        this.q.acquire();
    }

    public final void b() {
        Iterator<MessageStore.StoredMessage> allArrivedMessages = this.i.messageStore.getAllArrivedMessages(this.e);
        while (allArrivedMessages.hasNext()) {
            MessageStore.StoredMessage next = allArrivedMessages.next();
            Bundle f = f(next.getMessageId(), next.getTopic(), next.getMessage());
            f.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.MESSAGE_ARRIVED_ACTION);
            this.i.callbackToActivity(this.e, Status.OK, f);
        }
    }

    public final void c(Bundle bundle) {
        a();
        this.j = true;
        h(false);
        this.i.callbackToActivity(this.e, Status.ERROR, bundle);
        g();
    }

    public void close() {
        this.i.traceDebug("MqttConnection", "close()");
        try {
            MqttAsyncClient mqttAsyncClient = this.g;
            if (mqttAsyncClient != null) {
                mqttAsyncClient.close();
            }
        } catch (MqttException e) {
            e(new Bundle(), e);
        }
    }

    public void connect(MqttConnectOptions mqttConnectOptions, String str, String str2) {
        MqttAsyncClient mqttAsyncClient;
        this.d = mqttConnectOptions;
        this.f = str2;
        if (mqttConnectOptions != null) {
            this.k = mqttConnectOptions.isCleanSession();
        }
        if (this.d.isCleanSession()) {
            this.i.messageStore.clearArrivedMessages(this.e);
        }
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "Connecting {" + this.f15430a + "} as {" + this.b + "}");
        final Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.CONNECT_ACTION);
        try {
            if (this.c == null) {
                File externalFilesDir = this.i.getExternalFilesDir("MqttConnection");
                if (externalFilesDir == null && (externalFilesDir = this.i.getDir("MqttConnection", 0)) == null) {
                    bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "Error! No external and internal storage available");
                    bundle.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, new MqttPersistenceException());
                    this.i.callbackToActivity(this.e, Status.ERROR, bundle);
                    return;
                }
                this.c = new MqttDefaultFilePersistence(externalFilesDir.getAbsolutePath());
            }
            MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle) { // from class: org.eclipse.paho.android.service.MqttConnection.1
                @Override // org.eclipse.paho.android.service.MqttConnection.MqttConnectionListener, org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, th.getLocalizedMessage());
                    bundle.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, th);
                    MqttService mqttService2 = MqttConnection.this.i;
                    mqttService2.traceError("MqttConnection", "connect fail, call connect to reconnect.reason:" + th.getMessage());
                    MqttConnection.this.c(bundle);
                }

                @Override // org.eclipse.paho.android.service.MqttConnection.MqttConnectionListener, org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onSuccess(IMqttToken iMqttToken) {
                    MqttConnection.this.d(bundle);
                    MqttConnection.this.i.traceDebug("MqttConnection", "connect success!");
                }
            };
            if (this.g == null) {
                AlarmPingSender alarmPingSender = new AlarmPingSender(this.i);
                this.h = alarmPingSender;
                MqttAsyncClient mqttAsyncClient2 = new MqttAsyncClient(this.f15430a, this.b, this.c, alarmPingSender);
                this.g = mqttAsyncClient2;
                mqttAsyncClient2.setCallback(this);
                this.i.traceDebug("MqttConnection", "Do Real connect!");
                h(true);
                mqttAsyncClient = this.g;
            } else if (this.l) {
                this.i.traceDebug("MqttConnection", "myClient != null and the client is connecting. Connect return directly.");
                MqttService mqttService2 = this.i;
                mqttService2.traceDebug("MqttConnection", "Connect return:isConnecting:" + this.l + ".disconnected:" + this.j);
                return;
            } else if (!this.j) {
                this.i.traceDebug("MqttConnection", "myClient != null and the client is connected and notify!");
                d(bundle);
                return;
            } else {
                this.i.traceDebug("MqttConnection", "myClient != null and the client is not connected");
                this.i.traceDebug("MqttConnection", "Do Real connect!");
                h(true);
                mqttAsyncClient = this.g;
            }
            mqttAsyncClient.connect(this.d, str, mqttConnectionListener);
        } catch (Exception e) {
            MqttService mqttService3 = this.i;
            mqttService3.traceError("MqttConnection", "Exception occurred attempting to connect: " + e.getMessage());
            h(false);
            e(bundle, e);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttCallbackExtended
    public void connectComplete(boolean z, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.CONNECT_EXTENDED_ACTION);
        bundle.putBoolean(MqttServiceConstants.CALLBACK_RECONNECT, z);
        bundle.putString(MqttServiceConstants.CALLBACK_SERVER_URI, str);
        this.i.callbackToActivity(this.e, Status.OK, bundle);
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttCallback
    public void connectionLost(Throwable th) {
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "connectionLost(" + th.getMessage() + ")");
        this.j = true;
        try {
            if (this.d.isAutomaticReconnect()) {
                this.h.schedule(100L);
            } else {
                this.g.disconnect(null, new IMqttActionListener() { // from class: org.eclipse.paho.android.service.MqttConnection.2
                    @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                    public void onFailure(IMqttToken iMqttToken, Throwable th2) {
                    }

                    @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                    public void onSuccess(IMqttToken iMqttToken) {
                    }
                });
            }
        } catch (Exception unused) {
        }
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.ON_CONNECTION_LOST_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, th.getMessage());
        if (th instanceof MqttException) {
            bundle.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, th);
        }
        bundle.putString(MqttServiceConstants.CALLBACK_EXCEPTION_STACK, Log.getStackTraceString(th));
        this.i.callbackToActivity(this.e, Status.OK, bundle);
        g();
    }

    public final void d(Bundle bundle) {
        a();
        this.i.callbackToActivity(this.e, Status.OK, bundle);
        b();
        h(false);
        this.j = false;
        g();
    }

    public void deleteBufferedMessage(int i) {
        this.g.deleteBufferedMessage(i);
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttCallback
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "deliveryComplete(" + iMqttDeliveryToken + ")");
        MqttMessage remove = this.n.remove(iMqttDeliveryToken);
        if (remove != null) {
            String remove2 = this.o.remove(iMqttDeliveryToken);
            String remove3 = this.p.remove(iMqttDeliveryToken);
            Bundle f = f(null, this.m.remove(iMqttDeliveryToken), remove);
            if (remove2 != null) {
                f.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SEND_ACTION);
                f.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, remove2);
                f.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, remove3);
                this.i.callbackToActivity(this.e, Status.OK, f);
            }
            f.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.MESSAGE_DELIVERED_ACTION);
            this.i.callbackToActivity(this.e, Status.OK, f);
        }
    }

    public void disconnect(long j, String str, String str2) {
        this.i.traceDebug("MqttConnection", "disconnect()");
        this.j = true;
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.DISCONNECT_ACTION);
        MqttAsyncClient mqttAsyncClient = this.g;
        if (mqttAsyncClient == null || !mqttAsyncClient.isConnected()) {
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
            this.i.traceError(MqttServiceConstants.DISCONNECT_ACTION, "not connected");
            this.i.callbackToActivity(this.e, Status.ERROR, bundle);
        } else {
            try {
                this.g.disconnect(j, str, new MqttConnectionListener(bundle));
            } catch (Exception e) {
                e(bundle, e);
            }
        }
        MqttConnectOptions mqttConnectOptions = this.d;
        if (mqttConnectOptions != null && mqttConnectOptions.isCleanSession()) {
            this.i.messageStore.clearArrivedMessages(this.e);
        }
        g();
    }

    public void disconnect(String str, String str2) {
        this.i.traceDebug("MqttConnection", "disconnect()");
        this.j = true;
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.DISCONNECT_ACTION);
        MqttAsyncClient mqttAsyncClient = this.g;
        if (mqttAsyncClient == null || !mqttAsyncClient.isConnected()) {
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
            this.i.traceError(MqttServiceConstants.DISCONNECT_ACTION, "not connected");
            this.i.callbackToActivity(this.e, Status.ERROR, bundle);
        } else {
            try {
                this.g.disconnect(str, new MqttConnectionListener(bundle));
            } catch (Exception e) {
                e(bundle, e);
            }
        }
        MqttConnectOptions mqttConnectOptions = this.d;
        if (mqttConnectOptions != null && mqttConnectOptions.isCleanSession()) {
            this.i.messageStore.clearArrivedMessages(this.e);
        }
        g();
    }

    public final void e(Bundle bundle, Exception exc) {
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, exc.getLocalizedMessage());
        bundle.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, exc);
        this.i.callbackToActivity(this.e, Status.ERROR, bundle);
    }

    public final Bundle f(String str, String str2, MqttMessage mqttMessage) {
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_MESSAGE_ID, str);
        bundle.putString(MqttServiceConstants.CALLBACK_DESTINATION_NAME, str2);
        bundle.putParcelable(MqttServiceConstants.CALLBACK_MESSAGE_PARCEL, new ParcelableMqttMessage(mqttMessage));
        return bundle;
    }

    public final void g() {
        PowerManager.WakeLock wakeLock = this.q;
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        this.q.release();
    }

    public MqttMessage getBufferedMessage(int i) {
        return this.g.getBufferedMessage(i);
    }

    public int getBufferedMessageCount() {
        return this.g.getBufferedMessageCount();
    }

    public String getClientHandle() {
        return this.e;
    }

    public String getClientId() {
        return this.b;
    }

    public MqttConnectOptions getConnectOptions() {
        return this.d;
    }

    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.g.getPendingDeliveryTokens();
    }

    public String getServerURI() {
        return this.f15430a;
    }

    public final synchronized void h(boolean z) {
        this.l = z;
    }

    public final void i(String str, MqttMessage mqttMessage, IMqttDeliveryToken iMqttDeliveryToken, String str2, String str3) {
        this.m.put(iMqttDeliveryToken, str);
        this.n.put(iMqttDeliveryToken, mqttMessage);
        this.o.put(iMqttDeliveryToken, str3);
        this.p.put(iMqttDeliveryToken, str2);
    }

    public boolean isConnected() {
        MqttAsyncClient mqttAsyncClient = this.g;
        return mqttAsyncClient != null && mqttAsyncClient.isConnected();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttCallback
    public void messageArrived(String str, MqttMessage mqttMessage) {
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "messageArrived(" + str + ",{" + mqttMessage.toString() + "})");
        String storeArrived = this.i.messageStore.storeArrived(this.e, str, mqttMessage);
        Bundle f = f(storeArrived, str, mqttMessage);
        f.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.MESSAGE_ARRIVED_ACTION);
        f.putString(MqttServiceConstants.CALLBACK_MESSAGE_ID, storeArrived);
        this.i.callbackToActivity(this.e, Status.OK, f);
    }

    public void offline() {
        if (this.j || this.k) {
            return;
        }
        connectionLost(new Exception("Android offline"));
    }

    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage, String str2, String str3) {
        DisconnectedBufferOptions disconnectedBufferOptions;
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SEND_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str3);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str2);
        MqttAsyncClient mqttAsyncClient = this.g;
        IMqttDeliveryToken iMqttDeliveryToken = null;
        try {
        } catch (Exception e) {
            e(bundle, e);
        }
        if (mqttAsyncClient != null && mqttAsyncClient.isConnected()) {
            iMqttDeliveryToken = this.g.publish(str, mqttMessage, str2, new MqttConnectionListener(bundle));
        } else if (this.g == null || (disconnectedBufferOptions = this.s) == null || !disconnectedBufferOptions.isBufferEnabled()) {
            Log.i("MqttConnection", "Client is not connected, so not sending message");
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
            this.i.traceError(MqttServiceConstants.SEND_ACTION, "not connected");
            this.i.callbackToActivity(this.e, Status.ERROR, bundle);
            return iMqttDeliveryToken;
        } else {
            iMqttDeliveryToken = this.g.publish(str, mqttMessage, str2, new MqttConnectionListener(bundle));
        }
        i(str, mqttMessage, iMqttDeliveryToken, str2, str3);
        return iMqttDeliveryToken;
    }

    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z, String str2, String str3) {
        MqttMessage mqttMessage;
        IMqttDeliveryToken publish;
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SEND_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str3);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str2);
        MqttAsyncClient mqttAsyncClient = this.g;
        IMqttDeliveryToken iMqttDeliveryToken = null;
        if (mqttAsyncClient == null || !mqttAsyncClient.isConnected()) {
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
            this.i.traceError(MqttServiceConstants.SEND_ACTION, "not connected");
            this.i.callbackToActivity(this.e, Status.ERROR, bundle);
            return null;
        }
        MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
        try {
            mqttMessage = new MqttMessage(bArr);
            mqttMessage.setQos(i);
            mqttMessage.setRetained(z);
            publish = this.g.publish(str, bArr, i, z, str2, mqttConnectionListener);
        } catch (Exception e) {
            e = e;
        }
        try {
            i(str, mqttMessage, publish, str2, str3);
            return publish;
        } catch (Exception e2) {
            e = e2;
            iMqttDeliveryToken = publish;
            e(bundle, e);
            return iMqttDeliveryToken;
        }
    }

    public synchronized void reconnect() {
        final Bundle bundle;
        if (this.g == null) {
            this.i.traceError("MqttConnection", "Reconnect myClient = null. Will not do reconnect");
        } else if (this.l) {
            this.i.traceDebug("MqttConnection", "The client is connecting. Reconnect return directly.");
        } else if (!this.i.isOnline()) {
            this.i.traceDebug("MqttConnection", "The network is not reachable. Will not do reconnect");
        } else {
            if (this.d.isAutomaticReconnect()) {
                Log.i("MqttConnection", "Requesting Automatic reconnect using New Java AC");
                bundle = new Bundle();
                bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, this.f);
                bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, null);
                bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.CONNECT_ACTION);
                try {
                    this.g.reconnect();
                } catch (MqttException e) {
                    e = e;
                    Log.e("MqttConnection", "Exception occurred attempting to reconnect: " + e.getMessage());
                    h(false);
                    e(bundle, e);
                    return;
                }
                return;
            }
            if (this.j && !this.k) {
                this.i.traceDebug("MqttConnection", "Do Real Reconnect!");
                bundle = new Bundle();
                bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, this.f);
                bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, null);
                bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.CONNECT_ACTION);
                try {
                    this.g.connect(this.d, null, new MqttConnectionListener(bundle) { // from class: org.eclipse.paho.android.service.MqttConnection.3
                        @Override // org.eclipse.paho.android.service.MqttConnection.MqttConnectionListener, org.eclipse.paho.client.mqttv3.IMqttActionListener
                        public void onFailure(IMqttToken iMqttToken, Throwable th) {
                            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, th.getLocalizedMessage());
                            bundle.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, th);
                            MqttConnection.this.i.callbackToActivity(MqttConnection.this.e, Status.ERROR, bundle);
                            MqttConnection.this.c(bundle);
                        }

                        @Override // org.eclipse.paho.android.service.MqttConnection.MqttConnectionListener, org.eclipse.paho.client.mqttv3.IMqttActionListener
                        public void onSuccess(IMqttToken iMqttToken) {
                            MqttConnection.this.i.traceDebug("MqttConnection", "Reconnect Success!");
                            MqttConnection.this.i.traceDebug("MqttConnection", "DeliverBacklog when reconnect.");
                            MqttConnection.this.d(bundle);
                        }
                    });
                    h(true);
                } catch (MqttException e2) {
                    e = e2;
                    MqttService mqttService = this.i;
                    mqttService.traceError("MqttConnection", "Cannot reconnect to remote server." + e.getMessage());
                    h(false);
                    e(bundle, e);
                } catch (Exception e3) {
                    MqttService mqttService2 = this.i;
                    mqttService2.traceError("MqttConnection", "Cannot reconnect to remote server." + e3.getMessage());
                    h(false);
                    e(bundle, new MqttException(6, e3.getCause()));
                }
            }
            return;
        }
    }

    public void setBufferOpts(DisconnectedBufferOptions disconnectedBufferOptions) {
        this.s = disconnectedBufferOptions;
        this.g.setBufferOpts(disconnectedBufferOptions);
    }

    public void setClientHandle(String str) {
        this.e = str;
    }

    public void setClientId(String str) {
        this.b = str;
    }

    public void setConnectOptions(MqttConnectOptions mqttConnectOptions) {
        this.d = mqttConnectOptions;
    }

    public void setServerURI(String str) {
        this.f15430a = str;
    }

    public void subscribe(String str, int i, String str2, String str3) {
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "subscribe({" + str + "}," + i + ",{" + str2 + "}, {" + str3 + "}");
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SUBSCRIBE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str3);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str2);
        MqttAsyncClient mqttAsyncClient = this.g;
        if (mqttAsyncClient == null || !mqttAsyncClient.isConnected()) {
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
            this.i.traceError(MqttServiceConstants.SUBSCRIBE_ACTION, "not connected");
            this.i.callbackToActivity(this.e, Status.ERROR, bundle);
            return;
        }
        try {
            this.g.subscribe(str, i, str2, new MqttConnectionListener(bundle));
        } catch (Exception e) {
            e(bundle, e);
        }
    }

    public void subscribe(String[] strArr, int[] iArr, String str, String str2) {
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "subscribe({" + Arrays.toString(strArr) + "}," + Arrays.toString(iArr) + ",{" + str + "}, {" + str2 + "}");
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SUBSCRIBE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        MqttAsyncClient mqttAsyncClient = this.g;
        if (mqttAsyncClient == null || !mqttAsyncClient.isConnected()) {
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
            this.i.traceError(MqttServiceConstants.SUBSCRIBE_ACTION, "not connected");
            this.i.callbackToActivity(this.e, Status.ERROR, bundle);
            return;
        }
        try {
            this.g.subscribe(strArr, iArr, str, new MqttConnectionListener(bundle));
        } catch (Exception e) {
            e(bundle, e);
        }
    }

    public void subscribe(String[] strArr, int[] iArr, String str, String str2, IMqttMessageListener[] iMqttMessageListenerArr) {
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "subscribe({" + Arrays.toString(strArr) + "}," + Arrays.toString(iArr) + ",{" + str + "}, {" + str2 + "}");
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SUBSCRIBE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        MqttAsyncClient mqttAsyncClient = this.g;
        if (mqttAsyncClient == null || !mqttAsyncClient.isConnected()) {
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
            this.i.traceError(MqttServiceConstants.SUBSCRIBE_ACTION, "not connected");
            this.i.callbackToActivity(this.e, Status.ERROR, bundle);
            return;
        }
        new MqttConnectionListener(bundle);
        try {
            this.g.subscribe(strArr, iArr, iMqttMessageListenerArr);
        } catch (Exception e) {
            e(bundle, e);
        }
    }

    public void unsubscribe(String str, String str2, String str3) {
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "unsubscribe({" + str + "},{" + str2 + "}, {" + str3 + "})");
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.UNSUBSCRIBE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str3);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str2);
        MqttAsyncClient mqttAsyncClient = this.g;
        if (mqttAsyncClient == null || !mqttAsyncClient.isConnected()) {
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
            this.i.traceError(MqttServiceConstants.SUBSCRIBE_ACTION, "not connected");
            this.i.callbackToActivity(this.e, Status.ERROR, bundle);
            return;
        }
        try {
            this.g.unsubscribe(str, str2, new MqttConnectionListener(bundle));
        } catch (Exception e) {
            e(bundle, e);
        }
    }

    public void unsubscribe(String[] strArr, String str, String str2) {
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "unsubscribe({" + Arrays.toString(strArr) + "},{" + str + "}, {" + str2 + "})");
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.UNSUBSCRIBE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        MqttAsyncClient mqttAsyncClient = this.g;
        if (mqttAsyncClient == null || !mqttAsyncClient.isConnected()) {
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
            this.i.traceError(MqttServiceConstants.SUBSCRIBE_ACTION, "not connected");
            this.i.callbackToActivity(this.e, Status.ERROR, bundle);
            return;
        }
        try {
            this.g.unsubscribe(strArr, str, new MqttConnectionListener(bundle));
        } catch (Exception e) {
            e(bundle, e);
        }
    }
}
