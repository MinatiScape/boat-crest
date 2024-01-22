package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
/* loaded from: classes12.dex */
public class VMPCMac implements Mac {

    /* renamed from: a  reason: collision with root package name */
    public byte f14764a;
    public byte b = 0;
    public byte[] c = null;
    public byte d = 0;
    public byte[] e;
    public byte[] f;
    public byte[] g;
    public byte h;
    public byte i;
    public byte j;
    public byte k;

    public final void a(byte[] bArr, byte[] bArr2) {
        this.d = (byte) 0;
        this.c = new byte[256];
        for (int i = 0; i < 256; i++) {
            this.c[i] = (byte) i;
        }
        for (int i2 = 0; i2 < 768; i2++) {
            byte[] bArr3 = this.c;
            int i3 = i2 & 255;
            byte b = bArr3[(this.d + bArr3[i3] + bArr[i2 % bArr.length]) & 255];
            this.d = b;
            byte b2 = bArr3[i3];
            bArr3[i3] = bArr3[b & 255];
            bArr3[b & 255] = b2;
        }
        for (int i4 = 0; i4 < 768; i4++) {
            byte[] bArr4 = this.c;
            int i5 = i4 & 255;
            byte b3 = bArr4[(this.d + bArr4[i5] + bArr2[i4 % bArr2.length]) & 255];
            this.d = b3;
            byte b4 = bArr4[i5];
            bArr4[i5] = bArr4[b3 & 255];
            bArr4[b3 & 255] = b4;
        }
        this.b = (byte) 0;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        for (int i2 = 1; i2 < 25; i2++) {
            byte[] bArr2 = this.c;
            byte b = this.d;
            byte b2 = this.b;
            byte b3 = bArr2[(b + bArr2[b2 & 255]) & 255];
            this.d = b3;
            byte b4 = this.k;
            byte b5 = this.j;
            byte b6 = bArr2[(b4 + b5 + i2) & 255];
            this.k = b6;
            byte b7 = this.i;
            byte b8 = bArr2[(b5 + b7 + i2) & 255];
            this.j = b8;
            byte b9 = this.h;
            byte b10 = bArr2[(b7 + b9 + i2) & 255];
            this.i = b10;
            byte b11 = bArr2[(b9 + b3 + i2) & 255];
            this.h = b11;
            byte[] bArr3 = this.e;
            byte b12 = this.f14764a;
            bArr3[b12 & 31] = (byte) (b11 ^ bArr3[b12 & 31]);
            bArr3[(b12 + 1) & 31] = (byte) (b10 ^ bArr3[(b12 + 1) & 31]);
            bArr3[(b12 + 2) & 31] = (byte) (b8 ^ bArr3[(b12 + 2) & 31]);
            bArr3[(b12 + 3) & 31] = (byte) (b6 ^ bArr3[(b12 + 3) & 31]);
            this.f14764a = (byte) ((b12 + 4) & 31);
            byte b13 = bArr2[b2 & 255];
            bArr2[b2 & 255] = bArr2[b3 & 255];
            bArr2[b3 & 255] = b13;
            this.b = (byte) ((b2 + 1) & 255);
        }
        for (int i3 = 0; i3 < 768; i3++) {
            byte[] bArr4 = this.c;
            int i4 = i3 & 255;
            byte b14 = bArr4[(this.d + bArr4[i4] + this.e[i3 & 31]) & 255];
            this.d = b14;
            byte b15 = bArr4[i4];
            bArr4[i4] = bArr4[b14 & 255];
            bArr4[b14 & 255] = b15;
        }
        byte[] bArr5 = new byte[20];
        for (int i5 = 0; i5 < 20; i5++) {
            byte[] bArr6 = this.c;
            int i6 = i5 & 255;
            byte b16 = bArr6[(this.d + bArr6[i6]) & 255];
            this.d = b16;
            bArr5[i5] = bArr6[(bArr6[bArr6[b16 & 255] & 255] + 1) & 255];
            byte b17 = bArr6[i6];
            bArr6[i6] = bArr6[b16 & 255];
            bArr6[b16 & 255] = b17;
        }
        System.arraycopy(bArr5, 0, bArr, i, 20);
        reset();
        return 20;
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "VMPC-MAC";
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 20;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        KeyParameter keyParameter = (KeyParameter) parametersWithIV.getParameters();
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include a key");
        }
        byte[] iv = parametersWithIV.getIV();
        this.f = iv;
        if (iv == null || iv.length < 1 || iv.length > 768) {
            throw new IllegalArgumentException("VMPC-MAC requires 1 to 768 bytes of IV");
        }
        this.g = keyParameter.getKey();
        reset();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        a(this.g, this.f);
        this.b = (byte) 0;
        this.k = (byte) 0;
        this.j = (byte) 0;
        this.i = (byte) 0;
        this.h = (byte) 0;
        this.f14764a = (byte) 0;
        this.e = new byte[32];
        for (int i = 0; i < 32; i++) {
            this.e[i] = 0;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        byte[] bArr = this.c;
        byte b2 = this.d;
        byte b3 = this.b;
        byte b4 = bArr[(b2 + bArr[b3 & 255]) & 255];
        this.d = b4;
        byte b5 = this.k;
        byte b6 = this.j;
        byte b7 = bArr[(b5 + b6) & 255];
        this.k = b7;
        byte b8 = this.i;
        byte b9 = bArr[(b6 + b8) & 255];
        this.j = b9;
        byte b10 = this.h;
        byte b11 = bArr[(b8 + b10) & 255];
        this.i = b11;
        byte b12 = bArr[(b10 + b4 + ((byte) (b ^ bArr[(bArr[bArr[b4 & 255] & 255] + 1) & 255]))) & 255];
        this.h = b12;
        byte[] bArr2 = this.e;
        byte b13 = this.f14764a;
        bArr2[b13 & 31] = (byte) (b12 ^ bArr2[b13 & 31]);
        bArr2[(b13 + 1) & 31] = (byte) (b11 ^ bArr2[(b13 + 1) & 31]);
        bArr2[(b13 + 2) & 31] = (byte) (b9 ^ bArr2[(b13 + 2) & 31]);
        bArr2[(b13 + 3) & 31] = (byte) (b7 ^ bArr2[(b13 + 3) & 31]);
        this.f14764a = (byte) ((b13 + 4) & 31);
        byte b14 = bArr[b3 & 255];
        bArr[b3 & 255] = bArr[b4 & 255];
        bArr[b4 & 255] = b14;
        this.b = (byte) ((b3 + 1) & 255);
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            update(bArr[i + i3]);
        }
    }
}
