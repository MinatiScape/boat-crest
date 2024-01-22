package org.bouncycastle.pqc.crypto.xmss;

import java.io.IOException;
import java.util.Objects;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public final class XMSSMTPrivateKeyParameters extends AsymmetricKeyParameter implements XMSSStoreableObjectInterface {
    public final XMSSMTParameters i;
    public final long j;
    public final byte[] k;
    public final byte[] l;
    public final byte[] m;
    public final byte[] n;
    public final BDSStateMap o;

    /* loaded from: classes13.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final XMSSMTParameters f15330a;
        public long b = 0;
        public byte[] c = null;
        public byte[] d = null;
        public byte[] e = null;
        public byte[] f = null;
        public BDSStateMap g = null;
        public byte[] h = null;
        public XMSSParameters i = null;

        public Builder(XMSSMTParameters xMSSMTParameters) {
            this.f15330a = xMSSMTParameters;
        }

        public XMSSMTPrivateKeyParameters build() {
            return new XMSSMTPrivateKeyParameters(this);
        }

        public Builder withBDSState(BDSStateMap bDSStateMap) {
            this.g = bDSStateMap;
            return this;
        }

        public Builder withIndex(long j) {
            this.b = j;
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

    public XMSSMTPrivateKeyParameters(Builder builder) {
        super(true);
        XMSSMTParameters xMSSMTParameters = builder.f15330a;
        this.i = xMSSMTParameters;
        Objects.requireNonNull(xMSSMTParameters, "params == null");
        int digestSize = xMSSMTParameters.getDigestSize();
        byte[] bArr = builder.h;
        if (bArr != null) {
            Objects.requireNonNull(builder.i, "xmss == null");
            int height = xMSSMTParameters.getHeight();
            int i = (height + 7) / 8;
            long bytesToXBigEndian = XMSSUtil.bytesToXBigEndian(bArr, 0, i);
            this.j = bytesToXBigEndian;
            if (!XMSSUtil.isIndexValid(height, bytesToXBigEndian)) {
                throw new IllegalArgumentException("index out of bounds");
            }
            int i2 = i + 0;
            this.k = XMSSUtil.extractBytesAtOffset(bArr, i2, digestSize);
            int i3 = i2 + digestSize;
            this.l = XMSSUtil.extractBytesAtOffset(bArr, i3, digestSize);
            int i4 = i3 + digestSize;
            this.m = XMSSUtil.extractBytesAtOffset(bArr, i4, digestSize);
            int i5 = i4 + digestSize;
            this.n = XMSSUtil.extractBytesAtOffset(bArr, i5, digestSize);
            int i6 = i5 + digestSize;
            byte[] extractBytesAtOffset = XMSSUtil.extractBytesAtOffset(bArr, i6, bArr.length - i6);
            BDSStateMap bDSStateMap = null;
            try {
                bDSStateMap = (BDSStateMap) XMSSUtil.deserialize(extractBytesAtOffset);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
            bDSStateMap.setXMSS(builder.i);
            this.o = bDSStateMap;
            return;
        }
        this.j = builder.b;
        byte[] bArr2 = builder.c;
        if (bArr2 == null) {
            this.k = new byte[digestSize];
        } else if (bArr2.length != digestSize) {
            throw new IllegalArgumentException("size of secretKeySeed needs to be equal size of digest");
        } else {
            this.k = bArr2;
        }
        byte[] bArr3 = builder.d;
        if (bArr3 == null) {
            this.l = new byte[digestSize];
        } else if (bArr3.length != digestSize) {
            throw new IllegalArgumentException("size of secretKeyPRF needs to be equal size of digest");
        } else {
            this.l = bArr3;
        }
        byte[] bArr4 = builder.e;
        if (bArr4 == null) {
            this.m = new byte[digestSize];
        } else if (bArr4.length != digestSize) {
            throw new IllegalArgumentException("size of publicSeed needs to be equal size of digest");
        } else {
            this.m = bArr4;
        }
        byte[] bArr5 = builder.f;
        if (bArr5 == null) {
            this.n = new byte[digestSize];
        } else if (bArr5.length != digestSize) {
            throw new IllegalArgumentException("size of root needs to be equal size of digest");
        } else {
            this.n = bArr5;
        }
        BDSStateMap bDSStateMap2 = builder.g;
        if (bDSStateMap2 == null) {
            if (!XMSSUtil.isIndexValid(xMSSMTParameters.getHeight(), builder.b) || bArr4 == null || bArr2 == null) {
                this.o = new BDSStateMap();
                return;
            }
            bDSStateMap2 = new BDSStateMap(xMSSMTParameters, builder.b, bArr4, bArr2);
        }
        this.o = bDSStateMap2;
    }

    public BDSStateMap a() {
        return this.o;
    }

    public long getIndex() {
        return this.j;
    }

    public XMSSMTPrivateKeyParameters getNextKey() {
        return new Builder(this.i).withIndex(this.j + 1).withSecretKeySeed(this.k).withSecretKeyPRF(this.l).withPublicSeed(this.m).withRoot(this.n).withBDSState(new BDSStateMap(this.o, this.i, getIndex(), this.m, this.k)).build();
    }

    public XMSSMTParameters getParameters() {
        return this.i;
    }

    public byte[] getPublicSeed() {
        return XMSSUtil.cloneArray(this.m);
    }

    public byte[] getRoot() {
        return XMSSUtil.cloneArray(this.n);
    }

    public byte[] getSecretKeyPRF() {
        return XMSSUtil.cloneArray(this.l);
    }

    public byte[] getSecretKeySeed() {
        return XMSSUtil.cloneArray(this.k);
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int digestSize = this.i.getDigestSize();
        int height = (this.i.getHeight() + 7) / 8;
        byte[] bArr = new byte[height + digestSize + digestSize + digestSize + digestSize];
        XMSSUtil.copyBytesAtOffset(bArr, XMSSUtil.toBytesBigEndian(this.j, height), 0);
        int i = height + 0;
        XMSSUtil.copyBytesAtOffset(bArr, this.k, i);
        int i2 = i + digestSize;
        XMSSUtil.copyBytesAtOffset(bArr, this.l, i2);
        int i3 = i2 + digestSize;
        XMSSUtil.copyBytesAtOffset(bArr, this.m, i3);
        XMSSUtil.copyBytesAtOffset(bArr, this.n, i3 + digestSize);
        try {
            return Arrays.concatenate(bArr, XMSSUtil.serialize(this.o));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("error serializing bds state");
        }
    }
}
