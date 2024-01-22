package org.bouncycastle.cert.ocsp;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.ocsp.OCSPRequest;
import org.bouncycastle.asn1.ocsp.Request;
import org.bouncycastle.asn1.ocsp.Signature;
import org.bouncycastle.asn1.ocsp.TBSRequest;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.ContentSigner;
/* loaded from: classes12.dex */
public class OCSPReqBuilder {

    /* renamed from: a  reason: collision with root package name */
    public List f14496a = new ArrayList();
    public GeneralName b = null;
    public Extensions c = null;

    /* loaded from: classes12.dex */
    public class a {

        /* renamed from: a  reason: collision with root package name */
        public CertificateID f14497a;
        public Extensions b;

        public a(OCSPReqBuilder oCSPReqBuilder, CertificateID certificateID, Extensions extensions) {
            this.f14497a = certificateID;
            this.b = extensions;
        }

        public Request a() throws Exception {
            return new Request(this.f14497a.toASN1Primitive(), this.b);
        }
    }

    public final OCSPReq a(ContentSigner contentSigner, X509CertificateHolder[] x509CertificateHolderArr) throws OCSPException {
        Signature signature;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (a aVar : this.f14496a) {
            try {
                aSN1EncodableVector.add(aVar.a());
            } catch (Exception e) {
                throw new OCSPException("exception creating Request", e);
            }
        }
        TBSRequest tBSRequest = new TBSRequest(this.b, new DERSequence(aSN1EncodableVector), this.c);
        Signature signature2 = null;
        if (contentSigner != null) {
            if (this.b == null) {
                throw new OCSPException("requestorName must be specified if request is signed.");
            }
            try {
                OutputStream outputStream = contentSigner.getOutputStream();
                outputStream.write(tBSRequest.getEncoded(ASN1Encoding.DER));
                outputStream.close();
                DERBitString dERBitString = new DERBitString(contentSigner.getSignature());
                AlgorithmIdentifier algorithmIdentifier = contentSigner.getAlgorithmIdentifier();
                if (x509CertificateHolderArr == null || x509CertificateHolderArr.length <= 0) {
                    signature = new Signature(algorithmIdentifier, dERBitString);
                } else {
                    ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                    for (int i = 0; i != x509CertificateHolderArr.length; i++) {
                        aSN1EncodableVector2.add(x509CertificateHolderArr[i].toASN1Structure());
                    }
                    signature = new Signature(algorithmIdentifier, dERBitString, new DERSequence(aSN1EncodableVector2));
                }
                signature2 = signature;
            } catch (Exception e2) {
                throw new OCSPException("exception processing TBSRequest: " + e2, e2);
            }
        }
        return new OCSPReq(new OCSPRequest(tBSRequest, signature2));
    }

    public OCSPReqBuilder addRequest(CertificateID certificateID) {
        this.f14496a.add(new a(this, certificateID, null));
        return this;
    }

    public OCSPReqBuilder addRequest(CertificateID certificateID, Extensions extensions) {
        this.f14496a.add(new a(this, certificateID, extensions));
        return this;
    }

    public OCSPReq build() throws OCSPException {
        return a(null, null);
    }

    public OCSPReq build(ContentSigner contentSigner, X509CertificateHolder[] x509CertificateHolderArr) throws OCSPException, IllegalArgumentException {
        if (contentSigner != null) {
            return a(contentSigner, x509CertificateHolderArr);
        }
        throw new IllegalArgumentException("no signer specified");
    }

    public OCSPReqBuilder setRequestExtensions(Extensions extensions) {
        this.c = extensions;
        return this;
    }

    public OCSPReqBuilder setRequestorName(X500Name x500Name) {
        this.b = new GeneralName(4, x500Name);
        return this;
    }

    public OCSPReqBuilder setRequestorName(GeneralName generalName) {
        this.b = generalName;
        return this;
    }
}
