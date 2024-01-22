package org.bouncycastle.crypto.util;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes13.dex */
public class DEROtherInfo {

    /* renamed from: a  reason: collision with root package name */
    public final DERSequence f14878a;

    /* loaded from: classes13.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final AlgorithmIdentifier f14879a;
        public final ASN1OctetString b;
        public final ASN1OctetString c;
        public ASN1TaggedObject d;
        public ASN1TaggedObject e;

        public Builder(AlgorithmIdentifier algorithmIdentifier, byte[] bArr, byte[] bArr2) {
            this.f14879a = algorithmIdentifier;
            this.b = org.bouncycastle.crypto.util.a.a(bArr);
            this.c = org.bouncycastle.crypto.util.a.a(bArr2);
        }

        public DEROtherInfo build() {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            aSN1EncodableVector.add(this.f14879a);
            aSN1EncodableVector.add(this.b);
            aSN1EncodableVector.add(this.c);
            ASN1TaggedObject aSN1TaggedObject = this.d;
            if (aSN1TaggedObject != null) {
                aSN1EncodableVector.add(aSN1TaggedObject);
            }
            ASN1TaggedObject aSN1TaggedObject2 = this.e;
            if (aSN1TaggedObject2 != null) {
                aSN1EncodableVector.add(aSN1TaggedObject2);
            }
            return new DEROtherInfo(new DERSequence(aSN1EncodableVector));
        }

        public Builder withSuppPrivInfo(byte[] bArr) {
            this.e = new DERTaggedObject(false, 1, org.bouncycastle.crypto.util.a.a(bArr));
            return this;
        }

        public Builder withSuppPubInfo(byte[] bArr) {
            this.d = new DERTaggedObject(false, 0, org.bouncycastle.crypto.util.a.a(bArr));
            return this;
        }
    }

    public DEROtherInfo(DERSequence dERSequence) {
        this.f14878a = dERSequence;
    }

    public byte[] getEncoded() throws IOException {
        return this.f14878a.getEncoded();
    }
}
