package org.eclipse.paho.android.service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.SparseArray;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
/* loaded from: classes13.dex */
public class MqttAndroidClient extends BroadcastReceiver implements IMqttAsyncClient {
    public static final ExecutorService y = Executors.newCachedThreadPool();
    public final MyServiceConnection h;
    public MqttService i;
    public String j;
    public Context k;
    public final SparseArray<IMqttToken> l;
    public int m;
    public final String n;
    public final String o;
    public MqttClientPersistence p;
    public MqttConnectOptions q;
    public IMqttToken r;
    public MqttCallback s;
    public MqttTraceHandler t;
    public final Ack u;
    public boolean v;
    public volatile boolean w;
    public volatile boolean x;

    /* loaded from: classes13.dex */
    public enum Ack {
        AUTO_ACK,
        MANUAL_ACK
    }

    /* loaded from: classes13.dex */
    public final class MyServiceConnection implements ServiceConnection {
        public MyServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MqttAndroidClient.this.i = ((MqttServiceBinder) iBinder).getService();
            MqttAndroidClient.this.x = true;
            MqttAndroidClient.this.e();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            MqttAndroidClient.this.i = null;
        }
    }

    public MqttAndroidClient(Context context, String str, String str2) {
        this(context, str, str2, null, Ack.AUTO_ACK);
    }

    public MqttAndroidClient(Context context, String str, String str2, Ack ack) {
        this(context, str, str2, null, ack);
    }

    public MqttAndroidClient(Context context, String str, String str2, MqttClientPersistence mqttClientPersistence) {
        this(context, str, str2, mqttClientPersistence, Ack.AUTO_ACK);
    }

    public MqttAndroidClient(Context context, String str, String str2, MqttClientPersistence mqttClientPersistence, Ack ack) {
        this.h = new MyServiceConnection();
        this.l = new SparseArray<>();
        this.m = 0;
        this.p = null;
        this.v = false;
        this.w = false;
        this.x = false;
        this.k = context;
        this.n = str;
        this.o = str2;
        this.p = mqttClientPersistence;
        this.u = ack;
    }

    public final void a(Bundle bundle) {
        IMqttToken iMqttToken = this.r;
        j(bundle);
        l(iMqttToken, bundle);
    }

    public boolean acknowledgeMessage(String str) {
        return this.u == Ack.MANUAL_ACK && this.i.acknowledgeMessageArrival(this.j, str) == Status.OK;
    }

    public final void b(Bundle bundle) {
        if (this.s instanceof MqttCallbackExtended) {
            ((MqttCallbackExtended) this.s).connectComplete(bundle.getBoolean(MqttServiceConstants.CALLBACK_RECONNECT, false), bundle.getString(MqttServiceConstants.CALLBACK_SERVER_URI));
        }
    }

    public final void c(Bundle bundle) {
        if (this.s != null) {
            this.s.connectionLost((Exception) bundle.getSerializable(MqttServiceConstants.CALLBACK_EXCEPTION));
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient, java.lang.AutoCloseable
    public void close() {
        MqttService mqttService = this.i;
        if (mqttService != null) {
            if (this.j == null) {
                this.j = mqttService.getClient(this.n, this.o, this.k.getApplicationInfo().packageName, this.p);
            }
            this.i.close(this.j);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect() {
        return connect(null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(Object obj, IMqttActionListener iMqttActionListener) {
        return connect(new MqttConnectOptions(), obj, iMqttActionListener);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(MqttConnectOptions mqttConnectOptions) {
        return connect(mqttConnectOptions, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(MqttConnectOptions mqttConnectOptions, Object obj, IMqttActionListener iMqttActionListener) {
        IMqttActionListener actionCallback;
        IMqttToken mqttTokenAndroid = new MqttTokenAndroid(this, obj, iMqttActionListener);
        this.q = mqttConnectOptions;
        this.r = mqttTokenAndroid;
        if (this.i == null) {
            Intent intent = new Intent();
            intent.setClassName(this.k, "org.eclipse.paho.android.service.MqttService");
            if (this.k.startService(intent) == null && (actionCallback = mqttTokenAndroid.getActionCallback()) != null) {
                actionCallback.onFailure(mqttTokenAndroid, new RuntimeException("cannot start service org.eclipse.paho.android.service.MqttService"));
            }
            this.k.bindService(intent, this.h, 1);
            if (!this.w) {
                i(this);
            }
        } else {
            y.execute(new Runnable() { // from class: org.eclipse.paho.android.service.MqttAndroidClient.1
                @Override // java.lang.Runnable
                public void run() {
                    MqttAndroidClient.this.e();
                    if (MqttAndroidClient.this.w) {
                        return;
                    }
                    MqttAndroidClient mqttAndroidClient = MqttAndroidClient.this;
                    mqttAndroidClient.i(mqttAndroidClient);
                }
            });
        }
        return mqttTokenAndroid;
    }

    public final void d(Bundle bundle) {
        this.j = null;
        IMqttToken j = j(bundle);
        if (j != null) {
            ((MqttTokenAndroid) j).notifyComplete();
        }
        MqttCallback mqttCallback = this.s;
        if (mqttCallback != null) {
            mqttCallback.connectionLost(null);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void deleteBufferedMessage(int i) {
        this.i.deleteBufferedMessage(this.j, i);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect() {
        MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, null, null);
        this.i.disconnect(this.j, null, m(mqttTokenAndroid));
        return mqttTokenAndroid;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(long j) {
        MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, null, null);
        this.i.disconnect(this.j, j, null, m(mqttTokenAndroid));
        return mqttTokenAndroid;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(long j, Object obj, IMqttActionListener iMqttActionListener) {
        MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, obj, iMqttActionListener);
        this.i.disconnect(this.j, j, null, m(mqttTokenAndroid));
        return mqttTokenAndroid;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(Object obj, IMqttActionListener iMqttActionListener) {
        MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, obj, iMqttActionListener);
        this.i.disconnect(this.j, null, m(mqttTokenAndroid));
        return mqttTokenAndroid;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly() {
        throw new UnsupportedOperationException();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly(long j) {
        throw new UnsupportedOperationException();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly(long j, long j2) {
        throw new UnsupportedOperationException();
    }

    public final void e() {
        if (this.j == null) {
            this.j = this.i.getClient(this.n, this.o, this.k.getApplicationInfo().packageName, this.p);
        }
        this.i.setTraceEnabled(this.v);
        this.i.setTraceCallbackId(this.j);
        try {
            this.i.connect(this.j, this.q, null, m(this.r));
        } catch (MqttException e) {
            IMqttActionListener actionCallback = this.r.getActionCallback();
            if (actionCallback != null) {
                actionCallback.onFailure(this.r, e);
            }
        }
    }

    public final synchronized IMqttToken f(Bundle bundle) {
        return this.l.get(Integer.parseInt(bundle.getString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN)));
    }

    public final void g(Bundle bundle) {
        if (this.s != null) {
            String string = bundle.getString(MqttServiceConstants.CALLBACK_MESSAGE_ID);
            String string2 = bundle.getString(MqttServiceConstants.CALLBACK_DESTINATION_NAME);
            ParcelableMqttMessage parcelableMqttMessage = (ParcelableMqttMessage) bundle.getParcelable(MqttServiceConstants.CALLBACK_MESSAGE_PARCEL);
            try {
                if (this.u == Ack.AUTO_ACK) {
                    this.s.messageArrived(string2, parcelableMqttMessage);
                    this.i.acknowledgeMessageArrival(this.j, string);
                } else {
                    parcelableMqttMessage.messageId = string;
                    this.s.messageArrived(string2, parcelableMqttMessage);
                }
            } catch (Exception unused) {
            }
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public MqttMessage getBufferedMessage(int i) {
        return this.i.getBufferedMessage(this.j, i);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public int getBufferedMessageCount() {
        return this.i.getBufferedMessageCount(this.j);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public String getClientId() {
        return this.o;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public int getInFlightMessageCount() {
        return 0;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.i.getPendingDeliveryTokens(this.j);
    }

    public SSLSocketFactory getSSLSocketFactory(InputStream inputStream, String str) {
        try {
            KeyStore keyStore = KeyStore.getInstance("BKS");
            keyStore.load(inputStream, str.toCharArray());
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
            trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            SSLContext sSLContext = SSLContext.getInstance("TLSv1");
            sSLContext.init(null, trustManagers, null);
            return sSLContext.getSocketFactory();
        } catch (IOException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            throw new MqttSecurityException(e);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public String getServerURI() {
        return this.n;
    }

    public final void h(Bundle bundle) {
        IMqttToken j = j(bundle);
        if (j == null || this.s == null || ((Status) bundle.getSerializable(MqttServiceConstants.CALLBACK_STATUS)) != Status.OK || !(j instanceof IMqttDeliveryToken)) {
            return;
        }
        this.s.deliveryComplete((IMqttDeliveryToken) j);
    }

    public final void i(BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MqttServiceConstants.CALLBACK_TO_ACTIVITY);
        LocalBroadcastManager.getInstance(this.k).registerReceiver(broadcastReceiver, intentFilter);
        this.w = true;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public boolean isConnected() {
        MqttService mqttService;
        String str = this.j;
        return (str == null || (mqttService = this.i) == null || !mqttService.isConnected(str)) ? false : true;
    }

    public final synchronized IMqttToken j(Bundle bundle) {
        String string = bundle.getString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN);
        if (string != null) {
            int parseInt = Integer.parseInt(string);
            IMqttToken iMqttToken = this.l.get(parseInt);
            this.l.delete(parseInt);
            return iMqttToken;
        }
        return null;
    }

    public final void k(Bundle bundle) {
        l(f(bundle), bundle);
    }

    public final void l(IMqttToken iMqttToken, Bundle bundle) {
        if (iMqttToken == null) {
            this.i.traceError("MqttService", "simpleAction : token is null");
        } else if (((Status) bundle.getSerializable(MqttServiceConstants.CALLBACK_STATUS)) == Status.OK) {
            ((MqttTokenAndroid) iMqttToken).notifyComplete();
        } else {
            ((MqttTokenAndroid) iMqttToken).notifyFailure((Exception) bundle.getSerializable(MqttServiceConstants.CALLBACK_EXCEPTION));
        }
    }

    public final synchronized String m(IMqttToken iMqttToken) {
        int i;
        this.l.put(this.m, iMqttToken);
        i = this.m;
        this.m = i + 1;
        return Integer.toString(i);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void messageArrivedComplete(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    public final void n(Bundle bundle) {
        l(j(bundle), bundle);
    }

    public final void o(Bundle bundle) {
        if (this.t != null) {
            String string = bundle.getString(MqttServiceConstants.CALLBACK_TRACE_SEVERITY);
            String string2 = bundle.getString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE);
            String string3 = bundle.getString(MqttServiceConstants.CALLBACK_TRACE_TAG);
            if ("debug".equals(string)) {
                this.t.traceDebug(string3, string2);
            } else if ("error".equals(string)) {
                this.t.traceError(string3, string2);
            } else {
                this.t.traceException(string3, string2, (Exception) bundle.getSerializable(MqttServiceConstants.CALLBACK_EXCEPTION));
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String string = extras.getString(MqttServiceConstants.CALLBACK_CLIENT_HANDLE);
        if (string == null || !string.equals(this.j)) {
            return;
        }
        String string2 = extras.getString(MqttServiceConstants.CALLBACK_ACTION);
        if (MqttServiceConstants.CONNECT_ACTION.equals(string2)) {
            a(extras);
        } else if (MqttServiceConstants.CONNECT_EXTENDED_ACTION.equals(string2)) {
            b(extras);
        } else if (MqttServiceConstants.MESSAGE_ARRIVED_ACTION.equals(string2)) {
            g(extras);
        } else if (MqttServiceConstants.SUBSCRIBE_ACTION.equals(string2)) {
            n(extras);
        } else if (MqttServiceConstants.UNSUBSCRIBE_ACTION.equals(string2)) {
            p(extras);
        } else if (MqttServiceConstants.SEND_ACTION.equals(string2)) {
            k(extras);
        } else if (MqttServiceConstants.MESSAGE_DELIVERED_ACTION.equals(string2)) {
            h(extras);
        } else if (MqttServiceConstants.ON_CONNECTION_LOST_ACTION.equals(string2)) {
            c(extras);
        } else if (MqttServiceConstants.DISCONNECT_ACTION.equals(string2)) {
            d(extras);
        } else if (MqttServiceConstants.TRACE_ACTION.equals(string2)) {
            o(extras);
        } else {
            this.i.traceError("MqttService", "Callback action doesn't exist.");
        }
    }

    public final void p(Bundle bundle) {
        l(j(bundle), bundle);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage) {
        return publish(str, mqttMessage, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage, Object obj, IMqttActionListener iMqttActionListener) {
        MqttDeliveryTokenAndroid mqttDeliveryTokenAndroid = new MqttDeliveryTokenAndroid(this, obj, iMqttActionListener, mqttMessage);
        mqttDeliveryTokenAndroid.setDelegate(this.i.publish(this.j, str, mqttMessage, null, m(mqttDeliveryTokenAndroid)));
        return mqttDeliveryTokenAndroid;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z) {
        return publish(str, bArr, i, z, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z, Object obj, IMqttActionListener iMqttActionListener) {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        MqttDeliveryTokenAndroid mqttDeliveryTokenAndroid = new MqttDeliveryTokenAndroid(this, obj, iMqttActionListener, mqttMessage);
        mqttDeliveryTokenAndroid.setDelegate(this.i.publish(this.j, str, bArr, i, z, null, m(mqttDeliveryTokenAndroid)));
        return mqttDeliveryTokenAndroid;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void reconnect() {
    }

    public void registerResources(Context context) {
        if (context != null) {
            this.k = context;
            if (this.w) {
                return;
            }
            i(this);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public boolean removeMessage(IMqttDeliveryToken iMqttDeliveryToken) {
        return false;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void setBufferOpts(DisconnectedBufferOptions disconnectedBufferOptions) {
        this.i.setBufferOpts(this.j, disconnectedBufferOptions);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void setCallback(MqttCallback mqttCallback) {
        this.s = mqttCallback;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void setManualAcks(boolean z) {
        throw new UnsupportedOperationException();
    }

    public void setTraceCallback(MqttTraceHandler mqttTraceHandler) {
        this.t = mqttTraceHandler;
    }

    public void setTraceEnabled(boolean z) {
        this.v = z;
        MqttService mqttService = this.i;
        if (mqttService != null) {
            mqttService.setTraceEnabled(z);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i) {
        return subscribe(str, i, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener) {
        MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, obj, iMqttActionListener, new String[]{str});
        this.i.subscribe(this.j, str, i, (String) null, m(mqttTokenAndroid));
        return mqttTokenAndroid;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener, IMqttMessageListener iMqttMessageListener) {
        return subscribe(new String[]{str}, new int[]{i}, obj, iMqttActionListener, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i, IMqttMessageListener iMqttMessageListener) {
        return subscribe(str, i, (Object) null, (IMqttActionListener) null, iMqttMessageListener);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr) {
        return subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener) {
        MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, obj, iMqttActionListener, strArr);
        this.i.subscribe(this.j, strArr, iArr, (String) null, m(mqttTokenAndroid));
        return mqttTokenAndroid;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener, IMqttMessageListener[] iMqttMessageListenerArr) {
        this.i.subscribe(this.j, strArr, iArr, null, m(new MqttTokenAndroid(this, obj, iMqttActionListener, strArr)), iMqttMessageListenerArr);
        return null;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr, IMqttMessageListener[] iMqttMessageListenerArr) {
        return subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null, iMqttMessageListenerArr);
    }

    public void unregisterResources() {
        if (this.k == null || !this.w) {
            return;
        }
        synchronized (this) {
            LocalBroadcastManager.getInstance(this.k).unregisterReceiver(this);
            this.w = false;
        }
        if (this.x) {
            try {
                this.k.unbindService(this.h);
                this.x = false;
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String str) {
        return unsubscribe(str, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String str, Object obj, IMqttActionListener iMqttActionListener) {
        MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, obj, iMqttActionListener);
        this.i.unsubscribe(this.j, str, (String) null, m(mqttTokenAndroid));
        return mqttTokenAndroid;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String[] strArr) {
        return unsubscribe(strArr, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String[] strArr, Object obj, IMqttActionListener iMqttActionListener) {
        MqttTokenAndroid mqttTokenAndroid = new MqttTokenAndroid(this, obj, iMqttActionListener);
        this.i.unsubscribe(this.j, strArr, (String) null, m(mqttTokenAndroid));
        return mqttTokenAndroid;
    }
}
