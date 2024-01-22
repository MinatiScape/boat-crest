package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.ClientState;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class MqttInputStream extends InputStream {
    public final String h;
    public final Logger i;
    public ClientState j;
    public DataInputStream k;
    public ByteArrayOutputStream l;
    public int m;
    public int n;
    public byte[] o;

    public MqttInputStream(ClientState clientState, InputStream inputStream) {
        String name = MqttInputStream.class.getName();
        this.h = name;
        this.i = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
        this.j = null;
        this.j = clientState;
        this.k = new DataInputStream(inputStream);
        this.l = new ByteArrayOutputStream();
        this.m = -1;
    }

    public final void a() throws IOException {
        int size = this.l.size();
        int i = this.n;
        int i2 = size + i;
        int i3 = this.m - i;
        if (i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i4 = 0;
        while (i4 < i3) {
            try {
                int read = this.k.read(this.o, i2 + i4, i3 - i4);
                if (read >= 0) {
                    this.j.notifyReceivedBytes(read);
                    i4 += read;
                } else {
                    throw new EOFException();
                }
            } catch (SocketTimeoutException e) {
                this.n += i4;
                throw e;
            }
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.k.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.k.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.k.read();
    }

    public MqttWireMessage readMqttWireMessage() throws IOException, MqttException {
        try {
            if (this.m < 0) {
                this.l.reset();
                byte readByte = this.k.readByte();
                this.j.notifyReceivedBytes(1);
                byte b = (byte) ((readByte >>> 4) & 15);
                if (b >= 1 && b <= 14) {
                    this.m = MqttWireMessage.readMBI(this.k).getValue();
                    this.l.write(readByte);
                    this.l.write(MqttWireMessage.encodeMBI(this.m));
                    this.o = new byte[this.l.size() + this.m];
                    this.n = 0;
                } else {
                    throw ExceptionHelper.createMqttException(32108);
                }
            }
            if (this.m >= 0) {
                a();
                this.m = -1;
                byte[] byteArray = this.l.toByteArray();
                System.arraycopy(byteArray, 0, this.o, 0, byteArray.length);
                MqttWireMessage createWireMessage = MqttWireMessage.createWireMessage(this.o);
                this.i.fine(this.h, "readMqttWireMessage", "301", new Object[]{createWireMessage});
                return createWireMessage;
            }
            return null;
        } catch (SocketTimeoutException unused) {
            return null;
        }
    }
}
