package org.bouncycastle.asn1.cmc;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
/* loaded from: classes12.dex */
public class BodyPartReference extends ASN1Object implements ASN1Choice {
    public final BodyPartID h;
    public final BodyPartPath i;

    public BodyPartReference(BodyPartID bodyPartID) {
        this.h = bodyPartID;
        this.i = null;
    }

    public BodyPartReference(BodyPartPath bodyPartPath) {
        this.h = null;
        this.i = bodyPartPath;
    }

    public static BodyPartReference getInstance(Object obj) {
        if (obj instanceof BodyPartReference) {
            return (BodyPartReference) obj;
        }
        if (obj != null) {
            if (obj instanceof ASN1Encodable) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
                if (aSN1Primitive instanceof ASN1Integer) {
                    return new BodyPartReference(BodyPartID.getInstance(aSN1Primitive));
                }
                if (aSN1Primitive instanceof ASN1Sequence) {
                    return new BodyPartReference(BodyPartPath.getInstance(aSN1Primitive));
                }
            }
            if (obj instanceof byte[]) {
                try {
                    return getInstance(ASN1Primitive.fromByteArray((byte[]) obj));
                } catch (IOException unused) {
                    throw new IllegalArgumentException("unknown encoding in getInstance()");
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance(): " + obj.getClass().getName());
        }
        return null;
    }

    public BodyPartID getBodyPartID() {
        return this.h;
    }

    public BodyPartPath getBodyPartPath() {
        return this.i;
    }

    public boolean isBodyPartID() {
        return this.h != null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        BodyPartID bodyPartID = this.h;
        return bodyPartID != null ? bodyPartID.toASN1Primitive() : this.i.toASN1Primitive();
    }
}
