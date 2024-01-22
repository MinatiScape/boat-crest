package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
/* loaded from: classes13.dex */
public interface IDiscardedBufferMessageCallback {
    void messageDiscarded(MqttWireMessage mqttWireMessage);
}
