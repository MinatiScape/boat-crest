package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x509.Extension;
/* loaded from: classes12.dex */
public class a {
    public static BodyPartID[] a(BodyPartID[] bodyPartIDArr) {
        BodyPartID[] bodyPartIDArr2 = new BodyPartID[bodyPartIDArr.length];
        System.arraycopy(bodyPartIDArr, 0, bodyPartIDArr2, 0, bodyPartIDArr.length);
        return bodyPartIDArr2;
    }

    public static Extension[] b(Extension[] extensionArr) {
        Extension[] extensionArr2 = new Extension[extensionArr.length];
        System.arraycopy(extensionArr, 0, extensionArr2, 0, extensionArr.length);
        return extensionArr2;
    }

    public static BodyPartID[] c(ASN1Sequence aSN1Sequence) {
        BodyPartID[] bodyPartIDArr = new BodyPartID[aSN1Sequence.size()];
        for (int i = 0; i != aSN1Sequence.size(); i++) {
            bodyPartIDArr[i] = BodyPartID.getInstance(aSN1Sequence.getObjectAt(i));
        }
        return bodyPartIDArr;
    }
}
