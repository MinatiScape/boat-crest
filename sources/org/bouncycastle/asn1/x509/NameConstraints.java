package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class NameConstraints extends ASN1Object {
    public GeneralSubtree[] h;
    public GeneralSubtree[] i;

    public NameConstraints(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objects.nextElement());
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 0) {
                this.h = b(ASN1Sequence.getInstance(aSN1TaggedObject, false));
            } else if (tagNo != 1) {
                throw new IllegalArgumentException("Unknown tag encountered: " + aSN1TaggedObject.getTagNo());
            } else {
                this.i = b(ASN1Sequence.getInstance(aSN1TaggedObject, false));
            }
        }
    }

    public NameConstraints(GeneralSubtree[] generalSubtreeArr, GeneralSubtree[] generalSubtreeArr2) {
        this.h = a(generalSubtreeArr);
        this.i = a(generalSubtreeArr2);
    }

    public static GeneralSubtree[] a(GeneralSubtree[] generalSubtreeArr) {
        if (generalSubtreeArr != null) {
            int length = generalSubtreeArr.length;
            GeneralSubtree[] generalSubtreeArr2 = new GeneralSubtree[length];
            System.arraycopy(generalSubtreeArr, 0, generalSubtreeArr2, 0, length);
            return generalSubtreeArr2;
        }
        return null;
    }

    public static NameConstraints getInstance(Object obj) {
        if (obj instanceof NameConstraints) {
            return (NameConstraints) obj;
        }
        if (obj != null) {
            return new NameConstraints(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public final GeneralSubtree[] b(ASN1Sequence aSN1Sequence) {
        int size = aSN1Sequence.size();
        GeneralSubtree[] generalSubtreeArr = new GeneralSubtree[size];
        for (int i = 0; i != size; i++) {
            generalSubtreeArr[i] = GeneralSubtree.getInstance(aSN1Sequence.getObjectAt(i));
        }
        return generalSubtreeArr;
    }

    public GeneralSubtree[] getExcludedSubtrees() {
        return a(this.i);
    }

    public GeneralSubtree[] getPermittedSubtrees() {
        return a(this.h);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.h != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, new DERSequence(this.h)));
        }
        if (this.i != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, new DERSequence(this.i)));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
