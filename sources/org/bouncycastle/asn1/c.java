package org.bouncycastle.asn1;
/* loaded from: classes12.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public static final ASN1Sequence f14393a = new DERSequence();
    public static final ASN1Set b = new DERSet();

    public static ASN1Sequence a(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.size() < 1 ? f14393a : new DLSequence(aSN1EncodableVector);
    }

    public static ASN1Set b(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.size() < 1 ? b : new DLSet(aSN1EncodableVector);
    }
}
