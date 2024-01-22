package com.abupdate.mqtt_libs.mqttv3.a.c;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
/* loaded from: classes.dex */
public abstract class u {
    public static final String[] d = {"reserved", "CONNECT", "CONNACK", "PUBLISH", "PUBACK", "PUBREC", "PUBREL", "PUBCOMP", "SUBSCRIBE", "SUBACK", "UNSUBSCRIBE", "UNSUBACK", "PINGREQ", "PINGRESP", "DISCONNECT"};
    public byte c;
    public boolean b = false;

    /* renamed from: a  reason: collision with root package name */
    public int f1951a = 0;

    public u(byte b) {
        this.c = b;
    }

    public static u b(InputStream inputStream) throws MqttException {
        try {
            a aVar = new a(inputStream);
            DataInputStream dataInputStream = new DataInputStream(aVar);
            int readUnsignedByte = dataInputStream.readUnsignedByte();
            byte b = (byte) (readUnsignedByte >> 4);
            byte b2 = (byte) (readUnsignedByte & 15);
            long a2 = (aVar.a() + a(dataInputStream).a()) - aVar.a();
            byte[] bArr = new byte[0];
            if (a2 > 0) {
                int i = (int) a2;
                byte[] bArr2 = new byte[i];
                dataInputStream.readFully(bArr2, 0, i);
                bArr = bArr2;
            }
            if (b == 1) {
                return new d(b2, bArr);
            }
            if (b == 3) {
                return new o(b2, bArr);
            }
            if (b == 4) {
                return new k(b2, bArr);
            }
            if (b == 7) {
                return new l(b2, bArr);
            }
            if (b == 2) {
                return new c(b2, bArr);
            }
            if (b == 12) {
                return new i(b2, bArr);
            }
            if (b == 13) {
                return new j(b2, bArr);
            }
            if (b == 8) {
                return new r(b2, bArr);
            }
            if (b == 9) {
                return new q(b2, bArr);
            }
            if (b == 10) {
                return new t(b2, bArr);
            }
            if (b == 11) {
                return new s(b2, bArr);
            }
            if (b == 6) {
                return new n(b2, bArr);
            }
            if (b == 5) {
                return new m(b2, bArr);
            }
            if (b == 14) {
                return new e(b2, bArr);
            }
            throw com.abupdate.mqtt_libs.mqttv3.a.i.a(6);
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    public void a(int i) {
        this.f1951a = i;
    }

    public abstract byte c_();

    public byte[] d_() throws MqttException {
        return new byte[0];
    }

    public String e() {
        return new Integer(j()).toString();
    }

    public abstract byte[] e_() throws MqttException;

    public boolean f_() {
        return true;
    }

    public byte i() {
        return this.c;
    }

    public int j() {
        return this.f1951a;
    }

    public byte[] k() throws MqttException {
        try {
            int i = ((i() & 15) << 4) ^ (c_() & 15);
            byte[] e_ = e_();
            int length = e_.length + d_().length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeByte(i);
            dataOutputStream.write(a(length));
            dataOutputStream.write(e_);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    public byte[] l() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeShort(this.f1951a);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    public String toString() {
        return d[this.c];
    }

    public static u a(com.abupdate.mqtt_libs.mqttv3.j jVar) throws MqttException {
        byte[] d2 = jVar.d();
        if (d2 == null) {
            d2 = new byte[0];
        }
        return b(new v(jVar.a(), jVar.c(), jVar.b(), d2, jVar.f(), jVar.b_()));
    }

    public static u a(byte[] bArr) throws MqttException {
        return b(new ByteArrayInputStream(bArr));
    }

    public static byte[] a(long j) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        do {
            byte b = (byte) (j % 128);
            j /= 128;
            int i2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i2 > 0) {
                b = (byte) (b | 128);
            }
            byteArrayOutputStream.write(b);
            i++;
            if (i2 <= 0) {
                break;
            }
        } while (i < 4);
        return byteArrayOutputStream.toByteArray();
    }

    public static w a(DataInputStream dataInputStream) throws IOException {
        byte readByte;
        long j = 0;
        int i = 0;
        int i2 = 1;
        do {
            i++;
            j += (readByte & Byte.MAX_VALUE) * i2;
            i2 *= 128;
        } while ((dataInputStream.readByte() & 128) != 0);
        return new w(j, i);
    }

    public void a(boolean z) {
        this.b = z;
    }

    public void a(DataOutputStream dataOutputStream, String str) throws MqttException {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            dataOutputStream.write((byte) ((bytes.length >>> 8) & 255));
            dataOutputStream.write((byte) ((bytes.length >>> 0) & 255));
            dataOutputStream.write(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new MqttException(e);
        } catch (IOException e2) {
            throw new MqttException(e2);
        }
    }

    public String b(DataInputStream dataInputStream) throws MqttException {
        try {
            byte[] bArr = new byte[dataInputStream.readUnsignedShort()];
            dataInputStream.readFully(bArr);
            return new String(bArr, "UTF-8");
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }
}
