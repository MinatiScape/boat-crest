package org.bouncycastle.asn1.dvcs;

import java.util.Arrays;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.PolicyInformation;
/* loaded from: classes12.dex */
public class PathProcInput extends ASN1Object {
    public PolicyInformation[] h;
    public boolean i;
    public boolean j;
    public boolean k;

    public PathProcInput(PolicyInformation[] policyInformationArr) {
        this.i = false;
        this.j = false;
        this.k = false;
        this.h = policyInformationArr;
    }

    public PathProcInput(PolicyInformation[] policyInformationArr, boolean z, boolean z2, boolean z3) {
        this.i = false;
        this.j = false;
        this.k = false;
        this.h = policyInformationArr;
        this.i = z;
        this.j = z2;
        this.k = z3;
    }

    public static PolicyInformation[] a(ASN1Sequence aSN1Sequence) {
        int size = aSN1Sequence.size();
        PolicyInformation[] policyInformationArr = new PolicyInformation[size];
        for (int i = 0; i != size; i++) {
            policyInformationArr[i] = PolicyInformation.getInstance(aSN1Sequence.getObjectAt(i));
        }
        return policyInformationArr;
    }

    public static PathProcInput getInstance(Object obj) {
        if (obj instanceof PathProcInput) {
            return (PathProcInput) obj;
        }
        if (obj != null) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(obj);
            PathProcInput pathProcInput = new PathProcInput(a(ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(0))));
            for (int i = 1; i < aSN1Sequence.size(); i++) {
                ASN1Encodable objectAt = aSN1Sequence.getObjectAt(i);
                if (objectAt instanceof ASN1Boolean) {
                    pathProcInput.d(ASN1Boolean.getInstance(objectAt).isTrue());
                } else if (objectAt instanceof ASN1TaggedObject) {
                    ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objectAt);
                    int tagNo = aSN1TaggedObject.getTagNo();
                    if (tagNo == 0) {
                        pathProcInput.b(ASN1Boolean.getInstance(aSN1TaggedObject, false).isTrue());
                    } else if (tagNo != 1) {
                        throw new IllegalArgumentException("Unknown tag encountered: " + aSN1TaggedObject.getTagNo());
                    } else {
                        pathProcInput.c(ASN1Boolean.getInstance(aSN1TaggedObject, false).isTrue());
                    }
                } else {
                    continue;
                }
            }
            return pathProcInput;
        }
        return null;
    }

    public static PathProcInput getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public final void b(boolean z) {
        this.j = z;
    }

    public final void c(boolean z) {
        this.k = z;
    }

    public final void d(boolean z) {
        this.i = z;
    }

    public PolicyInformation[] getAcceptablePolicySet() {
        return this.h;
    }

    public boolean isExplicitPolicyReqd() {
        return this.j;
    }

    public boolean isInhibitAnyPolicy() {
        return this.k;
    }

    public boolean isInhibitPolicyMapping() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        int i = 0;
        while (true) {
            PolicyInformation[] policyInformationArr = this.h;
            if (i == policyInformationArr.length) {
                break;
            }
            aSN1EncodableVector2.add(policyInformationArr[i]);
            i++;
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        boolean z = this.i;
        if (z) {
            aSN1EncodableVector.add(ASN1Boolean.getInstance(z));
        }
        if (this.j) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, ASN1Boolean.getInstance(this.j)));
        }
        if (this.k) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, ASN1Boolean.getInstance(this.k)));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        return "PathProcInput: {\nacceptablePolicySet: " + Arrays.asList(this.h) + "\ninhibitPolicyMapping: " + this.i + "\nexplicitPolicyReqd: " + this.j + "\ninhibitAnyPolicy: " + this.k + "\n}\n";
    }
}
