package org.bouncycastle.cert.dane;

import java.io.IOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class DANEEntry {
    public static final int CERT_USAGE_ACCEPT = 3;
    public static final int CERT_USAGE_CA = 0;
    public static final int CERT_USAGE_PKIX_VALIDATE = 1;
    public static final int CERT_USAGE_TRUST_ANCHOR = 2;

    /* renamed from: a  reason: collision with root package name */
    public final String f14478a;
    public final byte[] b;
    public final X509CertificateHolder c;

    public DANEEntry(String str, byte[] bArr) throws IOException {
        this(str, Arrays.copyOfRange(bArr, 0, 3), new X509CertificateHolder(Arrays.copyOfRange(bArr, 3, bArr.length)));
    }

    public DANEEntry(String str, byte[] bArr, X509CertificateHolder x509CertificateHolder) {
        this.b = bArr;
        this.f14478a = str;
        this.c = x509CertificateHolder;
    }

    public static boolean isValidCertificate(byte[] bArr) {
        return (bArr[0] >= 0 || bArr[0] <= 3) && bArr[1] == 0 && bArr[2] == 0;
    }

    public X509CertificateHolder getCertificate() {
        return this.c;
    }

    public String getDomainName() {
        return this.f14478a;
    }

    public byte[] getFlags() {
        return Arrays.clone(this.b);
    }

    public byte[] getRDATA() throws IOException {
        byte[] encoded = this.c.getEncoded();
        byte[] bArr = this.b;
        byte[] bArr2 = new byte[bArr.length + encoded.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        System.arraycopy(encoded, 0, bArr2, this.b.length, encoded.length);
        return bArr2;
    }
}
