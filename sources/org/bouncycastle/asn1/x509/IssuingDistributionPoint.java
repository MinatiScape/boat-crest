package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.util.Strings;
/* loaded from: classes12.dex */
public class IssuingDistributionPoint extends ASN1Object {
    public DistributionPointName h;
    public boolean i;
    public boolean j;
    public ReasonFlags k;
    public boolean l;
    public boolean m;
    public ASN1Sequence n;

    public IssuingDistributionPoint(ASN1Sequence aSN1Sequence) {
        this.n = aSN1Sequence;
        for (int i = 0; i != aSN1Sequence.size(); i++) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(i));
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 0) {
                this.h = DistributionPointName.getInstance(aSN1TaggedObject, true);
            } else if (tagNo == 1) {
                this.i = ASN1Boolean.getInstance(aSN1TaggedObject, false).isTrue();
            } else if (tagNo == 2) {
                this.j = ASN1Boolean.getInstance(aSN1TaggedObject, false).isTrue();
            } else if (tagNo == 3) {
                this.k = new ReasonFlags(DERBitString.getInstance(aSN1TaggedObject, false));
            } else if (tagNo == 4) {
                this.l = ASN1Boolean.getInstance(aSN1TaggedObject, false).isTrue();
            } else if (tagNo != 5) {
                throw new IllegalArgumentException("unknown tag in IssuingDistributionPoint");
            } else {
                this.m = ASN1Boolean.getInstance(aSN1TaggedObject, false).isTrue();
            }
        }
    }

    public IssuingDistributionPoint(DistributionPointName distributionPointName, boolean z, boolean z2) {
        this(distributionPointName, false, false, null, z, z2);
    }

    public IssuingDistributionPoint(DistributionPointName distributionPointName, boolean z, boolean z2, ReasonFlags reasonFlags, boolean z3, boolean z4) {
        this.h = distributionPointName;
        this.l = z3;
        this.m = z4;
        this.j = z2;
        this.i = z;
        this.k = reasonFlags;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (distributionPointName != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, distributionPointName));
        }
        if (z) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, ASN1Boolean.getInstance(true)));
        }
        if (z2) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, ASN1Boolean.getInstance(true)));
        }
        if (reasonFlags != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 3, reasonFlags));
        }
        if (z3) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 4, ASN1Boolean.getInstance(true)));
        }
        if (z4) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 5, ASN1Boolean.getInstance(true)));
        }
        this.n = new DERSequence(aSN1EncodableVector);
    }

    public static IssuingDistributionPoint getInstance(Object obj) {
        if (obj instanceof IssuingDistributionPoint) {
            return (IssuingDistributionPoint) obj;
        }
        if (obj != null) {
            return new IssuingDistributionPoint(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static IssuingDistributionPoint getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public final void a(StringBuffer stringBuffer, String str, String str2, String str3) {
        stringBuffer.append("    ");
        stringBuffer.append(str2);
        stringBuffer.append(":");
        stringBuffer.append(str);
        stringBuffer.append("    ");
        stringBuffer.append("    ");
        stringBuffer.append(str3);
        stringBuffer.append(str);
    }

    public final String b(boolean z) {
        return z ? "true" : "false";
    }

    public DistributionPointName getDistributionPoint() {
        return this.h;
    }

    public ReasonFlags getOnlySomeReasons() {
        return this.k;
    }

    public boolean isIndirectCRL() {
        return this.l;
    }

    public boolean onlyContainsAttributeCerts() {
        return this.m;
    }

    public boolean onlyContainsCACerts() {
        return this.j;
    }

    public boolean onlyContainsUserCerts() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.n;
    }

    public String toString() {
        String lineSeparator = Strings.lineSeparator();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("IssuingDistributionPoint: [");
        stringBuffer.append(lineSeparator);
        DistributionPointName distributionPointName = this.h;
        if (distributionPointName != null) {
            a(stringBuffer, lineSeparator, "distributionPoint", distributionPointName.toString());
        }
        boolean z = this.i;
        if (z) {
            a(stringBuffer, lineSeparator, "onlyContainsUserCerts", b(z));
        }
        boolean z2 = this.j;
        if (z2) {
            a(stringBuffer, lineSeparator, "onlyContainsCACerts", b(z2));
        }
        ReasonFlags reasonFlags = this.k;
        if (reasonFlags != null) {
            a(stringBuffer, lineSeparator, "onlySomeReasons", reasonFlags.toString());
        }
        boolean z3 = this.m;
        if (z3) {
            a(stringBuffer, lineSeparator, "onlyContainsAttributeCerts", b(z3));
        }
        boolean z4 = this.l;
        if (z4) {
            a(stringBuffer, lineSeparator, "indirectCRL", b(z4));
        }
        stringBuffer.append("]");
        stringBuffer.append(lineSeparator);
        return stringBuffer.toString();
    }
}
