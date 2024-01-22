package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.util.Pack;
/* loaded from: classes13.dex */
public abstract class XMSSAddress {

    /* renamed from: a  reason: collision with root package name */
    public final int f15324a;
    public final long b;
    public final int c;
    public final int d;

    /* loaded from: classes13.dex */
    public static abstract class Builder<T extends Builder> {

        /* renamed from: a  reason: collision with root package name */
        public final int f15325a;
        public int b = 0;
        public long c = 0;
        public int d = 0;

        public Builder(int i) {
            this.f15325a = i;
        }

        public abstract XMSSAddress build();

        public abstract T getThis();

        public T withKeyAndMask(int i) {
            this.d = i;
            return getThis();
        }

        public T withLayerAddress(int i) {
            this.b = i;
            return getThis();
        }

        public T withTreeAddress(long j) {
            this.c = j;
            return getThis();
        }
    }

    public XMSSAddress(Builder builder) {
        this.f15324a = builder.b;
        this.b = builder.c;
        this.c = builder.f15325a;
        this.d = builder.d;
    }

    public final int getKeyAndMask() {
        return this.d;
    }

    public final int getLayerAddress() {
        return this.f15324a;
    }

    public final long getTreeAddress() {
        return this.b;
    }

    public final int getType() {
        return this.c;
    }

    public byte[] toByteArray() {
        byte[] bArr = new byte[32];
        Pack.intToBigEndian(this.f15324a, bArr, 0);
        Pack.longToBigEndian(this.b, bArr, 4);
        Pack.intToBigEndian(this.c, bArr, 12);
        Pack.intToBigEndian(this.d, bArr, 28);
        return bArr;
    }
}
