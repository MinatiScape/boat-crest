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
public class KeyTransRecipientInfo extends ASN1Object {
    public ASN1Integer h;
    public RecipientIdentifier i;
    public AlgorithmIdentifier j;
    public ASN1OctetString k;

    public KeyTransRecipientInfo(ASN1Sequence aSN1Sequence) {
        this.h = (ASN1Integer) aSN1Sequence.getObjectAt(0);
        this.i = RecipientIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.j = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(2));
        this.k = (ASN1OctetString) aSN1Sequence.getObjectAt(3);
    }

    public KeyTransRecipientInfo(RecipientIdentifier recipientIdentifier, AlgorithmIdentifier algorithmIdentifier, ASN1OctetString aSN1OctetString) {
        this.h = recipientIdentifier.toASN1Primitive() instanceof ASN1TaggedObject ? new ASN1Integer(2L) : new ASN1Integer(0L);
        this.i = recipientIdentifier;
        this.j = algorithmIdentifier;
        this.k = aSN1OctetString;
    }

    public static KeyTransRecipientInfo getInstance(Object obj) {
        if (obj instanceof KeyTransRecipientInfo) {
            return (KeyTransRecipientInfo) obj;
        }
        if (obj != null) {
            return new KeyTransRecipientInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1OctetString getEncryptedKey() {
        return this.k;
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm() {
        return this.j;
    }

    public RecipientIdentifier getRecipientIdentifier() {
        return this.i;
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
