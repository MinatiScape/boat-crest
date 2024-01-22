package org.bouncycastle.asn1.cms;

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
    public SignerIdentifier i;
    public AlgorithmIdentifier j;
    public ASN1Set k;
    public AlgorithmIdentifier l;
    public ASN1OctetString m;
    public ASN1Set n;

    public SignerInfo(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = (ASN1Integer) objects.nextElement();
        this.i = SignerIdentifier.getInstance(objects.nextElement());
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

    public SignerInfo(SignerIdentifier signerIdentifier, AlgorithmIdentifier algorithmIdentifier, ASN1Set aSN1Set, AlgorithmIdentifier algorithmIdentifier2, ASN1OctetString aSN1OctetString, ASN1Set aSN1Set2) {
        this.h = signerIdentifier.isTagged() ? new ASN1Integer(3L) : new ASN1Integer(1L);
        this.i = signerIdentifier;
        this.j = algorithmIdentifier;
        this.k = aSN1Set;
        this.l = algorithmIdentifier2;
        this.m = aSN1OctetString;
        this.n = aSN1Set2;
    }

    public SignerInfo(SignerIdentifier signerIdentifier, AlgorithmIdentifier algorithmIdentifier, Attributes attributes, AlgorithmIdentifier algorithmIdentifier2, ASN1OctetString aSN1OctetString, Attributes attributes2) {
        this.h = signerIdentifier.isTagged() ? new ASN1Integer(3L) : new ASN1Integer(1L);
        this.i = signerIdentifier;
        this.j = algorithmIdentifier;
        this.k = ASN1Set.getInstance(attributes);
        this.l = algorithmIdentifier2;
        this.m = aSN1OctetString;
        this.n = ASN1Set.getInstance(attributes2);
    }

    public static SignerInfo getInstance(Object obj) throws IllegalArgumentException {
        if (obj instanceof SignerInfo) {
            return (SignerInfo) obj;
        }
        if (obj != null) {
            return new SignerInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
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

    public SignerIdentifier getSID() {
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
