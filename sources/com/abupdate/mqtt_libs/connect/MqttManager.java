package com.abupdate.mqtt_libs.connect;

import android.content.Context;
import com.abupdate.mqtt_libs.mqtt_service.MqttTraceHandler;
import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.MqttCallback;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.trace.Trace;
/* loaded from: classes.dex */
public class MqttManager {
    public static MqttManager e;
    public static Context sCx;

    /* renamed from: a  reason: collision with root package name */
    public ConnectCommand f1922a;
    public MqttCallback b;
    public MqttTraceHandler c;
    public boolean d;

    public static synchronized MqttManager getInstance() {
        MqttManager mqttManager;
        synchronized (MqttManager.class) {
            if (e == null) {
                synchronized (MqttManager.class) {
                    if (e == null) {
                        e = new MqttManager();
                    }
                }
            }
            mqttManager = e;
        }
        return mqttManager;
    }

    public void connect(ConnectCommand connectCommand, IMqttActionListener iMqttActionListener) throws MqttException {
        MqttCallback mqttCallback = this.b;
        if (mqttCallback != null) {
            connectCommand.setMessageListener(mqttCallback);
        }
        MqttTraceHandler mqttTraceHandler = this.c;
        if (mqttTraceHandler != null) {
            connectCommand.setTraceCallback(mqttTraceHandler);
        }
        connectCommand.setTraceEnabled(this.d);
        this.f1922a = connectCommand;
        connectCommand.execute(iMqttActionListener);
    }

    public void disConnect(DisconnectCommand disconnectCommand, IMqttActionListener iMqttActionListener) throws MqttException {
        disconnectCommand.execute(iMqttActionListener);
    }

    public ConnectCommand getConnect() {
        return this.f1922a;
    }

    public boolean isConneect() {
        if (getConnect() == null || getConnect().getClient() == null) {
            return false;
        }
        try {
            return getConnect().getClient().isConnected();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean isKeepConnect() {
        if (getConnect() == null || getConnect().getClient() == null) {
            return false;
        }
        return getConnect().getClient().isKeepConnect();
    }

    public void keepConnect(long j, long j2) {
        if (getConnect() == null || getConnect().getClient() == null) {
            return;
        }
        try {
            getConnect().getClient().startKeepConnect(j, j2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void pub(PubCommand pubCommand, IMqttActionListener iMqttActionListener) throws MqttException {
        pubCommand.execute(iMqttActionListener);
    }

    public void registerMessageListener(MqttCallback mqttCallback) {
        this.b = mqttCallback;
    }

    public void registerTraceListener(MqttTraceHandler mqttTraceHandler) {
        this.c = mqttTraceHandler;
    }

    public void setContext(Context context) {
        sCx = context;
    }

    public void setTraceEnable(boolean z) {
        this.d = z;
        if (this.f1922a == null) {
            return;
        }
        getConnect().setTraceEnabled(z);
    }

    public void stopKeepConnect() {
        if (isKeepConnect()) {
            Trace.d("ConnectManager", "stopKeepConnect() will stop keep-connect-service");
            getConnect().getClient().stopKeepConnect();
        }
    }

    public void sub(SubCommand subCommand, IMqttActionListener iMqttActionListener) throws MqttException {
        subCommand.execute(iMqttActionListener);
    }

    public void unSub(UnsubCommand unsubCommand, IMqttActionListener iMqttActionListener) throws MqttException {
        unsubCommand.execute(iMqttActionListener);
    }
}
