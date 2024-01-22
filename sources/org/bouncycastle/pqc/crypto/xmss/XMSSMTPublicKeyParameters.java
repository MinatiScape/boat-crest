package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
/* loaded from: classes13.dex */
public final class XMSSMTPublicKeyParameters extends AsymmetricKeyParameter implements XMSSStoreableObjectInterface {
    public final XMSSMTParameters i;
    public final byte[] j;
    public final byte[] k;

    /* loaded from: classes13.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final XMSSMTParameters f15331a;
        public byte[] b = null;
        public byte[] c = null;
        public byte[] d = null;

        public Builder(XMSSMTParameters xMSSMTParameters) {
            this.f15331a = xMSSMTParameters;
        }

        public XMSSMTPublicKeyParameters build() {
            return new XMSSMTPublicKeyParameters(this);
        }

        public Builder withPublicKey(byte[] bArr) {
            this.d = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withPublicSeed(byte[] bArr) {
            this.c = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withRoot(byte[] bArr) {
            this.b = XMSSUtil.cloneArray(bArr);
            return this;
        }
    }

    public XMSSMTPublicKeyParameters(Builder builder) {
        super(false);
        XMSSMTParameters xMSSMTParameters = builder.f15331a;
        this.i = xMSSMTParameters;
        Objects.requireNonNull(xMSSMTParameters, "params == null");
        int digestSize = xMSSMTParameters.getDigestSize();
        byte[] bArr = builder.d;
        if (bArr != null) {
            if (bArr.length != digestSize + digestSize) {
                throw new IllegalArgumentException("public key has wrong size");
            }
            this.j = XMSSUtil.extractBytesAtOffset(bArr, 0, digestSize);
            this.k = XMSSUtil.extractBytesAtOffset(bArr, digestSize + 0, digestSize);
            return;
        }
        byte[] bArr2 = builder.b;
        if (bArr2 == null) {
            this.j = new byte[digestSize];
        } else if (bArr2.length != digestSize) {
            throw new IllegalArgumentException("length of root must be equal to length of digest");
        } else {
            this.j = bArr2;
        }
        byte[] bArr3 = builder.c;
        if (bArr3 == null) {
            this.k = new byte[digestSize];
        } else if (bArr3.length != digestSize) {
            throw new IllegalArgumentException("length of publicSeed must be equal to length of digest");
        } else {
            this.k = bArr3;
        }
    }

    public XMSSMTParameters getParameters() {
        return this.i;
    }

    public byte[] getPublicSeed() {
        return XMSSUtil.cloneArray(this.k);
    }

    public byte[] getRoot() {
        return XMSSUtil.cloneArray(this.j);
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int digestSize = this.i.getDigestSize();
        byte[] bArr = new byte[digestSize + digestSize];
        XMSSUtil.copyBytesAtOffset(bArr, this.j, 0);
        XMSSUtil.copyBytesAtOffset(bArr, this.k, digestSize + 0);
        return bArr;
    }
}
