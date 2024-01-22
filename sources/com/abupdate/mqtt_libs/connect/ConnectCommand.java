package com.abupdate.mqtt_libs.connect;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.abupdate.mqtt_libs.mqtt_service.MqttAndroidClient;
import com.abupdate.mqtt_libs.mqtt_service.MqttTraceHandler;
import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.MqttCallback;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.h;
import com.abupdate.mqtt_libs.mqttv3.m;
import com.abupdate.trace.Trace;
/* loaded from: classes.dex */
public class ConnectCommand implements Command {
    public static ConnectCommand s;

    /* renamed from: a  reason: collision with root package name */
    public String f1920a;
    public int b;
    public String c;
    public String d;
    public int e;
    public int f;
    public String g;
    public String h;
    public boolean i;
    public String j;
    public String k;
    public String l;
    public MqttAndroidClient m;
    public int n;
    public boolean o;
    public MqttCallback p;
    public boolean q = false;
    public MqttTraceHandler r;

    public static ConnectCommand getInstance() {
        if (s == null) {
            synchronized (ConnectCommand.class) {
                if (s == null) {
                    s = new ConnectCommand();
                }
            }
        }
        return s;
    }

    public final MqttAndroidClient a(Context context, String str, String str2) {
        return new MqttAndroidClient(context, str, str2);
    }

    public final String b() {
        String str = TextUtils.isEmpty(this.k) ? "tcp://" : "ssl://";
        return str + this.f1920a + ":" + this.b;
    }

    public void connect() {
    }

    @Override // com.abupdate.mqtt_libs.connect.Command
    public void execute(IMqttActionListener iMqttActionListener) throws MqttException {
        h hVar = new h();
        String b = b();
        if (this.m == null) {
            this.m = a(MqttManager.sCx, b, this.j);
        }
        if (!TextUtils.isEmpty(this.k)) {
            try {
                hVar.a(this.m.getSSLSocketFactory(getClass().getResourceAsStream(this.k), this.l));
            } catch (m e) {
                Log.e(getClass().getCanonicalName(), "MqttException Occured: ", e);
            }
        }
        hVar.a(this.i);
        hVar.b(this.e);
        hVar.a(this.f);
        if (!TextUtils.isEmpty(this.g)) {
            hVar.a(this.g);
        }
        if (!TextUtils.isEmpty(this.h)) {
            hVar.a(this.h.toCharArray());
        }
        boolean z = true;
        if (!TextUtils.isEmpty(this.c) || !TextUtils.isEmpty(this.d)) {
            try {
                hVar.a(this.d, this.c.getBytes(), this.n, this.o);
            } catch (Exception e2) {
                Trace.d("ConnectCommand", "connect() Exception Occured：" + e2.toString());
                z = false;
            }
        }
        this.m.setCallback(this.p);
        if (this.q) {
            this.m.setTraceCallback(this.r);
        }
        if (z) {
            try {
                this.m.connect(hVar, null, iMqttActionListener);
            } catch (MqttException e3) {
                Trace.d("ConnectCommand", "connect() MqttException Occured:" + e3.toString());
            }
        }
    }

    public MqttAndroidClient getClient() {
        return this.m;
    }

    public ConnectCommand setCleanSession(boolean z) {
        this.i = z;
        return this;
    }

    public ConnectCommand setClientId(String str) {
        this.j = str;
        return this;
    }

    public ConnectCommand setKeepAlive(int i) {
        this.f = i;
        return this;
    }

    public ConnectCommand setLastWill(String str, String str2, int i, boolean z) {
        this.c = str;
        this.d = str2;
        this.n = i;
        this.o = z;
        return this;
    }

    public void setMessageListener(MqttCallback mqttCallback) {
        this.p = mqttCallback;
    }

    public ConnectCommand setPort(int i) {
        this.b = i;
        return this;
    }

    public ConnectCommand setServer(String str) {
        this.f1920a = str;
        return this;
    }

    public ConnectCommand setSsl(String str, String str2) {
        this.k = str;
        this.l = str2;
        return this;
    }

    public ConnectCommand setTimeout(int i) {
        this.e = i;
        return this;
    }

    public ConnectCommand setTraceCallback(MqttTraceHandler mqttTraceHandler) {
        this.r = mqttTraceHandler;
        return this;
    }

    public ConnectCommand setTraceEnabled(boolean z) {
        this.q = z;
        return this;
    }

    public ConnectCommand setUserNameAndPassword(String str, String str2) {
        this.g = str;
        this.h = str2;
        return this;
    }
}
