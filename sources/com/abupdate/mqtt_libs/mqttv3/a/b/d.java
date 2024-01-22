package com.abupdate.mqtt_libs.mqttv3.a.b;

import com.htsmart.wristband2.WristbandManager;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public byte f1948a;
    public boolean b;
    public byte[] c;
    public boolean d;

    public d(byte b, boolean z, byte[] bArr) {
        this.d = false;
        this.f1948a = b;
        this.b = z;
        this.c = bArr;
    }

    public static byte[] d() {
        SecureRandom secureRandom = new SecureRandom();
        return new byte[]{(byte) secureRandom.nextInt(255), (byte) secureRandom.nextInt(255), (byte) secureRandom.nextInt(255), (byte) secureRandom.nextInt(255)};
    }

    public byte[] a() {
        return this.c;
    }

    public boolean b() {
        return this.d;
    }

    public byte[] c() {
        byte[] bArr = this.c;
        int length = bArr.length + 6;
        if (bArr.length > 65535) {
            length += 8;
        } else if (bArr.length >= 126) {
            length += 2;
        }
        ByteBuffer allocate = ByteBuffer.allocate(length);
        a(allocate, this.f1948a, this.b);
        byte[] d = d();
        a(allocate, this.c.length, d);
        int i = 0;
        while (true) {
            byte[] bArr2 = this.c;
            if (i < bArr2.length) {
                byte b = (byte) (bArr2[i] ^ d[i % 4]);
                bArr2[i] = b;
                allocate.put(b);
                i++;
            } else {
                allocate.flip();
                return allocate.array();
            }
        }
    }

    public static void b(ByteBuffer byteBuffer, int i, boolean z) {
        if (i < 0) {
            throw new IllegalArgumentException("Length cannot be negative");
        }
        int i2 = z ? WristbandManager.SYNC_STATE_FAILED_UNKNOWN : 0;
        if (i <= 65535) {
            if (i >= 126) {
                byteBuffer.put((byte) (i2 | 126));
                byteBuffer.put((byte) (i >> 8));
                byteBuffer.put((byte) (i & 255));
                return;
            }
            byteBuffer.put((byte) (i | i2));
            return;
        }
        byteBuffer.put((byte) (i2 | 127));
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) ((i >> 24) & 255));
        byteBuffer.put((byte) ((i >> 16) & 255));
        byteBuffer.put((byte) ((i >> 8) & 255));
        byteBuffer.put((byte) (i & 255));
    }

    public final void a(byte b) {
        this.b = (b & 128) != 0;
        this.f1948a = (byte) (b & 15);
    }

    public static void a(ByteBuffer byteBuffer, int i, byte[] bArr) {
        if (bArr != null) {
            b(byteBuffer, i, true);
            byteBuffer.put(bArr);
            return;
        }
        b(byteBuffer, i, false);
    }

    public d(InputStream inputStream) throws IOException {
        int i = 0;
        this.d = false;
        a((byte) inputStream.read());
        byte b = this.f1948a;
        int i2 = 2;
        if (b != 2) {
            if (b == 8) {
                this.d = true;
                return;
            }
            throw new IOException("Invalid Frame: Opcode: " + ((int) this.f1948a));
        }
        byte read = (byte) inputStream.read();
        boolean z = (read & 128) != 0;
        int i3 = (byte) (read & Byte.MAX_VALUE);
        if (i3 == 127) {
            i2 = 8;
        } else if (i3 != 126) {
            i2 = 0;
        }
        i3 = i2 > 0 ? 0 : i3;
        while (true) {
            i2--;
            if (i2 < 0) {
                break;
            }
            i3 |= (((byte) inputStream.read()) & 255) << (i2 * 8);
        }
        byte[] bArr = null;
        if (z) {
            byte[] bArr2 = new byte[4];
            inputStream.read(bArr2, 0, 4);
            bArr = bArr2;
        }
        this.c = new byte[i3];
        int i4 = 0;
        int i5 = i3;
        while (i4 != i3) {
            int read2 = inputStream.read(this.c, i4, i5);
            i4 += read2;
            i5 -= read2;
        }
        if (!z) {
            return;
        }
        while (true) {
            byte[] bArr3 = this.c;
            if (i >= bArr3.length) {
                return;
            }
            bArr3[i] = (byte) (bArr3[i] ^ bArr[i % 4]);
            i++;
        }
    }

    public static void a(ByteBuffer byteBuffer, byte b, boolean z) {
        byteBuffer.put((byte) ((b & 15) | (z ? (byte) 128 : (byte) 0)));
    }
}
