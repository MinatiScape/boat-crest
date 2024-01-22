package org.bouncycastle.asn1.ess;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.asn1.x509.IssuerSerial;
/* loaded from: classes12.dex */
public class OtherCertID extends ASN1Object {
    public ASN1Encodable h;
    public IssuerSerial i;

    public OtherCertID(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        boolean z = aSN1Sequence.getObjectAt(0).toASN1Primitive() instanceof ASN1OctetString;
        ASN1Encodable objectAt = aSN1Sequence.getObjectAt(0);
        this.h = z ? ASN1OctetString.getInstance(objectAt) : DigestInfo.getInstance(objectAt);
        if (aSN1Sequence.size() > 1) {
            this.i = IssuerSerial.getInstance(aSN1Sequence.getObjectAt(1));
        }
    }

    public OtherCertID(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
        this.h = new DigestInfo(algorithmIdentifier, bArr);
    }

    public OtherCertID(AlgorithmIdentifier algorithmIdentifier, byte[] bArr, IssuerSerial issuerSerial) {
        this.h = new DigestInfo(algorithmIdentifier, bArr);
        this.i = issuerSerial;
    }

    public static OtherCertID getInstance(Object obj) {
        if (obj instanceof OtherCertID) {
            return (OtherCertID) obj;
        }
        if (obj != null) {
            return new OtherCertID(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public AlgorithmIdentifier getAlgorithmHash() {
        return this.h.toASN1Primitive() instanceof ASN1OctetString ? new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1) : DigestInfo.getInstance(this.h).getAlgorithmId();
    }

    public byte[] getCertHash() {
        return this.h.toASN1Primitive() instanceof ASN1OctetString ? ((ASN1OctetString) this.h.toASN1Primitive()).getOctets() : DigestInfo.getInstance(this.h).getDigest();
    }

    public IssuerSerial getIssuerSerial() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        IssuerSerial issuerSerial = this.i;
        if (issuerSerial != null) {
            aSN1EncodableVector.add(issuerSerial);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
