package org.bouncycastle.cert.ocsp;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ocsp.BasicOCSPResponse;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.bouncycastle.asn1.ocsp.SingleResponse;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.util.Encodable;
/* loaded from: classes12.dex */
public class BasicOCSPResp implements Encodable {
    public BasicOCSPResponse h;
    public ResponseData i;
    public Extensions j;

    public BasicOCSPResp(BasicOCSPResponse basicOCSPResponse) {
        this.h = basicOCSPResponse;
        this.i = basicOCSPResponse.getTbsResponseData();
        this.j = Extensions.getInstance(basicOCSPResponse.getTbsResponseData().getResponseExtensions());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BasicOCSPResp) {
            return this.h.equals(((BasicOCSPResp) obj).h);
        }
        return false;
    }

    public X509CertificateHolder[] getCerts() {
        ASN1Sequence certs;
        if (this.h.getCerts() != null && (certs = this.h.getCerts()) != null) {
            int size = certs.size();
            X509CertificateHolder[] x509CertificateHolderArr = new X509CertificateHolder[size];
            for (int i = 0; i != size; i++) {
                x509CertificateHolderArr[i] = new X509CertificateHolder(Certificate.getInstance(certs.getObjectAt(i)));
            }
            return x509CertificateHolderArr;
        }
        return a.f14504a;
    }

    public Set getCriticalExtensionOIDs() {
        return a.b(this.j);
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return this.h.getEncoded();
    }

    public Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Extensions extensions = this.j;
        if (extensions != null) {
            return extensions.getExtension(aSN1ObjectIdentifier);
        }
        return null;
    }

    public List getExtensionOIDs() {
        return a.c(this.j);
    }

    public Set getNonCriticalExtensionOIDs() {
        return a.d(this.j);
    }

    public Date getProducedAt() {
        return a.a(this.i.getProducedAt());
    }

    public RespID getResponderId() {
        return new RespID(this.i.getResponderID());
    }

    public SingleResp[] getResponses() {
        ASN1Sequence responses = this.i.getResponses();
        int size = responses.size();
        SingleResp[] singleRespArr = new SingleResp[size];
        for (int i = 0; i != size; i++) {
            singleRespArr[i] = new SingleResp(SingleResponse.getInstance(responses.getObjectAt(i)));
        }
        return singleRespArr;
    }

    public byte[] getSignature() {
        return this.h.getSignature().getOctets();
    }

    public ASN1ObjectIdentifier getSignatureAlgOID() {
        return this.h.getSignatureAlgorithm().getAlgorithm();
    }

    public AlgorithmIdentifier getSignatureAlgorithmID() {
        return this.h.getSignatureAlgorithm();
    }

    public byte[] getTBSResponseData() {
        try {
            return this.h.getTbsResponseData().getEncoded(ASN1Encoding.DER);
        } catch (IOException unused) {
            return null;
        }
    }

    public int getVersion() {
        return this.i.getVersion().getValue().intValue() + 1;
    }

    public boolean hasExtensions() {
        return this.j != null;
    }

    public int hashCode() {
        return this.h.hashCode();
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws OCSPException {
        try {
            ContentVerifier contentVerifier = contentVerifierProvider.get(this.h.getSignatureAlgorithm());
            OutputStream outputStream = contentVerifier.getOutputStream();
            outputStream.write(this.h.getTbsResponseData().getEncoded(ASN1Encoding.DER));
            outputStream.close();
            return contentVerifier.verify(getSignature());
        } catch (Exception e) {
            throw new OCSPException("exception processing sig: " + e, e);
        }
    }
}
