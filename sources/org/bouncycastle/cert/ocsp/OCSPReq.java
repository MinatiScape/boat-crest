package org.bouncycastle.cert.ocsp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Exception;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ocsp.OCSPRequest;
import org.bouncycastle.asn1.ocsp.Request;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
/* loaded from: classes12.dex */
public class OCSPReq {
    public static final X509CertificateHolder[] c = new X509CertificateHolder[0];

    /* renamed from: a  reason: collision with root package name */
    public OCSPRequest f14495a;
    public Extensions b;

    public OCSPReq(ASN1InputStream aSN1InputStream) throws IOException {
        try {
            OCSPRequest oCSPRequest = OCSPRequest.getInstance(aSN1InputStream.readObject());
            this.f14495a = oCSPRequest;
            if (oCSPRequest == null) {
                throw new CertIOException("malformed request: no request data found");
            }
            this.b = oCSPRequest.getTbsRequest().getRequestExtensions();
        } catch (ClassCastException e) {
            throw new CertIOException("malformed request: " + e.getMessage(), e);
        } catch (IllegalArgumentException e2) {
            throw new CertIOException("malformed request: " + e2.getMessage(), e2);
        } catch (ASN1Exception e3) {
            throw new CertIOException("malformed request: " + e3.getMessage(), e3);
        }
    }

    public OCSPReq(OCSPRequest oCSPRequest) {
        this.f14495a = oCSPRequest;
        this.b = oCSPRequest.getTbsRequest().getRequestExtensions();
    }

    public OCSPReq(byte[] bArr) throws IOException {
        this(new ASN1InputStream(bArr));
    }

    public X509CertificateHolder[] getCerts() {
        ASN1Sequence certs;
        if (this.f14495a.getOptionalSignature() != null && (certs = this.f14495a.getOptionalSignature().getCerts()) != null) {
            int size = certs.size();
            X509CertificateHolder[] x509CertificateHolderArr = new X509CertificateHolder[size];
            for (int i = 0; i != size; i++) {
                x509CertificateHolderArr[i] = new X509CertificateHolder(Certificate.getInstance(certs.getObjectAt(i)));
            }
            return x509CertificateHolderArr;
        }
        return c;
    }

    public Set getCriticalExtensionOIDs() {
        return a.b(this.b);
    }

    public byte[] getEncoded() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new ASN1OutputStream(byteArrayOutputStream).writeObject(this.f14495a);
        return byteArrayOutputStream.toByteArray();
    }

    public Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Extensions extensions = this.b;
        if (extensions != null) {
            return extensions.getExtension(aSN1ObjectIdentifier);
        }
        return null;
    }

    public List getExtensionOIDs() {
        return a.c(this.b);
    }

    public Set getNonCriticalExtensionOIDs() {
        return a.d(this.b);
    }

    public Req[] getRequestList() {
        ASN1Sequence requestList = this.f14495a.getTbsRequest().getRequestList();
        int size = requestList.size();
        Req[] reqArr = new Req[size];
        for (int i = 0; i != size; i++) {
            reqArr[i] = new Req(Request.getInstance(requestList.getObjectAt(i)));
        }
        return reqArr;
    }

    public GeneralName getRequestorName() {
        return GeneralName.getInstance(this.f14495a.getTbsRequest().getRequestorName());
    }

    public byte[] getSignature() {
        if (isSigned()) {
            return this.f14495a.getOptionalSignature().getSignature().getOctets();
        }
        return null;
    }

    public ASN1ObjectIdentifier getSignatureAlgOID() {
        if (isSigned()) {
            return this.f14495a.getOptionalSignature().getSignatureAlgorithm().getAlgorithm();
        }
        return null;
    }

    public int getVersionNumber() {
        return this.f14495a.getTbsRequest().getVersion().getValue().intValue() + 1;
    }

    public boolean hasExtensions() {
        return this.b != null;
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws OCSPException {
        if (isSigned()) {
            try {
                ContentVerifier contentVerifier = contentVerifierProvider.get(this.f14495a.getOptionalSignature().getSignatureAlgorithm());
                contentVerifier.getOutputStream().write(this.f14495a.getTbsRequest().getEncoded(ASN1Encoding.DER));
                return contentVerifier.verify(getSignature());
            } catch (Exception e) {
                throw new OCSPException("exception processing signature: " + e, e);
            }
        }
        throw new OCSPException("attempt to verify signature on unsigned object");
    }

    public boolean isSigned() {
        return this.f14495a.getOptionalSignature() != null;
    }
}
