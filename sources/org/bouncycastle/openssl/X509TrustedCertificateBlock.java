package org.bouncycastle.openssl;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class X509TrustedCertificateBlock {

    /* renamed from: a  reason: collision with root package name */
    public final X509CertificateHolder f15202a;
    public final CertificateTrustBlock b;

    public X509TrustedCertificateBlock(X509CertificateHolder x509CertificateHolder, CertificateTrustBlock certificateTrustBlock) {
        this.f15202a = x509CertificateHolder;
        this.b = certificateTrustBlock;
    }

    public X509TrustedCertificateBlock(byte[] bArr) throws IOException {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(bArr);
        this.f15202a = new X509CertificateHolder(aSN1InputStream.readObject().getEncoded());
        this.b = new CertificateTrustBlock(aSN1InputStream.readObject().getEncoded());
    }

    public X509CertificateHolder getCertificateHolder() {
        return this.f15202a;
    }

    public byte[] getEncoded() throws IOException {
        return Arrays.concatenate(this.f15202a.getEncoded(), this.b.a().getEncoded());
    }

    public CertificateTrustBlock getTrustBlock() {
        return this.b;
    }
}
