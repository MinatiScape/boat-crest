package org.bouncycastle.asn1.pkcs;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class SignerInfo extends ASN1Object {
    public ASN1Integer h;
    public IssuerAndSerialNumber i;
    public AlgorithmIdentifier j;
    public ASN1Set k;
    public AlgorithmIdentifier l;
    public ASN1OctetString m;
    public ASN1Set n;

    public SignerInfo(ASN1Integer aSN1Integer, IssuerAndSerialNumber issuerAndSerialNumber, AlgorithmIdentifier algorithmIdentifier, ASN1Set aSN1Set, AlgorithmIdentifier algorithmIdentifier2, ASN1OctetString aSN1OctetString, ASN1Set aSN1Set2) {
        this.h = aSN1Integer;
        this.i = issuerAndSerialNumber;
        this.j = algorithmIdentifier;
        this.k = aSN1Set;
        this.l = algorithmIdentifier2;
        this.m = aSN1OctetString;
        this.n = aSN1Set2;
    }

    public SignerInfo(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = (ASN1Integer) objects.nextElement();
        this.i = IssuerAndSerialNumber.getInstance(objects.nextElement());
        this.j = AlgorithmIdentifier.getInstance(objects.nextElement());
        Object nextElement = objects.nextElement();
        if (nextElement instanceof ASN1TaggedObject) {
            this.k = ASN1Set.getInstance((ASN1TaggedObject) nextElement, false);
            nextElement = objects.nextElement();
        } else {
            this.k = null;
        }
        this.l = AlgorithmIdentifier.getInstance(nextElement);
        this.m = ASN1OctetString.getInstance(objects.nextElement());
        if (objects.hasMoreElements()) {
            this.n = ASN1Set.getInstance((ASN1TaggedObject) objects.nextElement(), false);
        } else {
            this.n = null;
        }
    }

    public static SignerInfo getInstance(Object obj) {
        if (obj instanceof SignerInfo) {
            return (SignerInfo) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new SignerInfo((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    public ASN1Set getAuthenticatedAttributes() {
        return this.k;
    }

    public AlgorithmIdentifier getDigestAlgorithm() {
        return this.j;
    }

    public AlgorithmIdentifier getDigestEncryptionAlgorithm() {
        return this.l;
    }

    public ASN1OctetString getEncryptedDigest() {
        return this.m;
    }

    public IssuerAndSerialNumber getIssuerAndSerialNumber() {
        return this.i;
    }

    public ASN1Set getUnauthenticatedAttributes() {
        return this.n;
    }

    public ASN1Integer getVersion() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        if (this.k != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.k));
        }
        aSN1EncodableVector.add(this.l);
        aSN1EncodableVector.add(this.m);
        if (this.n != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.n));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
