package org.bouncycastle.asn1.x509.qualified;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.GeneralName;
/* loaded from: classes12.dex */
public class SemanticsInformation extends ASN1Object {
    public ASN1ObjectIdentifier h;
    public GeneralName[] i;

    public SemanticsInformation(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.h = aSN1ObjectIdentifier;
        this.i = null;
    }

    public SemanticsInformation(ASN1ObjectIdentifier aSN1ObjectIdentifier, GeneralName[] generalNameArr) {
        this.h = aSN1ObjectIdentifier;
        this.i = a(generalNameArr);
    }

    public SemanticsInformation(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        if (aSN1Sequence.size() < 1) {
            throw new IllegalArgumentException("no objects in SemanticsInformation");
        }
        Object nextElement = objects.nextElement();
        if (nextElement instanceof ASN1ObjectIdentifier) {
            this.h = ASN1ObjectIdentifier.getInstance(nextElement);
            nextElement = objects.hasMoreElements() ? objects.nextElement() : null;
        }
        if (nextElement != null) {
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(nextElement);
            this.i = new GeneralName[aSN1Sequence2.size()];
            for (int i = 0; i < aSN1Sequence2.size(); i++) {
                this.i[i] = GeneralName.getInstance(aSN1Sequence2.getObjectAt(i));
            }
        }
    }

    public SemanticsInformation(GeneralName[] generalNameArr) {
        this.h = null;
        this.i = a(generalNameArr);
    }

    public static GeneralName[] a(GeneralName[] generalNameArr) {
        if (generalNameArr != null) {
            GeneralName[] generalNameArr2 = new GeneralName[generalNameArr.length];
            System.arraycopy(generalNameArr, 0, generalNameArr2, 0, generalNameArr.length);
            return generalNameArr2;
        }
        return null;
    }

    public static SemanticsInformation getInstance(Object obj) {
        if (obj instanceof SemanticsInformation) {
            return (SemanticsInformation) obj;
        }
        if (obj != null) {
            return new SemanticsInformation(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public GeneralName[] getNameRegistrationAuthorities() {
        return a(this.i);
    }

    public ASN1ObjectIdentifier getSemanticsIdentifier() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1ObjectIdentifier aSN1ObjectIdentifier = this.h;
        if (aSN1ObjectIdentifier != null) {
            aSN1EncodableVector.add(aSN1ObjectIdentifier);
        }
        if (this.i != null) {
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            int i = 0;
            while (true) {
                GeneralName[] generalNameArr = this.i;
                if (i >= generalNameArr.length) {
                    break;
                }
                aSN1EncodableVector2.add(generalNameArr[i]);
                i++;
            }
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
