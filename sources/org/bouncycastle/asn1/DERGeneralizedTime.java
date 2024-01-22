package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Date;
import org.bouncycastle.util.Strings;
/* loaded from: classes12.dex */
public class DERGeneralizedTime extends ASN1GeneralizedTime {
    public DERGeneralizedTime(String str) {
        super(str);
    }

    public DERGeneralizedTime(Date date) {
        super(date);
    }

    public DERGeneralizedTime(byte[] bArr) {
        super(bArr);
    }

    @Override // org.bouncycastle.asn1.ASN1GeneralizedTime, org.bouncycastle.asn1.ASN1Primitive
    public int a() {
        int length = g().length;
        return i.a(length) + 1 + length;
    }

    @Override // org.bouncycastle.asn1.ASN1GeneralizedTime, org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.g(24, g());
    }

    public final byte[] g() {
        byte[] bArr = this.time;
        if (bArr[bArr.length - 1] == 90) {
            if (!hasMinutes()) {
                byte[] bArr2 = this.time;
                byte[] bArr3 = new byte[bArr2.length + 4];
                System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length - 1);
                System.arraycopy(Strings.toByteArray("0000Z"), 0, bArr3, this.time.length - 1, 5);
                return bArr3;
            } else if (!hasSeconds()) {
                byte[] bArr4 = this.time;
                byte[] bArr5 = new byte[bArr4.length + 2];
                System.arraycopy(bArr4, 0, bArr5, 0, bArr4.length - 1);
                System.arraycopy(Strings.toByteArray("00Z"), 0, bArr5, this.time.length - 1, 3);
                return bArr5;
            } else if (hasFractionalSeconds()) {
                int length = this.time.length - 2;
                while (length > 0 && this.time[length] == 48) {
                    length--;
                }
                byte[] bArr6 = this.time;
                if (bArr6[length] == 46) {
                    byte[] bArr7 = new byte[length + 1];
                    System.arraycopy(bArr6, 0, bArr7, 0, length);
                    bArr7[length] = 90;
                    return bArr7;
                }
                byte[] bArr8 = new byte[length + 2];
                int i = length + 1;
                System.arraycopy(bArr6, 0, bArr8, 0, i);
                bArr8[i] = 90;
                return bArr8;
            } else {
                return this.time;
            }
        }
        return bArr;
    }
}
