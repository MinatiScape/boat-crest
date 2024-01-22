package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.pqc.crypto.xmss.XMSSAddress;
import org.bouncycastle.util.Pack;
/* loaded from: classes13.dex */
public final class LTreeAddress extends XMSSAddress {
    public final int e;
    public final int f;
    public final int g;

    /* loaded from: classes13.dex */
    public static class Builder extends XMSSAddress.Builder<Builder> {
        public int e;
        public int f;
        public int g;

        public Builder() {
            super(1);
            this.e = 0;
            this.f = 0;
            this.g = 0;
        }

        @Override // org.bouncycastle.pqc.crypto.xmss.XMSSAddress.Builder
        public XMSSAddress build() {
            return new LTreeAddress(this);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.bouncycastle.pqc.crypto.xmss.XMSSAddress.Builder
        public Builder getThis() {
            return this;
        }

        public Builder withLTreeAddress(int i) {
            this.e = i;
            return this;
        }

        public Builder withTreeHeight(int i) {
            this.f = i;
            return this;
        }

        public Builder withTreeIndex(int i) {
            this.g = i;
            return this;
        }
    }

    public LTreeAddress(Builder builder) {
        super(builder);
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
    }

    public int a() {
        return this.e;
    }

    public int b() {
        return this.f;
    }

    public int c() {
        return this.g;
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSAddress
    public byte[] toByteArray() {
        byte[] byteArray = super.toByteArray();
        Pack.intToBigEndian(this.e, byteArray, 16);
        Pack.intToBigEndian(this.f, byteArray, 20);
        Pack.intToBigEndian(this.g, byteArray, 24);
        return byteArray;
    }
}
