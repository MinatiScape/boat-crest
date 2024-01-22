package org.bouncycastle.pqc.crypto.xmss;

import java.io.IOException;
import java.util.Objects;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
/* loaded from: classes13.dex */
public final class XMSSPrivateKeyParameters extends AsymmetricKeyParameter implements XMSSStoreableObjectInterface {
    public final XMSSParameters i;
    public final byte[] j;
    public final byte[] k;
    public final byte[] l;
    public final byte[] m;
    public final BDS n;

    /* loaded from: classes13.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final XMSSParameters f15335a;
        public int b = 0;
        public byte[] c = null;
        public byte[] d = null;
        public byte[] e = null;
        public byte[] f = null;
        public BDS g = null;
        public byte[] h = null;
        public XMSSParameters i = null;

        public Builder(XMSSParameters xMSSParameters) {
            this.f15335a = xMSSParameters;
        }

        public XMSSPrivateKeyParameters build() {
            return new XMSSPrivateKeyParameters(this);
        }

        public Builder withBDSState(BDS bds) {
            this.g = bds;
            return this;
        }

        public Builder withIndex(int i) {
            this.b = i;
            return this;
        }

        public Builder withPrivateKey(byte[] bArr, XMSSParameters xMSSParameters) {
            this.h = XMSSUtil.cloneArray(bArr);
            this.i = xMSSParameters;
            return this;
        }

        public Builder withPublicSeed(byte[] bArr) {
            this.e = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withRoot(byte[] bArr) {
            this.f = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withSecretKeyPRF(byte[] bArr) {
            this.d = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withSecretKeySeed(byte[] bArr) {
            this.c = XMSSUtil.cloneArray(bArr);
            return this;
        }
    }

    public XMSSPrivateKeyParameters(Builder builder) {
        super(true);
        BDS bds;
        XMSSParameters xMSSParameters = builder.f15335a;
        this.i = xMSSParameters;
        Objects.requireNonNull(xMSSParameters, "params == null");
        int digestSize = xMSSParameters.getDigestSize();
        byte[] bArr = builder.h;
        if (bArr != null) {
            Objects.requireNonNull(builder.i, "xmss == null");
            int height = xMSSParameters.getHeight();
            int bigEndianToInt = Pack.bigEndianToInt(bArr, 0);
            if (!XMSSUtil.isIndexValid(height, bigEndianToInt)) {
                throw new IllegalArgumentException("index out of bounds");
            }
            this.j = XMSSUtil.extractBytesAtOffset(bArr, 4, digestSize);
            int i = 4 + digestSize;
            this.k = XMSSUtil.extractBytesAtOffset(bArr, i, digestSize);
            int i2 = i + digestSize;
            this.l = XMSSUtil.extractBytesAtOffset(bArr, i2, digestSize);
            int i3 = i2 + digestSize;
            this.m = XMSSUtil.extractBytesAtOffset(bArr, i3, digestSize);
            int i4 = i3 + digestSize;
            bds = null;
            try {
                bds = (BDS) XMSSUtil.deserialize(XMSSUtil.extractBytesAtOffset(bArr, i4, bArr.length - i4));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
            bds.setXMSS(builder.i);
            bds.validate();
            if (bds.getIndex() != bigEndianToInt) {
                throw new IllegalStateException("serialized BDS has wrong index");
            }
        } else {
            byte[] bArr2 = builder.c;
            if (bArr2 == null) {
                this.j = new byte[digestSize];
            } else if (bArr2.length != digestSize) {
                throw new IllegalArgumentException("size of secretKeySeed needs to be equal size of digest");
            } else {
                this.j = bArr2;
            }
            byte[] bArr3 = builder.d;
            if (bArr3 == null) {
                this.k = new byte[digestSize];
            } else if (bArr3.length != digestSize) {
                throw new IllegalArgumentException("size of secretKeyPRF needs to be equal size of digest");
            } else {
                this.k = bArr3;
            }
            byte[] bArr4 = builder.e;
            if (bArr4 == null) {
                this.l = new byte[digestSize];
            } else if (bArr4.length != digestSize) {
                throw new IllegalArgumentException("size of publicSeed needs to be equal size of digest");
            } else {
                this.l = bArr4;
            }
            byte[] bArr5 = builder.f;
            if (bArr5 == null) {
                this.m = new byte[digestSize];
            } else if (bArr5.length != digestSize) {
                throw new IllegalArgumentException("size of root needs to be equal size of digest");
            } else {
                this.m = bArr5;
            }
            bds = builder.g;
            if (bds == null) {
                this.n = (builder.b >= (1 << xMSSParameters.getHeight()) + (-2) || bArr4 == null || bArr2 == null) ? new BDS(xMSSParameters, builder.b) : new BDS(xMSSParameters, bArr4, bArr2, (OTSHashAddress) new OTSHashAddress.Builder().build(), builder.b);
                return;
            }
        }
        this.n = bds;
    }

    public BDS a() {
        return this.n;
    }

    public int getIndex() {
        return this.n.getIndex();
    }

    public XMSSPrivateKeyParameters getNextKey() {
        Builder withRoot;
        BDS bds;
        if (getIndex() < (1 << this.i.getHeight()) - 1) {
            withRoot = new Builder(this.i).withSecretKeySeed(this.j).withSecretKeyPRF(this.k).withPublicSeed(this.l).withRoot(this.m);
            bds = this.n.getNextState(this.l, this.j, (OTSHashAddress) new OTSHashAddress.Builder().build());
        } else {
            withRoot = new Builder(this.i).withSecretKeySeed(this.j).withSecretKeyPRF(this.k).withPublicSeed(this.l).withRoot(this.m);
            bds = new BDS(this.i, getIndex() + 1);
        }
        return withRoot.withBDSState(bds).build();
    }

    public XMSSParameters getParameters() {
        return this.i;
    }

    public byte[] getPublicSeed() {
        return XMSSUtil.cloneArray(this.l);
    }

    public byte[] getRoot() {
        return XMSSUtil.cloneArray(this.m);
    }

    public byte[] getSecretKeyPRF() {
        return XMSSUtil.cloneArray(this.k);
    }

    public byte[] getSecretKeySeed() {
        return XMSSUtil.cloneArray(this.j);
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int digestSize = this.i.getDigestSize();
        byte[] bArr = new byte[digestSize + 4 + digestSize + digestSize + digestSize];
        Pack.intToBigEndian(this.n.getIndex(), bArr, 0);
        XMSSUtil.copyBytesAtOffset(bArr, this.j, 4);
        int i = 4 + digestSize;
        XMSSUtil.copyBytesAtOffset(bArr, this.k, i);
        int i2 = i + digestSize;
        XMSSUtil.copyBytesAtOffset(bArr, this.l, i2);
        XMSSUtil.copyBytesAtOffset(bArr, this.m, i2 + digestSize);
        try {
            return Arrays.concatenate(bArr, XMSSUtil.serialize(this.n));
        } catch (IOException e) {
            throw new RuntimeException("error serializing bds state: " + e.getMessage());
        }
    }
}
