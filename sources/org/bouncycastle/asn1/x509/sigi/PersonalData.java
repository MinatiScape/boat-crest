package org.bouncycastle.asn1.x509.sigi;

import java.math.BigInteger;
import java.util.Enumeration;
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
import org.bouncycastle.asn1.x500.DirectoryString;
/* loaded from: classes12.dex */
public class PersonalData extends ASN1Object {
    public NameOrPseudonym h;
    public BigInteger i;
    public ASN1GeneralizedTime j;
    public DirectoryString k;
    public String l;
    public DirectoryString m;

    public PersonalData(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = NameOrPseudonym.getInstance(objects.nextElement());
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objects.nextElement());
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 0) {
                this.i = ASN1Integer.getInstance(aSN1TaggedObject, false).getValue();
            } else if (tagNo == 1) {
                this.j = ASN1GeneralizedTime.getInstance(aSN1TaggedObject, false);
            } else if (tagNo == 2) {
                this.k = DirectoryString.getInstance(aSN1TaggedObject, true);
            } else if (tagNo == 3) {
                this.l = DERPrintableString.getInstance(aSN1TaggedObject, false).getString();
            } else if (tagNo != 4) {
                throw new IllegalArgumentException("Bad tag number: " + aSN1TaggedObject.getTagNo());
            } else {
                this.m = DirectoryString.getInstance(aSN1TaggedObject, true);
            }
        }
    }

    public PersonalData(NameOrPseudonym nameOrPseudonym, BigInteger bigInteger, ASN1GeneralizedTime aSN1GeneralizedTime, DirectoryString directoryString, String str, DirectoryString directoryString2) {
        this.h = nameOrPseudonym;
        this.j = aSN1GeneralizedTime;
        this.l = str;
        this.i = bigInteger;
        this.m = directoryString2;
        this.k = directoryString;
    }

    public static PersonalData getInstance(Object obj) {
        if (obj == null || (obj instanceof PersonalData)) {
            return (PersonalData) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new PersonalData((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public ASN1GeneralizedTime getDateOfBirth() {
        return this.j;
    }

    public String getGender() {
        return this.l;
    }

    public BigInteger getNameDistinguisher() {
        return this.i;
    }

    public NameOrPseudonym getNameOrPseudonym() {
        return this.h;
    }

    public DirectoryString getPlaceOfBirth() {
        return this.k;
    }

    public DirectoryString getPostalAddress() {
        return this.m;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        if (this.i != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, new ASN1Integer(this.i)));
        }
        if (this.j != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.j));
        }
        if (this.k != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, this.k));
        }
        if (this.l != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 3, new DERPrintableString(this.l, true)));
        }
        if (this.m != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 4, this.m));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
