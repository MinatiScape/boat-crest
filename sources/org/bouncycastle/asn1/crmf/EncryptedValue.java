package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class EncryptedValue extends ASN1Object {
    public AlgorithmIdentifier h;
    public AlgorithmIdentifier i;
    public DERBitString j;
    public AlgorithmIdentifier k;
    public ASN1OctetString l;
    public DERBitString m;

    public EncryptedValue(ASN1Sequence aSN1Sequence) {
        int i = 0;
        while (aSN1Sequence.getObjectAt(i) instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(i);
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 0) {
                this.h = AlgorithmIdentifier.getInstance(aSN1TaggedObject, false);
            } else if (tagNo == 1) {
                this.i = AlgorithmIdentifier.getInstance(aSN1TaggedObject, false);
            } else if (tagNo == 2) {
                this.j = DERBitString.getInstance(aSN1TaggedObject, false);
            } else if (tagNo == 3) {
                this.k = AlgorithmIdentifier.getInstance(aSN1TaggedObject, false);
            } else if (tagNo != 4) {
                throw new IllegalArgumentException("Unknown tag encountered: " + aSN1TaggedObject.getTagNo());
            } else {
                this.l = ASN1OctetString.getInstance(aSN1TaggedObject, false);
            }
            i++;
        }
        this.m = DERBitString.getInstance(aSN1Sequence.getObjectAt(i));
    }

    public EncryptedValue(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, DERBitString dERBitString, AlgorithmIdentifier algorithmIdentifier3, ASN1OctetString aSN1OctetString, DERBitString dERBitString2) {
        if (dERBitString2 == null) {
            throw new IllegalArgumentException("'encValue' cannot be null");
        }
        this.h = algorithmIdentifier;
        this.i = algorithmIdentifier2;
        this.j = dERBitString;
        this.k = algorithmIdentifier3;
        this.l = aSN1OctetString;
        this.m = dERBitString2;
    }

    public static EncryptedValue getInstance(Object obj) {
        if (obj instanceof EncryptedValue) {
            return (EncryptedValue) obj;
        }
        if (obj != null) {
            return new EncryptedValue(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public final void a(ASN1EncodableVector aSN1EncodableVector, int i, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, i, aSN1Encodable));
        }
    }

    public DERBitString getEncSymmKey() {
        return this.j;
    }

    public DERBitString getEncValue() {
        return this.m;
    }

    public AlgorithmIdentifier getIntendedAlg() {
        return this.h;
    }

    public AlgorithmIdentifier getKeyAlg() {
        return this.k;
    }

    public AlgorithmIdentifier getSymmAlg() {
        return this.i;
    }

    public ASN1OctetString getValueHint() {
        return this.l;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        a(aSN1EncodableVector, 0, this.h);
        a(aSN1EncodableVector, 1, this.i);
        a(aSN1EncodableVector, 2, this.j);
        a(aSN1EncodableVector, 3, this.k);
        a(aSN1EncodableVector, 4, this.l);
        aSN1EncodableVector.add(this.m);
        return new DERSequence(aSN1EncodableVector);
    }
}
