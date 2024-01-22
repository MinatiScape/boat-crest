package org.bouncycastle.cert;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.operator.DigestCalculator;
/* loaded from: classes12.dex */
public class X509ExtensionUtils {

    /* renamed from: a  reason: collision with root package name */
    public DigestCalculator f14442a;

    public X509ExtensionUtils(DigestCalculator digestCalculator) {
        this.f14442a = digestCalculator;
    }

    public final byte[] a(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        byte[] bytes = subjectPublicKeyInfo.getPublicKeyData().getBytes();
        OutputStream outputStream = this.f14442a.getOutputStream();
        try {
            outputStream.write(bytes);
            outputStream.close();
            return this.f14442a.getDigest();
        } catch (IOException e) {
            throw new CertRuntimeException("unable to calculate identifier: " + e.getMessage(), e);
        }
    }

    public final byte[] b(X509CertificateHolder x509CertificateHolder) {
        Extension extension;
        if (x509CertificateHolder.getVersionNumber() == 3 && (extension = x509CertificateHolder.getExtension(Extension.subjectKeyIdentifier)) != null) {
            return ASN1OctetString.getInstance(extension.getParsedValue()).getOctets();
        }
        return a(x509CertificateHolder.getSubjectPublicKeyInfo());
    }

    public AuthorityKeyIdentifier createAuthorityKeyIdentifier(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        return new AuthorityKeyIdentifier(a(subjectPublicKeyInfo));
    }

    public AuthorityKeyIdentifier createAuthorityKeyIdentifier(SubjectPublicKeyInfo subjectPublicKeyInfo, GeneralNames generalNames, BigInteger bigInteger) {
        return new AuthorityKeyIdentifier(a(subjectPublicKeyInfo), generalNames, bigInteger);
    }

    public AuthorityKeyIdentifier createAuthorityKeyIdentifier(X509CertificateHolder x509CertificateHolder) {
        return new AuthorityKeyIdentifier(b(x509CertificateHolder), new GeneralNames(new GeneralName(x509CertificateHolder.getIssuer())), x509CertificateHolder.getSerialNumber());
    }

    public SubjectKeyIdentifier createSubjectKeyIdentifier(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        return new SubjectKeyIdentifier(a(subjectPublicKeyInfo));
    }

    public SubjectKeyIdentifier createTruncatedSubjectKeyIdentifier(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        byte[] a2 = a(subjectPublicKeyInfo);
        byte[] bArr = new byte[8];
        System.arraycopy(a2, a2.length - 8, bArr, 0, 8);
        bArr[0] = (byte) (bArr[0] & 15);
        bArr[0] = (byte) (bArr[0] | 64);
        return new SubjectKeyIdentifier(bArr);
    }
}
