package org.bouncycastle.asn1.x509.qualified;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class BiometricData extends ASN1Object {
    public TypeOfBiometricData h;
    public AlgorithmIdentifier i;
    public ASN1OctetString j;
    public DERIA5String k;

    public BiometricData(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = TypeOfBiometricData.getInstance(objects.nextElement());
        this.i = AlgorithmIdentifier.getInstance(objects.nextElement());
        this.j = ASN1OctetString.getInstance(objects.nextElement());
        if (objects.hasMoreElements()) {
            this.k = DERIA5String.getInstance(objects.nextElement());
        }
    }

    public BiometricData(TypeOfBiometricData typeOfBiometricData, AlgorithmIdentifier algorithmIdentifier, ASN1OctetString aSN1OctetString) {
        this.h = typeOfBiometricData;
        this.i = algorithmIdentifier;
        this.j = aSN1OctetString;
        this.k = null;
    }

    public BiometricData(TypeOfBiometricData typeOfBiometricData, AlgorithmIdentifier algorithmIdentifier, ASN1OctetString aSN1OctetString, DERIA5String dERIA5String) {
        this.h = typeOfBiometricData;
        this.i = algorithmIdentifier;
        this.j = aSN1OctetString;
        this.k = dERIA5String;
    }

    public static BiometricData getInstance(Object obj) {
        if (obj instanceof BiometricData) {
            return (BiometricData) obj;
        }
        if (obj != null) {
            return new BiometricData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1OctetString getBiometricDataHash() {
        return this.j;
    }

    public AlgorithmIdentifier getHashAlgorithm() {
        return this.i;
    }

    public DERIA5String getSourceDataUri() {
        return this.k;
    }

    public TypeOfBiometricData getTypeOfBiometricData() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        DERIA5String dERIA5String = this.k;
        if (dERIA5String != null) {
            aSN1EncodableVector.add(dERIA5String);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
