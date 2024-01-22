package com.abupdate.iot_libs;

import com.abupdate.iot_libs.a.b;
import com.abupdate.iot_libs.constant.OtaConstants;
import com.abupdate.iot_libs.info.DeviceInfo;
import com.abupdate.iot_libs.inter.IReportDeviceStatusCallback;
import com.abupdate.iot_libs.inter.IStatusListener;
import com.abupdate.iot_libs.inter.MessageListener;
import com.abupdate.iot_libs.inter.OtaListener;
import com.abupdate.iot_libs.service.OtaService;
import com.abupdate.iot_libs.utils.SPFTool;
import com.abupdate.mqtt_libs.connect.MqttManager;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.trace.Trace;
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes.dex */
public class MqttAgentPolicy {
    public static final String CONFIG_MQTT_CONNECT = "config_mqtt_connect";

    public static void connect() {
        Trace.d("MqttAgentPolicy", "%s%s%s", OtaConstants.SINGLE_LINE, MqttServiceConstants.CONNECT_ACTION, OtaConstants.SINGLE_LINE);
        SPFTool.putBoolean(CONFIG_MQTT_CONNECT, true);
        if (MqttManager.getInstance().isConneect()) {
            Trace.d("MqttAgentPolicy", "connect() is connected");
            OtaListener.getInstance().connect(new MqttException(new Throwable("is connected")));
        } else if (b.a().b() == b.a.Connecting) {
            Trace.d("MqttAgentPolicy", "connect() is connecting");
            OtaListener.getInstance().connect(new MqttException(new Throwable("is connecting")));
        } else if (!DeviceInfo.getInstance().isValid()) {
            Trace.d("MqttAgentPolicy", "connect() params is not valid");
        } else {
            OtaService.startByAction(OtaService.ACTION_CONNECT);
        }
    }

    public static void disConnect() {
        Trace.d("MqttAgentPolicy", "%s%s%s", OtaConstants.SINGLE_LINE, MqttServiceConstants.DISCONNECT_ACTION, OtaConstants.SINGLE_LINE);
        SPFTool.putBoolean(CONFIG_MQTT_CONNECT, false);
        OtaService.selfDisconnect();
    }

    public static void initMqtt() {
        MqttManager.getInstance().setContext(OtaAgentPolicy.sCx);
        MqttManager.getInstance().registerMessageListener(MessageListener.getInstance());
        MqttManager.getInstance().setTraceEnable(false);
    }

    public static boolean isConnected() {
        return MqttManager.getInstance().isConneect();
    }

    public static void registerStatusListener(IStatusListener iStatusListener) {
        OtaListener.getInstance().addListener(OtaListener.Action.CONNECT, iStatusListener);
    }

    public static void reportDeviceStatus(String str, IReportDeviceStatusCallback iReportDeviceStatusCallback) {
        if (!MqttManager.getInstance().isConneect()) {
            Trace.d("MqttAgentPolicy", "reportDeviceStatus() is disconnected");
        } else if (b.a().b() != b.a.Login) {
            Trace.e("MqttAgentPolicy", "reportDeviceStatus() device is off line");
        } else {
            b.a().a(str, iReportDeviceStatusCallback);
        }
    }

    public static void stopKeepConnect() {
        Trace.d("MqttAgentPolicy", "stopKeepConnect() start.");
        if (MqttManager.getInstance().isKeepConnect()) {
            OtaService.startByAction(OtaService.ACTION_DISCONNECT);
        } else {
            Trace.d("MqttAgentPolicy", "stopKeepConnect() is not config keep connect!");
        }
    }

    public static boolean unregisterStatusListener(IStatusListener iStatusListener) {
        return OtaListener.getInstance().removeListener(OtaListener.Action.CONNECT, iStatusListener);
    }
}
