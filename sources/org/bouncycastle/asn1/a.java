package org.bouncycastle.asn1;
/* loaded from: classes12.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final BERSequence f14391a = new BERSequence();

    static {
        new BERSet();
    }

    public static BERSequence a(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.size() < 1 ? f14391a : new BERSequence(aSN1EncodableVector);
    }
}
