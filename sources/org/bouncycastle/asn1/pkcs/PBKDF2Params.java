package org.bouncycastle.asn1.pkcs;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class PBKDF2Params extends ASN1Object {
    public static final AlgorithmIdentifier l = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA1, DERNull.INSTANCE);
    public final ASN1OctetString h;
    public final ASN1Integer i;
    public final ASN1Integer j;
    public final AlgorithmIdentifier k;

    public PBKDF2Params(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = (ASN1OctetString) objects.nextElement();
        this.i = (ASN1Integer) objects.nextElement();
        if (objects.hasMoreElements()) {
            Object nextElement = objects.nextElement();
            if (nextElement instanceof ASN1Integer) {
                this.j = ASN1Integer.getInstance(nextElement);
                nextElement = objects.hasMoreElements() ? objects.nextElement() : null;
            } else {
                this.j = null;
            }
            if (nextElement != null) {
                this.k = AlgorithmIdentifier.getInstance(nextElement);
                return;
            }
        } else {
            this.j = null;
        }
        this.k = null;
    }

    public PBKDF2Params(byte[] bArr, int i) {
        this(bArr, i, 0);
    }

    public PBKDF2Params(byte[] bArr, int i, int i2) {
        this(bArr, i, i2, null);
    }

    public PBKDF2Params(byte[] bArr, int i, int i2, AlgorithmIdentifier algorithmIdentifier) {
        this.h = new DEROctetString(Arrays.clone(bArr));
        this.i = new ASN1Integer(i);
        this.j = i2 > 0 ? new ASN1Integer(i2) : null;
        this.k = algorithmIdentifier;
    }

    public PBKDF2Params(byte[] bArr, int i, AlgorithmIdentifier algorithmIdentifier) {
        this(bArr, i, 0, algorithmIdentifier);
    }

    public static PBKDF2Params getInstance(Object obj) {
        if (obj instanceof PBKDF2Params) {
            return (PBKDF2Params) obj;
        }
        if (obj != null) {
            return new PBKDF2Params(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getIterationCount() {
        return this.i.getValue();
    }

    public BigInteger getKeyLength() {
        ASN1Integer aSN1Integer = this.j;
        if (aSN1Integer != null) {
            return aSN1Integer.getValue();
        }
        return null;
    }

    public AlgorithmIdentifier getPrf() {
        AlgorithmIdentifier algorithmIdentifier = this.k;
        return algorithmIdentifier != null ? algorithmIdentifier : l;
    }

    public byte[] getSalt() {
        return this.h.getOctets();
    }

    public boolean isDefaultPrf() {
        AlgorithmIdentifier algorithmIdentifier = this.k;
        return algorithmIdentifier == null || algorithmIdentifier.equals(l);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        ASN1Integer aSN1Integer = this.j;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        AlgorithmIdentifier algorithmIdentifier = this.k;
        if (algorithmIdentifier != null && !algorithmIdentifier.equals(l)) {
            aSN1EncodableVector.add(this.k);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
