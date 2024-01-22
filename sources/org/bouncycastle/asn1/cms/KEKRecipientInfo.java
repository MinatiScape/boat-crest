package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class KEKRecipientInfo extends ASN1Object {
    public ASN1Integer h;
    public KEKIdentifier i;
    public AlgorithmIdentifier j;
    public ASN1OctetString k;

    public KEKRecipientInfo(ASN1Sequence aSN1Sequence) {
        this.h = (ASN1Integer) aSN1Sequence.getObjectAt(0);
        this.i = KEKIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.j = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(2));
        this.k = (ASN1OctetString) aSN1Sequence.getObjectAt(3);
    }

    public KEKRecipientInfo(KEKIdentifier kEKIdentifier, AlgorithmIdentifier algorithmIdentifier, ASN1OctetString aSN1OctetString) {
        this.h = new ASN1Integer(4L);
        this.i = kEKIdentifier;
        this.j = algorithmIdentifier;
        this.k = aSN1OctetString;
    }

    public static KEKRecipientInfo getInstance(Object obj) {
        if (obj instanceof KEKRecipientInfo) {
            return (KEKRecipientInfo) obj;
        }
        if (obj != null) {
            return new KEKRecipientInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static KEKRecipientInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1OctetString getEncryptedKey() {
        return this.k;
    }

    public KEKIdentifier getKekid() {
        return this.i;
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm() {
        return this.j;
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
        aSN1EncodableVector.add(this.k);
        return new DERSequence(aSN1EncodableVector);
    }
}
