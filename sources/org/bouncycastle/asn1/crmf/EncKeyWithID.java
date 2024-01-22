package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.GeneralName;
/* loaded from: classes12.dex */
public class EncKeyWithID extends ASN1Object {
    public final PrivateKeyInfo h;
    public final ASN1Encodable i;

    public EncKeyWithID(ASN1Sequence aSN1Sequence) {
        ASN1Encodable aSN1Encodable;
        this.h = PrivateKeyInfo.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() > 1) {
            boolean z = aSN1Sequence.getObjectAt(1) instanceof DERUTF8String;
            aSN1Encodable = aSN1Sequence.getObjectAt(1);
            if (!z) {
                aSN1Encodable = GeneralName.getInstance(aSN1Encodable);
            }
        } else {
            aSN1Encodable = null;
        }
        this.i = aSN1Encodable;
    }

    public EncKeyWithID(PrivateKeyInfo privateKeyInfo) {
        this.h = privateKeyInfo;
        this.i = null;
    }

    public EncKeyWithID(PrivateKeyInfo privateKeyInfo, DERUTF8String dERUTF8String) {
        this.h = privateKeyInfo;
        this.i = dERUTF8String;
    }

    public EncKeyWithID(PrivateKeyInfo privateKeyInfo, GeneralName generalName) {
        this.h = privateKeyInfo;
        this.i = generalName;
    }

    public static EncKeyWithID getInstance(Object obj) {
        if (obj instanceof EncKeyWithID) {
            return (EncKeyWithID) obj;
        }
        if (obj != null) {
            return new EncKeyWithID(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Encodable getIdentifier() {
        return this.i;
    }

    public PrivateKeyInfo getPrivateKey() {
        return this.h;
    }

    public boolean hasIdentifier() {
        return this.i != null;
    }

    public boolean isIdentifierUTF8String() {
        return this.i instanceof DERUTF8String;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        ASN1Encodable aSN1Encodable = this.i;
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(aSN1Encodable);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
