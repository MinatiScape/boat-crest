package com.abupdate.mqtt_libs.mqtt_service;

import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import com.abupdate.mqtt_libs.mqtt_service.c;
import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.IMqttDeliveryToken;
import com.abupdate.mqtt_libs.mqttv3.IMqttToken;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.MqttMessage;
import com.abupdate.mqtt_libs.mqttv3.k;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes.dex */
public class d implements com.abupdate.mqtt_libs.mqttv3.f {

    /* renamed from: a  reason: collision with root package name */
    public String f1935a;
    public String b;
    public com.abupdate.mqtt_libs.mqttv3.g c;
    public com.abupdate.mqtt_libs.mqttv3.h d;
    public String e;
    public MqttService i;
    public String r;
    public String f = null;
    public com.abupdate.mqtt_libs.mqttv3.e g = null;
    public com.abupdate.mqtt_libs.mqtt_service.a h = null;
    public volatile boolean j = true;
    public boolean k = true;
    public volatile boolean l = false;
    public Map<IMqttDeliveryToken, String> m = new HashMap();
    public Map<IMqttDeliveryToken, MqttMessage> n = new HashMap();
    public Map<IMqttDeliveryToken, String> o = new HashMap();
    public Map<IMqttDeliveryToken, String> p = new HashMap();
    public PowerManager.WakeLock q = null;
    public com.abupdate.mqtt_libs.mqttv3.b s = null;

    /* loaded from: classes.dex */
    public class a extends C0197d {
        public final /* synthetic */ Bundle c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Bundle bundle, Bundle bundle2) {
            super(d.this, bundle, null);
            this.c = bundle2;
        }

        @Override // com.abupdate.mqtt_libs.mqtt_service.d.C0197d, com.abupdate.mqtt_libs.mqttv3.IMqttActionListener
        public void onFailure(IMqttToken iMqttToken, Throwable th) {
            this.c.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, th.getLocalizedMessage());
            this.c.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, th);
            MqttService mqttService = d.this.i;
            mqttService.traceError("MqttConnection", "connect fail, call connect to reconnect.reason:" + th.getMessage());
            d.this.x(this.c);
        }

        @Override // com.abupdate.mqtt_libs.mqtt_service.d.C0197d, com.abupdate.mqtt_libs.mqttv3.IMqttActionListener
        public void onSuccess(IMqttToken iMqttToken) {
            d.this.h(this.c);
            d.this.i.traceDebug("MqttConnection", "connect success!");
        }
    }

    /* loaded from: classes.dex */
    public class b implements IMqttActionListener {
        public b(d dVar) {
        }

        @Override // com.abupdate.mqtt_libs.mqttv3.IMqttActionListener
        public void onFailure(IMqttToken iMqttToken, Throwable th) {
        }

        @Override // com.abupdate.mqtt_libs.mqttv3.IMqttActionListener
        public void onSuccess(IMqttToken iMqttToken) {
        }
    }

    /* loaded from: classes.dex */
    public class c extends C0197d {
        public final /* synthetic */ Bundle c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Bundle bundle, Bundle bundle2) {
            super(d.this, bundle, null);
            this.c = bundle2;
        }

        @Override // com.abupdate.mqtt_libs.mqtt_service.d.C0197d, com.abupdate.mqtt_libs.mqttv3.IMqttActionListener
        public void onFailure(IMqttToken iMqttToken, Throwable th) {
            this.c.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, th.getLocalizedMessage());
            this.c.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, th);
            d.this.i.d(d.this.e, h.ERROR, this.c);
            d.this.x(this.c);
        }

        @Override // com.abupdate.mqtt_libs.mqtt_service.d.C0197d, com.abupdate.mqtt_libs.mqttv3.IMqttActionListener
        public void onSuccess(IMqttToken iMqttToken) {
            d.this.i.traceDebug("MqttConnection", "Reconnect Success!");
            d.this.i.traceDebug("MqttConnection", "DeliverBacklog when reconnect.");
            d.this.h(this.c);
        }
    }

    /* renamed from: com.abupdate.mqtt_libs.mqtt_service.d$d  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0197d implements IMqttActionListener {

        /* renamed from: a  reason: collision with root package name */
        public final Bundle f1936a;

        public /* synthetic */ C0197d(d dVar, Bundle bundle, a aVar) {
            this(bundle);
        }

        @Override // com.abupdate.mqtt_libs.mqttv3.IMqttActionListener
        public void onFailure(IMqttToken iMqttToken, Throwable th) {
            this.f1936a.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, th.getLocalizedMessage());
            this.f1936a.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, th);
            d.this.i.d(d.this.e, h.ERROR, this.f1936a);
        }

        @Override // com.abupdate.mqtt_libs.mqttv3.IMqttActionListener
        public void onSuccess(IMqttToken iMqttToken) {
            d.this.i.d(d.this.e, h.OK, this.f1936a);
        }

        public C0197d(Bundle bundle) {
            this.f1936a = bundle;
        }
    }

    public d(MqttService mqttService, String str, String str2, com.abupdate.mqtt_libs.mqttv3.g gVar, String str3) {
        this.c = null;
        this.i = null;
        this.r = null;
        this.f1935a = str;
        this.i = mqttService;
        this.b = str2;
        this.c = gVar;
        this.e = str3;
        this.r = d.class.getCanonicalName() + HexStringBuilder.DEFAULT_SEPARATOR + str2 + HexStringBuilder.DEFAULT_SEPARATOR + "on host " + str;
    }

    public boolean A() {
        com.abupdate.mqtt_libs.mqttv3.e eVar = this.g;
        return eVar != null && eVar.a();
    }

    public IMqttDeliveryToken[] B() {
        return this.g.b();
    }

    public void C() {
        if (this.j || this.k) {
            return;
        }
        connectionLost(new Exception("Android offline"));
    }

    public synchronized void D() {
        if (this.g == null) {
            this.i.traceError("MqttConnection", "Reconnect myClient = null. Will not do reconnect");
        } else if (this.l) {
            this.i.traceDebug("MqttConnection", "The client is connecting. Reconnect return directly.");
        } else if (!this.i.isOnline()) {
            this.i.traceDebug("MqttConnection", "The network is not reachable. Will not do reconnect");
        } else {
            if (this.d.n()) {
                Log.i("MqttConnection", "Requesting Automatic reconnect using New Java AC");
                Bundle bundle = new Bundle();
                bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, this.f);
                bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, null);
                bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.CONNECT_ACTION);
                try {
                    this.g.c();
                } catch (MqttException e) {
                    Log.e("MqttConnection", "Exception occurred attempting to reconnect: " + e.getMessage());
                    q(false);
                    i(bundle, e);
                }
                return;
            }
            if (this.j && !this.k) {
                this.i.traceDebug("MqttConnection", "Do Real Reconnect!");
                Bundle bundle2 = new Bundle();
                bundle2.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, this.f);
                bundle2.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, null);
                bundle2.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.CONNECT_ACTION);
                try {
                    this.g.a(this.d, (Object) null, new c(bundle2, bundle2));
                    q(true);
                } catch (MqttException e2) {
                    MqttService mqttService = this.i;
                    mqttService.traceError("MqttConnection", "Cannot reconnect to remote server." + e2.getMessage());
                    q(false);
                    i(bundle2, e2);
                } catch (Exception e3) {
                    MqttService mqttService2 = this.i;
                    mqttService2.traceError("MqttConnection", "Cannot reconnect to remote server." + e3.getMessage());
                    q(false);
                    i(bundle2, new MqttException(6, e3.getCause()));
                }
            }
            return;
        }
    }

    public int E() {
        return this.g.d();
    }

    public final void F() {
        Iterator<c.a> a2 = this.i.j.a(this.e);
        while (a2.hasNext()) {
            c.a next = a2.next();
            Bundle a3 = a(next.a(), next.b(), next.c());
            a3.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.MESSAGE_ARRIVED_ACTION);
            this.i.d(this.e, h.OK, a3);
        }
    }

    public final void G() {
        if (this.q == null) {
            this.q = ((PowerManager) this.i.getSystemService("power")).newWakeLock(1, this.r);
        }
        this.q.acquire();
    }

    public final void H() {
        PowerManager.WakeLock wakeLock = this.q;
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        this.q.release();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.f
    public void a(boolean z, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.CONNECT_EXTENDED_ACTION);
        bundle.putBoolean(MqttServiceConstants.CALLBACK_RECONNECT, z);
        bundle.putString(MqttServiceConstants.CALLBACK_SERVER_URI, str);
        this.i.d(this.e, h.OK, bundle);
    }

    public IMqttDeliveryToken c(String str, MqttMessage mqttMessage, String str2, String str3) {
        com.abupdate.mqtt_libs.mqttv3.b bVar;
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SEND_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str3);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str2);
        com.abupdate.mqtt_libs.mqttv3.e eVar = this.g;
        IMqttDeliveryToken iMqttDeliveryToken = null;
        if (eVar != null && eVar.a()) {
            try {
                iMqttDeliveryToken = this.g.a(str, mqttMessage, str2, new C0197d(this, bundle, null));
                n(str, mqttMessage, iMqttDeliveryToken, str2, str3);
                return iMqttDeliveryToken;
            } catch (Exception e) {
                i(bundle, e);
                return iMqttDeliveryToken;
            }
        } else if (this.g != null && (bVar = this.s) != null && bVar.b()) {
            try {
                iMqttDeliveryToken = this.g.a(str, mqttMessage, str2, new C0197d(this, bundle, null));
                n(str, mqttMessage, iMqttDeliveryToken, str2, str3);
                return iMqttDeliveryToken;
            } catch (Exception e2) {
                i(bundle, e2);
                return iMqttDeliveryToken;
            }
        } else {
            Log.i("MqttConnection", "Client is not connected, so not sending message");
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
            this.i.traceError(MqttServiceConstants.SEND_ACTION, "not connected");
            this.i.d(this.e, h.ERROR, bundle);
            return null;
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.MqttCallback
    public void connectionLost(Throwable th) {
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "connectionLost(" + th.getMessage() + ")");
        this.j = true;
        try {
            if (!this.d.n()) {
                this.g.a((Object) null, new b(this));
            } else {
                this.h.a(100L);
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
        this.i.d(this.e, h.OK, bundle);
        H();
    }

    public IMqttDeliveryToken d(String str, byte[] bArr, int i, boolean z, String str2, String str3) {
        MqttMessage mqttMessage;
        IMqttDeliveryToken a2;
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SEND_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str3);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str2);
        com.abupdate.mqtt_libs.mqttv3.e eVar = this.g;
        IMqttDeliveryToken iMqttDeliveryToken = null;
        if (eVar != null && eVar.a()) {
            C0197d c0197d = new C0197d(this, bundle, null);
            try {
                mqttMessage = new MqttMessage(bArr);
                mqttMessage.setQos(i);
                mqttMessage.setRetained(z);
                a2 = this.g.a(str, bArr, i, z, str2, c0197d);
            } catch (Exception e) {
                e = e;
            }
            try {
                n(str, mqttMessage, a2, str2, str3);
                return a2;
            } catch (Exception e2) {
                e = e2;
                iMqttDeliveryToken = a2;
                i(bundle, e);
                return iMqttDeliveryToken;
            }
        }
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
        this.i.traceError(MqttServiceConstants.SEND_ACTION, "not connected");
        this.i.d(this.e, h.ERROR, bundle);
        return null;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.MqttCallback
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "deliveryComplete(" + iMqttDeliveryToken + ")");
        MqttMessage remove = this.n.remove(iMqttDeliveryToken);
        if (remove != null) {
            String remove2 = this.o.remove(iMqttDeliveryToken);
            String remove3 = this.p.remove(iMqttDeliveryToken);
            Bundle a2 = a(null, this.m.remove(iMqttDeliveryToken), remove);
            if (remove2 != null) {
                a2.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SEND_ACTION);
                a2.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, remove2);
                a2.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, remove3);
                this.i.d(this.e, h.OK, a2);
            }
            a2.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.MESSAGE_DELIVERED_ACTION);
            this.i.d(this.e, h.OK, a2);
        }
    }

    public MqttMessage e(int i) {
        return this.g.a(i);
    }

    public String f() {
        return this.f1935a;
    }

    public void g(long j, String str, String str2) {
        this.i.traceDebug("MqttConnection", "disconnect()");
        this.j = true;
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.DISCONNECT_ACTION);
        com.abupdate.mqtt_libs.mqttv3.e eVar = this.g;
        if (eVar != null && eVar.a()) {
            try {
                this.g.a(j, str, new C0197d(this, bundle, null));
            } catch (Exception e) {
                i(bundle, e);
            }
        } else {
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
            this.i.traceError(MqttServiceConstants.DISCONNECT_ACTION, "not connected");
            this.i.d(this.e, h.ERROR, bundle);
        }
        com.abupdate.mqtt_libs.mqttv3.h hVar = this.d;
        if (hVar != null && hVar.l()) {
            this.i.j.b(this.e);
        }
        H();
    }

    public final void h(Bundle bundle) {
        G();
        this.i.d(this.e, h.OK, bundle);
        F();
        q(false);
        this.j = false;
        H();
    }

    public final void i(Bundle bundle, Exception exc) {
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, exc.getLocalizedMessage());
        bundle.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, exc);
        this.i.d(this.e, h.ERROR, bundle);
    }

    public void k(com.abupdate.mqtt_libs.mqttv3.b bVar) {
        this.s = bVar;
        this.g.a(bVar);
    }

    public void l(com.abupdate.mqtt_libs.mqttv3.h hVar, String str, String str2) {
        this.d = hVar;
        this.f = str2;
        if (hVar != null) {
            this.k = hVar.l();
        }
        if (this.d.l()) {
            this.i.j.b(this.e);
        }
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "Connecting {" + this.f1935a + "} as {" + this.b + "}");
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.CONNECT_ACTION);
        try {
            if (this.c == null) {
                File externalFilesDir = this.i.getExternalFilesDir("MqttConnection");
                if (externalFilesDir == null && (externalFilesDir = this.i.getDir("MqttConnection", 0)) == null) {
                    bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "Error! No external and internal storage available");
                    bundle.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, new k());
                    this.i.d(this.e, h.ERROR, bundle);
                    return;
                }
                this.c = new com.abupdate.mqtt_libs.mqttv3.b.b(externalFilesDir.getAbsolutePath());
            }
            a aVar = new a(bundle, bundle);
            if (this.g != null) {
                if (this.l) {
                    this.i.traceDebug("MqttConnection", "myClient != null and the client is connecting. Connect return directly.");
                    MqttService mqttService2 = this.i;
                    mqttService2.traceDebug("MqttConnection", "Connect return:isConnecting:" + this.l + ".disconnected:" + this.j);
                    return;
                } else if (!this.j) {
                    this.i.traceDebug("MqttConnection", "myClient != null and the client is connected and notify!");
                    h(bundle);
                    return;
                } else {
                    this.i.traceDebug("MqttConnection", "myClient != null and the client is not connected");
                    this.i.traceDebug("MqttConnection", "Do Real connect!");
                    q(true);
                    this.g.a(this.d, str, aVar);
                    return;
                }
            }
            this.h = new com.abupdate.mqtt_libs.mqtt_service.a(this.i);
            com.abupdate.mqtt_libs.mqttv3.e eVar = new com.abupdate.mqtt_libs.mqttv3.e(this.f1935a, this.b, this.c, this.h);
            this.g = eVar;
            eVar.a(this);
            this.i.traceDebug("MqttConnection", "Do Real connect!");
            q(true);
            this.g.a(this.d, str, aVar);
        } catch (Exception e) {
            MqttService mqttService3 = this.i;
            mqttService3.traceError("MqttConnection", "Exception occurred attempting to connect: " + e.getMessage());
            q(false);
            i(bundle, e);
        }
    }

    public void m(String str, int i, String str2, String str3) {
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "subscribe({" + str + "}," + i + ",{" + str2 + "}, {" + str3 + "}");
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SUBSCRIBE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str3);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str2);
        com.abupdate.mqtt_libs.mqttv3.e eVar = this.g;
        if (eVar != null && eVar.a()) {
            try {
                this.g.a(str, i, str2, new C0197d(this, bundle, null));
                return;
            } catch (Exception e) {
                i(bundle, e);
                return;
            }
        }
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
        this.i.traceError(MqttServiceConstants.SUBSCRIBE_ACTION, "not connected");
        this.i.d(this.e, h.ERROR, bundle);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.MqttCallback
    public void messageArrived(String str, MqttMessage mqttMessage) throws Exception {
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "messageArrived(" + str + ",{" + mqttMessage.toString() + "})");
        String a2 = this.i.j.a(this.e, str, mqttMessage);
        Bundle a3 = a(a2, str, mqttMessage);
        a3.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.MESSAGE_ARRIVED_ACTION);
        a3.putString(MqttServiceConstants.CALLBACK_MESSAGE_ID, a2);
        this.i.d(this.e, h.OK, a3);
    }

    public final void n(String str, MqttMessage mqttMessage, IMqttDeliveryToken iMqttDeliveryToken, String str2, String str3) {
        this.m.put(iMqttDeliveryToken, str);
        this.n.put(iMqttDeliveryToken, mqttMessage);
        this.o.put(iMqttDeliveryToken, str3);
        this.p.put(iMqttDeliveryToken, str2);
    }

    public void o(String str, String str2) {
        this.i.traceDebug("MqttConnection", "disconnect()");
        this.j = true;
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.DISCONNECT_ACTION);
        com.abupdate.mqtt_libs.mqttv3.e eVar = this.g;
        if (eVar != null && eVar.a()) {
            try {
                this.g.a(str, new C0197d(this, bundle, null));
            } catch (Exception e) {
                i(bundle, e);
            }
        } else {
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
            this.i.traceError(MqttServiceConstants.DISCONNECT_ACTION, "not connected");
            this.i.d(this.e, h.ERROR, bundle);
        }
        com.abupdate.mqtt_libs.mqttv3.h hVar = this.d;
        if (hVar != null && hVar.l()) {
            this.i.j.b(this.e);
        }
        H();
    }

    public void p(String str, String str2, String str3) {
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "unsubscribe({" + str + "},{" + str2 + "}, {" + str3 + "})");
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.UNSUBSCRIBE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str3);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str2);
        com.abupdate.mqtt_libs.mqttv3.e eVar = this.g;
        if (eVar != null && eVar.a()) {
            try {
                this.g.a(str, str2, new C0197d(this, bundle, null));
                return;
            } catch (Exception e) {
                i(bundle, e);
                return;
            }
        }
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
        this.i.traceError(MqttServiceConstants.SUBSCRIBE_ACTION, "not connected");
        this.i.d(this.e, h.ERROR, bundle);
    }

    public final synchronized void q(boolean z) {
        this.l = z;
    }

    public void r(String[] strArr, String str, String str2) {
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "unsubscribe({" + Arrays.toString(strArr) + "},{" + str + "}, {" + str2 + "})");
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.UNSUBSCRIBE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        com.abupdate.mqtt_libs.mqttv3.e eVar = this.g;
        if (eVar != null && eVar.a()) {
            try {
                this.g.a(strArr, str, new C0197d(this, bundle, null));
                return;
            } catch (Exception e) {
                i(bundle, e);
                return;
            }
        }
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
        this.i.traceError(MqttServiceConstants.SUBSCRIBE_ACTION, "not connected");
        this.i.d(this.e, h.ERROR, bundle);
    }

    public void s(String[] strArr, int[] iArr, String str, String str2) {
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "subscribe({" + Arrays.toString(strArr) + "}," + Arrays.toString(iArr) + ",{" + str + "}, {" + str2 + "}");
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SUBSCRIBE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        com.abupdate.mqtt_libs.mqttv3.e eVar = this.g;
        if (eVar != null && eVar.a()) {
            try {
                this.g.a(strArr, iArr, str, new C0197d(this, bundle, null));
                return;
            } catch (Exception e) {
                i(bundle, e);
                return;
            }
        }
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
        this.i.traceError(MqttServiceConstants.SUBSCRIBE_ACTION, "not connected");
        this.i.d(this.e, h.ERROR, bundle);
    }

    public void t(String[] strArr, int[] iArr, String str, String str2, com.abupdate.mqtt_libs.mqttv3.d[] dVarArr) {
        MqttService mqttService = this.i;
        mqttService.traceDebug("MqttConnection", "subscribe({" + Arrays.toString(strArr) + "}," + Arrays.toString(iArr) + ",{" + str + "}, {" + str2 + "}");
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SUBSCRIBE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        com.abupdate.mqtt_libs.mqttv3.e eVar = this.g;
        if (eVar != null && eVar.a()) {
            new C0197d(this, bundle, null);
            try {
                this.g.a(strArr, iArr, dVarArr);
                return;
            } catch (Exception e) {
                i(bundle, e);
                return;
            }
        }
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "not connected");
        this.i.traceError(MqttServiceConstants.SUBSCRIBE_ACTION, "not connected");
        this.i.d(this.e, h.ERROR, bundle);
    }

    public String u() {
        return this.b;
    }

    public void w(int i) {
        this.g.b(i);
    }

    public final void x(Bundle bundle) {
        G();
        this.j = true;
        q(false);
        this.i.d(this.e, h.ERROR, bundle);
        H();
    }

    public void z() {
        this.i.traceDebug("MqttConnection", "close()");
        try {
            com.abupdate.mqtt_libs.mqttv3.e eVar = this.g;
            if (eVar != null) {
                eVar.e();
            }
        } catch (MqttException e) {
            i(new Bundle(), e);
        }
    }

    public final Bundle a(String str, String str2, MqttMessage mqttMessage) {
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_MESSAGE_ID, str);
        bundle.putString(MqttServiceConstants.CALLBACK_DESTINATION_NAME, str2);
        bundle.putParcelable(MqttServiceConstants.CALLBACK_MESSAGE_PARCEL, new ParcelableMqttMessage(mqttMessage));
        return bundle;
    }
}
