package com.abupdate.iot_libs.a;

import android.text.TextUtils;
import com.abupdate.iot_libs.constant.SDKConfig;
import com.abupdate.iot_libs.info.DeviceInfo;
import com.abupdate.iot_libs.info.ProductInfo;
import com.abupdate.iot_libs.info.RegisterInfo;
import com.abupdate.iot_libs.inter.ILoginCallback;
import com.abupdate.iot_libs.inter.ILogoutCallback;
import com.abupdate.iot_libs.inter.IReportDeviceStatusCallback;
import com.abupdate.iot_libs.inter.MessageListener;
import com.abupdate.iot_libs.inter.OtaListener;
import com.abupdate.iot_libs.utils.j;
import com.abupdate.mqtt_libs.connect.ConnectCommand;
import com.abupdate.mqtt_libs.connect.DisconnectCommand;
import com.abupdate.mqtt_libs.connect.MqttManager;
import com.abupdate.mqtt_libs.connect.PubCommand;
import com.abupdate.mqtt_libs.connect.SubCommand;
import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.trace.Trace;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class b {
    public static b d;

    /* renamed from: a  reason: collision with root package name */
    public Map<Integer, String> f1886a = new HashMap();
    public MessageListener b = MessageListener.getInstance();
    public a c = a.Null;

    /* loaded from: classes.dex */
    public enum a {
        Null,
        Connecting,
        Connected,
        Login,
        Logout,
        Disconnecting,
        Disconnected
    }

    public static b a() {
        if (d == null) {
            synchronized (b.class) {
                if (d == null) {
                    d = new b();
                }
            }
        }
        return d;
    }

    public a b() {
        return this.c;
    }

    public void c() {
        MqttManager mqttManager = MqttManager.getInstance();
        try {
            mqttManager.connect(ConnectCommand.getInstance().setClientId(DeviceInfo.getInstance().mid).setServer(SDKConfig.MQTT_HOST).setPort(SDKConfig.MQTT_SSL_PORT).setTimeout(10).setKeepAlive(100).setLastWill(h(), String.format("product/%s/%s/logout", ProductInfo.getInstance().productId, RegisterInfo.getInstance().deviceId), 1, false).setCleanSession(false).setSsl("/assets/adcom.bks", new String(SDKConfig.KEY)).setUserNameAndPassword(ProductInfo.getInstance().productId + MqttTopic.TOPIC_LEVEL_SEPARATOR + RegisterInfo.getInstance().deviceId, RegisterInfo.getInstance().deviceSecret), OtaListener.getInstance().setAction(OtaListener.Action.CONNECT));
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d() {
        a(String.format("product/%s/%s/login/response", ProductInfo.getInstance().productId, RegisterInfo.getInstance().deviceId), 1, OtaListener.getInstance().setAction(OtaListener.Action.SUB_LOGIN));
    }

    public void e() {
        a(String.format("product/%s/%s/logout/response", ProductInfo.getInstance().productId, RegisterInfo.getInstance().deviceId), 1, OtaListener.getInstance().setAction(OtaListener.Action.SUB_LOGOUT));
    }

    public void f() {
        a(String.format("product/%s/%s/notify", ProductInfo.getInstance().productId, RegisterInfo.getInstance().deviceId), 1, OtaListener.getInstance().setAction(OtaListener.Action.SUB_NOTIFY));
    }

    public String g() {
        StringBuffer stringBuffer = new StringBuffer();
        long a2 = j.a();
        stringBuffer.append("D_");
        stringBuffer.append(RegisterInfo.getInstance().deviceId);
        stringBuffer.append(a2);
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            stringBuffer.append(random.nextInt(10));
        }
        return stringBuffer.toString();
    }

    public final String h() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("type", 2);
            jSONObject.put("seqno", g());
            jSONObject.put("body", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public void b(ILoginCallback iLoginCallback) {
        Trace.d("OtaTools", "login() start");
        if (!DeviceInfo.getInstance().isValid()) {
            Trace.e("OtaTools", "login() device info is null");
            return;
        }
        a(iLoginCallback);
        String format = String.format("product/%s/%s/login", ProductInfo.getInstance().productId, RegisterInfo.getInstance().deviceId);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        String valueOf = String.valueOf(j.a());
        String g = g();
        try {
            jSONObject.put("timestamp", valueOf);
            jSONObject.put("sign", ProductInfo.getInstance().productId + valueOf);
            jSONObject2.put("body", jSONObject);
            jSONObject2.put("seqno", g);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a(format, jSONObject2.toString(), 1, false, OtaListener.getInstance().setAction(OtaListener.Action.PUB_LOGIN));
        this.f1886a.put(1000, g);
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public boolean a(int i, String str) {
        if (TextUtils.equals(str, this.f1886a.get(Integer.valueOf(i)))) {
            this.f1886a.remove(Integer.valueOf(i));
            return true;
        }
        return false;
    }

    public void a(IMqttActionListener iMqttActionListener) {
        try {
            MqttManager.getInstance().disConnect(new DisconnectCommand(), iMqttActionListener);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void a(ILoginCallback iLoginCallback) {
        MessageListener messageListener = this.b;
        if (messageListener == null) {
            Trace.e("OtaTools", "setLoginListener() messageListener is null");
        } else {
            messageListener.setLoginListener(iLoginCallback);
        }
    }

    public void a(ILogoutCallback iLogoutCallback) {
        MessageListener messageListener = this.b;
        if (messageListener == null) {
            Trace.e("OtaTools", "setLoginListener() messageListener is null");
        } else {
            messageListener.setLogoutListener(iLogoutCallback);
        }
    }

    public void a(IReportDeviceStatusCallback iReportDeviceStatusCallback) {
        MessageListener messageListener = this.b;
        if (messageListener == null) {
            Trace.e("OtaTools", "setReportDeviceStatusListener() messageListener is null");
        } else {
            messageListener.setReportDeviceStatusListener(iReportDeviceStatusCallback);
        }
    }

    public void a(String str, IReportDeviceStatusCallback iReportDeviceStatusCallback) {
        Trace.d("OtaTools", "reportDeviceInfo() start.");
        a(iReportDeviceStatusCallback);
        String g = g();
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("firewareVersion", DeviceInfo.getInstance().version);
            jSONObject3.put("reported", jSONObject);
            jSONObject2.put("seqno", g);
            jSONObject2.put("body", jSONObject3);
            a(String.format("product/%s/%s/shadow/update", ProductInfo.getInstance().productId, RegisterInfo.getInstance().deviceId), jSONObject2.toString(), 1, false, OtaListener.getInstance().setAction(OtaListener.Action.PUB_REPORT_DEVICEINFO));
            this.f1886a.put(1002, g);
        } catch (JSONException e) {
            e.printStackTrace();
            Trace.e("OtaTools", "reportDeviceInfo() JsonException:" + e.toString());
            Trace.e("OtaTools", "reportDeviceInfo() 请确认传入的消息符合json格式");
        }
    }

    public void a(boolean z, ILogoutCallback iLogoutCallback) {
        Trace.d("OtaTools", "logout() start.");
        a(iLogoutCallback);
        String format = String.format("product/%s/%s/logout", ProductInfo.getInstance().productId, RegisterInfo.getInstance().deviceId);
        JSONObject jSONObject = new JSONObject();
        String g = g();
        try {
            jSONObject.put("seqno", g);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", z ? 1 : 2);
            jSONObject.put("body", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a(format, jSONObject.toString(), 1, false, OtaListener.getInstance().setAction(OtaListener.Action.PUB_LOGOUT));
        if (z) {
            this.f1886a.put(1001, g);
        }
    }

    public void a(String str, String str2, int i, boolean z, IMqttActionListener iMqttActionListener) {
        Trace.d("OtaTools", "pub() topic:" + str + "\nmessage:" + str2);
        try {
            MqttManager.getInstance().pub(new PubCommand().setTopic(str).setQos(i).setMessage(str2).setRetained(z), iMqttActionListener);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void a(String str, int i, IMqttActionListener iMqttActionListener) {
        try {
            MqttManager.getInstance().sub(new SubCommand().setQos(i).setTopic(str), iMqttActionListener);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
