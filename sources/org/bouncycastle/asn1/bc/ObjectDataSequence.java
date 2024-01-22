package org.bouncycastle.asn1.bc;

import java.util.Iterator;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Iterable;
/* loaded from: classes12.dex */
public class ObjectDataSequence extends ASN1Object implements Iterable<ASN1Encodable> {
    public final ASN1Encodable[] h;

    public ObjectDataSequence(ASN1Sequence aSN1Sequence) {
        this.h = new ASN1Encodable[aSN1Sequence.size()];
        int i = 0;
        while (true) {
            ASN1Encodable[] aSN1EncodableArr = this.h;
            if (i == aSN1EncodableArr.length) {
                return;
            }
            aSN1EncodableArr[i] = ObjectData.getInstance(aSN1Sequence.getObjectAt(i));
            i++;
        }
    }

    public ObjectDataSequence(ObjectData[] objectDataArr) {
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[objectDataArr.length];
        this.h = aSN1EncodableArr;
        System.arraycopy(objectDataArr, 0, aSN1EncodableArr, 0, objectDataArr.length);
    }

    public static ObjectDataSequence getInstance(Object obj) {
        if (obj instanceof ObjectDataSequence) {
            return (ObjectDataSequence) obj;
        }
        if (obj != null) {
            return new ObjectDataSequence(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override // org.bouncycastle.util.Iterable, java.lang.Iterable
    public Iterator<ASN1Encodable> iterator() {
        return new Arrays.Iterator(this.h);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.h);
    }
}
