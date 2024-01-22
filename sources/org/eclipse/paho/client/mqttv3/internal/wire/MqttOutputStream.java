package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.ClientState;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class MqttOutputStream extends OutputStream {
    public static final String k = MqttOutputStream.class.getName();
    public Logger h = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, k);
    public ClientState i;
    public BufferedOutputStream j;

    public MqttOutputStream(ClientState clientState, OutputStream outputStream) {
        this.i = null;
        this.i = clientState;
        this.j = new BufferedOutputStream(outputStream);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.j.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.j.flush();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.j.write(bArr);
        this.i.notifySentBytes(bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.j.write(bArr, i, i2);
        this.i.notifySentBytes(i2);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.j.write(i);
    }

    public void write(MqttWireMessage mqttWireMessage) throws IOException, MqttException {
        byte[] header = mqttWireMessage.getHeader();
        byte[] payload = mqttWireMessage.getPayload();
        this.j.write(header, 0, header.length);
        this.i.notifySentBytes(header.length);
        int i = 0;
        while (i < payload.length) {
            int min = Math.min(1024, payload.length - i);
            this.j.write(payload, i, min);
            i += 1024;
            this.i.notifySentBytes(min);
        }
        this.h.fine(k, "write", "529", new Object[]{mqttWireMessage});
    }
}
