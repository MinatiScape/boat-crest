package org.bouncycastle.asn1.cryptopro;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class GostR3410TransportParameters extends ASN1Object {
    public final ASN1ObjectIdentifier h;
    public final SubjectPublicKeyInfo i;
    public final byte[] j;

    public GostR3410TransportParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier, SubjectPublicKeyInfo subjectPublicKeyInfo, byte[] bArr) {
        this.h = aSN1ObjectIdentifier;
        this.i = subjectPublicKeyInfo;
        this.j = Arrays.clone(bArr);
    }

    public GostR3410TransportParameters(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.h = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
            this.j = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets();
            this.i = null;
        } else if (aSN1Sequence.size() == 3) {
            this.h = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
            this.i = SubjectPublicKeyInfo.getInstance(ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(1)), false);
            this.j = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(2)).getOctets();
        } else {
            throw new IllegalArgumentException("unknown sequence length: " + aSN1Sequence.size());
        }
    }

    public static GostR3410TransportParameters getInstance(Object obj) {
        if (obj instanceof GostR3410TransportParameters) {
            return (GostR3410TransportParameters) obj;
        }
        if (obj != null) {
            return new GostR3410TransportParameters(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static GostR3410TransportParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return new GostR3410TransportParameters(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1ObjectIdentifier getEncryptionParamSet() {
        return this.h;
    }

    public SubjectPublicKeyInfo getEphemeralPublicKey() {
        return this.i;
    }

    public byte[] getUkm() {
        return Arrays.clone(this.j);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        if (this.i != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.i));
        }
        aSN1EncodableVector.add(new DEROctetString(this.j));
        return new DERSequence(aSN1EncodableVector);
    }
}
