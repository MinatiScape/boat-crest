package org.bouncycastle.asn1.x9;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class DomainParameters extends ASN1Object {
    public final ASN1Integer h;
    public final ASN1Integer i;
    public final ASN1Integer j;
    public final ASN1Integer k;
    public final ValidationParams l;

    public DomainParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, ValidationParams validationParams) {
        if (bigInteger == null) {
            throw new IllegalArgumentException("'p' cannot be null");
        }
        if (bigInteger2 == null) {
            throw new IllegalArgumentException("'g' cannot be null");
        }
        if (bigInteger3 == null) {
            throw new IllegalArgumentException("'q' cannot be null");
        }
        this.h = new ASN1Integer(bigInteger);
        this.i = new ASN1Integer(bigInteger2);
        this.j = new ASN1Integer(bigInteger3);
        this.k = bigInteger4 != null ? new ASN1Integer(bigInteger4) : null;
        this.l = validationParams;
    }

    public DomainParameters(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 3 || aSN1Sequence.size() > 5) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = ASN1Integer.getInstance(objects.nextElement());
        this.i = ASN1Integer.getInstance(objects.nextElement());
        this.j = ASN1Integer.getInstance(objects.nextElement());
        ASN1Encodable a2 = a(objects);
        if (a2 == null || !(a2 instanceof ASN1Integer)) {
            this.k = null;
        } else {
            this.k = ASN1Integer.getInstance(a2);
            a2 = a(objects);
        }
        if (a2 != null) {
            this.l = ValidationParams.getInstance(a2.toASN1Primitive());
        } else {
            this.l = null;
        }
    }

    public static ASN1Encodable a(Enumeration enumeration) {
        if (enumeration.hasMoreElements()) {
            return (ASN1Encodable) enumeration.nextElement();
        }
        return null;
    }

    public static DomainParameters getInstance(Object obj) {
        if (obj instanceof DomainParameters) {
            return (DomainParameters) obj;
        }
        if (obj != null) {
            return new DomainParameters(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static DomainParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public BigInteger getG() {
        return this.i.getPositiveValue();
    }

    public BigInteger getJ() {
        ASN1Integer aSN1Integer = this.k;
        if (aSN1Integer == null) {
            return null;
        }
        return aSN1Integer.getPositiveValue();
    }

    public BigInteger getP() {
        return this.h.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.j.getPositiveValue();
    }

    public ValidationParams getValidationParams() {
        return this.l;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        ASN1Integer aSN1Integer = this.k;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        ValidationParams validationParams = this.l;
        if (validationParams != null) {
            aSN1EncodableVector.add(validationParams);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
