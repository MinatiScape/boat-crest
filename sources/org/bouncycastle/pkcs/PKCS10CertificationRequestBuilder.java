package org.bouncycastle.pkcs;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.pkcs.Attribute;
import org.bouncycastle.asn1.pkcs.CertificationRequest;
import org.bouncycastle.asn1.pkcs.CertificationRequestInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.operator.ContentSigner;
/* loaded from: classes13.dex */
public class PKCS10CertificationRequestBuilder {

    /* renamed from: a  reason: collision with root package name */
    public SubjectPublicKeyInfo f15254a;
    public X500Name b;
    public List c;
    public boolean d;

    public PKCS10CertificationRequestBuilder(X500Name x500Name, SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.c = new ArrayList();
        this.d = false;
        this.b = x500Name;
        this.f15254a = subjectPublicKeyInfo;
    }

    public PKCS10CertificationRequestBuilder(PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder) {
        this.c = new ArrayList();
        this.d = false;
        this.f15254a = pKCS10CertificationRequestBuilder.f15254a;
        this.b = pKCS10CertificationRequestBuilder.b;
        this.d = pKCS10CertificationRequestBuilder.d;
        this.c = new ArrayList(pKCS10CertificationRequestBuilder.c);
    }

    public PKCS10CertificationRequestBuilder addAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.c.add(new Attribute(aSN1ObjectIdentifier, new DERSet(aSN1Encodable)));
        return this;
    }

    public PKCS10CertificationRequestBuilder addAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable[] aSN1EncodableArr) {
        this.c.add(new Attribute(aSN1ObjectIdentifier, new DERSet(aSN1EncodableArr)));
        return this;
    }

    public PKCS10CertificationRequest build(ContentSigner contentSigner) {
        CertificationRequestInfo certificationRequestInfo;
        if (this.c.isEmpty()) {
            certificationRequestInfo = this.d ? new CertificationRequestInfo(this.b, this.f15254a, (ASN1Set) null) : new CertificationRequestInfo(this.b, this.f15254a, new DERSet());
        } else {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            for (Object obj : this.c) {
                aSN1EncodableVector.add(Attribute.getInstance(obj));
            }
            certificationRequestInfo = new CertificationRequestInfo(this.b, this.f15254a, new DERSet(aSN1EncodableVector));
        }
        try {
            OutputStream outputStream = contentSigner.getOutputStream();
            outputStream.write(certificationRequestInfo.getEncoded(ASN1Encoding.DER));
            outputStream.close();
            return new PKCS10CertificationRequest(new CertificationRequest(certificationRequestInfo, contentSigner.getAlgorithmIdentifier(), new DERBitString(contentSigner.getSignature())));
        } catch (IOException unused) {
            throw new IllegalStateException("cannot produce certification request signature");
        }
    }

    public PKCS10CertificationRequestBuilder setAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        for (Attribute attribute : this.c) {
            if (attribute.getAttrType().equals(aSN1ObjectIdentifier)) {
                throw new IllegalStateException("Attribute " + aSN1ObjectIdentifier.toString() + " is already set");
            }
        }
        addAttribute(aSN1ObjectIdentifier, aSN1Encodable);
        return this;
    }

    public PKCS10CertificationRequestBuilder setAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable[] aSN1EncodableArr) {
        for (Attribute attribute : this.c) {
            if (attribute.getAttrType().equals(aSN1ObjectIdentifier)) {
                throw new IllegalStateException("Attribute " + aSN1ObjectIdentifier.toString() + " is already set");
            }
        }
        addAttribute(aSN1ObjectIdentifier, aSN1EncodableArr);
        return this;
    }

    public PKCS10CertificationRequestBuilder setLeaveOffEmptyAttributes(boolean z) {
        this.d = z;
        return this;
    }
}
