package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.pqc.crypto.xmss.XMSSReducedSignature;
import org.bouncycastle.util.Pack;
/* loaded from: classes13.dex */
public final class XMSSSignature extends XMSSReducedSignature {
    public final int k;
    public final byte[] l;

    /* loaded from: classes13.dex */
    public static class Builder extends XMSSReducedSignature.Builder {
        public final XMSSParameters e;
        public int f;
        public byte[] g;

        public Builder(XMSSParameters xMSSParameters) {
            super(xMSSParameters);
            this.f = 0;
            this.g = null;
            this.e = xMSSParameters;
        }

        @Override // org.bouncycastle.pqc.crypto.xmss.XMSSReducedSignature.Builder
        public XMSSSignature build() {
            return new XMSSSignature(this);
        }

        public Builder withIndex(int i) {
            this.f = i;
            return this;
        }

        public Builder withRandom(byte[] bArr) {
            this.g = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withSignature(byte[] bArr) {
            Objects.requireNonNull(bArr, "signature == null");
            int digestSize = this.e.getDigestSize();
            int c = this.e.c().e().c();
            this.f = Pack.bigEndianToInt(bArr, 0);
            this.g = XMSSUtil.extractBytesAtOffset(bArr, 4, digestSize);
            withReducedSignature(XMSSUtil.extractBytesAtOffset(bArr, 4 + digestSize, (c * digestSize) + (this.e.getHeight() * digestSize)));
            return this;
        }
    }

    public XMSSSignature(Builder builder) {
        super(builder);
        this.k = builder.f;
        int digestSize = getParams().getDigestSize();
        byte[] bArr = builder.g;
        if (bArr == null) {
            this.l = new byte[digestSize];
        } else if (bArr.length != digestSize) {
            throw new IllegalArgumentException("size of random needs to be equal to size of digest");
        } else {
            this.l = bArr;
        }
    }

    public int getIndex() {
        return this.k;
    }

    public byte[] getRandom() {
        return XMSSUtil.cloneArray(this.l);
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSReducedSignature, org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int digestSize = getParams().getDigestSize();
        byte[] bArr = new byte[digestSize + 4 + (getParams().c().e().c() * digestSize) + (getParams().getHeight() * digestSize)];
        Pack.intToBigEndian(this.k, bArr, 0);
        XMSSUtil.copyBytesAtOffset(bArr, this.l, 4);
        int i = 4 + digestSize;
        for (byte[] bArr2 : getWOTSPlusSignature().a()) {
            XMSSUtil.copyBytesAtOffset(bArr, bArr2, i);
            i += digestSize;
        }
        for (int i2 = 0; i2 < getAuthPath().size(); i2++) {
            XMSSUtil.copyBytesAtOffset(bArr, getAuthPath().get(i2).getValue(), i);
            i += digestSize;
        }
        return bArr;
    }
}
