package org.bouncycastle.x509;

import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.CertificatePair;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jce.provider.X509CertificateObject;
/* loaded from: classes13.dex */
public class X509CertificatePair {

    /* renamed from: a  reason: collision with root package name */
    public X509Certificate f15414a;
    public X509Certificate b;

    public X509CertificatePair(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        new BCJcaJceHelper();
        this.f15414a = x509Certificate;
        this.b = x509Certificate2;
    }

    public X509CertificatePair(CertificatePair certificatePair) throws CertificateParsingException {
        new BCJcaJceHelper();
        if (certificatePair.getForward() != null) {
            this.f15414a = new X509CertificateObject(certificatePair.getForward());
        }
        if (certificatePair.getReverse() != null) {
            this.b = new X509CertificateObject(certificatePair.getReverse());
        }
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof X509CertificatePair)) {
            X509CertificatePair x509CertificatePair = (X509CertificatePair) obj;
            X509Certificate x509Certificate = this.f15414a;
            boolean equals = x509Certificate != null ? x509Certificate.equals(x509CertificatePair.f15414a) : x509CertificatePair.f15414a == null;
            X509Certificate x509Certificate2 = this.b;
            X509Certificate x509Certificate3 = x509CertificatePair.b;
            return equals && (x509Certificate2 != null ? x509Certificate2.equals(x509Certificate3) : x509Certificate3 == null);
        }
        return false;
    }

    public byte[] getEncoded() throws CertificateEncodingException {
        Certificate certificate;
        try {
            Certificate certificate2 = null;
            if (this.f15414a != null) {
                certificate = Certificate.getInstance(new ASN1InputStream(this.f15414a.getEncoded()).readObject());
                if (certificate == null) {
                    throw new CertificateEncodingException("unable to get encoding for forward");
                }
            } else {
                certificate = null;
            }
            if (this.b != null && (certificate2 = Certificate.getInstance(new ASN1InputStream(this.b.getEncoded()).readObject())) == null) {
                throw new CertificateEncodingException("unable to get encoding for reverse");
            }
            return new CertificatePair(certificate, certificate2).getEncoded(ASN1Encoding.DER);
        } catch (IOException e) {
            throw new c(e.toString(), e);
        } catch (IllegalArgumentException e2) {
            throw new c(e2.toString(), e2);
        }
    }

    public X509Certificate getForward() {
        return this.f15414a;
    }

    public X509Certificate getReverse() {
        return this.b;
    }

    public int hashCode() {
        X509Certificate x509Certificate = this.f15414a;
        int hashCode = x509Certificate != null ? (-1) ^ x509Certificate.hashCode() : -1;
        X509Certificate x509Certificate2 = this.b;
        return x509Certificate2 != null ? (hashCode * 17) ^ x509Certificate2.hashCode() : hashCode;
    }
}
