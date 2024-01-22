package org.bouncycastle.pqc.crypto.xmss;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
/* loaded from: classes13.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public final e f15340a;
    public final b b;
    public byte[] c;
    public byte[] d;

    public c(e eVar) {
        Objects.requireNonNull(eVar, "params == null");
        this.f15340a = eVar;
        int b = eVar.b();
        this.b = new b(eVar.a(), b);
        this.c = new byte[b];
        this.d = new byte[b];
    }

    public final byte[] a(byte[] bArr, int i, int i2, OTSHashAddress oTSHashAddress) {
        int b = this.f15340a.b();
        Objects.requireNonNull(bArr, "startHash == null");
        if (bArr.length != b) {
            throw new IllegalArgumentException("startHash needs to be " + b + "bytes");
        }
        Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
        Objects.requireNonNull(oTSHashAddress.toByteArray(), "otsHashAddress byte array == null");
        int i3 = i + i2;
        if (i3 <= this.f15340a.f() - 1) {
            if (i2 == 0) {
                return bArr;
            }
            byte[] a2 = a(bArr, i, i2 - 1, oTSHashAddress);
            OTSHashAddress oTSHashAddress2 = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(oTSHashAddress.c()).withChainAddress(oTSHashAddress.a()).withHashAddress(i3 - 1).withKeyAndMask(0).build();
            byte[] d = this.b.d(this.d, oTSHashAddress2.toByteArray());
            byte[] d2 = this.b.d(this.d, ((OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress2.getLayerAddress()).withTreeAddress(oTSHashAddress2.getTreeAddress()).withOTSAddress(oTSHashAddress2.c()).withChainAddress(oTSHashAddress2.a()).withHashAddress(oTSHashAddress2.b()).withKeyAndMask(1).build()).toByteArray());
            byte[] bArr2 = new byte[b];
            for (int i4 = 0; i4 < b; i4++) {
                bArr2[i4] = (byte) (a2[i4] ^ d2[i4]);
            }
            return this.b.a(d, bArr2);
        }
        throw new IllegalArgumentException("max chain length must not be greater than w");
    }

    public final List<Integer> b(byte[] bArr, int i, int i2) {
        Objects.requireNonNull(bArr, "msg == null");
        if (i == 4 || i == 16) {
            int log2 = XMSSUtil.log2(i);
            if (i2 <= (bArr.length * 8) / log2) {
                ArrayList arrayList = new ArrayList();
                for (int i3 : bArr) {
                    for (int i4 = 8 - log2; i4 >= 0; i4 -= log2) {
                        arrayList.add(Integer.valueOf((i3 >> i4) & (i - 1)));
                        if (arrayList.size() == i2) {
                            return arrayList;
                        }
                    }
                }
                return arrayList;
            }
            throw new IllegalArgumentException("outLength too big");
        }
        throw new IllegalArgumentException("w needs to be 4 or 16");
    }

    public final byte[] c(int i) {
        if (i < 0 || i >= this.f15340a.c()) {
            throw new IllegalArgumentException("index out of bounds");
        }
        return this.b.d(this.c, XMSSUtil.toBytesBigEndian(i, 32));
    }

    public b d() {
        return this.b;
    }

    public e e() {
        return this.f15340a;
    }

    public f f(OTSHashAddress oTSHashAddress) {
        Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
        byte[][] bArr = new byte[this.f15340a.c()];
        for (int i = 0; i < this.f15340a.c(); i++) {
            oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(oTSHashAddress.c()).withChainAddress(i).withHashAddress(oTSHashAddress.b()).withKeyAndMask(oTSHashAddress.getKeyAndMask()).build();
            bArr[i] = a(c(i), 0, this.f15340a.f() - 1, oTSHashAddress);
        }
        return new f(this.f15340a, bArr);
    }

    public f g(byte[] bArr, g gVar, OTSHashAddress oTSHashAddress) {
        Objects.requireNonNull(bArr, "messageDigest == null");
        if (bArr.length == this.f15340a.b()) {
            Objects.requireNonNull(gVar, "signature == null");
            Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
            List<Integer> b = b(bArr, this.f15340a.f(), this.f15340a.d());
            int i = 0;
            for (int i2 = 0; i2 < this.f15340a.d(); i2++) {
                i += (this.f15340a.f() - 1) - b.get(i2).intValue();
            }
            b.addAll(b(XMSSUtil.toBytesBigEndian(i << (8 - ((this.f15340a.e() * XMSSUtil.log2(this.f15340a.f())) % 8)), (int) Math.ceil((this.f15340a.e() * XMSSUtil.log2(this.f15340a.f())) / 8.0d)), this.f15340a.f(), this.f15340a.e()));
            byte[][] bArr2 = new byte[this.f15340a.c()];
            for (int i3 = 0; i3 < this.f15340a.c(); i3++) {
                oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(oTSHashAddress.c()).withChainAddress(i3).withHashAddress(oTSHashAddress.b()).withKeyAndMask(oTSHashAddress.getKeyAndMask()).build();
                bArr2[i3] = a(gVar.a()[i3], b.get(i3).intValue(), (this.f15340a.f() - 1) - b.get(i3).intValue(), oTSHashAddress);
            }
            return new f(this.f15340a, bArr2);
        }
        throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
    }

    public byte[] h() {
        return XMSSUtil.cloneArray(this.d);
    }

    public byte[] i(byte[] bArr, OTSHashAddress oTSHashAddress) {
        return this.b.d(bArr, ((OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(oTSHashAddress.c()).build()).toByteArray());
    }

    public void j(byte[] bArr, byte[] bArr2) {
        Objects.requireNonNull(bArr, "secretKeySeed == null");
        if (bArr.length != this.f15340a.b()) {
            throw new IllegalArgumentException("size of secretKeySeed needs to be equal to size of digest");
        }
        Objects.requireNonNull(bArr2, "publicSeed == null");
        if (bArr2.length != this.f15340a.b()) {
            throw new IllegalArgumentException("size of publicSeed needs to be equal to size of digest");
        }
        this.c = bArr;
        this.d = bArr2;
    }

    public g k(byte[] bArr, OTSHashAddress oTSHashAddress) {
        Objects.requireNonNull(bArr, "messageDigest == null");
        if (bArr.length == this.f15340a.b()) {
            Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
            List<Integer> b = b(bArr, this.f15340a.f(), this.f15340a.d());
            int i = 0;
            for (int i2 = 0; i2 < this.f15340a.d(); i2++) {
                i += (this.f15340a.f() - 1) - b.get(i2).intValue();
            }
            b.addAll(b(XMSSUtil.toBytesBigEndian(i << (8 - ((this.f15340a.e() * XMSSUtil.log2(this.f15340a.f())) % 8)), (int) Math.ceil((this.f15340a.e() * XMSSUtil.log2(this.f15340a.f())) / 8.0d)), this.f15340a.f(), this.f15340a.e()));
            byte[][] bArr2 = new byte[this.f15340a.c()];
            for (int i3 = 0; i3 < this.f15340a.c(); i3++) {
                oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress()).withTreeAddress(oTSHashAddress.getTreeAddress()).withOTSAddress(oTSHashAddress.c()).withChainAddress(i3).withHashAddress(oTSHashAddress.b()).withKeyAndMask(oTSHashAddress.getKeyAndMask()).build();
                bArr2[i3] = a(c(i3), 0, b.get(i3).intValue(), oTSHashAddress);
            }
            return new g(this.f15340a, bArr2);
        }
        throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
    }
}
