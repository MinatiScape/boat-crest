package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.Extension;
/* loaded from: classes12.dex */
public class ExtensionReq extends ASN1Object {
    public final Extension[] h;

    public ExtensionReq(ASN1Sequence aSN1Sequence) {
        this.h = new Extension[aSN1Sequence.size()];
        for (int i = 0; i != aSN1Sequence.size(); i++) {
            this.h[i] = Extension.getInstance(aSN1Sequence.getObjectAt(i));
        }
    }

    public ExtensionReq(Extension extension) {
        this.h = new Extension[]{extension};
    }

    public ExtensionReq(Extension[] extensionArr) {
        this.h = a.b(extensionArr);
    }

    public static ExtensionReq getInstance(Object obj) {
        if (obj instanceof ExtensionReq) {
            return (ExtensionReq) obj;
        }
        if (obj != null) {
            return new ExtensionReq(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static ExtensionReq getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Extension[] getExtensions() {
        return a.b(this.h);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.h);
    }
}
