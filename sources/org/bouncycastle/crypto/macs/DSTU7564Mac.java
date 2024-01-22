package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.digests.DSTU7564Digest;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class DSTU7564Mac implements Mac {

    /* renamed from: a  reason: collision with root package name */
    public DSTU7564Digest f14754a;
    public int b;
    public byte[] c = null;
    public byte[] d = null;
    public long e;

    public DSTU7564Mac(int i) {
        this.f14754a = new DSTU7564Digest(i);
        this.b = i / 8;
    }

    public final void a() {
        int byteLength = this.f14754a.getByteLength() - ((int) (this.e % this.f14754a.getByteLength()));
        if (byteLength < 13) {
            byteLength += this.f14754a.getByteLength();
        }
        byte[] bArr = new byte[byteLength];
        bArr[0] = Byte.MIN_VALUE;
        Pack.longToLittleEndian(this.e * 8, bArr, byteLength - 12);
        this.f14754a.update(bArr, 0, byteLength);
    }

    public final byte[] b(byte[] bArr) {
        int length = (((bArr.length + this.f14754a.getByteLength()) - 1) / this.f14754a.getByteLength()) * this.f14754a.getByteLength();
        if (this.f14754a.getByteLength() - (bArr.length % this.f14754a.getByteLength()) < 13) {
            length += this.f14754a.getByteLength();
        }
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        bArr2[bArr.length] = Byte.MIN_VALUE;
        Pack.intToLittleEndian(bArr.length * 8, bArr2, length - 12);
        return bArr2;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        if (this.c == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (bArr.length - i >= this.b) {
            a();
            DSTU7564Digest dSTU7564Digest = this.f14754a;
            byte[] bArr2 = this.d;
            dSTU7564Digest.update(bArr2, 0, bArr2.length);
            this.e = 0L;
            return this.f14754a.doFinal(bArr, i);
        } else {
            throw new OutputLengthException("Output buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "DSTU7564Mac";
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.b;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Bad parameter passed");
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        this.d = new byte[key.length];
        this.c = b(key);
        int i = 0;
        while (true) {
            byte[] bArr = this.d;
            if (i >= bArr.length) {
                DSTU7564Digest dSTU7564Digest = this.f14754a;
                byte[] bArr2 = this.c;
                dSTU7564Digest.update(bArr2, 0, bArr2.length);
                return;
            }
            bArr[i] = (byte) (~key[i]);
            i++;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        this.e = 0L;
        this.f14754a.reset();
        byte[] bArr = this.c;
        if (bArr != null) {
            this.f14754a.update(bArr, 0, bArr.length);
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        this.f14754a.update(b);
        this.e++;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException {
        if (bArr.length - i < i2) {
            throw new DataLengthException("Input buffer too short");
        }
        if (this.c != null) {
            this.f14754a.update(bArr, i, i2);
            this.e += i2;
            return;
        }
        throw new IllegalStateException(getAlgorithmName() + " not initialised");
    }
}
