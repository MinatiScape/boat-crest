package org.eclipse.paho.client.mqttv3.internal.wire;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;
/* loaded from: classes13.dex */
public class MqttSuback extends MqttAck {
    public int[] d;

    public MqttSuback(byte b, byte[] bArr) throws IOException {
        super((byte) 9);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.msgId = dataInputStream.readUnsignedShort();
        this.d = new int[bArr.length - 2];
        int i = 0;
        for (int read = dataInputStream.read(); read != -1; read = dataInputStream.read()) {
            this.d[i] = read;
            i++;
        }
        dataInputStream.close();
    }

    public int[] getGrantedQos() {
        return this.d;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public byte[] getVariableHeader() throws MqttException {
        return new byte[0];
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttAck, org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String toString() {
        int[] iArr;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append(" granted Qos");
        for (int i : this.d) {
            stringBuffer.append(HexStringBuilder.DEFAULT_SEPARATOR);
            stringBuffer.append(i);
        }
        return stringBuffer.toString();
    }
}
