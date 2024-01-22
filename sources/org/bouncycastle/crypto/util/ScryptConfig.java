package org.bouncycastle.crypto.util;

import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
/* loaded from: classes13.dex */
public class ScryptConfig extends PBKDFConfig {
    public final int b;
    public final int c;
    public final int d;
    public final int e;

    /* loaded from: classes13.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final int f14883a;
        public final int b;
        public final int c;
        public int d = 16;

        public Builder(int i, int i2, int i3) {
            if (i <= 1 || !e(i)) {
                throw new IllegalArgumentException("Cost parameter N must be > 1 and a power of 2");
            }
            this.f14883a = i;
            this.b = i2;
            this.c = i3;
        }

        public static boolean e(int i) {
            return (i & (i + (-1))) == 0;
        }

        public ScryptConfig build() {
            return new ScryptConfig(this);
        }

        public Builder withSaltLength(int i) {
            this.d = i;
            return this;
        }
    }

    public ScryptConfig(Builder builder) {
        super(MiscObjectIdentifiers.id_scrypt);
        this.b = builder.f14883a;
        this.c = builder.b;
        this.d = builder.c;
        this.e = builder.d;
    }

    public int getBlockSize() {
        return this.c;
    }

    public int getCostParameter() {
        return this.b;
    }

    public int getParallelizationParameter() {
        return this.d;
    }

    public int getSaltLength() {
        return this.e;
    }
}
