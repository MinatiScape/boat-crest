package org.eclipse.paho.client.mqttv3.internal;

import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.BufferedMessage;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class DisconnectedMessageBuffer implements Runnable {
    public final String h;
    public Logger i;
    public DisconnectedBufferOptions j;
    public ArrayList<BufferedMessage> k;
    public final Object l;
    public IDisconnectedBufferCallback m;
    public IDiscardedBufferMessageCallback n;

    public DisconnectedMessageBuffer(DisconnectedBufferOptions disconnectedBufferOptions) {
        String name = DisconnectedMessageBuffer.class.getName();
        this.h = name;
        this.i = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
        this.l = new Object();
        this.j = disconnectedBufferOptions;
        this.k = new ArrayList<>();
    }

    public void deleteMessage(int i) {
        synchronized (this.l) {
            this.k.remove(i);
        }
    }

    public BufferedMessage getMessage(int i) {
        BufferedMessage bufferedMessage;
        synchronized (this.l) {
            bufferedMessage = this.k.get(i);
        }
        return bufferedMessage;
    }

    public int getMessageCount() {
        int size;
        synchronized (this.l) {
            size = this.k.size();
        }
        return size;
    }

    public boolean isPersistBuffer() {
        return this.j.isPersistBuffer();
    }

    public void putMessage(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        if (mqttToken != null) {
            mqttWireMessage.setToken(mqttToken);
            mqttToken.internalTok.setMessageID(mqttWireMessage.getMessageId());
        }
        BufferedMessage bufferedMessage = new BufferedMessage(mqttWireMessage, mqttToken);
        synchronized (this.l) {
            if (this.k.size() < this.j.getBufferSize()) {
                this.k.add(bufferedMessage);
            } else if (this.j.isDeleteOldestMessages()) {
                if (this.n != null) {
                    this.n.messageDiscarded(this.k.get(0).getMessage());
                }
                this.k.remove(0);
                this.k.add(bufferedMessage);
            } else {
                throw new MqttException(32203);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        this.i.fine(this.h, "run", "516");
        while (getMessageCount() > 0) {
            try {
                this.m.publishBufferedMessage(getMessage(0));
                deleteMessage(0);
            } catch (MqttException e) {
                if (e.getReasonCode() == 32202) {
                    try {
                        Thread.sleep(100L);
                    } catch (Exception unused) {
                    }
                } else {
                    this.i.severe(this.h, "run", "519", new Object[]{Integer.valueOf(e.getReasonCode()), e.getMessage()});
                    return;
                }
            }
        }
    }

    public void setMessageDiscardedCallBack(IDiscardedBufferMessageCallback iDiscardedBufferMessageCallback) {
        this.n = iDiscardedBufferMessageCallback;
    }

    public void setPublishCallback(IDisconnectedBufferCallback iDisconnectedBufferCallback) {
        this.m = iDisconnectedBufferCallback;
    }
}
