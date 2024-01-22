package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class KeyAgreeRecipientInfo extends ASN1Object {
    public ASN1Integer h;
    public OriginatorIdentifierOrKey i;
    public ASN1OctetString j;
    public AlgorithmIdentifier k;
    public ASN1Sequence l;

    public KeyAgreeRecipientInfo(ASN1Sequence aSN1Sequence) {
        this.h = (ASN1Integer) aSN1Sequence.getObjectAt(0);
        this.i = OriginatorIdentifierOrKey.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(1), true);
        int i = 2;
        if (aSN1Sequence.getObjectAt(2) instanceof ASN1TaggedObject) {
            this.j = ASN1OctetString.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(2), true);
            i = 3;
        }
        this.k = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i));
        this.l = (ASN1Sequence) aSN1Sequence.getObjectAt(i + 1);
    }

    public KeyAgreeRecipientInfo(OriginatorIdentifierOrKey originatorIdentifierOrKey, ASN1OctetString aSN1OctetString, AlgorithmIdentifier algorithmIdentifier, ASN1Sequence aSN1Sequence) {
        this.h = new ASN1Integer(3L);
        this.i = originatorIdentifierOrKey;
        this.j = aSN1OctetString;
        this.k = algorithmIdentifier;
        this.l = aSN1Sequence;
    }

    public static KeyAgreeRecipientInfo getInstance(Object obj) {
        if (obj instanceof KeyAgreeRecipientInfo) {
            return (KeyAgreeRecipientInfo) obj;
        }
        if (obj != null) {
            return new KeyAgreeRecipientInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static KeyAgreeRecipientInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm() {
        return this.k;
    }

    public OriginatorIdentifierOrKey getOriginator() {
        return this.i;
    }

    public ASN1Sequence getRecipientEncryptedKeys() {
        return this.l;
    }

    public ASN1OctetString getUserKeyingMaterial() {
        return this.j;
    }

    public ASN1Integer getVersion() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.i));
        if (this.j != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, this.j));
        }
        aSN1EncodableVector.add(this.k);
        aSN1EncodableVector.add(this.l);
        return new DERSequence(aSN1EncodableVector);
    }
}
