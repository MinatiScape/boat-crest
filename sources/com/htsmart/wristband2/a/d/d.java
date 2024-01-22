package com.htsmart.wristband2.a.d;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.realsil.sdk.dfu.DfuConstants;
import com.realsil.sdk.dfu.image.constants.SubBinId;
/* loaded from: classes11.dex */
public class d {
    public static final int f = 0;
    public static final int g = 1;
    public static final int h = 2;
    public static final int[] j = {0, 49345, 49537, 320, 49921, 960, 640, 49729, 50689, 1728, 1920, 51009, 1280, 50625, 50305, 1088, 52225, 3264, 3456, 52545, 3840, 53185, 52865, 3648, 2560, 51905, 52097, 2880, 51457, 2496, 2176, 51265, 55297, 6336, 6528, 55617, 6912, 56257, 55937, 6720, 7680, 57025, 57217, 8000, 56577, com.veryfit.multi.nativeprotocol.b.d5, 7296, 56385, 5120, 54465, 54657, 5440, 55041, 6080, 5760, 54849, 53761, 4800, 4992, 54081, 4352, 53697, 53377, 4160, SubBinId.BB2.USER_DATA_1, 12480, 12672, 61761, 13056, 62401, 62081, 12864, 13824, 63169, 63361, 14144, 62721, 13760, 13440, 62529, 15360, 64705, 64897, 15680, 65281, 16320, 16000, 65089, 64001, 15040, 15232, 64321, 14592, 63937, 63617, 14400, 10240, 59585, 59777, 10560, 60161, 11200, 10880, 59969, 60929, 11968, 12160, 61249, 11520, 60865, 60545, 11328, 58369, 9408, 9600, 58689, 9984, 59329, 59009, 9792, com.veryfit.multi.nativeprotocol.b.r5, 58049, 58241, 9024, 57601, 8640, 8320, 57409, 40961, 24768, 24960, 41281, 25344, 41921, 41601, 25152, 26112, 42689, 42881, 26432, 42241, 26048, 25728, 42049, 27648, 44225, 44417, 27968, 44801, 28608, 28288, 44609, 43521, 27328, 27520, 43841, 26880, 43457, 43137, 26688, 30720, 47297, 47489, 31040, 47873, 31680, 31360, 47681, 48641, 32448, 32640, 48961, DfuConstants.MAX_CONNECTION_LOCK_TIMEOUT, 48577, 48257, 31808, 46081, 29888, 30080, 46401, 30464, 47041, 46721, 30272, 29184, 45761, 45953, 29504, 45313, 29120, 28800, 45121, 20480, 37057, 37249, 20800, 37633, 21440, 21120, 37441, 38401, 22208, 22400, 38721, 21760, 38337, 38017, 21568, 39937, 23744, 23936, 40257, 24320, 40897, 40577, 24128, 23040, 39617, 39809, 23360, 39169, 22976, 22656, 38977, 34817, 18624, 18816, 35137, 19200, 35777, 35457, 19008, 19968, 36545, 36737, 20288, 36097, 19904, 19584, 35905, 17408, 33985, 34177, 17728, 34561, 18368, 18048, 34369, 33281, 17088, 17280, 33601, 16640, 33217, 32897, 16448};

    /* renamed from: a  reason: collision with root package name */
    public boolean f11947a;
    public int b;
    public int c;
    public int d;
    public final byte[] e = new byte[504];
    public int i;

    /* loaded from: classes11.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f11948a;
        public final int b;

        public b(boolean z, int i) {
            this.f11948a = z;
            this.b = i;
        }

        public int a() {
            return this.b;
        }

        public boolean b() {
            return this.f11948a;
        }
    }

    public static int a(int i, byte b2) {
        return (i >> 8) ^ j[(b2 ^ i) & 255];
    }

    @Nullable
    public static b a(@NonNull byte[] bArr) {
        if (bArr.length != 8) {
            return null;
        }
        if (bArr[0] != -85) {
            return null;
        }
        if ((bArr[1] == 16 || bArr[1] == 48) && bArr[2] == 0 && bArr[3] == 0 && bArr[4] == 0 && bArr[5] == 0) {
            return new b((bArr[1] & 32) != 0, ((bArr[7] & 255) | (bArr[6] << 8)) & 65535);
        }
        return null;
    }

    @NonNull
    public static byte[] a(boolean z, int i) {
        return c(null, z, true, 0, i);
    }

    public static int b(byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = a(i2, bArr[i3]);
        }
        return i2;
    }

    @NonNull
    public static byte[] c(@NonNull byte[] bArr, int i) {
        return c(bArr, false, false, 0, i);
    }

    public static byte[] c(@Nullable byte[] bArr, boolean z, boolean z2, int i, int i2) {
        int i3;
        int i4;
        if (bArr != null) {
            i3 = bArr.length;
            i4 = b(bArr, i3);
        } else {
            i3 = 0;
            i4 = 0;
        }
        byte[] bArr2 = new byte[8];
        byte[] bArr3 = new byte[i3 + 8];
        bArr2[0] = -85;
        bArr2[1] = (byte) ((z2 ? z ? 48 : 16 : 0) | (i & 15));
        bArr2[2] = (byte) ((i3 >> 8) & 255);
        bArr2[3] = (byte) (i3 & 255);
        bArr2[4] = (byte) ((i4 >> 8) & 255);
        bArr2[5] = (byte) (i4 & 255);
        bArr2[6] = (byte) ((i2 >> 8) & 255);
        bArr2[7] = (byte) (i2 & 255);
        System.arraycopy(bArr2, 0, bArr3, 0, 8);
        if (i3 > 0) {
            System.arraycopy(bArr, 0, bArr3, 8, i3);
        }
        return bArr3;
    }

    public static boolean f(byte[] bArr, int i) {
        return b(bArr, bArr.length) == i;
    }

    public void a() {
        this.f11947a = false;
    }

    @Nullable
    public byte[] b() {
        int i = this.i;
        int i2 = this.b;
        if (i != i2) {
            Log.w("TransportPacket", "getRealPayload error:not integrated");
            return null;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.e, 0, bArr, 0, i2);
        return bArr;
    }

    public int c() {
        return this.d;
    }

    public int d(byte[] bArr) {
        return !this.f11947a ? g(bArr) : e(bArr);
    }

    public boolean d() {
        return this.f11947a;
    }

    public final int e(byte[] bArr) {
        int i = this.i;
        int length = bArr.length + i;
        if (length > 504 || length > this.b) {
            Log.w("TransportPacket", "parseData error:length");
            this.f11947a = false;
            return 2;
        }
        System.arraycopy(bArr, 0, this.e, i, bArr.length);
        this.i = length;
        if (length == this.b) {
            this.f11947a = false;
            return !f(b(), this.c) ? 2 : 1;
        }
        return 0;
    }

    public final int g(byte[] bArr) {
        String str;
        if (bArr.length < 8) {
            str = "parseHeader error:length";
        } else if (bArr[0] == -85) {
            boolean z = (bArr[1] & 16) != 0;
            this.b = ((bArr[2] << 8) | (bArr[3] & 255)) & 65535;
            this.c = ((bArr[4] << 8) | (bArr[5] & 255)) & 65535;
            this.d = ((bArr[6] << 8) | (bArr[7] & 255)) & 65535;
            this.i = 0;
            if (z) {
                throw new IllegalArgumentException("parseHeader error:isAck");
            }
            this.f11947a = true;
            int length = bArr.length - 8;
            if (length > 0) {
                byte[] bArr2 = new byte[length];
                System.arraycopy(bArr, 8, bArr2, 0, length);
                return e(bArr2);
            }
            return 0;
        } else {
            str = "parseHeader error:magic";
        }
        Log.w("TransportPacket", str);
        return 0;
    }
}
