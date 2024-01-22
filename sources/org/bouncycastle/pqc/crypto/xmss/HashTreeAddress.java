package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.pqc.crypto.xmss.XMSSAddress;
import org.bouncycastle.util.Pack;
/* loaded from: classes13.dex */
public final class HashTreeAddress extends XMSSAddress {
    public final int e;
    public final int f;
    public final int g;

    /* loaded from: classes13.dex */
    public static class Builder extends XMSSAddress.Builder<Builder> {
        public int e;
        public int f;

        public Builder() {
            super(2);
            this.e = 0;
            this.f = 0;
        }

        @Override // org.bouncycastle.pqc.crypto.xmss.XMSSAddress.Builder
        public XMSSAddress build() {
            return new HashTreeAddress(this);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.bouncycastle.pqc.crypto.xmss.XMSSAddress.Builder
        public Builder getThis() {
            return this;
        }

        public Builder withTreeHeight(int i) {
            this.e = i;
            return this;
        }

        public Builder withTreeIndex(int i) {
            this.f = i;
            return this;
        }
    }

    public HashTreeAddress(Builder builder) {
        super(builder);
        this.e = 0;
        this.f = builder.e;
        this.g = builder.f;
    }

    public int a() {
        return this.f;
    }

    public int b() {
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
