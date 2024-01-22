package org.bouncycastle.asn1.isismtt.x509;

import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class DeclarationOfMajority extends ASN1Object implements ASN1Choice {
    public static final int dateOfBirth = 2;
    public static final int fullAgeAtCountry = 1;
    public static final int notYoungerThan = 0;
    public ASN1TaggedObject h;

    public DeclarationOfMajority(int i) {
        this.h = new DERTaggedObject(false, 0, new ASN1Integer(i));
    }

    public DeclarationOfMajority(ASN1GeneralizedTime aSN1GeneralizedTime) {
        this.h = new DERTaggedObject(false, 2, aSN1GeneralizedTime);
    }

    public DeclarationOfMajority(ASN1TaggedObject aSN1TaggedObject) {
        if (aSN1TaggedObject.getTagNo() <= 2) {
            this.h = aSN1TaggedObject;
            return;
        }
        throw new IllegalArgumentException("Bad tag number: " + aSN1TaggedObject.getTagNo());
    }

    public DeclarationOfMajority(boolean z, String str) {
        if (str.length() > 2) {
            throw new IllegalArgumentException("country can only be 2 characters");
        }
        if (z) {
            this.h = new DERTaggedObject(false, 1, new DERSequence(new DERPrintableString(str, true)));
            return;
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(ASN1Boolean.FALSE);
        aSN1EncodableVector.add(new DERPrintableString(str, true));
        this.h = new DERTaggedObject(false, 1, new DERSequence(aSN1EncodableVector));
    }

    public static DeclarationOfMajority getInstance(Object obj) {
        if (obj == null || (obj instanceof DeclarationOfMajority)) {
            return (DeclarationOfMajority) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new DeclarationOfMajority((ASN1TaggedObject) obj);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public ASN1Sequence fullAgeAtCountry() {
        if (this.h.getTagNo() != 1) {
            return null;
        }
        return ASN1Sequence.getInstance(this.h, false);
    }

    public ASN1GeneralizedTime getDateOfBirth() {
        if (this.h.getTagNo() != 2) {
            return null;
        }
        return ASN1GeneralizedTime.getInstance(this.h, false);
    }

    public int getType() {
        return this.h.getTagNo();
    }

    public int notYoungerThan() {
        if (this.h.getTagNo() != 0) {
            return -1;
        }
        return ASN1Integer.getInstance(this.h, false).getValue().intValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }
}
