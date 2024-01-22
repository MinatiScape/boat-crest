package org.bouncycastle.cert.crmf.bc;

import java.security.SecureRandom;
import org.bouncycastle.cert.crmf.EncryptedValuePadder;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.generators.MGF1BytesGenerator;
import org.bouncycastle.crypto.params.MGFParameters;
/* loaded from: classes12.dex */
public class BcFixedLengthMGF1Padder implements EncryptedValuePadder {

    /* renamed from: a  reason: collision with root package name */
    public int f14468a;
    public SecureRandom b;
    public Digest c;

    public BcFixedLengthMGF1Padder(int i) {
        this(i, null);
    }

    public BcFixedLengthMGF1Padder(int i, SecureRandom secureRandom) {
        this.c = new SHA1Digest();
        this.f14468a = i;
        this.b = secureRandom;
    }

    @Override // org.bouncycastle.cert.crmf.EncryptedValuePadder
    public byte[] getPaddedData(byte[] bArr) {
        int i = this.f14468a;
        byte[] bArr2 = new byte[i];
        int digestSize = this.c.getDigestSize();
        byte[] bArr3 = new byte[digestSize];
        int digestSize2 = this.f14468a - this.c.getDigestSize();
        byte[] bArr4 = new byte[digestSize2];
        if (this.b == null) {
            this.b = new SecureRandom();
        }
        this.b.nextBytes(bArr3);
        MGF1BytesGenerator mGF1BytesGenerator = new MGF1BytesGenerator(this.c);
        mGF1BytesGenerator.init(new MGFParameters(bArr3));
        mGF1BytesGenerator.generateBytes(bArr4, 0, digestSize2);
        System.arraycopy(bArr3, 0, bArr2, 0, digestSize);
        System.arraycopy(bArr, 0, bArr2, digestSize, bArr.length);
        int length = bArr.length + digestSize;
        while (true) {
            length++;
            if (length == i) {
                break;
            }
            bArr2[length] = (byte) (this.b.nextInt(255) + 1);
        }
        for (int i2 = 0; i2 != digestSize2; i2++) {
            int i3 = i2 + digestSize;
            bArr2[i3] = (byte) (bArr2[i3] ^ bArr4[i2]);
        }
        return bArr2;
    }

    @Override // org.bouncycastle.cert.crmf.EncryptedValuePadder
    public byte[] getUnpaddedData(byte[] bArr) {
        int digestSize = this.c.getDigestSize();
        byte[] bArr2 = new byte[digestSize];
        int digestSize2 = this.f14468a - this.c.getDigestSize();
        byte[] bArr3 = new byte[digestSize2];
        System.arraycopy(bArr, 0, bArr2, 0, digestSize);
        MGF1BytesGenerator mGF1BytesGenerator = new MGF1BytesGenerator(this.c);
        mGF1BytesGenerator.init(new MGFParameters(bArr2));
        mGF1BytesGenerator.generateBytes(bArr3, 0, digestSize2);
        for (int i = 0; i != digestSize2; i++) {
            int i2 = i + digestSize;
            bArr[i2] = (byte) (bArr[i2] ^ bArr3[i]);
        }
        int length = bArr.length - 1;
        while (true) {
            if (length == digestSize) {
                length = 0;
                break;
            } else if (bArr[length] == 0) {
                break;
            } else {
                length--;
            }
        }
        if (length != 0) {
            int i3 = length - digestSize;
            byte[] bArr4 = new byte[i3];
            System.arraycopy(bArr, digestSize, bArr4, 0, i3);
            return bArr4;
        }
        throw new IllegalStateException("bad padding in encoding");
    }
}
