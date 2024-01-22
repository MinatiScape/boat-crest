package org.bouncycastle.asn1.pkcs;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class PBES2Parameters extends ASN1Object implements PKCSObjectIdentifiers {
    public KeyDerivationFunc h;
    public EncryptionScheme i;

    public PBES2Parameters(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(((ASN1Encodable) objects.nextElement()).toASN1Primitive());
        ASN1Encodable objectAt = aSN1Sequence2.getObjectAt(0);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.id_PBKDF2;
        if (objectAt.equals(aSN1ObjectIdentifier)) {
            this.h = new KeyDerivationFunc(aSN1ObjectIdentifier, PBKDF2Params.getInstance(aSN1Sequence2.getObjectAt(1)));
        } else {
            this.h = KeyDerivationFunc.getInstance(aSN1Sequence2);
        }
        this.i = EncryptionScheme.getInstance(objects.nextElement());
    }

    public PBES2Parameters(KeyDerivationFunc keyDerivationFunc, EncryptionScheme encryptionScheme) {
        this.h = keyDerivationFunc;
        this.i = encryptionScheme;
    }

    public static PBES2Parameters getInstance(Object obj) {
        if (obj instanceof PBES2Parameters) {
            return (PBES2Parameters) obj;
        }
        if (obj != null) {
            return new PBES2Parameters(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public EncryptionScheme getEncryptionScheme() {
        return this.i;
    }

    public KeyDerivationFunc getKeyDerivationFunc() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        return new DERSequence(aSN1EncodableVector);
    }
}
