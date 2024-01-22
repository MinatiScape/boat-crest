package org.bouncycastle.asn1.isismtt.x509;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.DirectoryString;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.IssuerSerial;
/* loaded from: classes12.dex */
public class ProcurationSyntax extends ASN1Object {
    public String h;
    public DirectoryString i;
    public GeneralName j;
    public IssuerSerial k;

    public ProcurationSyntax(String str, DirectoryString directoryString, GeneralName generalName) {
        this.h = str;
        this.i = directoryString;
        this.j = generalName;
        this.k = null;
    }

    public ProcurationSyntax(String str, DirectoryString directoryString, IssuerSerial issuerSerial) {
        this.h = str;
        this.i = directoryString;
        this.j = null;
        this.k = issuerSerial;
    }

    public ProcurationSyntax(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objects.nextElement());
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 1) {
                this.h = DERPrintableString.getInstance(aSN1TaggedObject, true).getString();
            } else if (tagNo == 2) {
                this.i = DirectoryString.getInstance(aSN1TaggedObject, true);
            } else if (tagNo != 3) {
                throw new IllegalArgumentException("Bad tag number: " + aSN1TaggedObject.getTagNo());
            } else {
                ASN1Primitive object = aSN1TaggedObject.getObject();
                if (object instanceof ASN1TaggedObject) {
                    this.j = GeneralName.getInstance(object);
                } else {
                    this.k = IssuerSerial.getInstance(object);
                }
            }
        }
    }

    public static ProcurationSyntax getInstance(Object obj) {
        if (obj == null || (obj instanceof ProcurationSyntax)) {
            return (ProcurationSyntax) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new ProcurationSyntax((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public IssuerSerial getCertRef() {
        return this.k;
    }

    public String getCountry() {
        return this.h;
    }

    public GeneralName getThirdPerson() {
        return this.j;
    }

    public DirectoryString getTypeOfSubstitution() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.h != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, new DERPrintableString(this.h, true)));
        }
        if (this.i != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, this.i));
        }
        aSN1EncodableVector.add(this.j != null ? new DERTaggedObject(true, 3, this.j) : new DERTaggedObject(true, 3, this.k));
        return new DERSequence(aSN1EncodableVector);
    }
}
