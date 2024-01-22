package org.bouncycastle.crypto.agreement.kdf;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Pack;
/* loaded from: classes8.dex */
public class DHKEKGenerator implements DerivationFunction {

    /* renamed from: a  reason: collision with root package name */
    public final Digest f14627a;
    public ASN1ObjectIdentifier b;
    public int c;
    public byte[] d;
    public byte[] e;

    public DHKEKGenerator(Digest digest) {
        this.f14627a = digest;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i, int i2) throws DataLengthException, IllegalArgumentException {
        boolean z;
        int i3 = i2;
        int i4 = i;
        if (bArr.length - i3 >= i4) {
            long j = i3;
            int digestSize = this.f14627a.getDigestSize();
            if (j <= 8589934591L) {
                long j2 = digestSize;
                int i5 = (int) (((j + j2) - 1) / j2);
                byte[] bArr2 = new byte[this.f14627a.getDigestSize()];
                int i6 = 0;
                int i7 = 0;
                int i8 = 1;
                while (i7 < i5) {
                    Digest digest = this.f14627a;
                    byte[] bArr3 = this.d;
                    digest.update(bArr3, i6, bArr3.length);
                    ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                    ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                    aSN1EncodableVector2.add(this.b);
                    aSN1EncodableVector2.add(new DEROctetString(Pack.intToBigEndian(i8)));
                    aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
                    if (this.e != null) {
                        z = true;
                        aSN1EncodableVector.add(new DERTaggedObject(true, i6, new DEROctetString(this.e)));
                    } else {
                        z = true;
                    }
                    aSN1EncodableVector.add(new DERTaggedObject(z, 2, new DEROctetString(Pack.intToBigEndian(this.c))));
                    try {
                        byte[] encoded = new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER);
                        this.f14627a.update(encoded, 0, encoded.length);
                        this.f14627a.doFinal(bArr2, 0);
                        if (i3 > digestSize) {
                            System.arraycopy(bArr2, 0, bArr, i4, digestSize);
                            i4 += digestSize;
                            i3 -= digestSize;
                        } else {
                            System.arraycopy(bArr2, 0, bArr, i4, i3);
                        }
                        i8++;
                        i7++;
                        i6 = 0;
                    } catch (IOException e) {
                        throw new IllegalArgumentException("unable to encode parameter info: " + e.getMessage());
                    }
                }
                this.f14627a.reset();
                return (int) j;
            }
            throw new IllegalArgumentException("Output length too large");
        }
        throw new OutputLengthException("output buffer too small");
    }

    public Digest getDigest() {
        return this.f14627a;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        DHKDFParameters dHKDFParameters = (DHKDFParameters) derivationParameters;
        this.b = dHKDFParameters.getAlgorithm();
        this.c = dHKDFParameters.getKeySize();
        this.d = dHKDFParameters.getZ();
        this.e = dHKDFParameters.getExtraInfo();
    }
}
