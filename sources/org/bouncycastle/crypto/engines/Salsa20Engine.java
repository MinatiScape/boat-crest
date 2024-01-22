package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.MaxBytesExceededException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.SkippingStreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Strings;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes12.dex */
public class Salsa20Engine implements SkippingStreamCipher {
    public static final int DEFAULT_ROUNDS = 20;
    public static final int[] g = Pack.littleEndianToInt(Strings.toByteArray("expand 16-byte kexpand 32-byte k"), 0, 8);
    public static final byte[] sigma = Strings.toByteArray("expand 32-byte k");
    public static final byte[] tau = Strings.toByteArray("expand 16-byte k");

    /* renamed from: a  reason: collision with root package name */
    public int f14707a;
    public byte[] b;
    public boolean c;
    public int d;
    public int e;
    public int[] engineState;
    public int f;
    public int rounds;
    public int[] x;

    public Salsa20Engine() {
        this(20);
    }

    public Salsa20Engine(int i) {
        this.f14707a = 0;
        this.engineState = new int[16];
        this.x = new int[16];
        this.b = new byte[64];
        this.c = false;
        if (i <= 0 || (i & 1) != 0) {
            throw new IllegalArgumentException("'rounds' must be a positive, even number");
        }
        this.rounds = i;
    }

    public static int rotl(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    public static void salsaCore(int i, int[] iArr, int[] iArr2) {
        if (iArr.length != 16) {
            throw new IllegalArgumentException();
        }
        if (iArr2.length != 16) {
            throw new IllegalArgumentException();
        }
        if (i % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        char c = 0;
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int i6 = iArr[4];
        int i7 = iArr[5];
        int i8 = iArr[6];
        int i9 = 7;
        int i10 = iArr[7];
        int i11 = iArr[8];
        int i12 = 9;
        int i13 = iArr[9];
        int i14 = iArr[10];
        int i15 = iArr[11];
        int i16 = iArr[12];
        int i17 = 13;
        int i18 = iArr[13];
        int i19 = iArr[14];
        int i20 = iArr[15];
        int i21 = i19;
        int i22 = i18;
        int i23 = i16;
        int i24 = i15;
        int i25 = i14;
        int i26 = i13;
        int i27 = i11;
        int i28 = i10;
        int i29 = i8;
        int i30 = i7;
        int i31 = i6;
        int i32 = i5;
        int i33 = i4;
        int i34 = i3;
        int i35 = i2;
        int i36 = i;
        while (i36 > 0) {
            int rotl = rotl(i35 + i23, i9) ^ i31;
            int rotl2 = i27 ^ rotl(rotl + i35, i12);
            int rotl3 = i23 ^ rotl(rotl2 + rotl, i17);
            int rotl4 = rotl(rotl3 + rotl2, 18) ^ i35;
            int rotl5 = i26 ^ rotl(i30 + i34, i9);
            int rotl6 = i22 ^ rotl(rotl5 + i30, i12);
            int rotl7 = i34 ^ rotl(rotl6 + rotl5, i17);
            int rotl8 = rotl(rotl7 + rotl6, 18) ^ i30;
            int rotl9 = i21 ^ rotl(i25 + i29, 7);
            int rotl10 = i33 ^ rotl(rotl9 + i25, 9);
            int rotl11 = i29 ^ rotl(rotl10 + rotl9, 13);
            int rotl12 = i25 ^ rotl(rotl11 + rotl10, 18);
            int rotl13 = i32 ^ rotl(i20 + i24, 7);
            int rotl14 = i28 ^ rotl(rotl13 + i20, 9);
            int i37 = i36;
            int rotl15 = i24 ^ rotl(rotl14 + rotl13, 13);
            int rotl16 = i20 ^ rotl(rotl15 + rotl14, 18);
            i34 = rotl7 ^ rotl(rotl4 + rotl13, 7);
            i33 = rotl10 ^ rotl(i34 + rotl4, 9);
            int rotl17 = rotl13 ^ rotl(i33 + i34, 13);
            int rotl18 = rotl4 ^ rotl(rotl17 + i33, 18);
            i29 = rotl11 ^ rotl(rotl8 + rotl, 7);
            i28 = rotl14 ^ rotl(i29 + rotl8, 9);
            int rotl19 = rotl(i28 + i29, 13) ^ rotl;
            i30 = rotl8 ^ rotl(rotl19 + i28, 18);
            i24 = rotl15 ^ rotl(rotl12 + rotl5, 7);
            int rotl20 = rotl(i24 + rotl12, 9) ^ rotl2;
            i26 = rotl5 ^ rotl(rotl20 + i24, 13);
            i25 = rotl12 ^ rotl(i26 + rotl20, 18);
            i23 = rotl3 ^ rotl(rotl16 + rotl9, 7);
            i22 = rotl6 ^ rotl(i23 + rotl16, 9);
            i21 = rotl9 ^ rotl(i22 + i23, 13);
            i20 = rotl16 ^ rotl(i21 + i22, 18);
            i32 = rotl17;
            i27 = rotl20;
            i35 = rotl18;
            i31 = rotl19;
            c = 0;
            i17 = 13;
            i12 = 9;
            i9 = 7;
            i36 = i37 - 2;
        }
        char c2 = c;
        iArr2[c2] = i35 + iArr[c2];
        iArr2[1] = i34 + iArr[1];
        iArr2[2] = i33 + iArr[2];
        iArr2[3] = i32 + iArr[3];
        iArr2[4] = i31 + iArr[4];
        iArr2[5] = i30 + iArr[5];
        iArr2[6] = i29 + iArr[6];
        iArr2[7] = i28 + iArr[7];
        iArr2[8] = i27 + iArr[8];
        iArr2[9] = i26 + iArr[9];
        iArr2[10] = i25 + iArr[10];
        iArr2[11] = i24 + iArr[11];
        iArr2[12] = i23 + iArr[12];
        iArr2[13] = i22 + iArr[13];
        iArr2[14] = i21 + iArr[14];
        iArr2[15] = i20 + iArr[15];
    }

    public final boolean a() {
        int i = this.d + 1;
        this.d = i;
        if (i == 0) {
            int i2 = this.e + 1;
            this.e = i2;
            if (i2 == 0) {
                int i3 = this.f + 1;
                this.f = i3;
                return (i3 & 32) != 0;
            }
        }
        return false;
    }

    public void advanceCounter() {
        int[] iArr = this.engineState;
        int i = iArr[8] + 1;
        iArr[8] = i;
        if (i == 0) {
            iArr[9] = iArr[9] + 1;
        }
    }

    public void advanceCounter(long j) {
        int i = (int) (j >>> 32);
        int i2 = (int) j;
        if (i > 0) {
            int[] iArr = this.engineState;
            iArr[9] = iArr[9] + i;
        }
        int[] iArr2 = this.engineState;
        int i3 = iArr2[8];
        iArr2[8] = iArr2[8] + i2;
        if (i3 == 0 || iArr2[8] >= i3) {
            return;
        }
        iArr2[9] = iArr2[9] + 1;
    }

    public final boolean b(int i) {
        int i2 = this.d + i;
        this.d = i2;
        if (i2 >= i || i2 < 0) {
            return false;
        }
        int i3 = this.e + 1;
        this.e = i3;
        if (i3 == 0) {
            int i4 = this.f + 1;
            this.f = i4;
            return (i4 & 32) != 0;
        }
        return false;
    }

    public final void c() {
        this.d = 0;
        this.e = 0;
        this.f = 0;
    }

    public void generateKeyStream(byte[] bArr) {
        salsaCore(this.rounds, this.engineState, this.x);
        Pack.intToLittleEndian(this.x, bArr, 0);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        if (this.rounds != 20) {
            return "Salsa20" + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.rounds;
        }
        return "Salsa20";
    }

    public long getCounter() {
        int[] iArr = this.engineState;
        return (iArr[9] << 32) | (iArr[8] & 4294967295L);
    }

    public int getNonceSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long getPosition() {
        return (getCounter() * 64) + this.f14707a;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException(getAlgorithmName() + " Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        if (iv == null || iv.length != getNonceSize()) {
            throw new IllegalArgumentException(getAlgorithmName() + " requires exactly " + getNonceSize() + " bytes of IV");
        }
        CipherParameters parameters = parametersWithIV.getParameters();
        if (parameters == null) {
            if (!this.c) {
                throw new IllegalStateException(getAlgorithmName() + " KeyParameter can not be null for first initialisation");
            }
            setKey(null, iv);
        } else if (!(parameters instanceof KeyParameter)) {
            throw new IllegalArgumentException(getAlgorithmName() + " Init parameters must contain a KeyParameter (or null for re-init)");
        } else {
            setKey(((KeyParameter) parameters).getKey(), iv);
        }
        reset();
        this.c = true;
    }

    public void packTauOrSigma(int i, int[] iArr, int i2) {
        int i3 = (i - 16) / 4;
        int[] iArr2 = g;
        iArr[i2] = iArr2[i3];
        iArr[i2 + 1] = iArr2[i3 + 1];
        iArr[i2 + 2] = iArr2[i3 + 2];
        iArr[i2 + 3] = iArr2[i3 + 3];
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (!this.c) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (i + i2 <= bArr.length) {
            if (i3 + i2 <= bArr2.length) {
                if (b(i2)) {
                    throw new MaxBytesExceededException("2^70 byte limit per IV would be exceeded; Change IV");
                }
                for (int i4 = 0; i4 < i2; i4++) {
                    byte[] bArr3 = this.b;
                    int i5 = this.f14707a;
                    bArr2[i4 + i3] = (byte) (bArr3[i5] ^ bArr[i4 + i]);
                    int i6 = (i5 + 1) & 63;
                    this.f14707a = i6;
                    if (i6 == 0) {
                        advanceCounter();
                        generateKeyStream(this.b);
                    }
                }
                return i2;
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        this.f14707a = 0;
        c();
        resetCounter();
        generateKeyStream(this.b);
    }

    public void resetCounter() {
        int[] iArr = this.engineState;
        iArr[9] = 0;
        iArr[8] = 0;
    }

    public void retreatCounter() {
        int[] iArr = this.engineState;
        if (iArr[8] == 0 && iArr[9] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        int i = iArr[8] - 1;
        iArr[8] = i;
        if (i == -1) {
            iArr[9] = iArr[9] - 1;
        }
    }

    public void retreatCounter(long j) {
        int i = (int) (j >>> 32);
        int i2 = (int) j;
        if (i != 0) {
            int[] iArr = this.engineState;
            if ((iArr[9] & 4294967295L) < (i & 4294967295L)) {
                throw new IllegalStateException("attempt to reduce counter past zero.");
            }
            iArr[9] = iArr[9] - i;
        }
        int[] iArr2 = this.engineState;
        if ((iArr2[8] & 4294967295L) >= (4294967295L & i2)) {
            iArr2[8] = iArr2[8] - i2;
        } else if (iArr2[9] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        } else {
            iArr2[9] = iArr2[9] - 1;
            iArr2[8] = iArr2[8] - i2;
        }
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        if (a()) {
            throw new MaxBytesExceededException("2^70 byte limit per IV; Change IV");
        }
        byte[] bArr = this.b;
        int i = this.f14707a;
        byte b2 = (byte) (b ^ bArr[i]);
        int i2 = (i + 1) & 63;
        this.f14707a = i2;
        if (i2 == 0) {
            advanceCounter();
            generateKeyStream(this.b);
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long seekTo(long j) {
        reset();
        return skip(j);
    }

    public void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            if (bArr.length != 16 && bArr.length != 32) {
                throw new IllegalArgumentException(getAlgorithmName() + " requires 128 bit or 256 bit key");
            }
            int length = (bArr.length - 16) / 4;
            int[] iArr = this.engineState;
            int[] iArr2 = g;
            iArr[0] = iArr2[length];
            iArr[5] = iArr2[length + 1];
            iArr[10] = iArr2[length + 2];
            iArr[15] = iArr2[length + 3];
            Pack.littleEndianToInt(bArr, 0, iArr, 1, 4);
            Pack.littleEndianToInt(bArr, bArr.length - 16, this.engineState, 11, 4);
        }
        Pack.littleEndianToInt(bArr2, 0, this.engineState, 6, 2);
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long skip(long j) {
        long j2;
        if (j >= 0) {
            if (j >= 64) {
                long j3 = j / 64;
                advanceCounter(j3);
                j2 = j - (j3 * 64);
            } else {
                j2 = j;
            }
            int i = this.f14707a;
            int i2 = (((int) j2) + i) & 63;
            this.f14707a = i2;
            if (i2 < i) {
                advanceCounter();
            }
        } else {
            long j4 = -j;
            if (j4 >= 64) {
                long j5 = j4 / 64;
                retreatCounter(j5);
                j4 -= j5 * 64;
            }
            for (long j6 = 0; j6 < j4; j6++) {
                if (this.f14707a == 0) {
                    retreatCounter();
                }
                this.f14707a = (this.f14707a - 1) & 63;
            }
        }
        generateKeyStream(this.b);
        return j;
    }
}
