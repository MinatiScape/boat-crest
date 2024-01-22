package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class EncryptedContentInfo extends ASN1Object {
    public ASN1ObjectIdentifier h;
    public AlgorithmIdentifier i;
    public ASN1OctetString j;

    public EncryptedContentInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmIdentifier algorithmIdentifier, ASN1OctetString aSN1OctetString) {
        this.h = aSN1ObjectIdentifier;
        this.i = algorithmIdentifier;
        this.j = aSN1OctetString;
    }

    public EncryptedContentInfo(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 2) {
            throw new IllegalArgumentException("Truncated Sequence Found");
        }
        this.h = (ASN1ObjectIdentifier) aSN1Sequence.getObjectAt(0);
        this.i = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() > 2) {
            this.j = ASN1OctetString.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(2), false);
        }
    }

    public static EncryptedContentInfo getInstance(Object obj) {
        if (obj instanceof EncryptedContentInfo) {
            return (EncryptedContentInfo) obj;
        }
        if (obj != null) {
            return new EncryptedContentInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public AlgorithmIdentifier getContentEncryptionAlgorithm() {
        return this.i;
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.h;
    }

    public ASN1OctetString getEncryptedContent() {
        return this.j;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        ASN1OctetString aSN1OctetString = this.j;
        if (aSN1OctetString != null) {
            aSN1EncodableVector.add(new BERTaggedObject(false, 0, aSN1OctetString));
        }
        return new BERSequence(aSN1EncodableVector);
    }
}
