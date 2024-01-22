package org.bouncycastle.crypto.agreement.kdf;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.crypto.DerivationParameters;
/* loaded from: classes8.dex */
public class DHKDFParameters implements DerivationParameters {

    /* renamed from: a  reason: collision with root package name */
    public ASN1ObjectIdentifier f14626a;
    public int b;
    public byte[] c;
    public byte[] d;

    public DHKDFParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i, byte[] bArr) {
        this(aSN1ObjectIdentifier, i, bArr, null);
    }

    public DHKDFParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i, byte[] bArr, byte[] bArr2) {
        this.f14626a = aSN1ObjectIdentifier;
        this.b = i;
        this.c = bArr;
        this.d = bArr2;
    }

    public ASN1ObjectIdentifier getAlgorithm() {
        return this.f14626a;
    }

    public byte[] getExtraInfo() {
        return this.d;
    }

    public int getKeySize() {
        return this.b;
    }

    public byte[] getZ() {
        return this.c;
    }
}
