package com.abupdate.iot_libs.inter;

import android.content.Context;
import android.content.Intent;
import com.abupdate.iot_libs.OtaAgentPolicy;
import com.abupdate.iot_libs.a.b;
import com.abupdate.iot_libs.constant.BroadcastConsts;
import com.abupdate.iot_libs.info.PushMessageInfo;
import com.abupdate.iot_libs.report.ReportManager;
import com.abupdate.iot_libs.service.OtaService;
import com.abupdate.iot_libs.utils.e;
import com.abupdate.iot_libs.utils.i;
import com.abupdate.mqtt_libs.mqttv3.IMqttDeliveryToken;
import com.abupdate.mqtt_libs.mqttv3.MqttCallback;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.MqttMessage;
import com.abupdate.trace.Trace;
import com.realsil.sdk.dfu.DfuConstants;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class MessageListener implements MqttCallback {
    public static final int DISCONNECTED_OK = -1;
    public static MessageListener h;
    public ILoginCallback b;
    public ILogoutCallback c;
    public IReportDeviceStatusCallback d;
    public ConnectionLostListener f;
    public boolean e = true;
    public b g = b.Null;

    /* renamed from: a  reason: collision with root package name */
    public Context f1901a = OtaAgentPolicy.sCx;

    /* loaded from: classes.dex */
    public interface ConnectionLostListener {
        void onConnectLost(int i);
    }

    /* loaded from: classes.dex */
    public class a implements TimeoutCallback {
        public a() {
        }

        @Override // com.abupdate.iot_libs.inter.TimeoutCallback
        public void onTimeout() {
            if (MessageListener.this.e) {
                if (MessageListener.this.g == b.Login) {
                    MessageListener.this.b.onLoginTimeout();
                }
                if (MessageListener.this.g == b.Logout) {
                    MessageListener.this.c.onLogoutTimeout();
                }
                if (MessageListener.this.g == b.ReportDeviceInfo) {
                    MessageListener.this.d.onReportFail(DfuConstants.MAX_CONNECTION_LOCK_TIMEOUT);
                }
                MessageListener.this.h(b.Null);
            }
        }
    }

    /* loaded from: classes.dex */
    public enum b {
        Null,
        Login,
        Logout,
        ReportDeviceInfo
    }

    public static MessageListener getInstance() {
        if (h == null) {
            synchronized (MessageListener.class) {
                if (h == null) {
                    h = new MessageListener();
                }
            }
        }
        return h;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.MqttCallback
    public void connectionLost(Throwable th) {
        int i;
        Trace.d("MessageListener", "connectionLost() ");
        if (this.f != null) {
            if (th == null || !(th instanceof MqttException)) {
                i = th == null ? -1 : 6;
            } else {
                i = ((MqttException) th).getReasonCode();
            }
            this.f.onConnectLost(i);
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.MqttCallback
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }

    public final String g(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return jSONObject.has("replyno") ? jSONObject.getString("replyno") : "";
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public final void h(b bVar) {
        this.e = true;
        this.g = bVar;
    }

    public final void i() {
        synchronized (MessageListener.class) {
            i.a().a(new a());
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.MqttCallback
    public void messageArrived(String str, MqttMessage mqttMessage) throws Exception {
        String str2 = new String(mqttMessage.getPayload());
        Trace.d("MessageListener", "messageArrived() :" + str);
        Trace.d("MessageListener", "messageArrived() :" + str2);
        if (this.g == b.Null && !str.endsWith("notify")) {
            Trace.d("MessageListener", "messageArrived() state is null");
        } else if (str.endsWith("login/response")) {
            if (com.abupdate.iot_libs.a.b.a().a(1000, g(str2))) {
                this.e = false;
                int b2 = e.b(str2);
                if (e.b(b2)) {
                    Trace.d("MessageListener", "messageArrived() login response success!");
                    this.b.onLoginSuccess();
                    com.abupdate.iot_libs.a.b.a().a(b.a.Login);
                    return;
                }
                Trace.d("MessageListener", "messageArrived() login response failed!");
                this.b.onLoginFail(b2);
            }
        } else if (str.endsWith("logout/response")) {
            if (com.abupdate.iot_libs.a.b.a().a(1001, g(str2))) {
                Trace.d("MessageListener", "messageArrived() logout response success!");
                this.e = false;
                int b3 = e.b(str2);
                if (e.b(b3)) {
                    this.c.onLogoutSuccess();
                    com.abupdate.iot_libs.a.b.a().a(b.a.Logout);
                    return;
                }
                this.c.onLogoutFail(b3);
            }
        } else if (str.endsWith("update/response")) {
            if (com.abupdate.iot_libs.a.b.a().a(1002, g(str2))) {
                Trace.d("MessageListener", "messageArrived() report device info success");
                this.e = false;
                int b4 = e.b(str2);
                if (e.b(b4)) {
                    this.d.onReportSuccess();
                } else {
                    this.d.onReportFail(b4);
                }
            }
        } else if (str.endsWith("notify")) {
            JSONObject jSONObject = new JSONObject(str2);
            if (jSONObject.has("body")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("body");
                if (jSONObject2.has("content")) {
                    String string = jSONObject2.getString("content");
                    Intent intent = new Intent();
                    intent.setAction(BroadcastConsts.ACTION_FOTA_NOTIFY);
                    intent.putExtra(BroadcastConsts.KEY_FOTA_NOTIFY, string);
                    this.f1901a.sendBroadcast(intent, "permissions.com.abupdate.fota.update.smawatch");
                }
                if (jSONObject2.has("msgId")) {
                    ReportManager.getInstance(this.f1901a).savePushResponseData(new PushMessageInfo(jSONObject2.getString("msgId")));
                    OtaService.startByAction(OtaService.ACTION_REPORT);
                }
            }
        }
    }

    public void setConnectionLostListener(ConnectionLostListener connectionLostListener) {
        this.f = connectionLostListener;
    }

    public void setLoginListener(ILoginCallback iLoginCallback) {
        this.b = iLoginCallback;
        h(b.Login);
        i();
    }

    public void setLogoutListener(ILogoutCallback iLogoutCallback) {
        this.c = iLogoutCallback;
        h(b.Logout);
        i();
    }

    public void setReportDeviceStatusListener(IReportDeviceStatusCallback iReportDeviceStatusCallback) {
        this.d = iReportDeviceStatusCallback;
        h(b.ReportDeviceInfo);
        i();
    }
}
