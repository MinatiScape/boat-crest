package org.bouncycastle.asn1.bc;

import java.math.BigInteger;
import java.util.Date;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERGeneralizedTime;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class ObjectStoreData extends ASN1Object {
    public final BigInteger h;
    public final AlgorithmIdentifier i;
    public final ASN1GeneralizedTime j;
    public final ASN1GeneralizedTime k;
    public final ObjectDataSequence l;
    public final String m;

    public ObjectStoreData(ASN1Sequence aSN1Sequence) {
        this.h = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getValue();
        this.i = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.j = ASN1GeneralizedTime.getInstance(aSN1Sequence.getObjectAt(2));
        this.k = ASN1GeneralizedTime.getInstance(aSN1Sequence.getObjectAt(3));
        this.l = ObjectDataSequence.getInstance(aSN1Sequence.getObjectAt(4));
        this.m = aSN1Sequence.size() == 6 ? DERUTF8String.getInstance(aSN1Sequence.getObjectAt(5)).getString() : null;
    }

    public ObjectStoreData(AlgorithmIdentifier algorithmIdentifier, Date date, Date date2, ObjectDataSequence objectDataSequence, String str) {
        this.h = BigInteger.valueOf(1L);
        this.i = algorithmIdentifier;
        this.j = new DERGeneralizedTime(date);
        this.k = new DERGeneralizedTime(date2);
        this.l = objectDataSequence;
        this.m = str;
    }

    public static ObjectStoreData getInstance(Object obj) {
        if (obj instanceof ObjectStoreData) {
            return (ObjectStoreData) obj;
        }
        if (obj != null) {
            return new ObjectStoreData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public String getComment() {
        return this.m;
    }

    public ASN1GeneralizedTime getCreationDate() {
        return this.j;
    }

    public AlgorithmIdentifier getIntegrityAlgorithm() {
        return this.i;
    }

    public ASN1GeneralizedTime getLastModifiedDate() {
        return this.k;
    }

    public ObjectDataSequence getObjectDataSequence() {
        return this.l;
    }

    public BigInteger getVersion() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.h));
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        aSN1EncodableVector.add(this.k);
        aSN1EncodableVector.add(this.l);
        String str = this.m;
        if (str != null) {
            aSN1EncodableVector.add(new DERUTF8String(str));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
