package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.crmf.CertTemplate;
/* loaded from: classes12.dex */
public class ModCertTemplate extends ASN1Object {
    public final BodyPartPath h;
    public final BodyPartList i;
    public final boolean j;
    public final CertTemplate k;

    public ModCertTemplate(ASN1Sequence aSN1Sequence) {
        ASN1Encodable objectAt;
        if (aSN1Sequence.size() != 4 && aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.h = BodyPartPath.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = BodyPartList.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() == 4) {
            this.j = ASN1Boolean.getInstance(aSN1Sequence.getObjectAt(2)).isTrue();
            objectAt = aSN1Sequence.getObjectAt(3);
        } else {
            this.j = true;
            objectAt = aSN1Sequence.getObjectAt(2);
        }
        this.k = CertTemplate.getInstance(objectAt);
    }

    public ModCertTemplate(BodyPartPath bodyPartPath, BodyPartList bodyPartList, boolean z, CertTemplate certTemplate) {
        this.h = bodyPartPath;
        this.i = bodyPartList;
        this.j = z;
        this.k = certTemplate;
    }

    public static ModCertTemplate getInstance(Object obj) {
        if (obj instanceof ModCertTemplate) {
            return (ModCertTemplate) obj;
        }
        if (obj != null) {
            return new ModCertTemplate(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BodyPartList getCertReferences() {
        return this.i;
    }

    public CertTemplate getCertTemplate() {
        return this.k;
    }

    public BodyPartPath getPkiDataReference() {
        return this.h;
    }

    public boolean isReplacingFields() {
        return this.j;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        boolean z = this.j;
        if (!z) {
            aSN1EncodableVector.add(ASN1Boolean.getInstance(z));
        }
        aSN1EncodableVector.add(this.k);
        return new DERSequence(aSN1EncodableVector);
    }
}
