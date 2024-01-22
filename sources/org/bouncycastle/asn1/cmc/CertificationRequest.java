package org.bouncycastle.asn1.cmc;

import java.io.IOException;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class CertificationRequest extends ASN1Object {
    public static final ASN1Integer k = new ASN1Integer(0);
    public final b h;
    public final AlgorithmIdentifier i;
    public final DERBitString j;

    /* loaded from: classes12.dex */
    public class b extends ASN1Object {
        public final ASN1Integer h;
        public final X500Name i;
        public final ASN1Sequence j;
        public final ASN1Set k;

        public b(CertificationRequest certificationRequest, ASN1Sequence aSN1Sequence) {
            if (aSN1Sequence.size() != 4) {
                throw new IllegalArgumentException("incorrect sequence size for CertificationRequestInfo");
            }
            this.h = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
            this.i = X500Name.getInstance(aSN1Sequence.getObjectAt(1));
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(2));
            this.j = aSN1Sequence2;
            if (aSN1Sequence2.size() != 2) {
                throw new IllegalArgumentException("incorrect subjectPublicKeyInfo size for CertificationRequestInfo");
            }
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(3);
            if (aSN1TaggedObject.getTagNo() != 0) {
                throw new IllegalArgumentException("incorrect tag number on attributes for CertificationRequestInfo");
            }
            this.k = ASN1Set.getInstance(aSN1TaggedObject, false);
        }

        public b(CertificationRequest certificationRequest, X500Name x500Name, AlgorithmIdentifier algorithmIdentifier, DERBitString dERBitString, ASN1Set aSN1Set) {
            this.h = CertificationRequest.k;
            this.i = x500Name;
            this.j = new DERSequence(new ASN1Encodable[]{algorithmIdentifier, dERBitString});
            this.k = aSN1Set;
        }

        public final ASN1Sequence e() {
            return this.j;
        }

        public final ASN1Set getAttributes() {
            return this.k;
        }

        public final X500Name getSubject() {
            return this.i;
        }

        public final ASN1Integer getVersion() {
            return this.h;
        }

        @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
        public ASN1Primitive toASN1Primitive() {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            aSN1EncodableVector.add(this.h);
            aSN1EncodableVector.add(this.i);
            aSN1EncodableVector.add(this.j);
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.k));
            return new DERSequence(aSN1EncodableVector);
        }
    }

    public CertificationRequest(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.h = new b(ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(0)));
        this.i = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.j = DERBitString.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public CertificationRequest(X500Name x500Name, AlgorithmIdentifier algorithmIdentifier, DERBitString dERBitString, ASN1Set aSN1Set, AlgorithmIdentifier algorithmIdentifier2, DERBitString dERBitString2) {
        this.h = new b(x500Name, algorithmIdentifier, dERBitString, aSN1Set);
        this.i = algorithmIdentifier2;
        this.j = dERBitString2;
    }

    public static CertificationRequest getInstance(Object obj) {
        if (obj instanceof CertificationRequest) {
            return (CertificationRequest) obj;
        }
        if (obj != null) {
            return new CertificationRequest(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Set getAttributes() {
        return this.h.getAttributes();
    }

    public DERBitString getSignature() {
        return this.j;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.i;
    }

    public X500Name getSubject() {
        return this.h.getSubject();
    }

    public DERBitString getSubjectPublicKey() {
        return DERBitString.getInstance(this.h.e().getObjectAt(1));
    }

    public AlgorithmIdentifier getSubjectPublicKeyAlgorithm() {
        return AlgorithmIdentifier.getInstance(this.h.e().getObjectAt(0));
    }

    public BigInteger getVersion() {
        return this.h.getVersion().getValue();
    }

    public ASN1Primitive parsePublicKey() throws IOException {
        return ASN1Primitive.fromByteArray(getSubjectPublicKey().getOctets());
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        return new DERSequence(aSN1EncodableVector);
    }
}
