package com.abupdate.mqtt_libs.mqttv3;

import com.abupdate.mqtt_libs.mqttv3.a.c.u;
/* loaded from: classes.dex */
public interface IMqttToken {
    IMqttActionListener getActionCallback();

    c getClient();

    MqttException getException();

    int[] getGrantedQos();

    int getMessageId();

    u getResponse();

    boolean getSessionPresent();

    String[] getTopics();

    Object getUserContext();

    boolean isComplete();

    void setActionCallback(IMqttActionListener iMqttActionListener);

    void setUserContext(Object obj);

    void waitForCompletion() throws MqttException;

    void waitForCompletion(long j) throws MqttException;
}
