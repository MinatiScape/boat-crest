package org.bouncycastle.pqc.crypto.xmss;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes13.dex */
public class XMSSReducedSignature implements XMSSStoreableObjectInterface {
    public final XMSSParameters h;
    public final g i;
    public final List<XMSSNode> j;

    /* loaded from: classes13.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final XMSSParameters f15337a;
        public g b = null;
        public List<XMSSNode> c = null;
        public byte[] d = null;

        public Builder(XMSSParameters xMSSParameters) {
            this.f15337a = xMSSParameters;
        }

        public XMSSReducedSignature build() {
            return new XMSSReducedSignature(this);
        }

        public Builder withAuthPath(List<XMSSNode> list) {
            this.c = list;
            return this;
        }

        public Builder withReducedSignature(byte[] bArr) {
            this.d = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withWOTSPlusSignature(g gVar) {
            this.b = gVar;
            return this;
        }
    }

    public XMSSReducedSignature(Builder builder) {
        List<XMSSNode> list;
        XMSSParameters xMSSParameters = builder.f15337a;
        this.h = xMSSParameters;
        Objects.requireNonNull(xMSSParameters, "params == null");
        int digestSize = xMSSParameters.getDigestSize();
        int c = xMSSParameters.c().e().c();
        int height = xMSSParameters.getHeight();
        byte[] bArr = builder.d;
        if (bArr == null) {
            g gVar = builder.b;
            this.i = gVar == null ? new g(xMSSParameters.c().e(), (byte[][]) Array.newInstance(byte.class, c, digestSize)) : gVar;
            list = builder.c;
            if (list == null) {
                list = new ArrayList<>();
            } else if (list.size() != height) {
                throw new IllegalArgumentException("size of authPath needs to be equal to height of tree");
            }
        } else if (bArr.length != (c * digestSize) + (height * digestSize)) {
            throw new IllegalArgumentException("signature has wrong size");
        } else {
            byte[][] bArr2 = new byte[c];
            int i = 0;
            for (int i2 = 0; i2 < c; i2++) {
                bArr2[i2] = XMSSUtil.extractBytesAtOffset(bArr, i, digestSize);
                i += digestSize;
            }
            this.i = new g(this.h.c().e(), bArr2);
            list = new ArrayList<>();
            for (int i3 = 0; i3 < height; i3++) {
                list.add(new XMSSNode(i3, XMSSUtil.extractBytesAtOffset(bArr, i, digestSize)));
                i += digestSize;
            }
        }
        this.j = list;
    }

    public List<XMSSNode> getAuthPath() {
        return this.j;
    }

    public XMSSParameters getParams() {
        return this.h;
    }

    public g getWOTSPlusSignature() {
        return this.i;
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int digestSize = this.h.getDigestSize();
        byte[] bArr = new byte[(this.h.c().e().c() * digestSize) + (this.h.getHeight() * digestSize)];
        int i = 0;
        for (byte[] bArr2 : this.i.a()) {
            XMSSUtil.copyBytesAtOffset(bArr, bArr2, i);
            i += digestSize;
        }
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            XMSSUtil.copyBytesAtOffset(bArr, this.j.get(i2).getValue(), i);
            i += digestSize;
        }
        return bArr;
    }
}
