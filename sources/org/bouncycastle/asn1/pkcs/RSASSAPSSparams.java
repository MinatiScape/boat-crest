package org.bouncycastle.asn1.pkcs;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class RSASSAPSSparams extends ASN1Object {
    public static final AlgorithmIdentifier DEFAULT_HASH_ALGORITHM;
    public static final AlgorithmIdentifier DEFAULT_MASK_GEN_FUNCTION;
    public static final ASN1Integer DEFAULT_SALT_LENGTH;
    public static final ASN1Integer DEFAULT_TRAILER_FIELD;
    public AlgorithmIdentifier h;
    public AlgorithmIdentifier i;
    public ASN1Integer j;
    public ASN1Integer k;

    static {
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
        DEFAULT_HASH_ALGORITHM = algorithmIdentifier;
        DEFAULT_MASK_GEN_FUNCTION = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, algorithmIdentifier);
        DEFAULT_SALT_LENGTH = new ASN1Integer(20L);
        DEFAULT_TRAILER_FIELD = new ASN1Integer(1L);
    }

    public RSASSAPSSparams() {
        this.h = DEFAULT_HASH_ALGORITHM;
        this.i = DEFAULT_MASK_GEN_FUNCTION;
        this.j = DEFAULT_SALT_LENGTH;
        this.k = DEFAULT_TRAILER_FIELD;
    }

    public RSASSAPSSparams(ASN1Sequence aSN1Sequence) {
        this.h = DEFAULT_HASH_ALGORITHM;
        this.i = DEFAULT_MASK_GEN_FUNCTION;
        this.j = DEFAULT_SALT_LENGTH;
        this.k = DEFAULT_TRAILER_FIELD;
        for (int i = 0; i != aSN1Sequence.size(); i++) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(i);
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 0) {
                this.h = AlgorithmIdentifier.getInstance(aSN1TaggedObject, true);
            } else if (tagNo == 1) {
                this.i = AlgorithmIdentifier.getInstance(aSN1TaggedObject, true);
            } else if (tagNo == 2) {
                this.j = ASN1Integer.getInstance(aSN1TaggedObject, true);
            } else if (tagNo != 3) {
                throw new IllegalArgumentException("unknown tag");
            } else {
                this.k = ASN1Integer.getInstance(aSN1TaggedObject, true);
            }
        }
    }

    public RSASSAPSSparams(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, ASN1Integer aSN1Integer, ASN1Integer aSN1Integer2) {
        this.h = algorithmIdentifier;
        this.i = algorithmIdentifier2;
        this.j = aSN1Integer;
        this.k = aSN1Integer2;
    }

    public static RSASSAPSSparams getInstance(Object obj) {
        if (obj instanceof RSASSAPSSparams) {
            return (RSASSAPSSparams) obj;
        }
        if (obj != null) {
            return new RSASSAPSSparams(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public AlgorithmIdentifier getHashAlgorithm() {
        return this.h;
    }

    public AlgorithmIdentifier getMaskGenAlgorithm() {
        return this.i;
    }

    public BigInteger getSaltLength() {
        return this.j.getValue();
    }

    public BigInteger getTrailerField() {
        return this.k.getValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (!this.h.equals(DEFAULT_HASH_ALGORITHM)) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.h));
        }
        if (!this.i.equals(DEFAULT_MASK_GEN_FUNCTION)) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, this.i));
        }
        if (!this.j.equals(DEFAULT_SALT_LENGTH)) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, this.j));
        }
        if (!this.k.equals(DEFAULT_TRAILER_FIELD)) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 3, this.k));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
