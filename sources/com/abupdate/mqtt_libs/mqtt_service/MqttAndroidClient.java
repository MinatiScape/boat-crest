package com.abupdate.mqtt_libs.mqtt_service;

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
import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.IMqttDeliveryToken;
import com.abupdate.mqtt_libs.mqttv3.IMqttToken;
import com.abupdate.mqtt_libs.mqttv3.MqttCallback;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.MqttMessage;
import com.abupdate.mqtt_libs.mqttv3.k;
import com.abupdate.mqtt_libs.mqttv3.m;
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
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes.dex */
public class MqttAndroidClient extends BroadcastReceiver implements com.abupdate.mqtt_libs.mqttv3.c {
    public static final ExecutorService y = Executors.newCachedThreadPool();
    public final c h;
    public MqttService i;
    public String j;
    public Context k;
    public final SparseArray<IMqttToken> l;
    public int m;
    public final String n;
    public final String o;
    public com.abupdate.mqtt_libs.mqttv3.g p;
    public com.abupdate.mqtt_libs.mqttv3.h q;
    public IMqttToken r;
    public MqttCallback s;
    public MqttTraceHandler t;
    public final a u;
    public boolean v;
    public volatile boolean w;
    public volatile boolean x;

    /* loaded from: classes.dex */
    public enum a {
        AUTO_ACK,
        MANUAL_ACK
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MqttAndroidClient.this.j();
            if (MqttAndroidClient.this.w) {
                return;
            }
            MqttAndroidClient mqttAndroidClient = MqttAndroidClient.this;
            mqttAndroidClient.n(mqttAndroidClient);
        }
    }

    /* loaded from: classes.dex */
    public final class c implements ServiceConnection {
        public c() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MqttAndroidClient.this.i = ((f) iBinder).a();
            MqttAndroidClient.this.x = true;
            MqttAndroidClient.this.j();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            MqttAndroidClient.this.i = null;
        }

        public /* synthetic */ c(MqttAndroidClient mqttAndroidClient, b bVar) {
            this();
        }
    }

    public MqttAndroidClient(Context context, String str, String str2) {
        this(context, str, str2, null, a.AUTO_ACK);
    }

    public boolean acknowledgeMessage(String str) {
        return this.u == a.MANUAL_ACK && this.i.acknowledgeMessageArrival(this.j, str) == h.OK;
    }

    public void close() {
        MqttService mqttService = this.i;
        if (mqttService != null) {
            if (this.j == null) {
                this.j = mqttService.getClient(this.n, this.o, this.k.getApplicationInfo().packageName, this.p);
            }
            this.i.close(this.j);
        }
    }

    public IMqttToken connect() throws MqttException {
        return connect(null, null);
    }

    public void deleteBufferedMessage(int i) {
        this.i.deleteBufferedMessage(this.j, i);
    }

    public IMqttToken disconnect() throws MqttException {
        g gVar = new g(this, null, null);
        this.i.disconnect(this.j, null, r(gVar));
        return gVar;
    }

    public void disconnectForcibly() throws MqttException {
        throw new UnsupportedOperationException();
    }

    public final void f(Bundle bundle) {
        IMqttToken iMqttToken = this.r;
        o(bundle);
        q(iMqttToken, bundle);
    }

    public final void g(Bundle bundle) {
        if (this.s instanceof com.abupdate.mqtt_libs.mqttv3.f) {
            ((com.abupdate.mqtt_libs.mqttv3.f) this.s).a(bundle.getBoolean(MqttServiceConstants.CALLBACK_RECONNECT, false), bundle.getString(MqttServiceConstants.CALLBACK_SERVER_URI));
        }
    }

    public MqttMessage getBufferedMessage(int i) {
        return this.i.getBufferedMessage(this.j, i);
    }

    public int getBufferedMessageCount() {
        return this.i.getBufferedMessageCount(this.j);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.c
    public String getClientId() {
        return this.o;
    }

    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.i.getPendingDeliveryTokens(this.j);
    }

    public SSLSocketFactory getSSLSocketFactory(InputStream inputStream, String str) throws m {
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
            throw new m(e);
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.c
    public String getServerURI() {
        return this.n;
    }

    public final void h(Bundle bundle) {
        if (this.s != null) {
            this.s.connectionLost((Exception) bundle.getSerializable(MqttServiceConstants.CALLBACK_EXCEPTION));
        }
    }

    public final void i(Bundle bundle) {
        this.j = null;
        IMqttToken o = o(bundle);
        if (o != null) {
            ((g) o).a();
        }
        MqttCallback mqttCallback = this.s;
        if (mqttCallback != null) {
            mqttCallback.connectionLost(null);
        }
    }

    public boolean isConnected() {
        MqttService mqttService;
        String str = this.j;
        return (str == null || (mqttService = this.i) == null || !mqttService.isConnected(str)) ? false : true;
    }

    public boolean isKeepConnect() {
        MqttService mqttService;
        String str = this.j;
        if (str == null || (mqttService = this.i) == null) {
            return false;
        }
        return mqttService.containClient(str);
    }

    public final void j() {
        if (this.j == null) {
            this.j = this.i.getClient(this.n, this.o, this.k.getApplicationInfo().packageName, this.p);
        }
        this.i.setTraceEnabled(this.v);
        this.i.setTraceCallbackId(this.j);
        try {
            this.i.connect(this.j, this.q, null, r(this.r));
        } catch (MqttException e) {
            IMqttActionListener actionCallback = this.r.getActionCallback();
            if (actionCallback != null) {
                actionCallback.onFailure(this.r, e);
            }
        }
    }

    public final synchronized IMqttToken k(Bundle bundle) {
        return this.l.get(Integer.parseInt(bundle.getString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN)));
    }

    public final void l(Bundle bundle) {
        if (this.s != null) {
            String string = bundle.getString(MqttServiceConstants.CALLBACK_MESSAGE_ID);
            String string2 = bundle.getString(MqttServiceConstants.CALLBACK_DESTINATION_NAME);
            ParcelableMqttMessage parcelableMqttMessage = (ParcelableMqttMessage) bundle.getParcelable(MqttServiceConstants.CALLBACK_MESSAGE_PARCEL);
            try {
                if (this.u == a.AUTO_ACK) {
                    this.s.messageArrived(string2, parcelableMqttMessage);
                    this.i.acknowledgeMessageArrival(this.j, string);
                } else {
                    parcelableMqttMessage.n = string;
                    this.s.messageArrived(string2, parcelableMqttMessage);
                }
            } catch (Exception unused) {
            }
        }
    }

    public final void m(Bundle bundle) {
        IMqttToken o = o(bundle);
        if (o == null || this.s == null || ((h) bundle.getSerializable(MqttServiceConstants.CALLBACK_STATUS)) != h.OK || !(o instanceof IMqttDeliveryToken)) {
            return;
        }
        this.s.deliveryComplete((IMqttDeliveryToken) o);
    }

    public void messageArrivedComplete(int i, int i2) throws MqttException {
        throw new UnsupportedOperationException();
    }

    public final void n(BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MqttServiceConstants.CALLBACK_TO_ACTIVITY);
        LocalBroadcastManager.getInstance(this.k).registerReceiver(broadcastReceiver, intentFilter);
        this.w = true;
    }

    public final synchronized IMqttToken o(Bundle bundle) {
        String string = bundle.getString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN);
        if (string != null) {
            int parseInt = Integer.parseInt(string);
            IMqttToken iMqttToken = this.l.get(parseInt);
            this.l.delete(parseInt);
            return iMqttToken;
        }
        return null;
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
            f(extras);
        } else if (MqttServiceConstants.CONNECT_EXTENDED_ACTION.equals(string2)) {
            g(extras);
        } else if (MqttServiceConstants.MESSAGE_ARRIVED_ACTION.equals(string2)) {
            l(extras);
        } else if (MqttServiceConstants.SUBSCRIBE_ACTION.equals(string2)) {
            s(extras);
        } else if (MqttServiceConstants.UNSUBSCRIBE_ACTION.equals(string2)) {
            u(extras);
        } else if (MqttServiceConstants.SEND_ACTION.equals(string2)) {
            p(extras);
        } else if (MqttServiceConstants.MESSAGE_DELIVERED_ACTION.equals(string2)) {
            m(extras);
        } else if (MqttServiceConstants.ON_CONNECTION_LOST_ACTION.equals(string2)) {
            h(extras);
        } else if (MqttServiceConstants.DISCONNECT_ACTION.equals(string2)) {
            i(extras);
        } else if (MqttServiceConstants.TRACE_ACTION.equals(string2)) {
            t(extras);
        } else {
            this.i.traceError("MqttService", "Callback action doesn't exist.");
        }
    }

    public final void p(Bundle bundle) {
        q(k(bundle), bundle);
    }

    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z) throws MqttException, k {
        return publish(str, bArr, i, z, null, null);
    }

    public final void q(IMqttToken iMqttToken, Bundle bundle) {
        if (iMqttToken != null) {
            if (((h) bundle.getSerializable(MqttServiceConstants.CALLBACK_STATUS)) == h.OK) {
                ((g) iMqttToken).a();
                return;
            }
            ((g) iMqttToken).c((Exception) bundle.getSerializable(MqttServiceConstants.CALLBACK_EXCEPTION));
            return;
        }
        this.i.traceError("MqttService", "simpleAction : token is null");
    }

    public final synchronized String r(IMqttToken iMqttToken) {
        int i;
        this.l.put(this.m, iMqttToken);
        i = this.m;
        this.m = i + 1;
        return Integer.toString(i);
    }

    public void registerResources(Context context) {
        if (context != null) {
            this.k = context;
            if (this.w) {
                return;
            }
            n(this);
        }
    }

    public final void s(Bundle bundle) {
        q(o(bundle), bundle);
    }

    public void setBufferOpts(com.abupdate.mqtt_libs.mqttv3.b bVar) {
        this.i.setBufferOpts(this.j, bVar);
    }

    public void setCallback(MqttCallback mqttCallback) {
        this.s = mqttCallback;
    }

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

    public void startKeepConnect(long j, long j2) {
        MqttService mqttService = this.i;
        if (mqttService == null) {
            return;
        }
        mqttService.startKeepConnect(j, j2);
    }

    public void stopKeepConnect() {
        this.i.stopKeepConnect();
    }

    public IMqttToken subscribe(String str, int i) throws MqttException, m {
        return subscribe(str, i, (Object) null, (IMqttActionListener) null);
    }

    public final void t(Bundle bundle) {
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

    public final void u(Bundle bundle) {
        q(o(bundle), bundle);
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

    public IMqttToken unsubscribe(String str) throws MqttException {
        return unsubscribe(str, (Object) null, (IMqttActionListener) null);
    }

    public MqttAndroidClient(Context context, String str, String str2, a aVar) {
        this(context, str, str2, null, aVar);
    }

    public IMqttToken connect(com.abupdate.mqtt_libs.mqttv3.h hVar) throws MqttException {
        return connect(hVar, null, null);
    }

    public void disconnectForcibly(long j) throws MqttException {
        throw new UnsupportedOperationException();
    }

    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage) throws MqttException, k {
        return publish(str, mqttMessage, (Object) null, (IMqttActionListener) null);
    }

    public IMqttToken subscribe(String[] strArr, int[] iArr) throws MqttException, m {
        return subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null);
    }

    public IMqttToken unsubscribe(String[] strArr) throws MqttException {
        return unsubscribe(strArr, (Object) null, (IMqttActionListener) null);
    }

    public MqttAndroidClient(Context context, String str, String str2, com.abupdate.mqtt_libs.mqttv3.g gVar) {
        this(context, str, str2, gVar, a.AUTO_ACK);
    }

    public IMqttToken connect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return connect(new com.abupdate.mqtt_libs.mqttv3.h(), obj, iMqttActionListener);
    }

    public void disconnectForcibly(long j, long j2) throws MqttException {
        throw new UnsupportedOperationException();
    }

    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, k {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        e eVar = new e(this, obj, iMqttActionListener, mqttMessage);
        eVar.b(this.i.publish(this.j, str, bArr, i, z, null, r(eVar)));
        return eVar;
    }

    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        g gVar = new g(this, obj, iMqttActionListener, new String[]{str});
        this.i.subscribe(this.j, str, i, (String) null, r(gVar));
        return gVar;
    }

    public IMqttToken unsubscribe(String str, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        g gVar = new g(this, obj, iMqttActionListener);
        this.i.unsubscribe(this.j, str, (String) null, r(gVar));
        return gVar;
    }

    public MqttAndroidClient(Context context, String str, String str2, com.abupdate.mqtt_libs.mqttv3.g gVar, a aVar) {
        this.h = new c(this, null);
        this.l = new SparseArray<>();
        this.m = 0;
        this.p = null;
        this.v = false;
        this.w = false;
        this.x = false;
        this.k = context;
        this.n = str;
        this.o = str2;
        this.p = gVar;
        this.u = aVar;
    }

    public IMqttToken connect(com.abupdate.mqtt_libs.mqttv3.h hVar, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        IMqttActionListener actionCallback;
        IMqttToken gVar = new g(this, obj, iMqttActionListener);
        this.q = hVar;
        this.r = gVar;
        if (this.i == null) {
            Intent intent = new Intent();
            intent.setClassName(this.k, "com.abupdate.mqtt_libs.mqtt_service.MqttService");
            if (this.k.startService(intent) == null && (actionCallback = gVar.getActionCallback()) != null) {
                actionCallback.onFailure(gVar, new RuntimeException("cannot start service com.abupdate.mqtt_libs.mqtt_service.MqttService"));
            }
            this.k.bindService(intent, this.h, 1);
            if (!this.w) {
                n(this);
            }
        } else {
            y.execute(new b());
        }
        return gVar;
    }

    public IMqttToken disconnect(long j) throws MqttException {
        g gVar = new g(this, null, null);
        this.i.disconnect(this.j, j, null, r(gVar));
        return gVar;
    }

    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        g gVar = new g(this, obj, iMqttActionListener, strArr);
        this.i.subscribe(this.j, strArr, iArr, (String) null, r(gVar));
        return gVar;
    }

    public IMqttToken unsubscribe(String[] strArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        g gVar = new g(this, obj, iMqttActionListener);
        this.i.unsubscribe(this.j, strArr, (String) null, r(gVar));
        return gVar;
    }

    public IMqttToken disconnect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        g gVar = new g(this, obj, iMqttActionListener);
        this.i.disconnect(this.j, null, r(gVar));
        return gVar;
    }

    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener, com.abupdate.mqtt_libs.mqttv3.d dVar) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, obj, iMqttActionListener, new com.abupdate.mqtt_libs.mqttv3.d[]{dVar});
    }

    public IMqttToken disconnect(long j, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        g gVar = new g(this, obj, iMqttActionListener);
        this.i.disconnect(this.j, j, null, r(gVar));
        return gVar;
    }

    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, k {
        e eVar = new e(this, obj, iMqttActionListener, mqttMessage);
        eVar.b(this.i.publish(this.j, str, mqttMessage, null, r(eVar)));
        return eVar;
    }

    public IMqttToken subscribe(String str, int i, com.abupdate.mqtt_libs.mqttv3.d dVar) throws MqttException {
        return subscribe(str, i, (Object) null, (IMqttActionListener) null, dVar);
    }

    public IMqttToken subscribe(String[] strArr, int[] iArr, com.abupdate.mqtt_libs.mqttv3.d[] dVarArr) throws MqttException {
        return subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null, dVarArr);
    }

    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener, com.abupdate.mqtt_libs.mqttv3.d[] dVarArr) throws MqttException {
        this.i.subscribe(this.j, strArr, iArr, null, r(new g(this, obj, iMqttActionListener, strArr)), dVarArr);
        return null;
    }
}
