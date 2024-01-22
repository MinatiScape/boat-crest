package org.bouncycastle.asn1.cmp;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.GeneralName;
/* loaded from: classes12.dex */
public class PKIHeader extends ASN1Object {
    public static final int CMP_1999 = 1;
    public static final int CMP_2000 = 2;
    public static final GeneralName NULL_NAME = new GeneralName(X500Name.getInstance(new DERSequence()));
    public ASN1Integer h;
    public GeneralName i;
    public GeneralName j;
    public ASN1GeneralizedTime k;
    public AlgorithmIdentifier l;
    public ASN1OctetString m;
    public ASN1OctetString n;
    public ASN1OctetString o;
    public ASN1OctetString p;
    public ASN1OctetString q;
    public PKIFreeText r;
    public ASN1Sequence s;

    public PKIHeader(int i, GeneralName generalName, GeneralName generalName2) {
        this(new ASN1Integer(i), generalName, generalName2);
    }

    public PKIHeader(ASN1Integer aSN1Integer, GeneralName generalName, GeneralName generalName2) {
        this.h = aSN1Integer;
        this.i = generalName;
        this.j = generalName2;
    }

    public PKIHeader(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = ASN1Integer.getInstance(objects.nextElement());
        this.i = GeneralName.getInstance(objects.nextElement());
        this.j = GeneralName.getInstance(objects.nextElement());
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects.nextElement();
            switch (aSN1TaggedObject.getTagNo()) {
                case 0:
                    this.k = ASN1GeneralizedTime.getInstance(aSN1TaggedObject, true);
                    break;
                case 1:
                    this.l = AlgorithmIdentifier.getInstance(aSN1TaggedObject, true);
                    break;
                case 2:
                    this.m = ASN1OctetString.getInstance(aSN1TaggedObject, true);
                    break;
                case 3:
                    this.n = ASN1OctetString.getInstance(aSN1TaggedObject, true);
                    break;
                case 4:
                    this.o = ASN1OctetString.getInstance(aSN1TaggedObject, true);
                    break;
                case 5:
                    this.p = ASN1OctetString.getInstance(aSN1TaggedObject, true);
                    break;
                case 6:
                    this.q = ASN1OctetString.getInstance(aSN1TaggedObject, true);
                    break;
                case 7:
                    this.r = PKIFreeText.getInstance(aSN1TaggedObject, true);
                    break;
                case 8:
                    this.s = ASN1Sequence.getInstance(aSN1TaggedObject, true);
                    break;
                default:
                    throw new IllegalArgumentException("unknown tag number: " + aSN1TaggedObject.getTagNo());
            }
        }
    }

    public static PKIHeader getInstance(Object obj) {
        if (obj instanceof PKIHeader) {
            return (PKIHeader) obj;
        }
        if (obj != null) {
            return new PKIHeader(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public final void a(ASN1EncodableVector aSN1EncodableVector, int i, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, i, aSN1Encodable));
        }
    }

    public PKIFreeText getFreeText() {
        return this.r;
    }

    public InfoTypeAndValue[] getGeneralInfo() {
        ASN1Sequence aSN1Sequence = this.s;
        if (aSN1Sequence == null) {
            return null;
        }
        int size = aSN1Sequence.size();
        InfoTypeAndValue[] infoTypeAndValueArr = new InfoTypeAndValue[size];
        for (int i = 0; i < size; i++) {
            infoTypeAndValueArr[i] = InfoTypeAndValue.getInstance(this.s.getObjectAt(i));
        }
        return infoTypeAndValueArr;
    }

    public ASN1GeneralizedTime getMessageTime() {
        return this.k;
    }

    public AlgorithmIdentifier getProtectionAlg() {
        return this.l;
    }

    public ASN1Integer getPvno() {
        return this.h;
    }

    public ASN1OctetString getRecipKID() {
        return this.n;
    }

    public ASN1OctetString getRecipNonce() {
        return this.q;
    }

    public GeneralName getRecipient() {
        return this.j;
    }

    public GeneralName getSender() {
        return this.i;
    }

    public ASN1OctetString getSenderKID() {
        return this.m;
    }

    public ASN1OctetString getSenderNonce() {
        return this.p;
    }

    public ASN1OctetString getTransactionID() {
        return this.o;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        a(aSN1EncodableVector, 0, this.k);
        a(aSN1EncodableVector, 1, this.l);
        a(aSN1EncodableVector, 2, this.m);
        a(aSN1EncodableVector, 3, this.n);
        a(aSN1EncodableVector, 4, this.o);
        a(aSN1EncodableVector, 5, this.p);
        a(aSN1EncodableVector, 6, this.q);
        a(aSN1EncodableVector, 7, this.r);
        a(aSN1EncodableVector, 8, this.s);
        return new DERSequence(aSN1EncodableVector);
    }
}
