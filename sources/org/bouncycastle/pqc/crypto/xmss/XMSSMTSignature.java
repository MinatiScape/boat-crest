package org.bouncycastle.pqc.crypto.xmss;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bouncycastle.pqc.crypto.xmss.XMSSReducedSignature;
/* loaded from: classes13.dex */
public final class XMSSMTSignature implements XMSSStoreableObjectInterface {
    public final XMSSMTParameters h;
    public final long i;
    public final byte[] j;
    public final List<XMSSReducedSignature> k;

    /* loaded from: classes13.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final XMSSMTParameters f15332a;
        public long b = 0;
        public byte[] c = null;
        public List<XMSSReducedSignature> d = null;
        public byte[] e = null;

        public Builder(XMSSMTParameters xMSSMTParameters) {
            this.f15332a = xMSSMTParameters;
        }

        public XMSSMTSignature build() {
            return new XMSSMTSignature(this);
        }

        public Builder withIndex(long j) {
            this.b = j;
            return this;
        }

        public Builder withRandom(byte[] bArr) {
            this.c = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withReducedSignatures(List<XMSSReducedSignature> list) {
            this.d = list;
            return this;
        }

        public Builder withSignature(byte[] bArr) {
            this.e = bArr;
            return this;
        }
    }

    public XMSSMTSignature(Builder builder) {
        XMSSMTParameters xMSSMTParameters = builder.f15332a;
        this.h = xMSSMTParameters;
        Objects.requireNonNull(xMSSMTParameters, "params == null");
        int digestSize = xMSSMTParameters.getDigestSize();
        byte[] bArr = builder.e;
        if (bArr == null) {
            this.i = builder.b;
            byte[] bArr2 = builder.c;
            if (bArr2 == null) {
                this.j = new byte[digestSize];
            } else if (bArr2.length != digestSize) {
                throw new IllegalArgumentException("size of random needs to be equal to size of digest");
            } else {
                this.j = bArr2;
            }
            List<XMSSReducedSignature> list = builder.d;
            this.k = list == null ? new ArrayList<>() : list;
            return;
        }
        int c = xMSSMTParameters.getWOTSPlus().e().c();
        int ceil = (int) Math.ceil(xMSSMTParameters.getHeight() / 8.0d);
        int height = ((xMSSMTParameters.getHeight() / xMSSMTParameters.getLayers()) + c) * digestSize;
        if (bArr.length != ceil + digestSize + (xMSSMTParameters.getLayers() * height)) {
            throw new IllegalArgumentException("signature has wrong size");
        }
        long bytesToXBigEndian = XMSSUtil.bytesToXBigEndian(bArr, 0, ceil);
        this.i = bytesToXBigEndian;
        if (!XMSSUtil.isIndexValid(xMSSMTParameters.getHeight(), bytesToXBigEndian)) {
            throw new IllegalArgumentException("index out of bounds");
        }
        int i = ceil + 0;
        this.j = XMSSUtil.extractBytesAtOffset(bArr, i, digestSize);
        this.k = new ArrayList();
        for (int i2 = i + digestSize; i2 < bArr.length; i2 += height) {
            this.k.add(new XMSSReducedSignature.Builder(this.h.getXMSSParameters()).withReducedSignature(XMSSUtil.extractBytesAtOffset(bArr, i2, height)).build());
        }
    }

    public long getIndex() {
        return this.i;
    }

    public byte[] getRandom() {
        return XMSSUtil.cloneArray(this.j);
    }

    public List<XMSSReducedSignature> getReducedSignatures() {
        return this.k;
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int digestSize = this.h.getDigestSize();
        int c = this.h.getWOTSPlus().e().c();
        int ceil = (int) Math.ceil(this.h.getHeight() / 8.0d);
        int height = ((this.h.getHeight() / this.h.getLayers()) + c) * digestSize;
        byte[] bArr = new byte[ceil + digestSize + (this.h.getLayers() * height)];
        XMSSUtil.copyBytesAtOffset(bArr, XMSSUtil.toBytesBigEndian(this.i, ceil), 0);
        int i = ceil + 0;
        XMSSUtil.copyBytesAtOffset(bArr, this.j, i);
        int i2 = i + digestSize;
        for (XMSSReducedSignature xMSSReducedSignature : this.k) {
            XMSSUtil.copyBytesAtOffset(bArr, xMSSReducedSignature.toByteArray(), i2);
            i2 += height;
        }
        return bArr;
    }
}
