package org.bouncycastle.asn1.pkcs;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DLSequence;
/* loaded from: classes12.dex */
public class ContentInfo extends ASN1Object implements PKCSObjectIdentifiers {
    public ASN1ObjectIdentifier h;
    public ASN1Encodable i;
    public boolean j;

    public ContentInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.j = true;
        this.h = aSN1ObjectIdentifier;
        this.i = aSN1Encodable;
    }

    public ContentInfo(ASN1Sequence aSN1Sequence) {
        this.j = true;
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = (ASN1ObjectIdentifier) objects.nextElement();
        if (objects.hasMoreElements()) {
            this.i = ((ASN1TaggedObject) objects.nextElement()).getObject();
        }
        this.j = aSN1Sequence instanceof BERSequence;
    }

    public static ContentInfo getInstance(Object obj) {
        if (obj instanceof ContentInfo) {
            return (ContentInfo) obj;
        }
        if (obj != null) {
            return new ContentInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Encodable getContent() {
        return this.i;
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        ASN1Encodable aSN1Encodable = this.i;
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(new BERTaggedObject(true, 0, aSN1Encodable));
        }
        return this.j ? new BERSequence(aSN1EncodableVector) : new DLSequence(aSN1EncodableVector);
    }
}
