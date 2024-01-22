package org.bouncycastle.asn1.x509;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class BasicConstraints extends ASN1Object {
    public ASN1Boolean h;
    public ASN1Integer i;

    public BasicConstraints(int i) {
        this.h = ASN1Boolean.getInstance(false);
        this.i = null;
        this.h = ASN1Boolean.getInstance(true);
        this.i = new ASN1Integer(i);
    }

    public BasicConstraints(ASN1Sequence aSN1Sequence) {
        this.h = ASN1Boolean.getInstance(false);
        this.i = null;
        if (aSN1Sequence.size() == 0) {
            this.h = null;
            this.i = null;
            return;
        }
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Boolean) {
            this.h = ASN1Boolean.getInstance(aSN1Sequence.getObjectAt(0));
        } else {
            this.h = null;
            this.i = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        }
        if (aSN1Sequence.size() > 1) {
            if (this.h == null) {
                throw new IllegalArgumentException("wrong sequence in constructor");
            }
            this.i = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1));
        }
    }

    public BasicConstraints(boolean z) {
        this.h = ASN1Boolean.getInstance(false);
        this.i = null;
        if (z) {
            this.h = ASN1Boolean.getInstance(true);
        } else {
            this.h = null;
        }
        this.i = null;
    }

    public static BasicConstraints fromExtensions(Extensions extensions) {
        return getInstance(extensions.getExtensionParsedValue(Extension.basicConstraints));
    }

    public static BasicConstraints getInstance(Object obj) {
        if (obj instanceof BasicConstraints) {
            return (BasicConstraints) obj;
        }
        if (obj instanceof X509Extension) {
            return getInstance(X509Extension.convertValueToObject((X509Extension) obj));
        }
        if (obj != null) {
            return new BasicConstraints(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static BasicConstraints getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public BigInteger getPathLenConstraint() {
        ASN1Integer aSN1Integer = this.i;
        if (aSN1Integer != null) {
            return aSN1Integer.getValue();
        }
        return null;
    }

    public boolean isCA() {
        ASN1Boolean aSN1Boolean = this.h;
        return aSN1Boolean != null && aSN1Boolean.isTrue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1Boolean aSN1Boolean = this.h;
        if (aSN1Boolean != null) {
            aSN1EncodableVector.add(aSN1Boolean);
        }
        ASN1Integer aSN1Integer = this.i;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuilder sb;
        if (this.i != null) {
            sb = new StringBuilder();
            sb.append("BasicConstraints: isCa(");
            sb.append(isCA());
            sb.append("), pathLenConstraint = ");
            sb.append(this.i.getValue());
        } else if (this.h == null) {
            return "BasicConstraints: isCa(false)";
        } else {
            sb = new StringBuilder();
            sb.append("BasicConstraints: isCa(");
            sb.append(isCA());
            sb.append(")");
        }
        return sb.toString();
    }
}
