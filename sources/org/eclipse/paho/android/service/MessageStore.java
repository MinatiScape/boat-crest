package org.eclipse.paho.android.service;

import java.util.Iterator;
import org.eclipse.paho.client.mqttv3.MqttMessage;
/* loaded from: classes13.dex */
public interface MessageStore {

    /* loaded from: classes13.dex */
    public interface StoredMessage {
        String getClientHandle();

        MqttMessage getMessage();

        String getMessageId();

        String getTopic();
    }

    void clearArrivedMessages(String str);

    void close();

    boolean discardArrived(String str, String str2);

    Iterator<StoredMessage> getAllArrivedMessages(String str);

    String storeArrived(String str, String str2, MqttMessage mqttMessage);
}
